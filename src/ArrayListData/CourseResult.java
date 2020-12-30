package ArrayListData;

public class CourseResult {
    private  int sno;
    private  String sname;
    private  int courseid;
    private  String cname;

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public int getCourseid() {
        return courseid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
    public CourseResult(){}
    public CourseResult(int sno, String sname, int courseid, String cname) {
        this.sno = sno;
        this.sname = sname;
        this.courseid = courseid;
        this.cname = cname;
    }
}
