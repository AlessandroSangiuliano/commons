package utils;

import java.io.Serializable;
import java.util.List;

public class CommandArguments implements Serializable
{
    private List<String> arguments;
    private String command;

    /*** ACCESSORS ***/

    public List<String> getArguments()
    {
        return arguments;
    }

    public void setArguments(List<String> arguments)
    {
        this.arguments = arguments;
    }

    public String getCommand()
    {
        return command;
    }

    public void setCommand(String command)
    {
        this.command = command;
    }

    @Override
    public String toString()
    {
        return "CommandArguments{" +
                "arguments=" + arguments +
                ", command='" + command + '\'' +
                '}';
    }
}
