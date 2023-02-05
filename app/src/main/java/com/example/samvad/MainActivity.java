package com.example.samvad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    DrawerLayout drawer_lay;
    NavigationView nav_view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id_finder(); // all id are set here;
        toolbar_navDrawer_setup(); // for toolbar set up and navigation drawer set up

    }

    private void toolbar_navDrawer_setup() {
        setSupportActionBar(toolbar);
        // setActionBar(toolbar); for normal or old toolbar
        toolbar.setTitle("Samvad");
        toolbar.setSubtitle("Kyuki,baatein rukni nahi chhaiye");
        nav_view.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer_lay,toolbar,R.string.OpenDrawer,R.string.CloseDrawer);
        drawer_lay.addDrawerListener(toggle);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.teal_200));
        toggle.setDrawerSlideAnimationEnabled(true);
        /*toggle.setHomeAsUpIndicator(R.id.saved_messages);*/
        toggle.syncState();

        nav_view.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        if (drawer_lay.isDrawerOpen(GravityCompat.START)) {
            drawer_lay.closeDrawer(GravityCompat.START);
        }
        else{
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Close App")
                    .setIcon(R.drawable.alert_icon)
                    .setCancelable(false)
                    .setMessage("Are your sure want to exit?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.finishAffinity(MainActivity.this);
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });

            builder.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.toolbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    public void id_finder() {
        toolbar = findViewById(R.id.toolbar);
        drawer_lay= findViewById(R.id.drawer_lay);
        nav_view=findViewById(R.id.nav_view);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search_bar:
                openSearchView();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openSearchView() {
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.new_group:
                break;
            case R.id.call_history:
                break;
            case R.id.linked_device:
                break;
            case R.id.settings:
                break;
            case R.id.share:
                break;
            case R.id.rate_us:
                break;
            case R.id.feedback:
                break;
            case R.id.privacy_policy:
                break;
           
        }
        drawer_lay.closeDrawer(GravityCompat.START);
        return true;
    }
}