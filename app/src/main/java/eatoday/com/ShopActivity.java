package eatoday.com;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import eatoday.com.databinding.ActivityMainBinding;

public class ShopActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ActivityMainBinding binding;
    public Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_product_activity);
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.shop);
        bottomNavigationView.setOnItemSelectedListener((@NonNull MenuItem item) -> {

            switch(item.getItemId())
            {
                case R.id.settings:
                    startActivity(new Intent(getApplicationContext(), MainUpdate.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.shop:
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
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        bottomNavigationView = findViewById(R.id.bottomNavigationView);
//        bottomNavigationView.setOnItemSelectedListener((@NonNull MenuItem item) -> {
//            return onOptionsItemSelected(item);
//        });
//    }
//
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.bottom_nav_menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.home:
//                intent = new Intent(MainUpdate.this, MainActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.settings:
//                intent = new Intent(MainUpdate.this, MainUpdate.class);
//                startActivity(intent);
//                break;
//            case R.id.person:
//                Intent intent = new Intent(MainUpdate.this, MainUpdate.class);
//                startActivity(intent);
//                break;
//            case R.id.shop:
//                intent = new Intent(MainUpdate.this, MainUpdate.class);
//                startActivity(intent);
//                break;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//        return super.onOptionsItemSelected(item);
//    }
