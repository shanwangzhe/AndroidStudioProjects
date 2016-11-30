package fr.univ_lille1.plante;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Plant> plants;
    PlantDbServer plantDb;
    ListView listView;
    long pId;
    static int year,month,day;
    String date;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        plantDb = new PlantDbServer(getApplicationContext());
        Calendar c = Calendar.getInstance();
        year=c.get(Calendar.YEAR); //获取Calendar对象中的年
        month=c.get(Calendar.MONTH);//获取Calendar对象中的月
        day=c.get(Calendar.DAY_OF_MONTH);//获取这个月的第几天
        int monthS = month+1;
        date=year+"-"+monthS+"-"+day;
        refreshList();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                    pId = plants.get(pos).getId();
                    Intent intent = new Intent(MainActivity.this, PlantInfoActivity.class);
                    intent.putExtra("id", pId);
                    startActivityForResult(intent, 1);
                }
        });
        listView.setLongClickable(true);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                                                @Override
                                                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long id) {
                                                    pId = plants.get(pos).getId();
                                                    position = pos;
                                                    plantDb.updatePlant(pId,date);
                                                    Log.d("changan",date);
                                                    refreshList();
                                                    return true;
                                                }
                                            });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callAddPlantActivity();
            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        switch (resultCode) {
            case RESULT_OK:
                refreshList();
                break;
            case RESULT_CANCELED:
                callAddPlantActivity();
                break;
            default:
                break;
        }
    }

        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final MyApplication application = (MyApplication)this.getApplicationContext();

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_date) {
            Log.d("setdateqian", String.valueOf(day));
            DatePickerDialog dialog=new DatePickerDialog(MainActivity.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int myear,
                                              int monthOfYear, int dayOfMonth) {
                            year = myear;
                            month = monthOfYear;
                            int monthS = month+1;
                            day = dayOfMonth;
                            date=year+"-"+monthS+"-"+day;
                            application.setDate(date);
                            refreshList();
                        }
                    }
                    , application.getYear(), application.getMonth(), application.getDay());
            dialog.show();
            return true;
        }
        else if(id==R.id.action_fixture){
            fixture();
            refreshList();
        }
        else if(id == R.id.action_deleteAll){
            plantDb.deleteAll();
            refreshList();
        }
        return super.onOptionsItemSelected(item);
    }



    protected boolean callAddPlantActivity()
    {

        Intent intent = new Intent(this, NewPlantActivity.class);
        startActivityForResult(intent, 1);
        return true;
    }

    public void refreshList()
    {
        plants = plantDb.getPlants();
        PlantAdapter adapter = new PlantAdapter(this, plants);
        listView = (ListView) findViewById(R.id.listPlants);
        listView.setAdapter(adapter);
    }

    public void fixture(){
        List<String> list = Arrays.asList(new String[]{"Prunus mume","Orchidaceae","Bambuseae","Chrysanthemum","Iris","Helianthus annuus","Rose","Lotus","Hyacinthus orientalis","Gypsophila paniculata"});
        for(int i=0;i<10;i++){
            Plant plant = new Plant(list.get(i),(int)(Math.random()*10)+2);
            plant.setWater_date(date);
            plantDb.addPlant(plant);
        }
    }

}
