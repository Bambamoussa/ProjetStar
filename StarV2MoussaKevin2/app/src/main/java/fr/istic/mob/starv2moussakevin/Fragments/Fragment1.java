package fr.istic.mob.starv2moussakevin.Fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import fr.istic.mob.starv2moussakevin.adapters.Routes;
import fr.istic.mob.starv2moussakevin.R;
import fr.istic.mob.starv2moussakevin.modele.StarContract;
import fr.istic.mob.starv2moussakevin.modele.Util;

public class Fragment1 extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private Frag1 frag1;
    private Calendar currentDate = Calendar.getInstance();
    private TextView textViewDate;
    private TextView textViewTime;
    private Spinner spinnerLines;
    private Spinner spinnerDirections;
    private Button buttonValidate;
    private ArrayList<fr.istic.mob.starv2moussakevin.modele.Route> routes;
    private fr.istic.mob.starv2moussakevin.modele.Route chosenRoute;
    private String dayOfWeek;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag1,
                container, false);
        textViewDate = view.findViewById(R.id.text_view_date);
        textViewDate.setOnClickListener(this);
        textViewTime = view.findViewById(R.id.text_view_time);
        textViewTime.setOnClickListener(this);
        buttonValidate = view.findViewById(R.id.button_validate);
        buttonValidate.setOnClickListener(this);
        spinnerLines = view.findViewById(R.id.spinner_lines);
        spinnerLines.setOnItemSelectedListener(this);
        spinnerDirections = view.findViewById(R.id.spinner_directions);
        spinnerDirections.setOnItemSelectedListener(this);
        return view;

    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Cursor cursorBusRoutes = getActivity().getContentResolver().query(StarContract.BusRoutes.CONTENT_URI, null, null, null, null);
        routes = new ArrayList<>();
        if (cursorBusRoutes != null) {
            while (cursorBusRoutes.moveToNext()) {
                fr.istic.mob.starv2moussakevin.modele.Route aRoute = fr.istic.mob.starv2moussakevin.modele.Route.populateRoute(cursorBusRoutes);
                this.routes.add(aRoute);
            }
            final Routes routes = new Routes(getActivity(), this.routes);
            spinnerLines.setAdapter(routes);
            cursorBusRoutes.close();
        }

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.text_view_date) {
            final int year = currentDate.get(Calendar.YEAR);
            final int month = currentDate.get(Calendar.MONTH);
            final int day = currentDate.get(Calendar.DAY_OF_MONTH);

            final DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), R.style.Theme_AppCompat_DayNight_Dialog, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                    SimpleDateFormat simpledateformat = new SimpleDateFormat("EEEE", Locale.US);
                    Date date = new Date(selectedYear, selectedMonth, selectedDay - 1);
                    dayOfWeek = simpledateformat.format(date).toLowerCase();
                    textViewDate.setText(new StringBuilder().append(selectedDay).append("/").append(selectedMonth + 1).append("/").append(selectedYear));

                }
            }, year, month, day);
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
            datePickerDialog.show();
        } else if (id == R.id.text_view_time) {
            int hour = currentDate.get(Calendar.HOUR_OF_DAY);
            int minutes = currentDate.get(Calendar.MINUTE);
            TimePickerDialog picker = new TimePickerDialog(getActivity(), R.style.Theme_AppCompat_DayNight_Dialog,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker tp, int selectedHour, int selectedMinute) {
                            textViewTime.setText(Util.checkDigit(selectedHour) + ":" + Util.checkDigit(selectedMinute));
                        }
                    }, hour, minutes, true);
            picker.show();
        } else if (id == R.id.button_validate) {
            if (!textViewDate.getText().toString().equals(getString(R.string.date_pick)) && !textViewTime.getText().toString().equals(getString(R.string.time_pick))) {
                frag1.Request(textViewDate.getText().toString(), textViewTime.getText().toString(), chosenRoute.getRoute_id(), chosenRoute.getDirection_id(), dayOfWeek);
            } else {
                Toast.makeText(getActivity(), R.string.toast_invalid_request, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            frag1 = (Frag1) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement " + frag1.getClass().getName());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        frag1 = null;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int spinnerId = parent.getId();
        if (spinnerId == R.id.spinner_lines) {
            chosenRoute = routes.get((int) id);
            String route_long_name = chosenRoute.getRoute_long_name();
            ArrayList<String> terminus = new ArrayList<>();
            String[] splits = route_long_name.split("<>");
            if (splits.length >= 2) {
                terminus.add(splits[splits.length - 1]);
                terminus.add(splits[0]);
            }
            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, terminus);
            spinnerDirections.setAdapter(adapter);
            spinnerDirections.setVisibility(View.VISIBLE);
        } else if (spinnerId == R.id.spinner_directions) {
            chosenRoute.setDirection_id(String.valueOf(id));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        spinnerDirections.setVisibility(View.INVISIBLE);
        chosenRoute = null;
    }
}
