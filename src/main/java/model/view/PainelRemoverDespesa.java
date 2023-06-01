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

		setLayout(null);
		
		nomeDespesa_camp = new JTextField();
		nomeDespesa_camp.setBounds(49, 134, 115, 20);
		add(nomeDespesa_camp);
		nomeDespesa_camp.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Nome da despesa");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_1.setBounds(39, 117, 101, 14);
		add(lblNewLabel_1);
		
		btn_removerDespesa = new JButton(" Remover ");
		btn_removerDespesa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (despesaController.removerDespesaController(userLogado, nomeDespesa_camp.getText())) {
					JOptionPane.showMessageDialog(null, "Despesa removida com sucesso!", "GS - Gerenciador de salário", JOptionPane.INFORMATION_MESSAGE);
				}else {
					
				}
			}
		});
		btn_removerDespesa.setBackground(new Color(192, 192, 192));
		btn_removerDespesa.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		btn_removerDespesa.setBounds(189, 133, 101, 23);
		add(btn_removerDespesa);
		
		lblNewLabel_2 = new JLabel(" Remover despesa cadastrada ");
		lblNewLabel_2.setFont(new Font("Source Serif Pro Semibold", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_2.setBounds(95, 74, 279, 14);
		add(lblNewLabel_2);
		
    }
		
}


