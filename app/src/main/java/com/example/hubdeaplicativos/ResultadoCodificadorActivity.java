package com.example.hubdeaplicativos;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class ResultadoCodificadorActivity extends AppCompatActivity {

    private boolean estaCodificado = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_codificador);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(getString(R.string.titulo_resultado_codificador));
        }

        TextView textViewLabelResultado = findViewById(R.id.textViewLabelResultado);
        TextView textViewResultado = findViewById(R.id.textViewResultado);
        Button buttonDecodificar = findViewById(R.id.buttonDecodificar);

        Intent intent = getIntent();

        final String mensagemOriginal = intent.getStringExtra("MENSAGEM_ORIGINAL");
        if (mensagemOriginal == null) {
            Toast.makeText(this, "Erro: Mensagem não recebida.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        final int chaveCifra = intent.getIntExtra("CHAVE_CIFRA", 0);
        final String mensagemCodificada = cifraDeCesar(mensagemOriginal, chaveCifra);

        textViewResultado.setText(mensagemCodificada);

        tocarSom();
        animarTextView(textViewResultado);

        buttonDecodificar.setOnClickListener(v -> {
            if (estaCodificado) {
                textViewResultado.setText(mensagemOriginal);
                textViewLabelResultado.setText(getString(R.string.label_original));
                buttonDecodificar.setText(R.string.botao_codificar);
                estaCodificado = false;
            } else {
                textViewResultado.setText(mensagemCodificada);
                textViewLabelResultado.setText(getString(R.string.label_resultado));
                buttonDecodificar.setText(R.string.botao_decodificar);
                estaCodificado = true;
            }
            tocarSom();
            animarTextView(textViewResultado);
        });
    }

    private String cifraDeCesar(String texto, int chave) {
        StringBuilder textoCifrado = new StringBuilder();
        int tamanhoAlfabeto = 26;

        for (int i = 0; i < texto.length(); i++) {
            char caractere = texto.charAt(i);

            if (Character.isLetter(caractere)) {
                char base = Character.isLowerCase(caractere) ? 'a' : 'A';
                int caractereOriginalPos = caractere - base;
                int caractereNovoPos = (caractereOriginalPos + chave) % tamanhoAlfabeto;
                if (caractereNovoPos < 0) {
                    caractereNovoPos += tamanhoAlfabeto;
                }
                char novoCaractere = (char) (base + caractereNovoPos);
                textoCifrado.append(novoCaractere);
            } else {
                textoCifrado.append(caractere);
            }
        }
        return textoCifrado.toString();
    }

    private void tocarSom() {
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.alerta);
        mediaPlayer.setOnCompletionListener(MediaPlayer::release);
        mediaPlayer.start();
    }

    private void animarTextView(TextView textView) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.bounce_animation);
        textView.startAnimation(animation);
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}