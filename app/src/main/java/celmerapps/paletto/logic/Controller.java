package celmerapps.paletto.logic;

import android.view.View;

import celmerapps.paletto.data.ColorListSourceInterface;
import celmerapps.paletto.data.DataSourceInterface;
import celmerapps.paletto.data.ListItem;
import celmerapps.paletto.view.DetailViewInterface;
import celmerapps.paletto.view.ViewInterface;

/**
 * Created by Mati on 2017-07-19.
 */

public class Controller {

    // ======================== DataSource & ListActivity

    private DataSourceInterface dataSource;
    private ViewInterface view;

    public Controller(ViewInterface view, DataSourceInterface dataSource) {
        this.dataSource = dataSource;
        this.view = view;

        getListFromDataSource();
    }

    public void getListFromDataSource() {
        view.setUpAdapterAndView(
                dataSource.getListOfData()
        );
    }

    public void onListItemClick(ListItem selectedItem, View viewRoot) {
        view.startDetailActivity(
                selectedItem.getImg_uri(),
                viewRoot
        );
    }

    public void addItemToList(ListItem listItem) {
        this.dataSource.addListItem(listItem);
        this.view.addListItem(listItem);
    }

    // ============================= Colory & DetailActivity

    private ColorListSourceInterface colorListSource;
    private DetailViewInterface detailView;

    public Controller(DetailViewInterface detailView, ColorListSourceInterface colorListSource) {
        this.colorListSource = colorListSource;
        this.detailView = detailView;

        getColorListFromColorListSource();
    }

    public void getColorListFromColorListSource() {
        detailView.setUpAdapterAndView(
                colorListSource.getListOfColors(
                        detailView.getListItem()
                )
        );
    }

    public void onListItemSwiped(int position, ListItem listItem) {
        dataSource.deleteListItem(listItem);
        view.deleteListItemAt(position);
    }
}
