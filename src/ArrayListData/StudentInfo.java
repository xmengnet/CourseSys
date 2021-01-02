package ArrayListData;

public class StudentInfo {
    private  int sno;
    private  String spasswd;
    private String sname;
    private  String classes;
    private  String  id;
    private  String enrollment;

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public String getSpasswd() {
        return spasswd;
    }

    public void setSpasswd(String spasswd) {
        this.spasswd = spasswd;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(String enrollment) {
        this.enrollment = enrollment;
    }

    public StudentInfo() {
    }

    public StudentInfo(int sno, String spasswd, String sname, String classes, String id, String enrollment) {
        this.sno = sno;
        this.spasswd = spasswd;
        this.sname = sname;
        this.classes = classes;
        this.id = id;
        this.enrollment = enrollment;
    }
}
