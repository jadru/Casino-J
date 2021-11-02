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
    int win;
    int lose;
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
        finally {
            try{
                con.close();
            }
            catch(Exception e){

            }
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
        finally {
            try{
                con.close();
            }
            catch(Exception e){

            }
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
        finally {
            try{
                con.close();
            }
            catch(Exception e){

            }
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
        finally {
            try{
                con.close();
            }
            catch(Exception e){

            }
        }

        return this.point;
    }
    public int getWin(String id){
        try{
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:src/jjam/userdb.db");
            Statement stmt = con.createStatement();
            sql="select * from player where id = '"+id+"'";
            ResultSet rs = stmt.executeQuery(sql);
            this.point = rs.getInt("win");

        }
        catch(SQLException | ClassNotFoundException e){
            System.err.println(e.getMessage());
        }
        finally {
            try{
                con.close();
            }
            catch(Exception e){

            }
        }

        return this.win;
    }
    public int getLose(String id){
        try{
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:src/jjam/userdb.db");
            Statement stmt = con.createStatement();
            sql="select * from player where id = '"+id+"'";
            ResultSet rs = stmt.executeQuery(sql);
            this.point = rs.getInt("lose");

        }
        catch(SQLException | ClassNotFoundException e){
            System.err.println(e.getMessage());
        }
        finally {
            try{
                con.close();
            }
            catch(Exception e){

            }
        }

        return this.lose;
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
        finally {
            try{
                con.close();
            }
            catch(Exception e){

            }
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
        finally {
            try{
                con.close();
            }
            catch(Exception e){

            }
        }
        return false;
    }
    void insert(){

        this.sql="insert into player (id,nickname,password)" ;
        sql+= "VALUES ('"+ id +"','"+ nickname +"','"+ password +"')";
        try{
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:src/jjam/userdb.db");
            Statement stmt = con.createStatement();
            stmt.execute(sql);

        }
        catch(SQLException | ClassNotFoundException e){
            System.err.println(e.getMessage());
        }
        finally {
            try{
                con.close();
            }
            catch(Exception e){

            }
        }
    }
    void givePoint(String id){
        int point = getPoint(id);
        point+=10;
        try{
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:src/jjam/userdb.db");
            Statement stmt = con.createStatement();
            stmt.execute("update player set point = "+point+" where id = '"+id+"'");
        }
        catch(SQLException | ClassNotFoundException e){
            System.err.println(e.getMessage());
        }
        finally {
            try{
                con.close();
            }
            catch(Exception e){

            }
        }

    }
    public static void main(String[]args)throws Exception{

    }
}