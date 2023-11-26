package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        Util.getConnection();
        UserServiceImpl usi = new UserServiceImpl();
        usi.createUsersTable();
        usi.saveUser("as", "sa", (byte) 0);
        usi.saveUser("asd", "saas", (byte) 127);
        usi.removeUserById(1);
        System.out.println(usi.getAllUsers());
        usi.cleanUsersTable();
        usi.dropUsersTable();
    }
}
