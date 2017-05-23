package com.example.mervesimsek.vehicleapp.controller;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mervesimsek.vehicleapp.common.BaseController;
import com.example.mervesimsek.vehicleapp.common.CommonObjectManager;
import com.example.mervesimsek.vehicleapp.common.ConstraintStrings;
import com.example.mervesimsek.vehicleapp.dal.DatabaseService;
import com.example.mervesimsek.vehicleapp.dal.VehicleDAL;
import com.example.mervesimsek.vehicleapp.model.VehicleModel;
import com.example.mervesimsek.vehicleapp.R;

import java.util.List;

import static com.example.mervesimsek.vehicleapp.common.CommonObjectManager.OperationStatus.detail;
import static com.example.mervesimsek.vehicleapp.common.CommonObjectManager.OperationStatus.insert;
import static com.example.mervesimsek.vehicleapp.common.CommonObjectManager.OperationStatus.update;


/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link VehicleListController} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class VehicleListController extends BaseController {
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    SearchView searchView;
    RecyclerView recyclerView;


    @Override
    protected void customOnCreate(@Nullable Bundle savedInstanceState, int layoutResID, int toolbarResID) {
        super.customOnCreate(savedInstanceState, R.layout.activity_item_list, R.id.toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabnew);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddActivityScreen();
            }
        });

        this.recyclerView = (RecyclerView) findViewById(R.id.item_list);
        assert this.recyclerView != null;


        if (findViewById(R.id.item_detail_container) != null) {
            mTwoPane = true;
        }
    }

    public void getData() {
        List<VehicleModel> dataList = VehicleDAL.getInstance().GetVehicleList();
        this.LoadDataSourceRecyclerView(dataList);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (CommonObjectManager.Status != detail) {
            this.getData();
        }
    }

    private void showAddActivityScreen() {
        CommonObjectManager.Status = insert;
        Intent intent = new Intent(VehicleListController.this, VehicleDetailActivityController.class);
        startActivity(intent);
    }

    /**
     * Search işlemleri
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        final MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.menuSearch);
        searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String searchValue) {
                List<VehicleModel> dataListByFilter = VehicleDAL.getInstance().GetVehicleListBy(searchValue);
                LoadDataSourceRecyclerView(dataListByFilter);
                return true;
            }

            public boolean onQueryTextSubmit(String query) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView, List<VehicleModel> dataList) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(dataList));
    }

    public void LoadDataSourceRecyclerView(List<VehicleModel> dataList) {
        this.setupRecyclerView(this.recyclerView, dataList);
    }

    public class SimpleItemRecyclerViewAdapter extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {
        private final List<VehicleModel> mValues;

        public SimpleItemRecyclerViewAdapter(List<VehicleModel> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_content, parent, false);
            return new ViewHolder(view);
        }

        private void showVehicleDetailActivityController(Context context) {
             CommonObjectManager.Status = update;
            Intent intent = new Intent(context, VehicleDetailActivityController.class);
            context.startActivity(intent);

        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.vehicleViewHolder = mValues.get(position);
            holder.mIdView.setText(mValues.get(position).Nickname);
            holder.mContentView.setText(mValues.get(position).BrandName);
            holder.mModelYear.setText(mValues.get(position).ModelYear);
            holder.mCircle.setText(mValues.get(position).Nickname.substring(0, 1).toUpperCase());
            ((GradientDrawable) holder.mCircle.getBackground()).setColor(mValues.get(position).NicknameColor);
            //TODO: detay acilan kisim. Butona basınca buradan detay ekranını acıyor.
            holder.mDetail.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CommonObjectManager.Status = detail;
                    CommonObjectManager.VehicleListSelectedRowModel = holder.vehicleViewHolder;
                    showVehicleDetailActivityController(currentContext);
                }
            });
            //TODO:Delete işlemleri ve alert dialog
            holder.mDelete.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Context mContext = v.getContext();
                    //Are you sure? Alert Dialog.
                    AlertDialog.Builder builder = new AlertDialog.Builder(VehicleListController.this);
                    builder.setTitle(ConstraintStrings.DeleteTitle)
                            .setMessage(ConstraintStrings.DeleteQuestion)
                            .setIcon(R.drawable.alert)
                            .setPositiveButton(ConstraintStrings.Yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    VehicleDAL.getInstance().DeleteVehicle(holder.vehicleViewHolder.Id);
                                    mValues.remove(position);
                                    notifyDataSetChanged();

                                    Toast.makeText(VehicleListController.this, ConstraintStrings.DeletedError, Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton(ConstraintStrings.No, null)
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
            public VehicleModel vehicleViewHolder;


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
                // TODO : VehicleListController içindeki circle Random renk ayarı
                if (vehicleViewHolder != null) {
                    ((GradientDrawable) mCircle.getBackground()).setColor(vehicleViewHolder.NicknameColor);
                }
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }
}

