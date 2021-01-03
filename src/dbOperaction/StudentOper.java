package dbOperaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import db.*;
import ArrayListData.*;

public class StudentOper {
    /*查看所有可选的课程*/
    public Vector display() throws Exception {
        ArrayList<Courses> list=new ArrayList<Courses>();
        // 首先拿到数据库的连接
        DbCon dbCon=new DbCon();
        Statement statement=dbCon.getCon().createStatement();
        String query;
        query="select * from courses";
        /*执行查询语句*/
        ResultSet resultSet=statement.executeQuery(query);

//        System.out.println("课程号   "+"课程名  "+"限报人数   "+"剩余人数   "+"教授教师   "+"课时  ");
        Vector rowData = new Vector();
        while(resultSet.next()){
            Vector hang = new Vector();

            hang.add(resultSet.getInt(1));
            hang.add(resultSet.getString(2));
            hang.add(resultSet.getInt(3));
            hang.add(resultSet.getInt(4));
            hang.add(resultSet.getString(5));
            hang.add(resultSet.getInt(6));
            rowData.add(hang);
        }
        return  rowData;
    }

    /*选择课程*/
    public void addClasses(CourseResult courseResult) throws Exception {
        // 首先拿到数据库的连接
        DbCon dbCon = new DbCon();
        Connection con = dbCon.getCon();
        String query = "insert into courseresult"
                // 学号sno、姓名sname、课程号courseid、课程名cname
                + "(sno, sname,courseid, cname)"
                + "values(" + "?,?,?,?)";
         /* 参数用?表示，相当于占位符，然后在对参数进行赋值。当真正执行时，
          这些参数会加载在SQL语句中，把SQL语句拼接完整才去执行。这样就会减少对数据库的操作*/
        /*  prepareStatement这个方法会将SQL语句加载到驱动程序con集成程序中，
         但是并不直接执行,而是当它调用execute()方法的时候才真正执行；*/
        PreparedStatement psmt = con.prepareStatement(query);
        // 先对应SQL语句，给SQL语句传递参数
        psmt.setInt(1, courseResult.getSno());
        psmt.setString(2, courseResult.getSname());
        psmt.setInt(3, courseResult.getCourseid());
        psmt.setString(4, courseResult.getCname());
        //执行SQL语句
        psmt.execute();
        System.out.println(query + "\t选课程成功");
    }

    /*根据学号和课程号删除已选课程信息信息*/
    public int  delClasses(int sno,String courseid) throws Exception{
        DbCon dbCon = new DbCon();
        Connection con = dbCon.getCon();
        String query;
        query =
                "DELETE FROM courseresult  "+"where sno=? AND courseid=?";
        /*执行删除课程语句语句*/
        PreparedStatement pstm = con.prepareStatement(query);
        pstm.setInt(1,sno);
        pstm.setString(2,courseid);
        int result = pstm.executeUpdate();
        if(result>0){
            System.out.println("删除成功");
            return  1;
        }
        else{
            System.out.println("删除失败");
            return  0;
        }
    }


    /*查看已选课程信息*/
    public CourseResult changeClasses(int sno) throws Exception {
        ArrayList<CourseResult> list = new ArrayList<>();
        // 首先拿到数据库的连接
        DbCon dbCon = new DbCon();
        Statement statement = dbCon.getCon().createStatement();
        String query = null;
        /*测试时用的*/
//        Scanner sc = new Scanner(System.in);
//        sno = sc.nextInt();

        query = "select * from courseresult where sno ='" + sno + "' ";
        // 执行SQL语句
        ResultSet resultSet = statement.executeQuery(query);
        System.out.println("学号   " + "姓名   " + "课程号   " + "课程名  ");
        while (resultSet.next()) {
            CourseResult courseResult1 = new CourseResult();
            courseResult1.setSno(resultSet.getInt(1));
            courseResult1.setSname(resultSet.getString(2));
            courseResult1.setCourseid(resultSet.getInt(3));
            courseResult1.setCname(resultSet.getString(4));
            list.add(courseResult1);
        }
        for (CourseResult i : list) {
            System.out.println(i.getSno() + " " + i.getSname() + i.getCourseid() + " " + i.getCname());
        }
        return null;
    }

    /*根据课程号查询课程名*/
    public  String GetCnameBycourseid(int courseid) throws Exception{
        String cname=null;
        DbCon dbCon = new DbCon();
        Connection con = dbCon.getCon();
        Courses courses=new Courses();
        String query;
         query =
                "select cname from courses where courseid = '"+courseid+"'";
        /*执行查询语句*/
        Statement statement=dbCon.getCon().createStatement();
        ResultSet resultSet=statement.executeQuery(query);
        while(resultSet.next()){
            cname=resultSet.getString(1);
            System.out.println(cname);
        }
        return cname;
    }
    /*根据学号查询姓名*/
    public String GetSnameBySno(int sno) throws Exception{
        String sname=null;
        DbCon dbCon = new DbCon();
        Connection con = dbCon.getCon();
        StudentInfo studentInfo=new StudentInfo();
        String query;
        query =
                "select sname from studentInfo where sno = '"+sno+"'";
        /*执行查询语句*/
        Statement statement=dbCon.getCon().createStatement();
        ResultSet resultSet=statement.executeQuery(query);
        while(resultSet.next()){
            sname=resultSet.getString(1);
            System.out.println(sname);
        }
        return sname;
    }
    /*学生退选之后课程剩余可选人数加一*/
    public void add(String courseid) throws Exception{
        int surplus = 0;
        DbCon dbCon = new DbCon();
        Connection con = dbCon.getCon();
        Courses courses=new Courses();
        String query;
        query =
                "update courses set surplus=surplus+1 where courseid="+courseid;
        /*执行加一语句*/
        Statement statement=dbCon.getCon().createStatement();
        statement.execute(query);
    }


      /*学生选课之后剩余人数减一*/
      public void dec(String courseid) throws Exception{
        int surplus = 0;
        DbCon dbCon = new DbCon();
        Connection con = dbCon.getCon();
        Courses courses=new Courses();
        String query;
        query =
                "update courses set surplus=surplus-1 where courseid="+courseid;
        /*执行减一语句*/
        Statement statement=dbCon.getCon().createStatement();
        statement.execute(query);
    }
    /*修改学生密码*/
    /*studentInfo*/
    public int changeSpasswd(int sno,String spasswd) throws Exception{
        DbCon dbCon = new DbCon();
        Connection con = dbCon.getCon();
        String query;
        query =
                "update studentInfo set"+"where sno=? AND spasswd=?";
        /*执行修改密码语句*/
        Statement statement=dbCon.getCon().createStatement();
        PreparedStatement pstm = con.prepareStatement(query);
        pstm.setInt(1,sno);
        pstm.setString(2,spasswd);
        statement.execute(query);
        int result = pstm.executeUpdate();
        if(result>0){
            System.out.println("删除成功");
            return  1;
        }
        else{
            System.out.println("删除失败");
            return  0;
        }
    }






    /*以下紧测试时使用*/
    public static void main(String[] args) throws Exception {
        StudentOper in=new StudentOper();
//        CourseResult co=new CourseResult(1810361232,"董新生",10001,"戴冬");
//        new GetCna
        //in.addClasses(co);
//        in.delClasses(co);
        //System.out.println(in.changeClasses(1810361232));
       // System.out.println(in.GetCnameBycourseid(10002));
        //in.dec("10002");
        // in.delClasses(1810361242,"10002");
    }
}
