package twitter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import java.net.Socket;

import java.sql.*;

import java.util.ArrayList;

public class Server {
    private static Connection        link  = null;
    private static String            url   = "jdbc:mysql://localhost:3306/twitter";
    private static String            user  = "test";
    private static String            pass  = "test";
    private static int               port  = 1234;
    private static ArrayList<Socket> users = new ArrayList<Socket>(10);

    public static void main(String[] args) {
        ServerListener s;

        try {
            s = new ServerListener(port);
        } catch (IOException e) {
            System.out.println("Can't open server socket on port: " + port);

            return;
        }

        while (true) {
            try {
                Socket clientSocket = s.acceptConnection();

                users.add(clientSocket);

                ServerThread newThread = new ServerThread(clientSocket);
            } catch (IOException e) {
                System.out.println("Connection failed...");
            }
        }
    }

    static void removeClient(Socket userSocket) {
        synchronized (users) {
            DataOutputStream userOutput;

            try {
                userOutput = new DataOutputStream(userSocket.getOutputStream());
                userOutput.writeUTF("Invalid user name or password");
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage() + "Error occurred during user disposal");
            }

            System.out.println("Disconnecting user " + userSocket);
            users.remove(users.indexOf(userSocket));

            try {
                userSocket.close();
            } catch (IOException ioe) {
                System.out.println("Cant break connection.");
            }
        }
    }

    public static Connection connectToDatabase() {
        try {
            link = DriverManager.getConnection(url, user, pass);
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage() + "Error occurred during connection to the database.");
        }

        return link;
    }

    public static boolean validateUser(Connection link, String userName, String userPass) {
        boolean           isValid   = true;
        PreparedStatement statement = null;
        ResultSet         result    = null;

        try {
            statement = link.prepareStatement("select name,password\n" + "from users\n"
                                              + "where name = ? and password = ?;");
            statement.setString(1, userName);
            statement.setString(2, userPass);
            result = statement.executeQuery();
            result.next();
            isValid = !(result.getString("name").isEmpty());
        } catch (SQLException sqle) {
            System.err.println("Error occurred during validation of user data.");
            isValid = false;
        } finally {
            try {
                statement.close();
                result.close();
            } catch (SQLException sqle) {
                System.err.println("Error occurred during statement and result close() call.");
            }
        }

        return isValid;
    }

    public static String getText(Socket userSocket) {
        DataInputStream userInput;
        String          text = null;

        try {
            userInput = new DataInputStream(userSocket.getInputStream());
            text      = userInput.readUTF();
        } catch (IOException ex) {
            System.out.println("Couldnt get text.");
        }

        return text;
    }

    public static void saveMessageToDatabase(String userName, String message) {
        PreparedStatement statement = null;
        ResultSet         result    = null;

        try {
            statement = link.prepareStatement("select channels.id " + "from channels "
                                              + "where channels.owner_name = ?;");
            statement.setString(1, userName);
            result = statement.executeQuery();
            result.next();

            short channelId = result.getShort("id");

            statement = link.prepareStatement("select max(id) from messages");
            result    = statement.executeQuery();
            result.next();

            int newMessageId = result.getInt("max(id)") + 1;

            statement = link.prepareStatement("insert into messages " + "values(?,?,?,?);");
            statement.setInt(1, newMessageId);
            statement.setDate(2, new java.sql.Date((new java.util.Date()).getTime()));
            statement.setString(3, message);
            statement.setShort(4, channelId);
            statement.executeUpdate();
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage() + " Error occurred while saving message to database.");
        } finally {
            try {
                statement.close();
                result.close();
            } catch (SQLException sqle) {
                System.err.println("Closing statement and result in save message method.");
            }
        }
    }

    public static void subscribeUserToAnotherChannel(String userName, String ownerOfChannelSubscribingTo) {
        PreparedStatement statement = null;
        ResultSet         result    = null;

        try {
            statement = link.prepareStatement("select channels.id " + "from channels "
                                              + "where channels.owner_name = ?;");
            statement.setString(1, ownerOfChannelSubscribingTo);
            result = statement.executeQuery();
            result.next();

            short channelId = result.getShort("id");

            statement = link.prepareStatement("insert into users_channels " + "values(?,?);");
            statement.setShort(1, channelId);
            statement.setString(2, userName);
            statement.executeUpdate();
        } catch (SQLException sqle) {
            System.err.println("Error occurred while subscribing to new channel.");
        } finally {
            try {
                statement.close();
                result.close();
            } catch (SQLException sqle) {
                System.err.println("Closing statement and result in subscribe to channel method.");
            }
        }
    }

    public static String getAllMessagesForUser(String userName) {
        PreparedStatement statement   = null;
        ResultSet         result      = null;
        StringBuilder     allMessages = new StringBuilder();

        try {
            statement = link.prepareStatement("select t2.owner_name,messages.send_date,messages.content "
                                              + "from messages join ( " + " select t1.channel_id,channels.owner_name "
                                              + " from channels join ( " + "         select users_channels.channel_id "
                                              + "         from users_channels "
                                              + "         where users_channels.user_name = ? "
                                              + "         order by users_channels.channel_id) as t1 "
                                              + " on t1.channel_id = channels.id " + " order by t1.channel_id) as t2 "
                                              + "on messages.channel_id = t2.channel_id " 
                                              + "order by messages.id desc;");
            statement.setString(1, userName);
            result = statement.executeQuery();

            while (result.next()) {
                allMessages.append(result.getString("owner_name"));
                allMessages.append("(");
                allMessages.append(result.getDate("send_date").toString());
                allMessages.append(") : \n");
                allMessages.append(result.getString("content"));
                allMessages.append("\n");
            }
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage() + " Error occurred while getting all messages.");
        } finally {
            try {
                statement.close();
                result.close();
            } catch (SQLException sqle) {
                System.err.println("Closing statement in subscribe to channel method.");
            }
        }

        return allMessages.toString();
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
