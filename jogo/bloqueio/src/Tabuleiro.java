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
        tabuleiroJanela.setSize(800, 600); // Tamanho da janela ajustado
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
        mainPanel.setBackground(Color.decode("#171133")); // Cor de fundo do tabuleiro

        // Rótulo com o nome do jogador na parte superior
        JLabel nomeLabel = new JLabel("Jogador: " + nomeJogador, SwingConstants.CENTER);
        nomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nomeLabel.setForeground(Color.WHITE); // Texto branco para melhor contraste
        nomeLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        mainPanel.add(nomeLabel, BorderLayout.NORTH);

        // Painel com GridLayout para o tabuleiro
        JPanel tabuleiroPanel = new JPanel();
        tabuleiroPanel.setLayout(new GridLayout(7, 7, 5, 5)); // Grid com espaçamento entre botões
        tabuleiroPanel.setBackground(Color.decode("#171133")); // Fundo do painel do tabuleiro

        // Reduz o tamanho do tabuleiro adicionando margens
        JPanel tabuleiroWrapper = new JPanel(new GridBagLayout());
        tabuleiroWrapper.setBackground(Color.decode("#171133"));
        tabuleiroWrapper.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50)); // Margens ao redor do tabuleiro
        tabuleiroWrapper.add(tabuleiroPanel);

        // Lista de botões
        botoes = new ArrayList<>();

        // Criar botões do tabuleiro com tamanho reduzido
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                JButton botao = new JButton();
                configurarBotao(botao);
                tabuleiroPanel.add(botao);
                botoes.add(botao);
            }
        }

        // Adicionar o painel do tabuleiro ao centro do painel principal
        mainPanel.add(tabuleiroWrapper, BorderLayout.CENTER);

        // Adicionar painel principal ao frame
        tabuleiroJanela.add(mainPanel);
        tabuleiroJanela.setVisible(true);
    }

    private void configurarBotao(JButton botao) {
        botao.setBackground(Color.decode("#581e44")); // Cor de fundo dos botões
        botao.setFont(new Font("Arial", Font.BOLD, 12)); // Tamanho reduzido da fonte
        botao.setPreferredSize(new Dimension(50, 50)); // Tamanho reduzido dos botões
        botao.setFocusPainted(false);
        botao.setBorder(null);
        botao.setForeground(Color.WHITE); // Cor do texto para contraste

        botao.addActionListener(e -> {
            // Restaura a cor padrão de todos os botões
            for (JButton b : botoes) {
                b.setBackground(Color.decode("#581e44"));
            }
            // Altera a cor do botão clicado
            botao.setBackground(Color.BLUE);
        });
    }
}
