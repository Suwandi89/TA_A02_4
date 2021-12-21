package apap.tugaskelompok.sibusiness.service;

import apap.tugaskelompok.sibusiness.models.UserModel;

import java.util.List;

public interface UserService {
    UserModel addUser(UserModel user);
    public String encrypt(String password);
    List<UserModel> getUserList();
    UserModel getUserByUsername(String username);
    UserModel getUserByUuid(String uuid);
    void updateUser(UserModel user);

}
