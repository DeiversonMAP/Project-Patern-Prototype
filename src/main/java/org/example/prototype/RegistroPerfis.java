package org.example.prototype;

import java.util.HashMap;
import java.util.Map;

public class RegistroPerfis {

    private final Map<String, PerfilImpressao> modelos = new HashMap<>();

    public void registrar(String chave, PerfilImpressao modelo) {
        modelos.put(chave, modelo.clonar());
    }

    public PerfilImpressao criar(String chave) {
        PerfilImpressao modelo = modelos.get(chave);
        if (modelo == null) {
            throw new IllegalArgumentException("Perfil nao registrado: " + chave);
        }
        return modelo.clonar();
    }

    public int quantidade() {
        return modelos.size();
    }
}
