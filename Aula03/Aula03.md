Claro. Abaixo está uma explicação didática sobre **getters**, **setters**, **construtores**, **associação entre classes**, e **referência de objetos por parâmetro**, com exemplos em **Java** e **TypeScript**, seguida de uma **lista de exercícios** no final.

---

# Conceitos de POO: getters, setters, construtores, associação e referência por parâmetro

## 1. Getters e Setters

Em Programação Orientada a Objetos, normalmente os atributos de uma classe não devem ficar totalmente expostos para acesso direto.
Por isso, usamos **encapsulamento**, que consiste em proteger os dados internos do objeto e controlar a forma como eles são lidos e alterados.

Os métodos mais comuns para isso são:

* **getter**: método usado para **obter/ler** o valor de um atributo
* **setter**: método usado para **alterar** o valor de um atributo

### Por que usar getters e setters?

Porque eles permitem:

* proteger os dados do objeto
* validar valores antes de alterar atributos
* controlar melhor as regras de negócio
* evitar alterações indevidas

---

## 2. Exemplo de getters e setters em Java

```java
public class Pessoa {
    private String nome;
    private int idade;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        if (idade >= 0) {
            this.idade = idade;
        }
    }
}
```

### Uso

```java
public class Main {
    public static void main(String[] args) {
        Pessoa p = new Pessoa();

        p.setNome("Carlos");
        p.setIdade(25);

        System.out.println("Nome: " + p.getNome());
        System.out.println("Idade: " + p.getIdade());
    }
}
```

### Observação importante

No exemplo acima, o atributo `idade` não pode receber valor negativo, porque o setter faz a validação.

---

## 3. Exemplo de getters e setters em TypeScript

Em TypeScript, podemos fazer de duas formas:

* com métodos tradicionais (`getNome()`, `setNome()`)
* com `get` e `set` da linguagem

### Forma tradicional

```typescript
class Pessoa {
  private nome: string;
  private idade: number;

  constructor(nome: string, idade: number) {
    this.nome = nome;
    this.idade = idade;
  }

  public getNome(): string {
    return this.nome;
  }

  public setNome(nome: string): void {
    this.nome = nome;
  }

  public getIdade(): number {
    return this.idade;
  }

  public setIdade(idade: number): void {
    if (idade >= 0) {
      this.idade = idade;
    }
  }
}

const p = new Pessoa("Carlos", 25);
console.log(p.getNome());
console.log(p.getIdade());
```

### Forma com `get` e `set`

```typescript
class Pessoa {
  private _nome: string;
  private _idade: number;

  constructor(nome: string, idade: number) {
    this._nome = nome;
    this._idade = idade;
  }

  get nome(): string {
    return this._nome;
  }

  set nome(nome: string) {
    this._nome = nome;
  }

  get idade(): number {
    return this._idade;
  }

  set idade(idade: number) {
    if (idade >= 0) {
      this._idade = idade;
    }
  }
}

const p = new Pessoa("Ana", 30);
console.log(p.nome);
p.idade = 31;
console.log(p.idade);
```

---

# 4. Construtores

O **construtor** é um método especial usado para criar e inicializar objetos.

Ele é executado automaticamente quando usamos `new`.

### Objetivo do construtor

* definir valores iniciais
* garantir que o objeto já nasça em um estado válido
* facilitar a criação de objetos completos

---

## 5. Exemplo de construtor em Java

```java
public class Produto {
    private String nome;
    private double preco;

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public void exibirDados() {
        System.out.println("Produto: " + nome);
        System.out.println("Preço: " + preco);
    }
}
```

### Uso

```java
public class Main {
    public static void main(String[] args) {
        Produto produto = new Produto("Teclado", 199.90);
        produto.exibirDados();
    }
}
```

---

## 6. Exemplo de construtor em TypeScript

```typescript
class Produto {
  private nome: string;
  private preco: number;

  constructor(nome: string, preco: number) {
    this.nome = nome;
    this.preco = preco;
  }

  public exibirDados(): void {
    console.log(`Produto: ${this.nome}`);
    console.log(`Preço: ${this.preco}`);
  }
}

const produto = new Produto("Teclado", 199.9);
produto.exibirDados();
```

---

# 7. Associação: quando uma classe usa outra

A **associação** acontece quando um objeto de uma classe se relaciona com um objeto de outra classe.

Em termos simples:

> uma classe **usa**, **conhece** ou **se relaciona** com outra

Esse é um dos relacionamentos mais comuns em POO.

### Exemplo conceitual

* um `Aluno` pertence a um `Curso`
* um `Pedido` possui um `Cliente`
* um `Carro` tem um `Motorista`

Nesse caso, uma classe possui referência para um objeto da outra.

---

## 8. Exemplo de associação em Java

### Classe Curso

```java
public class Curso {
    private String nome;

    public Curso(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
```

### Classe Aluno

```java
public class Aluno {
    private String nome;
    private Curso curso;

    public Aluno(String nome, Curso curso) {
        this.nome = nome;
        this.curso = curso;
    }

    public void exibirDados() {
        System.out.println("Aluno: " + nome);
        System.out.println("Curso: " + curso.getNome());
    }
}
```

### Main

```java
public class Main {
    public static void main(String[] args) {
        Curso curso = new Curso("Análise e Desenvolvimento de Sistemas");
        Aluno aluno = new Aluno("Marina", curso);

        aluno.exibirDados();
    }
}
```

### O que aconteceu aqui?

A classe `Aluno` possui um atributo do tipo `Curso`.
Isso significa que `Aluno` está associado a `Curso`.

---

## 9. Exemplo de associação em TypeScript

```typescript
class Curso {
  constructor(private nome: string) {}

  public getNome(): string {
    return this.nome;
  }
}

class Aluno {
  private nome: string;
  private curso: Curso;

  constructor(nome: string, curso: Curso) {
    this.nome = nome;
    this.curso = curso;
  }

  public exibirDados(): void {
    console.log(`Aluno: ${this.nome}`);
    console.log(`Curso: ${this.curso.getNome()}`);
  }
}

const curso = new Curso("ADS");
const aluno = new Aluno("Marina", curso);

aluno.exibirDados();
```

---

# 10. Referência de objetos por parâmetro

Quando passamos um objeto para um método, não estamos copiando o objeto inteiro.
Na prática, o método recebe uma **referência** para esse objeto.

Isso significa que o método passa a acessar o mesmo objeto criado fora dele.

## Ideia central

Se um método altera o estado do objeto recebido, essa alteração pode ser percebida fora do método também.

---

## 11. Exemplo em Java

```java
public class Conta {
    private String titular;
    private double saldo;

    public Conta(String titular, double saldo) {
        this.titular = titular;
        this.saldo = saldo;
    }

    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
        }
    }

    public void exibirDados() {
        System.out.println("Titular: " + titular);
        System.out.println("Saldo: " + saldo);
    }
}
```

```java
public class Banco {
    public void realizarDeposito(Conta conta, double valor) {
        conta.depositar(valor);
    }
}
```

```java
public class Main {
    public static void main(String[] args) {
        Conta conta = new Conta("Lucas", 1000);
        Banco banco = new Banco();

        banco.realizarDeposito(conta, 500);

        conta.exibirDados();
    }
}
```

### Resultado esperado

O saldo da conta será alterado para `1500`, porque o método recebeu referência para o mesmo objeto `Conta`.

---

## 12. Exemplo em TypeScript

```typescript
class Conta {
  constructor(private titular: string, private saldo: number) {}

  public depositar(valor: number): void {
    if (valor > 0) {
      this.saldo += valor;
    }
  }

  public exibirDados(): void {
    console.log(`Titular: ${this.titular}`);
    console.log(`Saldo: ${this.saldo}`);
  }
}

class Banco {
  public realizarDeposito(conta: Conta, valor: number): void {
    conta.depositar(valor);
  }
}

const conta = new Conta("Lucas", 1000);
const banco = new Banco();

banco.realizarDeposito(conta, 500);
conta.exibirDados();
```

---

# 13. Atenção: passar objeto por parâmetro não é criar outro objeto

Veja este caso:

## Java

```java
public class Pessoa {
    private String nome;

    public Pessoa(String nome) {
        this.nome = nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void exibirNome() {
        System.out.println(nome);
    }
}
```

```java
public class Teste {
    public void alterarPessoa(Pessoa p) {
        p.setNome("Nome alterado");
    }
}
```

```java
public class Main {
    public static void main(String[] args) {
        Pessoa pessoa = new Pessoa("João");
        Teste teste = new Teste();

        teste.alterarPessoa(pessoa);
        pessoa.exibirNome();
    }
}
```

O nome exibido será `"Nome alterado"`.

Isso ocorre porque `p` aponta para o mesmo objeto que `pessoa`.

---

# 14. Exemplo mais completo integrando tudo

Agora vamos juntar:

* construtor
* getters e setters
* associação
* passagem de objeto por parâmetro

## Java

```java
public class Professor {
    private String nome;

    public Professor(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
```

```java
public class Disciplina {
    private String nome;
    private Professor professor;

    public Disciplina(String nome, Professor professor) {
        this.nome = nome;
        this.professor = professor;
    }

    public String getNome() {
        return nome;
    }

    public Professor getProfessor() {
        return professor;
    }
}
```

```java
public class Aluno {
    private String nome;
    private Disciplina disciplina;

    public Aluno(String nome, Disciplina disciplina) {
        this.nome = nome;
        this.disciplina = disciplina;
    }

    public void exibirDados() {
        System.out.println("Aluno: " + nome);
        System.out.println("Disciplina: " + disciplina.getNome());
        System.out.println("Professor: " + disciplina.getProfessor().getNome());
    }
}
```

```java
public class Secretaria {
    public void trocarProfessor(Disciplina disciplina, Professor novoProfessor) {
        // como não existe setter, seria necessário criar um
        // disciplina.setProfessor(novoProfessor);
    }
}
```

Aqui temos:

* `Professor` e `Disciplina` com construtores
* `Disciplina` associada a `Professor`
* `Aluno` associado a `Disciplina`
* possibilidade de passar objetos por parâmetro em métodos

---

## TypeScript

```typescript
class Professor {
  constructor(private nome: string) {}

  public getNome(): string {
    return this.nome;
  }
}

class Disciplina {
  private nome: string;
  private professor: Professor;

  constructor(nome: string, professor: Professor) {
    this.nome = nome;
    this.professor = professor;
  }

  public getNome(): string {
    return this.nome;
  }

  public getProfessor(): Professor {
    return this.professor;
  }

  public setProfessor(professor: Professor): void {
    this.professor = professor;
  }
}

class Aluno {
  constructor(private nome: string, private disciplina: Disciplina) {}

  public exibirDados(): void {
    console.log(`Aluno: ${this.nome}`);
    console.log(`Disciplina: ${this.disciplina.getNome()}`);
    console.log(`Professor: ${this.disciplina.getProfessor().getNome()}`);
  }
}

class Secretaria {
  public trocarProfessor(disciplina: Disciplina, novoProfessor: Professor): void {
    disciplina.setProfessor(novoProfessor);
  }
}

const professor1 = new Professor("Ana");
const professor2 = new Professor("Carlos");
const disciplina = new Disciplina("POO", professor1);
const aluno = new Aluno("Marcos", disciplina);
const secretaria = new Secretaria();

aluno.exibirDados();

secretaria.trocarProfessor(disciplina, professor2);

aluno.exibirDados();
```



# Lista de exercícios

## Parte 1 — Conceitos básicos

1. Explique com suas palavras o que é encapsulamento.
2. Qual a diferença entre acessar um atributo diretamente e usar getter/setter?
3. Para que serve um construtor?
4. O que significa dizer que uma classe está associada a outra?
5. O que acontece quando um objeto é passado como parâmetro para um método?

---

## Parte 2 — Exercícios de implementação simples

6. Crie uma classe `Pessoa` em Java com os atributos `nome` e `idade`, ambos privados.

   * Crie getters e setters.
   * Não permita idade negativa.

7. Crie a mesma classe `Pessoa` em TypeScript.

   * Implemente construtor.
   * Implemente getter e setter para `idade`.

8. Crie uma classe `Produto` com:

   * `nome`
   * `preco`
   * construtor
   * método para exibir os dados

9. Crie uma classe `Livro` com:

   * `titulo`
   * `autor`
   * `numeroPaginas`
   * construtor
   * getters para todos os atributos

10. Crie uma classe `Funcionario` com:

* `nome`
* `salario`
* setter que impeça salário negativo

---

## Parte 3 — Associação entre classes

11. Crie uma classe `Motor` e uma classe `Carro`.

* `Carro` deve possuir um atributo do tipo `Motor`.
* Crie um método para exibir os dados do carro e do motor.

12. Crie uma classe `Cliente` e uma classe `Pedido`.

* `Pedido` deve estar associado a `Cliente`.

13. Crie uma classe `Professor` e uma classe `Turma`.

* `Turma` deve possuir referência para `Professor`.

14. Crie uma classe `Departamento` e uma classe `Funcionario`.

* Cada funcionário deve pertencer a um departamento.

15. Crie uma classe `Filme` e uma classe `Diretor`.

* Um filme deve armazenar o diretor responsável.

---

## Parte 4 — Passagem de objeto por parâmetro

16. Crie uma classe `ContaBancaria` com saldo e método `depositar`.
    Depois, crie uma classe `CaixaEletronico` com um método que receba uma `ContaBancaria` por parâmetro e faça um depósito.

17. Crie uma classe `Aluno` com atributo `nota`.
    Crie um método em outra classe que receba um aluno por parâmetro e altere sua nota.

18. Crie uma classe `Lampada` com atributo `ligada`.
    Crie uma classe `Interruptor` com método que receba uma lâmpada e altere seu estado.

19. Crie uma classe `Carrinho` com quantidade de itens.
    Crie uma classe `Loja` com método que receba um carrinho por parâmetro e adicione item.

20. Crie uma classe `Usuario` com atributo `email`.
    Crie um método em uma classe `Sistema` que receba um usuário por parâmetro e altere o email.

---

## Parte 5 — Exercícios de interpretação

21. Em um sistema, a classe `Aluno` possui um atributo do tipo `Curso`. Que tipo de relacionamento existe aí?
22. Se um método recebe um objeto `Conta` e chama `depositar(100)`, o saldo original pode mudar? Explique.
23. Por que um setter pode ser melhor do que deixar o atributo público?
24. Um construtor é obrigatório em toda classe? Explique.
25. Uma classe pode ter associação com mais de uma outra classe? Dê um exemplo.

---

## Parte 6 — Desafios

26. Crie um sistema com as classes:

* `Autor`
* `Livro`
* `Biblioteca`

Regras:

* `Livro` deve estar associado a `Autor`
* `Biblioteca` deve receber um `Livro` por parâmetro em um método de cadastro
* crie métodos para exibir os dados

27. Crie um sistema com:

* `Paciente`
* `Medico`
* `Consulta`

Regras:

* `Consulta` deve associar um paciente a um médico
* use construtores e getters
* crie um método para exibir resumo da consulta

28. Crie um sistema com:

* `Jogador`
* `Time`
* `Tecnico`

Regras:

* `Time` deve ter associação com `Tecnico`
* `Jogador` deve estar associado a `Time`

29. Crie um sistema com:

* `Cliente`
* `Produto`
* `Venda`

Regras:

* `Venda` deve estar associada a `Cliente`
* `Venda` deve receber um `Produto` por parâmetro em método de adicionar item

30. Crie um sistema acadêmico com:

* `Aluno`
* `Disciplina`
* `Professor`

Regras:

* `Disciplina` associada a `Professor`
* `Aluno` associado a `Disciplina`
* crie construtores, getters e ao menos um setter com validação

---
