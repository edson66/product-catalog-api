# Product Catalog API

Esta é uma API RESTful para gerenciamento de um catálogo de produtos, desenvolvida com Spring Boot 3, Java 21 e MySQL. A API permite cadastrar, consultar, atualizar e deletar produtos e suas categorias.

## Funcionalidades

* **Autenticação JWT:** Proteção de rotas com tokens JWT.
* **Gerenciamento de Produtos:**
    * Listar todos os produtos.
    * Cadastrar novos produtos com nome, descrição, preço, quantidade e categoria.
    * Detalhar produtos por ID.
    * Atualizar informações de produtos.
    * Deletar produtos.
* **Gerenciamento de Categorias:**
    * Listar todas as categorias.
    * Cadastrar novas categorias.
    * Detalhar categorias por ID.
    * Atualizar informações de categorias.
    * Deletar categorias.
* **Validação de Dados:** Validações de entrada para garantir a integridade dos dados.
* **Dockerização:** Imagens Docker para a API e o banco de dados.
* **CI/CD:** Pipeline de Integração Contínua e Deploy Contínuo configurado com GitHub Actions e Render.

##  Tecnologias Utilizadas

* **Linguagem:** Java 21
* **Framework:** Spring Boot 3
* **Banco de Dados:** MySQL
* **Persistência:** Spring Data JPA, Flyway Migrations
* **Segurança:** Spring Security, JWT
* **Containerização:** Docker, Docker Compose
* **Testes:** JUnit 5, Mockito, Spring Boot Test
* **CI/CD:** GitHub Actions, Render
* **Documentação (futuro):** Swagger/OpenAPI (ainda não implementado)

## Como Rodar Localmente

Para rodar a API na sua máquina local, você precisará ter o Docker e Docker Compose instalados.

1.  **Clone o Repositório:**
    ```bash
    git clone [https://github.com/edison69/product-catalog-api.git](https://github.com/edison69/product-catalog-api.git)
    cd product-catalog-api
    ```

2.  **Inicie o Ambiente com Docker Compose:**
    Este comando irá construir a imagem Docker da sua API e iniciar um contêiner MySQL de desenvolvimento.
    ```bash
    docker-compose up --build -d
    ```
    * Aguarde alguns segundos para o banco de dados e a API subirem completamente.

3.  **Acesse a API:**
    A API estará disponível em `http://localhost:8080`.

    * **Endpoints:**
        * `/produtos`
        * `/categorias`
        * `/auth` (para autenticação e obtenção de token JWT)

4.  **Desligar o Ambiente:**
    Para parar e remover os contêineres:
    ```bash
    docker-compose down
    ```

## Como Rodar os Testes Localmente

Os testes unitários e de integração podem ser executados com o Maven. Eles utilizarão um banco de dados MySQL temporário, gerenciado pelo Docker Compose.

1.  **Certifique-se de que o ambiente Docker Compose está desligado:**
    ```bash
    docker-compose down # Se estiver rodando, desligue
    ```

2.  **Inicie apenas o serviço de banco de dados para testes:**
    ```bash
    docker-compose up -d db # Isso subirá apenas o contêiner MySQL
    ```

3.  **Execute os testes com Maven:**
    ```bash
    mvn clean test
    ```

4.  **Desligue o banco de dados de testes:**
    ```bash
    docker-compose down db
    ```

##  Deploy

Esta API está deployada na plataforma [Render](https://render.com/).

### Processo de CI/CD

O projeto utiliza um pipeline de CI/CD configurado com GitHub Actions:

1.  **Integração Contínua (CI):**
    * Disparado em cada Pull Request para a branch `main`.
    * **Verifica:** Compilação, testes unitários e de integração (rodando contra um MySQL temporário com Docker Compose).
    * **Bloqueia merges:** Se algum teste falhar, o Pull Request não pode ser mesclado na `main`.

2.  **Deploy Contínuo (CD):**
    * Disparado automaticamente após um merge bem-sucedido na branch `main`.
    * Aciona um Deploy Hook no Render, que então puxa o código, constrói a imagem Docker e atualiza a API em produção.

## Contribuições

Contribuições são bem-vindas\! Siga o fluxo:
1.  Faça um Fork do projeto.
2.  Crie uma nova branch (`git switch -c feature/sua-feature`).
3.  Faça suas mudanças e commits (`git commit -m "feat: Adiciona nova feature"`).
4.  Envie suas mudanças (`git push origin feature/sua-feature`).
5.  Abra um Pull Request para a branch `main`.

---

**Desenvolvido por:** Edson Ulisses