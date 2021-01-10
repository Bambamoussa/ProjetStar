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
import fr.istic.mob.starv2moussakevin.modele.RouteStop;

public class RoutesStopAdapter extends ArrayAdapter<RouteStop> {

    public RoutesStopAdapter(ArrayList<RouteStop> data, Context context) {
        super(context, R.layout.ligne_r, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RouteStop item = getItem(position);
        RoutesStopAdapter.ViewHolder viewHolder;

        final View result;

        if (convertView == null) {

            viewHolder = new RoutesStopAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.ligne_r, parent, false);
            viewHolder.RouteNom = (TextView) convertView.findViewById(R.id.line_name_research);
            convertView.setBackgroundColor(Color.parseColor("#" + item.getRoute_color()));
            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (RoutesStopAdapter.ViewHolder) convertView.getTag();
            result = convertView;
        }

        viewHolder.RouteNom.setText(item.getRoute_name());
        viewHolder.RouteNom.setBackgroundColor(Color.parseColor("#" + item.getRoute_color()));
        viewHolder.RouteNom.setTextColor(Color.parseColor("#" + item.getRoute_txt_color()));

        return convertView;
    }

    private static class ViewHolder {
        TextView RouteNom;
    }
}
