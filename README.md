# Gerenciador de Produtos

**Gerenciador de Produtos** é uma aplicação simples desenvolvida em Java, utilizando Hibernate para interação com banco de dados, permitindo realizar operações básicas de CRUD (Criar, Ler, Atualizar e Deletar) em uma tabela de produtos.

Este projeto foi desenvolvido com o objetivo de demonstrar uma implementação simples de um sistema de gerenciamento de produtos, utilizando o Hibernate para persistência de dados e a interação com um banco de dados MySQL.

## Funcionalidades

- **Adicionar Produtos**: Permite a criação de novos produtos com nome, preço e quantidade.
- **Excluir Produtos**: Permite a remoção de produtos existentes da base de dados.
- **Atualizar Produtos**: Permite a edição de dados de produtos, como nome, preço e quantidade.
- **Listar Produtos**: Exibe todos os produtos cadastrados no banco de dados.
- **Pesquisar Produtos**: Permite buscar produtos pelo nome, utilizando uma busca com base parcial.

## Tecnologias Utilizadas

- **Java**: Linguagem principal para desenvolvimento da aplicação.
- **Hibernate**: Framework ORM para mapeamento objeto-relacional, utilizado para persistir e recuperar dados do banco de dados.
- **MySQL**: Banco de dados utilizado para armazenar as informações dos produtos.
- **JPA (Jakarta Persistence API)**: API para gerenciamento de dados relacionais, integrando com o Hibernate.
- **SQL**: Linguagem utilizada para interagir com o banco de dados.

## Estrutura do Projeto

O projeto contém as seguintes classes principais:

- **Produto.java**: Representa a entidade `Produto`, com os atributos `id`, `nome`, `preco` e `quantidade`. Esta classe está anotada com JPA para mapear os atributos para a tabela `produtos` no banco de dados.
- **Gerenciadorestoque.java**: Responsável pela lógica de interação com o banco de dados, implementando as operações CRUD para a classe `Produto`.

## Requisitos

Para rodar este projeto localmente, você precisa ter:

- **Java 8 ou superior** instalado.
- **MySQL** instalado e configurado.
- **IDE** (como IntelliJ IDEA ou Eclipse) para compilar e rodar o código.
- Dependências do Hibernate configuradas no `pom.xml` (se usando Maven) ou `build.gradle` (se usando Gradle).

## Como Rodar o Projeto

1. **Clone o repositório**:
   ```
   git clone https://github.com/seuusuario/Gerenciador_de_Produtos.git
   ```

2. **Configuração do Banco de Dados**:
   - Crie um banco de dados no MySQL chamado `banco_produtos` ou altere a URL de conexão no código para o nome de seu banco de dados.
   - Exemplo de configuração no código:
     ```java
     .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/banco_produtos")
     ```

3. **Adicione dependências**:
   - Se você estiver usando o Maven, adicione as dependências do Hibernate no `pom.xml`.
   - Se usar Gradle, adicione as dependências no `build.gradle`.

4. **Compile e execute o projeto**:
   - Compile o código através de sua IDE ou com o comando Maven/Gradle.
   - Execute a classe `Gerenciadorestoque` para iniciar a aplicação.

## Exemplos de Uso

### Adicionar um Produto
```java
Produto novoProduto = new Produto("Produto Exemplo", 25.50, 10);
gerenciador.addProduto(novoProduto);
```

### Atualizar um Produto
```java
Produto produtoExistente = new Produto(1, "Produto Atualizado", 30.00, 15);
gerenciador.updateProduto(1, produtoExistente);
```

### Excluir um Produto
```java
gerenciador.deleteProduto(1);
```

### Listar Todos os Produtos
```java
List<Produto> produtos = gerenciador.listAllProdutos();
produtos.forEach(System.out::println);
```

### Pesquisar Produtos por Nome
```java
List<Produto> produtosEncontrados = gerenciador.searchProdutoByName("Produto");
produtosEncontrados.forEach(System.out::println);
```

## Contribuição

Se você quiser contribuir para o desenvolvimento deste projeto, sinta-se à vontade para abrir uma **pull request** com suas melhorias ou sugestões.

## Licença

Este projeto é licenciado sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---
