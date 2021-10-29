package jjam;
import java.sql.*;
public class SQLiteManager{

    int id;
    String nickname;
    String password;
    String sql;
    Connection con = null;

    SQLiteManager(int id,String nickname,String password){
        this.id=id;
        this.nickname=nickname;
        this.password=password;

    }


    void insert(){

        this.sql="insert into users (id,nickname,password)" ;
        sql+= "VALUES ("+ id +",'"+ nickname +"','"+ password +"')";
        try{
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:src/jjam/userdb");
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