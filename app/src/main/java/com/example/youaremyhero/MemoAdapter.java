package com.example.youaremyhero;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.ViewHolder>
                          implements OnMemoItemClickListener {

    ArrayList<Memo> memoitems = new ArrayList<Memo>();

    OnMemoItemClickListener listener;

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
        Memo item = memoitems.get(position);
        viewHolder.setItem(item);

    }

    @Override
    public int getItemCount() {
        return memoitems.size();
    }

    public void addItem(Memo item) {
        memoitems.add(item);
    }

    public void setItems(ArrayList<Memo> items) {
        this.memoitems = items;
    }

    public Memo getItem(int position) {
        return memoitems.get(position);
    }

    public void setOnItemClickListener(OnMemoItemClickListener listener) {
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


        public ViewHolder(@NonNull View itemView, final OnMemoItemClickListener listener) {
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

        public void setItem(Memo item) {

            theme.setText(item.getTheme());
            memoContent.setText(item.getMemoContent());

        }



    }
}
