package apap.tugaskelompok.sibusiness.repository;

import apap.tugaskelompok.sibusiness.models.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDB extends JpaRepository<RoleModel,Long> {
}
