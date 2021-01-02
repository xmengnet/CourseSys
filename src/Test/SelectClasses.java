package Test;

import ArrayListData.Courses;
import db.DbCon;
import dbOperaction.StudentOper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class SelectClasses extends JTable {
    /*columnNames存放列名*/
    static Vector  columnNames;
    static JTable jt = null;
    static JScrollPane jsp = null;
    public void init() throws Exception {
        columnNames = new Vector();
        JFrame jFrame = new JFrame("选择课程");
        /*设置列名*/
        columnNames.add("课程号");
        columnNames.add("课程名");
        columnNames.add("限报人数");
        columnNames.add("剩余人数");
        columnNames.add("教授教师");
        columnNames.add("课时");

        /*创建学生操作类对像*/
        StudentOper studentOper=new StudentOper();
        /*调用学生类里的方法*/
        studentOper.display();

        /*加入内容到表格中*/
        jt = new JTable(studentOper.display(), columnNames);
        jt.setEnabled(false);

        //初始化 jsp滚动条
        jsp = new JScrollPane(jt);
        /*创建一个提示标签*/
        JLabel titleLabel=new JLabel("请从下列课程中选择您感兴趣的课程");
        Box horizontalBox1 = Box.createHorizontalBox();
        horizontalBox1.add(titleLabel);
        /*课程号标签*/
        JLabel cnamelabel=new JLabel("请输入课程号:");
        JTextField cnameText=new JTextField(20);
        /*确认按钮*/
        JButton confirm=new JButton("确认");

        confirm.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = cnameText.getText();
                System.out.println(text);
               int courseid=Integer.parseInt(text);
                System.out.println(courseid);
                Courses courses=new Courses();

            }
        });

        /*创建一个水平盒子和垂直盒子*/
        Box horizontalBox = Box.createHorizontalBox();
        Box verticalBox = Box.createVerticalBox();
        JPanel jPanel=new JPanel();
        jPanel.add(horizontalBox);

        /*把标签文本框加入到水平盒子中*/
        horizontalBox.add(cnamelabel);
        horizontalBox.add(cnameText);
        horizontalBox.add(confirm);

        /*把提示文本表格、水平盒子加入到垂直盒子中*/
        verticalBox.add(Box.createVerticalStrut(20));
        verticalBox.add(horizontalBox1);
        verticalBox.add(Box.createVerticalStrut(10));
        verticalBox.add(jt);
        verticalBox.add(Box.createVerticalStrut(20));
//        verticalBox.add(jsp);

        verticalBox.add(jPanel);
        /*把水平盒子加入到jFrame中*/
        jFrame.add(verticalBox);

        /*设置jFrame的大小、位置可见性*/
        jFrame.setSize(800, 550);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*窗口在屏幕中间显示*/
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }

    public static void main(String[] args)  {
          SelectClasses s=new SelectClasses();
        try {
            s.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
