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
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private JLabel statusLabel;
    private Gerenciadorestoque gerenciador;

    public LoginGUI() {
        gerenciador = new Gerenciadorestoque();

        // Configurações da janela principal
        setTitle("Login - Gerenciador de Produtos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Painel de entrada de dados
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2, 10, 10));
        inputPanel.setBounds(50, 50, 300, 150);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.setBackground(new Color(245, 245, 245));

        // Nome de usuário
        inputPanel.add(new JLabel("Usuário:"));
        usernameField = new JTextField();
        usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        usernameField.setBackground(new Color(250, 250, 250));
        inputPanel.add(usernameField);

        // Senha
        inputPanel.add(new JLabel("Senha:"));
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passwordField.setBackground(new Color(250, 250, 250));
        inputPanel.add(passwordField);

        // Botão de login
        loginButton = new JButton("Login");
        loginButton.setBackground(new Color(34, 167, 240)); // Azul claro
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        loginButton.setFocusPainted(false);
        loginButton.setBorder(BorderFactory.createEmptyBorder());
        loginButton.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR)); // Cursor de mão
        loginButton.addActionListener(new LoginButtonListener());

        // Efeito ao passar o mouse
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(28, 140, 210)); // Azul mais forte
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(34, 167, 240)); // Azul claro
            }
        });

        inputPanel.add(loginButton);

        // Botão de cadastro
        registerButton = new JButton("Cadastrar");
        registerButton.setBackground(new Color(34, 167, 240));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        registerButton.setFocusPainted(false);
        registerButton.setBorder(BorderFactory.createEmptyBorder());
        registerButton.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
        registerButton.addActionListener(new RegisterButtonListener());

        // Efeito ao passar o mouse
        registerButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registerButton.setBackground(new Color(28, 140, 210));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                registerButton.setBackground(new Color(34, 167, 240));
            }
        });

        inputPanel.add(registerButton);
        add(inputPanel);

        // Status de login
        statusLabel = new JLabel(" ", JLabel.CENTER);
        statusLabel.setForeground(Color.RED);
        statusLabel.setBounds(50, 210, 300, 30);
        add(statusLabel);

        getContentPane().setBackground(new Color(240, 240, 240));
    }

    // Listener para o botão de login
    private class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (gerenciador.validateLogin(username, password)) {
                statusLabel.setForeground(Color.GREEN);
                statusLabel.setText("Login bem-sucedido!");
                new IndexGUI().setVisible(true);
                dispose();
            } else {
                statusLabel.setForeground(Color.RED);
                statusLabel.setText("Usuário ou senha incorretos.");
            }
        }
    }

    // Listener para o botão de cadastro
    private class RegisterButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new RegisterGUI().setVisible(true);
            dispose();
        }
    }

    // Método principal para executar o programa
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginGUI().setVisible(true));
    }
}
