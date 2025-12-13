package view;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import model.Disciplina;
import model.Periodo;
import javax.swing.*;
import java.awt.*;

public class TelaPesquisaPeriodo extends JFrame {
    private JTextField txtFiltro;
    private JButton btnPesquisar;   
    private JButton btnEditar;        
    private JButton btnExcluir;        
    private JButton btnFechar; 
    private JTable tabelaPeriodos;      
    private DefaultTableModel modeloTabela;

    public TelaPesquisaPeriodo() {
        setTitle("PESQUISA DE PERÍODOS");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null); 
        inicializarComponentes();    
        carregarTodosPeriodos();       
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
        painelFiltro.add(new JLabel("Pesquisar por ano:"), gbc);

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
            JOptionPane.showMessageDialog(this, "Pesquisar período em desenvolvimento");
        });
        painelFiltro.add(btnPesquisar, gbc);

        add(painelFiltro, BorderLayout.NORTH);

        // Tabela
        String[] colunas = {"Período"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabelaPeriodos = new JTable(modeloTabela);
        tabelaPeriodos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaPeriodos.getTableHeader().setReorderingAllowed(false);
        tabelaPeriodos.setDefaultEditor(Object.class, null);

        // Centraliza os dados nas células
        DefaultTableCellRenderer centralizador = new DefaultTableCellRenderer();
        centralizador.setHorizontalAlignment(SwingConstants.CENTER);
        tabelaPeriodos.setDefaultRenderer(Object.class, centralizador);

        JScrollPane scrollPane = new JScrollPane(tabelaPeriodos);
        add(scrollPane, BorderLayout.CENTER);

        // Listener de seleção
        tabelaPeriodos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int linhaSelecionada = tabelaPeriodos.getSelectedRow();
                boolean temLinhaSelected = (linhaSelecionada != -1);
                btnEditar.setEnabled(temLinhaSelected);
                btnExcluir.setEnabled(temLinhaSelected);
            }
        });

        // Painel inferior
        JPanel painelInferior = new JPanel(new BorderLayout());
        JLabel lblInfo = new JLabel("Selecione um período para habilitar edição/exclusão", SwingConstants.CENTER);
        lblInfo.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        painelInferior.add(lblInfo, BorderLayout.NORTH);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        btnEditar = new JButton("Editar");
        btnEditar.setEnabled(false);
        btnEditar.addActionListener(e -> new TelaCadPeriodo(getPeriodoSelecionado()));           
        painelBotoes.add(btnEditar);

        btnExcluir = new JButton("Excluir");
        btnExcluir.setEnabled(false);
        btnExcluir.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Excluir período em desenvolvimento");
        });
        painelBotoes.add(btnExcluir);

        btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> dispose());
        painelBotoes.add(btnFechar);

        painelInferior.add(painelBotoes, BorderLayout.CENTER);
        add(painelInferior, BorderLayout.SOUTH);
    }

    private void carregarTodosPeriodos() {
        modeloTabela.setRowCount(0);

        try {
            // Método provisório até que o PeriodoController esteja pronto
            List<Periodo> periodos = new ArrayList<>();

            Periodo per1 = new Periodo();
            per1.setNome_periodo("2024/1");
            periodos.add(per1);

            Periodo per2 = new Periodo();
            per2.setNome_periodo("2024/2");
            periodos.add(per2);

            Periodo per3 = new Periodo();
            per3.setNome_periodo("2025/1");
            periodos.add(per3);

            // Preenche a tabela
            for (Periodo p : periodos) {
                Object[] linha = {p.getNome_periodo()};
                modeloTabela.addRow(linha);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Erro ao carregar períodos: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }

        /* Método que vai funcionar na versão final - voltar quando PeriodoController estiver ok
        try {
            List<Periodo> periodos = PeriodoController.listarTodosPeriodos();

            for (Periodo p : periodos) {
                Object[] linha = {p.getNome_periodo()};
                modeloTabela.addRow(linha);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Erro ao carregar períodos: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
        */
    }

    private Periodo getPeriodoSelecionado() {
        int linhaSelecionada = tabelaPeriodos.getSelectedRow();

         if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um período na tabela");
            return null;
        }

         String nome_periodo = tabelaPeriodos.getValueAt(linhaSelecionada, 0).toString();

         Periodo periodo = new Periodo();
         periodo.setNome_periodo(nome_periodo);

        return periodo;
    }

}
