package fr.univ_lille1.plante;


import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PlantAdapter extends ArrayAdapter<Plant> {

    public PlantAdapter(Context context, List<Plant> plants) {
        super(context, 0, plants);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Plant plant = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_plant,parent, false);
        }

        PlantViewHolder viewHolder = (PlantViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new PlantViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.water_fre = (TextView) convertView.findViewById(R.id.water_fre);
            //viewHolder.avatar = (ImageView) convertView.findViewById(R.id.avatar);
            convertView.setTag(viewHolder);
        }


        viewHolder.name.setText(plant.getName());
        viewHolder.water_fre.setText(plant.getWater_Fre());
        //viewHolder.avatar.setImageDrawable(new ColorDrawable(book.getImage()));

        return convertView;
    }

    private class PlantViewHolder{
        public TextView name;
        public TextView water_fre;
    }
}