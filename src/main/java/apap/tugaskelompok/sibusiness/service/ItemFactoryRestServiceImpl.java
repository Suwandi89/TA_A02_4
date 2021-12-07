package apap.tugaskelompok.sibusiness.service;

import apap.tugaskelompok.sibusiness.models.ItemFactoryModel;
import apap.tugaskelompok.sibusiness.repository.ItemFactoryDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Transactional
public class ItemFactoryRestServiceImpl implements ItemFactoryRestService {
    @Autowired
    ItemFactoryDB itemFactoryDB;


    @Override
    public ItemFactoryModel addItemFactory(ItemFactoryModel itemFactory) {
        return itemFactoryDB.save(itemFactory);
    }

    @Override
    public ItemFactoryModel updateItemFactory(ItemFactoryModel itemFactory) {
        return itemFactoryDB.save(itemFactory);
    }

    @Override
    public List<ItemFactoryModel> getItemFactoryList() {
        return itemFactoryDB.findAll();
    }

    @Override
    public void deleteItemFactory(ItemFactoryModel itemFactory) {
        itemFactoryDB.delete(itemFactory);
    }

    @Override
    public ItemFactoryModel getById(Long id) {
        return itemFactoryDB.getById(id);
    }
}
