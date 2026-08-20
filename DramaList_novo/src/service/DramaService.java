package service;

import dao.DramaDAO;
import java.sql.SQLException;
import java.util.List;
import model.Drama;
import repository.DramaRepository;

public class DramaService {

    private final DramaRepository repository;

    // Construtor utilizado pela aplicação
    public DramaService() {
        this.repository = new DramaDAO();
    }

    // Construtor utilizado para testes e para injeção de dependência
    public DramaService(DramaRepository repository) {
        this.repository = repository;
    }

    public void cadastrar(Drama drama) throws SQLException {

        validarDrama(drama);

        repository.cadastrar(drama);
    }

    public List<Drama> buscarPorTitulo(String titulo)
            throws SQLException {

        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "O título deve ser informado."
            );
        }

        return repository.buscarPorTitulo(titulo);
    }

    public Drama buscarPorId(int idDrama) throws SQLException {

        if (idDrama <= 0) {
            throw new IllegalArgumentException(
                    "O ID do drama deve ser maior que zero."
            );
        }

        return repository.buscarPorId(idDrama);
    }

    public void atualizar(Drama drama) throws SQLException {

        validarDrama(drama);

        if (drama.getIdDrama() <= 0) {
            throw new IllegalArgumentException(
                    "O ID do drama deve ser informado."
            );
        }

        repository.atualizar(drama);
    }

    public void excluir(int idDrama) throws SQLException {

        if (idDrama <= 0) {
            throw new IllegalArgumentException(
                    "O ID do drama deve ser maior que zero."
            );
        }

        repository.excluir(idDrama);
    }

    public List<Drama> listarPopulares(int limite)
            throws SQLException {

        validarLimite(limite);

        return repository.listarPopulares(limite);
    }

    public List<Drama> listarUltimosLancamentos(int limite)
            throws SQLException {

        validarLimite(limite);

        return repository.listarUltimosLancamentos(limite);
    }

    private void validarLimite(int limite) {

        if (limite <= 0) {
            throw new IllegalArgumentException(
                    "O limite deve ser maior que zero."
            );
        }
    }

    private void validarDrama(Drama drama) {

        if (drama == null) {
            throw new IllegalArgumentException(
                    "O drama não pode ser nulo."
            );
        }

        if (drama.getTitulo() == null
                || drama.getTitulo().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "O título do drama deve ser informado."
            );
        }

        if (drama.getNumeroEpisodios() <= 0) {
            throw new IllegalArgumentException(
                    "O número de episódios deve ser maior que zero."
            );
        }
    }
}