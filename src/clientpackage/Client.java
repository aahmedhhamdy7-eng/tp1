package clientpackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner clavier = new Scanner(System.in);

        try (Socket socket = new Socket("localhost", 1234);
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             DataInputStream in = new DataInputStream(socket.getInputStream())) {

            System.out.println("Connecté au serveur.");

            int x;

            do {
                System.out.print("Saisir un entier (0 pour arrêter) : ");
                x = clavier.nextInt();

                out.writeInt(x);
                out.flush();

                if (x != 0) {
                    int resultat = in.readInt();
                    System.out.println("Résultat : " + resultat);
                }

            } while (x != 0);

            System.out.println("Communication terminée.");

        } catch (IOException e) {
            System.out.println("Erreur client : " + e.getMessage());
        }
    }
}
