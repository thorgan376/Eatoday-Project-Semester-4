package eatoday.com;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import eatoday.com.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private HomeFragment homeFragment = new HomeFragment();
    private NotificationFragment notificationFragment = new NotificationFragment();
    private MyListFragment myListFragment = new MyListFragment();
    private ProfileFragment profileFragment = new ProfileFragment();

    private FragmentManager fragmentManager;
    private Fragment active = homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.frameLayout, profileFragment).hide(profileFragment).commit();
        fragmentManager.beginTransaction().add(R.id.frameLayout, notificationFragment).hide(notificationFragment).commit();
        fragmentManager.beginTransaction().add(R.id.frameLayout, myListFragment).hide(myListFragment).commit();
        fragmentManager.beginTransaction().add(R.id.frameLayout, homeFragment).commit();
        binding.bottomNavigation.setOnNavigationItemSelectedListener (mOnNavigationItemSelectedListener);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home:
                    fragmentManager.beginTransaction().hide(active).show(homeFragment).commit();
                    active = homeFragment;
                    return true;
                case R.id.notification:
                    fragmentManager.beginTransaction().hide(active).show(notificationFragment).commit();
                    active = notificationFragment;
                    return true;
                case R.id.list:
                    fragmentManager.beginTransaction().hide(active).show(myListFragment).commit();
                    active = myListFragment;
                    return true;
                case R.id.profile:
                    fragmentManager.beginTransaction().hide(active).show(profileFragment).commit();
                    active = profileFragment;
                    return true;
            }
            return false;
        }
    };
}



