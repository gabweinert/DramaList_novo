package service;

import dao.UsuarioDAO;
import java.sql.SQLException;
import java.util.List;
import model.Usuario;
import repository.UsuarioRepository;

public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService() {
        this.repository = new UsuarioDAO();
    }

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public void cadastrar(Usuario usuario) throws SQLException {

        validarUsuario(usuario);

        repository.cadastrar(usuario);
    }

    public List<Usuario> listar() throws SQLException {

        return repository.listar();
    }

    public Usuario buscarPorId(int idUsuario)
            throws SQLException {

        if (idUsuario <= 0) {
            throw new IllegalArgumentException(
                    "O ID do usuário deve ser maior que zero."
            );
        }

        return repository.buscarPorId(idUsuario);
    }

    public void atualizar(Usuario usuario)
            throws SQLException {

        validarUsuario(usuario);

        if (usuario.getIdUsuario() <= 0) {
            throw new IllegalArgumentException(
                    "O ID do usuário deve ser informado."
            );
        }

        repository.atualizar(usuario);
    }

    public void excluir(int idUsuario)
            throws SQLException {

        if (idUsuario <= 0) {
            throw new IllegalArgumentException(
                    "O ID do usuário deve ser maior que zero."
            );
        }

        repository.excluir(idUsuario);
    }

    private void validarUsuario(Usuario usuario) {

        if (usuario == null) {
            throw new IllegalArgumentException(
                    "O usuário não pode ser nulo."
            );
        }

        if (usuario.getNome() == null
                || usuario.getNome().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "O nome do usuário deve ser informado."
            );
        }

        if (usuario.getEmail() == null
                || usuario.getEmail().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "O e-mail do usuário deve ser informado."
            );
        }

        if (usuario.getSenha() == null
                || usuario.getSenha().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "A senha do usuário deve ser informada."
            );
        }
    }
}