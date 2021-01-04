package Test;

import Manager.AdminMenu;
import db.DbCon;
import dbOperaction.AdminOper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;

public class AchangePwd {
   JDialog jDialog=new JDialog();

   /*当前用户名标签*/
    JLabel nameLabel=new JLabel("用户名");
    /*当前用户名输入框*/
    JTextField nameText=new JTextField();
    /*当前密码标签*/
   JLabel Nowpasswd=new JLabel("原密码");
   /*当前密码输入框*/
   JTextField NowPwdText=new JTextField();

   /*修改的密码的标签*/
   JLabel changePasswd=new JLabel("修改的密码");
   /*修改的密码输入框*/
   JTextField changePwdText=new JTextField();

   /*确认框标签*/
   JButton confirm=new JButton("确认修改");

   public void init(){

       /*设置用户名标签和输入框*/
       nameLabel.setSize(100,30);
       nameLabel.setLocation(140,60);
       nameText.setSize(160,30);
       nameText.setLocation(220,60);

       /*设置当前密码输入框、标签的大小和位置*/
       Nowpasswd.setSize(100,30);
       Nowpasswd.setLocation(140,100);
       NowPwdText.setSize(160,30);
       NowPwdText.setLocation(220,100);

       /*设置修改密码的标签和输入框的大小和位置*/
       changePasswd.setSize(100,30);
       changePasswd.setLocation(140,140);
       changePwdText.setSize(160,30);
       changePwdText.setLocation(220,140);

       /*设置确认框的位置和大小*/
       confirm.setSize(90,40);
       confirm.setLocation(245,200);

       /*把组件加入对话框中*/
       jDialog.add(nameLabel);
       jDialog.add(nameText);
       jDialog.add(Nowpasswd);
       jDialog.add(NowPwdText);
       jDialog.add(changePasswd);
       jDialog.add(changePwdText);
       jDialog.add(confirm);

       /*设置确认按钮的监听*/
        confirm.addActionListener(e -> {
            DbCon dbCon = new DbCon();
            String name = nameText.getText();
            String nowpasswd = Nowpasswd.getText();
            String changePwd = changePwdText.getText();
            String query = "select admin from manager where admin =" + "'" + name + "'";
            System.out.println(query);
             /* 查询帐号信息*/
            Statement statement;
            try {
                statement = dbCon.getCon().createStatement();

                ResultSet resultSet;
                    /*执行操作语句*/
                resultSet = statement.executeQuery(query);

                if (!resultSet.next()) {
                    JOptionPane.showMessageDialog(null, "账号输入错误");
                } else {
                    query = "select passwd from manager where passwd = " + "'" + nowpasswd + "'";

                    resultSet = statement.executeQuery(query);

                    System.out.println("执行代码:"+resultSet);
                    if (!resultSet.next()) {
                        JOptionPane.showMessageDialog(null, "原密码输入错误");
                    } else {


                        /*修改密码部分*/
//               AdminOper adminOper=new AdminOper();
//               try {
//                   int result = adminOper.Adpasswd("admin", changePwd);
//                   if(result==1){
//                       JOptionPane.showMessageDialog(null,"密码修改成功");
//                   }
//                   else{
//                       JOptionPane.showMessageDialog(null,"密码修改失败");
//                   }
//               } catch (Exception exception) {
//                   exception.printStackTrace();
//               }


                    }
                }
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
        });

        /*设置对话框的属性*/
       jDialog.setLayout(null);
       jDialog.setLocationRelativeTo(null);
       jDialog.setSize(550,400);
       jDialog.setVisible(true);

   }

    public static void main(String[] args) {
     AchangePwd a=new AchangePwd();
     a.init();
    }
}
