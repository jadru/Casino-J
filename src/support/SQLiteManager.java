package support;

import java.sql.*;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SQLiteManager {

    private final static String UNSECURED_CHAR_REGULAR_EXPRESSION = "[^\\p{Alnum}]|select|delete|update|insert|create|alter|drop";
    static String SQLURL = "jdbc:sqlite:src/database/userdb.db";
    private final String GET_PLAYER_INFO_CMD = "get_player_info";
    String id;
    String nickname;
    String password;
    String sql;
    int skin;
    int point;
    int win;
    int lose;
    Connection con = null;
    private Pattern unsecuredCharPattern;
    public SQLiteManager(String id, String nickname, String password) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
    }

    public void initialize() {
        unsecuredCharPattern = Pattern.compile(UNSECURED_CHAR_REGULAR_EXPRESSION, Pattern.CASE_INSENSITIVE);
    }

    private String makeSecureString(final String str, int maxLength) {
        String secureStr = str.substring(0, maxLength);
        Matcher matcher = unsecuredCharPattern.matcher(secureStr);
        return matcher.replaceAll("");
    }

    public String getNickname(String id) {
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(SQLURL);
            Statement stmt = con.createStatement();
            sql = "select * from player where id = '" + id + "'";
            ResultSet rs = stmt.executeQuery(sql);
            this.nickname = rs.getString("nickname");
            this.skin = rs.getInt("skin");
            this.point = rs.getInt("point");

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {

            }
        }
        return this.nickname;
    }

    public int getSkin(String id) {
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(SQLURL);
            Statement stmt = con.createStatement();
            sql = "select * from player where id = '" + id + "'";
            ResultSet rs = stmt.executeQuery(sql);
            this.skin = rs.getInt("skin");

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {

            }
        }
        return this.skin;
    }

    public int getPoint(String id) {
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(SQLURL);
            Statement stmt = con.createStatement();
            sql = "select * from player where id = '" + id + "'";
            ResultSet rs = stmt.executeQuery(sql);
            this.point = rs.getInt("point");

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {

            }
        }

        return this.point;
    }

    public int getWin(String id) {
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(SQLURL);
            Statement stmt = con.createStatement();
            sql = "select * from player where id = '" + id + "'";
            ResultSet rs = stmt.executeQuery(sql);
            this.win = rs.getInt("win");

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {

            }
        }

        return this.win;
    }

    public boolean skinCheck(String id, int index) {
        try {
            Class.forName("org.sqlite.JDBC");
            int bcheck;
            con = DriverManager.getConnection(SQLURL);
            Statement stmt = con.createStatement();
            sql = "select * from player where id = '" + id + "'";
            ResultSet rs = stmt.executeQuery(sql);
            if (index == 0) {
                bcheck = rs.getInt("skin0");
                if (bcheck == 1)
                    return true;
            } else if (index == 1) {
                bcheck = rs.getInt("skin1");
                if (bcheck == 1) {
                    return true;
                }
            } else if (index == 2) {
                bcheck = rs.getInt("skin2");
                if (bcheck == 1) {
                    return true;
                }
            } else if (index == 3) {
                bcheck = rs.getInt("skin3");
                if (bcheck == 1) {
                    return true;
                }
            } else if (index == 4) {
                bcheck = rs.getInt("skin4");
                if (bcheck == 1) {
                    return true;
                }
            }


        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {

            }
        }

        return false;
    }

    public int getLose(String id) {
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(SQLURL);
            Statement stmt = con.createStatement();
            sql = "select * from player where id = '" + id + "'";
            ResultSet rs = stmt.executeQuery(sql);
            this.lose = rs.getInt("lose");

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {

            }
        }

        return this.lose;
    }

    public boolean login(String iid, String ppw) {
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(SQLURL);
            if (con != null) {
                System.out.println(" DB Connect Succese");
            } else {
                System.out.println(" DB Connect Fail!");
                return false;
            }
            initialize();
            Statement stmt = con.createStatement();
            sql = "select * from player where id ='" + makeSecureString(iid, iid.length()) + "' " + "and password = '" + makeSecureString(ppw, ppw.length()) + "'";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next() == true) {
                return true;
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found Exception");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (Exception e) {

            }
        }
        return false;
    }

    public boolean idCheck(String iid) {
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(SQLURL);
            Statement stmt = con.createStatement();
            sql = "select * from player where id ='" + iid + "'";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next() == true) {
                return true;
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {

            }
        }
        return false;
    }

    public void insert() {

        this.sql = "insert into player (id,nickname,password)";
        sql += "VALUES ('" + id + "','" + nickname + "','" + password + "')";
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(SQLURL);
            Statement stmt = con.createStatement();
            stmt.execute(sql);

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {

            }
        }
    }

    public void givePoint(String id, int point) {
        int apoint = getPoint(id);
        apoint += point;
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(SQLURL);
            Statement stmt = con.createStatement();
            stmt.execute("update player set point = " + apoint + " where id = '" + id + "'");
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {

            }
        }

    }

    public void giveRecord(String id, int win, int lose, int point) {
        int apoint = getPoint(id);
        int wwin = getWin(id);
        int llose = getLose(id);
        apoint += point;
        wwin += win;
        llose += lose;
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(SQLURL);
            Statement stmt = con.createStatement();
            stmt.execute("update player set win = " + wwin + " where id = '" + id + "'");
            stmt.execute("update player set lose = " + llose + " where id = '" + id + "'");
            stmt.execute("update player set point = " + apoint + " where id = '" + id + "'");
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {

            }
        }

    }

    public void SkinChange(String id, int index) {
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(SQLURL);
            Statement stmt = con.createStatement();
            if (index == 0) {
                stmt.execute("update player set skin = " + index + " where id = '" + id + "'");
            } else if (index == 1) {
                stmt.execute("update player set skin = " + index + " where id = '" + id + "'");
            } else if (index == 2) {
                stmt.execute("update player set skin = " + index + " where id = '" + id + "'");
            } else if (index == 3) {
                stmt.execute("update player set skin = " + index + " where id = '" + id + "'");
            } else if (index == 4) {
                stmt.execute("update player set skin = " + index + " where id = '" + id + "'");
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {

            }
        }
    }

    public void buySkin(String id, int index) {
        try {
            int apoint = getPoint(id);
            apoint -= 1000;
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(SQLURL);
            Statement stmt = con.createStatement();
            PreparedStatement psmt = null;
            if (index == 1) {
                stmt.execute("update player set skin1 = 1" + " where id = '" + id + "'");
            } else if (index == 2) {
                stmt.execute("update player set skin2 = 1" + " where id = '" + id + "'");
            } else if (index == 3) {
                stmt.execute("update player set skin3 = 1" + " where id = '" + id + "'");
            } else if (index == 4) {
                stmt.execute("update player set skin4 = 1" + " where id = '" + id + "'");
            } else {

            }
            stmt.execute("update player set point = " + apoint + " where id = '" + id + "'");
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {

            }
        }
    }

    public Vector<String> getrank() {
        Vector<String> vec = new Vector<String>();
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(SQLURL);
            Statement stmt = con.createStatement();
            sql = "select * from player order by point DESC";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                vec.add(rs.getString("nickname"));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (Exception e) {

            }
        }

        return vec;
    }
}