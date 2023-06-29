package model.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.UsuarioController;
import model.vo.UsuarioVO;

import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.Cursor;

import controller.*;

public class MenuPrincipal extends JFrame {

	private JPanel contentPane;
	
	// Paineis separados
	private PainelVerConta painelVerConta;
	private PainelAlterarDados painelAlterarDados;
	private PainelConsultarDespesa painelConsultarDesp;
	private PainelAdicionarDespesa painelInserirDesp;
	private PainelCalculoMensal painelCalculoMensal;
	private PainelConsultaMes painelConsultaMes;
	private PainelTabelaCompleta painelTabelaCompleta;
	private PainelRemoverDespesa painelRemoverDespesa;
	private PainelAlterarDespesa painelAlterarDespesa;
	
	UsuarioController usuarioController = new UsuarioController();
	EnderecoController endController = new EnderecoController();
	DespesaController despController = new DespesaController();
	TabelaController tabController = new TabelaController();

	private JMenuItem mntmTabelaCompleta;

	private JMenu mnNewMenu_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param userLogado 
	 */
	public MenuPrincipal(final UsuarioVO userOnline) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/icons/bank.png")));
		setBackground(new Color(255, 255, 255));
		setTitle("Gerenciamento Mensal | Menu");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 710, 558);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(255, 255, 255));
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Conta");
		mnNewMenu.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/user.png")));
		mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 12));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmVerConta = new JMenuItem("Ver conta ");
		mntmVerConta.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		mntmVerConta.setBackground(new Color(255, 255, 255));
		mntmVerConta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		mntmVerConta.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/account.png")));
		mntmVerConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//TROCA DE PAINEL NA TELA PRINCIPAL
				painelVerConta = new PainelVerConta(userOnline);
				setContentPane(painelVerConta);
				revalidate(); 
				
			}
		});
		mntmVerConta.setFont(new Font("Segoe UI", Font.ITALIC, 12));
		mnNewMenu.add(mntmVerConta);
		
		JMenuItem mntmAtualizarDados = new JMenuItem("Atualizar dados ");
		mntmAtualizarDados.setBackground(new Color(255, 255, 255));
		mntmAtualizarDados.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
		mntmAtualizarDados.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/pencil.png")));
		mntmAtualizarDados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					painelAlterarDados = new PainelAlterarDados(userOnline);
					setContentPane(painelAlterarDados);
					revalidate(); 
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		mntmAtualizarDados.setFont(new Font("Segoe UI", Font.ITALIC, 12));
		mnNewMenu.add(mntmAtualizarDados);
		
		JMenuItem mntmTrocarConta = new JMenuItem("Trocar usuário");
		mntmTrocarConta.setBackground(new Color(255, 255, 255));
		mntmTrocarConta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0));
		mntmTrocarConta.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/refresh.png")));
		mntmTrocarConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Traz a tela de login novamente 
				dispose();
				TelaLogin tela = new TelaLogin();
				tela.setVisible(true);
				
			}
		});
		mntmTrocarConta.setFont(new Font("Segoe UI", Font.ITALIC, 12));
		mnNewMenu.add(mntmTrocarConta);
		
		JMenuItem mntmEncerrarConta = new JMenuItem("Encerrar conta");
		mntmEncerrarConta.setBackground(new Color(255, 255, 255));
		mntmEncerrarConta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		mntmEncerrarConta.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/cross.png")));
		mntmEncerrarConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int opcaoSelecionada = JOptionPane.showConfirmDialog(null, "Confirma a exclusão da sua conta?");
				
				if (opcaoSelecionada == JOptionPane.YES_OPTION) {
					
					//Apaga todas os registros relacionados
					endController.excluirEndController(userOnline);
					despController.excluirDespesasController(userOnline);
					tabController.excluirTabelasController(userOnline);
					usuarioController.excluirContaController(userOnline);
					//CHAMA O LOGIN NOVAMENTE
					dispose();
					TelaLogin login = new TelaLogin();
					login.setVisible(true);

				JOptionPane.showMessageDialog(null, "Conta excluída com sucesso!", "GS - Gerenciador de salário", JOptionPane.INFORMATION_MESSAGE);
				} else  {
				   dispose();
				   MenuPrincipal tela = new MenuPrincipal(userOnline);
				   tela.setVisible(true);
				} 	
			}	
		});
		mntmEncerrarConta.setFont(new Font("Segoe UI", Font.ITALIC, 12));
		mnNewMenu.add(mntmEncerrarConta);
		
		mnNewMenu_2 = new JMenu("Despesas");
		mnNewMenu_2.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/expenses.png")));
		mnNewMenu_2.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 12));
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmConsultarDesp = new JMenuItem("Consultar");
		mntmConsultarDesp.setBackground(new Color(255, 255, 255));
		mntmConsultarDesp.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/search.png")));
		mntmConsultarDesp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				painelConsultarDesp = new PainelConsultarDespesa(userOnline);
				setContentPane(painelConsultarDesp);
				revalidate();
				
			}
		});
		
		JMenuItem mntmEditarDesp = new JMenuItem("Editar ");
		mntmEditarDesp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				painelAlterarDespesa = new PainelAlterarDespesa(userOnline);
				setContentPane(painelAlterarDespesa);
				revalidate();
				
			}
		});
		mntmEditarDesp.setBackground(new Color(255, 255, 255));
		mntmEditarDesp.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/pencil.png")));
		mntmEditarDesp.setFont(new Font("Segoe UI", Font.ITALIC, 12));
		mnNewMenu_2.add(mntmEditarDesp);
		mntmConsultarDesp.setFont(new Font("Segoe UI", Font.ITALIC, 12));
		mnNewMenu_2.add(mntmConsultarDesp);
		
		JMenuItem mntmInserirNovaDespesa = new JMenuItem("Inserir nova ");
		mntmInserirNovaDespesa.setBackground(new Color(255, 255, 255));
		mntmInserirNovaDespesa.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/add.png")));
		mntmInserirNovaDespesa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				painelInserirDesp = new PainelAdicionarDespesa(userOnline);
				setContentPane(painelInserirDesp);
				revalidate();
				
			}
		});
		mntmInserirNovaDespesa.setFont(new Font("Segoe UI", Font.ITALIC, 12));
		mnNewMenu_2.add(mntmInserirNovaDespesa);
		
		JMenuItem mntmRemoverDesp = new JMenuItem("Remover despesa");
		mntmRemoverDesp.setBackground(new Color(255, 255, 255));
		mntmRemoverDesp.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/remove.png")));
		mntmRemoverDesp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				painelRemoverDespesa = new PainelRemoverDespesa(userOnline);
				setContentPane(painelRemoverDespesa);
				revalidate();
				
			
			}
		});
		mntmRemoverDesp.setFont(new Font("Segoe UI", Font.ITALIC, 12));
		mnNewMenu_2.add(mntmRemoverDesp);
		
		JMenu mnNewMenu_1 = new JMenu("Cálculos");
		mnNewMenu_1.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/chart-histogram.png")));
		mnNewMenu_1.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 12));
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmCalculoMensal = new JMenuItem("Cálculo mensal");
		mntmCalculoMensal.setBackground(new Color(255, 255, 255));
		mntmCalculoMensal.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/chart-histogram.png")));
		mntmCalculoMensal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				painelCalculoMensal = new PainelCalculoMensal(userOnline);
				setContentPane(painelCalculoMensal);
				revalidate();
				
			}
		});
		mntmCalculoMensal.setFont(new Font("Segoe UI", Font.ITALIC, 12));
		mnNewMenu_1.add(mntmCalculoMensal);
		
		JMenu mnNewMenu_3 = new JMenu("Tabela");
		mnNewMenu_3.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/cells.png")));
		mnNewMenu_3.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 12));
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmMesEsp = new JMenuItem("Mês específico");
		mntmMesEsp.setBackground(new Color(255, 255, 255));
		mntmMesEsp.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/calendar (1).png")));
		mntmMesEsp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				painelConsultaMes = new PainelConsultaMes(userOnline);
				setContentPane(painelConsultaMes);
				revalidate();
				
			}
		});
		mntmMesEsp.setFont(new Font("Segoe UI", Font.ITALIC, 12));
		mnNewMenu_3.add(mntmMesEsp);
		
	    mntmTabelaCompleta = new JMenuItem("Tabela completa");
		mntmTabelaCompleta.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/cells.png")));
		mntmTabelaCompleta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				painelTabelaCompleta = new PainelTabelaCompleta(userOnline);
				setContentPane(painelTabelaCompleta);
				revalidate();
				
			}
		});
		mntmTabelaCompleta.setFont(new Font("Segoe UI", Font.ITALIC, 12));
		mnNewMenu_3.add(mntmTabelaCompleta);
		
		JMenu mnNewMenu_4 = new JMenu("Sobre");
		mnNewMenu_4.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/info.png")));
		mnNewMenu_4.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 12));
		menuBar.add(mnNewMenu_4);
		
		JMenuItem mntmSobre = new JMenuItem("Sobre o sistema ");
		mntmSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				    dispose();
				   //Tela com informações sobre os istema sendo exibida em modal
		           TelaSobreSistema tela = new TelaSobreSistema(userOnline);
		           tela.setVisible(true);
				
			}
		});
		mntmSobre.setFont(new Font("Segoe UI", Font.ITALIC, 12));
		mntmSobre.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/about (1).png")));
		mntmSobre.setBackground(new Color(255, 255, 255));
		mnNewMenu_4.add(mntmSobre);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 461, 725, 36);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblContato = new JLabel("Made by Pedro A/Gabriel (48988471534)");
		lblContato.setFont(new Font("Yu Gothic UI", Font.ITALIC, 9));
		lblContato.setBounds(275, 11, 299, 14);
		panel.add(lblContato);
		
		JLabel lblIconPrincipal = new JLabel("");
		lblIconPrincipal.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/bankColor2.png")));
		lblIconPrincipal.setBounds(206, 44, 275, 326);
		contentPane.add(lblIconPrincipal);
		
		JLabel lblTItuloPrincipal = new JLabel("Sistema para gerenciamento de despesas mensais individuais ");
		lblTItuloPrincipal.setFont(new Font("Source Code Pro Black", Font.ITALIC, 11));
		lblTItuloPrincipal.setBounds(122, 356, 449, 14);
		contentPane.add(lblTItuloPrincipal);
		
	}
}