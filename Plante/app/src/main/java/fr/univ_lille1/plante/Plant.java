package fr.univ_lille1.plante;

import java.util.Date;

/**
 * Created by fan on 16/11/22.
 */

public class Plant {
    public long id;
    public String name;
    public int water_fre;
    public String water_date;
    public int count;//compteur de jours écoulés

    public Plant()
    {
    }

    public Plant(String name, int water_fre)
    {
        this.name = name;
        this.water_fre = water_fre;
    }

    public void update(String name, int water_fre) {
        this.name = name;
        this.water_fre = water_fre;
    }

    public String getName() {return name;}

    public int getWater_Fre() {
        return water_fre;
    }

    public String getWater_date() {return water_date;}

    public int getCount(){
        return count;
    }

    public long getId() {
        return id;
    }

    public void setId(long id){
        this.id=id;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setWater_date(String date){this.water_date=date;}

    public void setWater_fre(int water_fre){this.water_fre=water_fre;}

}
