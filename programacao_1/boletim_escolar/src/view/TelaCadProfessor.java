package view;

import javax.swing.*;
import java.awt.*;
import model.Professor;

public class TelaCadProfessor extends JFrame {

    private JTextField txtId;
    private JTextField txtmatricula;
    private JTextField txtNome;
    private JTextField txtEndereco;
    private JTextField txtTelefone;
    private JTextField txtEmail; 
    private JComboBox<String> comboStatus;
    private JButton btnSalvar;
    //private JButton btnEditar; - removido pois vai ficar na janela pesquisa
    private JButton btnPesquisar;
    private JButton btnFechar;

    public TelaCadProfessor(){
        //bloco cria e configura a tela
        setTitle("CADASTRO DE PROFESSOR");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 350);
        setLocationRelativeTo(null);

        inicializarComponentes();// inicializa a tela

                
        txtmatricula.setEnabled(false);
        txtmatricula.setText("Automático"); // PRECISO DESENVOLVER lógica para gerar o número da matrícula
        txtmatricula.setForeground(Color.GRAY); //campo matricula vai aparecer desabilitado

        setVisible(true); // mostra tela centralizada
    }

    public TelaCadProfessor(Professor professor) {

        setTitle("EDITAR PROFESSOR");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 350);
        setLocationRelativeTo(null);

        inicializarComponentes();// inicializa a tela

        // Oculta botão Pesquisar e muda Salvar para Atualizar
        btnPesquisar.setVisible(false);
        btnSalvar.setText("Atualizar");

        // Preenche os campos com os dados do professor
        txtmatricula.setText(String.valueOf(professor.getMatricula()));
        txtmatricula.setEnabled(false);
        txtmatricula.setForeground(Color.BLACK);

        txtNome.setText(professor.getNome());
        txtEndereco.setText(professor.getEndereco());
        txtTelefone.setText(professor.getTelefone());
        txtEmail.setText(professor.getEmail());
        comboStatus.setSelectedItem(professor.getStatus());

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

        // config campo matricula
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        JLabel lblMatricula = new JLabel("MATRÍCULA:");
        lblMatricula.setFont(fonteLabel);
        painelPrincipal.add(lblMatricula, gbc);


        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        txtmatricula = new JTextField(15);
        txtmatricula.setFont(fonteCampo);
        painelPrincipal.add(txtmatricula, gbc);

        // config campo nome
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        JLabel lblNome = new JLabel("NOME:");
        lblNome.setFont(fonteLabel);
        painelPrincipal.add(lblNome, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        txtNome = new JTextField(15);
        txtNome.setFont(fonteCampo);
        painelPrincipal.add(txtNome, gbc);

        // config campo endereco
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        JLabel lblEndereco = new JLabel("ENDEREÇO:");
        lblEndereco.setFont(fonteLabel);
        painelPrincipal.add(lblEndereco, gbc);  

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        txtEndereco = new JTextField(15);
        txtEndereco.setFont(fonteCampo);
        painelPrincipal.add(txtEndereco, gbc);

        // config campo telefone
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        JLabel lblTelefone = new JLabel("TELEFONE:");
        lblTelefone.setFont(fonteLabel);
        painelPrincipal.add(lblTelefone, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        txtTelefone = new JTextField(15);
        txtTelefone.setFont(fonteCampo);
        painelPrincipal.add(txtTelefone, gbc);

        // config campo email
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        JLabel lblEmail = new JLabel("EMAIL:");
        lblEmail.setFont(fonteLabel);
        painelPrincipal.add(lblEmail, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        txtEmail = new JTextField(15); 
        txtEmail.setFont(fonteCampo);
        painelPrincipal.add(txtEmail, gbc);

        // config campo status        
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        JLabel lblStatus = new JLabel("Status:");
        lblStatus.setFont(fonteLabel);
        painelPrincipal.add(lblStatus, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        comboStatus = new JComboBox<>(new String[]{"Ativo", "Desvinculado"});
        comboStatus.setFont(fonteCampo);
        painelPrincipal.add(comboStatus, gbc);


        // cria um painel só para botões abaixo dos campos de preenchimento
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        //FlowLayout organiza os botões em linha
        
        // botão salvar
        btnSalvar = new JButton("Salvar"); // cria os botões
        btnSalvar.setFont(fonteCampo);
        painelBotoes.add(btnSalvar);
        btnSalvar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Salvar professor em desenvolvimento");
            // Aqui vai: validar campos, salvar no DAO, limpar form
        });

        /* botão editar - removido pois vai ficar na janela pesquisa
        btnEditar= new JButton("Editar");
        btnEditar.setFont(fonteCampo);
        painelBotoes.add(btnEditar);
        btnEditar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Editar professor em desenvolvimento");
            // Aqui vai: validar campos, atualizar no DAO, limpar form
        });*/

        // botão pesquisar
        btnPesquisar= new JButton("Pesquisar");
        btnPesquisar.setFont(fonteCampo);
        painelBotoes.add(btnPesquisar);
        btnPesquisar.addActionListener(e ->new TelaPesquisaProfessor());
        
        // botão fechar
        btnFechar= new JButton("Fechar");
        btnFechar.setFont(fonteCampo);
        painelBotoes.add(btnFechar);
        btnFechar.addActionListener(e -> dispose()); //fecha a janela 


        // adiciona o painel de botões ao painel principal
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);
        painelPrincipal.add(painelBotoes, gbc);

        // adiciona o painel principal à janela
        add(painelPrincipal, BorderLayout.CENTER);
    }   
}
