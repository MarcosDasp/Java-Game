// Importações
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MenuPrincipal {
    private JFrame frame; // Cria a variavel do JFrame

    public MenuPrincipal() {

        // Janela principal
        frame = new JFrame("Menu Principal"); // Cria uma janela
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha quando clica no "X"
        frame.setSize(700, 500); // Define o tamanho da Janela
        frame.setLocationRelativeTo(null); // Centraliza a janela no meio da tela

        // Painel
        JPanel mainPanel = new JPanel(); // Cria um painel
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); // Define o Layout do painel
        mainPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Alinha no centro da janela

        JPanel wrapperPanel = new JPanel(new GridBagLayout()); 
        frame.add(wrapperPanel);
        wrapperPanel.add(mainPanel);

        // Título
        JLabel titleLabel = new JLabel("BLOQUEIO"); // Texto do Titulo
        titleLabel.setFont(new Font("Arial", Font.BOLD, 70)); // Define o tamanho e a fonte do texto
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Alinha no centro
        mainPanel.add(titleLabel); // Adiciona o texto no Painel

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espaçamento

        // Entrada de dados
        JLabel jogador1Label = new JLabel("Nome do Jogador 1:"); // Texto
        jogador1Label.setAlignmentX(Component.CENTER_ALIGNMENT); // Centraliza na tela
        mainPanel.add(jogador1Label); // Adiciona na tela

        JTextField jogador1Field = new JTextField(); // Lugar para colocar o nome
        jogador1Field.setMaximumSize(new Dimension(300, 30)); // Define largura
        mainPanel.add(jogador1Field); // Adiciona na tela

        mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espaçamento

        // Campo de texto 2
        JLabel jogador2Label = new JLabel("Nome do Jogador 2:"); // Texto
        jogador2Label.setAlignmentX(Component.CENTER_ALIGNMENT); // Centraliza na tela
        mainPanel.add(jogador2Label); // Adiciona na tela

        JTextField jogador2Field = new JTextField(); // Lugar para colocar o nome
        jogador2Field.setMaximumSize(new Dimension(300, 30)); // Define largura
        mainPanel.add(jogador2Field); // Adiciona na tela

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espaçamento

        // Botão Iniciar Jogo
        JButton iniciarButton = new JButton("Iniciar Jogo"); // Cria o botão
        iniciarButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Centraliza na tela
        iniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeJogador1 = jogador1Field.getText().trim(); // Cria uma variavel de texto com o nome do Jogador 1
                String nomeJogador2 = jogador2Field.getText().trim(); // Cria uma variavel de texto com o nome do Jogador 2

                // Valida que os nomes foram preenchidos
                if (nomeJogador1.isEmpty() || nomeJogador2.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Por favor, insira os nomes dos dois jogadores.",
                            "Erro", JOptionPane.ERROR_MESSAGE); // Mensagem de erro
                    return;
                }

                // Fecha o menu principal e inicia o jogo
                frame.dispose();
                new JogoBloqueio(nomeJogador1, nomeJogador2); // Abre o Jogo passando as variaveis dos nomes
            }
        });
        mainPanel.add(iniciarButton); // Adiciona o botão na tela

        // Botão Como Jogar
        JButton comoJogarButton = new JButton("Como Jogar?"); // Cria o botão
        comoJogarButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Centraliza na tela
       // Quando clicar no botão ele exibe uma mensagem explicando como jogar
        comoJogarButton.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(frame,
                    "O objetivo do jogo é mover sua peça até o lado oposto do tabuleiro.\n" +
                            "Use as barreiras para bloquear o caminho do adversário.",
                    "Como Jogar", JOptionPane.INFORMATION_MESSAGE);
        });
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espaçamento
        mainPanel.add(comoJogarButton); // Adiciona o botão na tela

        // Torna a janela visível
        frame.setVisible(true);
    }
}