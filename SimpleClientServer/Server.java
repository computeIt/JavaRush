import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		int port = 8080;      //задаем порт на котором будет ждать сервер

		try(ServerSocket serverSocket = new ServerSocket(port)) {
			
			System.out.println("waiting for a connection...");    
			Socket socket = serverSocket.accept();          //после подключения к сокету сервера создаем еще сокет, через который и будет идти работа
			System.out.println("we have a client! huraaaay)))) ");
			
			InputStream sIn = socket.getInputStream();
			OutputStream sOut = socket.getOutputStream();
			
			DataInputStream dataIn = new DataInputStream(sIn);  //данные классы использованы для облегчения чтения строк (?)
			DataOutputStream dataOut = new DataOutputStream(sOut);
			
			String line = null;
			while(true){
				line = dataIn.readUTF();    //читаем входящую строку от клиента
				System.out.println("client sent to server string: " + line);
				System.out.println("my server sends back reversed string");
				StringBuilder sb = new StringBuilder(line);
				line = sb.reverse().toString(); //переворачиваем ее
				
				dataOut.writeUTF(line);         //и отсылаем
				dataOut.flush();
				System.out.println("waiting for a next line...");
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
