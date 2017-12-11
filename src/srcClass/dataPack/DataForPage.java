package dataPack;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.*;

public class DataForPage{

    public static final String path = System.getenv("CATALINA_HOME") + "/webapps/TeamPage/";
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
      //  if (true) return DataForPage.class.getProtectionDomain().getCodeSource().getLocation().getFile()
        conn = null;
     //   if (true) return
        Class.forName("org.sqlite.JDBC");
        try{
            conn = DriverManager.getConnection("jdbc:sqlite://" + DataForPage.class.getProtectionDomain().getCodeSource().getLocation().getPath().replaceAll("%20", " ") + "TEST1.db", "root", "");
        } catch(SQLException sqlEx) {
            return "jdbc:sqlite:" + DataForPage.class.getProtectionDomain().getCodeSource().getLocation().getPath().replaceFirst("%20", " ") + "../TEST1.db";
        };
        statmt = conn.createStatement();
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
