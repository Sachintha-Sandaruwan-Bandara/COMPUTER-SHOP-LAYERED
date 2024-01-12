package lk.ijse.computerShop.dao.impl;
/* 
    @author Sachi_S_Bandara
    @created 1/12/2024 - 1:27 PM 
*/

import lk.ijse.computerShop.dao.custom.ItemDao;
import lk.ijse.computerShop.entity.Item;

import java.util.ArrayList;

public class ItemDaoImpl implements ItemDao {
    @Override
    public boolean save(Item Dto) {
        return false;
    }

    @Override
    public ArrayList<Item> getAll() {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public Item get(String Id) {
        return null;
    }

    @Override
    public boolean update(Item Dto) {
        return false;
    }

    @Override
    public String getLastId() {
        return null;
    }

    @Override
    public String splitId(String currentId) {
        return null;
    }
}
