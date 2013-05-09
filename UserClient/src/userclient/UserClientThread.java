package userclient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import java.net.Socket;

public class UserClientThread extends Thread {
    private Socket             socket;
    private DataOutputStream   out;
    private DataInputStream    in;
    private String             textReceived;
    private GraphicsUserClient panel;

    public UserClientThread(String host, int port) {
        try {
            this.socket = new Socket(host, port);
            this.in     = new DataInputStream(this.socket.getInputStream());
            this.out    = new DataOutputStream(this.socket.getOutputStream());
        } catch (IOException e) {
            System.out.println("Cant connect to server.");
        }
    }

    @Override
    public void run() {
        this.panel.invokeGUI();

        try {
            while (!this.socket.isClosed()) {
                this.textReceived = this.in.readUTF();
                this.writeToTextBox(textReceived);
                if(textReceived == "Invalid username or password"){
                    this.panel.dispose();
                    this.close();
                }
            }
        } catch (IOException e) {
            System.out.println("Interrupting thread caus of error.");
            this.interrupt();
        }
    }

    public DataOutputStream getOutputStream() {
        return this.out;
    }

    void setGUI(GraphicsUserClient panel) {
        this.panel = panel;
    }

    void writeToTextBox(String text) {
        this.panel.writeToTextPanel(text);
    }

    void sendMessage(String message) {
        try {
            this.out.writeUTF(message);
            this.out.flush();
        } catch (IOException e) {
            System.out.println("Cant send message.");
        }
    }

    void close() throws IOException {
        if (this.in != null) {
            in.close();
        }

        if (this.out != null) {
            out.close();
        }

        if (this.socket != null) {
            socket.close();
        }
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
