package Student;


import dbOperaction.StudentOper;

import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public  class StudentInfo extends JDialog implements ActionListener{
    JButton Updatebt;
    JButton cannel;
    JLabel snolabel1;
    JLabel  spasswdlabel1;
    JLabel snamelabel1;
    JLabel classeslabel1;
    JLabel idlabel1;
    JLabel enrollmentlabel1;
    JLabel snolabel2;
    JTextField  spasswdtext;
    JLabel snamelabel2;
    JLabel classeslabel2;
    JLabel idlabel2;
    JLabel enrollmentlabel2;
    static String sno;

    public StudentInfo(String snum) throws Exception {
        sno = snum;
        init();
    }
    public void init() {
        this.setSize(600,450);
        this.setLocation(100, 20);
        this.setLayout(null);


        snolabel1=new JLabel("学号");
        snolabel1.setSize(100,30);
        snolabel1.setLocation(100, 60);
        this.add(snolabel1);

        snolabel2=new JLabel();
        snolabel2.setSize(120,30);
        snolabel2.setLocation(220, 60);
        this.add(snolabel2);

        spasswdlabel1=new JLabel("密码");
        spasswdlabel1.setSize(100,30);
        spasswdlabel1.setLocation(100, 100);
        this.add( spasswdlabel1);

        spasswdtext=new JTextField();
        spasswdtext.setSize(120,30);
        spasswdtext.setLocation(220, 100);
        this.add( spasswdtext);

        snamelabel1=new JLabel("姓名");
        snamelabel1.setSize(100,30);
        snamelabel1.setLocation(100, 140);
        this.add( snamelabel1);

        snamelabel2=new JLabel();
        snamelabel2.setSize(120, 30);
        snamelabel2.setLocation(220, 140);
        this.add( snamelabel2);

        classeslabel1=new JLabel("班级");
        classeslabel1.setSize(100,30);
        classeslabel1.setLocation(100, 180);
        this.add(classeslabel1);

        classeslabel2=new JLabel();
        classeslabel2.setSize(120, 30);
        classeslabel2.setLocation(220, 180);
        this.add(classeslabel2);

        idlabel1=new JLabel("身份证号");
        idlabel1.setSize(100,30);
        idlabel1.setLocation(100, 220);
        this.add(idlabel1);

        idlabel2=new JLabel();
        idlabel2.setSize(120, 30);
        idlabel2.setLocation(220, 220);
        this.add(idlabel2);

        enrollmentlabel1=new JLabel("注册时间");
        enrollmentlabel1.setSize(100,30);
        enrollmentlabel1.setLocation(100, 260);
        this.add(enrollmentlabel1);

        enrollmentlabel2=new JLabel();
        enrollmentlabel2.setSize(120, 30);
        enrollmentlabel2.setLocation(220, 260);
        this.add(enrollmentlabel2);

        Updatebt=new JButton("修改密码");
        Updatebt.setSize(85,40);
        Updatebt.setLocation(150, 320);
        this.add( Updatebt);
        Updatebt.addActionListener(e -> {
            StudentOper studentOper = new StudentOper();

            try {
               int result= studentOper.changeSpasswd(sno,spasswdtext.getText());
                if(result==1){
                    JOptionPane.showMessageDialog(null, "密码修改成功");
                }
                else{
                    JOptionPane.showMessageDialog(null, "密码修改失败");
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        this.setVisible(true);


        cannel=new JButton("取消");
        cannel.setSize(85,40);
        cannel.setLocation(300, 320);
        this.add(cannel);
        cannel.addActionListener(this);
        this.setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
//        StudentOper studentOper = new StudentOper();
//        try {
//            studentOper.changeSpasswd(1810361232,spasswdtext.getText());
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }

//        String addName=Nametext.getText();
//        String addTeacher=Teachertext.getText();
//        String addlimit=limittext.getText();
//        String addcurplus=curplustext.getText();
//        String addcid=cidtext.getText();
//        String addteachTime=teachTimetext.getText();

//        DbCon dbCon=new DbCon();
//        try {
//            Connection conn = dbCon.getCon();
//            Statement st = conn.createStatement();
//            String  strSQL="insert into courses values('"+addcid+"','"+addName+"','"+addlimit+"','"+addcurplus+"','"+addTeacher+"','"+addteachTime+"')";
//            String  strSQL1="(Select* from courses where courseid='"+addcid+"' )";
//
//            if(!addName.trim().equals("")&&!addcurplus.trim().equals("")&&!addTeacher.trim().equals("")&&!addcid.trim().equals("")&&!addlimit.trim().equals("")&&!addteachTime.trim().equals(""))
//            {
//                ResultSet rs1=st.executeQuery(strSQL1);
//                if(rs1.next())
//                {
//                    JOptionPane.showMessageDialog(null,"该课程已存在");     }
//                else {
//                    int rs=st.executeUpdate(strSQL);
//                    if(rs==1) {
//                        JOptionPane.showMessageDialog(null,"课程添加成功"); }
//                    else{
//                        JOptionPane.showMessageDialog(null,"课程添加失败");
//                    }
//                }
//            }
//            else
//            { JOptionPane.showMessageDialog(null,"请输入课程信息");
//            }
//            conn.close();//关闭数据库连接
//        } catch (Exception ex) {
//            System.out.println("数据库连接或者是数据库操作失败");
//        }
    }
    public static void main(String[] args) {
        try {
            StudentInfo s=new StudentInfo("1810361232");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




