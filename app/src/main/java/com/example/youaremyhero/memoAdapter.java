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

public class memoAdapter extends RecyclerView.Adapter<memoAdapter.ViewHolder>
                          implements onMemoItemClickListener {

    ArrayList<memo> memoitems = new ArrayList<memo>();

    onMemoItemClickListener listener;

    int layoutType = 0;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.memo_item, viewGroup, false);
        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        memo item = memoitems.get(position);
        viewHolder.setItem(item);

    }

    @Override
    public int getItemCount() {
        return memoitems.size();
    }

    public void addItem(memo item) {
        memoitems.add(item);
    }

    public void setItems(ArrayList<memo> items) {
        this.memoitems = items;
    }

    public memo getItem(int position) {
        return memoitems.get(position);
    }

    public void setOnItemClickListener(onMemoItemClickListener listener) {
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
        TextView theme;
        TextView memoContent;


        public ViewHolder(@NonNull View itemView, final onMemoItemClickListener listener) {
            super(itemView);

            layout1 = itemView.findViewById(R.id.layout1);

            theme = itemView.findViewById(R.id.theme);
            memoContent = itemView.findViewById(R.id.memoContent);

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

        public void setItem(memo item) {

            theme.setText(item.getTheme());
            memoContent.setText(item.getMemoContent());

        }



    }
}
