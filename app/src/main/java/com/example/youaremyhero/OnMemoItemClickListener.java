package com.example.youaremyhero;

import android.view.View;

public interface OnMemoItemClickListener {
    public void onItemClick(MemoAdapter.ViewHolder holder, View view, int position);
}
