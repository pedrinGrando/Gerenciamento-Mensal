package model.view;

import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import model.vo.*;
import controller.*;
import exceptions.CampoInvalidoException;
import exceptions.CpfJaUtilizadoException;

import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JRadioButton;

public class TelaCadastro extends JFrame {

	private JPanel contentPane;
	private JTextField campoNome;
	private JTextField emailCamp;
	private JTextField loginCamp;
	private JTextField ruaCamp;
	private JTextField bairroCamp;
	private JTextField campNumero;
    UsuarioController usuarioController = new UsuarioController();
    UsuarioVO usuario = new UsuarioVO();
    EnderecoVO endereco = new EnderecoVO();
    EnderecoController enderecoController = new EnderecoController();
    
	private MaskFormatter mascaraCpf;
	private MaskFormatter mascaraData;
	private MaskFormatter mascaraCEP;
	private JFormattedTextField cpfCamp;
	private DatePicker dataNasciCamp;
	
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private Container lblNewLabel_9;
	private JLabel lblNewLabel_10;
	private Container lblNewLabel_11;
	private JFormattedTextField cepCamp;
	private JLabel lblNewLabel_12;
	private Container lblNewLabel_13;
	private JLabel lblNewLabel_14;
	private JButton btnNewButton;
	private JButton btnSalvar;
	private JPasswordField campSenha;
	private JPasswordField confirmCampSenha;
	private JTextField campCidade;
	private JNumberFormatField salarioCamp;
	private JComboBox cbEstados;
	private JRadioButton radioAceita;
	private JRadioButton radioNaAceita;
	private JLabel lblNewLabel_15;
	private JLabel lblNewLabel_16;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastro frame = new TelaCadastro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public TelaCadastro() throws ParseException {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaCadastro.class.getResource("/icons/bank.png")));
		
		mascaraCpf = new MaskFormatter("###.###.###-##");
		mascaraCpf.setValueContainsLiteralCharacters(false);
		
		mascaraCEP = new MaskFormatter("#####-###");
		mascaraCpf.setValueContainsLiteralCharacters(false);
		
		String[] listaEstados = {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS",
				 "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"};
		
		setTitle("Gerenciamento-Mensal | Cadastro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 761, 594);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		radioAceita = new JRadioButton("Aceita os termos de cadastro");
		radioAceita.setBackground(new Color(0, 255, 255));
		radioAceita.setFont(new Font("Tahoma", Font.ITALIC, 11));
		radioAceita.setBounds(483, 114, 208, 23);
		contentPane.add(radioAceita);
		
		radioNaAceita = new JRadioButton("Não aceita os termos de cadastro ");
		radioNaAceita.setBackground(new Color(0, 255, 255));
		radioNaAceita.setFont(new Font("Tahoma", Font.ITALIC, 11));
		radioNaAceita.setBounds(483, 140, 208, 23);
		contentPane.add(radioNaAceita);
		
		
		campoNome = new JTextField();
		campoNome.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		campoNome.setBounds(105, 24, 254, 20);
		contentPane.add(campoNome);
		campoNome.setColumns(10);
		
		cpfCamp = new JFormattedTextField(mascaraCpf);
		cpfCamp.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		cpfCamp.setBounds(105, 67, 254, 20);
		contentPane.add(cpfCamp);
		
		dataNasciCamp = new DatePicker();
		dataNasciCamp.getComponentToggleCalendarButton().setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		dataNasciCamp.getComponentDateTextField().setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		dataNasciCamp.getComponentToggleCalendarButton().setText("");
		dataNasciCamp.getComponentToggleCalendarButton().setIcon(new ImageIcon(TelaCadastro.class.getResource("/icons/calendario16px.png")));
		dataNasciCamp.getComponentToggleCalendarButton().setBackground(new Color(192, 192, 192));
		dataNasciCamp.getComponentDateTextField().setBackground(new Color(192, 192, 192));
		dataNasciCamp.setBounds(136, 111, 223, 20);
		contentPane.add(dataNasciCamp);
		

	    lblNewLabel = new JLabel("Nome : ");
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel.setBounds(20, 27, 46, 14);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("CPF :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_1.setBounds(20, 70, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Data de Nascimento : ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_2.setBounds(20, 114, 106, 14);
		contentPane.add(lblNewLabel_2);
		
		emailCamp = new JTextField();
		emailCamp.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		emailCamp.setBounds(105, 161, 254, 20);
		contentPane.add(emailCamp);
		emailCamp.setColumns(10);
		
		lblNewLabel_3 = new JLabel("Email : ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_3.setBounds(20, 164, 63, 14);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Salário líquido: ");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_4.setBounds(20, 214, 92, 14);
		contentPane.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Login : ");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_5.setBounds(20, 261, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		loginCamp = new JTextField();
		loginCamp.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		loginCamp.setBounds(105, 258, 143, 20);
		contentPane.add(loginCamp);
		loginCamp.setColumns(10);
		
		lblNewLabel_6 = new JLabel("_______________________________________________________");
		lblNewLabel_6.setBounds(399, 304, 423, 14);
		contentPane.add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("______________________________________________");
		lblNewLabel_7.setBounds(29, 304, 290, 14);
		contentPane.add(lblNewLabel_7);
		
		lblNewLabel_8 = new JLabel("Seu endereço ");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_8.setBounds(312, 304, 115, 14);
		contentPane.add(lblNewLabel_8);
		
		lblNewLabel_15 = new JLabel("Senha : ");
		lblNewLabel_15.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_15.setBounds(526, 27, 63, 14);
		contentPane.add(lblNewLabel_15);
		
		lblNewLabel_16 = new JLabel("Confirme a senha: ");
		lblNewLabel_16.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_16.setBounds(483, 67, 106, 14);
		contentPane.add(lblNewLabel_16);
		
		campSenha = new JPasswordField();
		campSenha.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		campSenha.setBounds(582, 24, 95, 20);
		contentPane.add(campSenha);
		
		confirmCampSenha = new JPasswordField();
		confirmCampSenha.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		confirmCampSenha.setBounds(582, 64, 95, 20);
		contentPane.add(confirmCampSenha);
		
		ruaCamp = new JTextField();
		ruaCamp.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		ruaCamp.setBounds(76, 350, 254, 20);
		contentPane.add(ruaCamp);
		ruaCamp.setColumns(10);
		
		lblNewLabel_9 = new JLabel("Rua :");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_9.setBounds(20, 353, 46, 14);
		contentPane.add(lblNewLabel_9);
		
		bairroCamp = new JTextField();
		bairroCamp.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		bairroCamp.setBounds(76, 394, 254, 20);
		contentPane.add(bairroCamp);
		bairroCamp.setColumns(10);
		
		lblNewLabel_10 = new JLabel("Bairro :");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_10.setBounds(20, 397, 46, 14);
		contentPane.add(lblNewLabel_10);
		
		campNumero = new JTextField();
		campNumero.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		campNumero.setBounds(76, 437, 122, 20);
		contentPane.add(campNumero);
		campNumero.setColumns(10);
		
		lblNewLabel_11 = new JLabel("Número :");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_11.setBounds(20, 440, 46, 14);
		contentPane.add(lblNewLabel_11);
		
		cepCamp = new JFormattedTextField(mascaraCEP);
		cepCamp.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		cepCamp.setBounds(76, 474, 122, 20);
		contentPane.add(cepCamp);
		
		lblNewLabel_12 = new JLabel("CEP : ");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_12.setBounds(20, 477, 46, 14);
		contentPane.add(lblNewLabel_12);
		
	    campCidade = new JTextField();
	    campCidade.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		campCidade.setBounds(538, 394, 187, 20);
		contentPane.add(campCidade);
		campCidade.setColumns(10);
		
		lblNewLabel_13 = new JLabel("Estado : ");
		lblNewLabel_13.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_13.setBounds(468, 353, 46, 14);
		contentPane.add(lblNewLabel_13);
		
		cbEstados = new JComboBox(listaEstados);
		cbEstados.setBorder(null);
		cbEstados.setBackground(new Color(192, 192, 192));
		cbEstados.setBounds(538, 349, 63, 22);
		contentPane.add(cbEstados);
		
		salarioCamp = new JNumberFormatField();
		salarioCamp.setFont(new Font("Tahoma", Font.ITALIC, 11));
		salarioCamp.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		salarioCamp.setBounds(105, 211, 143, 20);
		contentPane.add(salarioCamp);
		salarioCamp.setColumns(10);
		
		lblNewLabel_14 = new JLabel("Cidade : ");
		lblNewLabel_14.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_14.setBounds(468, 397, 60, 14);
		contentPane.add(lblNewLabel_14);
		
		btnNewButton = new JButton("Voltar");
		btnNewButton.setBorder(null);
		btnNewButton.setIcon(new ImageIcon(TelaCadastro.class.getResource("/icons/back.png")));
		btnNewButton.setBackground(new Color(0, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// retorna para a tela de login 
				dispose();
				TelaLogin tela = new TelaLogin();
				tela.setVisible(true);
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.ITALIC, 11));
		btnNewButton.setBounds(257, 527, 115, 23);
		contentPane.add(btnNewButton);
		
		btnSalvar = new JButton("Cadastrar");
		btnSalvar.setBorder(null);
		btnSalvar.setIcon(new ImageIcon(TelaCadastro.class.getResource("/icons/diskette.png")));
		btnSalvar.setBackground(new Color(0, 255, 255));
		//btnSalvar.setEnabled(false);
		
		
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				 String valor1 = "";
				
				// DEFININDO TIPO DE USUARIO DE ACORDO COM A REGRA 
				usuario.setTipoUsuario(2);
				
				// removendo mascara
				try {
					String cpfSemMascara = (String) mascaraCpf.stringToValue(
							cpfCamp.getText());
					usuario.setCpf(cpfSemMascara);
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(null, "Erro ao converter o CPF", 
							"Erro", JOptionPane.ERROR_MESSAGE); 
				}
	
				// inserindo dados nos objetos 
			
				usuario.setNome(campoNome.getText());
				usuario.setEmail(emailCamp.getText());
				usuario.setDataNasci(dataNasciCamp.getDate());
			    usuario.setLogin(loginCamp.getText());
			    
			    valor1 = salarioCamp.getText().replace(",", ".");
			    
			    usuario.setSalariol(Double.parseDouble(valor1.replace("R$", "")));
			    //METODO PARA VERIFICAR SENHA 
			    this.verificarSenhasDigitadas(campSenha.getText(), confirmCampSenha.getText());
			    
			    try {
			    //cadastramento no banco, chamando validacoes
			    if (usuario.getSenha() != null) {
			    	usuarioController.cadastrarUsuarioController(usuario);
			      }
			    } catch (CpfJaUtilizadoException | CampoInvalidoException excecao) {
			    	JOptionPane.showMessageDialog(null, excecao.getMessage(), 
							"Erro", JOptionPane.WARNING_MESSAGE); 
			    }
			    
			    //INICIA O OBJETO DE ENDERECO
                EnderecoVO endereco = new EnderecoVO();
				
				endereco.setIdUsuario(usuario.getIdUsuario());
				endereco.setBairro(bairroCamp.getText());
				endereco.setCep(cepCamp.getText());
				endereco.setRua(ruaCamp.getText());
				endereco.setNumero(Integer.parseInt(campNumero.getText()));
				endereco.setCidade(campCidade.getText());
				endereco.setEstado((String) cbEstados.getSelectedItem());
				
				 //cadastramento no banco, chamando validacoes
				try {
					endereco = enderecoController.cadastrarEnderecoController(endereco);
				} catch (CampoInvalidoException e2) {
					JOptionPane.showMessageDialog(null, "Informe os seguintes campos\n " + e2.getMessage());
				}
				
				if (usuario.getIdUsuario() != 0 && endereco.getIdEndereco() != 0) {
					JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!", 
							"Sucesso", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Não foi possível cadastrar o usuário!", 
							"Sucesso", JOptionPane.ERROR_MESSAGE);
				}	
			}

			private void verificarSenhasDigitadas(String text, String text2) {
			
				if (text.equals(text2)) {
					usuario.setSenha(text2);
					
				} else {
					JOptionPane.showMessageDialog(null, "Senhas não coincidem!", "Gerenciamento-Mensal" ,JOptionPane.ERROR_MESSAGE);
				}
				
			}

		});
		btnSalvar.setFont(new Font("Tahoma", Font.ITALIC, 11));
		btnSalvar.setBounds(371, 527, 128, 23);
		contentPane.add(btnSalvar);
	
		
			}
		}
