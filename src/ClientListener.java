import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ClientListener extends Thread {
	public DatagramSocket CSocket;

	public ClientListener(DatagramSocket CSocket) {
		this.CSocket = CSocket;
	}

	@Override
	public void run() {
		try {

			while (true) {

				byte[] buf2 = new byte[10000];
				DatagramPacket packet2 = new DatagramPacket(buf2, buf2.length);
				CSocket.receive(packet2);
				String str = new String(packet2.getData());
				System.out.println(str);

			}

		} catch (Exception e) {
			e.getMessage();

		}
	}

}
