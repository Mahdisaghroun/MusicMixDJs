
public class Lanch {
public static void main(String[] args) {
	Main main = new Main();
	main.frame.setVisible(true);
	new Thread (new Runnable() {
		
		@Override
		public void run() {
			for(int i=0;i<7;i++) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Menu menu = new Menu() ;
	main.frame.dispose();
			menu.frmMenu.setVisible(true);
			
			
		}
	}).start();
}
}
