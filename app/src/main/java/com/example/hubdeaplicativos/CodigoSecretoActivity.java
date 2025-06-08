package com.example.hubdeaplicativos;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CodigoSecretoActivity extends AppCompatActivity {

    private String codigoSecreto;
    private TentativaAdapter adapter;
    private final List<Tentativa> listaDeTentativas = new ArrayList<>();
    private long tempoInicial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codigo_secreto);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(getString(R.string.titulo_codigo_secreto));
        }
        EditText editTextTentativa = findViewById(R.id.editTextTentativa);
        Button buttonTentar = findViewById(R.id.buttonTentar);
        RecyclerView recyclerViewTentativas = findViewById(R.id.recyclerViewTentativas);
        adapter = new TentativaAdapter();
        recyclerViewTentativas.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewTentativas.setAdapter(adapter);
        codigoSecreto = gerarCodigoSecreto();
        tempoInicial = System.currentTimeMillis();
        buttonTentar.setOnClickListener(v -> {
            String palpite = editTextTentativa.getText().toString();
            if (palpite.length() != 4) {
                Toast.makeText(this, getString(R.string.erro_tentativa_invalida), Toast.LENGTH_SHORT).show();
                return;
            }
            List<Feedback> feedbacks = compararCodigo(palpite, codigoSecreto);
            Tentativa novaTentativa = new Tentativa(palpite, feedbacks);
            listaDeTentativas.add(novaTentativa);
            adapter.submitList(new ArrayList<>(listaDeTentativas));
            recyclerViewTentativas.scrollToPosition(listaDeTentativas.size() - 1);
            editTextTentativa.setText("");
            if (verificarVitoria(feedbacks)) {
                long tempoFinal = System.currentTimeMillis();
                long tempoDecorrido = tempoFinal - tempoInicial;
                int numTentativas = listaDeTentativas.size();
                Intent intent = new Intent(CodigoSecretoActivity.this, SucessoCodigoActivity.class);
                intent.putExtra("NUM_TENTATIVAS", numTentativas);
                intent.putExtra("TEMPO_DECORRIDO", tempoDecorrido);
                startActivity(intent);
                finish(); // Fecha a tela do jogo
            }
        });
    }

    private String gerarCodigoSecreto() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            sb.append(random.nextInt(10)); // Gera dígitos de 0 a 9
        }
        return sb.toString();
    }

    private List<Feedback> compararCodigo(String palpite, String codigoSecreto) {
        List<Feedback> feedbacks = new ArrayList<>(Collections.nCopies(4, Feedback.ERRADO));
        boolean[] codigoUsado = new boolean[4];
        boolean[] palpiteUsado = new boolean[4];

        for (int i = 0; i < 4; i++) {
            if (palpite.charAt(i) == codigoSecreto.charAt(i)) {
                feedbacks.set(i, Feedback.CERTO);
                codigoUsado[i] = true;
                palpiteUsado[i] = true;
            }
        }

        for (int i = 0; i < 4; i++) {
            if (palpiteUsado[i]) continue;

            for (int j = 0; j < 4; j++) {
                if (!codigoUsado[j] && palpite.charAt(i) == codigoSecreto.charAt(j)) {
                    feedbacks.set(i, Feedback.POSICAO_ERRADA);
                    codigoUsado[j] = true;
                    break;
                }
            }
        }
        return feedbacks;
    }

    private boolean verificarVitoria(List<Feedback> feedbacks) {
        for (Feedback f : feedbacks) {
            if (f != Feedback.CERTO) {
                return false;
            }
        }
        return true;
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