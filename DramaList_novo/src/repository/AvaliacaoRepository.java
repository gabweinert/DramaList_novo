package repository;

import java.sql.SQLException;
import java.util.List;
import model.Avaliacao;

public interface AvaliacaoRepository {

    void cadastrar(Avaliacao avaliacao) throws SQLException;

    List<Avaliacao> listar() throws SQLException;

    Avaliacao buscarPorId(int idAvaliacao) throws SQLException;

    List<Avaliacao> buscarPorDrama(int idDrama) throws SQLException;

    List<Avaliacao> buscarPorUsuario(int idUsuario) throws SQLException;

    void atualizar(Avaliacao avaliacao) throws SQLException;

    void excluir(int idAvaliacao) throws SQLException;
}