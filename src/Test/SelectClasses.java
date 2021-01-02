package Test;

import db.DbCon;
import dbOperaction.StudentOper;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class SelectClasses extends JTable {
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

        StudentOper studentOper=new StudentOper();
        studentOper.display();

        jt = new JTable(studentOper.display(), columnNames);
       jt.setEnabled(false);
        //初始化 jsp
        jsp = new JScrollPane(jt);

        //把jsp放入到jFrame
        jFrame.add(jsp);

        jFrame.setSize(800, 550);

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);//窗口在屏幕中间显示
        jFrame.setVisible(true);
    }
}
