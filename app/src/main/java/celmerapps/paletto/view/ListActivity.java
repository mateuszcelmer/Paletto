package celmerapps.paletto.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import celmerapps.paletto.R;
import celmerapps.paletto.data.FakeDataSource;
import celmerapps.paletto.data.ListItem;
import celmerapps.paletto.logic.Controller;

public class ListActivity extends AppCompatActivity implements ViewInterface {

    private static final String EXTRA_IMAGE_SRC = "EXTRA_IMAGE_SRC";
    private static final int PICK_IMAGE = 0;

    private List<ListItem> listOfData;

    private LayoutInflater layoutInflater;
    private RecyclerView recyclerView;
    private CustomAdapter adapter;

    private Controller controller;

    private static Context mContext;
    public static Context getContext() {
        //  return instance.getApplicationContext();
        return mContext;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_frame);
        mContext = getApplicationContext();

        recyclerView = (RecyclerView) findViewById(R.id.rec_list_activity);
        layoutInflater = getLayoutInflater();

        controller = new Controller(this, new FakeDataSource());

        if (listOfData.isEmpty())
            ((TextView) findViewById(R.id.tv_info_activity_list)).setText(R.string.info_when_empty);
        else
            ((TextView) findViewById(R.id.tv_info_activity_list)).setText(R.string.info_when_notempty);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(createHelperCallback());
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    @Override
    public void setUpAdapterAndView(List<ListItem> listOfData) {

        this.listOfData = listOfData;

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new CustomAdapter();
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void startDetailActivity(Uri uri, View viewRoot) {
        Intent intent = new Intent(this, DetailActivity.class);
        //Toast.makeText(this,uri.toString(),Toast.LENGTH_LONG).show();
        intent.putExtra(EXTRA_IMAGE_SRC, uri.toString());
        startActivity(intent);
    }

    @Override
    public void addListItem(ListItem listItem) {
        listOfData.add(listItem);
    }

    @Override
    public void deleteListItemAt(int position) {
        listOfData.remove(position);
        adapter.notifyItemRemoved(position);
    }

    public void onAddButtonClick(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
        ((TextView)findViewById(R.id.tv_info_activity_list)).setText(R.string.info_when_notempty);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == PICK_IMAGE) {
            if (resultCode == RESULT_OK) {
                if (intent != null) {
                    final Uri uri = intent.getData();
                    //useImage(uri);
                    //Toast.makeText(this, "uri: "+ new ListItem(uri).getImg_name(), Toast.LENGTH_LONG).show();
                    controller.addItemToList(new ListItem(uri));
                }
            }
            super.onActivityResult(requestCode, resultCode, intent);

        }
    }

    private class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {


        @Override
        public CustomAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = layoutInflater.inflate(R.layout.item_data, parent, false);
            return new CustomViewHolder(v);
        }

        @Override
        public void onBindViewHolder(CustomAdapter.CustomViewHolder holder, int position) {
            ListItem currentItem = listOfData.get(position);

            holder.name.setText(
                    currentItem.getName()
            );

            if (currentItem.hasThumbnail()) {

                //image
                Glide
                        .with(getContext())
                        .load(currentItem.getRealPathOfThumbnail())
                        .into(holder.thumbnail);

            } else
                holder.thumbnail.setImageResource(
                        R.mipmap.ic_launcher_round
                );
        }

        @Override
        public int getItemCount() {
            return listOfData.size();
        }

        class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private ImageView thumbnail;
            private TextView name;
            private ViewGroup container;

            public CustomViewHolder(View itemView) {
                super(itemView);

                this.thumbnail = itemView.findViewById(R.id.imv_thumbnail_list_item);
                this.name = itemView.findViewById(R.id.tv_name_list_item);
                this.container = itemView.findViewById(R.id.root_list_item);
                this.container.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {

                ListItem listItem = listOfData.get(
                        this.getAdapterPosition()
                );

                controller.onListItemClick(
                        listItem,
                        view
                );

            }
        }
    }

    private ItemTouchHelper.Callback createHelperCallback(){

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT ) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();

                controller.onListItemSwiped(
                        position,
                        listOfData.get(position)
                );

                Toast.makeText(getContext(),"deleted from the list",Toast.LENGTH_SHORT).show();

                if(listOfData.size() == 0)
                    ((TextView) findViewById(R.id.tv_info_activity_list)).setText(R.string.info_when_empty);

            }
        };
    return simpleItemTouchCallback;
    }
}
