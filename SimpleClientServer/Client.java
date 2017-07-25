import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

	public static void main(String[] args) throws IOException {
		String address = "127.0.0.1";   //из этой строки мы потом вытащим ip для подключения на локальном компе
		int serverPort = 8080;          //порт, который совпадает с портом, на котором сидит сервер
		Socket socket = null;
		
		try {
			InetAddress ipAddress = InetAddress.getByName(address);   //получение ip адреса из строки
			socket = new Socket(ipAddress, serverPort);   //создаем сокет для подключения к серверу
			System.out.println("connection successful");
			
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			
			DataInputStream in = new DataInputStream(is);
			DataOutputStream out = new DataOutputStream(os);
			
			while (true) {
				BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
				String line = null;
				System.out.println("input any string and we will send it to server");
				line = keyboard.readLine();
				out.writeUTF(line);           //отправляем строку, введенную с клавиатуры
				out.flush();
				System.out.println("line have sent successfully");
				line = in.readUTF();
				System.out.println("server`s answer: \n" + line);
				System.out.println();
			}			
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			socket.close();
		}
	}
}
