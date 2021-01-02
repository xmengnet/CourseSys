package Manager;

import db.*;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.sql.*;
import java.util.Enumeration;

public class adminLogin extends JFrame {
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
    public adminLogin() {
//        设置窗口不可变，并设置居中
        this.init();
        this.addComponent();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    public void init() {
        this.setSize(500, 400);
        this.setVisible(true);
        this.setTitle("管理员登录界面");
        this.setLayout(null);
        this.setLocation(700, 300);
        InitGlobalFont(new Font("alias", Font.PLAIN, 12));  //统一设置字体
    }

    private static void InitGlobalFont(Font font) {
        FontUIResource fontRes = new FontUIResource(font);
        for (Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements();) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, fontRes);
            }
        }
    }

    private void addComponent() {
        lable1 = new JLabel("用户名");
        lable1.setSize(100, 70);
        lable1.setLocation(100, 80);
        this.add(lable1);
        lable2 = new JLabel("密    码");
        lable2.setSize(100, 70);
        lable2.setLocation(100, 130);
        this.add(lable2);

        text1 = new JTextField();
        text1.setSize(150, 30);
        text1.setLocation(160, 100);
        this.add(text1);
        text2 = new JPasswordField();
        text2.setSize(150, 30);
        text2.setLocation(160, 150);
        this.add(text2);

        bt1 = new JButton("登录");
        bt1.setSize(70, 30);
        bt1.setLocation(140, 195);
        this.add(bt1);
        bt1.addActionListener(e -> {
            DbCon dbCon = new DbCon();
            String name = text1.getText();
            String passwd = text2.getText();
            String query = "select admin from manager where admin =" + "'" + name + "'";
            System.out.println(query);
            //    查询帐号信息
            Statement statement;
            try {
                statement = dbCon.getCon().createStatement();

                ResultSet resultSet;
                //        执行操作语句

                resultSet = statement.executeQuery(query);

                if (!resultSet.next()) {
                    JOptionPane.showMessageDialog(null, "账号或密码错误");
                } else {
                    query = "select passwd from manager where passwd = " + "'" + passwd + "'";

                    resultSet = statement.executeQuery(query);

                    System.out.println("执行代码:"+resultSet);
                    if (!resultSet.next()) {
                        JOptionPane.showMessageDialog(null, "账号或密码错误");
                    } else {
//                        打开管理员主界面,并传递登陆用户名
                        new AdminMenu(name);
                        dispose();
                    }
                }
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
        });
        bt2 = new JButton("退出");
        bt2.setSize(70, 30);
        bt2.setLocation(250, 195);
        this.add(bt2);
        bt2.addActionListener(e -> {
            dispose();//关闭当前窗口
        });
        this.setBackground(Color.blue);
        //设置单击关闭按钮时的默认操作
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new adminLogin();
    }
}
