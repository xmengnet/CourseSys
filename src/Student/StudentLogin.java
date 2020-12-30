package Student;

import db.*;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentLogin extends JFrame{
    //标签
    private JLabel lable1;
    private JLabel lable2;
    //文本框
    private JTextField text1;
    private JPasswordField text2;
    //按钮
    private JButton bt1;
    private JButton bt2;
    //构造函数
    public StudentLogin()
    {
        this.init();
        this.addComponent();

    }

    public void init()
    {
        this.setSize(500,400);
        this.setVisible(true);
        this.setTitle("学生登录界面");
        this.setLayout(null);
        this.setLocation(700, 300);
    }
    private void addComponent()
    {
        lable1 = new JLabel("账    号");
        lable1.setSize(100,70);
        lable1.setLocation(100,80);
        this.add(lable1);
        lable2 = new JLabel("密    码");
        lable2.setSize(100,70);
        lable2.setLocation(100,130);
        this.add(lable2);

        text1 = new JTextField();
        text1.setSize(150,30);
        text1.setLocation(160,100);
        this.add(text1);
        text2 = new JPasswordField();
        text2.setSize(150,30);
        text2.setLocation(160,150);
        this.add(text2);

        bt1 = new JButton("登录");
        bt1.setSize(70,30);
        bt1.setLocation(140,195);
        this.add(bt1);
        bt1.addActionListener(e->{
            DbCon dbCon=new DbCon();
            String name=text1.getText();
            String passwd=text2.getText();
            String sql = "select sno from studentInfo where sno="+"'"+name+"'";//
            System.out.println(sql);
            //    查询帐号信息
            Statement statement = null;
            try {
                statement = dbCon.getCon().createStatement();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            ResultSet resultSet = null;
            //        执行操作语句
            try {
                resultSet = statement.executeQuery(sql);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                if (!resultSet.next()) {
                    JOptionPane.showMessageDialog(null, "账号错误");
                }
                else {
                    sql = "select spasswd from studentInfo where spasswd = " + "'"+passwd+"'";

                    try {
                        resultSet = statement.executeQuery(sql);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    System.out.println(resultSet);
                    if (!resultSet.next()) {
                        JOptionPane.showMessageDialog(null, "密码错误");

                    } else {
                        JOptionPane.showMessageDialog(null, "欢迎"+name+"登录");
//                        new MainWindow();
                        dispose();
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        bt2 = new JButton("退出");
        bt2.setSize(70,30);
        bt2.setLocation(250,195);
        this.add(bt2);
        bt2.addActionListener(e->{
            dispose();//关闭当前窗口
            //System.exit(0);
        });
        this.setBackground(Color.blue);
        //设置单击关闭按钮时的默认操作
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public static void main(String[] args) {
        new StudentLogin();
    }
}