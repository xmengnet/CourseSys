package Manager;

import ArrayListData.Courses;
import dbOperaction.AdminOper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminAdd {
    JDialog jDialog = new JDialog();

    /*课程号标签*/
    JLabel cidLabel = new JLabel("课程号");
    JTextField cidText = new JTextField();
    /*课程名标签*/
    JLabel cnameLabel = new JLabel("课程名");
    JTextField cnameText = new JTextField();
    /*限报人数标签*/
    JLabel limLabel = new JLabel("限报人数");
    JTextField limText = new JTextField();
    /*剩余可选人数标签*/
    JLabel surLabel = new JLabel("剩余可选人数");
    JTextField surText = new JTextField();
    /*授课教师标签*/
    JLabel teacherLabel = new JLabel("授课教师");
    JTextField teacherText = new JTextField();
    /*课时总数标签*/
    JLabel timeLabel = new JLabel("课时总数");
    JTextField timeText = new JTextField();

    /*确认添加按钮*/
    JButton confirm = new JButton("添加课程");

    public AdminAdd() {
        init();
    }

    public void init() {
        /*设置课程号位置*/
        cidLabel.setSize(100, 30);
        cidLabel.setLocation(140, 60);
        cidText.setSize(160, 30);
        cidText.setLocation(220, 60);

        /*设置课程名位置*/
        cnameLabel.setSize(100, 30);
        cnameLabel.setLocation(140, 100);
        cnameText.setSize(160, 30);
        cnameText.setLocation(220, 100);

        /*设置限报人数位置*/
        limLabel.setSize(100, 30);
        limLabel.setLocation(140, 140);
        limText.setSize(160, 30);
        limText.setLocation(220, 140);

        /*设置剩余可报人数位置*/
        surLabel.setSize(100, 30);
        surLabel.setLocation(140, 180);
        surText.setSize(160, 30);
        surText.setLocation(220, 180);

        /*设置授课教师位置*/
        teacherLabel.setSize(100, 30);
        teacherLabel.setLocation(140, 220);
        teacherText.setSize(160, 30);
        teacherText.setLocation(220, 220);

        /*设置课时位置*/
        timeLabel.setSize(100, 30);
        timeLabel.setLocation(140, 260);
        timeText.setSize(160, 30);
        timeText.setLocation(220, 260);

        /*设置确认按钮的位置和大小*/
        confirm.setSize(90, 40);
        confirm.setLocation(245, 320);

        /*组件添加到jDialograme中*/
        jDialog.setTitle("添加课程");
        jDialog.add(cidLabel);
        jDialog.add(cidText);
        jDialog.add(cnameLabel);
        jDialog.add(cnameText);
        jDialog.add(limLabel);
        jDialog.add(limText);
        jDialog.add(surLabel);
        jDialog.add(surText);
        jDialog.add(teacherLabel);
        jDialog.add(teacherText);
        jDialog.add(timeLabel);
        jDialog.add(timeText);
        jDialog.add(confirm);

        jDialog.setSize(600, 450);
        jDialog.setLayout(null);
        jDialog.setLocationRelativeTo(null);
        jDialog.setVisible(true);

        /*给按钮添加监听*/
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*获取课程号*/
                int cid = Integer.parseInt(cidText.getText());
                /*获取课程名*/
                String cname = cnameText.getText();
                /*获取限报人数*/
                int limitnum = Integer.parseInt(limText.getText());
                /*获取剩余可报人数*/
                int surplus = Integer.parseInt(surText.getText());
                /*获取教师名*/
                String cteacher = teacherText.getText();
                /*获取教授课时*/
                int ctime = Integer.parseInt(timeText.getText());
                /*创建一个课程对象，用带参构造初始化*/
                Courses co = new Courses(cid, cname, limitnum, surplus, ctime, cteacher);
                /*创建一个管理员操作类*/
                AdminOper adminOper = new AdminOper();
                try {
                    /*传入课程对象，添加课程*/
                    Boolean result = adminOper.AdminAdd(co);
                    if (result) {
                        JOptionPane.showMessageDialog(null, "添加课程成功");
                    } else {
                        JOptionPane.showMessageDialog(null, "添加课程失败");
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
    }

}
