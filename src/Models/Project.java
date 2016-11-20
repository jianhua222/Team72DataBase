package Models;

/**
 * Created by Allen on 11/20/2016.
 */
public class Project {
    private String pname;
    private int pnum_student;
    private String adv_name;
    private String adv_email;
    private String discription;
    private String Desig_name;
    public Project(){
        this(" " , 0 , " ", " ", " " , " ");
    }
    public Project(String pname, int pnum_student, String adv_name, String adv_email, String discription, String Desig_name){
        this.pname = pname;
        this.pnum_student = pnum_student;
        this.adv_name = adv_name;
        this.adv_email = adv_email;
        this.discription = discription;
        this.Desig_name = Desig_name;
    }
    public String getPname(){
        return getPname();
    }
    public int getPnum_student(){
        return pnum_student;
    }
    public String getAdv_name(){
        return adv_name;
    }
    public String getAdv_email(){
        return adv_email;
    }
    public String getDiscription(){
        return discription;
    }
    public String getDesig_name(){
        return Desig_name;
    }
}
