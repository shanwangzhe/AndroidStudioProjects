package fr.univ_lille1.plante;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class NewPlantActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_plant);

        findViewById(R.id.saveButton).setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.saveButton)
        {
            EditText nameField = (EditText) findViewById(R.id.plantName);
            EditText water_fre = (EditText) findViewById(R.id.water_fre);

            if(nameField.getText().toString().equals(""))
                Toast.makeText(this, getString(R.string.plant_cant_be_void), Toast.LENGTH_SHORT).show();
            else
            {
                PlantDbServer plantDb = new PlantDbServer(getApplicationContext());
                Plant plant = new Plant(nameField.getText().toString(),Integer.parseInt(water_fre.getText().toString()));
                long id = plantDb.addPlant(plant);
                plant.setId(id);
                Toast.makeText(getApplicationContext(), "New ID: " + id, Toast.LENGTH_SHORT).show();

                setResult(RESULT_OK);
                finish();
            }
        }

    }
}
