package lk.ijse.computerShop.bo.custom;
/* 
    @author Sachi_S_Bandara
    @created 1/11/2024 - 9:18 PM 
*/

import lk.ijse.computerShop.bo.SuperBo;
import lk.ijse.computerShop.dto.CustomerDto;
import lk.ijse.computerShop.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBo extends SuperBo {
    boolean saveCustomer(CustomerDto customerDto) throws SQLException;
    ArrayList<Customer> getAllCustomers() throws SQLException;
    boolean deleteCustomer(String id) throws SQLException;
    Customer getCustomer(String updateCustomerId) throws SQLException;
    boolean updateCustomer(CustomerDto dto) throws SQLException;
    String getLastCustomerId() throws SQLException;
    String splitId(String currentCusId);
}
