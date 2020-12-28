package db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbCon {
    private String dbUrl="jdbc:mysql://129.204.201.99:3306/CourseSys";
    private String dbUserName="CourseSys";
    private String dbPassword="987654321";
    private String jdbcName="com.mysql.cj.jdbc.Driver";
    /**
     * 获取数据库连接
     * @return
     * @throws Exception
     */
    public Connection getCon() throws Exception{
        Class.forName(jdbcName);
        Connection con=DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
        return con;
    }
    public void closeCon(Connection con) throws Exception{
        if(con!=null){
            con.close();
        }
    }
    public static void main(String[] args) {
        DbCon dbCon=new DbCon();
        try {
            dbCon.getCon();
            System.out.println("数据库连接成功!");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}


//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.ArrayList;
//
//public class dbcon {
//    public static void DataBase() {
////        ArrayList<StuInfo> list = new ArrayList<>();
//        try {
//            //                加载mysql驱动
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            System.out.println("驱动加载成功！");
//            //                连接数据库，设置连接对象
//            Connection connection = DriverManager.getConnection("jdbc:mysql://129.204.201.99:3306/CourseSys", "CourseSys", "987654321");
//            System.out.println("数据库连接成功!");
//            //                创建执行环境
//            Statement statement = connection.createStatement();
////            创建ResultSet结果集
//            ResultSet resultSet;
////            if(i==1){
////                resultSet = statement.executeQuery(query);
////                System.out.println("ID" + "\t\t\t" + "姓名" + "\t\t" + "年龄" + "\t" + "性别");
////
////                while (resultSet.next()) {
////                    StuInfo s = new StuInfo();
////                    s.setId(resultSet.getInt(1));
////                    s.setName(resultSet.getString(2));
////                    s.setAge(resultSet.getInt(3));
////                    s.setSex(resultSet.getString(4));
////                    list.add(s);
////                }
////                if (list.size() == 0) {
////                    System.out.println("内容为空！");
////                } else {
////                    for (StuInfo s : list) {
////                        System.out.println(s.getId() + "\t" + s.getName() + "\t" + s.getAge() + "\t" + s.getSex());
////                    }
////                }
////            }else if(i==2){
////                statement.executeUpdate(query);
////            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//        new dbcon().D
//    }
//}
