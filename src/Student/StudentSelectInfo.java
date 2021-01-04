package Student;

import dbOperaction.StudentOper;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class StudentSelectInfo extends JDialog{
    static String currentsno;
    static Vector columnNames;
    static JTable jt = null;
    static JScrollPane jsp = null;
    static String sno;

    public StudentSelectInfo(String snum) {
        sno = snum;
        init();
    }
    public void init() {
        columnNames = new Vector();
        columnNames.add("学号");
        columnNames.add("姓名");
        columnNames.add("课程ID");
        columnNames.add("课程名");
        StudentOper studentOper = new StudentOper();
        try {
            jt = new JTable(studentOper.changeClasses(sno), columnNames);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //        最上面的提示框
        JLabel titleLabel = new JLabel("当前所选课程如下所示");
        titleLabel.setLocation(200, 10);
        titleLabel.setSize(200, 40);
        titleLabel.setFont((new Font("仿宋", Font.BOLD, 20)));
        this.add(titleLabel);
        jt.setEnabled(false);
        currentsno = sno;
        jt.setSize(600, 200);
        jt.setLocation(20, 50);
        //初始化 jsp滚动条
        jsp = new JScrollPane(jt);
        jsp.setSize(600, 200);
        jsp.setLocation(20, 70);
        this.add(jsp);

        this.setTitle("学生选课信息");
        this.setSize(650, 500);
        this.setLocation(600, 300);
        this.setLayout(null);



        this.setVisible(true);
    }

//    public static void main(String[] args) {
//        StudentSelectInfo studentSelectInfo = new StudentSelectInfo("1810361232");
//    }
}
