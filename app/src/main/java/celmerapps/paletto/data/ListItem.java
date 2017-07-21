package celmerapps.paletto.data;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.v7.app.AppCompatActivity;

import celmerapps.paletto.view.DetailActivity;
import celmerapps.paletto.view.ListActivity;

/**
 * Created by Mati on 2017-07-19.
 */

public class ListItem extends AppCompatActivity{

    private Uri img_uri;
    private boolean hasThumbnail = true;

    public ListItem(Uri img_uri) {
        this.img_uri = img_uri;
    }

    public String getName(){

// ===========================================
//        Cursor returnCursor =
//                getContentResolver().query(img_uri, null, null, null, null);
//
//        int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
//        returnCursor.moveToFirst();
//        return returnCursor.getString(nameIndex);
// ===========================================
//        return getRealPath().toString();

        String[] parts = getRealPath().toString().split("/");
        if(parts.length-1 >= 0)
            return parts[parts.length-1];
        else
            return "no-name";

    }

    public Uri getImg_uri() {
        return img_uri;
    }

    public String getRealPath() {
        Context context = ListActivity.getContext();
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(this.getImg_uri(),  proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public String getRealPathOfThumbnail() {
        Context context = ListActivity.getContext();
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Thumbnails.DATA };
            cursor = context.getContentResolver().query(this.getImg_uri(),  proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public Bitmap getBitmapOfThumbnail(){
        Bitmap myBitmap = BitmapFactory.decodeFile(getRealPathOfThumbnail());
        return myBitmap;
    }

    public Bitmap getBitmap(){
        Bitmap myBitmap = BitmapFactory.decodeFile(getRealPath());
        return myBitmap;
    }

    public void setImg_uri(Uri img_uri) {
        this.img_uri = img_uri;
    }

    public boolean hasThumbnail() {
        return hasThumbnail;
    }

    public void setHasThumbnail(boolean hasThumbnail) {
        this.hasThumbnail = hasThumbnail;
    }

    public void setNoThumbnail() {
        this.hasThumbnail = false;
    }
}
