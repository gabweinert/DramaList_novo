package dao;

import model.Drama;
import repository.DramaRepository;
import util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DramaDAO implements DramaRepository {

    @Override
    public void cadastrar(Drama drama) throws SQLException {

        String sql = "INSERT INTO drama "
                + "(titulo, paisOrigem, dataLancamento, genero, "
                + "numeroEpisodios, sinopse) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, drama.getTitulo());
            stmt.setString(2, drama.getPaisOrigem());

            if (drama.getDataLancamento() != null) {
                stmt.setDate(3,
                        java.sql.Date.valueOf(drama.getDataLancamento()));
            } else {
                stmt.setDate(3, null);
            }

            stmt.setString(4, drama.getGenero());
            stmt.setInt(5, drama.getNumeroEpisodios());
            stmt.setString(6, drama.getSinopse());

            stmt.executeUpdate();
        }
    }

    @Override
    public List<Drama> buscarPorTitulo(String titulo) throws SQLException {

        String sql = "SELECT * FROM drama WHERE titulo LIKE ?";

        List<Drama> lista = new ArrayList<>();

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + titulo + "%");

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    lista.add(mapearDrama(rs));
                }
            }
        }

        return lista;
    }

    @Override
    public Drama buscarPorId(int idDrama) throws SQLException {

        String sql = "SELECT * FROM drama WHERE idDrama = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idDrama);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return mapearDrama(rs);
                }
            }
        }

        return null;
    }

    @Override
    public void atualizar(Drama drama) throws SQLException {

        String sql = "UPDATE drama SET "
                + "titulo = ?, "
                + "paisOrigem = ?, "
                + "dataLancamento = ?, "
                + "genero = ?, "
                + "numeroEpisodios = ?, "
                + "sinopse = ? "
                + "WHERE idDrama = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, drama.getTitulo());
            stmt.setString(2, drama.getPaisOrigem());

            if (drama.getDataLancamento() != null) {
                stmt.setDate(3,
                        java.sql.Date.valueOf(drama.getDataLancamento()));
            } else {
                stmt.setDate(3, null);
            }

            stmt.setString(4, drama.getGenero());
            stmt.setInt(5, drama.getNumeroEpisodios());
            stmt.setString(6, drama.getSinopse());
            stmt.setInt(7, drama.getIdDrama());

            stmt.executeUpdate();
        }
    }

    @Override
    public void excluir(int idDrama) throws SQLException {

        String sql = "DELETE FROM drama WHERE idDrama = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idDrama);

            stmt.executeUpdate();
        }
    }

    @Override
    public List<Drama> listarPopulares(int limite) throws SQLException {

        String sql = "SELECT * FROM drama LIMIT ?";

        List<Drama> lista = new ArrayList<>();

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, limite);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    lista.add(mapearDrama(rs));
                }
            }
        }

        return lista;
    }

    @Override
    public List<Drama> listarUltimosLancamentos(int limite)
            throws SQLException {

        String sql = """
                SELECT * FROM drama
                ORDER BY dataLancamento DESC
                LIMIT ?
                """;

        List<Drama> lista = new ArrayList<>();

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, limite);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    lista.add(mapearDrama(rs));
                }
            }
        }

        return lista;
    }

    private Drama mapearDrama(ResultSet rs) throws SQLException {

        Drama drama = new Drama();

        drama.setIdDrama(rs.getInt("idDrama"));
        drama.setTitulo(rs.getString("titulo"));
        drama.setPaisOrigem(rs.getString("paisOrigem"));

        if (rs.getDate("dataLancamento") != null) {
            drama.setDataLancamento(
                    rs.getDate("dataLancamento").toLocalDate()
            );
        }

        drama.setGenero(rs.getString("genero"));
        drama.setNumeroEpisodios(rs.getInt("numeroEpisodios"));
        drama.setSinopse(rs.getString("sinopse"));

        return drama;
    }
}