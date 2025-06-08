package com.example.hubdeaplicativos;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class SucessoCodigoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sucesso_codigo);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(getString(R.string.titulo_sucesso_codigo));
        }

        TextView textViewNumTentativas = findViewById(R.id.textViewNumTentativas);
        TextView textViewTempoDecorrido = findViewById(R.id.textViewTempoDecorrido);
        TextView textViewMensagemSecreta = findViewById(R.id.textViewMensagemSecreta);
        Button buttonVoltarMenuSucesso = findViewById(R.id.buttonVoltarMenuSucesso);

        Intent intent = getIntent();
        int numTentativas = intent.getIntExtra("NUM_TENTATIVAS", 0);
        long tempoDecorridoMs = intent.getLongExtra("TEMPO_DECORRIDO", 0);

        String textoTentativas = getString(R.string.label_numero_tentativas) + " " + numTentativas;
        textViewNumTentativas.setText(textoTentativas);

        String textoTempo = getString(R.string.label_tempo_decorrido) + " " + formatarTempo(tempoDecorridoMs);
        textViewTempoDecorrido.setText(textoTempo);

        tocarSomVitoria();
        animarMensagemSecreta(textViewMensagemSecreta);

        buttonVoltarMenuSucesso.setOnClickListener(v -> finish());
    }

    private String formatarTempo(long millis) {
        long segundos = TimeUnit.MILLISECONDS.toSeconds(millis);
        long milissegundosRestantes = millis % 1000;
        return String.format(Locale.getDefault(), "%d segundos e %d ms", segundos, milissegundosRestantes);
    }

    private void tocarSomVitoria() {
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.tetra);
        mediaPlayer.setOnCompletionListener(MediaPlayer::release);
        mediaPlayer.start();
    }

    private void animarMensagemSecreta(TextView textView) {
        textView.setVisibility(TextView.VISIBLE);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.bounce_animation);
        textView.startAnimation(animation);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}