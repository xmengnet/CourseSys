package Student;

import db.*;

import java.sql.*;
import java.sql.Statement;
import java.util.Scanner;

public class StudentLogin {
    private static DbCon dbCon = new DbCon();
    public static void main(String[] args) throws Exception {
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入账号：");
        String count=scanner.next();
        System.out.println("请输入密码：");
        String passwd=scanner.next();
        String quary = "select passwd from manager where admin = "+passwd;
        Statement statement = dbCon.getCon().createStatement();
        ResultSet resultSet;
        resultSet = statement.executeQuery(quary);
        if (resultSet.next()) {
            System.out.println(resultSet.getString(1)+resultSet.getString(2));
        }
    }
}
