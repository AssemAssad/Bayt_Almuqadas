package com.example.baytalmuqadas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.baytalmuqadas.fragments.SuratFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private Fragment fragment;
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
                fragment = new SuratFragment();
                break;
            case R.id.menu_penanda:
                break;
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_fragment,fragment).commit();
        return false;
    }
}