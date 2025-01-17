package com.example.listycity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private final ArrayList<String> items;
    private final OnDeleteClickListener deleteClickListener;

    public interface OnDeleteClickListener {
        void onDeleteClick(int position);
    }

    public ItemAdapter(ArrayList<String> items, OnDeleteClickListener deleteClickListener) {
        this.items = items;
        this.deleteClickListener = deleteClickListener;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView contentView;
        ImageButton deleteButton;

        public ItemViewHolder(View itemView) {
            super(itemView);
            contentView = itemView.findViewById(R.id.content_view);
            deleteButton = itemView.findViewById(R.id.delete_button);
        }
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        /* Set text items */
        holder.contentView.setText(items.get(position));

        /* Set delete click handler */
        holder.deleteButton.setOnClickListener(v -> deleteClickListener.onDeleteClick(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
