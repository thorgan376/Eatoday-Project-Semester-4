
package eatoday.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import eatoday.com.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ActivityMainBinding binding;
    public Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnItemSelectedListener((@NonNull MenuItem item) -> {

                switch(item.getItemId())
                {
                    case R.id.settings:
                        startActivity(new Intent(getApplicationContext(), MainUpdate.class));
                        overridePendingTransition(0,0);
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
                        return true;
                }
                return false;
        });

    }
}
//        bottomNavigationView.setOnItemSelectedListener((@NonNull MenuItem item) -> {
//            int xx = 11;
//            return onOptionsItemSelected(item);
//        });
//    }
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
//                intent = new Intent(MainActivity.this, MainActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.settings:
//                intent = new Intent(MainActivity.this, MainUpdate.class);
//                startActivity(intent);
//                break;
//            case R.id.person:
//                Intent intent = new Intent(MainActivity.this, MainUpdate.class);
//                startActivity(intent);
//                break;
//            case R.id.shop:
//                intent = new Intent(MainActivity.this, MainUpdate.class);
//                startActivity(intent);
//                break;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//        return super.onOptionsItemSelected(item);



