package fr.univ_lille1.plante;

import android.app.Application;
import java.util.Calendar;

public class MyApplication extends Application {
    private String date;
    @Override
    public void onCreate() {
        init();
        super.onCreate();
        setDate(date);
    }
    public void init() {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        this.date=year+"-"+month+"-"+day;
    }

    public String getDate() {
        if(date==null){
            init();
        }
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getYear(){
        return Integer.parseInt(date.split("-")[0]);
    }


    public int getMonth(){
        return Integer.parseInt(date.split("-")[1])-1;
    }

    public int getDay(){
        return Integer.parseInt(date.split("-")[2]);
    }
}

