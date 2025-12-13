package view;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import model.Professor;
import javax.swing.*;
import java.awt.*;

public class TelaPesquisaProfessor extends JFrame {
    private JTextField txtFiltro;
    private JButton btnPesquisar;   
    private JButton btnEditar;        
    private JButton btnExcluir;        
    private JButton btnFechar; 
    private JTable tabelaProfessores;      
    private DefaultTableModel modeloTabela;

    public TelaPesquisaProfessor() {
        // Configurar a janela
        setTitle("PESQUISA DE PROFESSORES");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 600);
        setLocationRelativeTo(null); 
        inicializarComponentes();    
        carregarTodosProfessores();       
        setVisible(true);            
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout(10, 10));

        // Painel de filtro no topo
        JPanel painelFiltro = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        painelFiltro.add(new JLabel("Pesquisar por nome:"));
        txtFiltro = new JTextField(30);
        painelFiltro.add(txtFiltro);
        btnPesquisar = new JButton("Pesquisar");
        btnPesquisar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Pesquisar professor em desenvolvimento");
        });
        painelFiltro.add(btnPesquisar);
        add(painelFiltro, BorderLayout.NORTH);

        // Tabela
        String[] colunas = {"Matrícula", "Nome", "Telefone", "E-mail"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabelaProfessores = new JTable(modeloTabela);
        tabelaProfessores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaProfessores.getTableHeader().setReorderingAllowed(false);
        tabelaProfessores.setDefaultEditor(Object.class, null);

        // Centraliza Matrícula
        DefaultTableCellRenderer centralizador = new DefaultTableCellRenderer();
        centralizador.setHorizontalAlignment(SwingConstants.CENTER);
        tabelaProfessores.getColumnModel().getColumn(0).setCellRenderer(centralizador);

        // Ajusta largura das colunas
        tabelaProfessores.getColumnModel().getColumn(0).setPreferredWidth(100); // Matrícula
        tabelaProfessores.getColumnModel().getColumn(1).setPreferredWidth(250); // Nome
        tabelaProfessores.getColumnModel().getColumn(2).setPreferredWidth(120); // Telefone
        tabelaProfessores.getColumnModel().getColumn(3).setPreferredWidth(230); // E-mail

        JScrollPane scrollPane = new JScrollPane(tabelaProfessores);
        add(scrollPane, BorderLayout.CENTER);

        // Listener de seleção
        tabelaProfessores.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int linhaSelecionada = tabelaProfessores.getSelectedRow();
                boolean temLinhaSelected = (linhaSelecionada != -1);
                btnEditar.setEnabled(temLinhaSelected);
                btnExcluir.setEnabled(temLinhaSelected);
            }
        });

        // Painel inferior
        JPanel painelInferior = new JPanel(new BorderLayout());
        JLabel lblInfo = new JLabel("Selecione um professor para habilitar edição/exclusão", SwingConstants.CENTER);
        lblInfo.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        painelInferior.add(lblInfo, BorderLayout.NORTH);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnEditar = new JButton("Editar");
        btnEditar.setEnabled(false);
        btnEditar.addActionListener(e -> new TelaCadProfessor(getProfessorSelecionado()));
        painelBotoes.add(btnEditar);

        btnExcluir = new JButton("Excluir");
        btnExcluir.setEnabled(false);
        btnExcluir.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Excluir professor em desenvolvimento");
        });
        painelBotoes.add(btnExcluir);

        btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> dispose());
        painelBotoes.add(btnFechar);

        painelInferior.add(painelBotoes, BorderLayout.CENTER);
        add(painelInferior, BorderLayout.SOUTH);
    }

    private void carregarTodosProfessores() {
        modeloTabela.setRowCount(0);

        try {
            List<Professor> professores = new ArrayList<>();

            Professor p1 = new Professor();
            p1.setMatricula(3001);
            p1.setNome("Carlos Mendes");
            p1.setTelefone("(11) 97654-3210");
            p1.setEmail("carlos.mendes@escola.com");
            professores.add(p1);

            Professor p2 = new Professor();
            p2.setMatricula(3002);
            p2.setNome("Fernanda Lima");
            p2.setTelefone("(11) 98765-1234");
            p2.setEmail("fernanda.lima@escola.com");
            professores.add(p2);

            Professor p3 = new Professor();
            p3.setMatricula(3003);
            p3.setNome("Roberto Alves");
            p3.setTelefone("(11) 99123-4567");
            p3.setEmail("roberto.alves@escola.com");
            professores.add(p3);

            for (Professor p : professores) {
                Object[] linha = {
                    p.getMatricula(),
                    p.getNome(),
                    p.getTelefone(),
                    p.getEmail()
                };
                modeloTabela.addRow(linha);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Erro ao carregar professores: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }

        /* Método que vai funcionar na versão final - voltar quando ProfessorController estiver ok
        try {
            List<Professor> professores = ProfessorController.listarTodosProfessores();

            for (Professor p : professores) {
                Object[] linha = {
                    p.getMatricula(),
                    p.getNome(),
                    p.getTelefone(),
                    p.getEmail()
                };
                modeloTabela.addRow(linha);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Erro ao carregar professores: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
        */
    }

    private Professor getProfessorSelecionado() {
        int linhaSelecionada = tabelaProfessores.getSelectedRow();

        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um professor na tabela");
            return null;
        }

        // Pega os dados da linha selecionada
        int matricula = Integer.parseInt(tabelaProfessores.getValueAt(linhaSelecionada, 0).toString());
        String nome = tabelaProfessores.getValueAt(linhaSelecionada, 1).toString();
        String telefone = tabelaProfessores.getValueAt(linhaSelecionada, 2).toString();
        String email = tabelaProfessores.getValueAt(linhaSelecionada, 3).toString();

        // Cria objeto Professor com os dados
        Professor professor = new Professor();
        professor.setMatricula(matricula);
        professor.setNome(nome);
        professor.setTelefone(telefone);
        professor.setEmail(email);

        return professor;
    }
}
