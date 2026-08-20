package test;

import java.util.ArrayList;
import java.util.List;

import model.Avaliacao;
import repository.AvaliacaoRepository;

public class AvaliacaoRepositoryTeste
        implements AvaliacaoRepository {

    private final List<Avaliacao> avaliacoes =
            new ArrayList<>();

    @Override
    public void cadastrar(Avaliacao avaliacao) {

        avaliacao.setIdAvaliacao(
                avaliacoes.size() + 1
        );

        avaliacoes.add(avaliacao);
    }

    @Override
    public List<Avaliacao> listar() {

        return new ArrayList<>(avaliacoes);
    }

    @Override
    public Avaliacao buscarPorId(int idAvaliacao) {

        for (Avaliacao avaliacao : avaliacoes) {

            if (avaliacao.getIdAvaliacao()
                    == idAvaliacao) {

                return avaliacao;
            }
        }

        return null;
    }

    @Override
    public List<Avaliacao> buscarPorDrama(
            int idDrama) {

        List<Avaliacao> resultado =
                new ArrayList<>();

        for (Avaliacao avaliacao : avaliacoes) {

            if (avaliacao.getIdDrama()
                    == idDrama) {

                resultado.add(avaliacao);
            }
        }

        return resultado;
    }

    @Override
    public List<Avaliacao> buscarPorUsuario(
            int idUsuario) {

        List<Avaliacao> resultado =
                new ArrayList<>();

        for (Avaliacao avaliacao : avaliacoes) {

            if (avaliacao.getIdUsuario()
                    == idUsuario) {

                resultado.add(avaliacao);
            }
        }

        return resultado;
    }

    @Override
    public void atualizar(Avaliacao avaliacao) {

        for (int i = 0;
                i < avaliacoes.size();
                i++) {

            if (avaliacoes.get(i)
                    .getIdAvaliacao()
                    == avaliacao.getIdAvaliacao()) {

                avaliacoes.set(i, avaliacao);
                return;
            }
        }
    }

    @Override
    public void excluir(int idAvaliacao) {

        avaliacoes.removeIf(
                avaliacao ->
                        avaliacao.getIdAvaliacao()
                        == idAvaliacao
        );
    }
}