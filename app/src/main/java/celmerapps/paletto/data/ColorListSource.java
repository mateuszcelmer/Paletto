package celmerapps.paletto.data;

import android.app.ListActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import celmerapps.paletto.view.DetailActivity;

/**
 * Created by Mati on 2017-07-20.
 */

public class ColorListSource extends AppCompatActivity implements ColorListSourceInterface {

    @Override
    public List<ColorItem> getListOfColors(ListItem listItem) {

        Palette.PaletteAsyncListener paletteListener = new Palette.PaletteAsyncListener() {
            public void onGenerated(Palette palette) {
                // access palette colors here
            }
        };

        String realpath = listItem.getRealPath();
        Bitmap myBitmap = BitmapFactory.decodeFile(realpath);

        if (myBitmap != null && !myBitmap.isRecycled()) {
            Palette palette = Palette.from(myBitmap).generate();
        }

        Palette palette = new Palette.Builder(myBitmap).generate();

        Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
        Palette.Swatch vibrantLightSwatch = palette.getLightVibrantSwatch();
        Palette.Swatch vibrantDarkSwatch = palette.getDarkVibrantSwatch();
        Palette.Swatch mutedSwatch = palette.getMutedSwatch();
        Palette.Swatch mutedLightSwatch = palette.getLightMutedSwatch();
        Palette.Swatch mutedDarkSwatch = palette.getDarkMutedSwatch();

        ArrayList<Integer> colors = new ArrayList<>();
        ArrayList<Integer> textColors = new ArrayList<>();
        ArrayList<String> info = new ArrayList<>();

        if (vibrantSwatch != null)
        {
            colors.add(vibrantSwatch.getRgb());
            textColors.add(vibrantSwatch.getTitleTextColor());
            info.add("vibrant");
        }
        if (vibrantLightSwatch != null)
        {
            colors.add(vibrantLightSwatch.getRgb());
            textColors.add(vibrantLightSwatch.getTitleTextColor());
            info.add("vibrant light");
        }
        if (vibrantDarkSwatch != null)
        {
            colors.add(vibrantDarkSwatch.getRgb());
            textColors.add(vibrantDarkSwatch.getTitleTextColor());
            info.add("vibrant dark");
        }
        if (mutedSwatch != null)
        {
            colors.add(mutedSwatch.getRgb());
            textColors.add(mutedSwatch.getTitleTextColor());
            info.add("muted");
        }
        if (mutedLightSwatch != null)
        {
            colors.add(mutedLightSwatch.getRgb());
            textColors.add(mutedLightSwatch.getTitleTextColor());
            info.add("muted light");
        }
        if (mutedDarkSwatch != null)
        {
            colors.add(mutedDarkSwatch.getRgb());
            textColors.add(mutedDarkSwatch.getTitleTextColor());
            info.add("muted dark");
        }

        ArrayList<ColorItem> listOfColors = new ArrayList<>();

        for (int i = 0; i < colors.size(); i++) {
            listOfColors.add(
                    createNewColorItem(
                            colors.get(i),
                            textColors.get(i),
                            info.get(i)
                    )
            );
        }

        // separator
        ColorItem separator = new ColorItem(Color.LTGRAY, Color.DKGRAY, "");
        separator.setText("More swatches:");
        listOfColors.add(separator);

        List<Palette.Swatch> listOfSwatches = palette.getSwatches();

        for (int i=0; i<listOfSwatches.size(); i++)
        {
            listOfColors.add(
                    createNewColorItem(
                            listOfSwatches.get(i).getRgb(),
                            listOfSwatches.get(i).getTitleTextColor(),
                            "population: "+listOfSwatches.get(i).getPopulation()
                    )
            );
        }

        return listOfColors;
    }

    @Override
    public ColorItem createNewColorItem(int color_int, int textColor, String info) {
        return new ColorItem(color_int, textColor, info);
    }
}
