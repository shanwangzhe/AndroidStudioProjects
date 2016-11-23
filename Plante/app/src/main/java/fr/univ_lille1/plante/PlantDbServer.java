package fr.univ_lille1.plante;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class PlantDbServer {
    private PlantDbHelper dbhelper;
    public PlantDbServer(Context context)
    {
        this.dbhelper = new PlantDbHelper(context);
    }

    public List<Plant> getPlants ()
    {
        ArrayList<Plant> plants = new ArrayList<Plant>();

        SQLiteDatabase db = this.dbhelper.getReadableDatabase();
        String[] fields = {
                PlantDbHelper.PlantTable._ID,
                PlantDbHelper.PlantTable.PLANT_NAME
        };
        Cursor cursor = db.query(PlantDbHelper.PlantTable.TABLE_NAME, fields, null, null, null, null, null);

        cursor.moveToFirst();

        while(!cursor.isAfterLast())
        {
            Plant temp = new Plant();
            temp.setId(cursor.getLong(cursor.getColumnIndex("_id")));
            temp.setName(cursor.getString(cursor.getColumnIndex("name")));
            cursor.moveToNext();
            plants.add(temp);
        }

        return plants;
    }

    public long addPlant(Plant plant)
    {
        SQLiteDatabase db = this.dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PlantDbHelper.PlantTable.PLANT_NAME, plant.getName());
        values.put(PlantDbHelper.PlantTable.WATERING_FREQUENCY, plant.getWater_Fre());
        long id = db.insert(PlantDbHelper.PlantTable.TABLE_NAME, null, values);
        db.close();
        return id;
    }

}
