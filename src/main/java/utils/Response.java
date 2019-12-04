package utils;

import java.io.Serializable;

public class Response implements Serializable
{
    private String serverResponse;
    private String message;


    @Override
    public String toString()
    {
        return "Response{" +
                "serverResponse='" + serverResponse + '\'' +
                '}';
    }

    /*** ACCESSORS ***/

    public String getServerResponse()
    {
        return serverResponse;
    }

    public void setServerResponse(String serverResponse)
    {
        this.serverResponse = serverResponse;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
}
