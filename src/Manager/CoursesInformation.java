package Manager;

import dbOperaction.AdminOper;

import javax.swing.*;

import java.util.Vector;
public class CoursesInformation {
    /*columnNames存放列名*/
    static Vector columnNames;
    static JTable jt = null;
    static JScrollPane jsp = null;
    //    传递管理员账号
    static String name;

    public CoursesInformation(String admin) throws Exception {
        name = admin;
        init();
    }

    public void init() throws Exception {
        columnNames = new Vector();
        JDialog jDialog = new JDialog();
        jDialog.setTitle("显示课程信息");
        /*设置列名*/
        columnNames.add("课程号");
        columnNames.add("课程名");
        columnNames.add("限报人数");
        columnNames.add("剩余人数");
        columnNames.add("教授教师");
        columnNames.add("课时");
        /*创建管理员操作类对像*/
        AdminOper adminOper = new AdminOper();
        /*调用管理员类里的方法*/
        adminOper.display();
        /*加入内容到表格中*/
        jt = new JTable(adminOper.display(), columnNames);
        jt.setEnabled(false);

        //初始化 jsp滚动条
        jsp = new JScrollPane(jt);
        /*创建一个提示标签*/
        JLabel titleLabel = new JLabel("所有课程信息如下所示");
        Box horizontalBox1 = Box.createHorizontalBox();
        horizontalBox1.add(titleLabel);
        /*创建一个水平盒子和垂直盒子*/
        Box horizontalBox = Box.createHorizontalBox();
        Box verticalBox = Box.createVerticalBox();
        JPanel jPanel = new JPanel();
        jPanel.add(horizontalBox);

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

//    /*以下仅在调试时使用*/
//    public static void main(String[] args) {
//        try {
//            CoursesInformation coursesInformation = new CoursesInformation("admin");
//            coursesInformation.init();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
