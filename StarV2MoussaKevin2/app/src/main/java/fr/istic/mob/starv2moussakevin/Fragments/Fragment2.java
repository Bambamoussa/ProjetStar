package fr.istic.mob.starv2moussakevin.Fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.ListFragment;

import java.util.ArrayList;

import fr.istic.mob.starv2moussakevin.adapters.AFrag2;
import fr.istic.mob.starv2moussakevin.R;
import fr.istic.mob.starv2moussakevin.modele.RouteTrip;
import fr.istic.mob.starv2moussakevin.modele.StarContract;


public class Fragment2 extends ListFragment {

    private Frag2 frag2;
    private ArrayList<RouteTrip> routes;
    private String date;
    private String time;
    private String route;
    private String Week;
    private String direction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag2, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle argsFromFragment1 = getArguments();
        route = argsFromFragment1.getString(getResources().getString(R.string.key_request_route));
        direction = argsFromFragment1.getString(getResources().getString(R.string.key_request_direction));
        date = argsFromFragment1.getString(getResources().getString(R.string.key_request_date));
        time = argsFromFragment1.getString(getResources().getString(R.string.key_request_time));
        Week = argsFromFragment1.getString(getResources().getString(R.string.key_request_day_of_week));
        Cursor cursorStopsByLines = getActivity().getContentResolver().query(StarContract.Stops.CONTENT_URI, null,
                StarContract.BusRoutes.BusRouteColumns.SHORT_NAME + "=? and " + StarContract.Trips.TripColumns.DIRECTION_ID + "=?", new String[]{route, direction}, null);
        routes = new ArrayList<>();
        if (cursorStopsByLines != null) {
            while (cursorStopsByLines.moveToNext()) {
                RouteTrip routeTrip = RouteTrip.populate(cursorStopsByLines);
                this.routes.add(routeTrip);
            }
        }
        final AFrag2 adapter = new AFrag2(routes, getActivity());
        getListView().setAdapter(adapter);
        cursorStopsByLines.close();
    }


    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        frag2.ClickFrag2(routes.get((int) id), route, date, time, Week);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            frag2 = (Frag2) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement " + frag2.getClass().getName());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        frag2 = null;
    }
}

