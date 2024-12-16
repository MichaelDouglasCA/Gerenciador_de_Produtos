package org.example;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class LoginGUI extends JFrame {
    // Campos para o nome de usuário e senha
    private JTextField usernameField;
    private JPasswordField passwordField;

    // Botões para login e cadastro
    private JButton loginButton;
    private JButton registerButton;

    // Label para exibir mensagens de status
    private JLabel statusLabel;

    // Instância do gerenciador de estoque para validar o login
    private Gerenciadorestoque gerenciador;

    // Construtor da classe LoginGUI
    public LoginGUI() {
        // Inicializa o gerenciador de estoque que vai ser usado para validar login
        gerenciador = new Gerenciadorestoque();

        // Configurações da janela principal
        setTitle("Login - Gerenciador de Produtos"); // Título da janela
        setSize(400, 300); // Tamanho da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Comportamento ao fechar a janela
        setLocationRelativeTo(null); // Centraliza a janela na tela

        // Layout principal da janela, com o uso de layout nulo para posicionamento
        // manual
        setLayout(null);

        // Painel que contém os campos de entrada de dados (nome de usuário e senha)
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2, 10, 10)); // Layout de grid 4x2
        inputPanel.setBounds(50, 50, 300, 150); // Posição e tamanho do painel
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Borda do painel
        inputPanel.setBackground(new Color(240, 240, 240)); // Fundo claro do painel

        // Adiciona o label e campo para o nome de usuário
        inputPanel.add(new JLabel("Usuário:"));
        usernameField = new JTextField(); // Campo de texto para o usuário
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14)); // Define a fonte
        inputPanel.add(usernameField);

        // Adiciona o label e campo para a senha
        inputPanel.add(new JLabel("Senha:"));
        passwordField = new JPasswordField(); // Campo de senha
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14)); // Define a fonte
        inputPanel.add(passwordField);

        // Botão de login
        loginButton = new JButton("Login");
        loginButton.setBackground(new Color(0, 123, 255)); // Cor de fundo azul
        loginButton.setForeground(Color.WHITE); // Cor do texto branca
        loginButton.setFont(new Font("Arial", Font.BOLD, 14)); // Fonte em negrito
        loginButton.setFocusPainted(false); // Remove a borda de foco ao clicar
        loginButton.setBorder(BorderFactory.createEmptyBorder()); // Remove a borda
        loginButton.addActionListener(new LoginButtonListener()); // Define o listener de ação para o botão

        inputPanel.add(loginButton); // Adiciona o botão ao painel

        // Botão de cadastro
        registerButton = new JButton("Cadastrar");
        registerButton.setBackground(new Color(0, 123, 255)); // Cor de fundo azul
        registerButton.setForeground(Color.WHITE); // Cor do texto branca
        registerButton.setFont(new Font("Arial", Font.BOLD, 14)); // Fonte em negrito
        registerButton.setFocusPainted(false); // Remove a borda de foco ao clicar
        registerButton.setBorder(BorderFactory.createEmptyBorder()); // Remove a borda
        registerButton.addActionListener(new RegisterButtonListener()); // Define o listener de ação para o botão

        inputPanel.add(registerButton); // Adiciona o botão ao painel

        // Adiciona o painel de entrada na janela principal
        add(inputPanel);

        // Label para exibir o status da tentativa de login
        statusLabel = new JLabel(" ", JLabel.CENTER); // Centraliza a label
        statusLabel.setForeground(Color.RED); // Inicialmente a cor da fonte é vermelha
        statusLabel.setBounds(50, 210, 300, 30); // Posição da label
        add(statusLabel);

        // Define a cor de fundo da janela principal
        getContentPane().setBackground(new Color(240, 240, 240));
    }

    // Listener para o botão de login
    private class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Obtém os valores digitados nos campos de nome de usuário e senha
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            // Valida o login com o gerenciador de estoque
            if (gerenciador.validateLogin(username, password)) {
                // Se o login for bem-sucedido
                statusLabel.setForeground(Color.GREEN); // Altera a cor da label para verde
                statusLabel.setText("Login bem-sucedido!"); // Exibe a mensagem de sucesso
                new IndexGUI().setVisible(true); // Abre a janela principal (Index)
                dispose(); // Fecha a janela de login
            } else {
                // Se o login falhar
                statusLabel.setForeground(Color.RED); // Altera a cor da label para vermelha
                statusLabel.setText("Usuário ou senha incorretos."); // Exibe a mensagem de erro
            }
        }
    }

    // Listener para o botão de cadastro
    private class RegisterButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Abre a janela de cadastro
            new RegisterGUI().setVisible(true);
            dispose(); // Fecha a janela de login
        }
    }

    // Método principal para executar o programa
    public static void main(String[] args) {
        // Inicia a interface gráfica na thread de eventos do Swing
        SwingUtilities.invokeLater(() -> new LoginGUI().setVisible(true));
    }
}
