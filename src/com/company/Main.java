package com.company;

import com.company.user_parser.User;
import com.company.user_parser.UserParser;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserParser userParser = new UserParser();
        List<User> userList = userParser.parseList("programmer:vasia@123,programmer:vaska," +
                " vasil@1234,vasiliy, vasilius:@trulala");
        for (User user : userList) {
            System.out.println(user.toString());
        }
    }
}
