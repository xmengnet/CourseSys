package dbOperaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import db.*;
import ArrayListData.*;

public class StudentOper {
    /*查看所有可选的课程*/
    public Courses display() throws Exception {
        ArrayList<Courses> list=new ArrayList<Courses>();
        // 首先拿到数据库的连接
        DbCon dbCon=new DbCon();
        Statement statement=dbCon.getCon().createStatement();
        String query;
        query="select * from courses";
        /*执行查询语句*/
        ResultSet resultSet=statement.executeQuery(query);

        System.out.println("课程号   "+"课程名  "+"限报人数   "+"剩余人数   "+"教授教师   "+"课时  ");
        while(resultSet.next()){
            Courses courses=new Courses();
            courses.setCourseid(resultSet.getInt(1));
            courses.setCname(resultSet.getString(2));
            courses.setLimitnum(resultSet.getInt(3));
            courses.setSurplus(resultSet.getInt(4));
            courses.setCteacher(resultSet.getString(5));
            courses.setTeachtime(resultSet.getInt(6));
            list.add(courses);
        }

        for(Courses i:list)
        {
            System.out.println(i.getCourseid()+" "+i.getCname()+" "+i.getLimitnum()+" "+
                    i.getSurplus()+" "+i.getCteacher()+" "+i.getTeachtime());
        }
        return null;
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
        System.out.println(query + "\t选课程成功");
    }

    /*删除已选课程信息信息*/
    public void delClasses(CourseResult courseResult) throws Exception {
        // 首先拿到数据库的连接
        DbCon dbCon = new DbCon();
        Connection con = dbCon.getCon();
        String query =
                "DELETE FROM courseresult " +
                        // 参数用?表示，相当于占位符
                        "WHERE  sno=? AND courseid = ?";
        // 预编译sql语句
        PreparedStatement psmt = con.prepareStatement(query);
        // 先对应SQL语句，给SQL语句传递参数
        psmt.setInt(1, courseResult.getSno());
        psmt.setInt(2, courseResult.getCourseid());
        // 执行SQL语句
        System.out.println(query + "\t删除成功");
        psmt.execute();

    }

    /*查看已选课程信息*/
    public CourseResult changeClasses(int sno) throws Exception {
        ArrayList<CourseResult> list = new ArrayList<CourseResult>();
        // 首先拿到数据库的连接
        DbCon dbCon = new DbCon();
        Statement statement = dbCon.getCon().createStatement();
        String query = null;
        Scanner sc = new Scanner(System.in);
        sno = sc.nextInt();
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


    public static void main(String[] args) throws Exception {
        StudentOper in=new StudentOper();
        //CourseResult co=new CourseResult(1810361232,"董新生",10001,"戴冬");
        //in.addClasses(co);
//        in.delClasses(co);
        //System.out.println(in.changeClasses(1810361232));
        System.out.println(in.display());
    }

}
