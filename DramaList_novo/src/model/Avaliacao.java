package model;

public class Avaliacao {

    private int idAvaliacao;
    private int nota;
    private String resenha;
    private int idUsuario;
    private int idDrama;

    public Avaliacao() {
    }

    public Avaliacao(int idUsuario, int idDrama, int nota, String resenha) {
        this.idUsuario = idUsuario;
        this.idDrama = idDrama;
        this.nota = nota;
        this.resenha = resenha;
    }

    public int getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(int idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getResenha() {
        return resenha;
    }

    public void setResenha(String resenha) {
        this.resenha = resenha;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdDrama() {
        return idDrama;
    }

    public void setIdDrama(int idDrama) {
        this.idDrama = idDrama;
    }
}