package view;

import javax.swing.*;
import model.Turma;
import java.awt.*;

public class TelaCadTurma extends JFrame {

    private JTextField txtId;
    private JTextField txtNomeTurma;
    private JButton btnSalvar;
    private JButton btnEditar;
    private JButton btnPesquisar;
    private JButton btnFechar;

    public TelaCadTurma(){
        //bloco cria e configura a tela
        setTitle("CADASTRO DE TURMA");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 200);
        setLocationRelativeTo(null);

        inicializarComponentes();// inicializa a tela
    
        setVisible(true); // mostra tela centralizada
    }

    public TelaCadTurma(Turma turma) {

        setTitle("EDITAR TURMA");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 200);
        setLocationRelativeTo(null);

        inicializarComponentes();// inicializa a tela

        // Oculta botão Pesquisar e muda Salvar para Atualizar
        btnPesquisar.setVisible(false);
        btnSalvar.setText("Atualizar");

        // Preenche os campos com os dados da turma
        txtNomeTurma.setText(turma.getNome_turma());

        setVisible(true); // mostra tela centralizada

    }

    private void inicializarComponentes(){
        // cria o painel principal
        JPanel painelPrincipal = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //configurações gerais
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;

        Font fonteLabel = new Font("Arial", Font.BOLD, 14);
        Font fonteCampo = new Font("Arial", Font.PLAIN, 14);

        // config campo ID
        // o campo ID foi retirado da tela inicial pq não é relevante para usuário final

        // config campo Turma
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        JLabel lblMatricula = new JLabel("TURMA:");
        lblMatricula.setFont(fonteLabel);
        painelPrincipal.add(lblMatricula, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        txtNomeTurma = new JTextField(15);
        txtNomeTurma.setFont(fonteCampo);
        painelPrincipal.add(txtNomeTurma, gbc);

        // cria um painel só para botões abaixo dos campos de preenchimento
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        //FlowLayout organiza os botões em linha

        // botão salvar
        btnSalvar = new JButton("Salvar"); // cria os botões
        btnSalvar.setFont(fonteCampo);
        painelBotoes.add(btnSalvar);
        btnSalvar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Salvar turma em desenvolvimento");
            // Aqui vai: validar campos, salvar no DAO, limpar form
        });

        // botão editar
        btnEditar= new JButton("Editar");
        btnEditar.setFont(fonteCampo);
        painelBotoes.add(btnEditar);
        btnEditar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Editar turma em desenvolvimento");
            // Aqui vai: validar campos, atualizar no DAO, limpar form
        });

        // botão pesquisar
        btnPesquisar= new JButton("Pesquisar");
        btnPesquisar.setFont(fonteCampo);
        painelBotoes.add(btnPesquisar);
        btnPesquisar.addActionListener(e -> new TelaPesquisaTurma());
         /*{
            JOptionPane.showMessageDialog(this, "Pesquisar turma em desenvolvimento");
            // Aqui vai: abrir tela de pesquisa de turma
        });*/

        // botão fechar
        btnFechar= new JButton("Fechar");
        btnFechar.setFont(fonteCampo);
        painelBotoes.add(btnFechar);
        btnFechar.addActionListener(e -> dispose()); //fecha a janela


        // adiciona o painel de botões ao painel principal
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);
        painelPrincipal.add(painelBotoes, gbc);

        // adiciona o painel principal à janela
        add(painelPrincipal, BorderLayout.CENTER);

    }
}