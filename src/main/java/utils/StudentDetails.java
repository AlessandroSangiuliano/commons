package utils;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class StudentDetails implements Serializable
{
    private String name;
    private String lastName;
    private List<Integer> grades;
    private Double average;

    public StudentDetails()
    {

    }

    public StudentDetails(String aName, String aLastName)
    {
        name = aName;
        lastName = aLastName;
    }

    public Double calculateAverage()
    {
        Integer sum = grades.stream().collect(Collectors.summingInt(Integer::intValue));
        average = Double.valueOf(sum) / Double.valueOf(grades.size());
        return average;
    }

    /*** ACCESSORS ***/

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

    public Double getAverage()
    {
        if (average == null || average == 0.0d || average == 0)
            average = calculateAverage();

        return average;
    }

    public void setAverage(Double average)
    {
        this.average = average;
    }
}
