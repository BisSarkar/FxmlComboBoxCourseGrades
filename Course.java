/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courseList;

/**
 *
 * @author snadi
 */
public class Course  {
    
    private String code;
    private double grade;
    private double maxGrade;
    
    public Course(String code, double grade, double maxGrade) {
        setCode(code);
        setGrade(grade);
        setMaxGrade(maxGrade);
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        if (code != null && !code.trim().isEmpty() && code.matches("[a-zA-Z]{4}\\d{5}"))
            this.code = code;
        else
            throw new IllegalArgumentException("Course code can't be empty");
    }
    
    public double getGrade() {
        return grade;
    }
    
    public void setGrade(double grade) {
        if (grade >= 0)
            this.grade = grade;
        else
            throw new IllegalArgumentException("Grade can't be a negative value.");
    }
    
    public double getMaxGrade() {
        return maxGrade;
    }
    
    public void setMaxGrade(double maxGrade) {
       if (maxGrade >= 0)
            this.maxGrade = maxGrade;
        else
            throw new IllegalArgumentException("Grade can't be a negative value.");
    }
    @Override
    public String toString() {
        return String.format("%s", code);
    }
    
    // todo: hash
    
}