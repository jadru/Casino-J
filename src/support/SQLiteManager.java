package support;
import java.sql.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    static String SQLURL = "jdbc:sqlite:src/database/userdb.db";

    public SQLiteManager(String id, String nickname, String password){
        this.id=id;
        this.nickname=nickname;
        this.password=password;
    }
    private final String GET_PLAYER_INFO_CMD="get_player_info";


    private final static String UNSECURED_CHAR_REGULAR_EXPRESSION="[^\\p{Alnum}]|select|delete|update|insert|create|alter|drop";
    private Pattern unsecuredCharPattern;
    public void initialize(){
        unsecuredCharPattern= Pattern.compile(UNSECURED_CHAR_REGULAR_EXPRESSION,Pattern.CASE_INSENSITIVE);
    }
    private String makeSecureString(final String str,int maxLength){
        String secureStr = str.substring(0,maxLength);
        Matcher matcher = unsecuredCharPattern.matcher(secureStr);
        return matcher.replaceAll("");
    }
    public String getNickname(String id){
        try{
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(SQLURL);
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
            con = DriverManager.getConnection(SQLURL);
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
            con = DriverManager.getConnection(SQLURL);
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
            con = DriverManager.getConnection(SQLURL);
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
            con = DriverManager.getConnection(SQLURL);
            Statement stmt = con.createStatement();
            sql="select * from player where id = '"+id+"'";
            ResultSet rs = stmt.executeQuery(sql);
            this.win = rs.getInt("win");

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
            con = DriverManager.getConnection(SQLURL);
            Statement stmt = con.createStatement();
            sql="select * from player where id = '"+id+"'";
            ResultSet rs = stmt.executeQuery(sql);
            this.lose = rs.getInt("lose");

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

    public boolean login(String iid, String ppw){
        try{
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(SQLURL);
            if (con != null){
                System.out.println(" DB Connect Succese");
            } else {
                System.out.println(" DB Connect Fail!");
                return false;
            }
            initialize();
            Statement stmt = con.createStatement();
            sql="select * from player where id ='"+makeSecureString(iid,iid.length())+"' "+"and password = '"+makeSecureString(ppw,ppw.length())+"'";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()==true){
                return true;
            }

        }
        catch(ClassNotFoundException e){
            System.out.println("Class Not Found Exception");
            e.printStackTrace();
        }
        catch (SQLException e){
            e.printStackTrace();
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
    public boolean idCheck(String iid){
        try{
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(SQLURL);
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
   public void insert(){

        this.sql="insert into player (id,nickname,password)" ;
        sql+= "VALUES ('"+ id +"','"+ nickname +"','"+ password +"')";
        try{
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(SQLURL);
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
    public void givePoint(String id,int point){
        int apoint = getPoint(id);
        apoint += point;
        try{
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(SQLURL);
            Statement stmt = con.createStatement();
            stmt.execute("update player set point = "+apoint+" where id = '"+id+"'");
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
    public Vector<String> getrank(){
        Vector<String> vec = new Vector<String>();
        try{
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(SQLURL);
            Statement stmt = con.createStatement();
            sql="select * from player order by point DESC";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                vec.add(rs.getString("id"));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        finally {
            try{
                con.close();
            }
            catch(Exception e){

            }
        }

        return vec;
    }

    /*public ArrayList<ResultSet> getOrderByDescPoint(){
        Map<Integer, Object> map = new HashMap<Integer, Object>();
        try{
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:src/jjam/userdb.db");
            Statement stmt = con.createStatement();
            sql="select * from player";
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while(rs.next()) {
                for(int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    map.put(rs.getInt("point"), rs);

                }
                System.out.println(rs.getString("id"));
            }

            Object[] mapkey = map.keySet().toArray();
            Arrays.sort(mapkey);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        finally {
            try{
                con.close();
            }
            catch(Exception e){

            }
        }
        return new ArrayList(map.values());
    }*/
}