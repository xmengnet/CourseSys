package Student;

import ArrayListData.CourseResult;
import VerCode.ValidCode;
import dbOperaction.StudentOper;

import javax.swing.*;
import java.awt.event.ActionEvent;

import java.util.Vector;

public class SelectClasses {
    /*columnNames存放列名*/
    static Vector columnNames;
    static JTable jt = null;
    static JScrollPane jsp = null;
    //    传递当前登陆学生学号
    static String sno;

    public SelectClasses(String snum) throws Exception {
        sno = snum;
        init();
    }

    public void init() throws Exception {
        columnNames = new Vector();
        JDialog jDialog = new JDialog();
        jDialog.setTitle("学生选课");
        /*设置列名*/
        columnNames.add("课程号");
        columnNames.add("课程名");
        columnNames.add("限报人数");
        columnNames.add("剩余人数");
        columnNames.add("教授教师");
        columnNames.add("课时");

        /*创建学生操作类对像*/
        StudentOper studentOper = new StudentOper();
        /*调用学生类里的方法*/
        studentOper.display();

        /*加入内容到表格中*/
        jt = new JTable(studentOper.display(), columnNames);
        jt.setEnabled(false);

        //初始化 jsp滚动条
        jsp = new JScrollPane(jt);
        /*创建一个提示标签*/
        JLabel titleLabel = new JLabel("请从下列课程中选择您感兴趣的课程");
        Box horizontalBox1 = Box.createHorizontalBox();
        horizontalBox1.add(titleLabel);
        /*课程号标签*/
        JLabel cnamelabel = new JLabel("请输入课程号:");
        JTextField cnameText = new JTextField(20);
        /*确认按钮*/
        JButton confirm = new JButton("确认");

        confirm.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

                ValidCode vcode = new ValidCode();
                JButton confirm = new JButton("提交");
                confirm.addActionListener(ele -> {
                    if (validCode.receive(textValue.getText())) {
                        /*获取文本框内容*/
                        String text = cnameText.getText();
                        if (text.length() != 5) {
                            /*弹出对话框说名课程号填写错误*/
                            JOptionPane.showMessageDialog(null, "课程号输入错误");
                        }
                        /*课程号填写成功后进行的操作*/
                        else {
                            CourseResult courseResult = new CourseResult();
                            /*创建学生操作类对像*/
                            StudentOper studentOper = new StudentOper();

                            var sname = new StudentOper();
                            var cname = new StudentOper();
                            int courseid = Integer.parseInt(text);
                            try {
                                courseResult.setCname(cname.GetCnameBycourseid(courseid));
                                courseResult.setSname(sname.GetSnameBySno(Integer.parseInt(sno)));
                                courseResult.setSno(Integer.parseInt(sno));
                                courseResult.setCourseid(courseid);
                                /*把学号，姓名，课程号，课程名传入addClasses中*/
                                studentOper.addClasses(courseResult);
                                /*弹出消息框告知选课成功*/
                                JOptionPane.showMessageDialog(null, "课程选择成功");
                                /*监听选课成功确认按钮加监听*/
                                StudentOper in = new StudentOper();
                                in.dec(text);
                            } catch (Exception exception) {
                                JOptionPane.showMessageDialog(null, "请勿重复选择一门课程！");
                                exception.printStackTrace();
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "验证码输入错误，请重新输入！", "错误", 0);
                    }
                });
//                设置布局
                vCode.setLocation(10,10);
                vCode.setSize(90,30);
                codeValue.setLocation(90,10);
                codeValue.setSize(100,30);
                textValue.setLocation(180,10);
                textValue.setSize(100,30);
                refreshCode.setLocation(290,10);
                refreshCode.setSize(100,30);
                confirm.setLocation(400,10);
                confirm.setSize(100,30);
                verCode.add(vCode);
                verCode.add(codeValue);
                verCode.add(textValue);
                verCode.add(refreshCode);
                verCode.add(confirm);
                verCode.setSize(510,100);
                verCode.setLocationRelativeTo(null);
                verCode.setVisible(true);

            }
        });

        /*创建一个水平盒子和垂直盒子*/
        Box horizontalBox = Box.createHorizontalBox();
        Box verticalBox = Box.createVerticalBox();
        JPanel jPanel = new JPanel();
        jPanel.add(horizontalBox);

        /*把标签文本框加入到水平盒子中*/
        horizontalBox.add(cnamelabel);
        horizontalBox.add(cnameText);
        horizontalBox.add(confirm);

        /*把提示文本表格、水平盒子加入到垂直盒子中*/
        verticalBox.add(Box.createVerticalStrut(20));
        verticalBox.add(horizontalBox1);
        verticalBox.add(Box.createVerticalStrut(20));
        verticalBox.add(jsp);
        verticalBox.add(jPanel);
        verticalBox.add(Box.createVerticalStrut(100));
        /*把水平盒子加入到jDialog中*/
        jDialog.add(verticalBox);

        /*设置jDialog的大小、位置可见性*/
        jDialog.setSize(800, 550);
        jDialog.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        /*窗口在屏幕中间显示*/
        jDialog.setLocationRelativeTo(null);
        jDialog.setVisible(true);
    }

    /*以下仅在调试时使用*/
//    public static void main(String[] args) {
//        try {
//            SelectClasses s = new SelectClasses("1810361232");
//            s.init();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
