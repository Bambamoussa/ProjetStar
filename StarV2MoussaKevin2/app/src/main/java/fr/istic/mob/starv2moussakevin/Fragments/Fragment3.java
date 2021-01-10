package fr.istic.mob.starv2moussakevin.Fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.ListFragment;

import java.util.ArrayList;

import fr.istic.mob.starv2moussakevin.adapters.AFrag3;
import fr.istic.mob.starv2moussakevin.R;
import fr.istic.mob.starv2moussakevin.modele.Calendar;
import fr.istic.mob.starv2moussakevin.modele.StarContract;

public class Fragment3 extends ListFragment {

    private Frag3 frag3;
    private TextView fragment_title;
    private ArrayList<Calendar> calendars;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag3, container, false);
        fragment_title = view.findViewById(R.id.label_fragment3);
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle argsFromFragment1 = getArguments();
        String route = argsFromFragment1.getString(getResources().getString(R.string.key_request_route));
        String stop_id = argsFromFragment1.getString(getResources().getString(R.string.key_stop_id));
        String stop_name = argsFromFragment1.getString(getResources().getString(R.string.key_stop_name));
        String direction = argsFromFragment1.getString(getResources().getString(R.string.key_request_direction));
        String starting_hour = argsFromFragment1.getString(getResources().getString(R.string.key_request_time));
        String date = argsFromFragment1.getString(getResources().getString(R.string.key_request_date));
        String dayOfWeek = argsFromFragment1.getString(getResources().getString(R.string.key_request_day_of_week));
        String selection = StarContract.Trips.TripColumns.ROUTE_ID + " = ?  "
                + StarContract.StopTimes.StopTimeColumns.STOP_ID + " =?  "
                + StarContract.Trips.TripColumns.DIRECTION_ID + " =?"
                + StarContract.StopTimes.StopTimeColumns.ARRIVAL_TIME + " >?";
        String[] selectionArgs = new String[]{route, stop_id, direction, dayOfWeek, starting_hour};
        Cursor cursorStopTimes = getActivity().getContentResolver().query(StarContract.StopTimes.CONTENT_URI, null, selection, selectionArgs, "");
        calendars = new ArrayList<>();
        if (cursorStopTimes != null) {
            while (cursorStopTimes.moveToNext()) {
                Calendar calendar = Calendar.populate(cursorStopTimes, stop_id);
                calendars.add(calendar);
            }
        }

        final AFrag3 adapter = new AFrag3(calendars, getActivity());
        setListAdapter(adapter);
        if(!calendars.isEmpty()) {
            fragment_title.setText(getResources().getString(R.string.label_fragment3) + " " + stop_name);
        }
        else {
            fragment_title.setText(getResources().getString(R.string.no_timetables));
        }
    }


    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Calendar calendar = calendars.get((int) id);
        frag3.ClickFrag3(calendar);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            frag3 = (Frag3) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement " + frag3.getClass().getName());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        frag3 = null;
    }

}

