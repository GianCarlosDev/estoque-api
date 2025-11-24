📦Estoque API

API REST para controle de estoque desenvolvida em Java 17 com Spring Boot, permitindo gerenciar produtos, funcionários e movimentações de entrada/saída, além de identificar automaticamente produtos com estoque baixo.

Tecnologias
	•	Java 17
	•	Spring Boot
	•	Spring Web
	•	Spring Data JPA
	•	MySQL
	•	Lombok

Funcionalidades
	•	Produtos
	•	Criar novos produtos
	•	Listar produtos
	•	Definir um estoque mínimo para cada produto
	•	Gerar alerta quando o estoque está abaixo do mínimo
	•	Endpoint para listar produtos críticos (com alerta)
	•	Funcionários
	•	Cadastrar funcionário
	•	Listar funcionários
	•	Cada funcionário tem um cargo (REPOSITOR ou VENDEDOR)
	•	Permissões por cargo:
	•	REPOSITOR → pode fazer entrada
	•	VENDEDOR → pode fazer saída
	•	Movimentações de Estoque
	•	Registrar movimentação do tipo ENTRADA ou SAÍDA
	•	Validar se o funcionário tem permissão para o tipo de movimentação
	•	Ao registrar movimentação, o sistema ajusta o estoque do produto
	•	Recalcula alerta de estoque baixo para o produto

API – Endpoints

Método	Rota	Descrição
POST	/produto	Cadastrar um novo produto
GET	/produto	Listar todos os produtos
GET	/produto/alerta	Listar produtos com estoque baixo
POST	/funcionario	Cadastrar funcionário
GET	/funcionario	Listar funcionários
POST	/movimentacao	Registrar movimentação de estoque

Exemplo JSON para movimentação:

{
  "codigoP": "PRO123",
  "codigoF": "FUNC001",
  "quantidade": 15,
  "tipo": "ENTRADA"
}

Regras de Negócio
	•	O estoque de um produto não pode ficar negativo.
	•	Quando ocorre uma movimentação, o sistema recalcula se deve ativar ou desativar alerta de estoque baixo.
	•	A permissão para movimentação depende do cargo do funcionário: repositor só entra; vendedor só sai.
	•	Os produtos com estoque em alerta são disponibilizados via endpoint /produto/alerta.

Estrutura do Projeto

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

Como Rodar Localmente
	1.	Configure um banco MySQL (ou ajuste para outro DB no application.properties).
	2.	Crie o schema / banco no MySQL para a aplicação.
	3.	No projeto, ajuste as credenciais de conexão com banco em src/main/resources/application.properties.
	4.	Compile e rode a aplicação com Maven ou sua IDE:

mvn clean install  
mvn spring-boot:run  


	5.	A API ficará disponível (por exemplo) em http://localhost:8080