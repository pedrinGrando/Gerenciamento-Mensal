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
	
	UsuarioController usuarioController = new UsuarioController();

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
		mntmEncerrarConta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		mntmEncerrarConta.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/cross.png")));
		mntmEncerrarConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean retorn = false;
				String resp = JOptionPane.showInputDialog("Deseja excluir sua conta?\n"
						+userOnline.getNome() + " Digite:"
								+ "\n1-Confirmar"
								+ "\n2-Cancelar");
				if (resp.equals("1")) {
				retorn = usuarioController.excluirContaController(userOnline);
				JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso!", "GS - Gerenciador de salário", JOptionPane.INFORMATION_MESSAGE);
				} else if (resp.equals("2")) {
				   dispose();
				   MenuPrincipal tela = new MenuPrincipal(userOnline);
				   tela.setVisible(true);
				}
				
			}
				
		});
		mntmEncerrarConta.setFont(new Font("Segoe UI", Font.ITALIC, 12));
		mnNewMenu.add(mntmEncerrarConta);
		
		JMenu mnNewMenu_2 = new JMenu("Despesas");
		mnNewMenu_2.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/expenses.png")));
		mnNewMenu_2.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 12));
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmConsultarDesp = new JMenuItem("Consultar");
		mntmConsultarDesp.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/search.png")));
		mntmConsultarDesp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				painelConsultarDesp = new PainelConsultarDespesa(userOnline);
				setContentPane(painelConsultarDesp);
				revalidate();
				
			}
		});
		mntmConsultarDesp.setFont(new Font("Segoe UI", Font.ITALIC, 12));
		mnNewMenu_2.add(mntmConsultarDesp);
		
		JMenuItem mntmInserirNovaDespesa = new JMenuItem("Inserir nova ");
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
		
		JMenuItem mntmTabelaCompleta = new JMenuItem("Tabela completa");
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
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 461, 725, 36);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Made by Pedro A/Gabriel (48988471534)");
		lblNewLabel.setFont(new Font("Yu Gothic UI", Font.ITALIC, 9));
		lblNewLabel.setBounds(294, 11, 299, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icons/bankColor2.png")));
		lblNewLabel_1.setBounds(206, 44, 275, 326);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Sistema para gerenciamento de despesas mensais individuais ");
		lblNewLabel_2.setFont(new Font("Source Code Pro Black", Font.ITALIC, 11));
		lblNewLabel_2.setBounds(122, 356, 449, 14);
		contentPane.add(lblNewLabel_2);
	}
}
