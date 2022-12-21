package eatoday.com;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import eatoday.com.databinding.ActivityMainBinding;

public class PersonActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ActivityMainBinding binding;
    public Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person);
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.person);
        bottomNavigationView.setOnItemSelectedListener((@NonNull MenuItem item) -> {

            switch(item.getItemId())
            {
                case R.id.person:
                    return true;
                case R.id.shop:
                    startActivity(new Intent(getApplicationContext(), ShopActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.settings:
                    startActivity(new Intent(getApplicationContext(), MainUpdate.class));
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
