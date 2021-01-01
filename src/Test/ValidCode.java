package Test;
/**
 * 简易生成验证码功能，从数字和字母中选取四个进行
 * 合并，与输入内容进行对比
 * 时间：2021年1月1日22时33分
 */

import java.util.Random;
import java.util.Scanner;

public class ValidCode {
    public static void main(String[] args) {
        Random random = new Random();
        String[] code = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "A", "S", "D", "F",
                "G", "H", "J", "K", "L", "Z", "X", "C", "V", "B", "N", "M"};
        StringBuilder confirm = new StringBuilder();
        for (var i = 0; i < 4; i++) {
            int num = random.nextInt(36);
            System.out.println(num);
            confirm.append(code[num]);
        }
        System.out.println(confirm);
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入验证码：");
        String str = scanner.next();
        if (str.equals(confirm.toString())) {
            System.out.println("验证码正确！");
        } else {
            System.out.println("验证码输入错误！");
        }
    }
}
