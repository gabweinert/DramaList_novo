package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Conexao {

    private static final String URL =
            "jdbc:mysql://localhost:3306/DramaList"
            + "?useSSL=false&serverTimezone=UTC";

    private static final String USUARIO = "root";
    private static final String SENHA = "admin123";

    private Conexao() {
    }

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}