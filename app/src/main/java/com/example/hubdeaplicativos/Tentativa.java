package com.example.hubdeaplicativos;

import java.util.List;

public class Tentativa {
    private final String palpite;
    private final List<Feedback> feedbacks;

    public Tentativa(String palpite, List<Feedback> feedbacks) {
        this.palpite = palpite;
        this.feedbacks = feedbacks;
    }

    public String getPalpite() {
        return palpite;
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }
}
