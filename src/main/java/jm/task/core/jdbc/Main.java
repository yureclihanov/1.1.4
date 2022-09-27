package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Yuriy", "Lihanov", (byte) 27);
        userService.saveUser("Svetlana", "Lihanova", (byte) 24);
        userService.saveUser("Mira", "Lihanova", (byte) 2);
        userService.saveUser("Vasya", "Pupkin", (byte) 30);

        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
