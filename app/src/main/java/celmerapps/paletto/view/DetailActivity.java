package celmerapps.paletto.view;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import celmerapps.paletto.R;
import celmerapps.paletto.data.ColorItem;
import celmerapps.paletto.data.ColorListSource;
import celmerapps.paletto.data.ListItem;
import celmerapps.paletto.logic.Controller;

/**
 * Created by Mati on 2017-07-19.
 */

public class DetailActivity extends AppCompatActivity implements DetailViewInterface {

    private static final String EXTRA_IMAGE_SRC = "EXTRA_IMAGE_SRC";

    private List<ColorItem> listOfColors;
    private ListItem listItem;

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
        setContentView(R.layout.activity_detail);
        mContext = getApplicationContext();

        TextView name = (TextView) findViewById(R.id.tv_name_detail_activity);
        ImageView image = (ImageView) findViewById(R.id.imv_image_detail_activity);

        // get extras from listactivity
        Intent i = getIntent();
        String image_uri = (String) i.getSerializableExtra(EXTRA_IMAGE_SRC);

        //name
        listItem = new ListItem(Uri.parse(image_uri));
        name.setText(listItem.getName());

        //image
        Glide
                .with(this)
                .load(image_uri)
                .into(image);

        recyclerView = (RecyclerView) findViewById(R.id.rec_list_of_colors_detail_activity);
        layoutInflater = getLayoutInflater();

        controller = new Controller(this, new ColorListSource());
    }

    @Override
    public void setUpAdapterAndView(List<ColorItem> listOfColors) {
        this.listOfColors = listOfColors;

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new CustomAdapter();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public ListItem getListItem() {
        return listItem;
    }

    private class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

        @Override
        public CustomAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = layoutInflater.inflate(R.layout.color_item, parent, false);
            return new CustomViewHolder(v);
        }

        @Override
        public void onBindViewHolder(CustomAdapter.CustomViewHolder holder, int position) {
            ColorItem currentColorItem = listOfColors.get(position);
            holder.color_code.setText(currentColorItem.getText());
            holder.info.setText(currentColorItem.getInfo());
            holder.color_code.setTextColor(currentColorItem.getTextColor());
            holder.info.setTextColor(currentColorItem.getTextColor());
            holder.container.setBackground(currentColorItem.getColor());
        }

        @Override
        public int getItemCount() {
            return listOfColors.size();
        }

        class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private TextView color_code;
            private TextView info;
            private ViewGroup container;

            public CustomViewHolder(View itemView) {
                super(itemView);

                this.info = itemView.findViewById(R.id.tv_info_color_item);
                this.color_code = itemView.findViewById(R.id.tv_code_color_item);
                this.container = itemView.findViewById(R.id.root_color_item);
                this.container.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {

                ColorItem colorItem = listOfColors.get(
                        this.getAdapterPosition()
                );
                if (colorItem.isSwatch()) {
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("color code", colorItem.getText());
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(DetailActivity.getContext(), "code " + colorItem.getText() + " has been copied!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
