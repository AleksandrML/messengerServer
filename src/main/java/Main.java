import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Main {

    public static final String logFileName = "logs.log";
    public static final int PORT = SettingsReader.readPort();
    public static LinkedList<ServerThreadForClient> serverList = new LinkedList<>(); // список всех тредов для клиентов

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PORT);
        try {
            while (true) {
                // Блокируется до возникновения нового соединения:
                Socket clientSocket = server.accept();
                try {
                    serverList.add(new ServerThreadForClient(clientSocket)); // добавить новое соединенние в список
                } catch (IOException e) {
                    clientSocket.close();
                }
            }
        } finally {
            server.close();
        }
    }
}
