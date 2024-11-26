import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {

    public static void main(String[] args) {
        // Criar a janela principal
        JFrame frame = new JFrame("Bloqueio"); // Título da janela
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500); // Tamanho da janela

        // Centralizar a janela na tela
        frame.setLocationRelativeTo(null);

        // Criar um painel principal com BoxLayout para centralizar os componentes verticalmente
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Criar o rótulo (texto) e configurá-lo para centralizar
        JLabel label = new JLabel("Escolha uma opção:");
        label.setFont(new Font("Arial", Font.BOLD, 28));
        label.setAlignmentX(Component.CENTER_ALIGNMENT); // Centralizar horizontalmente

        // Criar os botões
        JButton button1 = new JButton("INICIAR");
        JButton button2 = new JButton("COMO JOGAR?");

        // Centralizar os botões
        button1.setAlignmentX(Component.CENTER_ALIGNMENT);
        button2.setAlignmentX(Component.CENTER_ALIGNMENT);

        button1.setBackground((Color.GRAY)); // Cor de fundo (azul claro)
        button1.setForeground(Color.WHITE); // Cor do texto (branco)
        button1.setFont(new Font("Arial", Font.BOLD, 14)); // Fonte e tamanho do texto
        button1.setFocusPainted(false); // Remove a borda de foco ao clicar
        button1.setCursor(new Cursor(Cursor)); // This one line changes the cursor.
        button1.setPreferredSize(new Dimension(120, 40)); 
        button1.setBorderPainted(false); // Remove a borda padrão
        button1.setToolTipText("O jogo ainda não ta pronto");

        button2.setBackground(Color.GRAY); // Cor de fundo (verde)
        button2.setForeground(Color.WHITE); // Cor do texto (branco)
        button2.setFont(new Font("Arial", Font.BOLD, 14)); // Fonte e tamanho do texto
        button2.setFocusPainted(false); // Remove a borda de foco ao clicar
        button2.setCursor(new Cursor(Cursor.HAND_CURSOR)); // This one line changes the cursor.
        button2.setPreferredSize(new Dimension(120, 40)); 
        button2.setBorderPainted(false); // Remove a borda padrão


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "O objetivo do jogo é mover sua peça do ponto de partida até o ponto de chegada no tabuleiro \nbloqueando ao mesmo tempo o caminho do adversário com barreiras para atrasá-lo.");
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "O objetivo do jogo é mover sua peça do ponto de partida até o ponto de chegada no tabuleiro \nbloqueando ao mesmo tempo o caminho do adversário com barreiras para atrasá-lo.");
            }
        });

        // Adicionar espaçamento entre os componentes
        panel.add(Box.createVerticalGlue()); // Espaço expansível acima
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Espaço fixo entre texto e botão
        panel.add(button1);
        panel.add(Box.createRigidArea(new Dimension(0, 5))); // Espaço fixo entre os botões
        panel.add(button2);
        panel.add(Box.createVerticalGlue()); // Espaço expansível abaixo

        // Adicionar o painel à janela
        frame.add(panel);

        // Tornar a janela visível
        frame.setVisible(true);
    }
}
