package model;

import java.time.LocalDate;

public class Drama {

    private int idDrama;
    private String titulo;
    private String paisOrigem;
    private LocalDate dataLancamento;
    private String genero;
    private int numeroEpisodios;
    private String sinopse;
    private String caminhoImagem;

    public Drama() {
    }

    public Drama(String titulo, String paisOrigem, LocalDate dataLancamento,
            String genero, int numeroEpisodios, String sinopse) {
        this.titulo = titulo;
        this.paisOrigem = paisOrigem;
        this.dataLancamento = dataLancamento;
        this.genero = genero;
        this.numeroEpisodios = numeroEpisodios;
        this.sinopse = sinopse;
    }

    public int getIdDrama() {
        return idDrama;
    }

    public void setIdDrama(int idDrama) {
        this.idDrama = idDrama;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPaisOrigem() {
        return paisOrigem;
    }

    public void setPaisOrigem(String paisOrigem) {
        this.paisOrigem = paisOrigem;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getNumeroEpisodios() {
        return numeroEpisodios;
    }

    public void setNumeroEpisodios(int numeroEpisodios) {
        this.numeroEpisodios = numeroEpisodios;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getCaminhoImagem() {
        return caminhoImagem;
    }

    public void setCaminhoImagem(String caminhoImagem) {
        this.caminhoImagem = caminhoImagem;
    }
}