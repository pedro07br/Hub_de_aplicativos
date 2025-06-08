package com.example.hubdeaplicativos;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class RPSActivity extends AppCompatActivity {

    private static final int PEDRA = 0;
    private static final int PAPEL = 1;
    private static final int TESOURA = 2;

    private int placarJogador = 0;
    private int placarIA = 0;

    private ImageView imageViewJogador;
    private ImageView imageViewIA;
    private TextView textViewPlacar;
    private TextView textViewResultadoRound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rpsactivity);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(getString(R.string.titulo_rps));
        }

        imageViewJogador = findViewById(R.id.imageViewJogador);
        imageViewIA = findViewById(R.id.imageViewIA);
        textViewPlacar = findViewById(R.id.textViewPlacar);
        textViewResultadoRound = findViewById(R.id.textViewResultadoRound);
        ImageButton imageButtonPedra = findViewById(R.id.imageButtonPedra);
        ImageButton imageButtonPapel = findViewById(R.id.imageButtonPapel);
        ImageButton imageButtonTesoura = findViewById(R.id.imageButtonTesoura);

        imageButtonPedra.setOnClickListener(v -> jogar(PEDRA));
        imageButtonPapel.setOnClickListener(v -> jogar(PAPEL));
        imageButtonTesoura.setOnClickListener(v -> jogar(TESOURA));
    }

    private void jogar(int escolhaJogador) {
        imageViewJogador.setImageResource(getImagemResource(escolhaJogador));
        int escolhaIA = new Random().nextInt(3); // Gera 0, 1 ou 2
        imageViewIA.setImageResource(getImagemResource(escolhaIA));
        if (escolhaJogador == escolhaIA) {
            mostrarResultado(getString(R.string.resultado_empate));
        } else if ((escolhaJogador == PEDRA && escolhaIA == TESOURA) ||
                (escolhaJogador == PAPEL && escolhaIA == PEDRA) ||
                (escolhaJogador == TESOURA && escolhaIA == PAPEL)) {
            placarJogador++;
            mostrarResultado(getString(R.string.resultado_vitoria));
        } else {
            placarIA++;
            mostrarResultado(getString(R.string.resultado_derrota));
        }

        atualizarPlacar();

        if (placarJogador == 2 || placarIA == 2) {
            Intent intent = new Intent(RPSActivity.this, ResultadoRPSActivity.class);
            intent.putExtra("PLACAR_JOGADOR", placarJogador);
            intent.putExtra("PLACAR_IA", placarIA);
            startActivity(intent);
            finish();
        }
    }

    private void mostrarResultado(String resultado) {
        textViewResultadoRound.setText(resultado);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.bounce_animation);
        textViewResultadoRound.startAnimation(animation);
    }

    private void atualizarPlacar() {
        textViewPlacar.setText(String.format(getString(R.string.formato_placar), placarJogador, placarIA));
    }

    private int getImagemResource(int escolha) {
        switch (escolha) {
            case PAPEL:
                return R.drawable.papel;
            case TESOURA:
                return R.drawable.tesoura;
            case PEDRA:
            default:
                return R.drawable.pedra;
        }
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