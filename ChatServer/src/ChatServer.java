import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class ChatServer
{
    static HashMap<String, PrintWriter> nameDB;

    static ArrayList<String> usernames = new ArrayList<>();
    static ArrayList<PrintWriter> printWriterArrayList = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        System.out.println("Waiting for clients...");
        int portNumber = 3000;
        ServerSocket serverSocket = new ServerSocket(portNumber);

        while(true)
        {
            Socket socket = serverSocket.accept();
            System.out.println("Connection Established");
            ConversationHandler handler = new ConversationHandler(socket);
            handler.start();

        }
    }
}
