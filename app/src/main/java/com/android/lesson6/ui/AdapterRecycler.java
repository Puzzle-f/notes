package com.android.lesson6.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.lesson6.CitiesFragment;
import com.android.lesson6.Note;
import com.android.lesson6.R;

import java.util.ArrayList;

public class AdapterRecycler extends RecyclerView.Adapter<AdapterRecycler.MyViewHolder> {

    private ArrayList<Note> noteTitles;
    private OnItemClickListener itemClickListener;  // Слушатель будет устанавливаться извне

    public AdapterRecycler(ArrayList<Note> noteTitles) {
        this.noteTitles = noteTitles;
    }

    //  вызывается столько раз, сколько элементов одновременно помещается на экране
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_item_recycler, parent, false);
        return new MyViewHolder(v);
    }

    //    вызывается столько раз, сколько пользователь будет просматривать элементов с учетом прокрутки
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.onBind(noteTitles.get(position));
    }

    //      возвращение размера списка
    @Override
    public int getItemCount() {
        return noteTitles.size();
    }

    //      Интерфейс для обработки нажатий
    public interface OnItemClickListener {
        void OnItemClick(View view, int position);
    }

    //    сеттер слушателя нажатий
    public void SetOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    //    класс элемента списка
    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

//        определяем данные, которые хотим отображать в карточке
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view_recycler);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(itemClickListener!=null){
                        itemClickListener.OnItemClick(v,getAdapterPosition());
                    }
                }
            });
        }

//        добавляем непосредственно данные, которые необходимо отображать в карточке
        public void onBind(Note notes) {
            textView.setText(notes.title);
        }
    }
}
