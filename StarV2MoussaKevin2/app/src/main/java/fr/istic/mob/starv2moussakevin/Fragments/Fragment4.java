package fr.istic.mob.starv2moussakevin.Fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.ListFragment;

import java.util.ArrayList;

import fr.istic.mob.starv2moussakevin.adapters.AFrag4;
import fr.istic.mob.starv2moussakevin.R;
import fr.istic.mob.starv2moussakevin.modele.RouteDetail;
import fr.istic.mob.starv2moussakevin.modele.StarContract;


public class Fragment4 extends ListFragment {
    private ArrayList<RouteDetail> routeDetails;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag4, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle argsFromFragment3 = getArguments();
        String trip_id = argsFromFragment3.getString(getResources().getString(R.string.key_request_trip));
        String arrival_time = argsFromFragment3.getString(getResources().getString(R.string.key_request_arrival_time));
        String selection = StarContract.StopTimes.StopTimeColumns.TRIP_ID + " =?  " +
                StarContract.StopTimes.StopTimeColumns.ARRIVAL_TIME + " >?";
        String[] selectionArgs = new String[]{trip_id, arrival_time};
        Cursor cursorRouteDetails = getActivity().getContentResolver().query(StarContract.RouteDetails.CONTENT_URI, null, selection, selectionArgs, "");
        routeDetails = new ArrayList<>();
        if (cursorRouteDetails != null) {
            while (cursorRouteDetails.moveToNext()) {
                RouteDetail routeDetail = RouteDetail.populateRouteDetail(cursorRouteDetails);
                routeDetails.add(routeDetail);
            }
        }
        final AFrag4 adapter = new AFrag4(routeDetails, getActivity());
        setListAdapter(adapter);
    }
}
