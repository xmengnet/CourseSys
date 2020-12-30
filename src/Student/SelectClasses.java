package Student;

import java.sql.*;
import java.util.Vector;

import db.*;

import javax.swing.*;

public class SelectClasses {


    //从数据库中取出信息
    //rowData用来存放行数据
    //columnNames存放列名
    static Vector rowData, columnNames;
    static JTable jt = null;
    static JScrollPane jsp = null;

    public static void main(String[] args) throws Exception {
        columnNames = new Vector();
        JFrame jFrame = new JFrame();
//        设置列名
        columnNames.add("课程号");
        columnNames.add("课程名");
        columnNames.add("限报人数");
        columnNames.add("剩余人数");
        columnNames.add("教授教师");
        columnNames.add("课时");
        rowData = new Vector();
//        rowData可以存放多行，开始从数据库里取
//        ArrayList<Courses> list= new ArrayList<>();
        DbCon dbCon = new DbCon();
        Statement statement = dbCon.getCon().createStatement();
        String query;
        query = "select * from courses";
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {

            Vector hang = new Vector();
            hang.add(resultSet.getInt(1));
            hang.add(resultSet.getString(2));
            hang.add(resultSet.getInt(3));
            hang.add(resultSet.getInt(4));
            hang.add(resultSet.getString(5));
            hang.add(resultSet.getInt(6));
            rowData.add(hang);
        }

        //初始化JTable
        jt = new JTable(rowData, columnNames);

        //初始化 jsp
        jsp = new JScrollPane(jt);

        //把jsp放入到jFrame
        jFrame.add(jsp);

        jFrame.setSize(400, 300);

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jFrame.setVisible(true);
    }
}
