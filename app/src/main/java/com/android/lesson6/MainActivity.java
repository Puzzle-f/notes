package com.android.lesson6;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    Button buttonAddNote;
    FragmentCreatingNote fragmentCreatingNote = new FragmentCreatingNote();
    boolean buttonAddIsChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initButton();
        initMenus();
    }

    // инициилизируем сам тулбар
    private void initMenus() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initDrawer(toolbar);
    }

    //    инициилизация бокового меню
    private void initDrawer(Toolbar toolbar) {
        final DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
//        класс следит за открытием и закрытием бокового меню drawerLayout а так же добаляет гамбургер меню
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

//        обработка кнопок бокового меню
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(item -> {
            String name = item.toString();
            System.out.println(name);
            Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
            return true;
        });
    }

    //    инициилизация меню поиска в тулбаре
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        инициилизируем кнопки тулбара
        getMenuInflater().inflate(R.menu.main, menu);
//        находим строку поиска и вешаем на неё слушателя
        MenuItem search = menu.findItem(R.id.action_search);

//        приложение падает на следующей строке

//        SearchView searchText = (SearchView) search.getActionView();
//        searchText.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                Toast.makeText(MainActivity.this, query, Toast.LENGTH_SHORT).show();
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return true;
//            }
//        });
        return true;
    }

    public void initButton() {
        buttonAddNote = findViewById(R.id.button_add_note);
        View.OnClickListener oclBtnOk = v -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if (!buttonAddIsChecked) {
//            открываем фрагмент ввода заметки
//            FragmentCreatingNote fragment = new FragmentCreatingNote();
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .add(R.id.cities, fragment).commitAllowingStateLoss();
                fragmentTransaction.add(R.id.cities, fragmentCreatingNote);
                buttonAddIsChecked = true;
                buttonAddNote.setText("Ok");
            } else {
                Toast toast = Toast.makeText(this, "сохранение заметки", Toast.LENGTH_LONG);
                toast.show();
                buttonAddIsChecked = false;
                buttonAddNote.setText("+");
                TextInputEditText textInputEditText = findViewById(R.id.edit_text_x3);
                fragmentTransaction.remove(fragmentCreatingNote);
            }
            fragmentTransaction.commitAllowingStateLoss();
        };
        buttonAddNote.setOnClickListener(oclBtnOk);
    }
}


