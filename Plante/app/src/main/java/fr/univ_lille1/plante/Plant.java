package fr.univ_lille1.plante;

/**
 * Created by fan on 16/11/22.
 */

public class Plant {
    public long id;
    public String name;
    public int water_fre;
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

    public String getName() {
        return name;
    }

    public int getWater_Fre() {
        return water_fre;
    }

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

}
