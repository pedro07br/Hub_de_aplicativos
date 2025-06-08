package com.example.hubdeaplicativos;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomizadorActivity extends AppCompatActivity implements CategoriaAdapter.OnCategoriaClickListener {

    private GerenciadorDeDados gerenciadorDeDados;
    private CategoriaAdapter adapter;
    private EditText editTextNovaCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_randomizador);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(getString(R.string.titulo_randomizador));
        }

        gerenciadorDeDados = GerenciadorDeDados.getInstance();

        editTextNovaCategoria = findViewById(R.id.editTextNovaCategoria);
        Button buttonAddCategoria = findViewById(R.id.buttonAddCategoria);
        Button buttonSortear = findViewById(R.id.buttonSortear);
        RecyclerView recyclerViewCategorias = findViewById(R.id.recyclerViewCategorias);

        adapter = new CategoriaAdapter(this);
        recyclerViewCategorias.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCategorias.setAdapter(adapter);

        buttonAddCategoria.setOnClickListener(v -> adicionarCategoria());

        buttonSortear.setOnClickListener(v -> sortearDeTodos());
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizarListaCategorias();
    }

    private void atualizarListaCategorias() {
        List<String> categorias = gerenciadorDeDados.getCategorias();
        adapter.submitList(new ArrayList<>(categorias));
    }

    private void adicionarCategoria() {
        String nomeCategoria = editTextNovaCategoria.getText().toString().trim();
        if (!nomeCategoria.isEmpty()) {
            gerenciadorDeDados.addCategoria(nomeCategoria);
            atualizarListaCategorias();
            editTextNovaCategoria.setText("");
        }
    }

    private void sortearDeTodos() {
        List<String> todosOsItens = gerenciadorDeDados.getTodosOsItens();
        if (todosOsItens.isEmpty()) {
            Toast.makeText(this, getString(R.string.nenhum_item), Toast.LENGTH_SHORT).show();
            return;
        }


        String itemSorteado = todosOsItens.get(new Random().nextInt(todosOsItens.size()));

        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.roleta);
        mediaPlayer.setOnCompletionListener(MediaPlayer::release);
        mediaPlayer.start();

        new AlertDialog.Builder(this)
                .setTitle(R.string.resultado_sorteio)
                .setMessage(itemSorteado)
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .show();
    }

    @Override
    public void onItemClick(String categoria) {
        Intent intent = new Intent(this, ItensActivity.class);
        intent.putExtra("NOME_CATEGORIA", categoria);
        startActivity(intent);
        Toast.makeText(this, "Abrir itens de: " + categoria, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteClick(String categoria) {
        new AlertDialog.Builder(this)
                .setTitle("Confirmar Exclusão")
                .setMessage("Tem certeza que deseja deletar a categoria '" + categoria + "' e todos os seus itens?")
                .setPositiveButton("Sim", (dialog, which) -> {
                    gerenciadorDeDados.removeCategoria(categoria);
                    atualizarListaCategorias();
                })
                .setNegativeButton("Não", null)
                .show();
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