package lk.ijse.computerShop.model;
/* 
    @author Sachi_S_Bandara
    @created 11/19/2023 - 10:11 PM 
*/

import lk.ijse.computerShop.db.DbConnection;
import lk.ijse.computerShop.dto.AttendenceDto;

import java.sql.*;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AttendenceModel {
    public boolean saveAttendence(AttendenceDto attendenceDto) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            String sql="insert into attendence values (?,?,?,?,?,?)";
            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setObject(1,attendenceDto.getAttendenceID());
            pstm.setObject(2,attendenceDto.getDate());
            pstm.setObject(3,attendenceDto.getInTime());
            pstm.setObject(4,attendenceDto.getOutTime());
            pstm.setObject(5,attendenceDto.getWorkingHours());
            pstm.setObject(6,attendenceDto.getEmpID());

            boolean b = pstm.executeUpdate() > 0;
            return b;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public String genarateId() {  try {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT attendenceID FROM attendence ORDER BY attendenceID DESC LIMIT 1";
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        String currentAttendenceId = null;

        if(resultSet.next()) {
            currentAttendenceId= resultSet.getString(1);
            return splitOrderId(currentAttendenceId);
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




    public boolean updateOutTime(String text, Date valueOf, Time valueOf1) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            String sql="update ATTENDENCE set outTime=? where EMPID=?&& DATE=?";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1,valueOf1);
            pstm.setObject(2,text);
            pstm.setObject(3,valueOf);

            boolean b = pstm.executeUpdate() > 0;
            return b;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public ArrayList<AttendenceDto> getAllAttendence() {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            String sql="select * from attendence";
            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet resultSet = pstm.executeQuery();

            ArrayList<AttendenceDto> attendenceList = new ArrayList<>();
            while (resultSet.next()){
                AttendenceDto attendenceDto = new AttendenceDto(
                        resultSet.getString(1),
                        resultSet.getDate(2),
                        resultSet.getTime(3),
                        resultSet.getTime(4),
                        resultSet.getInt(5),
                        resultSet.getString(6)

                );
                attendenceList.add(attendenceDto);

            }
            return attendenceList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean checkIsAdmited(String text, Date valueOf) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            String sql="select empID from attendence where date =? && empID=?";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1,valueOf);
            pstm.setObject(2,text);

            ResultSet resultSet = pstm.executeQuery();

            if (resultSet.next()){
              return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void setWorkingHours(String empId, Date date) {
        String timeIn=null;
        String  timeOut=null;
        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            String query = "SELECT inTime, outTime FROM attendence WHERE date = ? && empID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, String.valueOf(date));
            statement.setString(2, empId);

            try (
                    ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    timeIn = resultSet.getString("inTime");
                    timeOut = resultSet.getString("outTime");

                    System.out.println("Time In: " + timeIn + ", Time Out: " + timeOut);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        calculate(timeIn,timeOut,empId,date);

    }

    private void calculate(String timeIn, String timeOut, String empId, Date date) {

        System.out.println("awaa mn aawaaaaa");
        try {
            String oldTimeText = timeIn;
            String newTimeText = timeOut;

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

            // Parse time strings to LocalTime
            LocalTime oldTime = LocalTime.parse(oldTimeText, formatter);
            LocalTime newTime = LocalTime.parse(newTimeText, formatter);

            // Calculate time difference
            Duration duration = Duration.between(oldTime, newTime);
            long hours = duration.toHours();

            int working= (int) hours;

            Connection connection = DbConnection.getInstance().getConnection();
            String sql="update attendence set workingHours=? where empID=? && date=?";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1,working);
            pstm.setObject(2,empId);
            pstm.setObject(3,date);

            boolean b = pstm.executeUpdate() > 0;

            if (b){
                System.out.println("working hours added");
            }else{
                System.out.println("working hours not added");
            }


            System.out.println("Time Difference: " + hours + " hours");
        } catch (Exception e) {

        }
    }

}

