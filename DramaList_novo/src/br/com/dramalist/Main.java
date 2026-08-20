package br.com.dramalist;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Avaliacao;
import model.Drama;
import model.Usuario;

import repository.AvaliacaoRepository;
import repository.DramaRepository;
import repository.UsuarioRepository;

import service.AvaliacaoService;
import service.DramaService;
import service.UsuarioService;

import test.AvaliacaoRepositoryTeste;
import test.UsuarioRepositoryTeste;

public class Main {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("       TESTES DO DRAMALIST");
        System.out.println("=================================");

        testarDrama();
        testarUsuario();
        testarAvaliacao();

        System.out.println("\n=================================");
        System.out.println("       TESTES FINALIZADOS");
        System.out.println("=================================");
    }

    private static void testarDrama() {

        System.out.println("\n--- TESTES DE DRAMA ---");

        DramaRepository repository
                = new DramaRepositoryTeste();

        DramaService service
                = new DramaService(repository);

        testarCadastroDrama(service);
        testarBuscaPorTitulo(service);
        testarBuscaPorId(service);
        testarAtualizacaoDrama(service);
        testarUltimosLancamentos(service);
        testarExclusaoDrama(service);
        testarValidacaoTitulo(service);
        testarLimiteDrama(service);
    }

    private static void testarCadastroDrama(
            DramaService service) {

        try {

            Drama drama = new Drama(
                    "Crash Landing on You",
                    "Coreia do Sul",
                    LocalDate.of(2019, 12, 14),
                    "Romance",
                    16,
                    "Uma mulher sul-coreana acaba na Coreia do Norte."
            );

            service.cadastrar(drama);

            System.out.println(
                    "[OK] Cadastro de drama"
            );

        } catch (Exception e) {

            System.out.println(
                    "[ERRO] Cadastro de drama: "
                    + e.getMessage()
            );
        }
    }

    private static void testarBuscaPorTitulo(
            DramaService service) {

        try {

            List<Drama> resultados
                    = service.buscarPorTitulo("Crash");

            if (!resultados.isEmpty()) {

                System.out.println(
                        "[OK] Busca de drama por título"
                );

            } else {

                System.out.println(
                        "[ERRO] Busca de drama por título: "
                        + "nenhum resultado encontrado."
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "[ERRO] Busca por título: "
                    + e.getMessage()
            );
        }
    }

    private static void testarBuscaPorId(
            DramaService service) {

        try {

            Drama drama
                    = service.buscarPorId(1);

            if (drama != null) {

                System.out.println(
                        "[OK] Busca de drama por ID"
                );

            } else {

                System.out.println(
                        "[ERRO] Busca de drama por ID: "
                        + "drama não encontrado."
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "[ERRO] Busca por ID: "
                    + e.getMessage()
            );
        }
    }

    private static void testarAtualizacaoDrama(
            DramaService service) {

        try {

            Drama drama
                    = service.buscarPorId(1);

            if (drama == null) {

                System.out.println(
                        "[ERRO] Atualização: "
                        + "drama não encontrado."
                );

                return;
            }

            drama.setTitulo(
                    "Crash Landing on You - Atualizado"
            );

            drama.setNumeroEpisodios(16);

            service.atualizar(drama);

            Drama atualizado
                    = service.buscarPorId(1);

            if (atualizado != null
                    && atualizado.getTitulo()
                            .equals(
                                    "Crash Landing on You - Atualizado"
                            )) {

                System.out.println(
                        "[OK] Atualização de drama"
                );

            } else {

                System.out.println(
                        "[ERRO] Atualização de drama"
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "[ERRO] Atualização: "
                    + e.getMessage()
            );
        }
    }

    private static void testarUltimosLancamentos(
            DramaService service) {

        try {

            List<Drama> resultados
                    = service.listarUltimosLancamentos(5);

            if (resultados != null) {

                System.out.println(
                        "[OK] Listagem de últimos lançamentos"
                );

            } else {

                System.out.println(
                        "[ERRO] Listagem de últimos lançamentos"
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "[ERRO] Últimos lançamentos: "
                    + e.getMessage()
            );
        }
    }

    private static void testarExclusaoDrama(
            DramaService service) {

        try {

            service.excluir(1);

            Drama drama
                    = service.buscarPorId(1);

            if (drama == null) {

                System.out.println(
                        "[OK] Exclusão de drama"
                );

            } else {

                System.out.println(
                        "[ERRO] Exclusão de drama"
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "[ERRO] Exclusão: "
                    + e.getMessage()
            );
        }
    }

    private static void testarValidacaoTitulo(
            DramaService service) {

        try {

            Drama dramaInvalido
                    = new Drama(
                            "",
                            "Coreia do Sul",
                            LocalDate.now(),
                            "Romance",
                            16,
                            "Drama inválido para teste."
                    );

            service.cadastrar(dramaInvalido);

            System.out.println(
                    "[ERRO] Validação de título "
                    + "não funcionou."
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "[OK] Validação de título"
            );

        } catch (Exception e) {

            System.out.println(
                    "[ERRO] Validação de título: "
                    + e.getMessage()
            );
        }
    }

    private static void testarLimiteDrama(
            DramaService service) {

        try {

            service.listarPopulares(0);

            System.out.println(
                    "[ERRO] Validação de limite "
                    + "não funcionou."
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "[OK] Validação de limite"
            );

        } catch (Exception e) {

            System.out.println(
                    "[ERRO] Validação de limite: "
                    + e.getMessage()
            );
        }
    }

    private static void testarUsuario() {

        System.out.println("\n--- TESTES DE USUARIO ---");

        UsuarioRepository repository
                = new UsuarioRepositoryTeste();

        UsuarioService service
                = new UsuarioService(repository);

        testarCadastroUsuario(service);
        testarListagemUsuarios(service);
        testarBuscaUsuarioPorId(service);
        testarAtualizacaoUsuario(service);
        testarExclusaoUsuario(service);
        testarValidacaoNome(service);
        testarValidacaoEmail(service);
        testarValidacaoSenha(service);
    }

    private static void testarCadastroUsuario(
            UsuarioService service) {

        try {

            Usuario usuario
                    = new Usuario(
                            "Ana Souza",
                            "ana.souza@email.com",
                            "senha123"
                    );

            service.cadastrar(usuario);

            System.out.println(
                    "[OK] Cadastro de usuário"
            );

        } catch (Exception e) {

            System.out.println(
                    "[ERRO] Cadastro de usuário: "
                    + e.getMessage()
            );
        }
    }

    private static void testarListagemUsuarios(
            UsuarioService service) {

        try {

            List<Usuario> usuarios
                    = service.listar();

            if (!usuarios.isEmpty()) {

                System.out.println(
                        "[OK] Listagem de usuários"
                );

            } else {

                System.out.println(
                        "[ERRO] Listagem de usuários: "
                        + "nenhum usuário encontrado."
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "[ERRO] Listagem de usuários: "
                    + e.getMessage()
            );
        }
    }

    private static void testarBuscaUsuarioPorId(
            UsuarioService service) {

        try {

            Usuario usuario
                    = service.buscarPorId(1);

            if (usuario != null) {

                System.out.println(
                        "[OK] Busca de usuário por ID"
                );

            } else {

                System.out.println(
                        "[ERRO] Busca de usuário por ID: "
                        + "usuário não encontrado."
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "[ERRO] Busca de usuário por ID: "
                    + e.getMessage()
            );
        }
    }

    private static void testarAtualizacaoUsuario(
            UsuarioService service) {

        try {

            Usuario usuario
                    = service.buscarPorId(1);

            if (usuario == null) {

                System.out.println(
                        "[ERRO] Atualização de usuário: "
                        + "usuário não encontrado."
                );

                return;
            }

            usuario.setNome(
                    "Ana Souza Atualizada"
            );

            service.atualizar(usuario);

            Usuario atualizado
                    = service.buscarPorId(1);

            if (atualizado != null
                    && atualizado.getNome()
                            .equals(
                                    "Ana Souza Atualizada"
                            )) {

                System.out.println(
                        "[OK] Atualização de usuário"
                );

            } else {

                System.out.println(
                        "[ERRO] Atualização de usuário"
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "[ERRO] Atualização de usuário: "
                    + e.getMessage()
            );
        }
    }

    private static void testarExclusaoUsuario(
            UsuarioService service) {

        try {

            service.excluir(1);

            Usuario usuario
                    = service.buscarPorId(1);

            if (usuario == null) {

                System.out.println(
                        "[OK] Exclusão de usuário"
                );

            } else {

                System.out.println(
                        "[ERRO] Exclusão de usuário"
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "[ERRO] Exclusão de usuário: "
                    + e.getMessage()
            );
        }
    }

    private static void testarValidacaoNome(
            UsuarioService service) {

        try {

            Usuario usuario
                    = new Usuario(
                            "",
                            "teste@email.com",
                            "senha123"
                    );

            service.cadastrar(usuario);

            System.out.println(
                    "[ERRO] Validação de nome "
                    + "não funcionou."
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "[OK] Validação de nome"
            );

        } catch (Exception e) {

            System.out.println(
                    "[ERRO] Validação de nome: "
                    + e.getMessage()
            );
        }
    }

    private static void testarValidacaoEmail(
            UsuarioService service) {

        try {

            Usuario usuario
                    = new Usuario(
                            "Usuário Teste",
                            "",
                            "senha123"
                    );

            service.cadastrar(usuario);

            System.out.println(
                    "[ERRO] Validação de e-mail "
                    + "não funcionou."
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "[OK] Validação de e-mail"
            );

        } catch (Exception e) {

            System.out.println(
                    "[ERRO] Validação de e-mail: "
                    + e.getMessage()
            );
        }
    }

    private static void testarValidacaoSenha(
            UsuarioService service) {

        try {

            Usuario usuario
                    = new Usuario(
                            "Usuário Teste",
                            "teste@email.com",
                            ""
                    );

            service.cadastrar(usuario);

            System.out.println(
                    "[ERRO] Validação de senha "
                    + "não funcionou."
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "[OK] Validação de senha"
            );

        } catch (Exception e) {

            System.out.println(
                    "[ERRO] Validação de senha: "
                    + e.getMessage()
            );
        }
    }

    private static void testarAvaliacao() {

        System.out.println("\n--- TESTES DE AVALIACAO ---");

        AvaliacaoRepository repository
                = new AvaliacaoRepositoryTeste();

        AvaliacaoService service
                = new AvaliacaoService(repository);

        testarCadastroAvaliacao(service);
        testarListagemAvaliacoes(service);
        testarBuscaAvaliacaoPorId(service);
        testarBuscaAvaliacaoPorDrama(service);
        testarBuscaAvaliacaoPorUsuario(service);
        testarAtualizacaoAvaliacao(service);
        testarExclusaoAvaliacao(service);
        testarValidacaoNota(service);
    }

    private static void testarCadastroAvaliacao(
            AvaliacaoService service) {

        try {

            Avaliacao avaliacao
                    = new Avaliacao(
                            1,
                            1,
                            9,
                            "Excelente drama!"
                    );

            service.cadastrar(avaliacao);

            System.out.println(
                    "[OK] Cadastro de avaliação"
            );

        } catch (Exception e) {

            System.out.println(
                    "[ERRO] Cadastro de avaliação: "
                    + e.getMessage()
            );
        }
    }

    private static void testarListagemAvaliacoes(
            AvaliacaoService service) {

        try {

            List<Avaliacao> avaliacoes
                    = service.listar();

            if (!avaliacoes.isEmpty()) {

                System.out.println(
                        "[OK] Listagem de avaliações"
                );

            } else {

                System.out.println(
                        "[ERRO] Listagem de avaliações: "
                        + "nenhuma avaliação encontrada."
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "[ERRO] Listagem de avaliações: "
                    + e.getMessage()
            );
        }
    }

    private static void testarBuscaAvaliacaoPorId(
            AvaliacaoService service) {

        try {

            Avaliacao avaliacao
                    = service.buscarPorId(1);

            if (avaliacao != null) {

                System.out.println(
                        "[OK] Busca de avaliação por ID"
                );

            } else {

                System.out.println(
                        "[ERRO] Busca de avaliação por ID"
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "[ERRO] Busca por ID: "
                    + e.getMessage()
            );
        }
    }

    private static void testarBuscaAvaliacaoPorDrama(
            AvaliacaoService service) {

        try {

            List<Avaliacao> avaliacoes
                    = service.buscarPorDrama(1);

            if (!avaliacoes.isEmpty()) {

                System.out.println(
                        "[OK] Busca de avaliações por drama"
                );

            } else {

                System.out.println(
                        "[ERRO] Busca de avaliações por drama"
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "[ERRO] Busca por drama: "
                    + e.getMessage()
            );
        }
    }

    private static void testarBuscaAvaliacaoPorUsuario(
            AvaliacaoService service) {

        try {

            List<Avaliacao> avaliacoes
                    = service.buscarPorUsuario(1);

            if (!avaliacoes.isEmpty()) {

                System.out.println(
                        "[OK] Busca de avaliações por usuário"
                );

            } else {

                System.out.println(
                        "[ERRO] Busca de avaliações por usuário"
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "[ERRO] Busca por usuário: "
                    + e.getMessage()
            );
        }
    }

    private static void testarAtualizacaoAvaliacao(
            AvaliacaoService service) {

        try {

            Avaliacao avaliacao
                    = service.buscarPorId(1);

            if (avaliacao == null) {

                System.out.println(
                        "[ERRO] Atualização de avaliação: "
                        + "avaliação não encontrada."
                );

                return;
            }

            avaliacao.setNota(10);

            avaliacao.setResenha(
                    "Excelente drama! Atualizado."
            );

            service.atualizar(avaliacao);

            Avaliacao atualizada
                    = service.buscarPorId(1);

            if (atualizada != null
                    && atualizada.getNota() == 10
                    && atualizada.getResenha()
                            .equals(
                                    "Excelente drama! Atualizado."
                            )) {

                System.out.println(
                        "[OK] Atualização de avaliação"
                );

            } else {

                System.out.println(
                        "[ERRO] Atualização de avaliação"
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "[ERRO] Atualização de avaliação: "
                    + e.getMessage()
            );
        }
    }

    private static void testarExclusaoAvaliacao(
            AvaliacaoService service) {

        try {

            service.excluir(1);

            Avaliacao avaliacao
                    = service.buscarPorId(1);

            if (avaliacao == null) {

                System.out.println(
                        "[OK] Exclusão de avaliação"
                );

            } else {

                System.out.println(
                        "[ERRO] Exclusão de avaliação"
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "[ERRO] Exclusão de avaliação: "
                    + e.getMessage()
            );
        }
    }

    private static void testarValidacaoNota(
            AvaliacaoService service) {

        try {

            Avaliacao avaliacaoInvalida
                    = new Avaliacao(
                            1,
                            1,
                            11,
                            "Avaliação inválida."
                    );

            service.cadastrar(avaliacaoInvalida);

            System.out.println(
                    "[ERRO] Validação de nota "
                    + "não funcionou."
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "[OK] Validação de nota"
            );

        } catch (Exception e) {

            System.out.println(
                    "[ERRO] Validação de nota: "
                    + e.getMessage()
            );
        }
    }

    private static class DramaRepositoryTeste
            implements DramaRepository {

        private final List<Drama> dramas
                = new ArrayList<>();

        @Override
        public void cadastrar(Drama drama) {

            drama.setIdDrama(
                    dramas.size() + 1
            );

            dramas.add(drama);
        }

        @Override
        public List<Drama> buscarPorTitulo(
                String titulo) {

            List<Drama> resultado
                    = new ArrayList<>();

            for (Drama drama : dramas) {

                if (drama.getTitulo()
                        .toLowerCase()
                        .contains(
                                titulo.toLowerCase()
                        )) {

                    resultado.add(drama);
                }
            }

            return resultado;
        }

        @Override
        public Drama buscarPorId(int idDrama) {

            for (Drama drama : dramas) {

                if (drama.getIdDrama()
                        == idDrama) {

                    return drama;
                }
            }

            return null;
        }

        @Override
        public void atualizar(Drama drama) {

            for (int i = 0;
                    i < dramas.size();
                    i++) {

                if (dramas.get(i)
                        .getIdDrama()
                        == drama.getIdDrama()) {

                    dramas.set(i, drama);
                    return;
                }
            }
        }

        @Override
        public void excluir(int idDrama) {

            dramas.removeIf(
                    drama
                    -> drama.getIdDrama()
                    == idDrama
            );
        }

        @Override
        public List<Drama> listarPopulares(
                int limite) {

            return dramas.subList(
                    0,
                    Math.min(
                            limite,
                            dramas.size()
                    )
            );
        }

        @Override
        public List<Drama> listarUltimosLancamentos(
                int limite) {

            return dramas.subList(
                    0,
                    Math.min(
                            limite,
                            dramas.size()
                    )
            );
        }
    }
}
