import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {


    ServerSocket server_socket;
    BufferedReader in;
    PrintWriter out;
    int port = 8919;

    public Server() throws Exception {

        server_socket = new ServerSocket(port);
        System.out.println("Server listening to port: " + port);
        Socket socket = server_socket.accept(); //accept client connection
        ServerThread server_thread = new ServerThread(socket);
        Thread thread = new Thread(server_thread);
        thread.start();


    }

    public static void main(String[] args) {
        try {
            new Server();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
