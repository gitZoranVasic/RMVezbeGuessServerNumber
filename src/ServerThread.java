import java.io.*;
import java.net.Socket;

public class ServerThread implements Runnable {

    Socket socket;
    BufferedReader in;
    PrintWriter out;
    Server server;

    public ServerThread(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        try {

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

            String name = in.readLine();
            System.out.println("Konektovan je: " + name);

            int broj = server.getNum();
            int clientGuess = Integer.parseInt(in.readLine());

            while(clientGuess != broj ) {
                System.out.println("OVDE");

                System.out.println("Klijent " + name + " je pokusao sa brojem: " + clientGuess);
                out.println("Niste pogodili broj -- pokusajte ponovo!");
                clientGuess = Integer.parseInt(in.readLine());

                if(server.isPogodjen()) {
                    break;
                }
            }

            if(server.isPogodjen()) {
                out.println("Broj je vec pogodjen! Pogodio ga je klijent " + server.getPobednik() + ". Broj je " + broj);
            }else {
                out.println("POGODJEN! Pogodio ste! Broj je " + broj );
                server.setPogodjen(true);
                server.setPobednik(name);
                System.out.println("Pogodili ste, broj je " + broj);

            }
            socket.close();



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
