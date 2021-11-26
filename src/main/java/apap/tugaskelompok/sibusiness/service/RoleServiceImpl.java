package apap.tugaskelompok.sibusiness.service;

import apap.tugaskelompok.sibusiness.models.RoleModel;
import apap.tugaskelompok.sibusiness.repository.RoleDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements  RoleService{
    @Autowired
    RoleDB roleDb;

    @Override
    public List<RoleModel> findAll(){ return roleDb.findAll();}
}
