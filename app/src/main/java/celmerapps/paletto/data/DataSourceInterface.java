package celmerapps.paletto.data;

import java.util.List;

/**
 * Created by Mati on 2017-07-19.
 */

public interface DataSourceInterface {

    List<ListItem> getListOfData();

    ListItem createNewListItem();

    void addListItem(ListItem listItem);

    void deleteListItem(ListItem listItem);
}
