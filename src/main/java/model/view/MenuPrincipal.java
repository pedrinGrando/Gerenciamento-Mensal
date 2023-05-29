package model.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.vo.UsuarioVO;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import javax.swing.JMenuItem;
import javax.swing.JLabel;

public class MenuPrincipal extends JFrame {

	private JPanel contentPane;

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
	public MenuPrincipal(UsuarioVO userLogado) {
		setBackground(new Color(255, 255, 255));
		setTitle("Gerenciamento Mensal | Menu");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 741, 558);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Conta");
		mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 12));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmVerConta = new JMenuItem("Ver conta ");
		mntmVerConta.setFont(new Font("Segoe UI", Font.ITALIC, 12));
		mnNewMenu.add(mntmVerConta);
		
		JMenuItem mntmAtualizarDados = new JMenuItem("Atualizar dados ");
		mntmAtualizarDados.setFont(new Font("Segoe UI", Font.ITALIC, 12));
		mnNewMenu.add(mntmAtualizarDados);
		
		JMenuItem mntmTrocarConta = new JMenuItem("Trocar usuário");
		mntmTrocarConta.setFont(new Font("Segoe UI", Font.ITALIC, 12));
		mnNewMenu.add(mntmTrocarConta);
		
		JMenuItem mntmEncerrarConta = new JMenuItem("Encerrar conta");
		mntmEncerrarConta.setFont(new Font("Segoe UI", Font.ITALIC, 12));
		mnNewMenu.add(mntmEncerrarConta);
		
		JMenu mnNewMenu_2 = new JMenu("Despesas");
		mnNewMenu_2.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 12));
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmConsultarDesp = new JMenuItem("Consultar");
		mntmConsultarDesp.setFont(new Font("Segoe UI", Font.ITALIC, 12));
		mnNewMenu_2.add(mntmConsultarDesp);
		
		JMenuItem mntmInserirNovaDespesa = new JMenuItem("Inserir nova ");
		mntmInserirNovaDespesa.setFont(new Font("Segoe UI", Font.ITALIC, 12));
		mnNewMenu_2.add(mntmInserirNovaDespesa);
		
		JMenuItem mntmRemoverDesp = new JMenuItem("Remover despesa");
		mntmRemoverDesp.setFont(new Font("Segoe UI", Font.ITALIC, 12));
		mnNewMenu_2.add(mntmRemoverDesp);
		
		JMenu mnNewMenu_1 = new JMenu("Cálculos");
		mnNewMenu_1.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 12));
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmCalculoMensal = new JMenuItem("Cálculo mensal");
		mntmCalculoMensal.setFont(new Font("Segoe UI", Font.ITALIC, 12));
		mnNewMenu_1.add(mntmCalculoMensal);
		
		JMenuItem mntmMesAnt = new JMenuItem("Mês anterior");
		mntmMesAnt.setFont(new Font("Segoe UI", Font.ITALIC, 12));
		mnNewMenu_1.add(mntmMesAnt);
		
		JMenu mnNewMenu_3 = new JMenu("Tabela");
		mnNewMenu_3.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 12));
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmMesEsp = new JMenuItem("Mês específico");
		mntmMesEsp.setFont(new Font("Segoe UI", Font.ITALIC, 12));
		mnNewMenu_3.add(mntmMesEsp);
		
		JMenuItem mntmTabelaCompleta = new JMenuItem("Tabela completa");
		mntmTabelaCompleta.setFont(new Font("Segoe UI", Font.ITALIC, 12));
		mnNewMenu_3.add(mntmTabelaCompleta);
		
		JMenu mnNewMenu_4 = new JMenu("Sobre");
		mnNewMenu_4.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 12));
		menuBar.add(mnNewMenu_4);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 461, 725, 36);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Made by Pedro A/Gabriel (48988471534)");
		lblNewLabel.setFont(new Font("Source Sans Pro ExtraLight", Font.ITALIC, 9));
		lblNewLabel.setBounds(294, 11, 299, 14);
		panel.add(lblNewLabel);
	}
}
