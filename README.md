📦 Estoque API

API desenvolvida em Java 17 + Spring Boot para gerenciamento de produtos, funcionários e movimentações de estoque (entrada e saída), incluindo alerta automático para produtos com estoque abaixo do mínimo.

🚀 Tecnologias Utilizadas
	•	Java 17
	•	Spring Boot
	•	Spring Web
	•	Spring Data JPA
	•	MySQL
	•	Lombok

📁 Estrutura do Projeto

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

📌 Endpoints Principais

🧩 Produto

Método	Rota	Descrição
POST	/produto	Cadastrar produto
GET	/produto	Listar produtos
GET	/produto/alerta	Listar produtos com estoque baixo

👥 Funcionário

Método	Rota	Descrição
POST	/funcionario	Cadastrar funcionário
GET	/funcionario	Listar funcionários

Cargos permitidos:
	•	REPOSITOR → registra ENTRADA
	•	VENDEDOR → registra SAÍDA

🔄 Movimentação

Método	Rota	Descrição
POST	/movimentacao	Registrar entrada ou saída

Exemplo de requisição:

{
  "codigoP": "PRO123",
  "codigoF": "FUNC001",
  "quantidade": 10,
  "tipo": "SAIDA"
}

⚙️ Regras de Negócio

🔸 Estoque
	•	Não pode ser negativo
	•	ENTRADA → adiciona ao estoque
	•	SAÍDA → subtrai do estoque
	•	Alerta é ativado quando estoque < estoqueMinimo

🔸 Permissões
	•	VENDEDOR → somente SAÍDA
	•	REPOSITOR → somente ENTRADA

