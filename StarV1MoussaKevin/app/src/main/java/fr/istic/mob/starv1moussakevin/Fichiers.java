package fr.istic.mob.starv1moussakevin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import fr.istic.mob.starv1moussakevin.basesdonnees.CalendarEntity;
import fr.istic.mob.starv1moussakevin.basesdonnees.RouteEntity;
import fr.istic.mob.starv1moussakevin.basesdonnees.StopEntity;
import fr.istic.mob.starv1moussakevin.basesdonnees.StopTimeEntity;
import fr.istic.mob.starv1moussakevin.basesdonnees.TripEntity;

public class Fichiers {

    private String path;

    public Fichiers(String path) {
        this.path = path;
    }


    public ArrayList<?> readEntitiesFromFile(String fileName, Class type) {
        ArrayList<Object> entities = new ArrayList<>();
        try {
            File f = new File(path + File.separator + File.separator + fileName);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(f));

            String line = bufferedReader.readLine();
            line = bufferedReader.readLine();

            while (line != null) {
                String[] fields = line.split(",");
                if (type.equals(RouteEntity.class)) {
                    RouteEntity routeEntity = createRouteEntity(fields);
                    entities.add(routeEntity);
                } else if (type.equals(TripEntity.class)) {
                    TripEntity tripEntity = createTripEntity(fields);
                    entities.add(tripEntity);
                } else if (type.equals(CalendarEntity.class)) {
                    CalendarEntity calendarEntity = createCalendarEntity(fields);
                    entities.add(calendarEntity);
                } else if (type.equals(StopEntity.class)) {
                    StopEntity stopEntity = createStopEntity(fields);
                    entities.add(stopEntity);
                } else if (type.equals(StopTimeEntity.class)) {
                    StopTimeEntity stopTimeEntity = createStopTimeEntity(fields);
                    entities.add(stopTimeEntity);
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entities;
    }


    private RouteEntity createRouteEntity(String[] fields) {
        String route_id = fields[0].replace("\"", "");
        String route_short_name = fields[2].replace("\"", "");
        String route_long_name = fields[3].replace("\"", "");
        String route_desc = fields[4].replace("\"", "");
        String route_type = fields[5].replace("\"", "");
        String route_color = fields[7].replace("\"", "");
        String route_text_color = fields[8].replace("\"", "");

        return new RouteEntity(route_id, route_short_name, route_long_name, route_desc, route_type, route_color, route_text_color);
    }


    private TripEntity createTripEntity(String[] fields) {
        String route_id = fields[0].replace("\"", "");
        String service_id = fields[1].replace("\"", "");
        String trip_id = fields[2].replace("\"", "");
        String trip_headsign = fields[3].replace("\"", "");
        String direction_id = fields[5].replace("\"", "");


        return new TripEntity(trip_id, route_id, service_id, trip_headsign, direction_id );
    }

    private CalendarEntity createCalendarEntity(String[] fields) {
        String service_id = fields[0].replace("\"", "");
        String monday = fields[1].replace("\"", "");
        String tuesday = fields[2].replace("\"", "");
        String wednesday = fields[3].replace("\"", "");
        String thursday = fields[4].replace("\"", "");
        String friday = fields[5].replace("\"", "");
        String saturday = fields[6].replace("\"", "");
        String sunday = fields[7].replace("\"", "");
        String start_date = fields[8].replace("\"", "");
        String end_date = fields[9].replace("\"", "");

        return new CalendarEntity(service_id, monday, tuesday, wednesday, thursday, friday, saturday, sunday, start_date, end_date);
    }

    private StopEntity createStopEntity(String[] fields) {
        String stop_id = fields[0].replace("\"", "");
        String stop_name = fields[2].replace("\"", "");
        String stop_desc = fields[3].replace("\"", "");
        String stop_lat = fields[4].replace("\"", "");
        String stop_lon = fields[5].replace("\"", "");


        return new StopEntity(stop_id, stop_name, stop_desc, stop_lat, stop_lon);

    }

    private StopTimeEntity createStopTimeEntity(String[] fields) {
        String trip_id = fields[0].replace("\"", "");
        String arrival_time = fields[1].replace("\"", "");
        String departure_time = fields[2].replace("\"", "");
        String stop_id = fields[3].replace("\"", "");
        String stop_sequence = fields[4].replace("\"", "");

        return new StopTimeEntity(trip_id, arrival_time, departure_time, stop_id, stop_sequence);

    }
}