package fr.istic.mob.starv2moussakevin;

import android.app.SearchManager;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentTransaction;
import java.util.ArrayList;

import fr.istic.mob.starv2moussakevin.adapters.RoutesStopAdapter;
import fr.istic.mob.starv2moussakevin.adapters.RoutesView;
import fr.istic.mob.starv2moussakevin.Fragments.Frag1;
import fr.istic.mob.starv2moussakevin.Fragments.Frag2;
import fr.istic.mob.starv2moussakevin.Fragments.Frag3;
import fr.istic.mob.starv2moussakevin.Fragments.Fragment1;
import fr.istic.mob.starv2moussakevin.Fragments.Fragment2;
import fr.istic.mob.starv2moussakevin.Fragments.Fragment3;
import fr.istic.mob.starv2moussakevin.Fragments.Fragment4;
import fr.istic.mob.starv2moussakevin.modele.RouteTrip;
import fr.istic.mob.starv2moussakevin.modele.Calendar;
import fr.istic.mob.starv2moussakevin.modele.SearchedStop;
import fr.istic.mob.starv2moussakevin.modele.StarContract;
import fr.istic.mob.starv2moussakevin.modele.RouteStop;


public class MainActivity extends AppCompatActivity implements Frag1, Frag2, Frag3, androidx.appcompat.widget.SearchView.OnQueryTextListener, androidx.appcompat.widget.SearchView.OnCloseListener, AdapterView.OnItemClickListener {

    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;
    private Fragment4 fragment4;
    private ArrayList<SearchedStop> searchedStops;
    private ArrayList<RouteStop> routeStops;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState == null) {
                fragment1 = new Fragment1();
                fragment1.setArguments(getIntent().getExtras());

                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment1).commit();
            }
        }
        listView = findViewById(R.id.list_view);
        listView.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_p, menu);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener(this);


        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        getStopsFromDb(newText);
        return false;
    }

    @Override
    public boolean onClose() {
        listView.setVisibility(View.INVISIBLE);
        return false;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void Request(String date, String time, String route, String direction, String dayOfWeek) {
        fragment2 = new Fragment2();
        Bundle args = new Bundle();
        args.putString(getResources().getString(R.string.key_request_date), date);
        args.putString(getResources().getString(R.string.key_request_time), time);
        args.putString(getResources().getString(R.string.key_request_route), route);
        args.putString(getResources().getString(R.string.key_request_direction), direction);
        args.putString(getResources().getString(R.string.key_request_day_of_week), dayOfWeek);
        fragment2.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_right, R.anim.slide_out_left, R.anim.slide_left, R.anim.slide_out_right);
        transaction.replace(R.id.fragment_container, fragment2);
        // To allow the user to navigate backward through the fragment transactions
        transaction.addToBackStack("frag2");
        transaction.commit();
    }

    @Override
    public void ClickFrag2(RouteTrip stopSelected, String route, String date, String time, String dayOfWeek) {
        fragment3 = new Fragment3();
        Bundle args = new Bundle();
        args.putString(getResources().getString(R.string.key_request_route), route);
        args.putString(getResources().getString(R.string.key_stop_id), stopSelected.getStop_id());
        args.putString(getResources().getString(R.string.key_stop_name), stopSelected.getStop_name());
        args.putString(getResources().getString(R.string.key_request_date), date);
        args.putString(getResources().getString(R.string.key_request_time), time);
        args.putString(getResources().getString(R.string.key_request_direction), stopSelected.getDirection_id());
        args.putString(getResources().getString(R.string.key_request_day_of_week), dayOfWeek);
        fragment3.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_right, R.anim.slide_out_left, R.anim.slide_left, R.anim.slide_out_right);
        transaction.replace(R.id.fragment_container, fragment3);
        // To allow the user to navigate backward through the fragment transactions
        transaction.addToBackStack("frag3");
        transaction.commit();
    }


    @Override
    public void ClickFrag3(Calendar calendar) {
        fragment4 = new Fragment4();
        Bundle args = new Bundle();
        args.putString(getString(R.string.key_request_trip), calendar.getTrip_id());
        args.putString(getString(R.string.key_request_arrival_time), calendar.getTemps_arrive());
        fragment4.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_right, R.anim.slide_out_left, R.anim.slide_left, R.anim.slide_out_right);
        transaction.replace(R.id.fragment_container, fragment4);
        // To allow the user to navigate backward through the fragment transactions
        transaction.addToBackStack("frag4");
        transaction.commit();
    }


    private void getStopsFromDb(String searchText) {
        if (!searchText.trim().isEmpty()) {
            Cursor cursorBusStops = getContentResolver().query(StarContract.SearchedStops.CONTENT_URI, null, StarContract.Stops.StopColumns.NAME + "LIKE ?", new String[]{searchText + "%"}, StarContract.Stops.StopColumns.NAME + "ASC");
            searchedStops = new ArrayList<>();
            if (cursorBusStops != null) {
                while (cursorBusStops.moveToNext()) {
                    SearchedStop stop = SearchedStop.populateSearchResults(cursorBusStops);
                    searchedStops.add(stop);
                }
                final RoutesView adapter = new RoutesView(searchedStops, this);
                listView.setAdapter(adapter);
                if (!searchedStops.isEmpty()) {
                    listView.setVisibility(View.VISIBLE);
                } else {
                    listView.setVisibility(View.INVISIBLE);
                }
                cursorBusStops.close();
            }
        } else {
            listView.setVisibility(View.INVISIBLE);
        }
    }


    private void getRoutesForStop(String stop_name) {
        Cursor cursorRoutesByStop = getContentResolver().query(StarContract.RoutesForStop.CONTENT_URI, null,
                StarContract.Stops.StopColumns.NAME + "=? and ", new String[]{stop_name}, StarContract.BusRoutes.BusRouteColumns._ID);
        routeStops = new ArrayList<>();
        if (cursorRoutesByStop != null) {
            while (cursorRoutesByStop.moveToNext()) {
                RouteStop routeStop = RouteStop.populate(cursorRoutesByStop);
                routeStops.add(routeStop);
            }
            final RoutesStopAdapter adapter = new RoutesStopAdapter(routeStops, this);
            listView.setAdapter(adapter);
            listView.setVisibility(View.VISIBLE);
            cursorRoutesByStop.close();
        }
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Object itemClicked = adapterView.getAdapter().getItem(i);
        if (itemClicked instanceof SearchedStop) {
            SearchedStop searchedStop = (SearchedStop) itemClicked;
            getRoutesForStop(searchedStop.getStop_name());
        }
    }

}
