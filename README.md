📦 Estoque API

API REST desenvolvida em Java + Spring Boot para controle de estoque, permitindo o gerenciamento de produtos, funcionários e movimentações de entrada/saída, além de gerar automaticamente alertas para produtos com estoque baixo.

🚀 Tecnologias Utilizadas
	•	Java 17
	•	Spring Boot
	•	Spring Web
	•	Spring Data JPA
	•	MySQL
	•	Lombok

📁 Funcionalidades

🔹 Produtos
	•	Cadastro de novos produtos
	•	Listagem de produtos
	•	Definição de estoque mínimo
	•	Geração automática de alerta quando o estoque está abaixo do mínimo
	•	Endpoint dedicado para produtos críticos

Endpoints

Método	Endpoint	Descrição
POST	/produto	Cadastrar novo produto
GET	/produto	Listar todos os produtos
GET	/produto/alerta	Listar produtos com estoque abaixo do mínimo

🔹 Funcionários
	•	Cadastro de funcionários
	•	Listagem de funcionários
	•	Funcionários possuem cargos, e cada cargo define o tipo de movimentação permitida

Regras de Permissão

Cargo	Permissão
REPOSITOR	Movimentações de entrada
VENDEDOR	Movimentações de saída

Endpoints

Método	Endpoint	Descrição
POST	/funcionario	Cadastrar funcionário
GET	/funcionario	Listar funcionários

🔹 Movimentações
	•	Registro de movimentações de ENTRADA ou SAÍDA
	•	Validação automática baseada no cargo do funcionário
	•	Atualização automática do estoque do produto
	•	Atualização do status de alerta do produto

Endpoint

Método	Endpoint	Descrição
POST	/movimentacao	Registrar movimentação de estoque

Exemplo de JSON

{
  "codigoP": "PRO123",
  "codigoF": "FUNC001",
  "quantidade": 15,
  "tipo": "ENTRADA"
}

🛠️ Regras de Negócio
	•	Estoque nunca pode ficar negativo
	•	O alerta é recalculado a cada movimentação
	•	Funcionário só pode registrar movimentações permitidas pelo seu cargo
	•	Produtos críticos são automaticamente listados em /produto/alerta

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


