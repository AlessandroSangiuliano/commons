import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import services.ConnectionService;

@RunWith(JUnit4.class)
public class TestService
{
    @Test
    public void test_CHECK_CONNECTION()
    {
        boolean b = ConnectionService.checkConnection();
    }
}
