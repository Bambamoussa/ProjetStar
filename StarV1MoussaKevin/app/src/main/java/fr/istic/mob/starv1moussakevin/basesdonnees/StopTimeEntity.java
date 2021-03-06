package fr.istic.mob.starv1moussakevin.basesdonnees;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import fr.istic.mob.starv1moussakevin.basesdonnees.DAO.StarContract;

@Entity(tableName = StarContract.StopTimes.CONTENT_PATH)
public class StopTimeEntity {

    @PrimaryKey(autoGenerate = true)
    private int _id;

    @ColumnInfo(name = StarContract.StopTimes.StopTimeColumns.TRIP_ID)
    private String trip_id;

    @ColumnInfo(name = StarContract.StopTimes.StopTimeColumns.ARRIVAL_TIME)
    private String arrival_time;

    @ColumnInfo(name = StarContract.StopTimes.StopTimeColumns.DEPARTURE_TIME)
    private String departure_time;

    @ColumnInfo(name = StarContract.StopTimes.StopTimeColumns.STOP_ID)
    private String stop_id;

    @ColumnInfo(name = StarContract.StopTimes.StopTimeColumns.STOP_SEQUENCE)
    private String stop_sequence;

    public StopTimeEntity(int _id, String trip_id, String arrival_time, String departure_time, String stop_id, String stop_sequence) {
        this._id = _id;
        this.trip_id = trip_id;
        this.arrival_time = arrival_time;
        this.departure_time = departure_time;
        this.stop_id = stop_id;
        this.stop_sequence = stop_sequence;
    }


    @Ignore
    public StopTimeEntity(String trip_id, String arrival_time, String departure_time, String stop_id, String stop_sequence) {
        this.trip_id = trip_id;
        this.arrival_time = arrival_time;
        this.departure_time = departure_time;
        this.stop_id = stop_id;
        this.stop_sequence = stop_sequence;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(String trip_id) {
        this.trip_id = trip_id;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(String departure_time) {
        this.departure_time = departure_time;
    }

    public String getStop_id() {
        return stop_id;
    }

    public void setStop_id(String stop_id) {
        this.stop_id = stop_id;
    }

    public String getStop_sequence() {
        return stop_sequence;
    }

    public void setStop_sequence(String stop_sequence) {
        this.stop_sequence = stop_sequence;
    }
}