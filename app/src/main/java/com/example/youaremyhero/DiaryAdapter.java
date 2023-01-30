package com.example.youaremyhero;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.ViewHolder>
                          implements OnDiaryItemClickListener {

    ArrayList<Diary> items = new ArrayList<Diary>();

    OnDiaryItemClickListener listener;

    int layoutType = 0;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.diary_item, viewGroup, false);
        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Diary item = items.get(position);
        viewHolder.setItem(item);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Diary item) {
        items.add(item);
    }

    public void setItems(ArrayList<Diary> items) {
        this.items = items;
    }

    public Diary getItem(int position) {
        return items.get(position);
    }

    public void setOnItemClickListener(OnDiaryItemClickListener listener) {
        this.listener = listener;
    }


    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if (listener != null) {
            listener.onItemClick(holder, view, position);
        }

    }

    public void switchLayout(int position) {
        layoutType = position;
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layout1;
        ImageView mood;
        TextView theme;
        TextView date;
        TextView place;


        public ViewHolder(@NonNull View itemView, final OnDiaryItemClickListener listener) {
            super(itemView);

            layout1 = itemView.findViewById(R.id.layout1);

            mood = itemView.findViewById(R.id.mood);

            theme = itemView.findViewById(R.id.theme);
            date = itemView.findViewById(R.id.date);
            place = itemView.findViewById(R.id.place);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (listener != null) {
                        listener.onItemClick(ViewHolder.this, view, position);
                    }
                }
            });

        }

        public void setItem(Diary item) {


            String mood = item.getMood();
            int moodIndex = Integer.parseInt(mood);
            setMoodImage(moodIndex);

            theme.setText(item.getTheme());
            place.setText(item.getPlace());
            date.setText(item.getCreateDateStr());

        }

        public void setMoodImage(int moodIndex) {
            switch (moodIndex) {
                case 4:
                    mood.setImageResource(R.drawable.face1);
                    break;
                case 3:
                    mood.setImageResource(R.drawable.face2);
                    break;
                case 2:
                    mood.setImageResource(R.drawable.face3);
                    break;
                case 1:
                    mood.setImageResource(R.drawable.face4);
                    break;
                case 0:
                    mood.setImageResource(R.drawable.face5);
                    break;
                default:
                    mood.setImageResource(R.drawable.face3);
                    break;
            }

        }

    }
}
