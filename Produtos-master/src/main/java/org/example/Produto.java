package org.example;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity // Define esta classe como uma entidade JPA (Java Persistence API),
        // representando uma tabela no banco de dados
@Table(name = "produtos") // Especifica o nome da tabela no banco de dados associada a esta classe
public class Produto {

    @Id // Define que este atributo é a chave primária da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indica que o valor da chave primária será gerado
                                                        // automaticamente (auto incremento no banco de dados)
    @Column(name = "id") // Mapeia o atributo 'id' para a coluna 'id' da tabela 'produtos'
    private Integer id;

    @Column(name = "nome") // Mapeia o atributo 'nome' para a coluna 'nome' da tabela 'produtos'
    private String nome;

    @Column(name = "preco") // Mapeia o atributo 'preco' para a coluna 'preco' da tabela 'produtos'
    private Double preco;

    @Column(name = "quantidade") // Mapeia o atributo 'quantidade' para a coluna 'quantidade' da tabela
                                 // 'produtos'
    private int quantidade;

    // Construtor sem parâmetros (necessário para o Hibernate, pois o JPA precisa de
    // um construtor sem argumentos para criar o objeto)
    public Produto() {
    }

    // Construtor com parâmetros (facilita a criação de novos produtos, permitindo
    // definir nome, preço e quantidade)
    public Produto(String nome, double preco, int quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    // Construtor com parâmetros incluindo 'id' (útil para criar um produto com um
    // id específico, normalmente ao atualizar um produto)
    public Produto(Integer id, String nome, double preco, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    // Métodos de acesso (getters) e modificação (setters) para os atributos da
    // classe

    public Integer getId() {
        return id;
    }

    // Método para obter o nome do produto
    public String getNome() {
        return nome;
    }

    // Método para definir o nome do produto
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Método para obter o preço do produto
    public Double getPreco() {
        return preco;
    }

    // Método para definir o preço do produto
    public void setPreco(Double preco) {
        this.preco = preco;
    }

    // Método para obter a quantidade do produto
    public int getQuantidade() {
        return quantidade;
    }

    // Método para definir a quantidade do produto
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    // Sobrescrita do método toString() para exibir uma representação legível do
    // produto (útil para depuração e exibição)
    @Override
    public String toString() {
        // Retorna uma string com os detalhes do produto, formatando o preço para duas
        // casas decimais
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", preco=" + String.format("%.2f", preco) + // Formatação do preço com 2 casas decimais
                ", quantidade=" + quantidade +
                '}';
    }
}
