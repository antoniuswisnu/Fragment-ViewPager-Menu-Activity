package com.example.myapplication;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import androidx.viewpager2.widget.ViewPager2;

public class MainActivity extends AppCompatActivity {

    TabLayoutMediator mediator;
//    EditText et1;
//    EditText et2;
//    EditText ett1;
//    EditText ett2;
//    EditText ett3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager2 viewPager2 = findViewById(R.id.pager);

//        et1.setKeyListener(null);
//        et2.setKeyListener(null);
//        ett1.setKeyListener(null);
//        ett2.setKeyListener(null);
//        ett3.setKeyListener(null);

        SampleAdapter adapter = new SampleAdapter(getSupportFragmentManager(), getLifecycle());
        viewPager2.setAdapter(adapter);

        mediator = new TabLayoutMediator(tabLayout, viewPager2, (tab, position)->{
//            tab.setText("Masuk" + (position+1));
            if(position == 0){
                tab.setText("Masuk");
            } else {
                tab.setText("Daftar");
            }
        });
        mediator.attach();

//        mediator = new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
//            @Override
//            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
//            tab.setText("Tab"+ position+1);
//            }
//        });

    }

    public void showAbsenActivity(View view){
        Intent intent = new Intent(this, AbsenActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_menu, menu);
        return true;
//        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.menu_profile:
                Intent intent = new Intent(this, ProfileActivity.class);
                overridePendingTransition(0,0);
                startActivity(intent);
        }
        switch(item.getItemId()){
            case R.id.menu_favorite:
                Intent intent = new Intent(this, FavoriteActivity.class);
                overridePendingTransition(0,0);
                startActivity(intent);
        }
        switch(item.getItemId()){
            case R.id.menu_setting:
                Intent intent = new Intent(this, SettingsActivity.class);
                overridePendingTransition(0,0);
                startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }

}
