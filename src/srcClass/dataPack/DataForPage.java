package dataPack;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataForPage{

    private static String path = DataForPage.class.getProtectionDomain().getCodeSource().getLocation().getPath().replaceFirst("%20", " ") + "../../";
    public static String getPath() {
        return path;
    }

    private Connection conn;
    public Statement statmt;

    public DataForPage() throws SQLException, ClassNotFoundException{
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite://" + path + "/WEB-INF/database.db");
        statmt = conn.createStatement();
        statmt.execute("CREATE TABLE if not exists 'users' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'ip' text, 'time' text);");
    }

    public  void insert(String ip) throws SQLException{
        Date date = new Date();
        statmt.execute("INSERT INTO 'users' ('ip', 'time') VALUES ('" + ip +"', '" + date.toString() +"')");
    }


    public int getCountUsers() throws SQLException{
        ResultSet resSet = statmt.executeQuery("SELECT COUNT(*) FROM users");
        if (resSet.next())  return resSet.getInt(1);
        else return 0;
    }


    public String getDate(){
        Date dateNow = new Date();
        SimpleDateFormat formatForDate = new SimpleDateFormat("Текущая дата E dd.MM.yyyy '. Tекущее время ' HH:mm:ss zzz");
        return formatForDate.format(dateNow);
    }

    public void finalize() throws SQLException{
        conn.close();
        statmt.close();
    }


}
