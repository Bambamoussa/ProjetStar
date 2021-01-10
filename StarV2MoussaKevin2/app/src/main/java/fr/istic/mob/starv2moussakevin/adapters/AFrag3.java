package fr.istic.mob.starv2moussakevin.adapters;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import fr.istic.mob.starv2moussakevin.R;
import fr.istic.mob.starv2moussakevin.modele.Util;
import fr.istic.mob.starv2moussakevin.modele.Calendar;

public class AFrag3 extends ArrayAdapter<Calendar> {

    Context context;
    private ArrayList<Calendar> calendars;

    public AFrag3(ArrayList<Calendar> data, Context context) {
        super(context, R.layout.frag4ligne ,  data);
        this.calendars = data;
        this.context = context;
    }

    @Override
    public View getView(int indice, View convertView, ViewGroup vgroup) {

        Calendar item = getItem(indice);
        ViewHolder viewHolder ;
        final View result;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.frag3ligne, vgroup, false);
            viewHolder.StopTemps = (TextView) convertView.findViewById(R.id.txt_stop_time_f3);
            viewHolder.TermineStop = (TextView) convertView.findViewById(R.id.txt_terminus_f3);
            result = convertView;
            convertView.setTag(viewHolder);
        } else {
           viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }
        viewHolder.StopTemps.setText(Util.getArrive_temps(item.getTemps_arrive()));
        viewHolder.TermineStop.setText(item.getTrip_h());
        viewHolder.TermineStop.setTextColor(Color.GRAY);
        return convertView;
    }

    private static class ViewHolder {
        TextView StopTemps;
        TextView TermineStop;
    }

}

