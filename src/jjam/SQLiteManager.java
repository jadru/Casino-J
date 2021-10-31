package jjam;
import java.sql.*;
public class SQLiteManager{

    String id;
    String nickname;
    String password;
    String sql;
    Connection con = null;

    SQLiteManager(String id,String nickname,String password){
        this.id=id;
        this.nickname=nickname;
        this.password=password;

    }
    boolean idCheck(String iid){
        try{
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:C:src/jjam/userdb");
            Statement stmt = con.createStatement();
            sql="select * from player where id ='"+iid+"'";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()==true){
                return true;
            }
        }
        catch(SQLException | ClassNotFoundException e){
            System.err.println(e.getMessage());
        }
        return false;
    }
    void insert(){

        this.sql="insert into player (id,nickname,password)" ;
        sql+= "VALUES ('"+ id +"','"+ nickname +"','"+ password +"')";
        try{
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:C:src/jjam/userdb");
            Statement statement = con.createStatement();
            statement.execute(sql);

        }
        catch(SQLException | ClassNotFoundException e){
            System.err.println(e.getMessage());
        }
    }
    public static void main(String[]args)throws Exception{

    }
}