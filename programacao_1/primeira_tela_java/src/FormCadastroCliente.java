import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormCadastroCliente extends JFrame {

    // Componentes da interface
    private JTextField txtId;
    private JTextField txtNome;
    private JTextField txtEndereco;
    private JTextField txtTelefone;
    private JButton btnSalvar;
    private JButton btnLimpar;
    private JButton btnSair;

    public FormCadastroCliente() {
        // Configurações da janela
        setTitle("Cadastro de Cliente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Centralizar na tela

        // Inicializar componentes
        inicializarComponentes();

        // Tornar a janela visível
        setVisible(true);
    }

    private void inicializarComponentes() {
        // Criar painel principal
        JPanel painelPrincipal = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Configurações gerais do GridBagConstraints
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Campo ID
        gbc.gridx = 0;
        gbc.gridy = 0;
        painelPrincipal.add(new JLabel("ID:"), gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        txtId = new JTextField(15);
        painelPrincipal.add(txtId, gbc);

        // Campo Nome
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        painelPrincipal.add(new JLabel("Nome:"), gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        txtNome = new JTextField(15);
        painelPrincipal.add(txtNome, gbc);

        // Campo Endereço
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        painelPrincipal.add(new JLabel("Endereço:"), gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        txtEndereco = new JTextField(15);
        painelPrincipal.add(txtEndereco, gbc);

        // Campo Telefone
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        painelPrincipal.add(new JLabel("Telefone:"), gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        txtTelefone = new JTextField(15);
        painelPrincipal.add(txtTelefone, gbc);

        // Painel de botões
        JPanel painelBotoes = new JPanel(new FlowLayout());

        btnSalvar = new JButton("Salvar");
        btnLimpar = new JButton("Limpar");
        btnSair = new JButton("Sair");

        // Adicionar listeners aos botões
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarCliente();
            }
        });

        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });

        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnLimpar);
        painelBotoes.add(btnSair);

        // Adicionar painel de botões ao painel principal
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        painelPrincipal.add(painelBotoes, gbc);

        // Adicionar painel principal à janela
        add(painelPrincipal);
    }

    private void salvarCliente() {
        // Validar se os campos estão preenchidos
        if (txtId.getText().trim().isEmpty() ||
                txtNome.getText().trim().isEmpty() ||
                txtEndereco.getText().trim().isEmpty() ||
                txtTelefone.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Por favor, preencha todos os campos!",
                    "Campos obrigatórios",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Exibir dados salvos (simulação)
        String mensagem = String.format(
                "Cliente salvo com sucesso!\n\n" +
                        "ID: %s\n" +
                        "Nome: %s\n" +
                        "Endereço: %s\n" +
                        "Telefone: %s",
                txtId.getText(),
                txtNome.getText(),
                txtEndereco.getText(),
                txtTelefone.getText());

        JOptionPane.showMessageDialog(this, mensagem, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    private void limparCampos() {
        txtId.setText("");
        txtNome.setText("");
        txtEndereco.setText("");
        txtTelefone.setText("");
        txtId.requestFocus(); // Focar no primeiro campo
    }

    // Método main para executar a aplicação
    public static void main(String[] args) {
        // Executar na thread de eventos do Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FormCadastroCliente();
            }
        });
    }
}
