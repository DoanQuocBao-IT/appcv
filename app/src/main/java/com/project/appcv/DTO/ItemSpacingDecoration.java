package com.project.appcv.DTO;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class ItemSpacingDecoration extends RecyclerView.ItemDecoration {
    private int spacing;

    public ItemSpacingDecoration(int spacing) {
        this.spacing = spacing;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.bottom = spacing;

        // skip first item
        if (parent.getChildAdapterPosition(view) == 0||parent.getChildAdapterPosition(view) == 2||parent.getChildAdapterPosition(view) == 4) {
            outRect.top = 0;
        }
    }
}