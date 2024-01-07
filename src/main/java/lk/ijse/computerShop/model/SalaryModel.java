package lk.ijse.computerShop.model;
/* 
    @author Sachi_S_Bandara
    @created 11/21/2023 - 11:08 AM 
*/

import lk.ijse.computerShop.db.DbConnection;
import lk.ijse.computerShop.dto.SalaryDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SalaryModel {
    public String getEmpWorkingHours(String empID) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            String sql = "SELECT SUM(workingHours) as totalHours FROM attendence WHERE empID=?";

            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setObject(1, empID);

            ResultSet resultSet = pstm.executeQuery();
            String workingHours = null;
            if (resultSet.next()) {
               // System.out.println(resultSet.getString("totalHours"));
                workingHours=resultSet.getString("totalHours");
            }
            return workingHours;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public String generateSalaryId() {

        try {
            Connection connection = DbConnection.getInstance().getConnection();
            String sql="SELECT salaryID FROM salary ORDER BY salaryID DESC LIMIT 1";
            ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

            String currentSalaryId = null;

            if(resultSet.next()) {
                currentSalaryId= resultSet.getString(1);
                return splitOrderId(currentSalaryId);
            }
            return splitOrderId(null);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String splitOrderId(String currentSalaryId) {
        if (currentSalaryId != null) {
            String[] split = currentSalaryId.split("s");
            int id = Integer.parseInt(split[1]); // Extract numeric part
            id++; // Increment
            return String.format("s%03d", id); // Format with leading zeros
        }
        return "s001"; // Default value if currentSalaryId is null
    }

    public boolean saveSalaryPayment(SalaryDto salaryDto) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            String sql="insert into salary values (?,?,?,?)";
            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setObject(1,salaryDto.getSalaryID());
            pstm.setObject(2,salaryDto.getAmount());
            pstm.setObject(3,salaryDto.getDate());
            pstm.setObject(4,salaryDto.getEmpID());

            boolean b = pstm.executeUpdate() > 0;
            return b;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
