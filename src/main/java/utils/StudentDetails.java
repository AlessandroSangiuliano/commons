package utils;

public class StudentDetails
{
    private String name;
    private String lastName;

    public StudentDetails()
    {

    }

    public StudentDetails(String aName, String aLastName)
    {
        name = aName;
        lastName = aLastName;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
}
