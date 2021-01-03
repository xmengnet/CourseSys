package Student;
import dbOperaction.StudentOper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentDelete extends JDialog implements ActionListener {
        JLabel Namelabel;
        JTextField idtext;
        JButton Delbt;
        static  String currentsno;
        public StudentDelete(String sno) {
            currentsno=sno;
            this.setTitle("管理员删除课程");
            this.setSize(650,350);
            this.setLocation(100, 20);
            this.setLayout(null);
            Namelabel=new JLabel("请输入退选课程编号：");
            Namelabel.setSize(150,50);
            Namelabel.setLocation(50, 0);
            this.add(Namelabel);

            idtext=new JTextField();
            idtext.setSize(160,40);
            idtext.setLocation(200, 0);
            this.add(idtext);

            Delbt=new JButton("确认退选");
            Delbt.setSize(90,38);
            Delbt.setLocation(470, 0);
            this.add(Delbt);
            Delbt.addActionListener(this);
            this.setVisible(true);
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            /*delid为要删除的课程号*/
            String delid=idtext.getText();
            /*创建学生操作类对像*/
            StudentOper studentOper=new StudentOper();
            try {
                int result=studentOper.delClasses(Integer.parseInt(currentsno),delid);
                /*返回值为1删除成功，课程剩余可选人数加1*/
                if(result==1){
                JOptionPane.showMessageDialog(null, "课程删除成功");
                studentOper.add(delid);
                }
                else {
                    JOptionPane.showMessageDialog(null, "课程删除失败");
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }

        }
        /*以下仅调试时使用*/
        public static void main(String[] args) {
            StudentDelete a=new StudentDelete("1810361232");

        }
    }

