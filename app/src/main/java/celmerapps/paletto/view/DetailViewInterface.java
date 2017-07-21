package celmerapps.paletto.view;

import java.util.List;

import celmerapps.paletto.data.ColorItem;
import celmerapps.paletto.data.ListItem;

/**
 * Created by Mati on 2017-07-20.
 */

public interface DetailViewInterface {

    void setUpAdapterAndView(List<ColorItem> listOfColors);

    ListItem getListItem();

}
