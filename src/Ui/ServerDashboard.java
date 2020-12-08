package Ui;

import java.awt.EventQueue;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JSplitPane;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ServerDashboard {

	public JFrame frmServerDashbord;
	public ServerSocket serverSocket;
	JTextArea log;
	Socket server;
	private JLabel lblNewLabel;
	private JSeparator separator_1;
	private JLabel lblNewLabel_1;
	private JLabel Status;public JLabel lblNewLabel_2;
	private JLabel Adress;
	private JLabel Status_2;
	public JLabel musicIcon;
	public Service service;
	
		public ArrayList<String> listOfmusic= new ArrayList<String>();
		public JLabel listmusic;
	
	public ServerDashboard(ServerSocket serverSocket ) {
		this.serverSocket=serverSocket;
		try {
			initialize();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//this.listen();
		lblNewLabel_2.setVisible(false);
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws UnknownHostException 
	 */
	private void initialize() throws UnknownHostException {
		frmServerDashbord = new JFrame();
		frmServerDashbord.setTitle("Server Dashbord");
		frmServerDashbord.setResizable(false);
		frmServerDashbord.getContentPane().setBackground(Color.WHITE);
		frmServerDashbord.setBounds(100, 100, 637, 407);
		frmServerDashbord.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmServerDashbord.getContentPane().setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(317, 43, 11, 325);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(Color.GRAY);
		frmServerDashbord.getContentPane().add(separator);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 79, 297, 278);
		frmServerDashbord.getContentPane().add(scrollPane);
		
		log = new JTextArea();
		log.setEditable(false);
		log.setEnabled(false);
		log.setForeground(new Color(255, 0, 0));
		log.setDisabledTextColor(new Color(255, 0, 0));
		log.setBackground(Color.WHITE);
		log.setLineWrap(true);
		scrollPane.setViewportView(log);
		
		lblNewLabel = new JLabel("Server logs :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 43, 164, 25);
		frmServerDashbord.getContentPane().add(lblNewLabel);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(0, 43, 631, 13);
		frmServerDashbord.getContentPane().add(separator_1);
		
		lblNewLabel_1 = new JLabel("Status :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(10, 11, 78, 14);
		frmServerDashbord.getContentPane().add(lblNewLabel_1);
		
		Status = new JLabel("Connected");
		Status.setForeground(new Color(50, 205, 50));
		Status.setBounds(68, 12, 86, 14);
		frmServerDashbord.getContentPane().add(Status);
		Icon imgIcon = new ImageIcon(this.getClass().getResource("Nt6v.gif"));
		lblNewLabel_2 = new JLabel(imgIcon);
		lblNewLabel_2.setBounds(338, 182, 283, 173);
		lblNewLabel_2.setVisible(false);
	
		
		frmServerDashbord.getContentPane().add(lblNewLabel_2);
		
		JLabel port = new JLabel("Port :");
		
		port.setFont(new Font("Tahoma", Font.BOLD, 13));
		port.setBounds(447, 0, 78, 14);
		frmServerDashbord.getContentPane().add(port);
		
		JLabel Status_1 = new JLabel(""+serverSocket.getLocalPort());
		Status_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Status_1.setForeground(Color.BLACK);
		Status_1.setBounds(505, 1, 86, 14);
		frmServerDashbord.getContentPane().add(Status_1);
		
		Adress = new JLabel("Adress");
		Adress.setFont(new Font("Tahoma", Font.BOLD, 13));
		Adress.setBounds(447, 17, 78, 14);
		frmServerDashbord.getContentPane().add(Adress);
		
		Status_2 = new JLabel(InetAddress.getLocalHost().getHostAddress().toString());
		Status_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Status_2.setForeground(Color.BLACK);
		Status_2.setBounds(505, 18, 126, 14);
		frmServerDashbord.getContentPane().add(Status_2);
		ImageIcon imgIcon2 = new ImageIcon(this.getClass().getResource("playlist.png"));
		Image image = imgIcon2.getImage(); // transform it 
		Image newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imgIcon2 = new ImageIcon(newimg);
		musicIcon = new JLabel(imgIcon2);
		musicIcon.setVisible(false);
		musicIcon.setBounds(348, 54, 257, 103);
		frmServerDashbord.getContentPane().add(musicIcon);
		
		listmusic = new JLabel("");
		listmusic.setVisible(false);
		listmusic.setBounds(338, 157, 293, 14);
		frmServerDashbord.getContentPane().add(listmusic);
	}
	public  void listen (){
		while(!serverSocket.isClosed()) {
			if(serverSocket.isClosed()) {
			
				break;
				
			}
			
		
			try {
				server = this.serverSocket.accept();
			
		
			
			log.append(("Client Connected "+server.getRemoteSocketAddress().toString()+"\n"));
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						while(true)
						if(server.getInputStream().read()==-1) {
							
							log.append("Client "+server.getRemoteSocketAddress()+" Disconnect \n");
							service.error=true;
							lblNewLabel_2.setVisible(false);
						     listmusic.setText("");
						     listmusic.setVisible(false);
						        musicIcon.setVisible(false);
							break;
						}
					} 
					catch(java.net.SocketException ex ) {JOptionPane.showMessageDialog(frmServerDashbord, "Connection to client "+ server.getRemoteSocketAddress()+" Failed !", "OOps",JOptionPane.ERROR_MESSAGE);
					log.append("Connection to Client "+server.getRemoteSocketAddress()+" Failed!  \n");
					service.error=true;
					lblNewLabel_2.setVisible(false);
				     listmusic.setText("");
				     listmusic.setVisible(false);
				        musicIcon.setVisible(false);
					}
					catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}).start();
			service = new Service(server,server.getPort(),this);service.start();
			}
			catch (java.net.SocketException ex ) {
				JOptionPane.showMessageDialog(frmServerDashbord, "Connection Closed by Admin ", "OOps",JOptionPane.ERROR_MESSAGE);
				try {
					server.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				log.append("Connection to Client "+server.getRemoteSocketAddress()+" Failed ! \n");
			}
			catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}}

	}
}
