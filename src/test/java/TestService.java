import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import services.ConnectionService;
import services.ServerResponseHandler;
import utils.StudentDetails;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void test_THREADEDRESPONSE() throws InterruptedException
    {
        ConnectionService connectionService = new ConnectionService("127.0.0.1", true);
        connectionService.initChannels();
        ServerResponseHandler responseHandler = new ServerResponseHandler(connectionService);
        final boolean[] ok = {false};
        String ciao;

        new Thread(){
            @Override
            public void run()
            {
                try
                {
                    ok[0] = responseHandler.handleOK();
                } catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        }.start();


        Thread.sleep(3000);
        Assert.assertTrue(ok[0]);

    }

    @Test
    public void test_RECEIVED_OK_EXCEPTION()
    {

    }

    @Test
    public void test_CALCULATE_STUDENT_AVERAGE()
    {
        StudentDetails student = new StudentDetails("MARIO", "ROSSI");
        List<Integer> grades = new ArrayList<>();
        grades.add(4);
        grades.add(8);

        student.setGrades(grades);

        Double average = student.calculateAverage();
        Assert.assertTrue(average == 6.0d);
    }
}
