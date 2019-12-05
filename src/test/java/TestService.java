import org.junit.Assert;
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

    @Test
    public void test_INIT_CHANNELS_OK_AND_NOT() throws InterruptedException
    {
        ConnectionService connectionService = new ConnectionService("192.168.1.4", true);
        if (!connectionService.isSocketConnected())
        {
            String error_type = connectionService.getERROR_TYPE();
            Assert.assertTrue(error_type.equals("connect timed out"));
            return;
        }

        connectionService.initChannels();
    }
}
