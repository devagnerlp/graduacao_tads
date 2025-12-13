package view;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import model.Aluno;
import javax.swing.*;
import java.awt.*;


public class TelaPesquisaAluno extends JFrame {

    
    private JTextField txtFiltro;
    private JButton btnPesquisar;   
    private JButton btnEditar;        
    private JButton btnExcluir;        
    private JButton btnFechar; 
    private JTable tabelaAlunos;      
    private DefaultTableModel modeloTabela;



    public TelaPesquisaAluno() {
        // Configurar a janela
        setTitle("PESQUISA DE ALUNOS");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 600);
        setLocationRelativeTo(null); 

        inicializarComponentes();    
        carregarTodosAlunos();       

        setVisible(true);            
    }
    
    private void inicializarComponentes() {
        // Divide a janela em 5 regiões (North, South, East, West, Center)
        setLayout(new BorderLayout(10, 10));

        // cria o painel para pesquisa no topo
        JPanel painelFiltro = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));

        painelFiltro.add(new JLabel("Pesquisar por nome:"));

        txtFiltro = new JTextField(30); // Campo de texto para digitar o nome
        painelFiltro.add(txtFiltro);

        btnPesquisar = new JButton("Pesquisar");
        btnPesquisar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "excluir aluno em desenvolvimento");
            // trecho provisório ate´desenvover o método // pesquisarAlunos()); 
        });
        painelFiltro.add(btnPesquisar);

        add(painelFiltro, BorderLayout.NORTH); // Adiciona no topo da janela

        // tabela para mostrar os alunos
        //  as colunas da tabela (sem ID visível)
        String[] colunas = {"Matrícula", "Nome", "Telefone", "Responsável"};
        modeloTabela = new DefaultTableModel(colunas, 0);

        // Cria a tabela com o modelo
        tabelaAlunos = new JTable(modeloTabela);

        // Permite selecionar apenas uma linha por vez
        tabelaAlunos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Não permite reordenar as colunas
        tabelaAlunos.getTableHeader().setReorderingAllowed(false);

        // Bloqueia edição direta nas células
        tabelaAlunos.setDefaultEditor(Object.class, null);

        // Centraliza o conteúdo da coluna Matrícula
        DefaultTableCellRenderer centralizador = new DefaultTableCellRenderer();
        centralizador.setHorizontalAlignment(SwingConstants.CENTER);
        tabelaAlunos.getColumnModel().getColumn(0).setCellRenderer(centralizador);

        // Ajusta largura das colunas
        tabelaAlunos.getColumnModel().getColumn(0).setPreferredWidth(100); // Matrícula
        tabelaAlunos.getColumnModel().getColumn(1).setPreferredWidth(250); // Nome
        tabelaAlunos.getColumnModel().getColumn(2).setPreferredWidth(120); // Telefone
        tabelaAlunos.getColumnModel().getColumn(3).setPreferredWidth(250); // Mãe

        // Adiciona barra de rolagem
        JScrollPane scrollPane = new JScrollPane(tabelaAlunos);
        add(scrollPane, BorderLayout.CENTER);

        // Habilita/desabilita botões quando seleciona/desseleciona uma linha
        tabelaAlunos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) { // Evita evento duplicado
                int linhaSelecionada = tabelaAlunos.getSelectedRow();
                boolean temLinhaSelected = (linhaSelecionada != -1);
                btnEditar.setEnabled(temLinhaSelected);
                btnExcluir.setEnabled(temLinhaSelected);
            }
        });

        // painel inferior para botões e label informativo
        JPanel painelInferior = new JPanel(new BorderLayout());

        // Label informativo
        JLabel lblInfo = new JLabel("Selecione um aluno para habilitar edição/exclusão", SwingConstants.CENTER);
        lblInfo.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        painelInferior.add(lblInfo, BorderLayout.NORTH);

        // Painel de botões
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        btnEditar = new JButton("Editar");
        btnEditar.setEnabled(false); // Desabilitado até selecionar uma linha
        btnEditar.addActionListener(e -> new TelaCadAluno(getAlunoSelecionado()));
            /*{
            JOptionPane.showMessageDialog(this, "editar aluno em desenvolvimento");
            // trecho provisório ate´desenvover a função //abrirTelaEdicao()); 
        });*/
        painelBotoes.add(btnEditar);

        btnExcluir = new JButton("Excluir");
        btnExcluir.setEnabled(false); // Desabilitado até selecionar uma linha
        btnExcluir.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "excluir aluno em desenvolvimento");
            // trecho provisório ate´desenvover a função //excluirAluno()); 
        }); 
        painelBotoes.add(btnExcluir);

        btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> dispose()); // Fecha a janela
        painelBotoes.add(btnFechar);

        painelInferior.add(painelBotoes, BorderLayout.CENTER);

        add(painelInferior, BorderLayout.SOUTH); // Adiciona na base da janela
    }
   
    private void carregarTodosAlunos() {
        
        modeloTabela.setRowCount(0); // Limpa a tabela

        //Método provisório até que o AlunoController esteja pronto 

        try {  
            List<Aluno> alunos = new ArrayList<>();

            Aluno a1 = new Aluno();
            a1.setMatricula(2024001);
            a1.setNome("João Silva");
            a1.setTelefone("(11) 98765-4321");
            a1.setNome_mae("Maria Silva");
            alunos.add(a1);

            Aluno a2 = new Aluno();
            a2.setMatricula(2024002);
            a2.setNome("Ana Costa");
            a2.setTelefone("(11) 91234-5678");
            a2.setNome_mae("Carla Costa");
            alunos.add(a2);

            Aluno a3 = new Aluno();
            a3.setMatricula(2024003);
            a3.setNome("Pedro Santos");
            a3.setTelefone("(11) 99876-5432");
            a3.setNome_mae("Lucia Santos");
            alunos.add(a3);

            // Preenche a tabela com os dados mockados
            for (Aluno a : alunos) {
                Object[] linha = {
                    a.getMatricula(),    
                    a.getNome(),         
                    a.getTelefone(),     
                    a.getNome_mae()           
                };
                modeloTabela.addRow(linha);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Erro ao carregar alunos: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }

        /* Método que vai funcionar na versão final - voltar eles quando AlunoController estiver ok 
        try {
            // Chama o controller para buscar os dados
            List<Aluno> alunos = AlunoController.listarTodosAlunos();

            // Preenche a tabela com os dados recebidos
            for (Aluno a : alunos) {
                Object[] linha = {
                    a.getMatricula(),    
                    a.getNome(),         
                    a.getTelefone(),     
                    a.getNome_mae()           
                };
                modeloTabela.addRow(linha);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Erro ao carregar alunos: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }*/
    private Aluno getAlunoSelecionado() {
        int linhaSelecionada = tabelaAlunos.getSelectedRow();

        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um aluno na tabela");
            return null;
        }

        // Pega os dados da linha selecionada
        String matricula = tabelaAlunos.getValueAt(linhaSelecionada, 0).toString();
        String nome = tabelaAlunos.getValueAt(linhaSelecionada, 1).toString();
        String telefone = tabelaAlunos.getValueAt(linhaSelecionada, 2).toString();
        String nome_mae = tabelaAlunos.getValueAt(linhaSelecionada, 3).toString();

        // Cria objeto Aluno com os dados
        Aluno aluno = new Aluno();
        aluno.setMatricula(Integer.parseInt(matricula));
        aluno.setNome(nome);
        aluno.setTelefone(telefone);
        aluno.setNome_mae(nome_mae);

        // INCOOMPLETO:
        // buscar dados completos do banco (mãe, pai, endereço, email, data_nascimento, status)

        return aluno;
    }
}
