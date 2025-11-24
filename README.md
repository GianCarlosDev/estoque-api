📦 Estoque API

API REST para controle de estoque desenvolvida em Java 17 com Spring Boot, permitindo gerenciar produtos, funcionários e movimentações de entrada/saída, além de identificar automaticamente produtos com estoque baixo.

🚀 Tecnologias Utilizadas
	•	Java 17
	•	Spring Boot
	•	Spring Web
	•	Spring Data JPA
	•	MySQL
	•	Lombok

📁 Funcionalidades

🔹 Produtos
	•	Cadastro de produtos
	•	Listagem geral
	•	Definição de estoque mínimo
	•	Alerta automático de estoque baixo
	•	Endpoint para listar produtos críticos

Endpoints de Produto

Método	Endpoint	Descrição
POST	/produto	Cadastrar produto
GET	/produto	Listar produtos
GET	/produto/alerta	Listar produtos com estoque baixo

🔹 Funcionários
	•	Cadastro de funcionários
	•	Listagem de funcionários
	•	Cada funcionário possui um cargo
	•	Cargos e permissões:
	•	REPOSITOR → permite ENTRADA
	•	VENDEDOR → permite SAÍDA

Endpoints de Funcionário

Método	Endpoint	Descrição
POST	/funcionario	Cadastrar funcionário
GET	/funcionario	Listar funcionários

🔹 Movimentações de Estoque
	•	Registro de movimentações de ENTRADA ou SAÍDA
	•	Validação automática de permissão pelo cargo
	•	Atualiza o estoque
	•	Atualiza o status de alerta do produto

Endpoint de Movimentação

Método	Endpoint	Descrição
POST	/movimentacao	Registrar movimentação

Exemplo de JSON

{
  "codigoP": "PRO123",
  "codigoF": "FUNC001",
  "quantidade": 15,
  "tipo": "ENTRADA"
}

🛠️ Regras de Negócio
	•	Estoque não pode ficar negativo
	•	Alerta de estoque é recalculado após cada movimentação
	•	REPOSITOR só registra entrada
	•	VENDEDOR só registra saída
	•	Produtos com estoque baixo aparecem em /produto/alerta

📊 Estrutura do Projeto

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

▶️ Como Executar
	1.	Crie o banco no MySQL
	2.	Configure o application.properties
	3.	Execute:

mvn clean install
mvn spring-boot:run

A API subirá em:

http://localhost:8080

