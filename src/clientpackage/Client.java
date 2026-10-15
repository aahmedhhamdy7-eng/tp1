package clientpackage;

import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        System.out.println("Je suis un client pas encore connecté...");

        try (Socket socket = new Socket("localhost", 5000)) {
            System.out.println("Je suis un client connecté");

        } catch (IOException e) {
            System.out.println("Erreur client : " + e.getMessage());
        }
    }
}
