package com.example.mervesimsek.vehicleapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.mervesimsek.vehicleapp.dummy.DummyContent;

import java.io.IOException;
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

    SearchView searchView;
    RecyclerView recyclerView;
    Context currentContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        this.currentContext = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabnew);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ItemListActivity.this, AddCarActivity.class);
                startActivity(intent);
            }
        });

        this.recyclerView = (RecyclerView) findViewById(R.id.item_list);
        assert this.recyclerView != null;


        //TODO: veritabanı ile baglantı kuruldu ve select sorgusu calıstırıldı.
        Cursor vehicleDataFromDB = DummyContent.setupVehicleDatabase(this.currentContext);

        //TODO: olusturulan sorgu sonucuna gore Vehicle tipinde bir list olusturuldu.
        DummyContent.createVehicleList(vehicleDataFromDB);
        this.LoadDataSourceRecyclerView();

        if (findViewById(R.id.item_detail_container) != null) {
            mTwoPane = true;
        }
    }


    /**
     * Search
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        final MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.menuSearch);
        searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String query) {
                DummyContent.searchVehicleList(currentContext,query);
                LoadDataSourceRecyclerView();
                return true;

            }
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(DummyContent.vehicleModelList));

    }

    public void LoadDataSourceRecyclerView() {
        this.setupRecyclerView(this.recyclerView);
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<DummyContent.VehicleModel> mValues;


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
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.vehicleViewHolder = mValues.get(position);
            holder.mIdView.setText(mValues.get(position).nickname);
            holder.mContentView.setText(mValues.get(position).brand);
            holder.mModelYear.setText(mValues.get(position).modelyear);
            holder.mCircle.setText(mValues.get(position).nickname.substring(0, 1).toUpperCase());
            ((GradientDrawable) holder.mCircle.getBackground()).setColor(mValues.get(position).nicknameColor);
            //TODO: detay acilan kisim. Butona basınca buradan detay ekranını acıyor.
            holder.mDetail.setOnClickListener(new Button.OnClickListener() {
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

            holder.mDelete.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Context mContext = v.getContext();

                    AlertDialog.Builder builder = new AlertDialog.Builder(ItemListActivity.this);
                    builder
                            .setTitle("Delete record")
                            .setMessage("Are you sure?")
                            .setIcon(R.drawable.alert)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    DummyContent.deleteRow(holder.vehicleViewHolder.id, mContext);
                                       mValues.remove(position);
                                       notifyDataSetChanged();
                                    Toast.makeText(ItemListActivity.this, "Deleted",
                                            Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("No", null)
                            .show();

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
            public final Button mDelete;
            public final TextView mModelYear;
            public final Button mDetail;
            public final TextView mNickname;
            public final TextView mCircle;
            public DummyContent.VehicleModel vehicleViewHolder;


            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.id);
                mContentView = (TextView) view.findViewById(R.id.content);
                mDelete = (Button) view.findViewById(R.id.optiondetail);
                mModelYear = (TextView) view.findViewById(R.id.modelyearcontent);
                mDetail = (Button) view.findViewById(R.id.option);
                mNickname = (TextView) view.findViewById(R.id.nicknamelabel);
                mCircle = (TextView) view.findViewById(R.id.circle);
                // TODO : ItemListActivity içindeki circle Random renk ayarı
                if (vehicleViewHolder != null)
                {((GradientDrawable) mCircle.getBackground()).setColor(vehicleViewHolder.nicknameColor);}

            }




            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }

    // TODO : Ekranın başka bir yerine dokunarak klavye kapatma
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            view.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + view.getLeft() - scrcoords[0];
            float y = ev.getRawY() + view.getTop() - scrcoords[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

}

