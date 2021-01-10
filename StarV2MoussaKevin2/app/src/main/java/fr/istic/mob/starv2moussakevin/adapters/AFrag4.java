package fr.istic.mob.starv2moussakevin.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import fr.istic.mob.starv2moussakevin.R;
import fr.istic.mob.starv2moussakevin.modele.Util;
import fr.istic.mob.starv2moussakevin.modele.RouteDetail;

public class AFrag4 extends ArrayAdapter<RouteDetail> {


    Context context;
    private ArrayList<RouteDetail> routeDetails;

    public AFrag4(ArrayList<RouteDetail> data, Context context) {
        super(context, R.layout.frag4ligne, data);
        this.routeDetails = data;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RouteDetail routeDetail = getItem(position);
        ViewHolder viewHolder;
        final View result;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.frag4ligne, parent, false);
            viewHolder.ArretNom = (TextView) convertView.findViewById(R.id.txt_stop_name_f4);
            viewHolder.ArriveTemps = (TextView) convertView.findViewById(R.id.txt_arrival_time_f4);
            result = convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }
        viewHolder.ArretNom.setText(routeDetail.getStop_name());
        viewHolder.ArriveTemps.setText(Util.getArrive_temps(routeDetail.getTps_arrive()));
        return convertView;
    }

    private static class ViewHolder {
        TextView ArretNom;
        TextView ArriveTemps;
    }

}

