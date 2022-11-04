import java.io.*;
import java.net.Socket;

class ServerThreadForClient extends Thread {

    private Socket socket; // сокет, через который сервер общается с клиентом,
    // кроме него - клиент и сервер никак не связаны
    private BufferedReader in; // поток чтения из сокета
    private PrintWriter out; // поток записи в сокет

    public ServerThreadForClient(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        start(); // вызываем run()
    }
    @Override
    public void run() {
        String word;
        try {
            System.out.println("New connection accepted");
            String requestString = in.readLine();
            String clientName = null;
            System.out.println(requestString);
            if (requestString.startsWith("name:")) {
                clientName = requestString.replace("name:", "");
                out.println(String.format("Hi %s, you're connected", clientName));
            }
            if (clientName == null) {
                out.println("You should provide your name");
                socket.close();
                return;
            }

            while (true) {
                word = in.readLine();
                if(word.equals("/exit")) {
                    System.out.println(clientName + " is out");
                    break;                }
                for (ServerThreadForClient vr : Main.serverList) {
                    Logger.write(word, clientName);
                    vr.send(clientName + ": " + word); // отослать принятое сообщение с
                    // привязанного клиента всем остальным включая его
                }
            }
        } catch (IOException e) {
        }
    }

    private void send(String msg) {
        out.println(msg);
    }
}
