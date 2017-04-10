package com.mikecoding.navigationbartesting;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ArrayList<UnitListItem> unitList = new ArrayList<UnitListItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    public List<ItemValue> GetResource(int identifier){
        String[] unit_namestruncated;
        String splitString;
        Resources res = getResources();
        List<ItemValue> units = new ArrayList<ItemValue>();
        switch(identifier){
            case 0:
                unit_namestruncated= res.getStringArray(R.array.acceleration_array);
                for(int i = 0; i < unit_namestruncated.length; i++){
                    splitString = unit_namestruncated[i];
                    String[] items = splitString.split("\\,");
                    ItemValue itemValue = new ItemValue(items[0], Double.parseDouble(items[1]));
                    Log.d("TAG", items[0] + items[1]);
                    units.add(itemValue);
                }
                return units;
            case 1:
                unit_namestruncated= res.getStringArray(R.array.cooking_array);
                for(int i = 0; i < unit_namestruncated.length; i++){
                    splitString = unit_namestruncated[i];
                    String[] items = splitString.split(",");
                    ItemValue itemValue = new ItemValue(items[0], Double.parseDouble(items[1]));
                    units.add(itemValue);
                }
                return units;
            case 2:
                unit_namestruncated= res.getStringArray(R.array.datastorage_array);
                for(int i = 0; i < unit_namestruncated.length; i++){
                    splitString = unit_namestruncated[i];
                    String[] items = splitString.split(",");
                    ItemValue itemValue = new ItemValue(items[0], Double.parseDouble(items[1]));
                    units.add(itemValue);
                }
                return units;
            case 3:
                unit_namestruncated= res.getStringArray(R.array.length_array);
                //wont go inside the loop
                for(int i = 0; i < unit_namestruncated.length; i++){
                    splitString = unit_namestruncated[i];
                    String[] items = splitString.split(",");
                    ItemValue itemValue = new ItemValue(items[0], Double.parseDouble(items[1]));
                    units.add(itemValue);
                }
                return units;
            case 4:
                unit_namestruncated= res.getStringArray(R.array.time_array);
                for(int i = 0; i < unit_namestruncated.length; i++){
                    splitString = unit_namestruncated[i];
                    String[] items = splitString.split(",");
                    ItemValue itemValue = new ItemValue(items[0], Double.parseDouble(items[1]));
                    units.add(itemValue);
                }
                return units;
            default:
                unit_namestruncated= res.getStringArray(R.array.dummy_array);
                for(int i = 0; i < unit_namestruncated.length; i++){
                    splitString = unit_namestruncated[i];
                    String[] items = splitString.split(",");
                    ItemValue itemValue = new ItemValue(items[0], Double.parseDouble(items[1]));
                    units.add(itemValue);
                }
                return units;
        }

    }
    public void addContent(List<ItemValue> units){
        LinearLayout columnLinearLayout;

        columnLinearLayout = (LinearLayout) findViewById(R.id.units_layout);
        columnLinearLayout.removeAllViews();

        for(ItemValue item : units) {
            UnitListItem unitListItem = new UnitListItem(this, item);
            unitList.add(unitListItem);
            columnLinearLayout.addView(unitListItem);
        }
    }
    public void startConverting(Double mTo){
        //for each
        for(UnitListItem item : unitList){
            item.convert(mTo);
        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        /*
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        */
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_acce) {
            addContent(GetResource(0));
        } else if (id == R.id.nav_cook) {
            addContent(GetResource(1));
        } else if (id == R.id.nav_data) {
            addContent(GetResource(2));
        } else if (id == R.id.nav_dist) {
            addContent(GetResource(3));
        } else if (id == R.id.nav_time) {
            addContent(GetResource(4));
        } else if (id == R.id.nav_settings) {
            Toast.makeText(MainActivity.this, "Under Construction", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_about){
            Toast.makeText(MainActivity.this, "Under Construction", Toast.LENGTH_SHORT).show();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
