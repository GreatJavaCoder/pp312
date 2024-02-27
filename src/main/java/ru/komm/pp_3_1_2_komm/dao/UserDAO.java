package ru.komm.pp_3_1_2_komm.dao;

import ru.komm.pp_3_1_2_komm.model.User;

import java.util.List;

public interface UserDAO {
    public List<User> getAllUsers();
    public User getUser(int id);
    public void addUser(User user);
    public void editUser(int id, User updatedUser);
    public void deleteUser(int id);
}
