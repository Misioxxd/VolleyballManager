package com.gojowy.VolleyballManager;

import com.gojowy.VolleyballManager.Controller.InputController;

import java.net.*;
import java.io.*;

/**
 * The main class of the server
 *
 * @author Gall Anonim
 * @version 1.2
 */
public class TCPServer {

    /**
     * port number
     */
    final int PORT = 6666;

    /**
     * field represents the socket waiting for client connections
     */
    private ServerSocket serverSocket;

    /**
     * Creates the server socket
     *
     * @throws IOException when port is already bound
     */
    TCPServer() throws IOException {
        serverSocket = new ServerSocket(PORT);
    }

    /**
     * The main application method
     *
     * @params args all parametres are ignored
     */
    public static void main(String args[]) throws IOException {

        Socket socket = null;

        TCPServer tcpServer = null;
        try {
            tcpServer = new TCPServer();
            System.out.println("Server started");

            while (true) {
                socket = tcpServer.serverSocket.accept();
                try {
                    SingleService singleService = new SingleService(socket);
                    singleService.realize();
                } catch (IOException e) {
                    socket.close();
                    System.err.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            tcpServer.serverSocket.close();
        }
    }
}

/**
 * The server class servicing a single connection
 */
class SingleService {

    /**
     * socket representing connection to the client
     */
    private Socket socket;
    /**
     * buffered input character stream
     */
    private BufferedReader in;
    /**
     * Formatted output character stream
     */
    private PrintWriter out;

    /**
     * The constructor of instance of the SingleService class. Use the socket as
     * a parameter.
     *
     * @param socket socket representing connection to the client
     */
    public SingleService(Socket socket) throws IOException {
        this.socket = socket;
        out = new PrintWriter(
                new BufferedWriter(
                        new OutputStreamWriter(
                                socket.getOutputStream())), true);
        in = new BufferedReader(
                new InputStreamReader(
                        socket.getInputStream()));
    }

    /**
     * Realizes the service
     */
    public void realize() {
        try {
            out.println(new java.util.Date().toString());
            out.println("Enter the command: (HELP - list of commands)");

            while (true) {
                String str = in.readLine();
                if (str.toUpperCase().equals("QUIT")) {
                    break;
                } else if (str.toUpperCase().equals("HELP")) {
                    showList();
                } else if (str.toUpperCase().equals("START")) {
                    InputController inputController = new InputController(out, in);
                    out.println("Starting new Simulation insert round amount > 5 or quit to exit");
                    inputController.proceed();
                    break;
                } else {
                    out.println("Unknown command type help for command list");

                }

            }
            System.out.println("closing...");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
    /**
     * shows help
     */
    private void showList() {
        out.println("->START");
        out.println("--> QUIT");
    }
}
