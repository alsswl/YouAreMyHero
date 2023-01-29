package com.example.youaremyhero;

import android.view.View;

public interface OnDiaryItemClickListener {
    public void onItemClick(DiaryAdapter.ViewHolder holder, View view, int position);
}
