package lk.ijse.computerShop.model;
/* 
    @author Sachi_S_Bandara
    @created 11/21/2023 - 8:50 PM 
*/

import lk.ijse.computerShop.db.DbConnection;
import lk.ijse.computerShop.dto.SellingOrderDto;
import lk.ijse.computerShop.dto.tm.SellingOrderTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SellingOrderModel {
    public String generateNextOrderId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT soID FROM sellingOrder ORDER BY soID DESC LIMIT 1";
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        String currentOrderId = null;

        if(resultSet.next()) {
            currentOrderId = resultSet.getString(1);
            return splitOrderId(currentOrderId);
        }
        return splitOrderId(null);
    }

    private String splitOrderId(String currentOrderId) {    //O008
        if(currentOrderId != null) {
            String[] split = currentOrderId.split("O");
            int id = Integer.parseInt(split[1]);    //008
            id ++;  //9
            return "O00" + id;
        }
        return "O001";
    }

    public boolean placeSellingOrder(SellingOrderDto sellingOrderDto) throws SQLException {


            boolean result = false;
            Connection connection=null;

            try {
                connection = DbConnection.getInstance().getConnection();
                connection.setAutoCommit(false);

                boolean isPaymentSaved = new PaymentModel().savePayment(sellingOrderDto.orderId, sellingOrderDto.total);

                if (isPaymentSaved) {
                    boolean isOrderSaved= new SellingOrderModel().saveOrder(sellingOrderDto);
                    if(isOrderSaved) {
                        boolean isItemUpdated = updateItems(sellingOrderDto);
                        if (isItemUpdated){
                            boolean isSavedOrderDetails= orderDetails(sellingOrderDto);
                            if (isSavedOrderDetails) {
                                connection.commit();
                                result = true;
                            }
                        }
                    }
                }
            } catch (SQLException e) {
                connection.rollback();
            } finally {
                connection.setAutoCommit(true);
            }
            return result;


    }

    private boolean orderDetails(SellingOrderDto sellingOrderDto) {
        for (SellingOrderTm sellingOrderTm :sellingOrderDto.getTmList()){
            boolean isSavedOderDetail = saveOrderDetails(sellingOrderDto.orderId, sellingOrderTm.getCode(),sellingOrderTm.getQty());

            if (!isSavedOderDetail){
                return false;
            }
        }
        return true;
    }

    private boolean saveOrderDetails(String orderId, String itemCode, int qty) {


           try {
               Connection connection = DbConnection.getInstance().getConnection();
               String sql="insert into sellingorderdetails values (?,?,?)";
               PreparedStatement pstm = connection.prepareStatement(sql);
                pstm.setObject(1,orderId);
                pstm.setObject(2,itemCode);
                pstm.setObject(3,qty);

               boolean b = pstm.executeUpdate() > 0;
               return b;

           } catch (SQLException e) {
               throw new RuntimeException(e);
           }


    }

    private boolean updateItems(SellingOrderDto sellingOrderDto) {
       for (SellingOrderTm sellingOrderTm :sellingOrderDto.getTmList()){
           boolean isUpdated= new ItemModel().updateSellItems(sellingOrderTm);

           if (!isUpdated){
               return false;
           }
       }
        return true;
    }

    private  boolean saveOrder(SellingOrderDto sellingOrderDto) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            String sql="insert into sellingOrder values (?,?,?,?,?)";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1,sellingOrderDto.orderId);
            pstm.setObject(2,sellingOrderDto.orderDate);
            pstm.setObject(3,"u001");
            pstm.setObject(4,sellingOrderDto.customerId);
            pstm.setObject(5,sellingOrderDto.orderId);

            boolean b = pstm.executeUpdate() > 0;

            return b;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
