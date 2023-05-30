package model.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.vo.UsuarioVO;
import controller.UsuarioController;
import model.view.*;

public class PainelAlterarDados extends JPanel {
	private JTextField novoNome_camp;
	private JTextField novoEmail_camp;
	private JTextField novoUser_camp;
	private JComponent lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JTextField novoBruto_camp;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JButton btnNewButton_1;
	UsuarioController usuarioController = new UsuarioController();
	UsuarioVO userAtualizado = new UsuarioVO();
	private JTextField ruaCampo;
	private JTextField campBairro;
	private JTextField campNumero;
	private JTextField campCep;
	private JTextField campEstado;
	private JTextField campCIdade;

	/**
	 * Create the panel.
	 */
	public PainelAlterarDados(final UsuarioVO userLogado) {
		
	    setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Atualização de dados");
		lblNewLabel_1.setFont(new Font("Source Serif Pro Semibold", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_1.setBounds(33, 55, 260, 14);
		add(lblNewLabel_1);
		
		novoNome_camp = new JTextField();
		novoNome_camp.setBounds(10, 112, 192, 20);
		add(novoNome_camp);
		novoNome_camp.setColumns(10);
		
		novoEmail_camp = new JTextField();
		novoEmail_camp.setBounds(10, 167, 192, 20);
		add(novoEmail_camp);
		novoEmail_camp.setColumns(10);
		
		novoUser_camp = new JTextField();
		novoUser_camp.setBounds(10, 226, 192, 20);
		add(novoUser_camp);
		novoUser_camp.setColumns(10);
		
	    lblNewLabel_2 = new JLabel("Nome Completo");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.ITALIC, 9));
		lblNewLabel_2.setToolTipText("");
		lblNewLabel_2.setBounds(10, 98, 163, 14);
		add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("E-mail");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.ITALIC, 9));
		lblNewLabel_3.setBounds(10, 153, 163, 14);
		add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Login");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.ITALIC, 9));
		lblNewLabel_4.setBounds(10, 213, 163, 14);
		add(lblNewLabel_4);
		
		novoBruto_camp = new JTextField();
		novoBruto_camp.setBounds(38, 271, 102, 20);
		add(novoBruto_camp);
		novoBruto_camp.setColumns(10);
		
		lblNewLabel_5 = new JLabel("Salário líquido");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblNewLabel_5.setBounds(10, 257, 96, 14);
		add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("R$");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_6.setBounds(10, 274, 13, 14);
		add(lblNewLabel_6);
		
		btnNewButton_1 = new JButton(" Salvar ");
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\GETIN\\Desktop\\Gerenciador de salário(GS)\\GerenciadorSalario\\img\\saveIcon.png"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//INSERINDO NOVOS DADOS
				//userAtualizado.setBruto(Double.parseDouble(novoBruto_camp.getText()));
				userAtualizado.setEmail(novoEmail_camp.getText());
				userAtualizado.setNome(novoNome_camp.getText());
				userAtualizado.setLogin(novoUser_camp.getText());
				userAtualizado.setIdUsuario(userLogado.getIdUsuario());
				
				//boolean result = usuarioController.atualizarUsuarioController(userAtualizado);
				
				//if (result) {
					JOptionPane.showMessageDialog(null, "Dados atualizados com sucesso!", "GS - Gerenciador de Salário", JOptionPane.INFORMATION_MESSAGE);
				//} else {
					JOptionPane.showMessageDialog(null, "Não foi possível alterar!", "GS - Gerenciador de Salário", JOptionPane.WARNING_MESSAGE);
				//}
					
			}
		});
		btnNewButton_1.setBackground(new Color(192, 192, 192));
		btnNewButton_1.setFont(new Font("Tahoma", Font.ITALIC, 11));
		btnNewButton_1.setBounds(483, 422, 105, 23);
		add(btnNewButton_1);
		
		ruaCampo = new JTextField();
		ruaCampo.setBounds(10, 325, 192, 20);
		add(ruaCampo);
		ruaCampo.setColumns(10);
		
		JLabel lblRua = new JLabel("Rua ");
		lblRua.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblRua.setBounds(10, 312, 46, 14);
		add(lblRua);
		
		campBairro = new JTextField();
		campBairro.setBounds(10, 383, 192, 20);
		add(campBairro);
		campBairro.setColumns(10);
		
		campNumero = new JTextField();
		campNumero.setBounds(10, 438, 86, 20);
		add(campNumero);
		campNumero.setColumns(10);
		
		campCep = new JTextField();
		campCep.setBounds(377, 112, 105, 20);
		add(campCep);
		campCep.setColumns(10);
		
		campEstado = new JTextField();
		campEstado.setBounds(377, 167, 192, 20);
		add(campEstado);
		campEstado.setColumns(10);
		
		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblBairro.setBounds(10, 370, 46, 14);
		add(lblBairro);
		
		JLabel lblNumero = new JLabel("Número");
		lblNumero.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNumero.setBounds(10, 426, 46, 14);
		add(lblNumero);
		
		JLabel lblCep = new JLabel("CEP");
		lblCep.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblCep.setBounds(377, 98, 46, 14);
		add(lblCep);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblEstado.setBounds(377, 153, 46, 14);
		add(lblEstado);
		
		campCIdade = new JTextField();
		campCIdade.setBounds(377, 226, 192, 20);
		add(campCIdade);
		campCIdade.setColumns(10);
		
		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblCidade.setBounds(377, 213, 46, 14);
		add(lblCidade);
	}
}
