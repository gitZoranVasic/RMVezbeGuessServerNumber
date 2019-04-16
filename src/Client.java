import javax.naming.Name;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    Socket socket;
    BufferedReader in;
    PrintWriter out;


    public Client() throws Exception{




            socket = new Socket("localhost", 8919);
            System.out.println("Klijent konektovan!");

            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);



            Scanner sc = new Scanner(System.in);
            System.out.println("Unesite ime klijenta");
            String name = sc.nextLine();
            out.println(name);

            System.out.println("Unesite broj od 0-20");
            out.println(sc.nextLine());

            String guess = in.readLine();

            while (!guess.toLowerCase().contains("pogodjen")) {
                System.out.println(guess);
                out.println(sc.nextLine());
                guess = in.readLine();
            }

            System.out.println(guess);

            socket.close();


    }

    public static void main(String[] args) {
        try {
            new Client();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
