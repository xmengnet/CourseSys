package VerCode;

import javax.swing.*;
import java.awt.*;

public class CodeTest {
    JFrame jFrame = new JFrame("验证码测试");
    JLabel jLabel = new JLabel("验证码");
    JButton jButton = new JButton("刷新验证码");
    JTextField jTextField = new JTextField();
    JButton confirm = new JButton("提交");

    public void init() {
//        添加监听器，用来刷新内容
        ValidCode vcode = new ValidCode();
        jButton.addActionListener(e -> {
            jLabel.setText(null);
            jLabel.setText(vcode.init());
        });
        confirm.addActionListener(e->{
            if (vcode.receive(jTextField.getText())){
                JOptionPane.showMessageDialog(null, "成功！");
            }else {
                JOptionPane.showMessageDialog(null, "验证码错误！");
            }
        });
//        设置布局
        jFrame.setLayout(null);
        jLabel.setText(vcode.init());
        jLabel.setSize(100, 100);
        jLabel.setLocation(10, 0);
        jTextField.setLocation(100, 0);
        jTextField.setSize(110, 30);
        jButton.setSize(100, 20);
        jButton.setLocation(220, 10);
        confirm.setSize(100,20);
        confirm.setLocation(330,10);
        jLabel.setFont((new Font("仿宋", Font.BOLD, 20)));
        jFrame.setSize(700, 500);
        jFrame.add(jLabel);
        jFrame.add(jTextField);
        jFrame.add(jButton);
        jFrame.add(confirm);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(3);
        jFrame.setVisible(true);
    }

    public static void main(String[] args) {
        var test = new CodeTest();
        test.init();
    }
}
