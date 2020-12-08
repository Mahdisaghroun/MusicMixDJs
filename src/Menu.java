

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Ui.ServerUI;

import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu {

	public JFrame frmMenu;

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the application.
	 */
	public Menu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMenu = new JFrame();
		frmMenu.setTitle("Menu");
		frmMenu.setResizable(false);
		frmMenu.setBounds(100, 100, 675, 443);
		frmMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMenu.getContentPane().setLayout(null);
		ImageIcon imgIcon2 = new ImageIcon(this.getClass().getResource("Main.png"));
		Image image = imgIcon2.getImage(); // transform it 
		Image newimg = image.getScaledInstance(659, 404, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imgIcon2 = new ImageIcon(newimg);
		JButton CreateServer = new JButton("");
		CreateServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ServerUI CreateServerUI = new ServerUI();
				CreateServerUI.frame.setVisible(true);
				frmMenu.dispose();
			}
		});
		CreateServer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		CreateServer.setContentAreaFilled(false);
		CreateServer.setFocusable(false);
		CreateServer.setBorderPainted(false);
		CreateServer.setIcon(new ImageIcon(Menu.class.getResource("Groupe 1.png")));
		CreateServer.setBounds(215, 123, 241, 81);
		
		frmMenu.getContentPane().add(CreateServer);
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientUI connect = new ClientUI();
				connect.frame.setVisible(true);
			}
		});
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setIcon(new ImageIcon(Menu.class.getResource("Groupe 2.png")));
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setBounds(215, 229, 241, 81);
		frmMenu.getContentPane().add(btnNewButton_1);
		JLabel lblNewLabel = new JLabel(imgIcon2);
		lblNewLabel.setBounds(0, 0, 669, 414);
		frmMenu.getContentPane().add(lblNewLabel);
		  frmMenu.setLocationRelativeTo(null);
		
	
		
		
	}
}
