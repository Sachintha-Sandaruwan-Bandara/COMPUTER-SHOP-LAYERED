package lk.ijse.computerShop.model;
/* 
    @author Sachi_S_Bandara
    @created 11/30/2023 - 5:46 PM 
*/

import lk.ijse.computerShop.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaymentModel {
    public boolean savePayment(String id, double total) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            String sql="insert into payment values (?,?)";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1,id);
            pstm.setObject(2,total);

            boolean b = pstm.executeUpdate() > 0;
            return b;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
