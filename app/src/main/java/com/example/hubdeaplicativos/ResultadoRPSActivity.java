package com.example.hubdeaplicativos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class ResultadoRPSActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_rpsactivity);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(getString(R.string.titulo_resultado_rps));
        }

        TextView textViewResultadoFinal = findViewById(R.id.textViewResultadoFinal);
        TextView textViewPlacarFinal = findViewById(R.id.textViewPlacarFinal);
        Button buttonJogarNovamente = findViewById(R.id.buttonJogarNovamente);
        Button buttonVoltarMenu = findViewById(R.id.buttonVoltarMenu);

        Intent intent = getIntent();
        int placarJogador = intent.getIntExtra("PLACAR_JOGADOR", 0);
        int placarIA = intent.getIntExtra("PLACAR_IA", 0);

        String placarTexto = "Placar Final: " + placarJogador + " x " + placarIA;
        textViewPlacarFinal.setText(placarTexto);

        if (placarJogador > placarIA) {
            textViewResultadoFinal.setText(getString(R.string.mensagem_vitoria_final));
        } else {
            textViewResultadoFinal.setText(getString(R.string.mensagem_derrota_final));
        }

        buttonJogarNovamente.setOnClickListener(v -> {
            Intent novoJogoIntent = new Intent(ResultadoRPSActivity.this, RPSActivity.class);
            startActivity(novoJogoIntent);
            finish();
        });

        buttonVoltarMenu.setOnClickListener(v -> finish());
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