package dbOperaction;

import ArrayListData.Courses;
import db.DbCon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

public class AdminOper {
    /*管理员查看所有的课程*/
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

    /*管理员根据课程号删除课程*/
    public Boolean Admindel(String name,String courseid) throws Exception{
        DbCon dbCon = new DbCon();
        Connection con = dbCon.getCon();
        String query;
        query =
                "DELETE FROM courses where courseid="+courseid;
        /*执行删除课程语句语句*/
        Statement pstm = con.createStatement();
        int result = pstm.executeUpdate(query);
        if(result>0){
            System.out.println("删除成功");
            return  true;
        }
        else{
            System.out.println("删除失败");
            return  false;
        }
    }

    /*管理员添加课程*/
    public int AdminAdd(Courses courses) throws Exception {
        // 首先拿到数据库的连接
        DbCon dbCon = new DbCon();
        Connection con = dbCon.getCon();
        String query = "insert into courses"
                // 学号sno、姓名sname、课程号courseid、课程名cname
                + "(courseid, cname,limitnum, surplus,cteacher,teachtime)"
                + "values(" + "?,?,?,?,?,?)";
         /* 参数用?表示，相当于占位符，然后在对参数进行赋值。当真正执行时，
          这些参数会加载在SQL语句中，把SQL语句拼接完整才去执行。这样就会减少对数据库的操作*/
        /*  prepareStatement这个方法会将SQL语句加载到驱动程序con集成程序中，
         但是并不直接执行,而是当它调用execute()方法的时候才真正执行；*/
        PreparedStatement psmt = con.prepareStatement(query);
        // 先对应SQL语句，给SQL语句传递参数
        psmt.setInt(1,courses.getCourseid() );
        psmt.setString(2,courses.getCname());
        psmt.setInt(3,courses.getLimitnum());
        psmt.setInt(4,courses.getSurplus());
        psmt.setString(5,courses.getCteacher());
        psmt.setInt(6,courses.getTeachtime());
        //执行SQL语句
        psmt.execute();
        System.out.println(query + "\t添加课程成功");
        if(psmt!=null)
        {
            System.out.println("添加课程成功");
            return 1;
        }
        else{
            System.out.println("添加课程失败");
            return 0;
        }
    }
    /*修改管理员密码*/
    public int Adpasswd(String admin,String passwd) throws Exception{
        DbCon dbCon = new DbCon();
        Connection con = dbCon.getCon();
        String query;
        query =
                "UPDATE manager set passwd= ? WHERE admin= "+admin;
        /*执行修改密码语句*/
        PreparedStatement pstm = con.prepareStatement(query);
        pstm.setString(1,passwd);
        int result = pstm.executeUpdate();
        if(result>0){
            System.out.println("密码修改成功");
            return  1;
        }
        else{
            System.out.println("密码修改失败");
            return  0;
        }
    }
    /*以下紧测试时使用*/
    public static void main(String[] args) throws Exception {
        AdminOper in=new AdminOper();
        //in.Adpasswd("admin","123456");
        //in.Admindel("10005");
        // Courses co=new Courses(10005,"马原",4,4,32,"传承");
        //in.AdminAdd(co);
    }
}
