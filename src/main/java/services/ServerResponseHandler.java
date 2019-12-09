package services;

import constants.ServerResponse;
import utils.Response;

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

    public boolean handleOK()
    {
        boolean ok = false;

        Response response = this.deserializeObject();

        if (response.getServerResponse().equals(ServerResponse.OK))
            ok = true;

        return ok;
    }

    public boolean handleReceived()
    {
        boolean received = false;

        Response response = this.deserializeObject();

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
        } catch (ClassNotFoundException e)
        {
            logger.info("Class not found: " + e.getMessage());
        }

        return response;
    }

    public boolean handleExists()
    {
        boolean exists = false;

        Response response = this.deserializeObject();

        if (response.getServerResponse().equals(ServerResponse.EXISTS))
            exists = true;

        if (response.getServerResponse().equals(ServerResponse.NEXISTS))
            exists = false;

        return exists;
    }

    public String handleMessage()
    {
        Response response = deserializeObject();

        return response.getMessage();
    }

    public List<String> handleClassRooms()
    {
        Response response = deserializeObject();

        return response.getClassRooms();
    }
}
