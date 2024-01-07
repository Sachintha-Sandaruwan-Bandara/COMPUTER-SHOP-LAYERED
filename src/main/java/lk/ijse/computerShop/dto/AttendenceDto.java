package lk.ijse.computerShop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

/*
    @author Sachi_S_Bandara
    @created 11/19/2023 - 10:11 PM 
*/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AttendenceDto {
   private String attendenceID;
   private Date date ;
   private Time inTime;
  private Time outTime;
  private int  workingHours ;
   private String empID ;

}
