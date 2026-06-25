package view;
import javax.swing.*;

import model.Aluno;

import java.awt.*;


public class TelaCadAluno extends JFrame {

    private JTextField txtId;
    private JTextField txtmatricula;
    private JTextField txtNome;
    private JTextField txtEndereco;
    private JTextField txtTelefone;
    private JTextField txtEmail;
    private JTextField txtDataNascimento;
    private JTextField txtMae;
    private JTextField txtPai;
    private JComboBox<String> comboStatus;
    private JButton btnSalvar;
    //private JButton btnEditar; - removido pois vai ficar na janela pesquisa
    private JButton btnPesquisar;
    private JButton btnFechar;

    public TelaCadAluno(){
        //bloco cria e configura a tela
        setTitle("CADASTRO DE ALUNO");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 400);
        setLocationRelativeTo(null);

        inicializarComponentes();// inicializa a tela

        
        txtmatricula.setEnabled(false);
        txtmatricula.setText("Automático"); // PRECISO DESENVOLVER lógica para gerar o número da matrícula
        txtmatricula.setForeground(Color.GRAY); //campo matricula vai aparecer desabilitado

        setVisible(true); // mostra tela centralizada
    }

    public TelaCadAluno(Aluno aluno) {
        // Configura a tela
        setTitle("EDITAR ALUNO");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 400);
        setLocationRelativeTo(null);

        inicializarComponentes();

        // Oculta botão Pesquisar e muda Salvar para Atualizar
        btnPesquisar.setVisible(false);
        btnSalvar.setText("Atualizar");

        // Preenche os campos com os dados do aluno
        txtmatricula.setText(String.valueOf(aluno.getMatricula()));
        txtmatricula.setEnabled(false);
        txtmatricula.setForeground(Color.BLACK);

        txtNome.setText(aluno.getNome());
        txtMae.setText(aluno.getNome_mae());
        txtPai.setText(aluno.getNome_pai());
        txtEndereco.setText(aluno.getEndereco());
        txtTelefone.setText(aluno.getTelefone());
        txtEmail.setText(aluno.getEmail());
        txtDataNascimento.setText(aluno.getData_nascimento());
        txtDataNascimento.setForeground(Color.BLACK);
        comboStatus.setSelectedItem(aluno.getStatus());

        setVisible(true);
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

        // config campo nome mae
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        JLabel lblMae = new JLabel("NOME DA MÃE:");
        lblMae.setFont(fonteLabel);
        painelPrincipal.add(lblMae, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        txtMae = new JTextField(15); 
        txtMae.setFont(fonteCampo);
        painelPrincipal.add(txtMae, gbc);

        // config campo nome mae
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        JLabel lblPai = new JLabel("NOME DO PAI:");
        lblPai.setFont(fonteLabel);
        painelPrincipal.add(lblPai, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        txtPai = new JTextField(15); 
        txtPai.setFont(fonteCampo);
        painelPrincipal.add(txtPai, gbc);

        // config campo endereco
        gbc.gridx = 0;
        gbc.gridy = 4;
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
        gbc.gridy = 5;
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
        gbc.gridy = 6;
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

        // config campo data nascimento
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        JLabel lblDataNascimento = new JLabel("NASCIMENTO:");
        lblDataNascimento.setFont(fonteLabel);
        painelPrincipal.add(lblDataNascimento, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        txtDataNascimento = new JTextField(5);
        txtDataNascimento.setFont(fonteCampo);
        txtDataNascimento.setText("DD/MM/AAAA");
        txtDataNascimento.setForeground(Color.GRAY); // vai colocar formato da data dentro do campo
        txtDataNascimento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (txtDataNascimento.getText().equals("DD/MM/AAAA")) {
                    txtDataNascimento.setText("");
                    txtDataNascimento.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (txtDataNascimento.getText().isEmpty()) {
                    txtDataNascimento.setText("dd/mm/aaaa");
                    txtDataNascimento.setForeground(Color.GRAY);
                }
            }
        });
        painelPrincipal.add(txtDataNascimento, gbc);

        // config campo status        
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        JLabel lblStatus = new JLabel("Status:");
        lblStatus.setFont(fonteLabel);
        painelPrincipal.add(lblStatus, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        comboStatus = new JComboBox<>(new String[]{"Matriculado", "Transferido", "Egresso", "Evadido"});
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
            JOptionPane.showMessageDialog(this, "Salvar aluno em desenvolvimento");
            // Aqui vai: validar campos, salvar no DAO, limpar form
        });

        /* botão editar - removido pois vai ficar na janela pesquisa
        btnEditar= new JButton("Editar");
        btnEditar.setFont(fonteCampo);
        painelBotoes.add(btnEditar);
        btnEditar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Editar aluno em desenvolvimento");
            // Aqui vai: validar campos, atualizar no DAO, limpar form
        });*/

        // botão pesquisar
        btnPesquisar= new JButton("Pesquisar");
        btnPesquisar.setFont(fonteCampo);
        painelBotoes.add(btnPesquisar);
        btnPesquisar.addActionListener(e -> new TelaPesquisaAluno());
            /*{
            JOptionPane.showMessageDialog(this, "Pesquisar aluno em desenvolvimento");
            // Aqui vai: abrir tela de pesquisa de aluno
        });*/

        // botão fechar
        btnFechar= new JButton("Fechar");
        btnFechar.setFont(fonteCampo);
        painelBotoes.add(btnFechar);
        btnFechar.addActionListener(e -> dispose()); //fecha a janela 


        // adiciona o painel de botões ao painel principal
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);
        painelPrincipal.add(painelBotoes, gbc);

        // adiciona o painel principal à janela
        add(painelPrincipal, BorderLayout.CENTER);   
    

    }

}
