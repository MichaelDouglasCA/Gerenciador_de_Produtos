package org.example;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

        public class IndexGUI extends JFrame {
            private Gerenciadorestoque gerenciador;
    private JTextField nomeField, precoField, quantidadeField, idField, searchField;
    private JTextArea outputArea;
    private JTable table;  

    public IndexGUI() {
        gerenciador = new Gerenciadorestoque();
        
        // Configuração da janela
        setTitle("Gerenciador de Estoque");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Layout da interface
        setLayout(new BorderLayout());
        
        // Painel de entrada
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(7, 2, 10, 10)); 
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); 
        
        inputPanel.add(new JLabel("Buscar por Nome:"));
        searchField = new JTextField();
        searchField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        inputPanel.add(searchField);

        // Adiciona o painel de entrada no layout
        add(inputPanel, BorderLayout.NORTH);

        // Criação do JTextArea
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        add(scrollPane, BorderLayout.SOUTH);

        // Tabela para exibir os produtos
        table = new JTable();  // Initialize the instance variable
        table.setModel(new DefaultTableModel(new Object[]{"ID", "Nome", "Preço", "Quantidade"}, 0));
        table.setFillsViewportHeight(true);
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        add(tableScrollPane, BorderLayout.CENTER);
                // Painel de botões
                JPanel buttonPanel = new JPanel();
                buttonPanel.setLayout(new GridLayout(1, 6, 10, 10)); // Aumenta a grid para incluir o botão de buscar
                buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
                // Botões de ação
                JButton addButton = new JButton("Adicionar Produto");
                addButton.setBackground(new Color(0, 123, 255));
                addButton.setForeground(Color.WHITE);
                addButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
                addButton.setFocusPainted(false);
                addButton.addActionListener(new AddButtonListener());
                buttonPanel.add(addButton);
        
                JButton removeButton = new JButton("Remover Produto");
                removeButton.setBackground(new Color(220, 53, 69));
                removeButton.setForeground(Color.WHITE);
                removeButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
                removeButton.setFocusPainted(false);
                removeButton.addActionListener(new RemoveButtonListener());
                buttonPanel.add(removeButton);
        
                JButton listButton = new JButton("Listar Produtos");
                listButton.setBackground(new Color(40, 167, 69));
                listButton.setForeground(Color.WHITE);
                listButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
                listButton.setFocusPainted(false);
                listButton.addActionListener(new ListButtonListener());
                buttonPanel.add(listButton);
        
                JButton editButton = new JButton("Editar Produto");
                editButton.setBackground(new Color(255, 193, 7));
                editButton.setForeground(Color.WHITE);
                editButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
                editButton.setFocusPainted(false);
                editButton.addActionListener(new EditButtonListener());
                buttonPanel.add(editButton);
        
                JButton searchButton = new JButton("Buscar Produto");
                searchButton.setBackground(new Color(23, 162, 184));
                searchButton.setForeground(Color.WHITE);
                searchButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
                searchButton.setFocusPainted(false);
                searchButton.addActionListener(new SearchButtonListener());
                buttonPanel.add(searchButton);
        
                JButton exitButton = new JButton("Sair");
                exitButton.setBackground(new Color(108, 117, 125));
                exitButton.setForeground(Color.WHITE);
                exitButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
                exitButton.setFocusPainted(false);
                exitButton.addActionListener(e -> System.exit(0));
                buttonPanel.add(exitButton);
        
                // Adiciona o painel de botões no layout
                add(buttonPanel, BorderLayout.SOUTH);
            }
        
        

  // Listener do botão Adicionar Produto
private class AddButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Cria um novo JDialog para adicionar produto
        JDialog addDialog = new JDialog(IndexGUI.this, "Adicionar Produto", true);
        addDialog.setSize(400, 300);
        addDialog.setLayout(new GridLayout(4, 2, 10, 10));

        // Campos de entrada
        JTextField nomeField = new JTextField();
        JTextField precoField = new JTextField();
        JTextField quantidadeField = new JTextField();

        addDialog.add(new JLabel("Nome do Produto:"));
        addDialog.add(nomeField);
        addDialog.add(new JLabel("Preço do Produto:"));
        addDialog.add(precoField);
        addDialog.add(new JLabel("Quantidade do Produto:"));
        addDialog.add(quantidadeField);

        // Botão Salvar
        JButton salvarButton = new JButton("Salvar");
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Coleta os dados dos campos
                    String nome = nomeField.getText();
                    Double preco = Double.parseDouble(precoField.getText());
                    int quantidade = Integer.parseInt(quantidadeField.getText());

                    // Cria um novo produto e adiciona
                    Produto produto = new Produto(nome, preco, quantidade);
                    gerenciador.addProduto(produto);
                    outputArea.setText("Produto adicionado: " + produto);
                    
                    addDialog.dispose(); // Fecha o diálogo após salvar o produto
                } catch (NumberFormatException ex) {
                    outputArea.setText("Erro: Preço e quantidade devem ser números válidos.");
                }
            }
        });

        addDialog.add(salvarButton);
        addDialog.setLocationRelativeTo(null);
        addDialog.setVisible(true); // Exibe o diálogo
    }
}


    // Listener do botão Remover Produto
    private class RemoveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = JOptionPane.showInputDialog(
                IndexGUI.this,
                "Digite o ID ou Nome do Produto a ser removido:",
                "Remover Produto",
                JOptionPane.QUESTION_MESSAGE);

            if (input != null && !input.trim().isEmpty()) {
                try {
                    int id = Integer.parseInt(input.trim());
                    gerenciador.deleteProduto(id);
                    outputArea.setText("Produto removido com ID: " + id);
                } catch (NumberFormatException ex) {
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
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); // Limpa as linhas atuais da tabela
    
            if (produtos.isEmpty()) {
                JOptionPane.showMessageDialog(IndexGUI.this, "Nenhum produto cadastrado.", "Aviso", JOptionPane.WARNING_MESSAGE);
            } else {
                for (Produto produto : produtos) {
                    model.addRow(new Object[]{produto.getId(), produto.getNome(), produto.getPreco(), produto.getQuantidade()});
                }
            }
        }
    }
    

    // Listener do botão Editar Produto
   // Listener do botão Editar Produto
private class EditButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String input = JOptionPane.showInputDialog(
            IndexGUI.this,
            "Digite o Nome ou ID do Produto a ser editado:",
            "Editar Produto",
            JOptionPane.QUESTION_MESSAGE);

        if (input != null && !input.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(input.trim());
                Produto produto = gerenciador.getProduto(id);
                if (produto != null) {
                    // Cria um novo JDialog para edição
                    JDialog editDialog = new JDialog(IndexGUI.this, "Editar Produto", true);
                    editDialog.setSize(400, 300);
                    editDialog.setLayout(new GridLayout(5, 2, 10, 10));

                    // Campos de edição
                    JTextField nomeField = new JTextField(produto.getNome());
                    JTextField precoField = new JTextField(String.valueOf(produto.getPreco()));
                    JTextField quantidadeField = new JTextField(String.valueOf(produto.getQuantidade()));

                    editDialog.add(new JLabel("Nome do Produto:"));
                    editDialog.add(nomeField);

                    editDialog.add(new JLabel("Preço do Produto:"));
                    editDialog.add(precoField);

                    editDialog.add(new JLabel("Quantidade do Produto:"));
                    editDialog.add(quantidadeField);

                    // Botão Salvar
                    JButton salvarButton = new JButton("Salvar");
                    salvarButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            produto.setNome(nomeField.getText());
                            produto.setPreco(Double.parseDouble(precoField.getText()));
                            produto.setQuantidade(Integer.parseInt(quantidadeField.getText()));
                            gerenciador.updateProduto(id, produto);
                            outputArea.setText("Produto atualizado: " + produto);
                            editDialog.dispose(); // Fecha o diálogo
                        }
                    });

                    editDialog.add(salvarButton);
                    editDialog.setLocationRelativeTo(null);
                    editDialog.setVisible(true);
                } else {
                    outputArea.setText("Produto não encontrado.");
                }
            } catch (NumberFormatException ex) {
                List<Produto> produtos = gerenciador.searchProdutoByName(input.trim());
                if (!produtos.isEmpty()) {
                    Produto produto = produtos.get(0);
                    // Exibe as informações no painel de edição
                } else {
                    outputArea.setText("Produto não encontrado.");
                }
            }
        }
    }
}

    

private class SearchButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String nomeBusca = searchField.getText().trim();
        if (!nomeBusca.isEmpty()) {
            List<Produto> produtos = gerenciador.searchProdutoByName(nomeBusca);
            if (produtos.isEmpty()) {
                JOptionPane.showMessageDialog(IndexGUI.this, "Produto não encontrado.", "Busca", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Cria uma nova tela para exibir os resultados
                SearchResultDialog dialog = new SearchResultDialog(produtos);
                dialog.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(IndexGUI.this, "Por favor, insira um nome para buscar.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }
}

private class SearchResultDialog extends JDialog {
    public SearchResultDialog(List<Produto> produtos) {
        setTitle("Resultados da Busca");
        setSize(400, 300);
        setLocationRelativeTo(null);
        
        String[] columns = {"ID", "Nome", "Preço", "Quantidade"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable resultTable = new JTable(model);
        
        for (Produto produto : produtos) {
            model.addRow(new Object[]{produto.getId(), produto.getNome(), produto.getPreco(), produto.getQuantidade()});
        }
        
        add(new JScrollPane(resultTable), BorderLayout.CENTER);
        
        JButton closeButton = new JButton("Fechar");
        closeButton.addActionListener(e -> dispose());
        add(closeButton, BorderLayout.SOUTH);
    }
}


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            IndexGUI gui = new IndexGUI();
            gui.setVisible(true);
        });
    }
}
