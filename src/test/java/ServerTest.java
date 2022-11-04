import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ServerTest {

    @Test
    public void testLogger() {
        // given:
        String clientName = "Vasya";
        String clientMessage = "hi";

        // when:
        String result = Logger.getLoggerMessage(clientMessage, clientName);

        // then:
        Assertions.assertTrue(result.contains(clientMessage));
        Assertions.assertTrue(result.contains(clientName));
    }

}
