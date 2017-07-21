package celmerapps.paletto.data;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Mati on 2017-07-20.
 */

public class FakeColorListSource implements ColorListSourceInterface {

    public static final int SIZE_OF_COLLECTION_LIST = 12;
    private Random random;

    private final String[] color_codes = {
            "#EB381C",
            "#EB841C",
            "#167792",
            "#15AD43",
            "#BB220A",
            "#BB630A",
            "#0A5D74",
            "#078A2F",
            "#941400",
            "#944A00",
            "#03485C",
            "#006D21"
    };

    @Override
    public List<ColorItem> getListOfColors(ListItem listItem) {

        ArrayList<ColorItem> listOfColors = new ArrayList<>();

        for(int i=0; i<SIZE_OF_COLLECTION_LIST; i++)
        {
            listOfColors.add(
                    createNewColorItem(i, Color.BLACK, "")
            );
        }

        return listOfColors;
    }

    @Override
    public ColorItem createNewColorItem(int color_int, int textColor, String info) {

        random = new Random();

        int rand = random.nextInt(12);
        ColorItem colorItem = new ColorItem(
                color_codes[rand]
        );

        return colorItem;
    }
}
