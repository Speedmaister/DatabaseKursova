package twitter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import java.net.Socket;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ServerThread extends Thread {
    private Socket sock;

    public ServerThread(Socket clientSocket) {
        this.sock = clientSocket;
        this.start();
    }

    private static Connection connect(String url, String user, String password) {
        Connection result = null;

        try {
            result = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Connection problem" + e.getMessage());
        }

        return result;
    }

    @Override
    public void run() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            System.out.println("Cant load JDBC driver.");
        }

        DataInputStream  in;
        DataOutputStream out;
        String           message;
        Connection       link = Server.connectToDatabase();

        try {
            in  = new DataInputStream(this.sock.getInputStream());
            out = new DataOutputStream(sock.getOutputStream());

            String userName;
            String userPassword;

            userName     = in.readUTF();
            userPassword = in.readUTF();

            boolean isValidated = Server.validateUser(link, userName, userPassword);

            if (!isValidated) {
                Server.removeClient(sock);
            } else {
                out.writeUTF("Login successful.");

                TwitterRefresher refresher = new TwitterRefresher(this.sock, userName);

                refresher.start();

                while (true) {
                    String task = in.readUTF();

                    switch (task) {
                    case "message" :
                        String userMessage = Server.getText(sock);

                        Server.saveMessageToDatabase(userName, userMessage);

                        break;

                    case "subscribe" :
                        String ownerOfNewChannel = Server.getText(sock);

                        Server.subscribeUserToAnotherChannel(userName, ownerOfNewChannel);

                        break;

                    case "quit" :
                        Server.removeClient(sock);

                        break;
                    }
                }
            }
        } catch (IOException ioe) {
            System.out.println("Problem occurred on data tranfer for user :" + this.sock.toString());
        } finally {
            try {
                Server.removeClient(this.sock);

                if (link != null) {
                    link.close();
                }
            } catch (SQLException sqle) {
                System.out.println("Error closing link to database.");
            }
        }
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
