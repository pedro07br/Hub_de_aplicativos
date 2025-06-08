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

public class ItemAdapter extends ListAdapter<String, ItemAdapter.ItemViewHolder> {

    public interface OnItemClickListener {
        void onDeleteClick(String item);
    }

    private final OnItemClickListener listener;

    protected ItemAdapter(OnItemClickListener listener) {
        super(DIFF_CALLBACK);
        this.listener = listener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_valor, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        String item = getItem(position);
        holder.bind(item, listener);
    }
    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewNomeItem;
        private final ImageButton imageButtonDeleteItem;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNomeItem = itemView.findViewById(R.id.textViewNomeItem);
            imageButtonDeleteItem = itemView.findViewById(R.id.imageButtonDeleteItem);
        }

        public void bind(final String item, final OnItemClickListener listener) {
            textViewNomeItem.setText(item);
            imageButtonDeleteItem.setOnClickListener(v -> listener.onDeleteClick(item));
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
