package serveurpackage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        System.out.println("Je suis un serveur en attente de la connexion d'un client");

        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            // Première étape : attendre un client
            Socket socket = serverSocket.accept();

            System.out.println("Un client est connecté");

            // Dernière étape : fermer la socket du client
            socket.close();

        } catch (IOException e) {
            System.out.println("Erreur serveur : " + e.getMessage());
        }
    }
}
