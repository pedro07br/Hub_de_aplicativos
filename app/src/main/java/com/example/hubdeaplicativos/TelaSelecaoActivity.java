package com.example.hubdeaplicativos;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class TelaSelecaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_selecao);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(getString(R.string.titulo_tela_selecao));
        }

        CardView cardCodificador = findViewById(R.id.cardCodificador);
        cardCodificador.setOnClickListener(v -> {
            Intent intent = new Intent(TelaSelecaoActivity.this, CodificadorActivity.class);
            startActivity(intent);
        });

        CardView cardPPT = findViewById(R.id.cardPPT);
        cardPPT.setOnClickListener(v -> {
            Intent intent = new Intent(TelaSelecaoActivity.this, RPSActivity.class);
            startActivity(intent);
        });

        CardView cardCodigoSecreto = findViewById(R.id.cardCodigoSecreto);
        cardCodigoSecreto.setOnClickListener(v -> {
            Intent intent = new Intent(TelaSelecaoActivity.this, CodigoSecretoActivity.class);
            startActivity(intent);
        });

        CardView cardRandomizador = findViewById(R.id.cardRandomizador);
        cardRandomizador.setOnClickListener(v -> {
            Intent intent = new Intent(TelaSelecaoActivity.this, RandomizadorActivity.class);
            startActivity(intent);
        });

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