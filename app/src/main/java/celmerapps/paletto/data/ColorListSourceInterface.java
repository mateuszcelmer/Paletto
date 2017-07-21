package celmerapps.paletto.data;

import java.util.List;

/**
 * Created by Mati on 2017-07-20.
 */

public interface ColorListSourceInterface {

    List<ColorItem> getListOfColors(ListItem listItem);

    ColorItem createNewColorItem(int color_int, int textColor, String info);
}
