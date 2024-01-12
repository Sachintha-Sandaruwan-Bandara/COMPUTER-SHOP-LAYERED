package lk.ijse.computerShop.dao;
/* 
    @author Sachi_S_Bandara
    @created 1/11/2024 - 9:34 PM 
*/

import lk.ijse.computerShop.dto.CustomerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDao <T> extends SuperDao{
    boolean save(T Dto) throws SQLException;
    ArrayList<T> getAll() throws SQLException;
    boolean delete(String id) throws SQLException;
    T get(String Id) throws SQLException;
    boolean update(T Dto) throws SQLException;
    String getLastId() throws SQLException;
    String splitId(String currentId);

}
