package com.example.baytalmuqadas;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.baytalmuqadas.fragments.DuasFragment;
import com.example.baytalmuqadas.fragments.PrayerTimingsFragment;
import com.example.baytalmuqadas.fragments.SurahFragment;
import com.example.baytalmuqadas.fragments.TasbihFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.installations.FirebaseInstallations;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Locale;

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
        switch (item.getItemId()) {
            case R.id.menu_surat:
                fragment_surat = new SurahFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_fragment, fragment_surat).commit();
                return true;

            case R.id.menu_penanda:

                fragment_prayer_timings = new PrayerTimingsFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_fragment, fragment_prayer_timings).commit();

                return true;


            case R.id.menu_tasbih:

                fragment_tasbih = new TasbihFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_fragment, fragment_tasbih).commit();

                return true;

            case R.id.menu_duas:

                fragment_duas = new DuasFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_fragment, fragment_duas).commit();
                return true;
        }

        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_language, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        String language;
        switch (id) {

            case R.id.ar:
                language = "ar";
                setLocale(language);

                return true;

            case R.id.en:
                language = "en";
                setLocale(language);
                return true;

            case R.id.qiblah:
                Intent intent = new Intent(getApplicationContext(), Kaaba_direction.class);
                startActivity(intent);
                return true;
        }
        return false;

    }

    @SuppressWarnings("deprecation")
    private void setLocale(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);

    }

}