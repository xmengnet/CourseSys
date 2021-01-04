package Manager;

import Manager.AdminMenu;
import VerCode.ValidCode;
import db.DbCon;
import dbOperaction.AdminOper;

import javax.swing.*;


public class AChangePwd {
    JDialog jDialog = new JDialog();
    /*当前用户名标签*/
    JLabel nameLabel = new JLabel("用户名");
    /*当前用户名输入框*/
    JLabel nameText = new JLabel();
    /*当前密码标签*/
    JLabel currentPasswd = new JLabel("原密码");
    /*当前密码输入框*/
    JTextField currentPwdText = new JTextField();
    /*修改的密码的标签*/
    JLabel changePasswd = new JLabel("修改的密码");
    /*修改的密码输入框*/
    JTextField changePwdText = new JTextField();
    /*确认框标签*/
    JButton confirm = new JButton("确认修改");
    //    验证码标签、验证码框、刷新验证码
    JLabel verCodeLabel = new JLabel();
    JTextField verCodeValue = new JTextField();
    JButton refreshCode = new JButton("刷新验证码");
//    创建验证码对象
    ValidCode validCode=new ValidCode();

    //获取帐号和密码
    String username, passwd;

    public AChangePwd(String name, String pwd) {
        username = name;
        passwd = pwd;
        System.out.println(passwd);
        init();
    }

    public void init() {
//        设置验证码标签
        verCodeLabel.setText(validCode.init());
        verCodeLabel.setSize(100, 40);
        verCodeLabel.setLocation(150, 200);

//      设置文本框和刷新按钮
        verCodeValue.setSize(80,30);
        verCodeValue.setLocation(220,205);
        refreshCode.setSize(110,40);
        refreshCode.setLocation(310,200);
        /*设置用户名标签和输入框*/
        nameLabel.setSize(100, 30);
        nameLabel.setLocation(140, 60);
        nameText.setSize(160, 30);
        nameText.setLocation(220, 60);
        nameText.setText(username);
        /*设置当前密码输入框、标签的大小和位置*/
        currentPasswd.setSize(100, 30);
        currentPasswd.setLocation(140, 100);
        currentPwdText.setSize(160, 30);
        currentPwdText.setLocation(220, 100);

        /*设置修改密码的标签和输入框的大小和位置*/
        changePasswd.setSize(100, 30);
        changePasswd.setLocation(140, 140);
        changePwdText.setSize(160, 30);
        changePwdText.setLocation(220, 140);

        /*设置确认框的位置和大小*/
        confirm.setSize(90, 40);
        confirm.setLocation(245, 250);

        /*把组件加入对话框中*/
        jDialog.add(nameLabel);
        jDialog.add(nameText);
        jDialog.add(currentPasswd);
        jDialog.add(currentPwdText);
        jDialog.add(changePasswd);
        jDialog.add(changePwdText);
        jDialog.add(verCodeLabel);
        jDialog.add(verCodeValue);
        jDialog.add(refreshCode);
        jDialog.add(confirm);
//刷新按钮事件
        refreshCode.addActionListener(e->{
            verCodeLabel.setText(null);
            verCodeLabel.setText(validCode.init());
        });


        /*设置确认按钮的监听*/
        confirm.addActionListener(e -> {
            if(validCode.receive(verCodeValue.getText())){
                if (passwd.equals(currentPwdText.getText())){
                    /*修改密码部分*/
                    AdminOper adminOper=new AdminOper();
                    try {
                        int status= adminOper.Adpasswd(username,changePwdText.getText());
//                        System.out.println(status);
                        if(status>0){
                            JOptionPane.showMessageDialog(null,"密码修改成功！");
//                            JDialog.
                        }else {
                            JOptionPane.showMessageDialog(null,"密码修改失败！");
                        }

                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }

                }else {
                    JOptionPane.showMessageDialog(null,"当前密码输入错误！");
                    System.out.println(currentPwdText.getText());
                    verCodeLabel.setText(null);
                    verCodeLabel.setText(validCode.init());
                }
            }else {
                JOptionPane.showMessageDialog(null,"验证码输入错误！");
                verCodeLabel.setText(null);
                verCodeLabel.setText(validCode.init());
            }

        });

        /*设置对话框的属性*/
        jDialog.setLayout(null);
        jDialog.setSize(550, 400);
        jDialog.setLocationRelativeTo(null);
        jDialog.setVisible(true);

    }

//    public static void main(String[] args) {
//        AChangePwd a = new AChangePwd("admin", "123456");
////        a.init();
//    }
}
