package com.example.hubdeaplicativos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class TentativaAdapter extends ListAdapter<Tentativa, TentativaAdapter.TentativaViewHolder> {

    protected TentativaAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public TentativaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tentativa, parent, false);
        return new TentativaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TentativaViewHolder holder, int position) {
        Tentativa tentativa = getItem(position);
        holder.bind(tentativa);
    }

    public static class TentativaViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewTentativa;
        private final View[] feedbackViews;

        public TentativaViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTentativa = itemView.findViewById(R.id.textViewTentativa);
            View feedbackView1 = itemView.findViewById(R.id.feedbackView1);
            View feedbackView2 = itemView.findViewById(R.id.feedbackView2);
            View feedbackView3 = itemView.findViewById(R.id.feedbackView3);
            View feedbackView4 = itemView.findViewById(R.id.feedbackView4);
            feedbackViews = new View[]{feedbackView1, feedbackView2, feedbackView3, feedbackView4};
        }

        public void bind(Tentativa tentativa) {
            textViewTentativa.setText(tentativa.getPalpite());

            for (int i = 0; i < feedbackViews.length; i++) {
                if (i < tentativa.getFeedbacks().size()) {
                    Feedback feedback = tentativa.getFeedbacks().get(i);
                    feedbackViews[i].setBackgroundResource(getFeedbackDrawable(feedback));
                }
            }
        }

        private int getFeedbackDrawable(Feedback feedback) {
            switch (feedback) {
                case CERTO:
                    return R.drawable.feedback_certo;
                case POSICAO_ERRADA:
                    return R.drawable.feedback_posicao_errada;
                case ERRADO:
                default:
                    return R.drawable.feedback_errado;
            }
        }
    }

    private static final DiffUtil.ItemCallback<Tentativa> DIFF_CALLBACK = new DiffUtil.ItemCallback<>() {
        @Override
        public boolean areItemsTheSame(@NonNull Tentativa oldItem, @NonNull Tentativa newItem) {
            return oldItem.getPalpite().equals(newItem.getPalpite());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Tentativa oldItem, @NonNull Tentativa newItem) {
            return oldItem.getPalpite().equals(newItem.getPalpite()) &&
                    oldItem.getFeedbacks().equals(newItem.getFeedbacks());
        }
    };
}
