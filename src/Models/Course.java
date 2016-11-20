package Models;

/**
 * Created by Ranchy on 11/20/2016.
 */
public class Course {
    private String cnumber;
    private String cname;
    private String instructor;
    private int cnum_student;
    private String Desig_name;
    public Course(String cnumber, String cname,String instructor, int cnum_student, String Desig_name){
        this.cnumber = cnumber;
        this.cname = cname;
        this.instructor = instructor;
        this.cnum_student = cnum_student;
        this.Desig_name = Desig_name;
    }
    public String getCnumber(){
        return cnumber;
    }
    public String getCname(){
        return cname;
    }
    public String getInstructor(){
        return instructor;
    }
    public int getCnum_student(){
        return cnum_student;
    }
    public String getDesig_name(){
        return Desig_name;
    }

}
