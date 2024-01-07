package lk.ijse.computerShop.model;
/* 
    @author Sachi_S_Bandara
    @created 11/19/2023 - 3:44 PM 
*/

import lk.ijse.computerShop.db.DbConnection;
import lk.ijse.computerShop.dto.ItemDto;
import lk.ijse.computerShop.dto.tm.SellingOrderTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemModel {
    public boolean saveItem(ItemDto itemDto) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            String sql="insert into item values (?,?,?,?,?,?)";
            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setObject(1,itemDto.getItemID());
            pstm.setObject(2,itemDto.getName());
            pstm.setObject(3,itemDto.getBuyingPrice());
            pstm.setObject(4,itemDto.getSellingPrice());
            pstm.setObject(5,itemDto.getQty());
            pstm.setObject(6,itemDto.getImageBytes());


            boolean b = pstm.executeUpdate() > 0;
            return b;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<ItemDto> getAllItems() {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            String sql="select * from item";
            PreparedStatement pstm = connection.prepareStatement(sql);

            ArrayList<ItemDto> dtoList = new ArrayList<>();

            ResultSet resultSet = pstm.executeQuery();

            while (resultSet.next()){
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                int buyingPrice = resultSet.getInt(3);
                int sellingPrice = resultSet.getInt(4);
                int qty = resultSet.getInt(5);
                byte[] bytes = resultSet.getBytes(6);

                ItemDto itemDto= new ItemDto(id, name, buyingPrice, sellingPrice, qty,bytes);
                dtoList.add(itemDto);
            }
            return dtoList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteItem(String id) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            String sql="delete from item where itemID=?";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1,id);

            boolean b = pstm.executeUpdate() > 0;
            return b;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean updateSellItems(SellingOrderTm sellingOrderTm) {
        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();

            String sql="update item set qty=qty-? where itemID=?";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1,sellingOrderTm.getQty());
            pstm.setObject(2,sellingOrderTm.getCode());

            boolean b = pstm.executeUpdate() > 0;
            return b;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}
