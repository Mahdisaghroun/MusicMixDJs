import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		try {

			DatagramSocket client = new DatagramSocket();// create client datagramSocket
			while (true) {
				InetAddress address = InetAddress.getByName("localhost");// get the adrr of host if exist
				System.out.println("Search video :  :");

				String user = sc.nextLine();

				byte[] buf = user.getBytes();

				DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 8088);// craete datagramPcaket with
																							// data , length, addr ,and
																							// port

				packet.setData(buf);// force adding data to packet
				System.out.println("  Sending Data To Server ...");
				Thread.sleep(3000);
				client.send(packet);
				System.out.println("  Data Sent To Server");
				new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							BufferedOutputStream bOut = new BufferedOutputStream(
									new FileOutputStream(new File("D:\\natureexport.mp4")));
							while (true) {

								byte[] buf2 = new byte[1000];
								DatagramPacket packet2 = new DatagramPacket(buf2, buf2.length);
								client.receive(packet2);

								System.out.println("Wrinting in file ...");
								packet2.setLength(buf2.length);
								bOut.write(packet2.getData(), 0, buf2.length);
								bOut.flush();

							}
						} catch (Exception e) {
						}
					}
				}).start();
				;
			}
		} catch (Exception e) {
		}
	}
}
