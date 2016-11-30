package fr.univ_lille1.plante;


import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class PlantAdapter extends ArrayAdapter<Plant> {
    private Context mContext;

    public PlantAdapter(Context context, List<Plant> plants) {
        super(context, 0, plants);
        this.mContext = context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Plant plant = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_plant,parent, false);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (plant.getWater_date()!=null) {
                try {
                    Date date0 = sdf.parse(plant.getWater_date());
                    MyApplication application = (MyApplication) mContext.getApplicationContext();
                    Date date1 = sdf.parse(application.getDate());
                    GregorianCalendar cal1 = new GregorianCalendar();
                    GregorianCalendar cal2 = new GregorianCalendar();
                    cal1.setTime(date0);
                    cal2.setTime(date1);
                    double dayCount = (cal2.getTimeInMillis() - cal1.getTimeInMillis()) / (1000 * 3600 * 24);
                    int fre = plant.getWater_Fre();
                    if (fre - dayCount < 0)
                        convertView.setBackgroundResource(R.drawable.red);
                    else if (fre - dayCount >= 0 && fre - dayCount < 2)
                        convertView.setBackgroundResource(R.drawable.yellow);
                    else
                        convertView.setBackgroundResource(R.drawable.green);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

        }

        PlantViewHolder viewHolder = (PlantViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new PlantViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.water_fre = (TextView) convertView.findViewById(R.id.water_fre);
            viewHolder.avatar = (ImageView) convertView.findViewById(R.id.avatar);
            convertView.setTag(viewHolder);
        }

        viewHolder.name.setText(plant.getName());
        viewHolder.water_fre.setText(String.valueOf(plant.getWater_Fre()));
        viewHolder.avatar.setImageResource(R.drawable.th);

        return convertView;
    }

    private class PlantViewHolder{
        public TextView name;
        public TextView water_fre;
        public ImageView avatar;
    }

}

