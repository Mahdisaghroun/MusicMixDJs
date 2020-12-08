package Ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.ProgressBarUI;
import javax.swing.JProgressBar;
import java.awt.Label;
import java.awt.Window;

public class ServerUI {

	public JFrame frame;
	private JTextField adress;
	private JTextField port;
	private File file;
	ServerDashboard window ;
	ServerSocket serverSocket;
	
	

	/**
	 * Create the application.
	 */
	public ServerUI() {
		try {
			initialize();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws UnknownHostException 
	 */
	private void initialize() throws UnknownHostException {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBackground(Color.WHITE);
		frame.setResizable(false);
		frame.setBounds(100, 100, 491, 344);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Create Server");
		lblNewLabel.setFont(new Font("Rubik", Font.BOLD, 21));
		lblNewLabel.setBounds(167, 26, 161, 41);
		frame.getContentPane().add(lblNewLabel);
		
		adress = new JTextField();
		adress.setText(InetAddress.getLocalHost().getHostAddress().toString());
		adress.setBounds(144, 105, 201, 35);
		frame.getContentPane().add(adress);
		adress.setColumns(10);
		
		port = new JTextField();
		port.setColumns(10);
		port.setBounds(144, 163, 201, 35);
		frame.getContentPane().add(port);
		
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setVisible(false);
		progressBar.setStringPainted(true);
		progressBar.setBounds(114, 277, 268, 14);
		frame.getContentPane().add(progressBar);
	
		
		JButton btnNewButton = new JButton("Start Server");
		btnNewButton.setFocusable(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(!btnNewButton.getText().equals("Stop Server")) {
				
				
				Thread t = new Thread(new Runnable() {
					
					@Override
					public void run() {
						progressBar.setVisible(true);
						for(int i=0;i<100;i+=10) {
							progressBar.setValue(i);
							
								try {
									Thread.sleep(100);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
						}
						
					}
				});
				t.start();
				
				new Thread(new Runnable() {
				
					@Override
					public void run() {
						
					
						while(true) {
							if(!t.isAlive())
								break;
						}
						
						progressBar.setVisible(false);
						
						
					
						btnNewButton.setText("Stop Server");
						adress.setEnabled(false);
						port.setEnabled(false);
						
						try {
							int portt = Integer.valueOf(port.getText())  ;
						 serverSocket = new ServerSocket(portt);
							
						
							JOptionPane.showMessageDialog(frame, "Server Started at   "+port.getText() ,"Server Started ", JOptionPane.INFORMATION_MESSAGE);
						
						 window = new ServerDashboard(serverSocket);
							window.frmServerDashbord.setVisible(true);
							//window.serverSocket=serverSocket;
						
							window.listen();
						}
						
					
				catch(java.net.BindException ex) {
					JOptionPane.showMessageDialog(frame, "Address already in use " ,"Opps !", JOptionPane.ERROR_MESSAGE);
					btnNewButton.setText("Start Server");
				}
					 catch (Exception e) {
						
							JOptionPane.showMessageDialog(frame, e.getMessage(),"OOps ! " ,JOptionPane.ERROR_MESSAGE);
							btnNewButton.setText("Start Server");
							adress.setEnabled(true);
							port.setEnabled(true);
					}}
					
					
						
					
				}).start();
				
				
			}
				else{
					 window.frmServerDashbord.dispose();
						btnNewButton.setText("Start Server");
						
					try {
						serverSocket.close();
					} catch (IOException e) {
					
						JOptionPane.showMessageDialog(frame, e.getMessage(), "Oops", JOptionPane.INFORMATION_MESSAGE);
					}
					}
				}
		});
		btnNewButton.setFont(new Font("Rubik", Font.PLAIN, 13));
		btnNewButton.setBounds(183, 209, 124, 35);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Port");
		lblNewLabel_1.setFont(new Font("Rubik", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(98, 172, 76, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("IP Adress");
		lblNewLabel_1_1.setFont(new Font("Rubik", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(79, 115, 76, 14);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		
	
	}
}
