package Student;

import VerCode.ValidCode;
import dbOperaction.StudentOper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Vector;

public class StudentDelete extends JDialog {
    JLabel nameLabel;
    JTextField idtext;
    JButton delBtn;
    static String currentsno;
    static Vector columnNames;
    static JTable jt = null;
    static JScrollPane jsp = null;
    static String sno;

    public StudentDelete(String snum) throws Exception {
        sno = snum;
        init();
    }

    public void init() throws Exception {
        columnNames = new Vector();
        columnNames.add("学号");
        columnNames.add("姓名");
        columnNames.add("课程ID");
        columnNames.add("课程名");
        StudentOper studentOper = new StudentOper();
        jt = new JTable(studentOper.changeClasses(sno), columnNames);
        jt.setEnabled(false);
        currentsno = sno;
        jt.setSize(600, 200);
        jt.setLocation(20, 50);
        //初始化 jsp滚动条
        jsp = new JScrollPane(jt);
        jsp.setSize(600, 200);
        jsp.setLocation(20, 70);
        this.add(jsp);
//        最上面的提示框
        JLabel titleLabel = new JLabel("当前所选课程如下所示");
        titleLabel.setLocation(200, 10);
        titleLabel.setSize(200, 40);
        titleLabel.setFont((new Font("仿宋", Font.BOLD, 20)));
        this.add(titleLabel);
//            下面部分开始
        this.setTitle("学生退选课程");
        this.setSize(650, 500);
        this.setLocation(600, 300);
        this.setLayout(null);
        nameLabel = new JLabel("请输入退选课程编号：");
        nameLabel.setSize(150, 50);
        nameLabel.setLocation(50, 330);
        this.add(nameLabel);

        idtext = new JTextField();
        idtext.setSize(160, 40);
        idtext.setLocation(200, 330);
        this.add(idtext);

        delBtn = new JButton("确认退选");
        delBtn.setSize(90, 38);
        delBtn.setLocation(430, 330);
        delBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*delid为要删除的课程号*/
                String delid = idtext.getText();

                /*获取文本框内容*/
//                    String text = delid.getText();
                if (delid.equals("")) {
                    JOptionPane.showMessageDialog(null, "输入内容为空，请重新输入");
                } else if (delid.length() < 5) {
                    /*弹出对话框说名课程号填写错误*/
                    JOptionPane.showMessageDialog(null, "课程号输入错误");
                }
                /*课程号填写成功后进行的操作*/
                else {
                    JDialog verCode = new JDialog();
                    verCode.setTitle("请输入验证码！");
                    verCode.setLayout(null);
//                显示字体“验证码”
                    JLabel vCode = new JLabel("验证码：");
//                显示验证码的值
                    JLabel codeValue = new JLabel();
//                创建验证码对象
                    ValidCode validCode = new ValidCode();
                    String validCodeValue = validCode.init();
                    codeValue.setText(validCodeValue);
                    JTextField textValue = new JTextField();
//                刷新验证码
                    JButton refreshCode = new JButton("刷新验证码");
                    refreshCode.addActionListener(E -> {
                        codeValue.setText(null);
                        codeValue.setText(validCode.init());
                    });
//                ValidCode validcode = new ValidCode();
                    JButton confirm = new JButton("提交");
                    confirm.addActionListener(ele -> {
                        if (validCode.receive(textValue.getText())) {

                            try {
                                int result = studentOper.delClasses(Integer.parseInt(currentsno), delid);
                                /*返回值为1删除成功，课程剩余可选人数加1*/
                                if (result == 1) {
                                    JOptionPane.showMessageDialog(null, "课程删除成功");
                                    studentOper.add(delid);
                                    verCode.dispose();
                                } else {
                                    JOptionPane.showMessageDialog(null, "课程删除失败");
                                    verCode.dispose();
                                }
                            } catch (Exception exception) {
                                exception.printStackTrace();
                            }

                        }
                    });
                    //                设置布局
                    vCode.setLocation(10, 10);
                    vCode.setSize(70, 30);
                    codeValue.setLocation(70, 10);
                    codeValue.setSize(100, 30);
                    textValue.setLocation(130, 10);
                    textValue.setSize(100, 30);
                    refreshCode.setLocation(250, 10);
                    refreshCode.setSize(100, 30);
                    confirm.setLocation(370, 10);
                    confirm.setSize(100, 30);
                    verCode.add(vCode);
                    verCode.add(codeValue);
                    verCode.add(textValue);
                    verCode.add(refreshCode);
                    verCode.add(confirm);
                    verCode.setSize(510, 100);
                    verCode.setLocationRelativeTo(null);
                    verCode.setVisible(true);
                }
            }
        });
        this.add(delBtn);
        this.setVisible(true);
    }


//    /*以下仅调试时使用*/
//    public static void main(String[] args) {
//        try {
//            StudentDelete a = new StudentDelete("1810361232");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
}

