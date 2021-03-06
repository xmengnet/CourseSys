package Student;

import db.*;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.sql.*;
import java.util.Enumeration;

public class StudentLogin extends JFrame {
    //标签
    private JLabel lable1;
    private JLabel lable2;
    private JLabel lable3;
    private JLabel lable4;
    //文本框
    private JTextField text1;
    private JPasswordField text2;
    //按钮
    private JButton bt1;
    private JButton bt2;

    //构造函数
    public StudentLogin() {
        this.init();
        this.addComponent();
    }
//统一设置字体及其大小
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

    public void init() {
        //        设置窗口不可变，并设置居中
        this.setSize(500, 400);
        this.setVisible(true);
        this.setTitle("学生登录界面");
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        InitGlobalFont(new Font("alias", Font.PLAIN, 14));  //统一设置字体

    }

    private void addComponent() {
        int width = 25, height = 25;//两个图片的实际大小
        lable1 = new JLabel();
        ImageIcon icon1 = new ImageIcon("src/Images/账户.jpg");//导入图片路径
        lable1.setIcon(icon1);
        icon1.setImage(icon1.getImage().getScaledInstance(width, height,Image.SCALE_DEFAULT));//创建此图像的缩放版本
        lable1.setSize(25, 25);//图片容器大小
        lable1.setLocation(60, 102);
        this.add(lable1);

        lable2 = new JLabel();
        ImageIcon icon2 = new ImageIcon("src/Images/密码.jpg");
        lable2.setIcon(icon2);
        icon2.setImage(icon2.getImage().getScaledInstance(width, height,Image.SCALE_DEFAULT));//创建此图像的缩放版本
        lable2.setSize(25, 25);//图片容器大小
        lable2.setLocation(60, 151);
        this.add(lable2);

        lable3 = new JLabel("账    户：");
        lable3.setSize(60, 60);
        lable3.setLocation(100, 83);
        this.add(lable3);

        lable4 = new JLabel("密    码：");
        lable4.setSize(60, 60);
        lable4.setLocation(100, 133);
        this.add(lable4);

        text1 = new JTextField();
        text1.setSize(150, 30);
        text1.setLocation(160, 100);
        this.add(text1);
        text2 = new JPasswordField();
        text2.setSize(150, 30);
        text2.setLocation(160, 150);
        this.add(text2);

        this.setLayout(new FlowLayout());// 添加背景色
        this.getContentPane().setBackground(Color.white);// 核心，设置背景颜色，用这种方法。getContentPane()

        bt1 = new JButton("登录");
        bt1.setSize(70, 30);
        bt1.setLocation(140, 195);
        this.add(bt1);
        bt1.addActionListener(e -> {
            DbCon dbCon = new DbCon();
            String name = text1.getText();
            String passwd = text2.getText();
            String sql = "select sno from studentInfo where sno=" + "'" + name + "'";//
            System.out.println(sql);
            //    查询帐号信息
            Statement statement;
            try {
                statement = dbCon.getCon().createStatement();

                ResultSet resultSet;
                //        执行操作语句
                resultSet = statement.executeQuery(sql);

                if (!resultSet.next()) {
                    JOptionPane.showMessageDialog(null, "账号或密码错误");
                } else {
                    sql = "select spasswd from studentInfo where spasswd = " + "'" + passwd + "'";


                    resultSet = statement.executeQuery(sql);

                    System.out.println(resultSet);
                    if (!resultSet.next()) {
                        JOptionPane.showMessageDialog(null, "账号或密码错误");
                    } else {
                        JOptionPane.showMessageDialog(null, "欢迎" + name + "登录");
                        new StudentMenu(name);
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
