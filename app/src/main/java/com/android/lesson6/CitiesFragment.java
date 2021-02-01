package com.android.lesson6;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class CitiesFragment extends Fragment {
//    фрагмент для просмотра списка городов

    private boolean isLandscape;
    static final String ARG_INDEX = "index";

    // activity создана, можно к ней обращаться. Выполним начальные действия
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        Notes notes = savedInstanceState.getParcelable(ARG_INDEX);
        // Определение, можно ли будет расположить рядом герб в другом фрагменте
        isLandscape = getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;
        // Если можно нарисовать рядом герб, то сделаем это
        if (isLandscape) {
//            showLandCoatOfArms(notes);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cities, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList(view);
    }

    private void initList(View view) {
        LinearLayout layoutView = (LinearLayout) view;
        String[] titles = getResources().getStringArray(R.array.titles);
        String[] messages = getResources().getStringArray(R.array.messages_text);

        // В этом цикле создаём элемент TextView,
        // заполняем его значениями,
        // и добавляем на экран.
        // Кроме того, создаём обработку касания на элемент
        Context context = getContext();
        ArrayList<Notes> notesArrayList = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            if (context != null) {
                Notes notes = new Notes(titles[i], messages[i]);
                notesArrayList.add(i, notes);
                TextView textView = new TextView(context);
                textView.setText(titles[i]);
                textView.setTextSize(30);
                layoutView.addView(textView);
                final int fi = i;
                textView.setOnClickListener(v -> showCoatOfArms(notes));
            }
        }
    }

    private void showCoatOfArms(Notes notes) {
        if (isLandscape) {
            showLandCoatOfArms(notes);
        } else {
            showPortCoatOfArms(notes);
            Log.d("KEY", "выбор элемента index" + notes.title);
        }
    }

    // Показать герб в ландшафтной ориентации
    private void showLandCoatOfArms(Notes notes) {
        // Создаём новый фрагмент с текущей позицией для вывода герба
        CoatOfArmsFragment detail = CoatOfArmsFragment.newInstance(notes);

        // Выполняем транзакцию по замене фрагмента
        FragmentActivity context = getActivity();
        if (context != null) {
            FragmentManager fragmentManager = context.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.coat_of_arms, detail);  // замена фрагмента
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            fragmentTransaction.commit();
        }
    }

    // Показать герб в портретной ориентации.
    private void showPortCoatOfArms(Notes notes) {
        // Откроем вторую activity
        Context context = getContext();
        if (context != null) {
            Intent intent = new Intent(context, CoatOfArmsActivity.class);
            // и передадим туда параметры
            intent.putExtra(CoatOfArmsFragment.ARG_INDEX, notes);
            startActivity(intent);
            Log.d("KEY", "интентом передаём index " + notes.title + " в CoatOfArmsActivity и открываем его" );
        }
    }
}
