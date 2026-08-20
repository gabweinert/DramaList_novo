package service;

import dao.AvaliacaoDAO;
import java.sql.SQLException;
import java.util.List;
import model.Avaliacao;
import repository.AvaliacaoRepository;

public class AvaliacaoService {

    private final AvaliacaoRepository repository;

    public AvaliacaoService() {
        this.repository = new AvaliacaoDAO();
    }

    public AvaliacaoService(AvaliacaoRepository repository) {
        this.repository = repository;
    }

    public void cadastrar(Avaliacao avaliacao)
            throws SQLException {

        validarAvaliacao(avaliacao);

        repository.cadastrar(avaliacao);
    }

    public List<Avaliacao> listar()
            throws SQLException {

        return repository.listar();
    }

    public Avaliacao buscarPorId(int idAvaliacao)
            throws SQLException {

        validarId(idAvaliacao);

        return repository.buscarPorId(idAvaliacao);
    }

    public List<Avaliacao> buscarPorDrama(int idDrama)
            throws SQLException {

        validarIdDrama(idDrama);

        return repository.buscarPorDrama(idDrama);
    }

    public List<Avaliacao> buscarPorUsuario(int idUsuario)
            throws SQLException {

        validarIdUsuario(idUsuario);

        return repository.buscarPorUsuario(idUsuario);
    }

    public void atualizar(Avaliacao avaliacao)
            throws SQLException {

        validarAvaliacao(avaliacao);

        if (avaliacao.getIdAvaliacao() <= 0) {
            throw new IllegalArgumentException(
                    "O ID da avaliação deve ser informado."
            );
        }

        repository.atualizar(avaliacao);
    }

    public void excluir(int idAvaliacao)
            throws SQLException {

        validarId(idAvaliacao);

        repository.excluir(idAvaliacao);
    }

    private void validarAvaliacao(Avaliacao avaliacao) {

        if (avaliacao == null) {
            throw new IllegalArgumentException(
                    "A avaliação não pode ser nula."
            );
        }

        if (avaliacao.getIdUsuario() <= 0) {
            throw new IllegalArgumentException(
                    "O usuário deve ser informado."
            );
        }

        if (avaliacao.getIdDrama() <= 0) {
            throw new IllegalArgumentException(
                    "O drama deve ser informado."
            );
        }

        if (avaliacao.getNota() < 0
                || avaliacao.getNota() > 10) {

            throw new IllegalArgumentException(
                    "A nota deve estar entre 0 e 10."
            );
        }

        if (avaliacao.getResenha() == null
                || avaliacao.getResenha().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "A resenha deve ser informada."
            );
        }
    }

    private void validarId(int idAvaliacao) {

        if (idAvaliacao <= 0) {
            throw new IllegalArgumentException(
                    "O ID da avaliação deve ser maior que zero."
            );
        }
    }

    private void validarIdDrama(int idDrama) {

        if (idDrama <= 0) {
            throw new IllegalArgumentException(
                    "O ID do drama deve ser maior que zero."
            );
        }
    }

    private void validarIdUsuario(int idUsuario) {

        if (idUsuario <= 0) {
            throw new IllegalArgumentException(
                    "O ID do usuário deve ser maior que zero."
            );
        }
    }
}