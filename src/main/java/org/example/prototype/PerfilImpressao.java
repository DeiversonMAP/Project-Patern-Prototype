package org.example.prototype;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PerfilImpressao implements Prototipo<PerfilImpressao> {

    private String nome;
    private int temperaturaBico;
    private int temperaturaMesa;
    private int velocidadeMmPorSegundo;
    private int alturaCamadaMicrons;
    private final List<String> observacoes = new ArrayList<>();

    public PerfilImpressao(String nome, int temperaturaBico, int temperaturaMesa,
                           int velocidadeMmPorSegundo, int alturaCamadaMicrons) {
        this.nome = nome;
        this.temperaturaBico = temperaturaBico;
        this.temperaturaMesa = temperaturaMesa;
        this.velocidadeMmPorSegundo = velocidadeMmPorSegundo;
        this.alturaCamadaMicrons = alturaCamadaMicrons;
    }

    private PerfilImpressao(PerfilImpressao original) {
        this(original.nome, original.temperaturaBico, original.temperaturaMesa,
                original.velocidadeMmPorSegundo, original.alturaCamadaMicrons);
        this.observacoes.addAll(original.observacoes);
    }

    @Override
    public PerfilImpressao clonar() {
        return new PerfilImpressao(this);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTemperaturaBico() {
        return temperaturaBico;
    }

    public void setTemperaturaBico(int temperaturaBico) {
        this.temperaturaBico = temperaturaBico;
    }

    public int getTemperaturaMesa() {
        return temperaturaMesa;
    }

    public void setTemperaturaMesa(int temperaturaMesa) {
        this.temperaturaMesa = temperaturaMesa;
    }

    public int getVelocidadeMmPorSegundo() {
        return velocidadeMmPorSegundo;
    }

    public void setVelocidadeMmPorSegundo(int velocidadeMmPorSegundo) {
        this.velocidadeMmPorSegundo = velocidadeMmPorSegundo;
    }

    public int getAlturaCamadaMicrons() {
        return alturaCamadaMicrons;
    }

    public void setAlturaCamadaMicrons(int alturaCamadaMicrons) {
        this.alturaCamadaMicrons = alturaCamadaMicrons;
    }

    public void adicionarObservacao(String observacao) {
        observacoes.add(observacao);
    }

    public List<String> getObservacoes() {
        return Collections.unmodifiableList(observacoes);
    }

    public String descricao() {
        return nome + ": bico " + temperaturaBico + "C, mesa " + temperaturaMesa + "C, "
                + velocidadeMmPorSegundo + " mm/s, camada " + alturaCamadaMicrons + " um";
    }
}
