package Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentMenu extends JFrame implements ActionListener {

    JButton bt1;
    JButton bt2;
    JButton bt3;
    JButton bt4;
    JPanel panel;
    JLabel label;
    public static String userid;

    StudentMenu(String id) {
        userid = id;
        this.setSize(900, 700);
        this.setTitle("学生课程管理系统-学生端");
        this.setLayout(null);
        this.setLocation(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        bt1 = new JButton("选课信息");
        bt1.setSize(150, 50);
        bt1.setLocation(150, 400);
        bt1.addActionListener(this);
        bt1.setActionCommand("选课信息");

        bt2 = new JButton("退选课程");
        bt2.setSize(150, 50);
        bt2.setLocation(150, 500);
        bt2.addActionListener(this);
        bt2.setActionCommand("退选课程");

        bt3 = new JButton("选择课程");
        bt3.setSize(150, 50);
        bt3.setLocation(550, 400);
        bt3.addActionListener(this);
        bt3.setActionCommand("选择课程");

        bt4 = new JButton("个人信息");
        bt4.setSize(150, 50);
        bt4.setLocation(550, 500);
        bt4.addActionListener(this);
        bt4.setActionCommand("个人信息");

        this.add(bt1);
        this.add(bt2);
        this.add(bt3);
        this.add(bt4);

        panel = new JPanel();
        panel.setSize(650, 350);
        panel.setLocation(100, 20);
        panel.setLayout(null);
        panel.setBackground(Color.LIGHT_GRAY);


        label = new JLabel();

        label.setLocation(165, 60);
        label.setSize(700, 200);
        panel.add(label);
        label.setText("欢迎登陆课程管理系统");
        label.setFont((new Font("仿宋", Font.BOLD, 30)));
        this.add(panel);
        panel.setVisible(true);
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        JButton bt = (JButton) e.getSource();

        if (bt.getText().equals("选课信息")) {
            try {

            } catch (Exception exception) {
                exception.printStackTrace();
            }
            panel.setLocation(100, 20);
        } else if (bt.getText().equals("选择课程")) {

            try {
                new SelectClasses(userid);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            panel.setLocation(100, 20);

        } else if (bt.getText().equals("退选课程")) {

            new StudentDelete(userid);
            panel.setLocation(100, 20);

        } else if (bt.getText().equals("个人信息")) {
            try {
                new StudentInfo(userid);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            panel.setLocation(100, 20);


        }
    }


    public static void main(String[] args) {
        new StudentMenu("admin");
    }
}
