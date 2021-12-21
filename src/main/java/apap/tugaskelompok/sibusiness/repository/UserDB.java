package apap.tugaskelompok.sibusiness.repository;

import apap.tugaskelompok.sibusiness.models.UserModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDB extends JpaRepository<UserModel, Long> {
    UserModel findByUuid(String Uuid);
    UserModel findByUsername(String Username);
    void deleteByUsername(String Username);
}
