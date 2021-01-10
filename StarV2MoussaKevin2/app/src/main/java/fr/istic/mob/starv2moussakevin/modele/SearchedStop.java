package fr.istic.mob.starv2moussakevin.modele;

import android.database.Cursor;

public class SearchedStop {

    private String stop_name;

    public SearchedStop(String stop_name) {
        this.stop_name = stop_name;
    }

    public static SearchedStop populateSearchResults(Cursor cursor) {
        SearchedStop searchedStop = null;
        try {
            String stop_name = cursor.getString(cursor.getColumnIndex(StarContract.Stops.StopColumns.NAME));
            searchedStop = new SearchedStop(stop_name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return searchedStop;
    }

    public String getStop_name() {
        return stop_name;
    }

    public void setStop_name(String stop_name) {
        this.stop_name = stop_name;
    }
}
