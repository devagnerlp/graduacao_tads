package view;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import model.Disciplina;
import javax.swing.*;
import java.awt.*;

public class TelaPesquisaDisciplina extends JFrame {
    private JTextField txtFiltro;
    private JButton btnPesquisar;   
    private JButton btnEditar;        
    private JButton btnExcluir;        
    private JButton btnFechar; 
    private JTable tabelaDisciplinas;      
    private DefaultTableModel modeloTabela;

    public TelaPesquisaDisciplina() {
        setTitle("PESQUISA DE DISCIPLINAS");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null); 
        inicializarComponentes();    
        carregarTodasDisciplinas();       
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
        painelFiltro.add(new JLabel("Pesquisar por nome:"), gbc);

        gbc.gridx = 1;
        txtFiltro = new JTextField(20 );
        painelFiltro.add(txtFiltro, gbc);

        // Linha 2: Botão Pesquisar (abaixo)
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        btnPesquisar = new JButton("Pesquisar");
        btnPesquisar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Pesquisar disciplina em desenvolvimento");
        });
        painelFiltro.add(btnPesquisar, gbc);

        add(painelFiltro, BorderLayout.NORTH);


        // Tabela
        String[] colunas = {"Nome"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabelaDisciplinas = new JTable(modeloTabela);
        tabelaDisciplinas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaDisciplinas.getTableHeader().setReorderingAllowed(false);
        tabelaDisciplinas.setDefaultEditor(Object.class, null);

        // Centraliza os dados nas células
        DefaultTableCellRenderer centralizador = new DefaultTableCellRenderer();
        centralizador.setHorizontalAlignment(SwingConstants.CENTER);
        tabelaDisciplinas.setDefaultRenderer(Object.class, centralizador);

        JScrollPane scrollPane = new JScrollPane(tabelaDisciplinas);
        add(scrollPane, BorderLayout.CENTER);

        // Listener de seleção
        tabelaDisciplinas.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int linhaSelecionada = tabelaDisciplinas.getSelectedRow();
                boolean temLinhaSelected = (linhaSelecionada != -1);
                btnEditar.setEnabled(temLinhaSelected);
                btnExcluir.setEnabled(temLinhaSelected);
            }
        });

        // Painel inferior
        JPanel painelInferior = new JPanel(new BorderLayout());
        JLabel lblInfo = new JLabel("Selecione uma disciplina para habilitar edição/exclusão", SwingConstants.CENTER);
        lblInfo.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        painelInferior.add(lblInfo, BorderLayout.NORTH);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnEditar = new JButton("Editar");
        btnEditar.setEnabled(false);
        btnEditar.addActionListener(e -> new TelaCadDisciplina(getDisciplinaSelecionada()));
          
        painelBotoes.add(btnEditar);

        btnExcluir = new JButton("Excluir");
        btnExcluir.setEnabled(false);
        btnExcluir.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Excluir disciplina em desenvolvimento");
        });
        painelBotoes.add(btnExcluir);

        btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> dispose());
        painelBotoes.add(btnFechar);

        painelInferior.add(painelBotoes, BorderLayout.CENTER);
        add(painelInferior, BorderLayout.SOUTH);
    }

    private void carregarTodasDisciplinas() {
        modeloTabela.setRowCount(0);

        try {
            List<Disciplina> disciplinas = new ArrayList<>();

            Disciplina d1 = new Disciplina();
            d1.setNome_disciplina("Matemática");
            disciplinas.add(d1);

            Disciplina d2 = new Disciplina();
            d2.setNome_disciplina("Português");
            disciplinas.add(d2);

            Disciplina d3 = new Disciplina();
            d3.setNome_disciplina("História");
            disciplinas.add(d3);

            for (Disciplina d : disciplinas) {
                Object[] linha = {d.getNome_disciplina()};
                modeloTabela.addRow(linha);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Erro ao carregar disciplinas: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }

        /* Método que vai funcionar na versão final - voltar quando DisciplinaController estiver ok
        try {
            // Chama o controller para buscar os dados
            List<Disciplina> disciplinas = DisciplinaController.listarTodasDisciplinas();

            // Preenche a tabela com os dados recebidos
            for (Disciplina d : disciplinas) {
                Object[] linha = {d.getNome()};
                modeloTabela.addRow(linha);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Erro ao carregar disciplinas: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
        */
    }
    private Disciplina getDisciplinaSelecionada() {
        int linhaSelecionada = tabelaDisciplinas.getSelectedRow();

         if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma disciplina na tabela");
            return null;
        }

         String nome_disciplina = tabelaDisciplinas.getValueAt(linhaSelecionada, 0).toString();

         Disciplina disciplina = new Disciplina();
         disciplina.setNome_disciplina(nome_disciplina);

        return disciplina;
    }     

}
