package lk.ijse.computerShop.model;
/* 
    @author Sachi_S_Bandara
    @created 12/1/2023 - 10:43 AM 
*/

import lk.ijse.computerShop.db.DbConnection;
import lk.ijse.computerShop.dto.UserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel {
    public UserDto getUser(String uID) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            String sql="select * from user where userID=?";
            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setObject(1,uID);

            ResultSet resultSet = pstm.executeQuery();

            if (resultSet.next()){
                return new UserDto(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),resultSet.getString(4));
            }else {
                System.out.println("user not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
