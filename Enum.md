# ENUM em JAVA e POO


# 1. O que é `enum`?

`enum` significa **enumeration** (enumeração).

É um tipo especial em Java usado para representar **um conjunto fixo e controlado de constantes**.

Exemplos reais:

* Dias da semana
* Status de pedido
* Tipo de usuário
* Nível de acesso
* Estados de uma operação
* Prioridades
* Formas de pagamento



## Problema sem enum

```java
String status = "APROVADO";
```

Problemas:

* alguém pode escrever errado:

```java
"aproado"
"aprovdo"
"ok"
```

* difícil manter
* sem segurança de tipo



## Solução com enum

```java
Status status = Status.APROVADO;
```

Muito melhor:

* valores controlados
* autocomplete da IDE
* validação em tempo de compilação
* código mais limpo



# 2. Criando o primeiro enum

```java
public enum Status {
    PENDENTE,
    APROVADO,
    CANCELADO
}
```

Uso:

```java
Status s = Status.PENDENTE;
System.out.println(s);
```

Saída:

```java
PENDENTE
```



# 3. Enum é um tipo real

Você declara variável normalmente:

```java
Status pedido;
```

Atribui:

```java
pedido = Status.APROVADO;
```

Comparação:

```java
if (pedido == Status.APROVADO) {
    System.out.println("Liberado");
}
```



# 4. Por que usar `==` funciona?

Enums são instâncias únicas.

Cada valor existe uma única vez no sistema.

Por isso:

```java
status == Status.APROVADO
```

é seguro.



# 5. Exemplo real — Sistema de Pedidos

```java
public enum StatusPedido {
    AGUARDANDO_PAGAMENTO,
    PAGO,
    ENVIADO,
    ENTREGUE,
    CANCELADO
}
```

Classe:

```java
public class Pedido {
    private StatusPedido status;

    public Pedido() {
        status = StatusPedido.AGUARDANDO_PAGAMENTO;
    }
}
```



# 6. Enum com switch

```java
switch (status) {
    case PAGO:
        System.out.println("Separar envio");
        break;

    case CANCELADO:
        System.out.println("Encerrar pedido");
        break;
}
```

Muito comum em sistemas.



# 7. Enum com atributos

Enums podem ter dados internos.

## Exemplo: prioridade

```java
public enum Prioridade {
    BAIXA(1),
    MEDIA(2),
    ALTA(3);

    private int nivel;

    Prioridade(int nivel) {
        this.nivel = nivel;
    }

    public int getNivel() {
        return nivel;
    }
}
```

Uso:

```java
System.out.println(Prioridade.ALTA.getNivel());
```

Saída:

```java
3
```



# 8. Enum com descrição amigável

```java
public enum StatusPagamento {
    PENDENTE("Aguardando pagamento"),
    PAGO("Pagamento confirmado"),
    RECUSADO("Pagamento recusado");

    private String descricao;

    StatusPagamento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
```

Uso:

```java
System.out.println(StatusPagamento.PAGO.getDescricao());
```



# 9. Enum com comportamento (métodos)

```java
public enum Operacao {
    SOMAR,
    SUBTRAIR;

    public int executar(int a, int b) {
        switch(this) {
            case SOMAR:
                return a + b;
            case SUBTRAIR:
                return a - b;
            default:
                return 0;
        }
    }
}
```

Uso:

```java
System.out.println(Operacao.SOMAR.executar(10, 5));
```



# 10. Enum sobrescrevendo comportamento (avançado)

```java
public enum Desconto {

    BRONZE {
        public double aplicar(double valor) {
            return valor * 0.95;
        }
    },

    PRATA {
        public double aplicar(double valor) {
            return valor * 0.90;
        }
    },

    OURO {
        public double aplicar(double valor) {
            return valor * 0.85;
        }
    };

    public abstract double aplicar(double valor);
}
```

Uso:

```java
System.out.println(Desconto.OURO.aplicar(100));
```



# 11. Métodos prontos do enum



## values()

Retorna todos os valores.

```java
for (Status s : Status.values()) {
    System.out.println(s);
}
```



## valueOf()

Converte texto para enum.

```java
Status s = Status.valueOf("PENDENTE");
```



## name()

Nome original.

```java
System.out.println(Status.PENDENTE.name());
```



## ordinal()

Posição interna (evite depender disso)

```java
System.out.println(Status.PENDENTE.ordinal());
```



# 12. Exemplo Profissional — E-commerce

```java
public enum FormaPagamento {
    PIX,
    CARTAO,
    BOLETO,
    DINHEIRO
}
```

```java
public class Compra {
    private FormaPagamento forma;
}
```



# 13. Exemplo Profissional — Usuários

```java
public enum Perfil {
    ADMIN,
    CLIENTE,
    GERENTE,
    SUPORTE
}
```



# 14. Exemplo Profissional — API Backend

```java
public enum HttpStatusInterno {
    SUCESSO,
    ERRO_VALIDACAO,
    NAO_AUTORIZADO,
    RECURSO_NAO_ENCONTRADO
}
```



# 15. Enum dentro de POO

```java
public class Funcionario {
    private String nome;
    private Cargo cargo;
}
```

```java
public enum Cargo {
    ESTAGIARIO,
    JUNIOR,
    PLENO,
    SENIOR
}
```



# 16. Enum em Collections

```java
List<Status> lista = new ArrayList<>();

lista.add(Status.PENDENTE);
lista.add(Status.APROVADO);
```



# 17. EnumSet (alta performance)

Estrutura otimizada para enums.

```java
EnumSet<Perfil> acessos =
    EnumSet.of(Perfil.ADMIN, Perfil.SUPORTE);
```

Muito usado em sistemas grandes.



# 18. Erros comuns



## Usar String ao invés de enum

Errado:

```java
String tipo = "ADMIN";
```



## Comparar com texto

Errado:

```java
if(status.equals("PAGO"))
```



## Usar ordinal()

Evite:

```java
if(status.ordinal() == 2)
```

Se mudar ordem, quebra tudo.



# 19. Quando usar enum?

Use quando há **lista fixa e conhecida** de opções.

Exemplos:

✅ status
✅ perfil
✅ categoria
✅ prioridade
✅ tipo de usuário
✅ dias semana

Não use para dados variáveis:

❌ nomes de clientes
❌ produtos cadastráveis
❌ cidades dinâmicas



# 20. Enum vs Classe

Use enum quando:

* conjunto fechado
* valores limitados
* constantes com comportamento

Use classe quando:

* muitos objetos dinâmicos
* criados pelo usuário
* persistidos em banco



# 21. Exemplo Completo Realista

```java
public enum StatusChamado {
    ABERTO,
    EM_ANALISE,
    RESOLVIDO,
    FECHADO
}
```

```java
public class Chamado {
    private String titulo;
    private StatusChamado status;

    public Chamado(String titulo) {
        this.titulo = titulo;
        this.status = StatusChamado.ABERTO;
    }

    public void resolver() {
        status = StatusChamado.RESOLVIDO;
    }
}
```



# 22. Exercícios

## Fácil

Crie enum `DiaSemana`.



## Médio

Crie enum `NivelAcesso` com:

* VISITANTE
* USUARIO
* ADMIN



## Médio

Crie enum `Moeda` com símbolo:

* REAL("R$")
* DOLAR("$")
* EURO("€")



## Difícil

Crie enum `PlanoAssinatura` que calcule mensalidade:

* BASIC = 29
* PRO = 59
* PREMIUM = 99

Com método:

```java
double calcularAnual()
```



# 23. Boas práticas de mercado

* nomes em MAIÚSCULO
* singular para nome do enum
* evitar enums gigantes
* colocar comportamento junto
* substituir if/switch excessivo quando possível



# 24. Resumo Final

`enum` em Java:

* é um tipo especial
* representa opções fixas
* evita erros
* melhora legibilidade
* fortalece modelagem OO
* pode ter atributos
* pode ter métodos
* pode ter polimorfismo


