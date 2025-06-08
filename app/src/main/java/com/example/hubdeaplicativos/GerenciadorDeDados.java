package com.example.hubdeaplicativos;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GerenciadorDeDados {
    private static GerenciadorDeDados instance;
    private final Map<String, List<String>> dados;

    private GerenciadorDeDados() {
        dados = new LinkedHashMap<>();
    }

    public static synchronized GerenciadorDeDados getInstance() {
        if (instance == null) {
            instance = new GerenciadorDeDados();
        }
        return instance;
    }

    public List<String> getCategorias() {
        return new ArrayList<>(dados.keySet());
    }

    public List<String> getItens(String categoria) {
        return dados.get(categoria);
    }

    public void addCategoria(String categoria) {
        if (!dados.containsKey(categoria)) {
            dados.put(categoria, new ArrayList<>());
        }
    }

    public void removeCategoria(String categoria) {
        dados.remove(categoria);
    }

    public void addItem(String categoria, String item) {
        if (dados.containsKey(categoria)) {
            List<String> itens = dados.get(categoria);
            if (itens != null) {
                itens.add(item);
            }
        }
    }

    public void removeItem(String categoria, String item) {
        if (dados.containsKey(categoria)) {
            List<String> itens = dados.get(categoria);
            if (itens != null) {
                itens.remove(item);
            }
        }
    }

    public List<String> getTodosOsItens() {
        List<String> todos = new ArrayList<>();
        for (List<String> listaDeItens : dados.values()) {
            todos.addAll(listaDeItens);
        }
        return todos;
    }
}
