package dbOperaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import db.*;
import ArrayListData.*;

public class StudentOper {
    public void addClasses(CourseResult courseResult) throws Exception{
        // 首先拿到数据库的连接
        DbCon dbCon=new DbCon();
        Connection con = dbCon.getCon();
        String query="insert into courseresult"
                // 学号sno、姓名sname、课程号courseid、课程名cname
                + "(sno, sname,courseid, cname)"
                + "values(" + "?,?,?,?)";
         /* 参数用?表示，相当于占位符，然后在对参数进行赋值。当真正执行时，
          这些参数会加载在SQL语句中，把SQL语句拼接完整才去执行。这样就会减少对数据库的操作*/
//        String query=null;
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
        System.out.println(query+"\t选课程成功");
    }

    /*删除已选课程信息信息*/
    public void delClasses(CourseResult courseResult) throws Exception {
        // 首先拿到数据库的连接
        DbCon dbCon=new DbCon();
        Connection con = dbCon.getCon();
        String query=
                "DELETE FROM courseresult "+
                // 参数用?表示，相当于占位符
                "WHERE  sno=? AND courseid = ?";
        // 预编译sql语句
        PreparedStatement psmt = con.prepareStatement(query);
        // 先对应SQL语句，给SQL语句传递参数
        psmt.setInt(1,courseResult.getSno());
        psmt.setInt(2,courseResult.getCourseid());
        // 执行SQL语句
        System.out.println(query+"\t删除成功");
        psmt.execute();

    }

    /*查看已选课程信息*/
    public void changeClasses(CourseResult courseResult) throws Exception{
        // 首先拿到数据库的连接
        DbCon dbCon=new DbCon();
        Connection con = dbCon.getCon();
        String query="select * from courseresult where sno = ?";
                // 参数用?表示，相当于占位符
        // 预编译sql语句
        PreparedStatement psmt = con.prepareStatement(query);
        // 先对应SQL语句，给SQL语句传递参数
        psmt.setInt(1, courseResult.getSno());
        // 执行SQL语句
        psmt.execute();
       // System.out.println();
    }

    public static void main(String[] args) throws Exception {
        StudentOper in=new StudentOper();
        CourseResult co=new CourseResult(1810361232,"董新生",10001,"戴冬");
        //in.addClasses(co);
//        in.delClasses(co);
       in.changeClasses(co);
    }

}
