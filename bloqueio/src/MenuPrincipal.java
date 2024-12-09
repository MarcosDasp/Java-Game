import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MenuPrincipal {
    private JFrame frame;

    public MenuPrincipal() {

        // Janela principal
        frame = new JFrame("Menu Principal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);

        // Painel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        frame.add(wrapperPanel);
        wrapperPanel.add(mainPanel);

        // Título
        JLabel titleLabel = new JLabel("BLOQUEIO");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 70));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Entrada de dados
        JLabel jogador1Label = new JLabel("Nome do Jogador 1:"); // Texto
        jogador1Label.setAlignmentX(Component.CENTER_ALIGNMENT); // Centraliza na tela
        mainPanel.add(jogador1Label); // Adiciona na tela

        JTextField jogador1Field = new JTextField(); // Lugar para colocar o nome
        jogador1Field.setMaximumSize(new Dimension(300, 30)); // Define largura
        mainPanel.add(jogador1Field); // Adiciona na tela

        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Campo de texto 2
        JLabel jogador2Label = new JLabel("Jogador 2:"); // Texto
        jogador2Label.setAlignmentX(Component.CENTER_ALIGNMENT); // Centraliza na tela
        mainPanel.add(jogador2Label); // Adiciona na tela

        JTextField jogador2Field = new JTextField(); // Lugar para colocar o nome
        jogador2Field.setMaximumSize(new Dimension(300, 30)); // Define largura
        mainPanel.add(jogador2Field); // Adiciona na tela

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Botão Iniciar Jogo
        JButton iniciarButton = new JButton("Iniciar Jogo"); // Cria o botão
        iniciarButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Adiciona na tela
        iniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeJogador1 = jogador1Field.getText().trim();
                String nomeJogador2 = jogador2Field.getText().trim();

                // Valida que os nomes foram preenchidos
                if (nomeJogador1.isEmpty() || nomeJogador2.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Por favor, insira os nomes dos dois jogadores.",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Fecha o menu principal e inicia o jogo
                frame.dispose();
                new JogoBloqueio(nomeJogador1, nomeJogador2);
            }
        });
        mainPanel.add(iniciarButton);

        // Botão Como Jogar
        JButton comoJogarButton = new JButton("Como Jogar?");
        comoJogarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        comoJogarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame,
                        "O objetivo do jogo é mover sua peça até o lado oposto do tabuleiro.\n" +
                                "Use as barreiras para bloquear o caminho do adversário.",
                        "Como Jogar", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(comoJogarButton);

        // Torna a janela visível
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new MenuPrincipal();
    }
}