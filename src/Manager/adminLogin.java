package Manager;

import db.*;

import java.sql.*;
import java.sql.Statement;
import java.util.Scanner;

public class adminLogin {
    private static DbCon dbCon = new DbCon();

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入账号：");
        String count = scanner.next();
        System.out.println("请输入密码：");
        String passwd = scanner.next();
//        查询帐号信息
        String query = "select admin from manager where admin =" + count;

        Statement statement = dbCon.getCon().createStatement();
        ResultSet resultSet;
//        执行操作语句
        resultSet = statement.executeQuery(query);

        if (!resultSet.next()) {
//            System.out.println(resultSet.getString(1) + resultSet.getString(2));
            System.out.println("帐号不存在!");
        }
        else {
            query = "select passwd from manager where passwd = " + passwd;
            resultSet = statement.executeQuery(query);
            if (!resultSet.next()) {
                System.out.println("帐号或密码错误！");
            }
            else {
                System.out.println("登陆成功！");
            }

        }
    }
}
