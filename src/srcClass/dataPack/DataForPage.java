package dataPack;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataForPage{

    public static String path = DataForPage.class.getProtectionDomain().getCodeSource().getLocation().getPath().replaceFirst("%20", " ") + "../TEST1.db";

    public static Connection conn;
    public static Statement statmt;

    public static void initial() throws SQLException, ClassNotFoundException{
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite://" + path);
        statmt = conn.createStatement();
        statmt.execute("CREATE TABLE if not exists 'users' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'ip' text, 'time' text);");
    }

    public static void insert(String ip) throws SQLException{
        statmt.execute("INSERT INTO 'users' ('ip', 'time') VALUES ('" + ip +"', '" + (new Date()).toString() +"')");
    }


    public static int getCountUsers() throws SQLException{
        ResultSet resSet = statmt.executeQuery("SELECT COUNT(*) FROM users");
        if (resSet.next())  return resSet.getInt(1);
        else return 0;

    }


    public static String getDate(){
        Date dateNow = new Date();
        SimpleDateFormat formatForDate = new SimpleDateFormat("Текущая дата E dd.MM.yyyy '. текущее время ' hh:mm:ss zzz");
        return formatForDate.format(dateNow);
    }

    public static void close() throws SQLException{
        conn.close();
        statmt.close();
    }


}
