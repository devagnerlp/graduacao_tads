package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal() {// construtor da tela principal    
    

        // bloco para configuração tela principal
        setTitle("GERENCIALMENTO DE NOTAS ESCOLARES");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 400);
        setLocationRelativeTo(null);

        criarMenu(); //chama o método que cria e configura o menu

        criarConteudoPrincipal(); // chama o método que cria o conteúdo principal

        setVisible(true); //deixa janela visível    
    }

    private void criarMenu(){ // método que cria o menu
        Font fonteMenu = new Font("ARIAL", Font.BOLD, 14);

        JMenuBar menuBar = new JMenuBar(); // cria a barra de menu
        JMenu menuAdm = new JMenu("Administrador"); // insere a opção administrador

        JMenu subMenuCadastros = new JMenu("Cadastros"); // submenu cadastros
        JMenuItem itemAluno = new JMenuItem("Aluno");
        JMenuItem itemProfessor = new JMenuItem("Professor");
        JMenuItem itemDisciplina = new JMenuItem("Disciplina");
        JMenuItem itemTurma = new JMenuItem("Turma");
        JMenuItem itemPeriodo = new JMenuItem("Período");               

        subMenuCadastros.add(itemAluno);
        itemAluno.setFont(fonteMenu);
        subMenuCadastros.add(itemProfessor);
        itemProfessor.setFont(fonteMenu);
        subMenuCadastros.add(itemDisciplina);
        itemDisciplina.setFont(fonteMenu);
        subMenuCadastros.add(itemTurma);
        itemTurma.setFont(fonteMenu);
        subMenuCadastros.add(itemPeriodo);
        itemPeriodo.setFont(fonteMenu);

        itemAluno.addActionListener(e -> new TelaCadAluno());
        itemProfessor.addActionListener(e -> new TelaCadProfessor());
        itemDisciplina.addActionListener(e -> new TelaCadDisciplina());
        itemTurma.addActionListener(e -> new TelaCadTurma());
        itemPeriodo.addActionListener(e -> new TelaCadPeriodo());

        JMenu subMenuDiarios = new JMenu("Diários");
        JMenuItem itemCriarDiario = new JMenuItem("Criar Diário");
        JMenuItem itemGerenciarDiario = new JMenuItem("Gerenciar Diários");

        subMenuDiarios.add(itemCriarDiario);
        itemCriarDiario.setFont(fonteMenu);
        subMenuDiarios.add(itemGerenciarDiario);
        itemGerenciarDiario.setFont(fonteMenu);
            
        menuAdm.add(subMenuCadastros);
        subMenuCadastros.setFont(fonteMenu);
        menuAdm.add(subMenuDiarios);
        subMenuDiarios.setFont(fonteMenu);
        menuBar.add(menuAdm);
        menuAdm.setFont(fonteMenu);
        setJMenuBar(menuBar);
}


    private void criarConteudoPrincipal() {// configura como componentes vão ser organizados na tela
        JPanel painelCentral = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL; // define como componente preenche o espaço
        gbc.anchor = GridBagConstraints.CENTER; // centraliza o form na tela

        // Label centralizada
        JLabel lblTitulo = new JLabel("ESCOLHA UMA OPÇÃO");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        painelCentral.add(lblTitulo, gbc); // adiciona o rótulo a tela

        // Botão Consultar
        JButton btnConsultar = new JButton("CONSULTAR NOTAS");
        btnConsultar.setPreferredSize(new Dimension(250, 50));
        btnConsultar.setFont(new Font("Arial", Font.BOLD, 14));
        btnConsultar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //inserir o método para consultar as notas;
            }
        });
        gbc.gridy = 1;
        painelCentral.add(btnConsultar, gbc);

        // Botão Editar
        JButton btnEditar = new JButton("EDITAR NOTAS");
        btnEditar.setPreferredSize(new Dimension(250, 50));
        btnEditar.setFont(new Font("Arial", Font.BOLD, 14));
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //inserir o método para editar as notas;
            }
            
        });
        gbc.gridy = 2;
        painelCentral.add(btnEditar, gbc);

        add(painelCentral, BorderLayout.CENTER);


        // Botão sair
        JButton btnFechar = new JButton("Fechar");
        btnFechar.setPreferredSize(new Dimension(100, 40));
        btnFechar.setFont(new Font("Arial", Font.BOLD, 14));
        btnFechar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();                       
            }
            
        });
        gbc.gridy = 3;
        painelCentral.add(btnFechar, gbc);

        add(painelCentral, BorderLayout.CENTER);
    }

}
