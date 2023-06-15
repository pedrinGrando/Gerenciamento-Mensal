package model.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
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
import model.vo.*;
import controller.UsuarioController;
import exceptions.CampoInvalidoException;
import controller.EnderecoController;
import model.view.*;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class PainelAlterarDados extends JPanel {
	private JTextField novoNome_camp;
	private JTextField novoEmail_camp;
	private JTextField novoUser_camp;
	private JComponent lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JNumberFormatField novoSalarioL;
	private JLabel lblNewLabel_5;
	private JButton btnNewButton_1;
	
	UsuarioController usuarioController = new UsuarioController();
	UsuarioVO userAtualizado = new UsuarioVO();
	EnderecoVO endAtualizado = new EnderecoVO();
	EnderecoController enderecoController = new EnderecoController();
	DecimalFormat formato = new DecimalFormat("#,##0.00");
	
	private JTextField ruaCampo;
	private JTextField campBairro;
	private JTextField campNumero;
	private JTextField campCIdade;
	private JComboBox cbEstados;
	private JFormattedTextField cepCamp;
	private JPasswordField senhaAtualAtualizar;
	private JPasswordField novaSenhaAtualizar;
	private MaskFormatter mascaraCEP;
	private JLabel lblNewLabel_1;
	private JLabel lblRua;
	private JLabel lblBairro;
	private JLabel lblNumero;
	private JLabel lblCep;
	private JLabel lblEstado;
	private JComponent lblCidade;
	private JComponent lblNewLabel;
	private JLabel lblNewLabel_7;

	/**
	 * Create the panel.
	 * @throws ParseException 
	 */
	public PainelAlterarDados(final UsuarioVO userLogado) throws ParseException {
		
		setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setBackground(new Color(0, 255, 255));
		
	    setLayout(null);
	   
		lblNewLabel_1 = new JLabel("Alteração de dados cadastrais ");
		lblNewLabel_1.setFont(new Font("Source Serif Pro Semibold", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_1.setBounds(33, 29, 260, 14);
		add(lblNewLabel_1);
		
		mascaraCEP = new MaskFormatter("#####-###");
		mascaraCEP.setValueContainsLiteralCharacters(false);
		
		    EnderecoVO estado = new EnderecoVO();
		    estado.setIdEndereco(userLogado.getIdUsuario());
		    estado = enderecoController.consultarEnderecoPorId(estado);
		
		String[] listaEstados = {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS",
				 "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"};
		
		novoNome_camp = new JTextField();
		novoNome_camp.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		novoNome_camp.setFont(new Font("Tahoma", Font.ITALIC, 11));
		novoNome_camp.setBounds(10, 86, 217, 20);
		add(novoNome_camp);
		novoNome_camp.setColumns(10);
		novoNome_camp.setText(userLogado.getNome());
		
		novoEmail_camp = new JTextField();
		novoEmail_camp.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		novoEmail_camp.setFont(new Font("Tahoma", Font.ITALIC, 11));
		novoEmail_camp.setBounds(10, 141, 217, 20);
		add(novoEmail_camp);
		novoEmail_camp.setColumns(10);
		novoEmail_camp.setText(userLogado.getEmail());
		
		novoUser_camp = new JTextField();
		novoUser_camp.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		novoUser_camp.setFont(new Font("Tahoma", Font.ITALIC, 11));
		novoUser_camp.setBounds(10, 200, 217, 20);
		add(novoUser_camp);
		novoUser_camp.setColumns(10);
		novoUser_camp.setText(userLogado.getLogin());
		
		
	    lblNewLabel_2 = new JLabel("Nome Completo");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.ITALIC, 9));
		lblNewLabel_2.setToolTipText("");
		lblNewLabel_2.setBounds(10, 72, 163, 14);
		add(lblNewLabel_2);
		
		cbEstados = new JComboBox(listaEstados);
		cbEstados.setBorder(null);
		cbEstados.setBackground(new Color(192, 192, 192));
		cbEstados.setBounds(377, 154, 80, 22);
		add(cbEstados);
		
		cepCamp = new JFormattedTextField(mascaraCEP);
		cepCamp.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		cepCamp.setFont(new Font("Tahoma", Font.ITALIC, 11));
		cepCamp.setBounds(377, 86, 102, 20);
		add(cepCamp);
		cepCamp.setText("");
		
		cepCamp.setText(estado.getCep());
		
		lblNewLabel_3 = new JLabel("E-mail");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.ITALIC, 9));
		lblNewLabel_3.setBounds(10, 127, 163, 14);
		add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Login");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.ITALIC, 9));
		lblNewLabel_4.setBounds(10, 187, 163, 14);
		add(lblNewLabel_4);
		
		novoSalarioL = new JNumberFormatField();
		novoSalarioL.setFont(new Font("Tahoma", Font.ITALIC, 11));
		novoSalarioL.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		novoSalarioL.setBounds(10, 245, 102, 20);
		add(novoSalarioL);
		novoSalarioL.setColumns(10);
		novoSalarioL.setText("");
		
		senhaAtualAtualizar = new JPasswordField();
		senhaAtualAtualizar.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		senhaAtualAtualizar.setBounds(377, 262, 102, 20);
		add(senhaAtualAtualizar);
		
		lblNewLabel_5 = new JLabel("Salário líquido");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblNewLabel_5.setBounds(10, 231, 96, 14);
		add(lblNewLabel_5);
		
		campNumero = new JTextField();
		campNumero.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		campNumero.setBounds(10, 412, 102, 20);
		add(campNumero);
		campNumero.setColumns(10);
		
		campNumero.setText(String.valueOf(estado.getNumero()));
		
		campCIdade = new JTextField();
		campCIdade.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		campCIdade.setFont(new Font("Tahoma", Font.ITALIC, 11));
		campCIdade.setBounds(377, 200, 171, 20);
		add(campCIdade);
		campCIdade.setColumns(10);
		
		novaSenhaAtualizar = new JPasswordField();
		novaSenhaAtualizar.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		novaSenhaAtualizar.setBounds(377, 317, 102, 20);
		add(novaSenhaAtualizar);
		
		btnNewButton_1 = new JButton("");
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setIcon(new ImageIcon(PainelAlterarDados.class.getResource("/icons/diskette.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String valor1 = "";
				
				boolean result = false;
				boolean result2 = false;
				
				//INSERINDO NOVOS DADOS 
				valor1 = novoSalarioL.getText().replace(".", "");
				valor1 = valor1.replace(",", ".");
				
				userAtualizado.setIdUsuario(userLogado.getIdUsuario());
				userAtualizado.setSalariol(Double.parseDouble(valor1.replace("R$", "")));
				userAtualizado.setEmail(novoEmail_camp.getText());
				userAtualizado.setNome(novoNome_camp.getText());
				userAtualizado.setLogin(novoUser_camp.getText());
				userAtualizado.setSenha(novaSenhaAtualizar.getText());
				
					//VERIFICAR SENHA DIGITADA
					result = this.verificarSenha(senhaAtualAtualizar.getText(), novaSenhaAtualizar.getText());
					
						// ENDERECO
						endAtualizado.setBairro(campBairro.getText());
						endAtualizado.setCep(cepCamp.getText());
						endAtualizado.setLogradouro(ruaCampo.getText());
						endAtualizado.setIdUsuario(userAtualizado.getIdUsuario());
						endAtualizado.setUf((String) cbEstados.getSelectedItem());
						endAtualizado.setLocalidade(campCIdade.getText());
						endAtualizado.setNumero(Integer.parseInt(campNumero.getText()));
				
				if (result){
						try {
							usuarioController.atualizarUsuarioController(userAtualizado);
							enderecoController.atualizarEndController(endAtualizado);
							JOptionPane.showMessageDialog(null, "Dados atualizados com sucesso!", "Gerenciamento-Mensal", JOptionPane.INFORMATION_MESSAGE);
						} catch (SQLException | CampoInvalidoException e1) {
							JOptionPane.showMessageDialog(null, "A nova senha não pode ser igual à anterior!", "GS - Gerenciamento-Mensal", JOptionPane.ERROR_MESSAGE);
						} 
				
				} else {
					JOptionPane.showMessageDialog(null, "Não foi possível alterar!", "GS - Gerenciamento-Mensal", JOptionPane.ERROR_MESSAGE);
				}
					
			}

			private boolean verificarSenha(String text, String text2) {

				boolean retorno = false;
				
               if (text.equals(userLogado.getSenha())) {
            	   retorno = true;
               } else {
            	   JOptionPane.showMessageDialog(null, "Senha incorreta!", "Gerenciamento-Mensal", JOptionPane.ERROR_MESSAGE);
            	   retorno = false;
               }
				
				return retorno;
			}
		});
		btnNewButton_1.setBackground(new Color(0, 255, 255));
		btnNewButton_1.setFont(new Font("Tahoma", Font.ITALIC, 11));
		btnNewButton_1.setBounds(600, 451, 46, 23);
		add(btnNewButton_1);
		
		ruaCampo = new JTextField();
		ruaCampo.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		ruaCampo.setFont(new Font("Tahoma", Font.ITALIC, 11));
		ruaCampo.setBounds(10, 299, 217, 20);
		add(ruaCampo);
		ruaCampo.setColumns(10);
		
		ruaCampo.setText(estado.getLocalidade());
		
		lblRua = new JLabel("Rua ");
		lblRua.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblRua.setBounds(10, 286, 46, 14);
		add(lblRua);
		
		campBairro = new JTextField();
		campBairro.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		campBairro.setFont(new Font("Tahoma", Font.ITALIC, 11));
		campBairro.setBounds(10, 357, 217, 20);
		add(campBairro);
		campBairro.setColumns(10);
		
		campBairro.setText(estado.getBairro());
		
		lblBairro = new JLabel("Bairro");
		lblBairro.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblBairro.setBounds(10, 344, 46, 14);
		add(lblBairro);
		
		lblNumero = new JLabel("Número");
		lblNumero.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNumero.setBounds(10, 400, 46, 14);
		add(lblNumero);
		
		lblCep = new JLabel("CEP");
		lblCep.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblCep.setBounds(377, 72, 46, 14);
		add(lblCep);
		
		lblEstado = new JLabel("Estado");
		lblEstado.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblEstado.setBounds(377, 127, 46, 14);
		add(lblEstado);
		
	    lblCidade = new JLabel("Cidade");
		lblCidade.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblCidade.setBounds(377, 187, 46, 14);
		add(lblCidade);
		
		
		lblNewLabel = new JLabel("Senha atual : ");
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel.setBounds(377, 248, 102, 14);
		add(lblNewLabel);
		
		
		lblNewLabel_7 = new JLabel("Nova senha : ");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_7.setBounds(377, 302, 102, 14);
		add(lblNewLabel_7);
		
	}
}
