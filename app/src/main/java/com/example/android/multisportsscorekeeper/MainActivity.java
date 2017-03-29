package com.example.android.multisportsscorekeeper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    final FragmentManager fm = getSupportFragmentManager();
    Fragment welcome = new WelcomeFragment();
    final Fragment football = new FootballFragment();
    final Fragment basketball = new BasketballFragment();
    final Fragment volleyball = new VolleyballFragment();
    Fragment active = welcome;

    /*private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_football:
                    selectedFragment = FootballFragment.newInstance();
                    break;
                case R.id.navigation_basketball:
                    selectedFragment = BasketballFragment.newInstance();
                    break;
                case R.id.navigation_volleyball:
                    selectedFragment = VolleyballFragment.newInstance();
                    break;
            }
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.content, selectedFragment);
            transaction.commit();
            return true;
        }
    };*/

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            if (welcome != null){
                fm.beginTransaction().remove(welcome).commit();
                welcome = null;
            }
            switch (item.getItemId()) {
                case R.id.navigation_football:
                    fm.beginTransaction().hide(active).show(football).commit();
                    active = football;
                    break;
                case R.id.navigation_basketball:
                    fm.beginTransaction().hide(active).show(basketball).commit();
                    active = basketball;
                    break;
                case R.id.navigation_volleyball:
                    fm.beginTransaction().hide(active).show(volleyball).commit();
                    active = volleyball;
                    break;
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        fm.beginTransaction().add(R.id.content, volleyball, "3").commit();
        fm.beginTransaction().add(R.id.content, basketball, "2").commit();
        fm.beginTransaction().add(R.id.content,football, "1").commit();
        fm.beginTransaction().add(R.id.content,welcome, "0").commit();
        fm.beginTransaction().hide(volleyball).commit();
        fm.beginTransaction().hide(basketball).commit();
        fm.beginTransaction().hide(football).commit();

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}
