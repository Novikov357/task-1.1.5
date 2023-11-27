package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        final UserServiceImpl USI = new UserServiceImpl();
        USI.createUsersTable();
        USI.saveUser("as", "sa", (byte) 0);
        USI.saveUser("asd", "saas", (byte) 127);
        USI.saveUser("asdf", "zxc", (byte) 1);
        USI.saveUser("qwer", "wwqe", (byte) 2);
        System.out.println(USI.getAllUsers());
        USI.cleanUsersTable();
        USI.dropUsersTable();
    }
}
