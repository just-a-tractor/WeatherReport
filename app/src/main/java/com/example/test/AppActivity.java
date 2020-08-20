package com.example.test;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class AppActivity extends AppCompatActivity {

    private AsyncHelper asyncHelper;

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_layout);

        asyncHelper = new AsyncHelper(this);

        setFragment(R.layout.cities, false,false);
    }

    @Override
    public void onBackPressed() {

        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    public void setFragment(int layoutId, boolean addToBackStack, boolean animate) {

        Fragment fragment = null;
        switch (layoutId) {

            case R.layout.cities:
                fragment = new FragmentWeather();
                break;

            case R.layout.city_search:
                fragment = new FragmentCity();
                break;

        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if(animate)
                    ft.setCustomAnimations(R.anim.slide_left, R.anim.slide_right, R.anim.slide_left_2, R.anim.slide_right_2);
            ft.replace(R.id.FragmentLayout, fragment);
            if(addToBackStack)
                ft.addToBackStack(null);
            ft.commit();
        }
    }

    public AsyncHelper getAsyncHelper(){
        return asyncHelper;
    }
}
