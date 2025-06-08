package com.example.hubdeaplicativos;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ItensActivity extends AppCompatActivity implements ItemAdapter.OnItemClickListener {

    private String nomeCategoria;
    private GerenciadorDeDados gerenciadorDeDados;
    private ItemAdapter adapter;
    private EditText editTextNovoItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itens);
        gerenciadorDeDados = GerenciadorDeDados.getInstance();
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("NOME_CATEGORIA")) {
            nomeCategoria = intent.getStringExtra("NOME_CATEGORIA");
        } else {
            finish();
            return;
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(String.format(getString(R.string.titulo_itens_da_categoria), nomeCategoria));
        }
        editTextNovoItem = findViewById(R.id.editTextNovoItem);
        ImageButton buttonAddItem = findViewById(R.id.buttonAddItem);
        RecyclerView recyclerViewItens = findViewById(R.id.recyclerViewItens);
        adapter = new ItemAdapter(this);
        recyclerViewItens.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewItens.setAdapter(adapter);
        buttonAddItem.setOnClickListener(v -> adicionarItem());
        atualizarListaItens();
    }

    private void atualizarListaItens() {
        List<String> itens = gerenciadorDeDados.getItens(nomeCategoria);
        if (itens != null) {
            adapter.submitList(new ArrayList<>(itens));
        }
    }

    private void adicionarItem() {
        String nomeItem = editTextNovoItem.getText().toString().trim();
        if (!nomeItem.isEmpty()) {
            gerenciadorDeDados.addItem(nomeCategoria, nomeItem);
            atualizarListaItens();
            editTextNovoItem.setText("");
        }
    }

    @Override
    public void onDeleteClick(String item) {
        gerenciadorDeDados.removeItem(nomeCategoria, item);
        atualizarListaItens();
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