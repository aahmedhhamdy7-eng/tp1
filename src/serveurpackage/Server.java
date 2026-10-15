package serveurpackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Serveur en attente d'un client...");

            try (Socket socket = serverSocket.accept();
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
