package view;
import javax.swing.*;
import model.Disciplina;


import java.awt.*;

public class TelaCadDisciplina extends JFrame {

    private JTextField txtId;
    private JTextField txtNomeDisciplina;
    private JButton btnSalvar;
    // private JButton btnEditar; - removido pois vai ficar na janela pesquisa
    private JButton btnPesquisar;
    private JButton btnFechar;


    public TelaCadDisciplina(){
        //bloco cria e configura a tela
        setTitle("CADASTRO DE DISCIPLINA");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 200);
        setLocationRelativeTo(null);

        inicializarComponentes();// inicializa a tela
    
        setVisible(true); // mostra tela centralizada
    }

    public TelaCadDisciplina(Disciplina disciplina) {

        setTitle("EDITAR DISCIPLINA");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 200);
        setLocationRelativeTo(null);

        inicializarComponentes();// inicializa a tela

        // Oculta botão Pesquisar e muda Salvar para Atualizar
        btnPesquisar.setVisible(false);
        btnSalvar.setText("Atualizar");

        // Preenche os campos com os dados da disciplina
        txtNomeDisciplina.setText(disciplina.getNome_disciplina());

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
        JLabel lblMatricula = new JLabel("DISCIPLINA:");
        lblMatricula.setFont(fonteLabel);
        painelPrincipal.add(lblMatricula, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        txtNomeDisciplina = new JTextField(15);
        txtNomeDisciplina.setFont(fonteCampo);
        painelPrincipal.add(txtNomeDisciplina, gbc);


        // cria um painel só para botões abaixo dos campos de preenchimento
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        //FlowLayout organiza os botões em linha
        
        // botão salvar
        btnSalvar = new JButton("Salvar"); // cria os botões
        btnSalvar.setFont(fonteCampo);
        painelBotoes.add(btnSalvar);
        btnSalvar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Salvar disciplina em desenvolvimento");
            // Aqui vai: validar campos, salvar no DAO, limpar form
        });

        /* botão editar - removido pois vai ficar na janela pesquisa
        btnEditar= new JButton("Editar");
        btnEditar.setFont(fonteCampo);
        painelBotoes.add(btnEditar);
        btnEditar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Editar disciplina em desenvolvimento");
            // Aqui vai: validar campos, atualizar no DAO, limpar form
        });*/

        // botão pesquisar
        btnPesquisar= new JButton("Pesquisar");
        btnPesquisar.setFont(fonteCampo);
        painelBotoes.add(btnPesquisar);
        btnPesquisar.addActionListener(e -> new TelaPesquisaDisciplina());
    

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
