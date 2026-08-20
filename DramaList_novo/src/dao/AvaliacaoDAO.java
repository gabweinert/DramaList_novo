package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Avaliacao;
import repository.AvaliacaoRepository;
import util.Conexao;

public class AvaliacaoDAO implements AvaliacaoRepository {

    @Override
    public void cadastrar(Avaliacao avaliacao)
            throws SQLException {

        String sql = "INSERT INTO Avaliacao "
                + "(idUsuario, idDrama, nota, resenha) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt =
                     conn.prepareStatement(sql)) {

            stmt.setInt(1, avaliacao.getIdUsuario());
            stmt.setInt(2, avaliacao.getIdDrama());
            stmt.setInt(3, avaliacao.getNota());
            stmt.setString(4, avaliacao.getResenha());

            stmt.executeUpdate();
        }
    }

    @Override
    public List<Avaliacao> listar()
            throws SQLException {

        String sql = "SELECT * FROM Avaliacao";

        List<Avaliacao> lista = new ArrayList<>();

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt =
                     conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearAvaliacao(rs));
            }
        }

        return lista;
    }

    @Override
    public Avaliacao buscarPorId(int idAvaliacao)
            throws SQLException {

        String sql = "SELECT * FROM Avaliacao "
                + "WHERE idAvaliacao = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt =
                     conn.prepareStatement(sql)) {

            stmt.setInt(1, idAvaliacao);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return mapearAvaliacao(rs);
                }
            }
        }

        return null;
    }

    @Override
    public List<Avaliacao> buscarPorDrama(int idDrama)
            throws SQLException {

        String sql = "SELECT * FROM Avaliacao "
                + "WHERE idDrama = ?";

        List<Avaliacao> lista = new ArrayList<>();

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt =
                     conn.prepareStatement(sql)) {

            stmt.setInt(1, idDrama);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    lista.add(mapearAvaliacao(rs));
                }
            }
        }

        return lista;
    }

    @Override
    public List<Avaliacao> buscarPorUsuario(int idUsuario)
            throws SQLException {

        String sql = "SELECT * FROM Avaliacao "
                + "WHERE idUsuario = ?";

        List<Avaliacao> lista = new ArrayList<>();

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt =
                     conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    lista.add(mapearAvaliacao(rs));
                }
            }
        }

        return lista;
    }

    @Override
    public void atualizar(Avaliacao avaliacao)
            throws SQLException {

        String sql = "UPDATE Avaliacao SET "
                + "idUsuario = ?, "
                + "idDrama = ?, "
                + "nota = ?, "
                + "resenha = ? "
                + "WHERE idAvaliacao = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt =
                     conn.prepareStatement(sql)) {

            stmt.setInt(1, avaliacao.getIdUsuario());
            stmt.setInt(2, avaliacao.getIdDrama());
            stmt.setInt(3, avaliacao.getNota());
            stmt.setString(4, avaliacao.getResenha());
            stmt.setInt(5, avaliacao.getIdAvaliacao());

            stmt.executeUpdate();
        }
    }

    @Override
    public void excluir(int idAvaliacao)
            throws SQLException {

        String sql = "DELETE FROM Avaliacao "
                + "WHERE idAvaliacao = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt =
                     conn.prepareStatement(sql)) {

            stmt.setInt(1, idAvaliacao);

            stmt.executeUpdate();
        }
    }

    private Avaliacao mapearAvaliacao(ResultSet rs)
            throws SQLException {

        Avaliacao avaliacao = new Avaliacao();

        avaliacao.setIdAvaliacao(
                rs.getInt("idAvaliacao")
        );

        avaliacao.setIdUsuario(
                rs.getInt("idUsuario")
        );

        avaliacao.setIdDrama(
                rs.getInt("idDrama")
        );

        avaliacao.setNota(
                rs.getInt("nota")
        );

        avaliacao.setResenha(
                rs.getString("resenha")
        );

        return avaliacao;
    }
}