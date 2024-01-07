package lk.ijse.computerShop.model;
/* 
    @author Sachi_S_Bandara
    @created 11/3/2023 - 8:38 PM 
*/

import lk.ijse.computerShop.db.DbConnection;
import lk.ijse.computerShop.dto.CustomerDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerModel {
    public boolean saveCustomer(CustomerDto customerDto) {
        try {
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<CustomerDto> getAllCustomers() {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            String sql="select * from customer";
            PreparedStatement pstm = connection.prepareStatement(sql);

            ArrayList<CustomerDto> dtoList = new ArrayList<>();

            ResultSet resultSet = pstm.executeQuery();

            while (resultSet.next()){
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                String address = resultSet.getString(3);
                String email = resultSet.getString(4);
                String mobile = resultSet.getString(5);
                byte[] bytes = resultSet.getBytes(6);

                CustomerDto customerDto = new CustomerDto(id, name, address, email, mobile,bytes);
                dtoList.add(customerDto);
            }
            return dtoList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteCustomer(String id) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            String sql="delete from customer where cusID=?";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1,id);

            boolean b = pstm.executeUpdate() > 0;
            return b;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public CustomerDto getCustomer(String updateCustomerId) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            String sql="select * from customer where cusID=?";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1,updateCustomerId);

            ResultSet resultSet = pstm.executeQuery();
            CustomerDto customerDto = null;
            if (resultSet.next()){
                 customerDto = new CustomerDto(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                         resultSet.getBytes(6)


                );
            }
            return customerDto;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean updateCustomer(CustomerDto dto) {
        try {
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getLastCustomerId() {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            String sql="SELECT cusID FROM customer ORDER BY cusID DESC LIMIT 1";
            ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

            String currentCusId = null;

            if(resultSet.next()) {
                currentCusId= resultSet.getString(1);
                return splitOrderId(currentCusId);
            }
            return splitOrderId(null);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String splitOrderId(String currentCusId) {
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
