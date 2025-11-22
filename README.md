# 🏪 Sistema de Controle de Estoque – Spring Boot

Projeto sobre **controle de estoque**, incluindo cadastro de produtos, movimentação de entrada/saída, alertas automáticos de estoque baixo e listagem de produtos críticos.

---

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **Spring Web**
- **Spring Data JPA**
- **MySQL**
- **Lombok**

---

## 📦 Estrutura das Funcionalidades

### ✔️ Produtos
- Cadastro, edição e listagem.
- Controle de quantidade mínima.
- Campo `alerta` gerado automaticamente quando o estoque está baixo.

- ### ✔️ Funcionários
O sistema possui controle básico de funcionários para garantir regras de negócio:

- Funcionário possui **cargo** (ex.: `REPOSITOR`, `VENDEDOR`).
- Apenas **REPOSITOR** está autorizado a realizar **ENTRADA** de estoque.
- Apenas **ATENDENTE** está autorizado a realizar **SAÍDA** de estoque.
- A movimentação registra o código do funcionário responsável.
- Caso o funcionário tente realizar uma movimentação não permitida, o sistema lança erro.

### ✔️ Movimentação
- Tipos: **ENTRADA** e **SAÍDA**.
- Apenas **repositorio** executa *entrada*.
- Apenas **vendedor** executa *saída*.
- Após cada movimentação, o sistema verifica se o estoque ficou baixo.

### ✔️ Alertas
- Produto recebe `"ESTOQUE BAIXO"` automaticamente.
- Endpoint dedicado lista só os produtos críticos.

---

## 📁 Endpoints Principais

### 🔹 Produtos

`GET /produto`  
Lista todos os produtos.

`GET /produto/alerta`  
Lista somente produtos com estoque baixo.

`POST /produto`  
Cadastro de novo produto.

`POST /funcionario` 
Cria novo funcionario.

`GET /funcionario`  
Lista todos os funcionarios.

---

### 🔹 Movimentações

`POST /movimentacao`  
Cria uma movimentação de entrada ou saída.

Exemplo de JSON:

```json
{
  "codigoP": "PRO123",
  "codigoF": "FORNEC001",
  "quantidade": 15,
  "tipo": "ENTRADA"
}
