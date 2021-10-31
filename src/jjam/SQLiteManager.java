package jjam;
import java.sql.*;
public class SQLiteManager{

    String id;
    String nickname;
    String password;
    String sql;
    int level;
    int skin;
    int point;
    Connection con = null;

    public SQLiteManager(String id, String nickname, String password){
        this.id=id;
        this.nickname=nickname;
        this.password=password;

    }
    public String getNickname(String id){
        try{
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:src/jjam/userdb.db");
            Statement stmt = con.createStatement();
            sql="select * from player where id = '"+id+"'";
            ResultSet rs = stmt.executeQuery(sql);
            this.nickname = rs.getString("nickname");
            this.level = rs.getInt("level");
            this.skin = rs.getInt("skin");
            this.point = rs.getInt("point");
        }
        catch(SQLException | ClassNotFoundException e){
            System.err.println(e.getMessage());
        }
        return this.nickname;
    }
    public int getLevel(String id){
        try{
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:src/jjam/userdb.db");
            Statement stmt = con.createStatement();
            sql="select * from player where id = '"+id+"'";
            ResultSet rs = stmt.executeQuery(sql);
            this.level = rs.getInt("level");
        }
        catch(SQLException | ClassNotFoundException e){
            System.err.println(e.getMessage());
        }
        return this.level;
    }
    public int getSkin(String id){
        try{
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:src/jjam/userdb.db");
            Statement stmt = con.createStatement();
            sql="select * from player where id = '"+id+"'";
            ResultSet rs = stmt.executeQuery(sql);
            this.skin = rs.getInt("skin");
        }
        catch(SQLException | ClassNotFoundException e){
            System.err.println(e.getMessage());
        }
        return this.skin;
    }
    public int getPoint(String id){
        try{
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:src/jjam/userdb.db");
            Statement stmt = con.createStatement();
            sql="select * from player where id = '"+id+"'";
            ResultSet rs = stmt.executeQuery(sql);
            this.point = rs.getInt("point");
        }
        catch(SQLException | ClassNotFoundException e){
            System.err.println(e.getMessage());
        }
        return this.point;
    }


    boolean login(String iid,String ppw){
        try{
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:src/jjam/userdb.db");
            Statement stmt = con.createStatement();
            sql="select * from player where id ='"+iid+"' "+"and password = '"+ppw+"'";
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
    boolean idCheck(String iid){
        try{
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:src/jjam/userdb.db");
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
            con = DriverManager.getConnection("jdbc:sqlite:src/jjam/userdb.db");
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