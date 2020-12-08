import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Dhashboard {

	public JFrame frame;
	private JTextField textField;
	private File file;
	public Socket socket;

	public Dhashboard(Socket socket) {
		this.socket = socket;
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 770, 425);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Status :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 21, 62, 26);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Connected");
		lblNewLabel_1.setForeground(new Color(50, 205, 50));
		lblNewLabel_1.setBounds(60, 27, 75, 14);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblServer = new JLabel("Server :");
		lblServer.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblServer.setBounds(10, 41, 62, 26);
		frame.getContentPane().add(lblServer);

		JLabel lblNewLabel_2 = new JLabel(socket.getRemoteSocketAddress().toString());
		lblNewLabel_2.setBounds(60, 47, 166, 14);
		frame.getContentPane().add(lblNewLabel_2);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 65, 734, 2);
		frame.getContentPane().add(separator);

		JLabel lblDate = new JLabel("Date");

		lblDate.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDate.setBounds(587, 21, 62, 26);
		frame.getContentPane().add(lblDate);

		JLabel lblPlaceInServer = new JLabel("Place :");
		lblPlaceInServer.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPlaceInServer.setBounds(587, 41, 62, 26);
		frame.getContentPane().add(lblPlaceInServer);

		JLabel lblNewLabel_2_1 = new JLabel("1");
		lblNewLabel_2_1.setBounds(637, 47, 46, 14);
		frame.getContentPane().add(lblNewLabel_2_1);

		JLabel lblNewLabel_1_1 = new JLabel();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		lblNewLabel_1_1.setText(dateFormat.format(date).toString());
		lblNewLabel_1_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_1.setBounds(637, 27, 107, 14);
		frame.getContentPane().add(lblNewLabel_1_1);

		JLabel lblNewLabel_3 = new JLabel("Let's start streaming music in server !");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblNewLabel_3.setBounds(136, 85, 513, 33);
		frame.getContentPane().add(lblNewLabel_3);

		textField = new JTextField();
		textField.setEnabled(false);
		textField.setBorder(new LineBorder(Color.BLACK, 1, true));
		textField.setBounds(176, 192, 256, 31);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Choose music ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(".mp3", ".avi");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(frame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {

					file = new File(chooser.getSelectedFile().getAbsolutePath());
					textField.setText(chooser.getSelectedFile().getAbsolutePath());
				}
			}
		});
		btnNewButton.setFocusable(false);
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(442, 191, 118, 33);
		frame.getContentPane().add(btnNewButton);

		JButton btnSend = new JButton("Send ");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					if (!socket.isConnected()) {
						JOptionPane.showMessageDialog(frame, "Your are not connected to server", "Oops",
								JOptionPane.ERROR_MESSAGE);
					} else {
						FileInputStream otFile = new FileInputStream(file);
						byte[] totalSize = new byte[(int) file.length()];
						// BufferedInputStream botFile = new BufferedInputStream(otFile);
						ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
						ObjectOutputStream os = new ObjectOutputStream(outputStream);
						os.writeObject(file);
						byte[] data = outputStream.toByteArray();
						// System.out.println(totalSize);

						// byte[] buf2 = totalSize;
						// botFile.read(buf2);

						DatagramSocket DtSocket = new DatagramSocket();
						DatagramPacket packet2 = new DatagramPacket(data, data.length, socket.getLocalSocketAddress());
						// System.out.println("Sending 1 KO of File ...."+totalSize);
						DtSocket.send(packet2);

						// System.out.println("1 ko of file sent !");
						// totalSize-=1000;
					}
				} catch (Exception ex) {
				}
			}
		});
		btnSend.setFocusable(false);
		btnSend.setOpaque(false);
		btnSend.setBackground(Color.WHITE);
		btnSend.setBounds(326, 298, 118, 33);
		frame.getContentPane().add(btnSend);
	}
}
