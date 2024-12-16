package org.example;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "preco")
    private Double preco;

    @Column(name = "quantidade")
    private int quantidade;

    @Column(name = "categoria") // Novo campo para a categoria do produto
    private String categoria;

    @Column(name = "descricao") // Novo campo para a descrição do produto
    private String descricao;

    @Column(name = "imagem") // Novo campo para a URL da imagem do produto
    private String imagem;

    // Construtor sem parâmetros
    public Produto() {
    }

    // Construtor com parâmetros, agora incluindo categoria, descrição e imagem
    public Produto(String nome, double preco, int quantidade, String categoria, String descricao, String imagem) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.categoria = categoria;
        this.descricao = descricao;
        this.imagem = imagem;
    }

    // Construtor com parâmetros incluindo 'id'
    public Produto(Integer id, String nome, double preco, int quantidade, String categoria, String descricao,
            String imagem) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.categoria = categoria;
        this.descricao = descricao;
        this.imagem = imagem;
    }

    // Getters e Setters para os novos campos

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    // Métodos de acesso existentes

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    // Sobrescrita do método toString() para incluir os novos campos
    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", preco=" + String.format("%.2f", preco) +
                ", quantidade=" + quantidade +
                ", categoria='" + categoria + '\'' +
                ", descricao='" + descricao + '\'' +
                ", imagem='" + imagem + '\'' +
                '}';
    }
}
