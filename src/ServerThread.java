import java.io.*;
import java.net.Socket;

public class ServerThread implements Runnable {

    Socket socket;
    BufferedReader in;
    PrintWriter out;

    public ServerThread(Socket socket) {
        try {

            this.socket = socket;
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

            String name = in.readLine();
            System.out.println("Konektovan je: " + name);

            int broj = 20;
            int clientGuess = Integer.parseInt(in.readLine());

            while(clientGuess != broj) {
                System.out.println("Klijent " + name + " je pokusao sa brojem: " + clientGuess);
                out.println("Niste pogodili broj -- pokusajte ponovo!");
                clientGuess = Integer.parseInt(in.readLine());
            }

            out.println("Pogodili ste, broj je " + broj);
            System.out.println("Pogodili ste, broj je " + broj);
            socket.close();



        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void run() {

    }
}
