
package br.edu.poo.permissoes.app;

import java.util.Set;

/**
 * Esboco de classe principal para demonstracao manual dos cenarios obrigatorios.
 *
 * Objetivo:
 * - Servir como guia de orquestracao dos testes manuais do trabalho.
 * - Cobrir todas as regras descritas no enunciado, com dados em memoria.
 *
 * Observacao:
 * - Este arquivo pressupoe que as interfaces e classes de dominio/servico/repositorio
 *   ja foram implementadas em outros pacotes.
 * - Ajuste nomes de classes, metodos e construtores conforme sua implementacao final.
 */
public class Main {

    public static void main(String[] args) {
        new Main().executar();
    }

    private void executar() {
        imprimirCabecalho();

        // 1) Instanciacao de contratos por interfaces (DIP)
        // Substitua pelas implementacoes reais em memoria do seu projeto.
        PermissaoRepository permissaoRepository = new PermissaoRepositoryEmMemoria();
        PapelRepository papelRepository = new PapelRepositoryEmMemoria();
        UsuarioRepository usuarioRepository = new UsuarioRepositoryEmMemoria();
        RegistroAcessoRepository registroAcessoRepository = new RegistroAcessoRepositoryEmMemoria();
        RegistroAlteracaoRepository registroAlteracaoRepository = new RegistroAlteracaoRepositoryEmMemoria();

        AuditoriaService auditoriaService = new AuditoriaServiceEmMemoria(
            registroAcessoRepository,
            registroAlteracaoRepository
        );

        PoliticaAcesso politicaPadrao = new PoliticaPorPermissaoDireta();
        AutorizacaoService autorizacaoService = new AutorizacaoServicePadrao(politicaPadrao, auditoriaService);

        UsuarioService usuarioService = new UsuarioServicePadrao(usuarioRepository, auditoriaService);
        PapelService papelService = new PapelServicePadrao(papelRepository, permissaoRepository, auditoriaService);
        PermissaoService permissaoService = new PermissaoServicePadrao(permissaoRepository, auditoriaService);

        // 2) Cadastro de permissoes obrigatorias
        Permissao userCreate = permissaoService.cadastrar("USER_CREATE");
        Permissao userDelete = permissaoService.cadastrar("USER_DELETE");
        Permissao roleCreate = permissaoService.cadastrar("ROLE_CREATE");
        Permissao reportView = permissaoService.cadastrar("REPORT_VIEW");
        Permissao reportExport = permissaoService.cadastrar("REPORT_EXPORT");
        Permissao systemConfig = permissaoService.cadastrar("SYSTEM_CONFIG");

        // 3) Cadastro de papeis obrigatorios
        Papel admin = papelService.cadastrar("ADMIN");
        Papel manager = papelService.cadastrar("MANAGER");
        Papel auditor = papelService.cadastrar("AUDITOR");
        Papel basicUser = papelService.cadastrar("BASIC_USER");

        // 4) Associacao de permissoes aos papeis
        papelService.associarPermissao(admin.id(), userCreate.id());
        papelService.associarPermissao(admin.id(), userDelete.id());
        papelService.associarPermissao(admin.id(), roleCreate.id());
        papelService.associarPermissao(admin.id(), reportView.id());
        papelService.associarPermissao(admin.id(), reportExport.id());
        papelService.associarPermissao(admin.id(), systemConfig.id());

        papelService.associarPermissao(manager.id(), reportView.id());
        papelService.associarPermissao(manager.id(), reportExport.id());

        papelService.associarPermissao(auditor.id(), reportView.id());

        papelService.associarPermissao(basicUser.id(), reportView.id());

        // 5) Criacao de usuarios por especializacao (heranca consciente)
        Usuario administrador = usuarioService.cadastrar(new Administrador("admin@empresa.com", "Administrador Geral"));
        Usuario gerente = usuarioService.cadastrar(new UsuarioComum("gerente@empresa.com", "Gerente Regional"));
        Usuario usuarioAuditor = usuarioService.cadastrar(new UsuarioSistema("auditor@empresa.com", "Servico de Auditoria"));
        Usuario usuarioComum = usuarioService.cadastrar(new UsuarioComum("user@empresa.com", "Usuario Basico"));
        Usuario usuarioBloqueado = usuarioService.cadastrar(new UsuarioComum("bloqueado@empresa.com", "Usuario Bloqueado"));

        // 6) Associacao de papeis aos usuarios
        usuarioService.associarPapel(administrador.id(), admin.id());
        usuarioService.associarPapel(gerente.id(), manager.id());
        usuarioService.associarPapel(usuarioAuditor.id(), auditor.id());
        usuarioService.associarPapel(usuarioComum.id(), basicUser.id());
        usuarioService.associarPapel(usuarioBloqueado.id(), basicUser.id());

        // Usuario com multiplos papeis (cenario obrigatorio)
        usuarioService.associarPapel(gerente.id(), auditor.id());

        // 7) Bloqueio de usuario
        usuarioService.bloquear(usuarioBloqueado.id(), "Bloqueio inicial para demonstracao");

        // 8) Cenarios obrigatorios de verificacao de acesso
        executarCenario("Usuario autorizado acessando recurso permitido", () -> {
            verificarAcesso(autorizacaoService, administrador, "USER_CREATE");
        });

        executarCenario("Usuario tentando acessar recurso sem permissao", () -> {
            verificarAcesso(autorizacaoService, usuarioComum, "USER_DELETE");
        });

        executarCenario("Usuario bloqueado tentando acessar qualquer recurso", () -> {
            verificarAcesso(autorizacaoService, usuarioBloqueado, "REPORT_VIEW");
        });

        executarCenario("Usuario com multiplos papeis", () -> {
            verificarAcesso(autorizacaoService, gerente, "REPORT_VIEW");
            verificarAcesso(autorizacaoService, gerente, "REPORT_EXPORT");
        });

        // 9) Remocao de permissao e nova verificacao
        executarCenario("Remocao de permissao de papel e mudanca de comportamento", () -> {
            verificarAcesso(autorizacaoService, gerente, "REPORT_EXPORT");
            papelService.removerPermissao(manager.id(), reportExport.id());
            verificarAcesso(autorizacaoService, gerente, "REPORT_EXPORT");
        });

        // 10) Remocao de papel de usuario e nova verificacao
        executarCenario("Remocao de papel do usuario", () -> {
            verificarAcesso(autorizacaoService, usuarioComum, "REPORT_VIEW");
            usuarioService.removerPapel(usuarioComum.id(), basicUser.id());
            verificarAcesso(autorizacaoService, usuarioComum, "REPORT_VIEW");
        });

        // 11) Desbloqueio e nova verificacao
        executarCenario("Desbloqueio de usuario", () -> {
            verificarAcesso(autorizacaoService, usuarioBloqueado, "REPORT_VIEW");
            usuarioService.desbloquear(usuarioBloqueado.id(), "Desbloqueio para demonstracao");
            verificarAcesso(autorizacaoService, usuarioBloqueado, "REPORT_VIEW");
        });

        // 12) Listagem de permissoes efetivas de cada usuario
        executarCenario("Permissoes efetivas por usuario", () -> {
            imprimirPermissoesEfetivas(usuarioService, administrador);
            imprimirPermissoesEfetivas(usuarioService, gerente);
            imprimirPermissoesEfetivas(usuarioService, usuarioAuditor);
            imprimirPermissoesEfetivas(usuarioService, usuarioComum);
            imprimirPermissoesEfetivas(usuarioService, usuarioBloqueado);
        });

        // 13) Historicos de auditoria em memoria
        executarCenario("Historico de tentativas autorizadas", () -> {
            auditoriaService.listarTentativasAutorizadas()
                .forEach(registro -> System.out.println("  " + registro.resumo()));
        });

        executarCenario("Historico de tentativas negadas", () -> {
            auditoriaService.listarTentativasNegadas()
                .forEach(registro -> System.out.println("  " + registro.resumo()));
        });

        executarCenario("Alteracoes em papeis e usuarios (se auditoria de alteracao implementada)", () -> {
            auditoriaService.listarAlteracoes()
                .forEach(registro -> System.out.println("  " + registro.resumo()));
        });

        imprimirRodape();
    }

    private void verificarAcesso(AutorizacaoService autorizacaoService, Usuario usuario, String codigoPermissao) {
        DecisaoAcesso decisao = autorizacaoService.verificarAcesso(usuario.id(), codigoPermissao);
        System.out.printf(
            "  Usuario=%s | Acao=%s | Resultado=%s | Motivo=%s%n",
            usuario.identificador(),
            codigoPermissao,
            decisao.resultado(),
            decisao.motivo()
        );
    }

    private void imprimirPermissoesEfetivas(UsuarioService usuarioService, Usuario usuario) {
        Set<String> permissoes = usuarioService.listarPermissoesEfetivas(usuario.id());
        System.out.printf("  %s -> %s%n", usuario.identificador(), permissoes);
    }

    private void executarCenario(String titulo, Runnable acao) {
        System.out.println();
        System.out.println("=== " + titulo + " ===");
        try {
            acao.run();
        } catch (DominioException e) {
            System.out.println("  Erro de dominio: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("  Erro inesperado: " + e.getMessage());
        }
    }

    private void imprimirCabecalho() {
        System.out.println("============================================");
        System.out.println(" SISTEMA DE CONTROLE DE PERMISSOES E ACESSOS");
        System.out.println(" Cenarios manuais obrigatorios");
        System.out.println("============================================");
    }

    private void imprimirRodape() {
        System.out.println();
        System.out.println("============================================");
        System.out.println(" Fim da execucao dos cenarios");
        System.out.println("============================================");
    }

    // ------------------------------------------------------------
    // Abaixo: contratos minimos esperados.
    // Mantenha apenas se quiser usar este arquivo como guia autocontido.
    // Caso ja existam no projeto, remova estas declaracoes locais.
    // ------------------------------------------------------------

    interface PermissaoRepository {}
    interface PapelRepository {}
    interface UsuarioRepository {}
    interface RegistroAcessoRepository {}
    interface RegistroAlteracaoRepository {}

    interface PoliticaAcesso {}

    interface AutorizacaoService {
        DecisaoAcesso verificarAcesso(IdUsuario idUsuario, String codigoPermissao);
    }

    interface UsuarioService {
        Usuario cadastrar(Usuario usuario);
        void associarPapel(IdUsuario usuarioId, IdPapel papelId);
        void removerPapel(IdUsuario usuarioId, IdPapel papelId);
        void bloquear(IdUsuario usuarioId, String motivo);
        void desbloquear(IdUsuario usuarioId, String motivo);
        Set<String> listarPermissoesEfetivas(IdUsuario usuarioId);
    }

    interface PapelService {
        Papel cadastrar(String nome);
        void associarPermissao(IdPapel papelId, IdPermissao permissaoId);
        void removerPermissao(IdPapel papelId, IdPermissao permissaoId);
    }

    interface PermissaoService {
        Permissao cadastrar(String codigo);
    }

    interface AuditoriaService {
        java.util.List<RegistroAcesso> listarTentativasAutorizadas();
        java.util.List<RegistroAcesso> listarTentativasNegadas();
        java.util.List<RegistroAlteracao> listarAlteracoes();
    }

    // Implementacoes placeholders somente para manter o esboco legivel.
    // Substitua por implementacoes reais nos pacotes apropriados.
    static final class PermissaoRepositoryEmMemoria implements PermissaoRepository {}
    static final class PapelRepositoryEmMemoria implements PapelRepository {}
    static final class UsuarioRepositoryEmMemoria implements UsuarioRepository {}
    static final class RegistroAcessoRepositoryEmMemoria implements RegistroAcessoRepository {}
    static final class RegistroAlteracaoRepositoryEmMemoria implements RegistroAlteracaoRepository {}
    static final class PoliticaPorPermissaoDireta implements PoliticaAcesso {}

    static final class AutorizacaoServicePadrao implements AutorizacaoService {
        AutorizacaoServicePadrao(PoliticaAcesso politicaAcesso, AuditoriaService auditoriaService) {}
        @Override
        public DecisaoAcesso verificarAcesso(IdUsuario idUsuario, String codigoPermissao) {
            return DecisaoAcesso.negado("Implementacao pendente");
        }
    }

    static final class UsuarioServicePadrao implements UsuarioService {
        UsuarioServicePadrao(UsuarioRepository usuarioRepository, AuditoriaService auditoriaService) {}
        @Override
        public Usuario cadastrar(Usuario usuario) { return usuario; }
        @Override
        public void associarPapel(IdUsuario usuarioId, IdPapel papelId) {}
        @Override
        public void removerPapel(IdUsuario usuarioId, IdPapel papelId) {}
        @Override
        public void bloquear(IdUsuario usuarioId, String motivo) {}
        @Override
        public void desbloquear(IdUsuario usuarioId, String motivo) {}
        @Override
        public Set<String> listarPermissoesEfetivas(IdUsuario usuarioId) { return Set.of(); }
    }

    static final class PapelServicePadrao implements PapelService {
        PapelServicePadrao(PapelRepository papelRepository, PermissaoRepository permissaoRepository, AuditoriaService auditoriaService) {}
        @Override
        public Papel cadastrar(String nome) { return new Papel(new IdPapel(nome)); }
        @Override
        public void associarPermissao(IdPapel papelId, IdPermissao permissaoId) {}
        @Override
        public void removerPermissao(IdPapel papelId, IdPermissao permissaoId) {}
    }

    static final class PermissaoServicePadrao implements PermissaoService {
        PermissaoServicePadrao(PermissaoRepository permissaoRepository, AuditoriaService auditoriaService) {}
        @Override
        public Permissao cadastrar(String codigo) { return new Permissao(new IdPermissao(codigo)); }
    }

    static final class AuditoriaServiceEmMemoria implements AuditoriaService {
        AuditoriaServiceEmMemoria(RegistroAcessoRepository acessoRepository, RegistroAlteracaoRepository alteracaoRepository) {}
        @Override
        public java.util.List<RegistroAcesso> listarTentativasAutorizadas() { return java.util.List.of(); }
        @Override
        public java.util.List<RegistroAcesso> listarTentativasNegadas() { return java.util.List.of(); }
        @Override
        public java.util.List<RegistroAlteracao> listarAlteracoes() { return java.util.List.of(); }
    }

    static class DominioException extends RuntimeException {
        DominioException(String message) { super(message); }
    }

    record IdUsuario(String valor) {}
    record IdPapel(String valor) {}
    record IdPermissao(String valor) {}

    static class Usuario {
        private final IdUsuario id;
        private final String identificador;

        Usuario(String identificador) {
            this.id = new IdUsuario(identificador);
            this.identificador = identificador;
        }

        IdUsuario id() { return id; }
        String identificador() { return identificador; }
    }

    static final class Administrador extends Usuario {
        Administrador(String email, String nome) { super(email + " (" + nome + ")"); }
    }

    static final class UsuarioComum extends Usuario {
        UsuarioComum(String email, String nome) { super(email + " (" + nome + ")"); }
    }

    static final class UsuarioSistema extends Usuario {
        UsuarioSistema(String email, String nomeServico) { super(email + " (" + nomeServico + ")"); }
    }

    record Papel(IdPapel id) {}
    record Permissao(IdPermissao id) {}

    record DecisaoAcesso(String resultado, String motivo) {
        static DecisaoAcesso negado(String motivo) {
            return new DecisaoAcesso("NEGADO", motivo);
        }
    }

    record RegistroAcesso(String resumo) {}
    record RegistroAlteracao(String resumo) {}
}
