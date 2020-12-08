import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.annotation.processing.FilerException;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ClientUI {

	public JFrame frame;
	private JTextField txtLocalhost;
	private JTextField textField_1;
	private JTextField textField;
	private File file;
	Socket client = null;
	private boolean showDhashbord = true;

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the application.
	 */
	public ClientUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBackground(Color.WHITE);
		frame.setBounds(100, 100, 547, 360);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Connect To Server");
		lblNewLabel.setFont(new Font("Rubik", Font.BOLD, 26));
		lblNewLabel.setBounds(143, 29, 259, 40);
		frame.getContentPane().add(lblNewLabel);

		txtLocalhost = new JTextField();
		txtLocalhost.setText("localhost");
		txtLocalhost.setBounds(192, 109, 186, 30);
		frame.getContentPane().add(txtLocalhost);
		txtLocalhost.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("IP Adress");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(112, 116, 70, 14);
		frame.getContentPane().add(lblNewLabel_1);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(192, 166, 186, 30);
		frame.getContentPane().add(textField_1);

		JLabel lblNewLabel_1_1 = new JLabel("Port");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(136, 171, 70, 14);
		frame.getContentPane().add(lblNewLabel_1_1);

		JButton btnNewButton = new JButton("Connect");
		btnNewButton.setFocusable(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (btnNewButton.getText().equals("Disconnect"))
					try {
						client.close();
						JOptionPane.showMessageDialog(frame, "You are now disconnected from server ", "Info",
								JOptionPane.INFORMATION_MESSAGE);
						btnNewButton.setText("Connect");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				else
					try {

						// System.out.println("Connecting to " + txtLocalhost.getText() + " on port " +
						// textField_1.getText());
						client = new Socket(txtLocalhost.getText(), Integer.valueOf(textField_1.getText()));
						JOptionPane.showMessageDialog(frame, "You are now connected to server ", "Connected",
								JOptionPane.INFORMATION_MESSAGE);
						Dhashboard window = new Dhashboard(client);
						// window.socket=client;
						window.frame.setVisible(true);
						btnNewButton.setText("Disconnect");
						new Thread(new Runnable() {

							@Override
							public void run() {
								while (true) {
									try {
										if (client.getInputStream().read() == -1
												&& btnNewButton.getText().equals("Disconnect")) {
											JOptionPane.showMessageDialog(frame, "Server Interremped !", "Oops",
													JOptionPane.ERROR_MESSAGE);
											window.frame.dispose();
											btnNewButton.setText("Connect");
											break;
										}
									} catch (HeadlessException | IOException ex) {

										JOptionPane.showMessageDialog(frame, "Server Interremped !", "Oops",
												JOptionPane.ERROR_MESSAGE);
										window.frame.dispose();
										btnNewButton.setText("Connect");

										break;
									}

								}
							};

						}).start();

					} catch (UnknownHostException e) {
						JOptionPane.showMessageDialog(frame, "Server Not Found !", "Oops", JOptionPane.ERROR_MESSAGE);
					}

					catch (NumberFormatException | IOException e) {
						JOptionPane.showMessageDialog(frame, e.getMessage(), "Oops", JOptionPane.ERROR_MESSAGE);
					} catch (java.lang.IllegalArgumentException e) {
						JOptionPane.showMessageDialog(frame, "Please enter a valid PORT (XXXX)", "Oops",
								JOptionPane.ERROR_MESSAGE);
					}

			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(228, 222, 110, 30);
		frame.getContentPane().add(btnNewButton);

	}
}
