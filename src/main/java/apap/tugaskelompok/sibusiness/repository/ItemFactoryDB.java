package apap.tugaskelompok.sibusiness.repository;


import apap.tugaskelompok.sibusiness.models.ItemFactoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemFactoryDB extends JpaRepository<ItemFactoryModel,Long> {
}
