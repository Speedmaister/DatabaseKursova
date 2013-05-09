package twitter;

import java.io.DataOutputStream;
import java.io.IOException;

import java.net.Socket;

public class TwitterRefresher extends Thread {
    private Socket client;
    private String clientUserName;

    public TwitterRefresher(Socket client, String clientUserName) {
        this.client         = client;
        this.clientUserName = clientUserName;
    }

    @Override
    public void run() {
        try {
            while (true) {
                DataOutputStream out         = new DataOutputStream(this.client.getOutputStream());
                String           allMessages = Server.getAllMessagesForUser(this.clientUserName);

                out.writeUTF(allMessages);
                this.sleep(5000);
            }
        } catch (IOException ex) {
            System.out.println("Cant send data to client.");
        } catch (InterruptedException ex) {
            System.out.println("Problem with refresher.");
        }
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
