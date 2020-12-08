

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.LayoutManager;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Window.Type;

public class Main {

	public JFrame frame;


	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ImageIcon imgIcon2 = new ImageIcon(this.getClass().getResource("splash.png"));
		Image image = imgIcon2.getImage(); // transform it 
		Image newimg = image.getScaledInstance( 637, 430,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imgIcon2 = new ImageIcon(newimg);
		Icon imgIcon = new ImageIcon(this.getClass().getResource("ajax-loader (2).gif"));
		frame = new JFrame();
		frame.setType(Type.UTILITY);
		frame.setResizable(false);
		frame.setBounds(100, 100, 637, 430);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		// frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		    frame.setUndecorated(true);
		    frame.setLocationRelativeTo(null);
		JLabel panel = new JLabel(imgIcon2);
		JLabel lblNewLabel_1 = new JLabel(imgIcon);
		lblNewLabel_1.setBounds(214, 407, 233, 23);
		panel.setBounds(0, 0, 637, 430);
		JLabel lblNewLabel= new JLabel("Author: Mahdi Saghroun ");
	
		lblNewLabel.setFont(new Font("Rubik", Font.BOLD, 11));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(0, 416, 142, 14);
		frame.getContentPane().add(lblNewLabel_1);
		frame.getContentPane().add(lblNewLabel);
		frame.getContentPane().add(panel);
		}
}
