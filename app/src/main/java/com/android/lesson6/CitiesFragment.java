package com.android.lesson6;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.lesson6.ui.AdapterRecycler;

import java.util.ArrayList;

public class CitiesFragment extends Fragment {
//    фрагмент для просмотра списка городов

    private boolean isLandscape;
    static final String ARG_INDEX = "index";
    ArrayList<Note> notesArrayList = new ArrayList<>();

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
        View view = inflater.inflate(R.layout.fragment_cities_recycler, container, false);
//        return inflater.inflate(R.layout.fragment_cities, container, false);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList(view);

        RecyclerView notesRecyclerView = view.findViewById(R.id.recycler_view);
//        String[] strings = getResources().getStringArray(R.array.titles);
        AdapterRecycler adapterRecycler = new AdapterRecycler(notesArrayList);
        notesRecyclerView.setAdapter(adapterRecycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        notesRecyclerView.setLayoutManager(linearLayoutManager);
    }

    private void initList(View view) {
        String[] titles = getResources().getStringArray(R.array.titles);
        String[] messages = getResources().getStringArray(R.array.messages_text);
        LinearLayout linearLayout = (LinearLayout) view;
        // В этом цикле создаём элемент TextView,
        // заполняем его значениями,
        // и добавляем на экран.
        // Кроме того, создаём обработку касания на элемент
        Context context = getContext();
        for (int i = 0; i < titles.length; i++) {
            if (context != null) {
                Note notes = new Note(titles[i], messages[i]);
                notesArrayList.add(i, notes);
//                TextView textView = new TextView(context);
//                textView.setText(notes.title);
//                textView.setTextSize(30);
//                linearLayout.addView(textView);
//                final int fi = i;
//                textView.setOnClickListener(v -> showCoatOfArms(notes));
//                textView.setOnLongClickListener(new AdapterView.OnLongClickListener() {
//                    @Override
//                    public boolean onLongClick(View view) {
//                        Activity activity = requireActivity();
//                        PopupMenu popupMenu = new PopupMenu(activity, view);
//                        activity.getMenuInflater().inflate(R.menu.popup, popupMenu.getMenu());
//                        Menu menu = popupMenu.getMenu();
////                        menu.findItem(R.menu.popup).setVisible(true); // видимость элемента меню
//                        menu.add(0, 123456, 12, "Text menu 3"); // добавить пункт к меню
//                        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                            @Override
//                            public boolean onMenuItemClick(MenuItem item) {
//                                int id = item.getItemId();
//                                switch (id){
//                                    case R.id.delete_note:
//                                        Toast.makeText(getContext(), textView.getText() + " delete note ", Toast.LENGTH_SHORT).show();
//                                        return true;
//                                    case R.id.share:
//                                        Toast.makeText(getContext(), textView.getText() + " share ", Toast.LENGTH_SHORT).show();
//                                        return true;
//                                }
//                                return true;
//                            }
//                        });
//                        Log.d("KEY", "PopupMenu " + textView.getText());
//                        popupMenu.show();
//                        return true;
//                    }
//                });
            }
        }
    }

    private void showCoatOfArms(Note notes) {
        if (isLandscape) {
            showLandCoatOfArms(notes);
        } else {
            showPortCoatOfArms(notes);
            Log.d("KEY", "выбор элемента index" + notes.title);
        }
    }

    // Показать содержание заметки в ландшафтной ориентации
    private void showLandCoatOfArms(Note notes) {
        // Создаём новый фрагмент с текущей позицией для вывода содержания заметки
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

    // Показать содержание заметки в портретной ориентации.
    private void showPortCoatOfArms(Note notes) {
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
