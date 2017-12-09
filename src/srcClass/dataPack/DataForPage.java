package dataPack;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.*;

public class DataForPage{

    //private static final String path = System.getenv("CATALINA_HOME") + "/webapps/TeamPage/";
/*
    static {
        File checkExist = new File(path + "WEB-INF/Data/cntVisitors.txt");
        try {
            if (!checkExist.exists()) {
                checkExist.createNewFile();
                RandomAccessFile f = new RandomAccessFile(path + "WEB-INF/Data/cntVisitors.txt", "w");
                f.writeInt(0);
                f.close();
            }
            RandomAccessFile file = new RandomAccessFile(path + "WEB-INF/Data/cntVisitors.txt", "rw");
            Integer cntVisitors = file.readInt();
            cntVisitors++;
            file.writeInt(cntVisitors);
            file.close();
        } catch (IOException ignored){};
    }*/

    public static Connection conn;
    public static Statement statmt;
    public static ResultSet resSet;

    public static String Conn() throws ClassNotFoundException, SQLException
    {
        conn = null;
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:TEST1.s3db");statmt = conn.createStatement();
        statmt.execute("CREATE TABLE if not exists 'users' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text, 'phone' INT);");
        statmt.execute("INSERT INTO 'users' ('name', 'phone') VALUES ('Petya', 125453); ");
        statmt.execute("INSERT INTO 'users' ('name', 'phone') VALUES ('Vasya', 321789); ");
        statmt.execute("INSERT INTO 'users' ('name', 'phone') VALUES ('Masha', 456123); ");
        resSet = statmt.executeQuery("SELECT * FROM users");
        if(resSet.next())
        {
            String  name = resSet.getString("name");
            conn.close();
            statmt.close();
            resSet.close();
            return "name = " + name;
        }else {
            conn.close();
            statmt.close();
            resSet.close();
            return "bad";
        }
    }

    public static String ReadDB() throws ClassNotFoundException, SQLException
    {
        Conn();
        if (conn == null) return "Error";
        resSet = statmt.executeQuery("SELECT * FROM users");

        if(resSet.next())
        {
            int id = resSet.getInt("id");
            conn.close();
            statmt.close();
            resSet.close();
            return "ID = " + id ;
        }else {
            conn.close();
            statmt.close();
            resSet.close();
            return "bad";
        }
    }

    public static String getDate(){
        Date dateNow = new Date();
        SimpleDateFormat formatForDate = new SimpleDateFormat("Текущая дата E dd.MM.yyyy '. текущее время ' hh:mm:ss zzz");
        return formatForDate.format(dateNow);
    }

    public static Integer getCntVisits() {

       /*try {
            RandomAccessFile file= new RandomAccessFile(path + "WEB-INF/Data/cntVisitors.txt", "r");
            Integer cntVisitots = Integer.parseInt(file.readLine());
            file.close();
            return cntVisitots;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }*/
       return -1;
    }

}
