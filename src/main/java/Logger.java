import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Logger {

    public static void write(String message, String userName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(Main.logFileName, true))) {
            String text = getLoggerMessage(message, userName);
            bw.write(text);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    protected static String getLoggerMessage(String message, String userName) {
        return LocalDateTime.now() + "; " + userName + ": " + message + "\n";
    }
}
