import java.net.*;
import java.io.*;

public class Client {
	Socket socket;
	BufferedReader br;
	PrintWriter out;
	
	
	public Client() {
		try {
			System.out.println("Senting Request To Server");
			socket =new Socket("127.0.0.1",7777);
			System.out.println("Connection Done");
			
			
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());

			startReading();
			startWriting();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}

	public void startReading() {
		// thread - read the data;
		Runnable r1 =()-> {
			System.out.println("Reading Statrt");
			while (true) {
				try {
					String msg = br.readLine();
					if (msg.equals("exit")) {
						System.out.println("Server Terminater the chat");
						break;
					}
					System.out.println("Server : " + msg);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		};
		new Thread(r1).start();

	}
	
	public void startWriting() {
		// thread - user data take and send;
		
		Runnable r2 =()-> {
			System.out.println("Writing Starting ");
			while (true) {
				try {
					BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
					String content = br.readLine();
					out.print(content);
					out.flush();
				} catch (Exception e) {
					e.printStackTrace();

				}
			}

		};
		new Thread(r2).start();
	}

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("This is client...");
		new Client();  

	}

}
