package com.android.lesson6;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

// фрагмент для просмотра герба
public class CoatOfArmsFragment extends Fragment {

    static final String ARG_INDEX = "index";
    private Note notes;

    public static CoatOfArmsFragment newInstance(Note notes) {
        CoatOfArmsFragment f = new CoatOfArmsFragment();    // создание
        // Передача параметра
        Bundle args = new Bundle();
        args.putParcelable(ARG_INDEX, notes);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            notes = getArguments().getParcelable(ARG_INDEX);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_coat_of_arms, container, false);
        AppCompatTextView appCompatTextView = view.findViewById(R.id.coat_of_arms);
        appCompatTextView.setText(notes.message);
        return view;
    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        AppCompatTextView appCompatTextView = view.findViewById(R.id.coat_of_arms);
//        // Получить из ресурсов массив указателей на изображения гербов
//        TypedArray images = getResources().obtainTypedArray(R.array.messages_text);
//        // Выбрать по индексу подходящий
//        appCompatTextView.setText(images.getResourceId(index, -1));
//    }
}
