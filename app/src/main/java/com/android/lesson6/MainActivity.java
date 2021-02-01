package com.android.lesson6;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
            } else {
                Toast toast = Toast.makeText(this, "сохранение заметки", Toast.LENGTH_LONG);
                toast.show();
                TextInputEditText textInputEditText = findViewById(R.id.edit_text_x3);
                fragmentTransaction.remove(fragmentCreatingNote);
            }
            fragmentTransaction.commitAllowingStateLoss();
        };
        buttonAddNote.setOnClickListener(oclBtnOk);
    }
}


