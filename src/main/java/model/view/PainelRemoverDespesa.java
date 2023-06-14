package model.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.DespesaController;
import model.vo.*;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;


public class PainelRemoverDespesa extends JPanel {

	private JTextField nomeDespesa_camp;
	private JLabel lblNewLabel_1;
	private JButton btn_removerDespesa;
	private JLabel lblNewLabel_2;
	private JButton btn_voltar;
	
	DespesaController despesaController = new DespesaController();
	DespesaVO despesa = new DespesaVO();
	

	/**
	 * Create the panel.
	 */
	public PainelRemoverDespesa(final UsuarioVO userLogado) {
		setBackground(new Color(0, 255, 255));

		setLayout(null);
		
		nomeDespesa_camp = new JTextField();
		nomeDespesa_camp.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		nomeDespesa_camp.setBounds(42, 94, 181, 20);
		add(nomeDespesa_camp);
		nomeDespesa_camp.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Nome da despesa");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_1.setBounds(32, 77, 101, 14);
		add(lblNewLabel_1);
		
		btn_removerDespesa = new JButton("");
		btn_removerDespesa.setIcon(new ImageIcon(PainelRemoverDespesa.class.getResource("/icons/remove.png")));
		btn_removerDespesa.setBorder(null);
		btn_removerDespesa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (nomeDespesa_camp.getText().isEmpty() || nomeDespesa_camp.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "O nome é obrigatório!", "Gerenciador-Mensal", JOptionPane.INFORMATION_MESSAGE);
					
				}else  {
					despesaController.removerDespesaController(userLogado, nomeDespesa_camp.getText());
					JOptionPane.showMessageDialog(null, "Despesa removida com sucesso!", "Gerenciador-Mensal", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btn_removerDespesa.setBackground(new Color(0, 255, 255));
		btn_removerDespesa.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		btn_removerDespesa.setBounds(222, 94, 41, 23);
		add(btn_removerDespesa);
		
		lblNewLabel_2 = new JLabel(" Remover despesa cadastrada ");
		lblNewLabel_2.setFont(new Font("Source Serif Pro Semibold", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_2.setBounds(88, 34, 279, 14);
		add(lblNewLabel_2);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 195, 739, 10);
		add(panel);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PainelRemoverDespesa.class.getResource("/icons/bank.png")));
		lblNewLabel.setBounds(636, 430, 41, 34);
		add(lblNewLabel);
		
    }
}


