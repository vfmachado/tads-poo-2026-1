# Trabalho Prático Final - Programação Orientada a Objetos

## Título
**Sistema de Controle de Permissões e Acessos em Java**

## 1. Contextualização do problema
Sistemas corporativos modernos exigem controle rigoroso de quem pode acessar quais recursos e em quais condições. Falhas de modelagem nesse tipo de domínio tendem a gerar código frágil, acoplado e difícil de evoluir, com alto risco de inconsistências de segurança.

Neste trabalho, a turma deverá projetar e implementar um sistema de autorização em memória, com foco em qualidade arquitetural e maturidade de design orientado a objetos, e não apenas em execução funcional.

## 2. Objetivo geral
Projetar e implementar, em Java, um **Sistema de Controle de Permissões e Acessos** que demonstre domínio de Programação Orientada a Objetos, princípios SOLID, Object Calisthenics, clareza de responsabilidades e sustentabilidade do código.

## 3. Objetivos específicos
1. Modelar usuários, papéis, permissões e tentativas de acesso com abstrações orientadas ao domínio.
2. Implementar controle de autorização com baixo acoplamento e alta coesão.
3. Aplicar interfaces Java para contratos centrais do sistema.
4. Aplicar herança de forma justificada, evitando hierarquias artificiais.
5. Evidenciar princípios SOLID no projeto.
6. Aplicar conscientemente regras de Object Calisthenics.
7. Tratar validações de domínio e erros de forma explícita.
8. Demonstrar o funcionamento por cenários manuais executados em uma classe principal.

## 4. Escopo funcional obrigatório
O sistema deve implementar, no mínimo, as seguintes funcionalidades:

1. Cadastro de usuários.
2. Cadastro de permissões.
3. Cadastro de papéis/perfis de acesso.
4. Associação de permissões a papéis.
5. Associação de papéis a usuários.
6. Verificação se um usuário possui permissão para executar determinada ação.
7. Remoção de permissões de um papel.
8. Remoção de papéis de um usuário.
9. Listagem das permissões efetivas de um usuário.
10. Bloqueio e desbloqueio de usuários.
11. Diferenciação entre usuários comuns, administradores e usuários de sistema.
12. Registro em memória das tentativas de acesso autorizadas e negadas.

## 5. Requisitos funcionais
### RF01 - Gestão de permissões
O sistema deve permitir cadastrar, consultar e manipular permissões (ex.: USER_CREATE, REPORT_VIEW).

### RF02 - Gestão de papéis
O sistema deve permitir cadastrar papéis e associar/remover permissões de cada papel.

### RF03 - Gestão de usuários
O sistema deve permitir cadastrar usuários, atribuir/remover papéis e bloquear/desbloquear usuários.

### RF04 - Autorização
O sistema deve avaliar se um usuário pode executar uma ação sobre um recurso, respeitando bloqueio e permissões efetivas.

### RF05 - Auditoria de acesso em memória
Toda tentativa de acesso deve ser registrada em memória com, no mínimo:
1. Usuário.
2. Ação solicitada.
3. Resultado (AUTORIZADO/NEGADO).
4. Motivo resumido.
5. Data/hora.

### RF06 - Relatórios básicos no console
O sistema deve permitir exibir, no console:
1. Permissões efetivas por usuário.
2. Histórico de tentativas autorizadas.
3. Histórico de tentativas negadas.
4. Alterações em papéis e usuários, caso auditoria de mudanças seja implementada.

## 6. Requisitos não funcionais
1. O foco principal do trabalho é qualidade de design orientado a objetos e arquitetura, e não apenas funcionamento.
2. O código deve ser legível, coeso e de fácil manutenção.
3. O projeto deve demonstrar separação de responsabilidades entre camadas/pacotes.
4. O sistema deve operar integralmente em memória durante a execução.
5. O tempo de execução para os cenários da classe principal deve ser adequado para demonstração local.

## 7. Regras de negócio mínimas
1. Usuário bloqueado não pode acessar nenhum recurso.
2. Usuário sem papel associado não possui permissões efetivas.
3. Permissões efetivas do usuário resultam da união das permissões de todos os seus papéis.
4. Remoções de papel/permissão devem refletir imediatamente nas verificações de acesso subsequentes.
5. O sistema deve diferenciar comportamento por tipo de usuário (comum, administrador e sistema), conforme modelagem adotada.
6. Toda tentativa de acesso deve ser auditável em memória.

## 8. Restrições técnicas (obrigatórias)
1. Implementação obrigatória em **Java**.
3. Não utilizar Spring, Jakarta EE, Hibernate, JPA ou frameworks pesados.
4. Não utilizar banco de dados remoto, banco local, arquivos externos ou qualquer mecanismo de persistência.
5. Todos os dados devem ser inicializados e mantidos em memória (List, Set, Map ou repositórios em memória).
6. O projeto deve ser executável localmente.
7. O código deve estar organizado em pacotes Java.
8. Maven ou Gradle é permitido, mas não obrigatório.
9. Testes automatizados com JUnit são recomendados, porém não obrigatórios.
10. A avaliação mínima deve ser possível por meio de execução de uma classe principal (Main, App ou equivalente).

## 9. Exigências de modelagem orientada a objetos
O projeto deve explorar obrigatoriamente:

1. Programação Orientada a Objetos.
2. SOLID.
3. Object Calisthenics.
4. Herança.
5. Interfaces.
6. Polimorfismo.
7. Encapsulamento.
8. Baixo acoplamento.
9. Alta coesão.
10. Separação de responsabilidades.
11. Uso adequado de coleções.
12. Tratamento de erros e validações de domínio.

### 9.1 Uso obrigatório de interfaces Java
Devem existir interfaces para contratos importantes, como:

1. Repositórios em memória.
2. Serviços de autorização.
3. Estratégias de validação.
4. Políticas de acesso.
5. Serviços de auditoria/logging.

### 9.2 Uso consciente de herança
A herança deve ser adotada somente quando houver relação real de especialização (is-a), por exemplo:

1. Usuario como classe base abstrata.
2. Administrador, UsuarioComum e UsuarioSistema como especializações.

Modelos equivalentes serão aceitos, desde que tecnicamente justificados.

### 9.3 Justificativa de decisões de abstração
A equipe deve justificar onde e por que usou:

1. Interface.
2. Composição.
3. Herança.

## 10. Seção obrigatória de SOLID (evidências esperadas)
### SRP - Single Responsibility Principle
Cada classe deve possuir responsabilidade única e clara.

### OCP - Open/Closed Principle
Novas regras de permissão/autorização devem ser adicionáveis com mínima alteração em código existente.

### LSP - Liskov Substitution Principle
Subclasses devem substituir a classe base sem quebrar comportamento esperado pelo domínio.

### ISP - Interface Segregation Principle
Evitar interfaces grandes e genéricas; preferir contratos pequenos e coesos.

### DIP - Dependency Inversion Principle
Serviços devem depender de abstrações (interfaces), não de implementações concretas.

## 11. Seção obrigatória de Object Calisthenics
A aplicação das 9 regras deve ser perseguida com disciplina técnica. Especial atenção para:

1. Apenas um nível de indentação por método.
2. Evitar else sempre que possível (preferir guard clauses, polimorfismo ou objetos de estratégia).
3. Encapsular tipos primitivos e strings relevantes em objetos de valor.
4. Utilizar coleções de primeira classe.
5. Um ponto por linha.
6. Não abreviar nomes.
7. Manter classes pequenas.
8. Manter poucos atributos por classe.
9. Evitar getters e setters desnecessários, privilegiando comportamento dentro dos objetos.

## 12. Estrutura sugerida de pacotes (referência)
```text
src/main/java
 └── br.edu.seuprojeto.permissoes
	  ├── app
	  │   └── Main.java
	  ├── domain
	  │   ├── user
	  │   ├── role
	  │   ├── permission
	  │   ├── access
	  │   └── audit
	  ├── application
	  │   ├── service
	  │   └── policy
	  ├── infrastructure
	  │   └── repository
	  └── shared
			└── exception
```

Observação: a estrutura acima é uma referência. Adaptações são aceitas, desde que preservem separação clara de responsabilidades.

## 13. Cenários obrigatórios na classe principal (Main/App)
É obrigatória a criação de uma classe Main, App ou equivalente com cenários manuais completos de demonstração.

### 13.1 Dados mínimos a criar em memória
1. Criar permissões:
	- USER_CREATE
	- USER_DELETE
	- ROLE_CREATE
	- REPORT_VIEW
	- REPORT_EXPORT
	- SYSTEM_CONFIG

2. Criar papéis:
	- ADMIN
	- MANAGER
	- AUDITOR
	- BASIC_USER

3. Associar permissões aos papéis:
	- ADMIN recebe todas as permissões.
	- MANAGER pode visualizar e exportar relatórios.
	- AUDITOR pode apenas visualizar relatórios.
	- BASIC_USER possui permissões limitadas.

4. Criar usuários:
	- Um administrador.
	- Um gerente.
	- Um auditor.
	- Um usuário comum.
	- Um usuário bloqueado.

5. Associar papéis aos usuários.

### 13.2 Cenários de execução obrigatórios no console
1. Usuário autorizado acessando recurso permitido.
2. Usuário tentando acessar recurso sem permissão.
3. Usuário bloqueado tentando acessar qualquer recurso.
4. Usuário com múltiplos papéis.
5. Remoção de uma permissão e nova verificação, demonstrando mudança de comportamento.
6. Exibição das permissões efetivas de cada usuário.
7. Exibição do histórico de tentativas autorizadas.
8. Exibição do histórico de tentativas negadas.
9. Exibição de alterações em papéis e usuários, caso auditoria de mudanças tenha sido implementada.

## 14. Funcionalidades extras opcionais
As funcionalidades abaixo são opcionais e podem compor diferenciais de avaliação técnica:

1. Permissões temporárias.
2. Permissões herdadas por hierarquia de papéis.
3. Política de acesso baseada em contexto (horário, origem, ambiente, etc.).
4. Permissões sensíveis com autorização especial.
5. Auditoria de alterações em usuários, papéis e permissões.
6. Estratégias diferentes de autorização usando interfaces.
7. Regras customizadas por tipo de recurso.
8. Grupos de usuários.
9. Expiração de sessões.
10. Bloqueio automático por tentativas consecutivas de acesso negado.
11. Exportação simples de relatório de auditoria no console (sem arquivos).

## 15. Entregáveis obrigatórios
1. Código-fonte completo do projeto em Java.
2. Classe Main/App executável com os cenários obrigatórios.
3. Arquivo **DECISOES.md**, em português, contendo:
	- Justificativa do uso de herança.
	- Justificativa do uso de interfaces.
	- Pontos onde foi aplicado SOLID.
	- Pontos onde foi aplicado Object Calisthenics.
	- Principais decisões de modelagem.
	- Limitações conhecidas da implementação.
4. Instruções de execução local (README ou seção equivalente).

## 16. Critérios de avaliação (rubrica sugerida - 100 pontos)
### 1) Modelagem orientada a objetos (20 pontos)
1. Coerência das entidades e objetos de domínio.
2. Encapsulamento e invariantes preservados.
3. Uso adequado de composição e abstrações.

### 2) Uso correto de SOLID (20 pontos)
1. Evidências claras de SRP, OCP, LSP, ISP e DIP.
2. Decisões técnicas consistentes com manutenção e extensibilidade.

### 3) Aplicação de Object Calisthenics (15 pontos)
1. Aplicação prática das regras com justificativas.
2. Métodos curtos, baixo aninhamento e domínio expressivo.

### 4) Uso adequado de herança e interfaces (10 pontos)
1. Herança sem artificialidade.
2. Interfaces coesas e orientadas a contrato.
3. Polimorfismo relevante para regras de negócio.

### 5) Clareza das regras de domínio (10 pontos)
1. Regras implementadas de forma explícita e legível.
2. Comportamento previsível e consistente.

### 6) Organização do código em pacotes Java e legibilidade (10 pontos)
1. Separação de responsabilidades por pacote/camada.
2. Nomenclatura clara e ausência de ambiguidade.

### 7) Funcionamento dos cenários no Main/App (10 pontos)
1. Cobertura dos cenários obrigatórios.
2. Saídas de console claras para validação.

### 8) Tratamento de erros e validações de domínio (5 pontos)
1. Exceções de domínio apropriadas.
2. Mensagens de erro úteis e consistentes.

### 9) Justificativa técnica das decisões (DECISOES.md) (5 pontos)
1. Coerência entre discurso arquitetural e implementação.

## 17. Penalizações por más práticas
Poderão ser aplicados descontos quando identificadas as situações abaixo:

1. Classes anêmicas (dados expostos sem comportamento relevante).
2. Excesso de if/else onde polimorfismo/estratégia seria mais adequado.
3. Uso abusivo ou artificial de herança.
4. Ausência de encapsulamento.
5. Métodos muito longos e com múltiplas responsabilidades.
6. Acoplamento direto excessivo entre classes concretas.
7. Mistura de regras de domínio com lógica de apresentação (console) de forma desorganizada.

## 18. Orientações de boas práticas
1. Comece pelo domínio: modele conceitos e invariantes antes de implementar fluxos.
2. Prefira composição e contratos (interfaces) para variação de comportamento.
3. Evite classes utilitárias genéricas para concentrar regra de negócio fora do domínio.
4. Defina exceções de domínio específicas em pacote compartilhado.
5. Utilize coleções com semântica adequada (Set para unicidade, Map para acesso por chave, List para ordenação).
6. Garanta que o Main/App apenas orquestre cenários, evitando concentrar regras de negócio nele.
7. Registre decisões arquiteturais no DECISOES.md com justificativas técnicas objetivas.

## 19. Diretriz final de avaliação
A prioridade da avaliação será a qualidade de modelagem orientada a objetos e a robustez das decisões arquiteturais. A simples presença de funcionalidades sem boas abstrações, sem responsabilidade clara e sem justificativa de design não será suficiente para desempenho elevado na avaliação.
