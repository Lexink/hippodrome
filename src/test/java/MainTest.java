import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@Disabled
public class MainTest {
    @Test
    @Timeout(value = 22)
    void failsIfExecutionTimeExceeds22Seconds() throws Exception{
        String[] args = new String[0];
        Main.main(args);
    }
}
