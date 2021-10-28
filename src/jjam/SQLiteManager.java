package jjam;
import java.sql.*;
public class SQLiteManager{
    public static void main(String[]args)throws Exception{

        Connection connection = null;

        try{
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Project\\Casino-J\\src\\jjam\\userdb");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from users");
            while(rs.next()){
                System.out.print("id = " + rs.getInt("id") + " ");
                System.out.print("nickname = " + rs.getString("nickname") + " ");
                System.out.println("password = "+ rs.getString("password"));
            }
            rs.close();
            connection.close();

        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }
}