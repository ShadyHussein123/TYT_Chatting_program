import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient
{
    static JFrame chatFrame = new JFrame("TYT App");
    static JTextArea chatArea = new JTextArea(22,40);
    static JTextField messageField = new JTextField(40);
    static JLabel emptyLabel = new JLabel("             ");
    static JButton sendButton = new JButton("Send");
    static BufferedReader in;
    static PrintWriter out;


    public ChatClient()
    {
        chatFrame.setLayout(new FlowLayout());
        chatFrame.add(new JScrollPane(chatArea));

        chatFrame.add(emptyLabel);
        chatFrame.add(messageField);
        chatFrame.add(sendButton);

        chatFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        chatFrame.setSize(475,500);
        chatFrame.setVisible(true);

        messageField.setEditable(false);
        chatArea.setEditable(false);

        sendButton.addActionListener(new Listener());
        messageField.addActionListener(new Listener());
    }

    public void startChat()throws IOException
    {
         String ipAddress = JOptionPane.showInputDialog(chatFrame,"Enter ip Address: ","ip Address required!!",
                 JOptionPane.PLAIN_MESSAGE);

        Socket socket = new Socket(ipAddress,3000);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        while (true)
        {
            String read = in.readLine();
            if(read.equalsIgnoreCase("NameRequired"))
            {
                String name = JOptionPane.showInputDialog(chatFrame,"Enter username:"
                ,"Name Required",JOptionPane.PLAIN_MESSAGE);

                out.println(name);
            }
            else if(read.equalsIgnoreCase("NameAlreadyExists"))
            {
                String name = JOptionPane.showInputDialog(chatFrame,"Enter another username"
                        ,"Name already exists!!",JOptionPane.WARNING_MESSAGE);

                out.println(name);
            }
            else if (read.equals("NameAccepted"))
            {
                messageField.setEditable(true);
            }
            else
                {
                    chatArea.append(read + "\n");
                }



        }

    }

    class Listener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent actionEvent)
        {
            ChatClient.out.println(ChatClient.messageField.getText());
            ChatClient.messageField.setText("");
        }
    }
}
