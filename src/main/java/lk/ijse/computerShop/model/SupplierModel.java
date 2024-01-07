package lk.ijse.computerShop.model;
/* 
    @author Sachi_S_Bandara
    @created 11/18/2023 - 7:06 PM 
*/

import lk.ijse.computerShop.db.DbConnection;
import lk.ijse.computerShop.dto.SupplierDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierModel {
    public boolean saveSupplier(SupplierDto supplierDto) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            String sql="insert into supplier values (?,?,?,?,?)";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1,supplierDto.getId());
            pstm.setObject(2,supplierDto.getName());
            pstm.setObject(3,supplierDto.getAddress());
            pstm.setObject(4,supplierDto.getMobile());
            pstm.setObject(5,supplierDto.getEmail());

            boolean b = pstm.executeUpdate() > 0;
            return b;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public ArrayList<SupplierDto> getAllSuppliers() {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            String sql="select * from supplier";
            PreparedStatement pstm = connection.prepareStatement(sql);

            ArrayList<SupplierDto> dtoList = new ArrayList<>();

            ResultSet resultSet = pstm.executeQuery();

            while (resultSet.next()){
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                String address = resultSet.getString(3);
                String email = resultSet.getString(4);
                String mobile = resultSet.getString(5);


                SupplierDto supplierDto = new SupplierDto(id, name, address, email, mobile);
                dtoList.add(supplierDto);
            }
            return dtoList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteSupplier(String id) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            String sql="delete from supplier where supID=?";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1,id);

            boolean b = pstm.executeUpdate() > 0;
            return b;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public SupplierDto getsupplier(String updateCustomerId) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            String sql="select * from supplier where supID=?";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1,updateCustomerId);

            ResultSet resultSet = pstm.executeQuery();
            SupplierDto supplierDto = null;
            if (resultSet.next()){
                 supplierDto = new SupplierDto(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)


                );
            }
            return supplierDto;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean updateSupplier(SupplierDto dto) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            String sql="UPDATE supplier SET supID=?,name=?,address=?,email=?,mobile=? WHERE supID=?;";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1,dto.getId());
            pstm.setObject(2,dto.getName());
            pstm.setObject(3,dto.getAddress());
            pstm.setObject(4,dto.getEmail());
            pstm.setObject(5,dto.getMobile());
            pstm.setObject(6,dto.getId());

            boolean b = pstm.executeUpdate() > 0;
            return b;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
