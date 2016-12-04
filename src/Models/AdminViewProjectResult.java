package Models;

/**
 * Created by Ranchy on 12/4/2016.
 */
public class AdminViewProjectResult {
    private String project;
    private String major;
    private String year;
    private String status;
    private String student;
    public AdminViewProjectResult(String project, String major, String year, String status, String student){
        this.project = project;
        this.major = major;
        this.year = year;
        this.status = status;
        this.student = student;

    }
    public String getProject(){
        return project;
    }
    public String getMajor(){
        return major;
    }
    public String getYear(){
        return year;
    }
    public String getStatus(){
        return status;
    }
    public String getStudent(){
        return student;
    }
    public String toString(){
        return project+student;
    }
}
