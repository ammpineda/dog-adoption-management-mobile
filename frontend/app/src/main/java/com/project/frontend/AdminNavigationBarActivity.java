package com.project.frontend;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AdminNavigationBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_navigation_bar);

        BottomNavigationView bottomNavigationView = findViewById(R.id.adminBottomNavView);
        FrameLayout frameLayout = findViewById(R.id.adminFrameLayout);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if(itemId == R.id.navManageDogs){
                    loadFragment(new ManageDogsFragment(), false);

                } else if (itemId == R.id.navManageApplications) {
                    loadFragment(new ManageApplicationsFragment(), false);

                } else if (itemId == R.id.navLogout) {
                    Intent intent = new Intent(AdminNavigationBarActivity.this, UserNavigationBarActivity.class);
                    startActivity(intent);

                } else {
                    loadFragment(new LoginAdminFragment(), false);

                }

                return true;
            }
        });
        loadFragment(new ManageDogsFragment(), true);
    }

    private void loadFragment(Fragment fragment, boolean isAppInitialized){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(isAppInitialized){
            fragmentTransaction.add(R.id.adminFrameLayout, fragment);
        } else {
            fragmentTransaction.replace(R.id.adminFrameLayout, fragment);

        }

        fragmentTransaction.commit();
    }
}