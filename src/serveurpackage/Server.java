package serveurpackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class Server {
    public static void main(String[] args) {
        try {
            InetAddress adresse = InetAddress.getLocalHost();
            int port = 1234;

            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(adresse, port));

            System.out.println("Serveur en attente d'un client...");
            System.out.println("Adresse IP : " + adresse.getHostAddress());

            try (ServerSocket ss = serverSocket;
                 Socket socket = ss.accept();
                 DataInputStream in = new DataInputStream(socket.getInputStream());
                 DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

                System.out.println("Client connecté.");

                int x;

                while (true) {
                    x = in.readInt();

                    if (x == 0) {
                        System.out.println("Fin de la communication.");
                        break;
                    }

                    int resultat = x * 5;

                    out.writeInt(resultat);
                    out.flush();

                    System.out.println("Reçu : " + x
                            + " | Résultat : " + resultat);
                }
            }

        } catch (IOException e) {
            System.out.println("Erreur serveur : " + e.getMessage());
        }
    }
}