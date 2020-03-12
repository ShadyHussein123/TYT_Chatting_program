import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class ChatClientTester
{
    public static void main(String[] args) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        ChatClient client = new ChatClient();
        client.startChat();
    }
}
