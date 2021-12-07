package apap.tugaskelompok.sibusiness.service;

import apap.tugaskelompok.sibusiness.models.ItemFactoryModel;

import java.util.List;
import java.util.Optional;

public interface ItemFactoryRestService {
    ItemFactoryModel addItemFactory(ItemFactoryModel itemFactory);
    ItemFactoryModel updateItemFactory(ItemFactoryModel itemFactory);
    List<ItemFactoryModel> getItemFactoryList();
    void deleteItemFactory(ItemFactoryModel itemFactory);
    ItemFactoryModel getById(Long id);
}
