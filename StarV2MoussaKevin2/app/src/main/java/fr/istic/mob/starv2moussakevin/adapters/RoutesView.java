package fr.istic.mob.starv2moussakevin.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import fr.istic.mob.starv2moussakevin.R;
import fr.istic.mob.starv2moussakevin.modele.SearchedStop;
public class RoutesView extends ArrayAdapter<SearchedStop> {


    public RoutesView(ArrayList<SearchedStop> data, Context context) {
        super(context, R.layout.sr_ligne, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SearchedStop item = getItem(position);
        ViewHolder viewHolder;

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.sr_ligne, parent, false);
            viewHolder.ArretNom = (TextView) convertView.findViewById(R.id.txt_stop_name_stops_research);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }


        viewHolder.ArretNom.setText(item.getStop_name());
        return convertView;
    }

    private static class ViewHolder {
        TextView ArretNom;
    }


}
