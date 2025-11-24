# 📦 Estoque API  
API REST desenvolvida em **Spring Boot** para gerenciamento de estoque — produtos, funcionários e movimentações, com alerta automático quando o estoque fica baixo.

---

## 🚀 Tecnologias
- **Java 17**  
- **Spring Boot** (Web, Data JPA)  
- **MySQL**  
- **Lombok**  

---

## 🧩 O que essa API faz?
- Cadastro e consulta de **produtos**
- Registro de **movimentações** (entrada e saída)
- Controle de **funcionários** com regras por cargo:  
  - Repositor → pode fazer **entrada**  
  - Vendedor → pode fazer **saída**  
- Alerta automático quando o estoque fica abaixo do mínimo
- Endpoint dedicado para listar produtos críticos

---

## 🛠️ Como rodar o projeto
    git clone https://github.com/GianCarlosDev/estoque-api.git
    cd estoque-api
    ./mvnw spring-boot:run

Certifique-se de configurar o MySQL no `application.properties`.

---

## 📂 Estrutura do projeto
src/
├─ main/
│ ├─ java/
│ │ └─ com/giancarlosdev/estoque/
│ │ ├─ controller/ → Endpoints da API
│ │ ├─ service/ → Regras de negócio
│ │ ├─ repository/ → Repositórios JPA
│ │ ├─ model/ → Entidades (Produto, Funcionário, Movimentação)
│ │ ├─ dto/ → Objetos de transferência
│ │ └─ EstoqueApi.java → Classe principal
│ └─ resources/
│ ├─ application.properties → Configurações
│ └─ data.sql (opcional) → Seeds
└─ test/ → Testes futuros

---

## 🧪 Exemplos de endpoints

### Criar movimentação
    POST /movimentacao
    {
      "codigoP": "PRO123",
      "codigoF": "FUNC001",
      "quantidade": 15,
      "tipo": "ENTRADA"
    }

### Listar produtos com alerta
    GET /produto/alerta

---

## 📌 Roadmap (melhorias futuras)
- 🔐 Autenticação e autorização (JWT)  
- 🧪 Testes automatizados  
- 📄 Swagger/OpenAPI  
- 🐳 Dockerizar projeto  
- 📊 Paginação e filtros nas listagens  
- 📬 Notificações automáticas  

---

## Alunos
**Gian Carlos, Luís Gustavo, Pedro Henrique
Gustavo Barros, Carlos Geovane e Adrysson**
