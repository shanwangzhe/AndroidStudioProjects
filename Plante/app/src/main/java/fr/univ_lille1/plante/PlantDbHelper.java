package fr.univ_lille1.plante;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;


public class PlantDbHelper extends SQLiteOpenHelper{
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Plants";

    static class PlantTable implements BaseColumns {
        public static final String TABLE_NAME = "Plant";
        public static final String PLANT_NAME = "name";
        public static final String WATERING_FREQUENCY = "water_fre";
        public static final String WATERING_DATE = "water_date";
    }

    public PlantDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ PlantTable.TABLE_NAME +"( _id INTEGER PRIMARY KEY AUTOINCREMENT," +PlantTable.PLANT_NAME + " VARCHAR(20),"+PlantTable.WATERING_FREQUENCY+" INTEGER,"+PlantTable.WATERING_DATE+" VARCHAR(30));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    /*@Override
    public void onOpen(SQLiteDatabase db){
        db.execSQL("DROP TABLE Plant");
        db.execSQL("CREATE TABLE "+ PlantTable.TABLE_NAME +"( _id INTEGER PRIMARY KEY AUTOINCREMENT," +PlantTable.PLANT_NAME + " VARCHAR(20),"+PlantTable.WATERING_FREQUENCY+" INTEGER,"+PlantTable.WATERING_DATE+" VARCHAR(30));");
    }*/
}
