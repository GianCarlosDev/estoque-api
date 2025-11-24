📦 Estoque API

API REST desenvolvida em Java 17 com Spring Boot para gerenciar produtos, funcionários e movimentações de estoque, incluindo alertas automáticos para estoque baixo.

🚀 Tecnologias Utilizadas

• Java 17

• Spring Boot

• Spring Web

• Spring Data JPA

• MySQL

• Lombok

📁 Funcionalidades

🔹 Produtos

• Cadastro e listagem de produtos.

• Definição de estoque mínimo e alerta para estoque baixo.

• Endpoints disponíveis:

• POST /produto: Cadastra produto.

• GET /produto: Lista produtos.

• GET /produto/alerta: Lista produtos críticos.

🔹 Funcionários

• Cadastro e listagem de funcionários com cargos específicos.

• Permissões por cargo:

• REPOSITOR: Permite ENTRADA.

• VENDEDOR: Permite SAÍDA.

• Endpoints disponíveis:

• POST /funcionario: Cadastra funcionário.

• GET /funcionario: Lista funcionários.

🔹 Movimentações de Estoque

• Registro de movimentações (ENTRADA e SAÍDA), com validações automáticas de permissões.

• Atualização de estoque e status de alerta.

• Endpoint disponível:

• POST /movimentacao: Registra movimentação.

• Exemplo de JSON:
{
  "codigoP": "PRO123",
  "codigoF": "FUNC001",
  "quantidade": 15,
  "tipo": "ENTRADA"
}

🛠️ Regras de Negócio

• Estoque não pode ser negativo.

• Alertas de estoque recalculados após cada movimentação.

• Restrições específicas por cargo.

• Produtos críticos listados em /produto/alerta.

📊 Estrutura do Projeto:

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

1. Crie o banco no MySQL.

2. Configure o arquivo application.properties.

3. Execute os comandos:

mvn clean install
mvn spring-boot:run

4. Acesse a API em: http://localhost:8080.