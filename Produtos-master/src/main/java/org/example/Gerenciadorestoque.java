package org.example;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import static org.hibernate.cfg.JdbcSettings.FORMAT_SQL;
import static org.hibernate.cfg.JdbcSettings.HIGHLIGHT_SQL;
import static org.hibernate.cfg.JdbcSettings.PASS;
import static org.hibernate.cfg.JdbcSettings.SHOW_SQL;
import static org.hibernate.cfg.JdbcSettings.URL;
import static org.hibernate.cfg.JdbcSettings.USER;

public class Gerenciadorestoque {

    // Cria a conexão com o banco de dados usando Hibernate
    private SessionFactory configuration = new Configuration()
            .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect")
            .addAnnotatedClass(Produto.class) // Produto
            .addAnnotatedClass(Usuario.class) // Usuario
            .setProperty(URL, "jdbc:mysql://localhost:3306/banco_produtos")
            .setProperty(USER, "root")
            .setProperty(PASS, "")
            .setProperty("hibernate.agroal.maxSize", 20)
            .setProperty(SHOW_SQL, true)
            .setProperty(FORMAT_SQL, true)
            .setProperty(HIGHLIGHT_SQL, true)
            .buildSessionFactory();

    public boolean registerUser(String username, String password, String email) {
        try (Session session = configuration.openSession()) {
            session.beginTransaction();

            // Criação de um novo usuário
            Usuario usuario = new Usuario();
            usuario.setUsername(username);
            usuario.setPassword(password);
            usuario.setEmail(email); // Atribui o email

            // Salvar o novo usuário no banco de dados
            session.save(usuario);

            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para cadastrar um usuário
    public void addUsuario(Usuario usuario) {
        try (Session session = configuration.openSession()) {
            session.beginTransaction();
            session.persist(usuario);
            session.getTransaction().commit();
        }
    }

    // Método para validar login
    public boolean validateLogin(String username, String password) {
        try (Session session = configuration.openSession()) {
            session.beginTransaction();
            // Busca o usuário no banco com o nome de usuário fornecido
            Usuario usuario = session.createQuery("FROM Usuario WHERE username = :username", Usuario.class)
                    .setParameter("username", username)
                    .uniqueResult();
            session.getTransaction().commit();
            // Verifica se a senha bate
            return usuario != null && usuario.getPassword().equals(password);
        }
    }

    // Método para adicionar um produto no banco de dados
    public void addProduto(Produto produto) {
        // Abre uma nova sessão com o banco de dados
        try (Session session = configuration.openSession()) {
            // Inicia a transação
            session.beginTransaction();
            // Persiste o produto na base de dados
            session.persist(produto);
            // Comita a transação para salvar as mudanças no banco
            session.getTransaction().commit();
        }
    }

    // Método para excluir um produto pelo seu ID
    public void deleteProduto(Integer id) {
        // Abre uma nova sessão com o banco de dados
        try (Session session = configuration.openSession()) {
            // Inicia a transação
            session.beginTransaction();
            // Busca o produto com o ID fornecido
            Produto produto = session.get(Produto.class, id);
            // Se o produto existir, realiza a exclusão
            if (produto != null) {
                session.delete(produto);
            }
            // Comita a transação para salvar a exclusão no banco
            session.getTransaction().commit();
        }
    }

    // Método para obter um produto pelo seu ID
    public Produto getProduto(Integer id) {
        // Abre uma nova sessão com o banco de dados
        try (Session session = configuration.openSession()) {
            // Retorna o produto com o ID fornecido, se existir
            return session.get(Produto.class, id);
        }
    }

    // Método para atualizar um produto no banco de dados
    public Produto updateProduto(Integer id, Produto produto) {
        // Abre uma nova sessão com o banco de dados
        try (Session session = configuration.openSession()) {
            // Inicia a transação
            session.beginTransaction();
            // Busca o produto existente pelo ID
            Produto produtoExistente = session.get(Produto.class, id);
            // Se o produto existir, atualiza seus dados
            if (produtoExistente != null) {
                produtoExistente.setNome(produto.getNome());
                produtoExistente.setPreco(produto.getPreco());
                produtoExistente.setQuantidade(produto.getQuantidade());
                // Atualiza o produto no banco de dados
                session.update(produtoExistente);
            }
            // Comita a transação para salvar as mudanças
            session.getTransaction().commit();
            return produtoExistente;
        }
    }

    // Método para listar todos os produtos cadastrados
    public List<Produto> listAllProdutos() {
        // Abre uma nova sessão com o banco de dados
        try (Session session = configuration.openSession()) {
            // Inicia a transação
            session.beginTransaction();
            // Consulta todos os produtos no banco de dados
            List<Produto> produtos = session.createQuery("FROM Produto", Produto.class).getResultList();
            // Comita a transação
            session.getTransaction().commit();
            return produtos;
        }
    }

    // Método para buscar produtos pelo nome (parcial)
    public List<Produto> searchProdutoByName(String nome) {
        // Abre uma nova sessão com o banco de dados
        try (Session session = configuration.openSession()) {
            // Inicia a transação
            session.beginTransaction();
            // Consulta produtos cujo nome contenha o termo fornecido
            List<Produto> produtos = session.createQuery("FROM Produto WHERE nome LIKE :nome", Produto.class)
                    .setParameter("nome", "%" + nome + "%")
                    .getResultList();
            // Comita a transação
            session.getTransaction().commit();
            return produtos;
        }
    }
}
