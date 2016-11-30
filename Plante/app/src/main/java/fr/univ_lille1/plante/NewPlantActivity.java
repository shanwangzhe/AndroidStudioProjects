package fr.univ_lille1.plante;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class NewPlantActivity extends AppCompatActivity implements View.OnClickListener {
    EditText nameField, water_fre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_plant);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        int waterFre = intent.getIntExtra("water_fre", -1);
        nameField = (EditText) findViewById(R.id.plantName);
        water_fre = (EditText) findViewById(R.id.water_fre);
        if (name != null)
            nameField.setText(name);
        if (waterFre != -1)
            water_fre.setText(Integer.toString(waterFre));
        findViewById(R.id.saveButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final long pId = this.getIntent().getLongExtra("pId", -1);
        PlantDbServer plantDb = new PlantDbServer(getApplicationContext());
        if (v.getId() == R.id.saveButton) {
            if (nameField.getText().toString().equals("")|| water_fre.getText().toString().equals("")) {
                Toast.makeText(this, getString(R.string.plant_cant_be_void), Toast.LENGTH_SHORT).show();
            } else {
                int wf = Integer.parseInt(water_fre.getText().toString());
                if (wf < 2) {
                    Toast.makeText(this, getString(R.string.water_frequency_value), Toast.LENGTH_SHORT).show();
                } else {
                    String nf = nameField.getText().toString();
                    if (pId == -1) {
                        Plant plant = new Plant(nf, wf);
                        MyApplication application = (MyApplication) this.getApplicationContext();
                        plant.setWater_date(application.getDate());
                        long id = plantDb.addPlant(plant);
                        plant.setId(id);
                        Toast.makeText(getApplicationContext(), "New ID: " + id, Toast.LENGTH_SHORT).show();
                    } else {
                        plantDb.updatePlant(pId, nf, wf);
                    }
                    setResult(RESULT_OK);
                    finish();
                }
            }
        }
    }
}
