package Dao;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class CommonDao {

    public static ResultSet get(String qry){
        ResultSet rslt = null;

        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/phone_book","us2","1234");
            Statement stm = conn.createStatement();
            rslt = stm.executeQuery(qry);
        }catch (SQLException e){
            System.out.println("Can't get Result as :"+e.getMessage());
        }
        return rslt;
    }


    public static String modify(String qry) {

        String msg = "0";
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/phone_book","us2","1234");
            Statement stm = conn.createStatement();
            int rows = stm.executeUpdate(qry);

            if (rows != 0)
                msg = "1";

        } catch (SQLException e) {
            System.out.println("Can't Get Results as : " + e.getMessage());
            msg = "dberror";

        }
        return msg;

    }


}
