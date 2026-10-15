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

                System.out.println("Client connecte.");

                while (true) {
                    int a = in.readInt();

                    if (a == 0) {
                        System.out.println("Fin de la communication.");
                        break;
                    }

                    int b = in.readInt();
                    char operation = in.readChar();
                    double resultat = 0;

                    switch (operation) {
                        case '+':
                            resultat = a + b;
                            break;
                        case '-':
                            resultat = a - b;
                            break;
                        case '*':
                            resultat = a * b;
                            break;
                        case '/':
                            if (b == 0) {
                                out.writeUTF("Erreur : division par zero");
                                continue;
                            }
                            resultat = (double) a / b;
                            break;
                        default:
                            out.writeUTF("Operation invalide");
                            continue;
                    }

                    out.writeUTF(String.valueOf(resultat));
                    out.flush();

                    System.out.println(a + " " + operation + " " + b
                            + " = " + resultat);
                }
            }
        } catch (IOException e) {
            System.out.println("Erreur serveur : " + e.getMessage());
        }
    }
}