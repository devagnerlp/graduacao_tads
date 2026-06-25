package view;
import javax.swing.*;
import java.awt.*;
import model.Periodo;

public class TelaCadPeriodo extends JFrame {

    private JTextField txtId;
    private JTextField txtanoPeriodo;
    private JComboBox <String> comboStatus;
    private JButton btnSalvar;
    private JButton btnEditar;
    private JButton btnPesquisar;
    private JButton btnFechar;

    public TelaCadPeriodo(){
        //bloco cria e configura a tela
        setTitle("CADASTRO DE PERÍODO");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 200);
        setLocationRelativeTo(null);

        inicializarComponentes();// inicializa a tela

        setVisible(true); // mostra tela centralizada
    }

    public TelaCadPeriodo(Periodo periodo) {

        setTitle("EDITAR PERÍODO");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 200);
        setLocationRelativeTo(null);

        inicializarComponentes();// inicializa a tela

        // Oculta botão Pesquisar e muda Salvar para Atualizar
        btnPesquisar.setVisible(false);
        btnSalvar.setText("Atualizar");

        // Preenche os campos com os dados da disciplina
        txtanoPeriodo.setText(periodo.getNome_periodo());

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

        // config campo ano do período
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        JLabel lblanoPeriodo = new JLabel("ANO:");
        lblanoPeriodo.setFont(fonteLabel);
        painelPrincipal.add(lblanoPeriodo, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        txtanoPeriodo = new JTextField(15);
        txtanoPeriodo.setFont(fonteCampo);
        painelPrincipal.add(txtanoPeriodo, gbc);

         // config semestre do período
         gbc.gridx = 0;
         gbc.gridy = 1;
         gbc.fill = GridBagConstraints.NONE;
         gbc.weightx = 0;
         JLabel lblStatusPeriodo = new JLabel("SEMESTRE:");
         lblStatusPeriodo.setFont(fonteLabel);
         painelPrincipal.add(lblStatusPeriodo, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        comboStatus = new JComboBox<>(new String[]{"", "01", "02"}); // concatenar com ano para enviar ao banco
        painelPrincipal.add(comboStatus, gbc);

        // cria um painel só para botões abaixo dos campos de preenchimento
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        //FlowLayout organiza os botões em linha
        
        // botão salvar
        btnSalvar = new JButton("Salvar"); // cria os botões
        btnSalvar.setFont(fonteCampo);
        painelBotoes.add(btnSalvar);
        btnSalvar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Salvar período em desenvolvimento");
            // Aqui vai: validar campos, salvar no DAO, limpar form
        });

        // botão editar
        btnEditar= new JButton("Editar");
        btnEditar.setFont(fonteCampo);
        painelBotoes.add(btnEditar);
        btnEditar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Editar período em desenvolvimento");
            // Aqui vai: validar campos, atualizar no DAO, limpar form
        });

        // botão pesquisar
        btnPesquisar= new JButton("Pesquisar");
        btnPesquisar.setFont(fonteCampo);
        painelBotoes.add(btnPesquisar);
        btnPesquisar.addActionListener(e -> new TelaPesquisaPeriodo());

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




