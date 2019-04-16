import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Server {

    private int num = (int) (Math.random() * 20 + 1);
    private boolean pogodjen = false;
    private String pobednik;

    ServerSocket server_socket;
    BufferedReader in;
    PrintWriter out;
    int port = 8919;

    public Server() throws Exception {
        System.out.println("BROJ JE " + num);
        server_socket = new ServerSocket(port);
        System.out.println("Server listening to port: " + port);


        while (true) {
            Socket socket = server_socket.accept(); //accept client connection
            ServerThread server_thread = new ServerThread(socket, this);
            Thread thread = new Thread(server_thread);
            thread.start();
        }
    }

    public static void main(String[] args) {
        try {
            new Server();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean isPogodjen() {
        return pogodjen;
    }

    public void setPogodjen(boolean pogodjen) {
        this.pogodjen = pogodjen;
    }

    public String getPobednik() {
        return pobednik;
    }

    public void setPobednik(String pobednik) {
        this.pobednik = pobednik;
    }
}
