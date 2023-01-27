package eatoday.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import eatoday.com.databinding.ActivityMainBinding;
import eatoday.com.ui.AccountFragment;
import eatoday.com.ui.HomeFragment;
import eatoday.com.ui.MyListFragment;
import eatoday.com.ui.MyPostFragment;
import eatoday.com.ui.NotificationFragment;
import eatoday.com.ui.ProfileFragment;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private HomeFragment homeFragment = new HomeFragment();
    private NotificationFragment notificationFragment = new NotificationFragment();
    private MyListFragment myListFragment = new MyListFragment();
    private ProfileFragment profileFragment = new ProfileFragment();
    private MyPostFragment myPostFragment = new MyPostFragment();
    private AccountFragment accountFragment = new AccountFragment();
    private FragmentManager fragmentManager;
    private Fragment active = homeFragment;
    @Override
    protected void onResume() {
        super.onResume();
        //getting Root View that gets focus
        View rootView =((ViewGroup)findViewById(android.R.id.content)).
                getChildAt(0);
        rootView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    hideKeyboard(MainActivity.this);
                }
            }
        });
    }
    public static void hideKeyboard(Activity context) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow( context.getCurrentFocus().getWindowToken(), 0);
    }
    public void openMyPostFragment() {
        // use replace
        FragmentTransaction fragmentTransaction5 = fragmentManager.beginTransaction();
        fragmentTransaction5.replace(R.id.frameLayout,myPostFragment).addToBackStack(null).commit();
//        replaceFragment(myPostFragment);
//        use add
//        fragmentManager.beginTransaction().add(R.id.frameLayout, myPostFragment).hide(notificationFragment).commit();
//        fragmentManager.beginTransaction().hide(active).show(myPostFragment).commit();
    }
    public void openAccountFragment() {
        // use replace
        FragmentTransaction fragmentTransaction5 = fragmentManager.beginTransaction();
        fragmentTransaction5.replace(R.id.frameLayout,accountFragment).addToBackStack(null).commit();
    }
    public void replaceFragment (Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction() .setCustomAnimations(
                R.anim.slide_in,  // enter
                R.anim.fade_out,  // exit
                R.anim.fade_in,   // popEnter
                R.anim.slide_out  // popExit
        );
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction.replace(R.id.frameLayout, fragment).commit();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(homeFragment);
        binding.bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        profileFragment.setCallback(new ProfileFragment.Callback() {
            @Override
            public void onClickFood() {
                openMyPostFragment();
            }

            @Override
            public void onClickUser() {
                openAccountFragment();
            }

        });
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
                switch (item.getItemId()) {
                    case R.id.home:
                        replaceFragment(homeFragment);
                        break;
                    case R.id.notification:
                        replaceFragment(notificationFragment);
                        break;
                    case R.id.list:
                        replaceFragment(myListFragment);
                        break;
                    case R.id.profile:
                        replaceFragment(profileFragment);
                        break;
                }
                return true;
            };
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //call super
        super.onActivityResult(requestCode, resultCode, data);
    }

}



