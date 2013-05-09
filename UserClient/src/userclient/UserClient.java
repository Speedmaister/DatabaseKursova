package userclient;

import java.util.Scanner;

public class UserClient {
    private UserClientThread thread;

    public UserClient() {}

    public static void main(String[] args) {
        UserClient user  = new UserClient();
        String     host  = "localhost";
        int        port  = 1234;
        Scanner    input = new Scanner(System.in);

        user.thread = new UserClientThread(host, port);

        GraphicsUserClient panel = new GraphicsUserClient(user.thread);

        user.thread.setGUI(panel);
        user.thread.start();
    }
}

