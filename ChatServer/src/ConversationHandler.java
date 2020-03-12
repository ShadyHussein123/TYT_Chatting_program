import java.io.*;
import java.net.Socket;

public class ConversationHandler extends Thread
{
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out,printToFile;
    private FileWriter fileWriter;
    private BufferedWriter  bufferedWriter;
    String name;

    public ConversationHandler(Socket socket) throws IOException
    {
        this.socket = socket;
        fileWriter = new FileWriter("TYT_log.txt",true);
        bufferedWriter = new BufferedWriter(fileWriter);
        printToFile = new PrintWriter(bufferedWriter,true);
    }

    public void run()
    {
        try
        {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);

            int count = 0;
            while (true)
            {
                if(count > 0)
                {
                    out.println("NameAlreadyExists");
                }
                else
                {
                    out.println("NameRequired");
                }
                name = in.readLine();

                if (name== null)
                {
                    return;
                }

                if (!ChatServer.usernames.contains(name))
                {
                     ChatServer.usernames.add(name);
                     break;
                }
                count++;
            }
            out.println("NameAccepted" + name);
            ChatServer.printWriterArrayList.add(out);

            while (true)
            {
                String message = in.readLine();

                if(message == null)
                {
                    return;
                }

                printToFile.println(name + ": " + message);
                for(PrintWriter writer : ChatServer.printWriterArrayList)
                {
                    writer.println(name + ": " + message);
                }
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
