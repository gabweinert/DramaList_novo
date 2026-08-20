package repository;

import model.Drama;
import java.sql.SQLException;
import java.util.List;

public interface DramaRepository {

    void cadastrar(Drama drama) throws SQLException;

    List<Drama> buscarPorTitulo(String titulo) throws SQLException;

    Drama buscarPorId(int idDrama) throws SQLException;

    void atualizar(Drama drama) throws SQLException;

    void excluir(int idDrama) throws SQLException;

    List<Drama> listarPopulares(int limite) throws SQLException;

    List<Drama> listarUltimosLancamentos(int limite) throws SQLException;
}