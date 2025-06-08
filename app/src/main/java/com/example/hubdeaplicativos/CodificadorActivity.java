package com.example.hubdeaplicativos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class CodificadorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codificador);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(getString(R.string.titulo_codificador));
        }

        EditText editTextMensagem = findViewById(R.id.editTextMensagem);
        EditText editTextChave = findViewById(R.id.editTextChave);
        Button buttonCodificar = findViewById(R.id.buttonCodificar);

        buttonCodificar.setOnClickListener(v -> {
            String mensagem = editTextMensagem.getText().toString();
            String chaveStr = editTextChave.getText().toString();
            if (mensagem.trim().isEmpty()) {
                Toast.makeText(this, getString(R.string.erro_mensagem_vazia), Toast.LENGTH_SHORT).show();
                return;
            }

            if (chaveStr.trim().isEmpty()) {
                Toast.makeText(this, getString(R.string.erro_chave_invalida), Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                int chave = Integer.parseInt(chaveStr);
                Intent intent = new Intent(CodificadorActivity.this, ResultadoCodificadorActivity.class);
                intent.putExtra("MENSAGEM_ORIGINAL", mensagem);
                intent.putExtra("CHAVE_CIFRA", chave);
                startActivity(intent);

            } catch (NumberFormatException e) {
                Toast.makeText(this, getString(R.string.erro_chave_invalida), Toast.LENGTH_SHORT).show();
            }
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