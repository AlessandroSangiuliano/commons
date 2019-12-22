package utils;

import java.io.Serializable;
import java.util.List;

public class StudentDetails implements Serializable
{
    private String name;
    private String lastName;
    private List<Integer> grades;

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

    public List<Integer> getGrades()
    {
        return grades;
    }

    public void setGrades(List<Integer> grades)
    {
        this.grades = grades;
    }
}
