package test;

import java.util.ArrayList;
import java.util.List;
import model.Usuario;
import repository.UsuarioRepository;

public class UsuarioRepositoryTeste implements UsuarioRepository {

    private final List<Usuario> usuarios = new ArrayList<>();

    @Override
    public void cadastrar(Usuario usuario) {

        usuario.setIdUsuario(usuarios.size() + 1);
        usuarios.add(usuario);
    }

    @Override
    public List<Usuario> listar() {

        return new ArrayList<>(usuarios);
    }

    @Override
    public Usuario buscarPorId(int idUsuario) {

        for (Usuario usuario : usuarios) {

            if (usuario.getIdUsuario() == idUsuario) {
                return usuario;
            }
        }

        return null;
    }

    @Override
    public void atualizar(Usuario usuario) {

        for (int i = 0; i < usuarios.size(); i++) {

            if (usuarios.get(i).getIdUsuario()
                    == usuario.getIdUsuario()) {

                usuarios.set(i, usuario);
                return;
            }
        }
    }

    @Override
    public void excluir(int idUsuario) {

        usuarios.removeIf(
                usuario -> usuario.getIdUsuario() == idUsuario
        );
    }
}