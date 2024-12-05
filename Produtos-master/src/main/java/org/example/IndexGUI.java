package org.example;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class IndexGUI extends JFrame {
    private Gerenciadorestoque gerenciador;
    private JTextField nomeField, precoField, quantidadeField, idField, searchField;
    private JTextArea outputArea;

    public IndexGUI() {
        // Inicializa o gerenciador de estoque
        gerenciador = new Gerenciadorestoque();
        
        // Configuração da janela
        setTitle("Gerenciador de Estoque");
        setSize(500, 500); // Aumenta o tamanho da janela para dar mais espaço
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout da interface
        setLayout(new BorderLayout());

        // Painel de entrada (campo de texto para o nome, preço, quantidade e busca)
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 2)); // Adiciona mais uma linha para o campo de busca

        inputPanel.add(new JLabel("Nome do Produto:"));
        nomeField = new JTextField();
        inputPanel.add(nomeField);

        inputPanel.add(new JLabel("Preço do Produto:"));
        precoField = new JTextField();
        inputPanel.add(precoField);

        inputPanel.add(new JLabel("Quantidade do Produto:"));
        quantidadeField = new JTextField();
        inputPanel.add(quantidadeField);

        inputPanel.add(new JLabel("Buscar por Nome:"));
        searchField = new JTextField();
        inputPanel.add(searchField);

        // Adiciona o painel de entrada no layout
        add(inputPanel, BorderLayout.NORTH);

        // Área de saída (para mostrar o status das operações)
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.CENTER);

        // Painel de botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 6)); // Aumenta a grid para incluir o botão de buscar

        // Botões de ação (adicionar, remover, listar, editar, buscar e sair)
        JButton addButton = new JButton("Adicionar Produto");
        addButton.addActionListener(new AddButtonListener());
        buttonPanel.add(addButton);

        JButton removeButton = new JButton("Remover Produto");
        removeButton.addActionListener(new RemoveButtonListener());
        buttonPanel.add(removeButton);

        JButton listButton = new JButton("Listar Produtos");
        listButton.addActionListener(new ListButtonListener());
        buttonPanel.add(listButton);

        JButton editButton = new JButton("Editar Produto");
        editButton.addActionListener(new EditButtonListener());
        buttonPanel.add(editButton);

        JButton searchButton = new JButton("Buscar Produto");
        searchButton.addActionListener(new SearchButtonListener());
        buttonPanel.add(searchButton);

        JButton exitButton = new JButton("Sair");
        exitButton.addActionListener(e -> System.exit(0));
        buttonPanel.add(exitButton);

        // Adiciona o painel de botões no layout
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Listener do botão Adicionar Produto
    private class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Coleta os dados dos campos
                String nome = nomeField.getText();
                Double preco = Double.parseDouble(precoField.getText());
                int quantidade = Integer.parseInt(quantidadeField.getText());
                Produto produto = new Produto(nome, preco, quantidade);

                // Adiciona o produto ao gerenciador de estoque
                gerenciador.addProduto(produto);
                outputArea.setText("Produto adicionado: " + produto);
            } catch (NumberFormatException ex) {
                outputArea.setText("Erro: Preço e quantidade devem ser números válidos.");
            }
        }
    }

    // Listener do botão Remover Produto
    private class RemoveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Exibe uma caixa de diálogo para o ID ou nome do produto
            String input = JOptionPane.showInputDialog(
                IndexGUI.this, 
                "Digite o ID ou Nome do Produto a ser removido:", 
                "Remover Produto", 
                JOptionPane.QUESTION_MESSAGE);

            if (input != null && !input.trim().isEmpty()) {
                try {
                    // Tenta interpretar o input como um ID (número)
                    int id = Integer.parseInt(input.trim());
                    gerenciador.deleteProduto(id);
                    outputArea.setText("Produto removido com ID: " + id);
                } catch (NumberFormatException ex) {
                    // Caso não seja um número, tenta buscar pelo nome
                    List<Produto> produtos = gerenciador.searchProdutoByName(input.trim());
                    if (!produtos.isEmpty()) {
                        Produto produto = produtos.get(0);
                        gerenciador.deleteProduto(produto.getId());
                        outputArea.setText("Produto removido: " + produto);
                    } else {
                        outputArea.setText("Produto não encontrado pelo nome.");
                    }
                }
            } else {
                outputArea.setText("Entrada inválida. Por favor, insira um ID ou nome válido.");
            }
        }
    }

    // Listener do botão Listar Produtos
    private class ListButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Produto> produtos = gerenciador.listAllProdutos();
            if (produtos.isEmpty()) {
                outputArea.setText("Nenhum produto cadastrado.");
            } else {
                StringBuilder sb = new StringBuilder();
                for (Produto produto : produtos) {
                    sb.append(produto.toString()).append("\n");
                }
                outputArea.setText(sb.toString());
            }
        }
    }

    // Listener do botão Editar Produto
    private class EditButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Exibe uma caixa de diálogo para buscar o produto por nome ou ID
            String input = JOptionPane.showInputDialog(
                IndexGUI.this, 
                "Digite o Nome ou ID do Produto a ser editado:", 
                "Editar Produto", 
                JOptionPane.QUESTION_MESSAGE);

            if (input != null && !input.trim().isEmpty()) {
                try {
                    // Tenta interpretar o input como ID (número)
                    int id = Integer.parseInt(input.trim());
                    Produto produto = gerenciador.getProduto(id);
                    if (produto != null) {
                        // Preenche os campos com os dados do produto
                        nomeField.setText(produto.getNome());
                        precoField.setText(String.valueOf(produto.getPreco()));
                        quantidadeField.setText(String.valueOf(produto.getQuantidade()));

                        // Atualiza o produto com novos dados
                        String novoNome = nomeField.getText();
                        Double novoPreco = Double.parseDouble(precoField.getText());
                        int novaQuantidade = Integer.parseInt(quantidadeField.getText());

                        Produto produtoAtualizado = new Produto(id, novoNome, novoPreco, novaQuantidade);
                        gerenciador.updateProduto(id, produtoAtualizado);
                        outputArea.setText("Produto atualizado: " + produtoAtualizado);
                    } else {
                        outputArea.setText("Produto não encontrado pelo ID.");
                    }
                } catch (NumberFormatException ex) {
                    // Caso falhe na conversão para ID, tenta buscar o produto pelo nome
                    List<Produto> produtos = gerenciador.searchProdutoByName(input.trim());
                    if (!produtos.isEmpty()) {
                        Produto produto = produtos.get(0); // Considerando que o nome seja único ou pega o primeiro
                        // Preenche os campos com os dados do produto encontrado
                        nomeField.setText(produto.getNome());
                        precoField.setText(String.valueOf(produto.getPreco()));
                        quantidadeField.setText(String.valueOf(produto.getQuantidade()));

                        // Atualiza o produto com novos dados
                        String novoNome = nomeField.getText();
                        Double novoPreco = Double.parseDouble(precoField.getText());
                        int novaQuantidade = Integer.parseInt(quantidadeField.getText());

                        Produto produtoAtualizado = new Produto(produto.getId(), novoNome, novoPreco, novaQuantidade);
                        gerenciador.updateProduto(produto.getId(), produtoAtualizado);
                        outputArea.setText("Produto atualizado: " + produtoAtualizado);
                    } else {
                        outputArea.setText("Produto não encontrado pelo nome.");
                    }
                }
            } else {
                outputArea.setText("Entrada inválida. Por favor, insira um nome ou ID válido.");
            }
        }
    }

    // Listener do botão Buscar Produto
    private class SearchButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nomeBusca = searchField.getText();
            List<Produto> produtos = gerenciador.searchProdutoByName(nomeBusca);
            if (produtos.isEmpty()) {
                outputArea.setText("Nenhum produto encontrado.");
            } else {
                StringBuilder sb = new StringBuilder();
                for (Produto produto : produtos) {
                    sb.append(produto.toString()).append("\n");
                }
                outputArea.setText(sb.toString());
            }
        }
    }

    // Método principal para rodar a aplicação
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            IndexGUI frame = new IndexGUI();
            frame.setVisible(true);
        });
    }
}
