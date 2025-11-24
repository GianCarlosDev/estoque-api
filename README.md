📘 Documentação Técnica – Estoque API

1. Visão Geral do Projeto

A Estoque API é uma aplicação REST desenvolvida em Java 17 utilizando Spring Boot, criada para gerenciar produtos, funcionários e movimentações de estoque (entrada e saída).

A API também possui lógica automática para alerta de estoque baixo, ativado quando a quantidade fica abaixo do mínimo definido.

O projeto segue Arquitetura em Camadas:

Controller → Service → Repository

2. Tecnologias e Dependências
	•	Java 17
	•	Spring Boot
	•	Spring Web
	•	Spring Data JPA
	•	MySQL
	•	Lombok

3. Modelos (Entities)

3.1. Produto

Representa um item no estoque.

Atributos principais:
	•	id
	•	nome
	•	codigo
	•	estoque
	•	estoqueMinimo
	•	alerta (boolean)
	•	dataCadastro

Regras:
	•	alerta = true quando estoque < estoqueMinimo
	•	Estoque nunca pode ser negativo

3.2. Funcionario

Representa um trabalhador do estabelecimento.

Atributos principais:
	•	id
	•	nome
	•	codigo
	•	cargo

Cargos existentes:
	•	REPOSITOR → pode realizar ENTRADA
	•	VENDEDOR → pode realizar SAÍDA

3.3. Movimentacao

Representa uma operação de entrada ou saída.

Atributos principais:
	•	id
	•	tipo (ENTRADA ou SAÍDA)
	•	quantidade
	•	dataHora
	•	produto
	•	funcionario

Regras:
	•	VENDEDOR só registra SAÍDA
	•	REPOSITOR só registra ENTRADA
	•	Atualiza automaticamente o estoque do produto
	•	Após atualizar o estoque, recalcula o campo alerta

4. Camada Controller

4.1. ProdutoController

Método	Rota	Descrição
POST	/produto	Cadastrar produto
GET	/produto	Listar produtos
GET	/produto/alerta	Listar produtos com estoque abaixo do mínimo

4.2. FuncionarioController

Método	Rota	Descrição
POST	/funcionario	Cadastrar funcionário
GET	/funcionario	Listar funcionários

4.3. MovimentacaoController

Método	Rota	Descrição
POST	/movimentacao	Registrar entrada ou saída

Exemplo JSON:

{
  "codigoP": "PRO123",
  "codigoF": "FUNC001",
  "quantidade": 10,
  "tipo": "SAIDA"
}

5. Regras de Negócio

✔ Estoque
	•	Nunca pode ser negativo
	•	Movimentações:
	•	ENTRADA → soma ao estoque
	•	SAÍDA → subtrai do estoque
	•	Após atualizar o estoque:
	•	alerta = estoque < estoqueMinimo

✔ Permissões por Cargo

Cargo	Permissão
REPOSITOR	ENTRADA
VENDEDOR	SAÍDA

Tentativas inválidas geram exceção.

✔ Movimentações
	•	Funcionário deve existir
	•	Produto deve existir
	•	Quantidade deve ser maior que zero
	•	Atualiza automaticamente:
	•	Estoque
	•	Alerta
	•	Data/hora

6. Fluxo de Funcionamento
	1.	Cadastrar funcionários
Informando o cargo (REPOSITOR ou VENDEDOR).
	2.	Cadastrar produtos
Informando estoque mínimo.
	3.	Registrar movimentações
	•	Valida cargo x tipo
	•	Atualiza estoque
	•	Recalcula alerta
	4.	Consultar produtos críticos
	•	/produto/alerta mostra itens com estoque baixo

7. Estrutura de Pastas

src/
 └── main/
     ├── java/
     │   └── br.com.estoque/
     │       ├── controller/
     │       ├── service/
     │       ├── repository/
     │       └── model/
     └── resources/
         └── application.properties