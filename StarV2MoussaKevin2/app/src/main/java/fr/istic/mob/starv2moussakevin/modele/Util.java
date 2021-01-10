package fr.istic.mob.starv2moussakevin.modele;

public class Util {

    public static String getArrive_temps(String arrival_time) {
        String time = arrival_time;
        String[] hms = arrival_time.split(":");
        String hours = hms[0];
        int hourInt = Integer.valueOf(hours);
        if (hourInt >= 24) {
            String trueHour = String.valueOf(hourInt - 24);
            time = trueHour + ":" + hms[1] + ":" + hms[2];
        }
        return time;
    }

    public static String checkDigit(int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }

}


