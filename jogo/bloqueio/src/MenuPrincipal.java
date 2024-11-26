import javax.swing.*;
import java.awt.*;

public class MenuPrincipal {
    private JFrame frame;
    private JTextField nomeField; // Campo para inserir o nome
    private String nome; // Variável para armazenar o nome

    public MenuPrincipal() {
        // Criar a janela principal
        frame = new JFrame("Bloqueio");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null); // Centralizar a janela na tela

        // Criar painel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Criar o rótulo
        JLabel label = new JLabel("BLOQUEIO");
        label.setFont(new Font("Arial", Font.BOLD, 50));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Criar campo para inserir o nome
        nomeField = new JTextField();
        nomeField.setMaximumSize(new Dimension(250, 150)); // Define o tamanho do campo
        nomeField.setFont(new Font("Arial", Font.PLAIN, 16));
        nomeField.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Criar botões
        JButton button1 = new JButton("INICIAR");
        JButton button2 = new JButton("COMO JOGAR?");
        configurarBotao(button1, "Inicia o jogo");
        configurarBotao(button2, null);

        // Ação do botão "INICIAR"
        button1.addActionListener(e -> {
            nome = nomeField.getText(); // Salva o nome inserido
            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Por favor, insira um nome antes de iniciar!");
            } else {
                frame.dispose(); // Fecha o menu principal
                new Tabuleiro(nome); // Abre o tabuleiro passando o nome
            }
        });

        // Ação do botão "COMO JOGAR?"
        button2.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "O objetivo do jogo é mover sua peça do ponto de partida até o ponto de chegada no tabuleiro \nbloqueando ao mesmo tempo o caminho do adversário com barreiras para atrasá-lo.");
        });

        // Adicionar componentes ao painel
        panel.add(Box.createVerticalGlue());
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(nomeField);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(button1);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(button2);
        panel.add(Box.createVerticalGlue());

        // Adicionar painel à janela
        frame.add(panel);
        frame.setVisible(true);
    }

    private void configurarBotao(JButton botao, String toolTip) {
        botao.setAlignmentX(Component.CENTER_ALIGNMENT);
        botao.setBackground(Color.GRAY);
        botao.setForeground(Color.WHITE);
        botao.setFont(new Font("Arial", Font.BOLD, 14));
        botao.setFocusPainted(false);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botao.setPreferredSize(new Dimension(120, 40));
        botao.setBorderPainted(false);
        if (toolTip != null) botao.setToolTipText(toolTip);
    }
}
