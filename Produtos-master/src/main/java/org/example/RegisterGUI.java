package org.example;

import java.awt.Color;
import java.awt.Dimension;
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

public class RegisterGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private JButton registerButton;
    private JLabel statusLabel;
    private Gerenciadorestoque gerenciador;

    public RegisterGUI() {
        // Inicializa o gerenciador de estoque para fazer login
        gerenciador = new Gerenciadorestoque();

        setTitle("Cadastro - Gerenciador de Produtos");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout principal
        setLayout(null);

        // Painel de campos de input
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(7, 2, 10, 10));
        inputPanel.setBounds(50, 50, 300, 240); // Posição e tamanho ajustados
        inputPanel.setBackground(new Color(255, 255, 255)); // Fundo branco
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Mais espaçamento

        // Adicionando labels e campos de entrada
        inputPanel.add(new JLabel("Usuário:"));
        usernameField = new JTextField();
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameField.setBorder(BorderFactory.createLineBorder(new Color(0, 123, 255), 2)); // Borda azul
        inputPanel.add(usernameField);

        inputPanel.add(new JLabel("Senha:"));
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(0, 123, 255), 2)); // Borda azul
        inputPanel.add(passwordField);

        inputPanel.add(new JLabel("E-mail:"));
        emailField = new JTextField();
        emailField.setFont(new Font("Arial", Font.PLAIN, 14));
        emailField.setBorder(BorderFactory.createLineBorder(new Color(0, 123, 255), 2)); // Borda azul
        inputPanel.add(emailField);

        // Botão de Cadastro
        registerButton = new JButton("Cadastrar");
        registerButton.setBackground(new Color(0, 123, 255));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
        registerButton.setFocusPainted(false);
        registerButton.setBorder(BorderFactory.createEmptyBorder());
        registerButton.setPreferredSize(new Dimension(100, 40)); // Tamanho do botão ajustado
        registerButton.addActionListener(new RegisterButtonListener());
        registerButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR)); // Mudando o cursor para mão

        inputPanel.add(registerButton);

        add(inputPanel);

        // Status de cadastro
        statusLabel = new JLabel(" ", JLabel.CENTER);
        statusLabel.setForeground(Color.RED);
        statusLabel.setFont(new Font("Arial", Font.ITALIC, 12)); // Fonte itálica para status
        statusLabel.setBounds(50, 300, 300, 30); // Posição do status
        add(statusLabel);

        // Cor de fundo da janela
        getContentPane().setBackground(new Color(240, 240, 240));
    }

    // Listener para o botão de cadastro
    private class RegisterButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String email = emailField.getText();

            if (gerenciador.registerUser(username, password, email)) {
                statusLabel.setForeground(Color.GREEN);
                statusLabel.setText("Cadastro bem-sucedido!");
                new LoginGUI().setVisible(true); // Abre a tela de login após o cadastro
                dispose(); // Fecha a janela de cadastro
            } else {
                statusLabel.setForeground(Color.RED);
                statusLabel.setText("Erro ao cadastrar usuário.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegisterGUI().setVisible(true));
    }
}
