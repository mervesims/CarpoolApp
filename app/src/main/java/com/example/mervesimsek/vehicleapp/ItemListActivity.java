package com.example.mervesimsek.vehicleapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.mervesimsek.vehicleapp.dummy.DummyContent;

import java.util.List;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ItemListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentback = new Intent(ItemListActivity.this, MainActivity.class);
                startActivity(intentback);
                finish();
            }
        });
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabnew);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(ItemListActivity.this,AddCarActivity.class);
                startActivity(intent);
            }
        });

        View recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;


        //TODO: veritabanı ile baglantı kuruldu ve select sorgusu calıstırıldı.
        Cursor vehicleDataFromDB = DummyContent.setupVehicleDatabase(this);

        //TODO: olusturulan sorgu sonucuna gore Vehicle tipinde bir list olusturuldu.
        DummyContent.createVehicleList(vehicleDataFromDB);


        setupRecyclerView((RecyclerView) recyclerView);

        if (findViewById(R.id.item_detail_container) != null) {
            mTwoPane = true;
        }
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(DummyContent.vehicleModelList));
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        //private final List<DummyContent.DummyItem> mValues;
        private final List<DummyContent.VehicleModel> mValues;

        /*public SimpleItemRecyclerViewAdapter(List<DummyContent.DummyItem> items) {
            mValues = items;
        }*/

        public SimpleItemRecyclerViewAdapter(List<DummyContent.VehicleModel> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list_content, parent, false);
            return new ViewHolder(view);
        }


        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.vehicleViewHolder = mValues.get(position);
            holder.mIdView.setText(mValues.get(position).nickname);
            holder.mContentView.setText(mValues.get(position).brand);

            //TODO: detay acilan kisim. Butona basınca buradan detay ekranını acıyor.
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {

                        Bundle arguments = new Bundle();
                       arguments.putString(ItemDetailFragment.ARG_ITEM_ID, holder.vehicleViewHolder.brand);
                        ItemDetailFragment fragment = new ItemDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.item_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, ItemDetailActivity.class);

                        //TODO: burada cagirilan ekrana parametre geciriyor. Bir model yaptık ve modeli diger ekranda kullanacagız demektir bu. list ve detail gibi dusunebiliriz.

                        //diger ekrana veri gondermek icin ekranlar arasi iletisimde java bundle yapisini kullandigi icin bunu tanimliyoruz.
                        Bundle vehicleBundle = new Bundle();

                        // Elimizdeki var olan modeli diger ekranda kullabilmek icin bunu diger ekrana gondermeye ihtiyacimiz var.
                        // Bu yuzden elimizdeki modeli Serializable yaparak string json haline cevirip PUT kelimesi ile bundle icerisine atiyoruz.
                        vehicleBundle.putSerializable("UniqueObjectName", holder.vehicleViewHolder);

                        //serialize ettigimiz modelimizi acacagimiz detay ekraninin icerisine koyuyoruz. Sebebi ise detay ekraninin icerisinden buna ulasabilmek.
                        intent.putExtras(vehicleBundle);

                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mIdView;
            public final TextView mContentView;
            public DummyContent.VehicleModel vehicleViewHolder;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.id);
                mContentView = (TextView) view.findViewById(R.id.content);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }
}

