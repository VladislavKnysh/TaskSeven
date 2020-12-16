package com.company.user_parser;


import com.company.exception.InvalidUserException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserParser {

    public List<User> parseList(String user) {
        List<User> userList = new ArrayList<>();
        String[] strings = user.split("([,]\\s)|[,]");
        for (String string : strings) {
            User newUser = parse(string);
            if (Objects.nonNull(newUser)) {
                userList.add(newUser);
            }
        }
        return userList;
    }


    public User parse(String user) {
        String backup = user;
        try {

            String[] data = new String[3];

            if (checkData(user, DataPart.NICKNAME)) {
                data[0] = getData(user, DataPart.NICKNAME);
                user = user.replace(data[0], "");

            }
            if (checkData(user, DataPart.PASSWORD)) {
                data[2] = getData(user, DataPart.PASSWORD);
                user = user.replace(data[2], "");

            }
            if (checkData(user, DataPart.USERNAME)) {
                data[1] = getData(user, DataPart.USERNAME);

            } else throw new InvalidUserException();


            return createUser(data);
        } catch (InvalidUserException e) {
            System.out.println("Invalid user data: " + backup);
            return null;
        }
    }

    private User createUser(String[] data) {
        for (String s : data) {
            if (!Objects.nonNull(s)) {
                break;
            } else {
                return new User(data[0], data[1], data[2]);
            }
        }
        if (Objects.nonNull(data[0]) && !Objects.nonNull(data[2])) {
            return new User(data[0], data[1], null);
        } else if (!Objects.nonNull(data[0]) && Objects.nonNull(data[2])) {
            return new User(data[1], data[1], data[2]);
        } else return new User(data[1], data[1], null);


    }

    private boolean checkData(String string, DataPart dataPart) {
        Matcher matcher = dataPart.getPattern().matcher(string);
        return matcher.find();
    }

    private String getData(String string, DataPart dataPart) {
        Matcher matcher = dataPart.getPattern().matcher(string);
        matcher.find();
        return matcher.group();
    }


    private enum DataPart {
        NICKNAME("(\\w*\\d*)+(?=[:])"),
        USERNAME("(\\w*\\d+)|(\\w+\\d*)"),
        PASSWORD("(?<=@).(\\w*\\d*)+");
        private final String REGEX;

        DataPart(String regex) {
            this.REGEX = regex;
        }

        Pattern getPattern() {
            return Pattern.compile(REGEX);

        }

    }
}
