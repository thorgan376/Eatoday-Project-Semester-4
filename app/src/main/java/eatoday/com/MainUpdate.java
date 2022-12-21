package eatoday.com;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import eatoday.com.databinding.ActivityMainBinding;

public class MainUpdate extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ActivityMainBinding binding;
    public Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_update);
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.settings);
        bottomNavigationView.setOnItemSelectedListener((@NonNull MenuItem item) -> {

            switch(item.getItemId())
            {
                case R.id.settings:
                    return true;
                case R.id.shop:
                    startActivity(new Intent(getApplicationContext(), ShopActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.person:
                    startActivity(new Intent(getApplicationContext(), PersonActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.home:
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    overridePendingTransition(0,0);
                    return true;
            }
            return false;
        });

    }
}