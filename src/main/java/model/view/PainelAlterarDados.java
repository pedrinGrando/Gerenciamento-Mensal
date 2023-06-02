package model.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

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
import javax.swing.text.MaskFormatter;

import model.vo.UsuarioVO;
import controller.UsuarioController;
import model.view.*;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;

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
	private JTextField campCIdade;
	private JComboBox cbEstados;
	private JFormattedTextField cepCamp;
	private JPasswordField senhaAtualAtualizar;
	private JPasswordField novaSenhaAtualizar;
	private MaskFormatter mascaraCEP;

	/**
	 * Create the panel.
	 * @throws ParseException 
	 */
	public PainelAlterarDados(final UsuarioVO userLogado) throws ParseException {
		
	    setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Atualização de dados");
		lblNewLabel_1.setFont(new Font("Source Serif Pro Semibold", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_1.setBounds(33, 55, 260, 14);
		add(lblNewLabel_1);
		
		mascaraCEP = new MaskFormatter("#####-###");
		mascaraCEP.setValueContainsLiteralCharacters(false);
		
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
		
		cbEstados = new JComboBox();
		cbEstados.setBounds(377, 180, 80, 22);
		add(cbEstados);
		
		cepCamp = new JFormattedTextField(mascaraCEP);
		cepCamp.setBounds(377, 112, 114, 20);
		add(cepCamp);
		
		lblNewLabel_3 = new JLabel("E-mail");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.ITALIC, 9));
		lblNewLabel_3.setBounds(10, 153, 163, 14);
		add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Login");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.ITALIC, 9));
		lblNewLabel_4.setBounds(10, 213, 163, 14);
		add(lblNewLabel_4);
		
		novoBruto_camp = new JTextField();
		novoBruto_camp.setBounds(35, 271, 102, 20);
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
		btnNewButton_1.setBounds(514, 448, 105, 23);
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
		campCIdade.setBounds(377, 226, 171, 20);
		add(campCIdade);
		campCIdade.setColumns(10);
		
		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblCidade.setBounds(377, 213, 46, 14);
		add(lblCidade);
		
		
		JLabel lblNewLabel = new JLabel("Senha atual : ");
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel.setBounds(377, 274, 102, 14);
		add(lblNewLabel);
		
		senhaAtualAtualizar = new JPasswordField();
		senhaAtualAtualizar.setBounds(377, 288, 102, 20);
		add(senhaAtualAtualizar);
		
		JLabel lblNewLabel_7 = new JLabel("Nova senha : ");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_7.setBounds(377, 328, 102, 14);
		add(lblNewLabel_7);
		
		novaSenhaAtualizar = new JPasswordField();
		novaSenhaAtualizar.setBounds(377, 343, 102, 20);
		add(novaSenhaAtualizar);
	}
}
