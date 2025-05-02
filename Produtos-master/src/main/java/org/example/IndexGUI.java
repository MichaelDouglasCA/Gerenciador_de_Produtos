package org.example;
//
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class IndexGUI extends JFrame {
  private Gerenciadorestoque gerenciador;
private JTextField nomeField, precoField, quantidadeField, idField, searchField;
private JTextArea outputArea;
private JTable table;
private JLabel imagePreviewLabel = new JLabel(); // Label para exibir a imagem
private String imagemCaminho = "";

public IndexGUI() {
    gerenciador = new Gerenciadorestoque();

    // Configuração da janela
    setTitle("Gerenciador de Estoque");
    setSize(800, 600); // Ajustando o tamanho da janela
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    // Layout da interface
    setLayout(new BorderLayout());

    // Painel de entrada
    JPanel inputPanel = new JPanel();
    inputPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
    inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

    inputPanel.add(new JLabel("Buscar por Nome:"));
    searchField = new JTextField(20);
    searchField.setBorder(BorderFactory.createLineBorder(new Color(52, 58, 64), 1)); // Cor mais suave
    inputPanel.add(searchField);

    JButton searchButton = new JButton("Buscar");
    searchButton.setBackground(new Color(23, 162, 184));
    searchButton.setForeground(Color.WHITE);
    searchButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    searchButton.setFocusPainted(false);
    searchButton.addActionListener(new SearchButtonListener());
    inputPanel.add(searchButton);

    add(inputPanel, BorderLayout.NORTH);

    outputArea = new JTextArea();
    outputArea.setEditable(false);
    JScrollPane scrollPane = new JScrollPane(outputArea);
    scrollPane.setBorder(BorderFactory.createLineBorder(new Color(52, 58, 64), 1));
    add(scrollPane, BorderLayout.SOUTH);

    // Tabela para exibir os produtos
    table = new JTable(); 
    table.setModel(new DefaultTableModel(
            new Object[] { "ID", "Nome", "Preço", "Quantidade", "Categoria", "Descrição", "Imagem" }, 0));
    table.setFillsViewportHeight(true);
    table.setAutoCreateRowSorter(true);
    table.setSelectionBackground(new Color(52, 58, 64)); // Cor de fundo quando selecionado
    table.setSelectionForeground(Color.WHITE); // Cor do texto selecionado

    // Customizando cabeçalho da tabela
    JTableHeader header = table.getTableHeader();
    header.setBackground(new Color(23, 162, 184));
    header.setForeground(Color.WHITE);
    header.setFont(new Font("Arial", Font.BOLD, 14));

    // Ajustando o tamanho das colunas
    table.getColumnModel().getColumn(0).setPreferredWidth(50); // ID
    table.getColumnModel().getColumn(1).setPreferredWidth(150); // Nome
    table.getColumnModel().getColumn(2).setPreferredWidth(100); // Preço
    table.getColumnModel().getColumn(3).setPreferredWidth(100); // Quantidade
    table.getColumnModel().getColumn(4).setPreferredWidth(100); // Categoria
    table.getColumnModel().getColumn(5).setPreferredWidth(200); // Descrição
    table.getColumnModel().getColumn(6).setPreferredWidth(100); // Imagem

    // Método para renderizar a imagem na tabela
    table.getColumnModel().getColumn(6).setCellRenderer(new DefaultTableCellRenderer() {
        @Override
        public void setValue(Object value) {
            if (value != null) {
                String imagePath = (String) value;
                ImageIcon imageIcon = new ImageIcon(imagePath);
                int width = 80; // Tamanho ajustável para exibição na tabela
                int height = 80;
                imageIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH));
                setIcon(imageIcon);
            } else {
                setIcon(null);
            }
        }
    });

    table.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {
            int row = table.rowAtPoint(e.getPoint());
            int col = table.columnAtPoint(e.getPoint());

            if (col == 6) {
                String imagePath = (String) table.getValueAt(row, col);
                if (imagePath != null && !imagePath.isEmpty()) {
                    JDialog imageDialog = new JDialog();
                    imageDialog.setTitle("Visualizar Imagem");
                    imageDialog.setSize(600, 600);
                    imageDialog.setLocationRelativeTo(null);
                    JLabel imageLabel = new JLabel(new ImageIcon(imagePath));
                    imageDialog.add(imageLabel);
                    imageDialog.setVisible(true);
                }
            }
        }
    });

    JScrollPane tableScrollPane = new JScrollPane(table);
    tableScrollPane.setBorder(BorderFactory.createLineBorder(new Color(52, 58, 64), 1));
    add(tableScrollPane, BorderLayout.CENTER);

    // Painel de botões
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridLayout(1, 5, 10, 10));
    buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

    JButton addButton = new JButton("Adicionar");
    addButton.setBackground(new Color(0, 123, 255));
    addButton.setForeground(Color.WHITE);
    addButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    addButton.setFocusPainted(false);
    addButton.addActionListener(new AddButtonListener());
    buttonPanel.add(addButton);

    JButton removeButton = new JButton("Remover");
    removeButton.setBackground(new Color(220, 53, 69));
    removeButton.setForeground(Color.WHITE);
    removeButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    removeButton.setFocusPainted(false);
    removeButton.addActionListener(new RemoveButtonListener());
    buttonPanel.add(removeButton);

    JButton editButton = new JButton("Editar");
    editButton.setBackground(new Color(255, 193, 7));
    editButton.setForeground(Color.WHITE);
    editButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    editButton.setFocusPainted(false);
    editButton.addActionListener(new EditButtonListener());
    buttonPanel.add(editButton);

    JButton exitButton = new JButton("Sair");
    exitButton.setBackground(new Color(108, 117, 125));
    exitButton.setForeground(Color.WHITE);
    exitButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    exitButton.setFocusPainted(false);
    exitButton.addActionListener(e -> {
        dispose();
        new LoginGUI().setVisible(true);
    });
    buttonPanel.add(exitButton);

    add(buttonPanel, BorderLayout.SOUTH);

    listarProdutos();
}

    // Método para listar os produtos automaticamente
    private void listarProdutos() {
        List<Produto> produtos = gerenciador.listAllProdutos();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Limpa as linhas atuais da tabela

        // Verifica se há produtos
        if (produtos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum produto cadastrado.", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
            // Adiciona cada produto na tabela, incluindo imagem e outros detalhes
            for (Produto produto : produtos) {
                model.addRow(new Object[] {
                        produto.getId(),
                        produto.getNome(),
                        produto.getPreco(),
                        produto.getQuantidade(),
                        produto.getCategoria(),
                        produto.getDescricao(),
                        produto.getImagem() // Exibe o caminho da imagem na tabela
                });
            }
        }
    }

   // Listener do botão Adicionar Produto
private class AddButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Cria um novo JDialog para adicionar produto
        JDialog addDialog = new JDialog(IndexGUI.this, "Adicionar Produto", true);
        addDialog.setSize(400, 300);
        addDialog.setLayout(new GridLayout(9, 2, 10, 10));

        // Campos de entrada
        nomeField = new JTextField();
        precoField = new JTextField();
        quantidadeField = new JTextField();
        JTextField descricaoField = new JTextField();
        JTextField categoriaField = new JTextField();
        JTextField imagemUrlField = new JTextField(); // Campo para o link da imagem
        JButton uploadButton = new JButton("Upload Imagem");

        addDialog.add(new JLabel("Nome do Produto:"));
        addDialog.add(nomeField);
        addDialog.add(new JLabel("Preço do Produto:"));
        addDialog.add(precoField);
        addDialog.add(new JLabel("Quantidade do Produto:"));
        addDialog.add(quantidadeField);
        addDialog.add(new JLabel("Descrição do Produto:"));
        addDialog.add(descricaoField);
        addDialog.add(new JLabel("Categoria do Produto:"));
        addDialog.add(categoriaField);
        addDialog.add(new JLabel("URL da Imagem (ou botão de upload):"));
        addDialog.add(imagemUrlField); // Campo de link da imagem
        addDialog.add(uploadButton);
        
        // Criação do JLabel para visualização da imagem
        addDialog.add(new JLabel("Imagem do Produto:"));
        addDialog.add(imagePreviewLabel); // Adiciona o JLabel para exibir a imagem

        // Ação do botão de upload de imagem
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int resultado = fileChooser.showOpenDialog(addDialog);
                if (resultado == JFileChooser.APPROVE_OPTION) {
                    // Salva o caminho do arquivo na variável de instância
                    imagemCaminho = fileChooser.getSelectedFile().getAbsolutePath();
                    JOptionPane.showMessageDialog(addDialog, "Imagem selecionada: " + imagemCaminho);

                    // Exibe a imagem no JLabel
                    ImageIcon imageIcon = new ImageIcon(imagemCaminho);
                    imagePreviewLabel.setIcon(imageIcon); // Define a imagem no JLabel
                }
            }
        });

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
                    String descricao = descricaoField.getText();
                    String categoria = categoriaField.getText();
                    String imagem = imagemUrlField.getText().trim().isEmpty() ? imagemCaminho : imagemUrlField.getText().trim(); // Usa o link se o campo não estiver vazio

                    // Cria um novo produto e adiciona
                    Produto produto = new Produto(nome, preco, quantidade, descricao, categoria, imagem);
                    gerenciador.addProduto(produto);
                    outputArea.setText("Produto adicionado: " + produto);

                    listarProdutos(); // Atualiza a tabela de produtos
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

                    // Atualiza a lista de produtos após a remoção
                    listarProdutos(); // Método para listar os produtos na interface

                } catch (NumberFormatException ex) {
                    List<Produto> produtos = gerenciador.searchProdutoByName(input.trim());
                    if (!produtos.isEmpty()) {
                        Produto produto = produtos.get(0);
                        gerenciador.deleteProduto(produto.getId());
                        outputArea.setText("Produto removido: " + produto.getNome());

                        // Atualiza a lista de produtos após a remoção
                        listarProdutos(); // Atualiza a tabela de produtos após remoção
                    } else {
                        outputArea.setText("Produto não encontrado pelo nome.");
                    }
                }
            } else {
                outputArea.setText("Entrada inválida. Por favor, insira um ID ou nome válido.");
            }
        }
    }

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
                    // Tenta buscar o produto pelo ID
                    int id = Integer.parseInt(input.trim());
                    Produto produto = gerenciador.getProduto(id);
    
                    if (produto != null) {
                        // Cria o JDialog de edição do produto
                        JDialog editDialog = new JDialog(IndexGUI.this, "Editar Produto", true);
                        editDialog.setSize(400, 400);
                        editDialog.setLayout(new GridLayout(9, 2, 10, 10));
    
                        // Campos preenchidos com os dados do produto
                        JTextField nomeField = new JTextField(produto.getNome());
                        JTextField precoField = new JTextField(String.valueOf(produto.getPreco()));
                        JTextField quantidadeField = new JTextField(String.valueOf(produto.getQuantidade()));
                        JTextField descricaoField = new JTextField(produto.getDescricao());
                        JTextField categoriaField = new JTextField(produto.getCategoria());
                        JLabel imagePreviewLabel = new JLabel(); // Para exibir a imagem do produto
                        final String[] imagemCaminho = { produto.getImagem() };
    
                        // Exibe a imagem do produto, se houver
                        if (imagemCaminho[0] != null && !imagemCaminho[0].isEmpty()) {
                            ImageIcon imageIcon = new ImageIcon(imagemCaminho[0]);
                            imagePreviewLabel.setIcon(imageIcon);
                        }
    
                        // Campos do formulário de edição
                        editDialog.add(new JLabel("Nome do Produto:"));
                        editDialog.add(nomeField);
                        editDialog.add(new JLabel("Preço do Produto:"));
                        editDialog.add(precoField);
                        editDialog.add(new JLabel("Quantidade do Produto:"));
                        editDialog.add(quantidadeField);
                        editDialog.add(new JLabel("Descrição do Produto:"));
                        editDialog.add(descricaoField);
                        editDialog.add(new JLabel("Categoria do Produto:"));
                        editDialog.add(categoriaField);
                        editDialog.add(new JLabel("Imagem do Produto:"));
                        editDialog.add(imagePreviewLabel);
    
                        // Botão de upload da imagem
                        JButton uploadButton = new JButton("Alterar Imagem");
                        uploadButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JFileChooser fileChooser = new JFileChooser();
                                int resultado = fileChooser.showOpenDialog(editDialog);
                                if (resultado == JFileChooser.APPROVE_OPTION) {
                                    imagemCaminho[0] = fileChooser.getSelectedFile().getAbsolutePath(); // Atualiza a imagem
                                    JOptionPane.showMessageDialog(editDialog,
                                            "Imagem selecionada: " + imagemCaminho[0]);
    
                                    // Exibe a imagem selecionada no JLabel
                                    ImageIcon imageIcon = new ImageIcon(imagemCaminho[0]);
                                    imagePreviewLabel.setIcon(imageIcon);
                                }
                            }
                        });
                        editDialog.add(uploadButton);
    
                        // Botão de Salvar alterações
                        JButton salvarButton = new JButton("Salvar Alterações");
                        salvarButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    // Coleta os dados dos campos editados
                                    String nome = nomeField.getText();
                                    Double preco = Double.parseDouble(precoField.getText());
                                    int quantidade = Integer.parseInt(quantidadeField.getText());
                                    String descricao = descricaoField.getText();
                                    String categoria = categoriaField.getText();
    
                                    // Atualiza o produto com os novos dados
                                    produto.setNome(nome);
                                    produto.setPreco(preco);
                                    produto.setQuantidade(quantidade);
                                    produto.setDescricao(descricao);
                                    produto.setCategoria(categoria);
                                    produto.setImagem(imagemCaminho[0]);
    
                                    // Atualiza o produto no gerenciador de estoque
                                    gerenciador.updateProduto(produto.getId(), produto);
    
                                    // Atualiza a tabela de produtos
                                    listarProdutos();
                                    JOptionPane.showMessageDialog(editDialog, "Produto atualizado com sucesso!");
                                    editDialog.dispose(); // Fecha o diálogo
                                } catch (NumberFormatException ex) {
                                    JOptionPane.showMessageDialog(editDialog,
                                            "Erro: Preço e quantidade devem ser números válidos.");
                                }
                            }
                        });
                        editDialog.add(salvarButton);
    
                        // Exibe o diálogo
                        editDialog.setLocationRelativeTo(null);
                        editDialog.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(IndexGUI.this, "Produto não encontrado com o ID: " + id, "Erro",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    // Se o ID não for numérico, tenta buscar o produto pelo nome
                    List<Produto> produtos = gerenciador.searchProdutoByName(input.trim());
                    if (!produtos.isEmpty()) {
                        Produto produto = produtos.get(0);
                        // Cria o JDialog de edição para o produto encontrado
                    } else {
                        JOptionPane.showMessageDialog(IndexGUI.this, "Produto não encontrado.", "Erro",
                                JOptionPane.ERROR_MESSAGE);
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
                    JOptionPane.showMessageDialog(IndexGUI.this, "Produto não encontrado.", "Busca",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Cria uma nova tela para exibir os resultados
                    SearchResultDialog dialog = new SearchResultDialog(produtos);
                    dialog.setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(IndexGUI.this, "Por favor, insira um nome para buscar.", "Aviso",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private class SearchResultDialog extends JDialog {
        public SearchResultDialog(List<Produto> produtos) {
            setTitle("Resultados da Busca");
            setSize(400, 300);
            setLocationRelativeTo(null);

            String[] columns = { "ID", "Nome", "Preço", "Quantidade" };
            DefaultTableModel model = new DefaultTableModel(columns, 0);
            JTable resultTable = new JTable(model);

            for (Produto produto : produtos) {
                model.addRow(new Object[] { produto.getId(), produto.getNome(), produto.getPreco(),
                        produto.getQuantidade() });
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
