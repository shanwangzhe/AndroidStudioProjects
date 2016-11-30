package fr.univ_lille1.plante;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by fan on 16/11/22.
 */

public class PlantInfoActivity extends AppCompatActivity {
    TextView textViewName, textViewWaterFre,textViewWaterDate;
    ImageView imageViewCover;
    Button delete,update;
    PlantDbServer plantDb;
    Plant plant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_info);

        textViewName = (TextView) findViewById(R.id.textViewName);
        textViewWaterFre = (TextView) findViewById(R.id.textViewWaterFre);
        textViewWaterDate = (TextView) findViewById(R.id.textViewWaterDate);
        imageViewCover = (ImageView) findViewById(R.id.imageViewCover);
        delete = (Button) findViewById(R.id.delete);
        update = (Button) findViewById(R.id.update);

        final long id = this.getIntent().getLongExtra("id", -1);
        if (id >= 0) {
            plantDb = new PlantDbServer(getApplicationContext());
            plant = plantDb.getPlantById(id);
            textViewName.setText(plant.getName());
            textViewWaterFre.setText(String.valueOf(plant.getWater_Fre()));
            textViewWaterDate.setText(plant.getWater_date());
        }
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                plantDb.deleteUserPlant(id);
                setResult(RESULT_OK);
                finish();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlantInfoActivity.this, NewPlantActivity.class);
                intent.putExtra("name", plant.getName());
                intent.putExtra("water_fre", plant.getWater_Fre());
                intent.putExtra("pId",id);
                startActivity(intent);
                setResult(RESULT_OK);
                finish();
            }
        });
    }
}
