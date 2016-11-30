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
        List<Plant> plants = new ArrayList<Plant>();

        SQLiteDatabase db = this.dbhelper.getReadableDatabase();
        String[] fields = {
                PlantDbHelper.PlantTable._ID,
                PlantDbHelper.PlantTable.PLANT_NAME,
                PlantDbHelper.PlantTable.WATERING_FREQUENCY,
                PlantDbHelper.PlantTable.WATERING_DATE
        };
        Cursor cursor = db.query(PlantDbHelper.PlantTable.TABLE_NAME, fields, null, null, null, null, null);
        if(cursor!=null&&cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                Plant temp = new Plant();
                long _id = cursor.getLong(cursor.getColumnIndex(PlantDbHelper.PlantTable._ID));
                String name = cursor.getString(cursor.getColumnIndex(PlantDbHelper.PlantTable.PLANT_NAME));
                String date = cursor.getString(cursor.getColumnIndex(PlantDbHelper.PlantTable.WATERING_DATE));
                int water_fre = cursor.getInt(cursor.getColumnIndex(PlantDbHelper.PlantTable.WATERING_FREQUENCY));
                temp.setId(_id);
                temp.setName(name);
                temp.setWater_date(date);
                temp.setWater_fre(water_fre);
                plants.add(temp);
            }while(cursor.moveToNext());
            cursor.close();
        }
        return plants;
    }

    public long addPlant(Plant plant)
    {
        SQLiteDatabase db = this.dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PlantDbHelper.PlantTable.PLANT_NAME, plant.getName());
        values.put(PlantDbHelper.PlantTable.WATERING_FREQUENCY, plant.getWater_Fre());
        values.put(PlantDbHelper.PlantTable.WATERING_DATE,plant.getWater_date());
        long id = db.insert(PlantDbHelper.PlantTable.TABLE_NAME, null, values);
        return id;
    }

    public Plant getPlantById(long id){
        Plant plant=null;
        SQLiteDatabase db = this.dbhelper.getReadableDatabase();
        String[] fields = {
                PlantDbHelper.PlantTable.PLANT_NAME,
                PlantDbHelper.PlantTable.WATERING_FREQUENCY,
                PlantDbHelper.PlantTable.WATERING_DATE

        };
        String where = PlantDbHelper.PlantTable._ID+" = ?";
        String[] whereArgs = {String.valueOf(id)};
        Cursor cursor = db.query(PlantDbHelper.PlantTable.TABLE_NAME, fields, where, whereArgs, null, null, null,null);
        if(cursor.moveToFirst()){
            do {
                String name = cursor.getString(cursor.getColumnIndex(PlantDbHelper.PlantTable.PLANT_NAME));
                int water_fre = cursor.getInt(cursor.getColumnIndex(PlantDbHelper.PlantTable.WATERING_FREQUENCY));
                plant = new Plant(name, water_fre);
                String date = cursor.getString(cursor.getColumnIndex(PlantDbHelper.PlantTable.WATERING_DATE));
                plant.setWater_date(date);
            }while(cursor.moveToNext());
        }
        return plant;
    }

    public int updatePlant(long pId,String name,int water_fre){
        SQLiteDatabase db = this.dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PlantDbHelper.PlantTable.PLANT_NAME, name);
        values.put(PlantDbHelper.PlantTable.WATERING_FREQUENCY, water_fre);
        String where = PlantDbHelper.PlantTable._ID+" = ?";
        String[] args = {String.valueOf(pId)};
        return db.update(PlantDbHelper.PlantTable.TABLE_NAME, values,where,args);
    }

    public int updatePlant(long pId,String water_date){
        SQLiteDatabase db = this.dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PlantDbHelper.PlantTable.WATERING_DATE, water_date);
        String where = PlantDbHelper.PlantTable._ID+" = ?";
        String[] args = {String.valueOf(pId)};
        return db.update(PlantDbHelper.PlantTable.TABLE_NAME, values,where,args);
    }

    public int deleteUserPlant(long pId){
        SQLiteDatabase db = this.dbhelper.getWritableDatabase();
        String where = PlantDbHelper.PlantTable._ID+" = ?";
        String[] args = {String.valueOf(pId)};
        return db.delete(PlantDbHelper.PlantTable.TABLE_NAME, where, args);
    }
    public void deleteAll(){
        SQLiteDatabase db = this.dbhelper.getWritableDatabase();
        db.execSQL("DELETE FROM Plant");
    }
}
