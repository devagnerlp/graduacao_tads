package view;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableCellRenderer;
import model.Turma;
import javax.swing.*;
import java.awt.*;


public class TelaPesquisaTurma extends JFrame {
    private JTextField txtFiltro;
    private JButton btnPesquisar;   
    private JButton btnEditar;        
    private JButton btnExcluir;        
    private JButton btnFechar; 
    private JTable tabelaTurmas;      
    private DefaultTableModel modeloTabela;

    public TelaPesquisaTurma() {
        setTitle("PESQUISA DE TURMAS");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 400);
        setLocationRelativeTo(null); 
        inicializarComponentes();    
        carregarTodasTurmas();       
        setVisible(true);            
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout(10, 10));

        // Painel de filtro no topo com GridBagLayout
        JPanel painelFiltro = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Linha 1: Label e TextField
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        painelFiltro.add(new JLabel("Pesquisar por turma:"), gbc);

        gbc.gridx = 1;
        txtFiltro = new JTextField(20);
        painelFiltro.add(txtFiltro, gbc);

        // Linha 2: Botão Pesquisar (abaixo)
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        btnPesquisar = new JButton("Pesquisar");
        btnPesquisar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Pesquisar turma em desenvolvimento");
        });
        painelFiltro.add(btnPesquisar, gbc);

        add(painelFiltro, BorderLayout.NORTH);

        // Tabela
        String[] colunas = {"Série"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabelaTurmas = new JTable(modeloTabela);
        tabelaTurmas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaTurmas.getTableHeader().setReorderingAllowed(false);
        tabelaTurmas.setDefaultEditor(Object.class, null);

        // Centraliza os dados nas células
        DefaultTableCellRenderer centralizador = new DefaultTableCellRenderer();
        centralizador.setHorizontalAlignment(SwingConstants.CENTER);
        tabelaTurmas.setDefaultRenderer(Object.class, centralizador);

        JScrollPane scrollPane = new JScrollPane(tabelaTurmas);
        add(scrollPane, BorderLayout.CENTER);

        // Listener de seleção
        tabelaTurmas.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int linhaSelecionada = tabelaTurmas.getSelectedRow();
                boolean temLinhaSelected = (linhaSelecionada != -1);
                btnEditar.setEnabled(temLinhaSelected);
                btnExcluir.setEnabled(temLinhaSelected);
            }
        });

        // Painel inferior
        JPanel painelInferior = new JPanel(new BorderLayout());
        JLabel lblInfo = new JLabel("Selecione uma turma para habilitar edição/exclusão", SwingConstants.CENTER);
        lblInfo.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        painelInferior.add(lblInfo, BorderLayout.NORTH);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnEditar = new JButton("Editar");
        btnEditar.setEnabled(false);
        btnEditar.addActionListener(e -> new TelaCadTurma(getTurmaSelecionada()));
            
        painelBotoes.add(btnEditar);

        btnExcluir = new JButton("Excluir");
        btnExcluir.setEnabled(false);
        btnExcluir.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Excluir turma em desenvolvimento");
        });
        painelBotoes.add(btnExcluir);

        btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> dispose());
        painelBotoes.add(btnFechar);

        painelInferior.add(painelBotoes, BorderLayout.CENTER);
        add(painelInferior, BorderLayout.SOUTH);
    }

    private void carregarTodasTurmas() {
        modeloTabela.setRowCount(0);

        try {
            // Método provisório até que o TurmaController esteja pronto
            List<Turma> turmas = new ArrayList<>();

            // Turma 1
            Turma t1 = new Turma();
            t1.setNome_turma("Primeira Série");
            turmas.add(t1);

            // Turma 2
            Turma t2 = new Turma();
            t2.setNome_turma("Segunda Série");
            turmas.add(t2);

            // Turma 3
            Turma t3 = new Turma();
            t3.setNome_turma("Terceira Série");
            turmas.add(t3);

            // Preenche a tabela
            for (Turma t : turmas) {
                Object[] linha = { t.getNome_turma() };
                modeloTabela.addRow(linha);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Erro ao carregar turmas: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }

        /* Método que vai funcionar na versão final - voltar quando TurmaController estiver ok
        try {
            List<Turma> turmas = TurmaController.listarTodasTurmas();
            for (Turma t : turmas) {
                Object[] linha = {t.getNome_turma()};
                modeloTabela.addRow(linha);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Erro ao carregar turmas: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
        */
    }

    private Turma getTurmaSelecionada() {
        int linhaSelecionada = tabelaTurmas.getSelectedRow();

        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma turma na tabela");
            return null;
        }

        // Pega os dados da linha selecionada
        String nome_turma = tabelaTurmas.getValueAt(linhaSelecionada, 0).toString();        

        // Cria objeto Turma com os dados
        Turma turma = new Turma();
        turma.setNome_turma(nome_turma);        

        return turma;
    }
}

