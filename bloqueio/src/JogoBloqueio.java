// Importações
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;


public class JogoBloqueio {
    // Variaveis
    private JFrame tabuleiroJanela; // Cria a variavel do JFrame
    private ArrayList<JButton> botoes; // Cria um Array onde fica armazenado os botões
    private int[][] matrizTabuleiro; // Representa o estado do tabuleiro
    private int jogadorAtual = 1; // 1 para jogador 1, 2 para jogador 2
    private JLabel statusLabel; // Exibe mensagens do jogo
    private int[] posicaoJogador1 = {0, 3}; // Posição inicial do jogador 1
    private int[] posicaoJogador2 = {6, 3}; // Posição inicial do jogador 2
    private int barreirasJogador1 = 3; // Define o número de barreiras do jogador 1
    private int barreirasJogador2 = 3; // Define o número de barreiras do jogador 2
    private String nomeJogador1; // Nome do jogador 1
    private String nomeJogador2; // Nome do jogador 2

    public JogoBloqueio(String nomeJogador1, String nomeJogador2) {
         // Inicializa os nomes dos jogadores
        this.nomeJogador1 = nomeJogador1;
        this.nomeJogador2 = nomeJogador2;

        // Configura janela do tabuleiro
        tabuleiroJanela = new JFrame("Jogo de Bloqueio"); // Cria a Janela
        tabuleiroJanela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha quando clica no "X"
        tabuleiroJanela.setSize(600, 500); // Define o tamanho
        tabuleiroJanela.setLocationRelativeTo(null); // Centraliza a janela no meio da tela

        // Painel principal
        JPanel mainPanel = new JPanel(new BorderLayout()); // Usa um layout de bordas
        tabuleiroJanela.add(mainPanel); // Adiciona o painel à janela

         // Adiciona confirmação ao fechar
        tabuleiroJanela.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Se a pessoa clicar no "X" não faz nada
        // "Escuta" (Reconhece) se um evento (Fechar a janela nesse caso) aconteceu
        tabuleiroJanela.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                // Cria uma variavel para salvar se a pessoa clicou sim ou não, além de também criar uma mensagem com os botões de opções
                int resposta = JOptionPane.showConfirmDialog(tabuleiroJanela,
                        "Tem certeza que deseja fechar o jogo?",
                        "Confirmação",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                // Checa se a resposta foi sim, e caso for verdadeiro fecha a janela, se for falso ele não faz nada
                if (resposta == JOptionPane.YES_OPTION) {
                    System.exit(0); // Fecha o programa
                }
            }
        });

        // Cria um texto com os Status (ou dados) dos jogadores (Turno, Nome, Barreira)
        statusLabel = new JLabel(
                "Turno: Jogador 1 (" + nomeJogador1 + ") | Barreiras: " + barreirasJogador1 + " - " + barreirasJogador2,
                SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Define o tamanho e a fonte do texto
        mainPanel.add(statusLabel, BorderLayout.NORTH); // Adiciona o texto no topo da janela

        // Painel do tabuleiro
        JPanel tabuleiroPanel = new JPanel(new GridLayout(7, 7, 5, 5)); // Cria um painel com um Grid de 7x7
        botoes = new ArrayList<>(); // Inicializa a lista de botões
        matrizTabuleiro = new int[7][7]; // 0 = vazio, 1 = jogador 1, 2 = jogador 2, 3 = barreira

        // Configurar posições iniciais dos jogadores
        matrizTabuleiro[posicaoJogador1[0]][posicaoJogador1[1]] = 1; // Jogador 1
        matrizTabuleiro[posicaoJogador2[0]][posicaoJogador2[1]] = 2; // Jogador 2

        // Criar botões do tabuleiro
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                JButton botao = new JButton(); // Cria um botão
                configurarBotao(botao, i, j); // Configura o comportamento do botão
                tabuleiroPanel.add(botao); // Adiciona o botão ao painel do tabuleiro
                botoes.add(botao); // Adiciona o botão à lista
            }
        }

         // Cria uma margem ao redor do tabuleiro
        JPanel tabuleiroWrapper = new JPanel(new GridBagLayout()); // Centraliza o tabuleiro
        tabuleiroWrapper.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30)); // Adiciona margens
        tabuleiroWrapper.add(tabuleiroPanel); // Adiciona o painel do tabuleiro ao wrapper

        // Adiciona o wrapper ao centro do painel principal
        mainPanel.add(tabuleiroWrapper, BorderLayout.CENTER);

        // Atualiza o tabuleiro para as configurações iniciais
        atualizarTabuleiro();

        // Torna a janela visível=
        tabuleiroJanela.setVisible(true);
    }


    private void configurarBotao(JButton botao, int i, int j) {
        // Configurações visuais do botão
        botao.setBackground(Color.LIGHT_GRAY); // Cor de fundo padrão
        botao.setFocusPainted(false); // Remove o foco no botão
        botao.setPreferredSize(new Dimension(50, 50)); // Define o tamanho do botão

        // Escuta os eventos dos botões
        botao.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) { // Clique com o botão direito
                    colocarBarreira(i, j); // coloca barreira na posição clicada
                } else if (SwingUtilities.isLeftMouseButton(e)) { // Clique com o botão esquerdo
                    if (matrizTabuleiro[i][j] == 0) { // Apenas posições vazias podem ser ocupadas
                        moverJogador(i, j); // Move o Jogador para a posição clicada
                    }
                }
            }
        });
    }

    private void colocarBarreira(int i, int j) {
        // Lógica para colocar barreiras
        if (matrizTabuleiro[i][j] == 0) { // permite barreiras só em células vazias
            if (jogadorAtual == 1 && barreirasJogador1 > 0) { // Jogador 1 coloca barreira
                matrizTabuleiro[i][j] = 3; // Marca a botão como barreira
                barreirasJogador1--; // Tira a barreira do jogador 1
                passarTurno(); 
            } else if (jogadorAtual == 2 && barreirasJogador2 > 0) { // Jogador 2 coloca barreira
                matrizTabuleiro[i][j] = 3; // Marca como barreira
                barreirasJogador2--; // Tira a barreira do jogador 2
                passarTurno();
            } else {
                // Exibe aviso se não tiver mais barreiras disponíveis
                JOptionPane.showMessageDialog(tabuleiroJanela,
                        "Você não tem mais barreiras disponíveis!",
                        "Aviso", JOptionPane.WARNING_MESSAGE);
            }
            atualizarTabuleiro(); // Atualiza o estado visual do tabuleiro
        }
    }

    private void moverJogador(int i, int j) {
        // Lógica para mover o Jogador
        int[] posicaoAtual = jogadorAtual == 1 ? posicaoJogador1 : posicaoJogador2;

        // Verifica se o movimento é válido (célula próxima é vazia)
        if (Math.abs(posicaoAtual[0] - i) + Math.abs(posicaoAtual[1] - j) == 1) {
            // Atualiza a matriz
            matrizTabuleiro[posicaoAtual[0]][posicaoAtual[1]] = 0; // Libera a posição antiga
            matrizTabuleiro[i][j] = jogadorAtual; // Ocupa a nova posição

            // Atualiza a posição do jogador na memória
            if (jogadorAtual == 1) {
                posicaoJogador1[0] = i;
                posicaoJogador1[1] = j;
            } else {
                posicaoJogador2[0] = i;
                posicaoJogador2[1] = j;
            }

            // Verifica se o jogador venceu
            if (verificarVitoria(i)) {
                JOptionPane.showMessageDialog(tabuleiroJanela,
                        "Jogador " + jogadorAtual + " venceu!",
                        "Fim de Jogo",
                        JOptionPane.INFORMATION_MESSAGE);
                reiniciarJogo(); // Reinicia o jogo
                return;
            }

            // Passa o turno
            passarTurno();
        }
    }

    private void passarTurno() {
        // Alterna entre os jogadores 1 e 2
        jogadorAtual = (jogadorAtual == 1) ? 2 : 1;
        String nomeAtual = (jogadorAtual == 1) ? nomeJogador1 : nomeJogador2;
        
        // Atualiza o status com a informação de quem é o turno atual e barreiras restantes
        statusLabel.setText("Turno: Jogador " + jogadorAtual + " (" + nomeAtual + ")" +
                " | Barreiras: " + barreirasJogador1 + " - " + barreirasJogador2);
        atualizarTabuleiro(); // Atualiza o estado visual do tabuleiro
    }
    

    private boolean verificarVitoria(int linha) {
        // Condição de vitória para cada jogador
        // Jogador 1 vence ao chegar na última linha (linha 6)
        if (jogadorAtual == 1 && linha == 6) {
            return true;
        }
        // Jogador 2 vence ao chegar na primeira linha (linha 0)
        if (jogadorAtual == 2 && linha == 0) {
            return true;
        }
        return false;
    }

    private void reiniciarJogo() {
        tabuleiroJanela.dispose(); // Fecha a janela atual
        new MenuPrincipal(); // Reinicia o jogo chamando o menu principal
    }

    // Função para atualizar o Tabuleiro
    private void atualizarTabuleiro() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                JButton botao = botoes.get(i * 7 + j); // Obtém o botão correspondente
                botao.setIcon(null);  // Remove qualquer ícone existente

                // Configura a aparência do botão de acordo com o estado da célula
                if (matrizTabuleiro[i][j] == 1) {
                    botao.setBackground(Color.BLUE); // Jogador 1
                } else if (matrizTabuleiro[i][j] == 2) {
                    botao.setBackground(Color.RED); // Jogador 2
                } else if (matrizTabuleiro[i][j] == 3) {
                    botao.setBackground(Color.LIGHT_GRAY); // Reseta cor para barreira
                    ImageIcon iconeOriginal = new ImageIcon(getClass().getResource("barreira.png")); // Ícone da barreira
                    Image img = iconeOriginal.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Ajusta tamanho
                    botao.setIcon(new ImageIcon(img));

                } else {
                    botao.setBackground(Color.LIGHT_GRAY); // Célula vazia
                }
            }
        }
    }
}