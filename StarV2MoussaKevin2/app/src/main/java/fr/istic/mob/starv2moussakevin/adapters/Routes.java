package fr.istic.mob.starv2moussakevin.adapters;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import fr.istic.mob.starv2moussakevin.modele.Route;

public class Routes extends BaseAdapter {

    private ArrayList<fr.istic.mob.starv2moussakevin.modele.Route> routes;
    private Context context;

    public Routes(Context context, ArrayList<fr.istic.mob.starv2moussakevin.modele.Route> routes) {
        this.context = context;
        if (routes != null) {
            this.routes = routes;
        }
    }

    @Override
    public int getCount() {
        return routes.size();
    }

    @Override
    public Object getItem(int arg0) {
        return routes.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(android.R.layout.simple_spinner_dropdown_item, null);
        TextView txv = view.findViewById(android.R.id.text1);
        txv.setBackgroundColor(Color.parseColor("#" + routes.get(position).getRoute_color()));
        txv.setTextColor(Color.parseColor("#" + routes.get(position).getRoute_text_color()));
        txv.setText(routes.get(position).getRoute_short_name());
        txv.setWidth(400);
        return view;
    }

}