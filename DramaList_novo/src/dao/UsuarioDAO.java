package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Usuario;
import repository.UsuarioRepository;
import util.Conexao;

public class UsuarioDAO implements UsuarioRepository {

    @Override
    public void cadastrar(Usuario usuario) throws SQLException {

        String sql = "INSERT INTO Usuario (nome, email, senha) "
                + "VALUES (?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());

            stmt.executeUpdate();
        }
    }

    @Override
    public List<Usuario> listar() throws SQLException {

        String sql = "SELECT * FROM Usuario";

        List<Usuario> lista = new ArrayList<>();

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearUsuario(rs));
            }
        }

        return lista;
    }

    @Override
    public Usuario buscarPorId(int idUsuario)
            throws SQLException {

        String sql = "SELECT * FROM Usuario "
                + "WHERE idUsuario = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return mapearUsuario(rs);
                }
            }
        }

        return null;
    }

    @Override
    public void atualizar(Usuario usuario)
            throws SQLException {

        String sql = "UPDATE Usuario SET "
                + "nome = ?, "
                + "email = ?, "
                + "senha = ? "
                + "WHERE idUsuario = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setInt(4, usuario.getIdUsuario());

            stmt.executeUpdate();
        }
    }

    @Override
    public void excluir(int idUsuario)
            throws SQLException {

        String sql = "DELETE FROM Usuario "
                + "WHERE idUsuario = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);

            stmt.executeUpdate();
        }
    }

    private Usuario mapearUsuario(ResultSet rs)
            throws SQLException {

        Usuario usuario = new Usuario();

        usuario.setIdUsuario(
                rs.getInt("idUsuario")
        );

        usuario.setNome(
                rs.getString("nome")
        );

        usuario.setEmail(
                rs.getString("email")
        );

        usuario.setSenha(
                rs.getString("senha")
        );

        return usuario;
    }
}