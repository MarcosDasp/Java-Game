import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class Tabuleiro {
    private JFrame tabuleiroJanela;
    private ArrayList<JButton> botoes;

    public Tabuleiro(String nomeJogador) {
        // Cria a janela para o tabuleiro
        tabuleiroJanela = new JFrame("Tabuleiro 7x7");
        tabuleiroJanela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fecha apenas esta janela
        tabuleiroJanela.setSize(700, 700);
        tabuleiroJanela.setLocationRelativeTo(null);

        // Adiciona listener para o evento de fechamento
        tabuleiroJanela.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                SwingUtilities.invokeLater(MenuPrincipal::new);
            }
        });

        // Painel principal com BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Rótulo com o nome do jogador na parte superior
        JLabel nomeLabel = new JLabel("Jogador: " + nomeJogador, SwingConstants.CENTER);
        nomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nomeLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        mainPanel.add(nomeLabel, BorderLayout.NORTH);

        // Painel com GridLayout para o tabuleiro
        JPanel tabuleiroPanel = new JPanel();
        tabuleiroPanel.setLayout(new GridLayout(7, 7, 5, 5));

        // Lista de botões
        botoes = new ArrayList<>();

        // Criar botões do tabuleiro
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                JButton botao = new JButton();
                configurarBotao(botao);
                tabuleiroPanel.add(botao);
                botoes.add(botao);
            }
        }

        // Adicionar o painel do tabuleiro ao centro
        mainPanel.add(tabuleiroPanel, BorderLayout.CENTER);

        // Adicionar painel principal ao frame
        tabuleiroJanela.add(mainPanel);
        tabuleiroJanela.setVisible(true);
    }

    private void configurarBotao(JButton botao) {
        botao.setBackground(Color.LIGHT_GRAY);
        botao.setFont(new Font("Arial", Font.BOLD, 14));
        botao.setFocusPainted(false);

        botao.addActionListener(e -> {
            // Restaura a cor padrão de todos os botões
            for (JButton b : botoes) {
                b.setBackground(Color.LIGHT_GRAY);
            }
            // Altera a cor do botão clicado
            botao.setBackground(Color.BLUE);
        });
    }
}
