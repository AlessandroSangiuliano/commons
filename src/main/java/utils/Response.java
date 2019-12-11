package utils;

import java.io.Serializable;
import java.util.List;

public class Response implements Serializable
{
    private String serverResponse;
    private String message;
    private List<String> classRooms;
    private List<StudentDetails> studentDetails;


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

    public List<String> getClassRooms()
    {
        return classRooms;
    }

    public void setClassRooms(List<String> classRooms)
    {
        this.classRooms = classRooms;
    }

    public List<StudentDetails> getStudentDetails()
    {
        return studentDetails;
    }

    public void setStudentDetails(List<StudentDetails> studentDetails)
    {
        this.studentDetails = studentDetails;
    }
}
