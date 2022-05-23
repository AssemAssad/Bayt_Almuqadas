package com.example.baytalmuqadas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.baytalmuqadas.fragments.DuasFragment;
import com.example.baytalmuqadas.fragments.PrayerTimingsFragment;
import com.example.baytalmuqadas.fragments.SurahFragment;
import com.example.baytalmuqadas.fragments.TasbihFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private Fragment fragment_surat;
    private Fragment fragment_prayer_timings;
    private Fragment fragment_tasbih;
    private Fragment fragment_duas;
    private BottomNavigationView bottomnavview_menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomnavview_menu = findViewById(R.id.bottomnavview_menu);
        bottomnavview_menu.setOnNavigationItemSelectedListener(this);
        bottomnavview_menu.setSelectedItemId(R.id.menu_surat);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_surat:
                fragment_surat = new SurahFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_fragment,fragment_surat).commit();
                return true;

            case R.id.menu_penanda:

                fragment_prayer_timings = new PrayerTimingsFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_fragment,fragment_prayer_timings).commit();

                return true;


            case R.id.menu_tasbih:

                fragment_tasbih = new TasbihFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_fragment,fragment_tasbih).commit();

                return true;

            case R.id.menu_duas:

                fragment_duas = new DuasFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_fragment,fragment_duas).commit();
                return true;
        }

        return false;
    }



}