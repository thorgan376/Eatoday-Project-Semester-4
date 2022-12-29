package eatoday.com;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import eatoday.com.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private HomeFragment homeFragment = new HomeFragment();
    private NotificationFragment notificationFragment = new NotificationFragment();
    private MyListFragment myListFragment = new MyListFragment();
    private ProfileFragment profileFragment = new ProfileFragment();
    private MyPostFragment myPostFragment = new MyPostFragment();
    private FragmentManager fragmentManager;
    private Fragment active = homeFragment;

    public void openMyPostFragment() {
        // use replace
        FragmentTransaction fragmentTransaction5 = fragmentManager.beginTransaction();
        fragmentTransaction5.replace(R.id.frameLayout,myPostFragment).addToBackStack(null).commit();
//        use add
//        fragmentManager.beginTransaction().add(R.id.frameLayout, myPostFragment).hide(notificationFragment).commit();
//        fragmentManager.beginTransaction().hide(active).show(myPostFragment).commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        profileFragment.setCallback(new ProfileFragment.Callback() {
            @Override
            public void onClickFood() {
                openMyPostFragment();
            }
        });
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.frameLayout, profileFragment).hide(profileFragment).commit();
        fragmentManager.beginTransaction().add(R.id.frameLayout, notificationFragment).hide(notificationFragment).commit();
        fragmentManager.beginTransaction().add(R.id.frameLayout, myListFragment).hide(myListFragment).commit();
        fragmentManager.beginTransaction().add(R.id.frameLayout, homeFragment).commit();
        binding.bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home:
                    // use replace
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frameLayout, homeFragment).addToBackStack(null).commit();
//                    fragmentManager.beginTransaction().hide(active).show(homeFragment).commit();
//                    active = homeFragment;
                    return true;
                case R.id.notification:
                    // use replace
                    FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction();
                    fragmentTransaction2.replace(R.id.frameLayout, notificationFragment).addToBackStack(null).commit();
//                    fragmentManager.beginTransaction().hide(active).show(notificationFragment).commit();
//                    active = notificationFragment;
                    return true;
                case R.id.list:
                    // use replace
                    FragmentTransaction fragmentTransaction3 = fragmentManager.beginTransaction();
                    fragmentTransaction3.replace(R.id.frameLayout, myListFragment).addToBackStack(null).commit();
//                    fragmentManager.beginTransaction().hide(active).show(myListFragment).commit();
//                    active = myListFragment;
                    return true;
                case R.id.profile:
                    // use replace
                    FragmentTransaction fragmentTransaction4 = fragmentManager.beginTransaction();
                    fragmentTransaction4.replace(R.id.frameLayout,profileFragment).addToBackStack(null).commit();
//                    fragmentManager.beginTransaction().hide(active).show(profileFragment).commit();
//                    active = profileFragment;
                    return true;
            }
            return false;
        }
    };
}



