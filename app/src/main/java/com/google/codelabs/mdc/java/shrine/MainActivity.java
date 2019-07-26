package com.google.codelabs.mdc.java.shrine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.codelabs.mdc.java.shrine.fragments.DiscoverFragment;
import com.google.codelabs.mdc.java.shrine.fragments.ProductGridFragment;

public class MainActivity extends AppCompatActivity implements NavigationHost{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.bottom_navigation);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.activity_main_frame_layout, new ProductGridFragment())
                    .commit();
        }


    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    navigateTo(new ProductGridFragment(),false);
                    return true;
                case R.id.navigation_video:
                    return true;
                case R.id.navigation_discover:
                    navigateTo(new DiscoverFragment(),false);
                    return true;
                case R.id.navigation_profile:
                    navigateTo(new ProfileFragment(),false);
                    return true;
            }
            return false;
        }
    };


    @Override
    public void navigateTo(Fragment fragment, boolean addToBackstack) {
        FragmentTransaction transaction =
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.activity_main_frame_layout, fragment);

        if (addToBackstack) {
            transaction.addToBackStack(null);
        }

        transaction.commit();
    }
}
