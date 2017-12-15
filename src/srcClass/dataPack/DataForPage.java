package dataPack;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Данные для страницы(время, пользователи)
 */
public class DataForPage{

    /**
     * путь к директории сервера
     */
    private static String path = DataForPage.class.getProtectionDomain().getCodeSource().getLocation().getPath().replaceFirst("%20", " ") + "../../";
    public static String getPath() {
        return path;
    }

    /**
     * Соединение с базой данных
     */
    private Connection conn;
    /**
     *запрос к базе данных
     */
    public Statement statmt;

    /**
     * конструтор
     * подключение к базе данных
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public DataForPage() throws SQLException, ClassNotFoundException{
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite://" + path + "/WEB-INF/database.db");
        statmt = conn.createStatement();
        statmt.execute("CREATE TABLE if not exists 'users' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'ip' text, 'time' text);");
    }

    /**
     * вставляет нового пользователя по его ip
     * @param ip
     * @throws SQLException
     */
    public  void insert(String ip) throws SQLException{
        Date date = new Date();
        statmt.execute("INSERT INTO 'users' ('ip', 'time') VALUES ('" + ip +"', '" + date.toString() +"')");
    }

    /**
     * @return количество пользователей
     * @throws SQLException
     */
    public int getCountUsers() throws SQLException{
        ResultSet resSet = statmt.executeQuery("SELECT COUNT(*) FROM users");
        if (resSet.next())  return resSet.getInt(1);
        else return 0;
    }

    /**
     * @return текущую дату
     */
    public String getDate(){
        Date dateNow = new Date();
        SimpleDateFormat formatForDate = new SimpleDateFormat("Текущая дата E dd.MM.yyyy '. Tекущее время ' HH:mm:ss zzz");
        return formatForDate.format(dateNow);
    }

    /**
     * деструктор
     * отключается от базы данных
     * @throws SQLException
     */
    @Override
    public void finalize() throws SQLException{
        try{
            conn.close();
            statmt.close();
        } catch (SQLException ignored){}
    }


}
