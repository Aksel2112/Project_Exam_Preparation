package com.example.testdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ListView list;
    private String[] array;
    private ArrayAdapter<String> adapter;
    private DrawerLayout drawer;

    private AppBarConfiguration mAppBarConfiguration;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = findViewById(R.id.listView);
        array = getResources().getStringArray(R.array.topics_array);
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,new ArrayList<String>(Arrays.asList(array)));
        list.setAdapter(adapter);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.menu_lesson);
        setSupportActionBar(toolbar);



        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_lesson, R.id.nav_test, R.id.nav_exam)
                .setDrawerLayout(drawer)
                .build();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,Test_Content_Activity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_lesson)
        {
            Toast.makeText(this, "You wonna learn? LET'S LEARN!", Toast.LENGTH_SHORT).show();
            toolbar.setTitle(R.string.menu_lesson);
            array = getResources().getStringArray(R.array.topics_array);
            adapter.clear();
            adapter.addAll(array);
            adapter.notifyDataSetChanged();
        }
        else if (id == R.id.nav_test)
        {
            Toast.makeText(this, "Check your skills", Toast.LENGTH_SHORT).show();
            toolbar.setTitle(R.string.menu_tests);
            array = getResources().getStringArray(R.array.tests_array);
            adapter.clear();
            adapter.addAll(array);
            adapter.notifyDataSetChanged();
        }
        else if (id == R.id.nav_exam)
        {
            Toast.makeText(this, "Good luck!", Toast.LENGTH_SHORT).show();
            toolbar.setTitle(R.string.menu_exams);
            array = getResources().getStringArray(R.array.exams_array);
            adapter.clear();
            adapter.addAll(array);
            adapter.notifyDataSetChanged();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
