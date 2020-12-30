package ArrayListData;

public class Courses {
    private int courseid ;
    private String cname;
    private  int limitnum ;
    private  int  surplus;
    private  int  teachtime;
    private String cteacher;

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
    public int getLimitnum() {
        return limitnum;
    }
    public void setLimitnum(int limitnum) {
        this.limitnum = limitnum;
    }
    public int getSurplus() {
        return surplus;
    }

    public void setSurplus(int surplus) {
        this.surplus = surplus;
    }

    public int getTeachtime() {
        return teachtime;
    }

    public void setTeachtime(int teachtime) {
        this.teachtime = teachtime;
    }

    public String getCteacher() {
        return cteacher;
    }
    public void setCteacher(String cteacher) {
        this.cteacher = cteacher;
    }
    public Courses(){}
    public Courses(int courseid, String cname, int limitnum, int surplus, int teachtime, String cteacher) {
        this.courseid = courseid;
        this.cname = cname;
        this.limitnum = limitnum;
        this.surplus = surplus;
        this.teachtime = teachtime;
        this.cteacher = cteacher;
    }
}