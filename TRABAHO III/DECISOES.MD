
1. **Descrição geral da solução**

   * Como o sistema foi organizado;
   * Quais são os principais módulos, pacotes ou camadas;
   * Qual foi a lógica geral adotada para representar usuários, papéis, permissões e políticas de acesso.

2. **Justificativa do uso de herança**

   * Onde a herança foi utilizada;
   * Por que a herança foi escolhida nesses pontos;
   * Que relação de especialização existe entre as classes;
   * Quais alternativas foram consideradas, como composição ou interfaces;
   * Quais riscos ou limitações essa escolha pode gerar.

3. **Justificativa do uso de interfaces**

   * Quais interfaces foram criadas;
   * Que contratos elas representam;
   * Como elas contribuem para baixo acoplamento;
   * Como elas permitem trocar implementações sem alterar o domínio;
   * Exemplos de classes que dependem de abstrações em vez de implementações concretas.

4. **Uso de composição**

   * Onde a composição foi utilizada;
   * Por que composição foi preferida em vez de herança;
   * Como os objetos colaboram entre si;
   * Como a composição ajudou a reduzir acoplamento e duplicação.

5. **Aplicação dos princípios SOLID**

   * Exemplos concretos de aplicação do Single Responsibility Principle;
   * Exemplos concretos de aplicação do Open/Closed Principle;
   * Exemplos concretos de aplicação do Liskov Substitution Principle;
   * Exemplos concretos de aplicação do Interface Segregation Principle;
   * Exemplos concretos de aplicação do Dependency Inversion Principle;
   * Pontos em que algum princípio não foi plenamente aplicado e por quê.

6. **Aplicação de Object Calisthenics**

   * Quais regras foram aplicadas com mais rigor;
   * Quais regras foram difíceis de aplicar;
   * Exemplos de refatorações feitas para reduzir `if/else`, métodos longos ou classes grandes;
   * Exemplos de objetos de valor, coleções de primeira classe ou encapsulamento de primitivas;
   * Pontos em que os alunos decidiram não aplicar determinada regra e a justificativa técnica.

7. **Tratamento de erros e validações**

   * Quais validações foram implementadas;
   * Que exceções ou estratégias de erro foram usadas;
   * Como o sistema evita permissões duplicadas, usuários inválidos ou papéis inexistentes;

8. **Discussão crítica**

   * Quais foram as decisões mais difíceis;
   * Quais partes do código ficaram melhores do ponto de vista orientado a objetos;
   * Quais partes ainda poderiam ser melhoradas;
   * O que seria alterado se o sistema precisasse evoluir para um banco de dados real;
 
