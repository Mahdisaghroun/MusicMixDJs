package Ui;
import java.awt.Frame;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

import jaco.mp3.player.MP3Player;

public class Service extends Thread {
	public File file =null;
	public Socket socket ;
	public 	DatagramPacket packet ;
	public int port ;
	 static boolean playCompleted;
	public static ServerDashboard frame;
	 public static MP3Player player;
	 public static boolean error=false;
	
	 BufferedOutputStream bOut=null;
	public Service (Socket socket, int port,ServerDashboard frame) {
		//this.packet=packet;
		this.socket=socket;
		this.port=port;
		this.frame=frame;
		 
		
	}
	
	public void run() {
		try {
			if(!socket.isConnected()) {
				JOptionPane.showMessageDialog(frame.frmServerDashbord, "Connection closed ", "Oops", JOptionPane.ERROR_MESSAGE);
			}else {
			DatagramSocket server;
			server = new DatagramSocket(this.port);
			while(true) {
				
				byte[] buf = new byte[1024 * 1000 * 50];
				
				DatagramPacket packet = new DatagramPacket(buf, buf.length);
				
				server.receive(packet);
			frame.log.append("Client "+ socket.getRemoteSocketAddress()+" want to Stream a music file! \n ");
				//System.out.println("Data Come");
				String str=".mp3";
				
				
				
				byte[] data = packet.getData();
				ByteArrayInputStream in = new ByteArrayInputStream(data);
				ObjectInputStream is = new ObjectInputStream(in);
				file = (File) is.readObject();
				FileOutputStream bOut = new FileOutputStream(new File("D:\\Server\\"+file.getName()));
			
				frame.listOfmusic.add(file.getName());
				FileInputStream fin = new FileInputStream(file);
				
				int length;
				  byte[] buffer = new byte[(int) file.length()];
				 while ((length = fin.read(buffer)) > 0) {
			            bOut.write(buffer, 0, length);
			        }
				
				 frame.log.append("Now Streaming music file of client  "+ socket.getRemoteSocketAddress()+"\n");
				//System.out.println("Data Writed !! ");
				fin.close();
				bOut.close();
				
				 play("D:\\Server\\"+file.getName());
				
			}
				
		
			}}catch(Exception e) {}
		
		
	}
	 public static void play(String audioFilePath) {
	        File audioFile = new File(audioFilePath);
	
	      player = new MP3Player(audioFile);
	     player.play();
	     frame.lblNewLabel_2.setVisible(true);
	     frame.listmusic.setText(frame.listOfmusic.get(0));
	     frame.listmusic.setVisible(true);
	         frame.musicIcon.setVisible(true);
	         new Thread(new Runnable() {
				
				@Override
				public void run() {
				
				while(true) {
					 
					if( error ) {
					
						player.stop();
						frame.lblNewLabel_2.setVisible(false);
					     frame.listmusic.setText("");
					     frame.listmusic.setVisible(false);
					       frame.musicIcon.setVisible(false);
					       error=true;
					      
					 
					      
					       
					       break;
					}
				}
					
				}
			}).start();
	    }
}
