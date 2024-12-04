import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;


public class JogoBloqueio {
    private JFrame tabuleiroJanela;
    private ArrayList<JButton> botoes;
    private int[][] matrizTabuleiro; // Representa o estado do tabuleiro
    private int jogadorAtual = 1; // 1 para jogador 1, 2 para jogador 2
    private JLabel statusLabel; // Exibe mensagens do jogo
    private int[] posicaoJogador1 = {0, 3}; // Posição inicial do jogador 1
    private int[] posicaoJogador2 = {6, 3}; // Posição inicial do jogador 2
    private int barreirasJogador1 = 3; // Número de barreiras do jogador 1
    private int barreirasJogador2 = 3; // Número de barreiras do jogador 2
    private String nomeJogador1;
    private String nomeJogador2;

    public JogoBloqueio(String nomeJogador1, String nomeJogador2) {
        this.nomeJogador1 = nomeJogador1;
        this.nomeJogador2 = nomeJogador2;

        // Configura janela do tabuleiro
        tabuleiroJanela = new JFrame("Jogo de Bloqueio");
        tabuleiroJanela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tabuleiroJanela.setSize(600, 500); // Janela menor
        tabuleiroJanela.setLocationRelativeTo(null);

        // Painel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        tabuleiroJanela.add(mainPanel);

            // Adiciona confirmação ao fechar
        tabuleiroJanela.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        tabuleiroJanela.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                int resposta = JOptionPane.showConfirmDialog(tabuleiroJanela,
                        "Tem certeza que deseja fechar o jogo?",
                        "Confirmação",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (resposta == JOptionPane.YES_OPTION) {
                    System.exit(0); // Fecha o programa
                }
            }
        });

        // Label de status
        statusLabel = new JLabel(
                "Turno: Jogador 1 (" + nomeJogador1 + ") | Barreiras: " + barreirasJogador1 + " - " + barreirasJogador2,
                SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(statusLabel, BorderLayout.NORTH);

        // Painel do tabuleiro
        JPanel tabuleiroPanel = new JPanel(new GridLayout(7, 7, 5, 5));
        botoes = new ArrayList<>();
        matrizTabuleiro = new int[7][7]; // 0 = vazio, 1 = jogador 1, 2 = jogador 2, 3 = barreira

        // Configurar posições iniciais dos jogadores
        matrizTabuleiro[posicaoJogador1[0]][posicaoJogador1[1]] = 1; // Jogador 1
        matrizTabuleiro[posicaoJogador2[0]][posicaoJogador2[1]] = 2; // Jogador 2

        // Criar botões do tabuleiro
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                JButton botao = new JButton();
                configurarBotao(botao, i, j);
                tabuleiroPanel.add(botao);
                botoes.add(botao);
            }
        }

        // Reduzir o espaço ocupado pelo tabuleiro com margens
        JPanel tabuleiroWrapper = new JPanel(new GridBagLayout());
        tabuleiroWrapper.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30)); // Margens ao redor
        tabuleiroWrapper.add(tabuleiroPanel);

        // Adicionar painel do tabuleiro ao centro
        mainPanel.add(tabuleiroWrapper, BorderLayout.CENTER);

        // Atualizar tabuleiro para refletir as posições iniciais
        atualizarTabuleiro();

        // Exibir janela
        tabuleiroJanela.setVisible(true);
    }

    private void configurarBotao(JButton botao, int i, int j) {
        botao.setBackground(Color.LIGHT_GRAY);
        botao.setFocusPainted(false);
        botao.setPreferredSize(new Dimension(50, 50)); // Botões menores

        botao.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) { // Clique com o botão direito
                    colocarBarreira(i, j);
                } else if (SwingUtilities.isLeftMouseButton(e)) { // Clique com o botão esquerdo
                    if (matrizTabuleiro[i][j] == 0) { // Apenas posições vazias podem ser ocupadas
                        moverJogador(i, j);
                    }
                }
            }
        });
    }

    private void colocarBarreira(int i, int j) {
        if (matrizTabuleiro[i][j] == 0) { // Apenas em células vazias
            if (jogadorAtual == 1 && barreirasJogador1 > 0) {
                matrizTabuleiro[i][j] = 3; // Marca como barreira
                barreirasJogador1--;
                passarTurno();
            } else if (jogadorAtual == 2 && barreirasJogador2 > 0) {
                matrizTabuleiro[i][j] = 3; // Marca como barreira
                barreirasJogador2--;
                passarTurno();
            } else {
                JOptionPane.showMessageDialog(tabuleiroJanela,
                        "Você não tem mais barreiras disponíveis!",
                        "Aviso", JOptionPane.WARNING_MESSAGE);
            }
            atualizarTabuleiro();
        }
    }

    private void moverJogador(int i, int j) {
        int[] posicaoAtual = jogadorAtual == 1 ? posicaoJogador1 : posicaoJogador2;

        // Verifica se o movimento é válido (célula adjacente e vazia)
        if (Math.abs(posicaoAtual[0] - i) + Math.abs(posicaoAtual[1] - j) == 1) {
            // Atualiza a matriz
            matrizTabuleiro[posicaoAtual[0]][posicaoAtual[1]] = 0; // Libera a posição antiga
            matrizTabuleiro[i][j] = jogadorAtual; // Ocupa a nova posição

            // Atualiza a posição do jogador
            if (jogadorAtual == 1) {
                posicaoJogador1[0] = i;
                posicaoJogador1[1] = j;
            } else {
                posicaoJogador2[0] = i;
                posicaoJogador2[1] = j;
            }

            // Verifica vitória
            if (verificarVitoria(i)) {
                JOptionPane.showMessageDialog(tabuleiroJanela,
                        "Jogador " + jogadorAtual + " venceu!",
                        "Fim de Jogo",
                        JOptionPane.INFORMATION_MESSAGE);
                reiniciarJogo();
                return;
            }

            // Passa o turno
            passarTurno();
        }
    }

    private void passarTurno() {
        jogadorAtual = (jogadorAtual == 1) ? 2 : 1;
        String nomeAtual = (jogadorAtual == 1) ? nomeJogador1 : nomeJogador2;
        statusLabel.setText("Turno: Jogador " + jogadorAtual + " (" + nomeAtual + ")" +
                " | Barreiras: " + barreirasJogador1 + " - " + barreirasJogador2);
        atualizarTabuleiro();
    }
    

    private boolean verificarVitoria(int linha) {
        // Jogador 1 vence ao alcançar a última linha (linha 6)
        if (jogadorAtual == 1 && linha == 6) {
            return true;
        }
        // Jogador 2 vence ao alcançar a primeira linha (linha 0)
        if (jogadorAtual == 2 && linha == 0) {
            return true;
        }
        return false;
    }

    private void reiniciarJogo() {
        tabuleiroJanela.dispose(); // Fecha a janela atual
        new MenuPrincipal(); 
    }

    private void atualizarTabuleiro() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                JButton botao = botoes.get(i * 7 + j);
                if (matrizTabuleiro[i][j] == 1) {
                    botao.setBackground(Color.BLUE); // Jogador 1
                } else if (matrizTabuleiro[i][j] == 2) {
                    botao.setBackground(Color.RED); // Jogador 2
                } else if (matrizTabuleiro[i][j] == 3) {
                    botao.setBackground(Color.BLACK); // Barreiras
                } else {
                    botao.setBackground(Color.LIGHT_GRAY); // Célula vazia
                }
            }
        }
    }

        public static void main(String[] args) {
            new JogoBloqueio("Jogador 1", "Jogador 2");
            
        }
    }