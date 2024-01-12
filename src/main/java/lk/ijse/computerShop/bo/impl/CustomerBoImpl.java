package lk.ijse.computerShop.bo.impl;
/* 
    @author Sachi_S_Bandara
    @created 1/11/2024 - 9:16 PM 
*/

import lk.ijse.computerShop.bo.custom.CustomerBo;
import lk.ijse.computerShop.db.DbConnection;
import lk.ijse.computerShop.dto.CustomerDto;
import lk.ijse.computerShop.entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBoImpl implements CustomerBo {


    @Override
    public boolean saveCustomer(CustomerDto customerDto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="insert into customer values (?,?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1,customerDto.getId());
        pstm.setObject(2,customerDto.getName());
        pstm.setObject(3,customerDto.getAddress());
        pstm.setObject(4,customerDto.getEmail());
        pstm.setObject(5,customerDto.getMobile());
        pstm.setObject(6,customerDto.getImageBytes());


        boolean b = pstm.executeUpdate() > 0;
        return b;
    }

    @Override
    public ArrayList<Customer> getAllCustomers() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="select * from customer";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ArrayList<Customer> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()){
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String email = resultSet.getString(4);
            String mobile = resultSet.getString(5);
            byte[] bytes = resultSet.getBytes(6);

            Customer customer = new Customer(id, name, address, email, mobile,bytes);
            dtoList.add(customer);
        }
        return dtoList;
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="delete from customer where cusID=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,id);

        boolean b = pstm.executeUpdate() > 0;
        return b;
    }

    @Override
    public Customer getCustomer(String updateCustomerId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="select * from customer where cusID=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,updateCustomerId);

        ResultSet resultSet = pstm.executeQuery();
        Customer customer = null;
        if (resultSet.next()){
            customer = new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getBytes(6)


            );
        }
        return customer;
    }

    @Override
    public boolean updateCustomer(CustomerDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="UPDATE customer SET cusID=?,name=?,address=?,email=?,mobile=?,image=? WHERE cusID=?;";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,dto.getId());
        pstm.setObject(2,dto.getName());
        pstm.setObject(3,dto.getAddress());
        pstm.setObject(4,dto.getEmail());
        pstm.setObject(5,dto.getMobile());
        pstm.setObject(6,dto.getImageBytes());
        pstm.setObject(7,dto.getId());

        boolean b = pstm.executeUpdate() > 0;
        return b;
    }

    @Override
    public String getLastCustomerId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT cusID FROM customer ORDER BY cusID DESC LIMIT 1";
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        String currentCusId = null;

        if(resultSet.next()) {
            currentCusId= resultSet.getString(1);
            return splitId(currentCusId);
        }
        return splitId(null);
    }

    @Override
    public String splitId(String currentCusId) {

        if(currentCusId != null) {
            String[] split = currentCusId.split("c");
            int id = Integer.parseInt(split[1]);    //008
            id ++;  //9
            // return "c00" + id;
            return String.format("c%03d", id); // Formats the integer with leading zeros
        }
        return "c001";
    }
}
