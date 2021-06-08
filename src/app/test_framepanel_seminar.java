package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class test_framepanel_seminar extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBox_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test_framepanel_seminar frame = new test_framepanel_seminar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public test_framepanel_seminar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 532);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 500, 500);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(498, 0, 486, 493);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("Best\u00E4tigen");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton.setBounds(46, 219, 116, 46);
		panel_1.add(btnNewButton);
		
		
		JComboBox comboBox = new JComboBox();
	
		
		comboBox.setBounds(192, 231, 88, 26);
		panel_1.add(comboBox);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(310, 231, 88, 26);
		panel_1.add(comboBox_1);
		
		JButton btnNewButton_1 = new JButton("Wobble");
		btnNewButton_1.setBounds(46, 276, 116, 46);
		panel_1.add(btnNewButton_1);
	}
}
