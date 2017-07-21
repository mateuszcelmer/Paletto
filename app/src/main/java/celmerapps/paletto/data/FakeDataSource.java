package celmerapps.paletto.data;

import android.net.Uri;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Mati on 2017-07-19.
 */

public class FakeDataSource implements DataSourceInterface {

    public static final int SIZE_OF_COLLETION = 0;
    private Random random;

    private final String[] img_sources = {
            "@mipmap/ic_launcher"
    };

    private final Uri[] img_uris = {
            Uri.parse("IMG_0023142.jpg"),
            Uri.parse("IMG_2129572.jpg"),
            Uri.parse("IMG_ABC5540.jpg"),
            Uri.parse("IMG_0000020.jpg"),
            Uri.parse("IMG_000900A.jpg"),
            Uri.parse("IMG_0027283.jpg")
    };

    public FakeDataSource() {
        random = new Random();
    }

    @Override
    public List<ListItem> getListOfData() {

        ArrayList<ListItem> listOfData = new ArrayList<>();
        random = new Random();

        for (int i = 0; i < SIZE_OF_COLLETION; i++) {
            listOfData.add(
                    createNewListItem()
            );
        }

        return listOfData;
    }

    @Override
    public ListItem createNewListItem() {

        int randOne = random.nextInt(6);
        ListItem listItem = new ListItem(
                img_uris[0]
        );

        listItem.setNoThumbnail();

        return listItem;
    }

    @Override
    public void addListItem(ListItem listItem) {
        //dodatnie do fakeListy
    }

    @Override
    public void deleteListItem(ListItem listItem) {

    }
}
