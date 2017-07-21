package celmerapps.paletto.view;

import android.net.Uri;
import android.view.View;

import java.util.List;

import celmerapps.paletto.data.ListItem;

/**
 * Created by Mati on 2017-07-19.
 */

public interface ViewInterface {

    void setUpAdapterAndView(List<ListItem> listOfData);

    void startDetailActivity(Uri uri, View viewRoot);

    void addListItem(ListItem listItem);

    void deleteListItemAt(int position);
}
