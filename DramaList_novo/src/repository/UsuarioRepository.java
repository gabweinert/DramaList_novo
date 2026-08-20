package repository;

import java.sql.SQLException;
import java.util.List;
import model.Usuario;

public interface UsuarioRepository {

    void cadastrar(Usuario usuario) throws SQLException;

    List<Usuario> listar() throws SQLException;

    Usuario buscarPorId(int idUsuario) throws SQLException;

    void atualizar(Usuario usuario) throws SQLException;

    void excluir(int idUsuario) throws SQLException;
}