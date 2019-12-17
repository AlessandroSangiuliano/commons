package services;

import constants.ServerResponse;
import utils.Response;
import utils.StudentDetails;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.logging.Logger;

public class ServerResponseHandler
{
    private ConnectionService connectionService;
    private Logger logger = Logger.getLogger(ConnectionService.class.getName());

    public ServerResponseHandler(ConnectionService aConnectionService)
    {
        connectionService = aConnectionService;
    }

    public boolean handleOK() throws Exception
    {
        boolean ok = false;

        Response response = this.deserializeObject();

        if (response.getException() != null)
            throw response.getException();

        if (response.getServerResponse().equals(ServerResponse.OK))
            ok = true;

        return ok;
    }

    public boolean handleReceived() throws Exception
    {
        boolean received = false;

        Response response = this.deserializeObject();

        if (response.getException() != null)
            throw response.getException();

        if (response.getServerResponse().equals(ServerResponse.RECEIVED))
            received = true;

        return received;
    }

    public Response deserializeObject()
    {
        Response response = null;
        ObjectInputStream objectInputStream = connectionService.getObjectInputStream();

        try
        {
            response = (Response) objectInputStream.readObject();
        } catch (IOException e)
        {
            logger.info("Unable to read the response: " + e.getMessage());
            response = new Response();
            response.setException(e);
        } catch (ClassNotFoundException e)
        {
            logger.info("Class not found: " + e.getMessage());
            response = new Response();
            response.setException(e);
        }

        return response;
    }

    public boolean handleExists() throws Exception
    {
        boolean exists = false;

        Response response = this.deserializeObject();

        if (response.getException() != null)
            throw response.getException();

        if (response.getServerResponse().equals(ServerResponse.EXISTS))
            exists = true;

        if (response.getServerResponse().equals(ServerResponse.NEXISTS))
            exists = false;

        return exists;
    }

    public String handleMessage() throws Exception
    {
        Response response = deserializeObject();

        if (response.getException() != null)
            throw response.getException();

        return response.getMessage();
    }

    public List<String> handleClassRooms() throws Exception
    {
        Response response = deserializeObject();

        if (response.getException() != null)
            throw response.getException();

        return response.getClassRooms();
    }

    public List<StudentDetails> handleStudentNamesAndLastNames() throws Exception
    {
        Response response = deserializeObject();

        if (response.getException() != null)
            throw response.getException();

        return response.getStudentDetails();
    }

}
