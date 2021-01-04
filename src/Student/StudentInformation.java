package Student;


import ArrayListData.StudentInfo;
import VerCode.ValidCode;
import dbOperaction.StudentOper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public  class StudentInformation extends JDialog{
    JButton Updatebt;

    JLabel snolabel;
    JLabel  spasswdlabel;
    JLabel snamelabel;
    JLabel classeslabel;
    JLabel idlabel1;
    JLabel enrollmentlabel;
    JLabel snolabelText;
    JTextField  spasswdtext;
    JLabel snamelabelText;
    JLabel classeslabelText;
    JLabel idlabel2;
    JLabel enrollmentlabelText;
    //    验证码标签、验证码框、刷新验证码
    JLabel verCodeLabel = new JLabel();
    JTextField verCodeValue = new JTextField();
    JButton refreshCode = new JButton("刷新验证码");

    //    创建验证码对象
    ValidCode validCode=new ValidCode();

    static String sno;
    StudentInfo info = new StudentInfo();
//    private String info;

    public StudentInformation(String snum) {
        sno = snum;
        init();
    }
    public void init() {
//        设置验证码标签
        verCodeLabel.setText(validCode.init());
        verCodeLabel.setSize(100, 40);
        verCodeLabel.setLocation(150, 300);

//      设置文本框和刷新按钮
        verCodeValue.setSize(80,30);
        verCodeValue.setLocation(220,305);
        refreshCode.setSize(110,40);
        refreshCode.setLocation(310,300);

        this.setTitle("学生基本信息修改");
        this.setSize(600,450);
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        try {
            info=new StudentOper().DisStuInfo(sno);
        } catch (Exception e) {
            e.printStackTrace();
        }
        snolabel=new JLabel("学号");
        snolabel.setSize(100,30);
        snolabel.setLocation(140, 60);
        this.add(snolabel);

        snolabelText=new JLabel(sno);
        snolabelText.setSize(120,30);
        snolabelText.setLocation(260, 60);
        snolabelText.setText( sno);
        System.out.println(sno);
        this.add(snolabelText);
        spasswdlabel=new JLabel("密码");
        spasswdlabel.setSize(100,30);
        spasswdlabel.setLocation(140, 100);
        this.add( spasswdlabel);

        spasswdtext=new JTextField();
        spasswdtext.setSize(120,30);
        spasswdtext.setLocation(260, 100);
        spasswdtext.setText(info.getSpasswd());
        this.add( spasswdtext);
        System.out.println(info.getSpasswd());

        snamelabel=new JLabel("姓名");
        snamelabel.setSize(100,30);
        snamelabel.setLocation(140, 140);
        this.add( snamelabel);

        snamelabelText=new JLabel();
        snamelabelText.setSize(120, 30);
        snamelabelText.setLocation(260, 140);
        snamelabelText.setText(info.getSname());
        this.add( snamelabelText);
        System.out.println(info.getSname());

        classeslabel=new JLabel("班级");
        classeslabel.setSize(100,30);
        classeslabel.setLocation(140, 180);
        this.add(classeslabel);

        classeslabelText=new JLabel();
        classeslabelText.setSize(120, 30);
        classeslabelText.setLocation(260, 180);
        classeslabelText.setText(info.getClasses());
        this.add(classeslabelText);
        System.out.println(info.getClasses());

        idlabel1=new JLabel("身份证号");
        idlabel1.setSize(100,30);
        idlabel1.setLocation(140, 220);
        this.add(idlabel1);

        idlabel2=new JLabel();
        idlabel2.setSize(160, 30);
        idlabel2.setLocation(260, 220);
        idlabel2.setText(info.getId());
        this.add(idlabel2);
        System.out.println(info.getId());

        enrollmentlabel=new JLabel("注册时间");
        enrollmentlabel.setSize(100,30);
        enrollmentlabel.setLocation(140, 260);
        this.add(enrollmentlabel);

        enrollmentlabelText=new JLabel();
        enrollmentlabelText.setSize(120, 30);
        enrollmentlabelText.setLocation(260, 260);
        enrollmentlabelText.setText(info.getEnrollment());

        this.add(enrollmentlabelText);

        Updatebt=new JButton("确认修改密码");
        Updatebt.setSize(110,40);
        Updatebt.setLocation(250, 360);
        this.add( Updatebt);
        Updatebt.addActionListener(e -> {
            String passWd=spasswdtext.getText();

            if(validCode.receive(verCodeValue.getText())){
                if(passWd.equals(info.getSpasswd())){
                    JOptionPane.showMessageDialog(null,"密码未改变！");
                }else{
                    StudentOper studentOper = new StudentOper();
                    try {
                        int result= studentOper.changeSpasswd(sno,passWd);
                        if(result==1){
                            JOptionPane.showMessageDialog(null, "密码修改成功");
                            this.dispose();
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "密码修改失败");
                            verCodeLabel.setText(null);
                            verCodeValue.setText(null);
                            verCodeLabel.setText(validCode.init());
                        }
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }else {
            JOptionPane.showMessageDialog(null,"验证码输入错误！");
            verCodeValue.setText(null);
            verCodeLabel.setText(null);
            verCodeLabel.setText(validCode.init());
        }


        });

        //刷新按钮事件
        refreshCode.addActionListener(e->{
            verCodeLabel.setText(null);
            verCodeValue.setText(null);
            verCodeLabel.setText(validCode.init());
        });
        this.add(verCodeLabel);
        this.add(verCodeValue);
        this.add(refreshCode);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }


//    public static void main(String[] args) {
//        try {
//            new StudentInformation("1810361232");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}




