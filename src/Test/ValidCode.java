package Test;
/**
 * 简易生成验证码功能，从数字和字母中选取四个进行
 * 合并，与输入内容进行对比
 * 时间：2021年1月1日22时33分
 */

import java.util.*;

public class ValidCode {
    final static String[] code = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "0",
            "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "A", "S", "D", "F",
            "G", "H", "J", "K", "L", "Z", "X", "C", "V", "B", "N", "M"};
    static String verCode;

    //方法判断输入值与实际值是否相等，返回true或false
    public Boolean receive(String code) {
        if (code.equals(verCode)) {
            return true;
        } else return false;
    }

    //    无用代码（与init()重复）
//    public String refresh(){
//        StringBuilder confirm = new StringBuilder();
//        Random random = new Random();
//        for (var i = 0; i < 4; i++) {
//            int num = random.nextInt(36);
////排序生成验证码
//
//            confirm.append(code[num]);
//        }
//        System.out.println(confirm);
//        verCode=confirm.toString();
//        return verCode;
//    }
//返回生成的验证码，区分大小写
    public String init() {
        StringBuilder confirm = new StringBuilder();
        Random random = new Random();
        for (var i = 0; i < 4; i++) {
            int num = random.nextInt(36);
//排序生成验证码

            confirm.append(code[num]);
        }
        System.out.println(confirm);
        verCode = confirm.toString();
        return verCode;
    }

    public static void main(String[] args) {
        var code = new ValidCode();
        code.init();
    }
}
