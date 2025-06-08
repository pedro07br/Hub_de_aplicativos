package com.example.hubdeaplicativos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class CategoriaAdapter extends ListAdapter<String, CategoriaAdapter.CategoriaViewHolder> {

    public interface OnCategoriaClickListener {
        void onItemClick(String categoria);
        void onDeleteClick(String categoria);
    }

    private final OnCategoriaClickListener listener;

    protected CategoriaAdapter(OnCategoriaClickListener listener) {
        super(DIFF_CALLBACK);
        this.listener = listener;
    }

    @NonNull
    @Override
    public CategoriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categoria, parent, false);
        return new CategoriaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriaViewHolder holder, int position) {
        String categoria = getItem(position);
        holder.bind(categoria, listener);
    }

    public static class CategoriaViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewNomeCategoria;
        private final ImageButton imageButtonDeleteCategoria;

        public CategoriaViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNomeCategoria = itemView.findViewById(R.id.textViewNomeCategoria);
            imageButtonDeleteCategoria = itemView.findViewById(R.id.imageButtonDeleteCategoria);
        }

        public void bind(final String categoria, final OnCategoriaClickListener listener) {
            textViewNomeCategoria.setText(categoria);
            itemView.setOnClickListener(v -> listener.onItemClick(categoria));
            imageButtonDeleteCategoria.setOnClickListener(v -> listener.onDeleteClick(categoria));
        }
    }
    private static final DiffUtil.ItemCallback<String> DIFF_CALLBACK = new DiffUtil.ItemCallback<>() {
        @Override
        public boolean areItemsTheSame(@NonNull String oldItem, @NonNull String newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areContentsTheSame(@NonNull String oldItem, @NonNull String newItem) {
            return oldItem.equals(newItem);
        }
    };
}