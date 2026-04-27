# TRABALHO II - POO 2026 - 1

# Sistema de Gestão de Remoções e Transportes Clínicos

Uma empresa presta serviços para hospitais, clínicas e laboratórios. Ela realiza diferentes tipos de **operações clínicas de transporte**, cada uma com regras próprias de custo, risco, prioridade e execução.

O objetivo é modelar um sistema orientado a objetos em Java que represente essas operações de forma extensível.


## Conceito central

Toda operação possui:

* código
* origem
* destino
* distância em km
* solicitante
* data/hora de solicitação
* status
* veículo designado
* equipe responsável

Status possíveis:

* SOLICITADA
* APROVADA
* EM_EXECUCAO
* CONCLUIDA
* CANCELADA



# Tipos de operação

## 1. Transporte de Medicamento Controlado

Possui:

* nome do medicamento
* exige refrigeração
* temperatura mínima
* temperatura máxima
* exige autorização farmacêutica

Regras:

* custo base: `40 + distância * 2.5`
* refrigeração adiciona `35`
* autorização farmacêutica adiciona `20`
* se exige refrigeração, deve validar se temperatura mínima e máxima foram informadas



## 2. Transporte de Amostra Biológica

Possui:

* tipo da amostra
* nível de urgência
* risco biológico
* prazo máximo em minutos

Urgência:

* BAIXA
* MEDIA
* ALTA
* CRITICA

Regras:

* custo base: `30 + distância * 2`
* urgência ALTA adiciona `40`
* urgência CRITICA adiciona `80`
* risco biológico adiciona `50`
* se prazo máximo for menor que 60 minutos, adiciona `25`



## 3. Transporte de Equipamento Médico

Possui:

* nome do equipamento
* peso em kg
* valor estimado
* exige técnico acompanhante
* exige seguro

Regras:

* custo base: `50 + distância * 1.2`
* peso acima de 100kg adiciona `60`
* técnico acompanhante adiciona `70`
* seguro adiciona `2% do valor estimado`



## 4. Remoção de Paciente

Possui:

* nome do paciente
* idade
* nível clínico
* necessita oxigênio
* necessita UTI móvel
* necessita médico acompanhante

Nível clínico:

* ESTAVEL
* OBSERVACAO
* GRAVE
* CRITICO

Regras:

* custo base: `100 + distância * 4`
* oxigênio adiciona `30`
* UTI móvel adiciona `150`
* médico acompanhante adiciona `120`
* paciente CRITICO obrigatoriamente precisa de UTI móvel ou médico acompanhante



# Veículos

Crie uma hierarquia para veículos.

Todo veículo possui:

* placa
* modelo
* capacidade máxima em kg
* disponível ou indisponível

Tipos:

## Ambulância Simples

* possui maca
* possui oxigênio

## Ambulância UTI

* possui respirador
* possui monitor cardíaco
* possui equipe médica fixa

## Van Refrigerada

* temperatura atual
* suporta controle de temperatura

## Utilitário de Carga

* volume máximo em litros
* possui rampa de acesso



# Equipe

Crie uma classe `Profissional`.

Cada profissional possui:

* nome
* registro profissional
* tipo

Tipo profissional:

* MOTORISTA
* ENFERMEIRO
* MEDICO
* FARMACEUTICO
* TECNICO_EQUIPAMENTO

Uma operação pode ter vários profissionais na equipe.



# Regras de compatibilidade

Cada operação deve conseguir validar se o veículo e a equipe são compatíveis.

Exemplos:

## Medicamento Controlado

* se exige refrigeração, veículo deve ser `VanRefrigerada`
* se exige autorização farmacêutica, equipe deve possuir `FARMACEUTICO`

## Amostra Biológica

* se risco biológico for verdadeiro, equipe deve possuir pelo menos um `ENFERMEIRO`
* urgência CRITICA exige veículo disponível e equipe com motorista

## Equipamento Médico

* se peso for maior que 100kg, veículo precisa suportar o peso
* se exige técnico acompanhante, equipe deve possuir `TECNICO_EQUIPAMENTO`

## Remoção de Paciente

* se necessita UTI móvel, veículo deve ser `AmbulanciaUTI`
* se necessita oxigênio, veículo deve possuir oxigênio
* se nível clínico for GRAVE ou CRITICO, equipe deve possuir `MEDICO`



# Interfaces obrigatórias

## `Custeavel`

```java
double calcularCusto();
```

## `Auditavel`

```java
String gerarLogAuditoria();
```

## `Priorizavel`

```java
int calcularPrioridade();
```

## `Validavel`

```java
boolean validar();
```

## `Rastreavel`

```java
String obterDescricaoRastreamento();
```



# Herança obrigatória

Crie uma classe abstrata:

```java
OperacaoClinica
```

Ela deve implementar interfaces comuns e conter os atributos compartilhados.

Cada tipo de operação deve herdar dela.

Crie também uma classe abstrata:

```java
Veiculo
```

Cada veículo específico deve herdar dela.



# Polimorfismo obrigatório

No `main`, crie uma lista:

```java
List<OperacaoClinica> operacoes = new ArrayList<>();
```

Inclua objetos de todos os tipos.

Depois percorra a lista e execute:

* validação
* cálculo de custo
* cálculo de prioridade
* geração de log
* exibição de rastreamento
* mudança de status

O `main` não deve conter decisões específicas do tipo:

```java
if (operacao instanceof RemocaoPaciente)
```

A lógica deve estar dentro das classes.



# Regras de status

Implemente métodos na classe base:

```java
aprovar()
iniciar()
concluir()
cancelar()
```

Regras:

* só pode aprovar se estiver SOLICITADA
* só pode iniciar se estiver APROVADA
* só pode concluir se estiver EM_EXECUCAO
* não pode cancelar uma operação CONCLUIDA
* não pode iniciar uma operação inválida



# Requisitos técnicos obrigatórios

A solução deve conter:

* classe abstrata
* herança entre operações
* herança entre veículos
* interfaces
* polimorfismo
* encapsulamento
* sobrescrita de métodos
* enums
* composição
* listas de objetos
* validações distribuídas nas classes
* ausência de `instanceof` no `main`



# Entregável

O aluno deve entregar o projeto Java contendo:

* classes de domínio
* enums
* interfaces
* classe principal com simulação
* pelo menos 8 operações diferentes criadas no `main`
* pelo menos 4 veículos
* pelo menos 6 profissionais

