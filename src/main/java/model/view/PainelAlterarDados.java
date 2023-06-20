package model.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.SocketException;
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
import exceptions.EnderecoInvalidoException;
import controller.EnderecoController;
import model.dao.*;
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
	private JComponent visaoNome;
	private JLabel visaoEmail;
	private JLabel visaoLogin;
	private JNumberFormatField novoSalarioL;
	private JLabel visaoSalarioLiquido;
	private JButton btnSalvar;
	
	UsuarioController usuarioController = new UsuarioController();
	UsuarioVO userAtualizado = new UsuarioVO();
	EnderecoVO endAtualizado = new EnderecoVO();
	EnderecoController enderecoController = new EnderecoController();
	DecimalFormat formato = new DecimalFormat("#,##0.00");
	ViaCEP viaCep = new ViaCEP();
	EnderecoVO endPorCEP = new EnderecoVO();
	
	private JTextField ruaCampo;
	private JTextField campBairro;
	private JTextField campNumero;
	private JTextField campCIdade;
	private JComboBox cbEstados;
	private JFormattedTextField cepCamp;
	private JPasswordField senhaAtualAtualizar;
	private JPasswordField novaSenhaAtualizar;
	private MaskFormatter mascaraCEP;
	private JLabel lblTitulo;
	private JLabel lblRua;
	private JLabel lblBairro;
	private JLabel lblNumero;
	private JLabel lblCep;
	private JLabel lblEstado;
	private JComponent lblCidade;
	private JComponent visaoSenha;
	private JLabel visaoNovaSenha;
	private JButton btnBuscarCep;
	private JLabel lblErro;

	/**
	 * Create the panel.
	 * @throws ParseException 
	 */
	public PainelAlterarDados(final UsuarioVO userLogado) throws ParseException {
		
		setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setBackground(new Color(0, 255, 255));
		
	    setLayout(null);
	   
		lblTitulo = new JLabel("Alteração de dados cadastrais ");
		lblTitulo.setFont(new Font("Source Serif Pro Semibold", Font.BOLD | Font.ITALIC, 15));
		lblTitulo.setBounds(33, 29, 260, 14);
		add(lblTitulo);
		
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
		novoNome_camp.setColumns(10);
		
		// Adiciona um ouvinte de eventos de teclado ao campo
		novoNome_camp.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                
                // Verifica se o caractere é um número
                if (Character.isDigit(c)) {
                    e.consume(); // Impede que o caractere seja inserido
                }
            }
        });
		add(novoNome_camp);
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
		
		
	    visaoNome = new JLabel("Nome Completo");
		visaoNome.setFont(new Font("Tahoma", Font.ITALIC, 9));
		visaoNome.setToolTipText("");
		visaoNome.setBounds(10, 72, 163, 14);
		add(visaoNome);
		
		cbEstados = new JComboBox(listaEstados);
		cbEstados.setBorder(null);
		cbEstados.setBackground(new Color(192, 192, 192));
		cbEstados.setBounds(377, 154, 80, 22);
		add(cbEstados);
		
		btnBuscarCep = new JButton("");
		btnBuscarCep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					endPorCEP = enderecoController.buscarViaCepController(cepCamp.getText());
				} catch (EnderecoInvalidoException | CampoInvalidoException e1) {
					lblErro.setText("Digite o CEP!");
				} catch (IOException e2) {
					lblErro.setText("Digite o CEP!");
				}
				
				//insere os valores do cep
				campBairro.setText(endPorCEP.getBairro());
				ruaCampo.setText(endPorCEP.getLogradouro());
				campCIdade.setText(endPorCEP.getLocalidade());
				
			}
		});
		btnBuscarCep.setBackground(new Color(0, 255, 255));
		btnBuscarCep.setBorder(null);
		btnBuscarCep.setIcon(new ImageIcon(PainelAlterarDados.class.getResource("/icons/loupe.png")));
		btnBuscarCep.setBounds(114, 298, 37, 23);
		add(btnBuscarCep);
		
		cepCamp = new JFormattedTextField(mascaraCEP);
		cepCamp.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		cepCamp.setFont(new Font("Tahoma", Font.ITALIC, 11));
		cepCamp.setBounds(10, 299, 102, 20);
		add(cepCamp);
		cepCamp.setText("");
		
		cepCamp.setText(estado.getCep());
		
		visaoEmail = new JLabel("E-mail");
		visaoEmail.setFont(new Font("Tahoma", Font.ITALIC, 9));
		visaoEmail.setBounds(10, 127, 163, 14);
		add(visaoEmail);
		
		visaoLogin = new JLabel("Login");
		visaoLogin.setFont(new Font("Tahoma", Font.ITALIC, 9));
		visaoLogin.setBounds(10, 187, 163, 14);
		add(visaoLogin);
		
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
		
		visaoSalarioLiquido = new JLabel("Salário líquido");
		visaoSalarioLiquido.setFont(new Font("Tahoma", Font.ITALIC, 10));
		visaoSalarioLiquido.setBounds(10, 231, 96, 14);
		add(visaoSalarioLiquido);
		
		campNumero = new JTextField();
		campNumero.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		campNumero.setBounds(377, 86, 171, 20);
		campNumero.setColumns(10);
		
		// Adiciona um ouvinte de eventos de teclado ao campo//impede letras
		campNumero.addKeyListener(new KeyAdapter() {
	            @Override
	            public void keyTyped(KeyEvent e) {
	                char c = e.getKeyChar();
	                
	                // Verifica se o caractere não é uma letra
	                if (Character.isLetter(c)) {
	                    e.consume(); // Impede que o caractere seja inserido
	                }
	            }
	        });
		
		
		
		add(campNumero);
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
		
		btnSalvar = new JButton("");
		btnSalvar.setBorder(null);
		btnSalvar.setIcon(new ImageIcon(PainelAlterarDados.class.getResource("/icons/diskette.png")));
		btnSalvar.addActionListener(new ActionListener() {
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
		btnSalvar.setBackground(new Color(0, 255, 255));
		btnSalvar.setFont(new Font("Tahoma", Font.ITALIC, 11));
		btnSalvar.setBounds(600, 451, 46, 23);
		add(btnSalvar);
		
		ruaCampo = new JTextField();
		ruaCampo.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		ruaCampo.setFont(new Font("Tahoma", Font.ITALIC, 11));
		ruaCampo.setBounds(10, 414, 217, 20);
		add(ruaCampo);
		ruaCampo.setColumns(10);
		
		ruaCampo.setText(estado.getLocalidade());
		
		lblRua = new JLabel("Rua ");
		lblRua.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblRua.setBounds(10, 400, 46, 14);
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
		lblNumero.setBounds(377, 72, 46, 14);
		add(lblNumero);
		
		lblCep = new JLabel("CEP");
		lblCep.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblCep.setBounds(10, 287, 46, 14);
		add(lblCep);
		
		lblEstado = new JLabel("Estado");
		lblEstado.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblEstado.setBounds(377, 127, 46, 14);
		add(lblEstado);
		
	    lblCidade = new JLabel("Cidade");
		lblCidade.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblCidade.setBounds(377, 187, 46, 14);
		add(lblCidade);
		
		visaoSenha = new JLabel("Senha atual : ");
		visaoSenha.setFont(new Font("Tahoma", Font.ITALIC, 11));
		visaoSenha.setBounds(377, 248, 102, 14);
		add(visaoSenha);
		
		visaoNovaSenha = new JLabel("Nova senha : ");
		visaoNovaSenha.setFont(new Font("Tahoma", Font.ITALIC, 11));
		visaoNovaSenha.setBounds(377, 302, 102, 14);
		add(visaoNovaSenha);
		
		lblErro = new JLabel("");
		lblErro.setForeground(new Color(255, 0, 0));
		lblErro.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblErro.setBounds(59, 320, 114, 14);
		add(lblErro);
		
	}
}
