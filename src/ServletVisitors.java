import dataPack.DataForPage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * сервлет страницы пользователей
 */
public class ServletVisitors extends HttpServlet {

    /**
     * данные страницы
     */
    private DataForPage data;

    /**
     * выводит html-таблицу с пользователями
     * @param out - объект PrintWriter, для вывода в html
     * @throws SQLException
     */
    public void outUsersTable(PrintWriter out) throws SQLException{
        ResultSet resSet = data.statmt.executeQuery("SELECT * FROM users");
        out.println("  <table border='1'>\n" +
                "   <caption><p id='title1'>Посетители сайта</p></caption>\n" +
                "   <tr>\n" +
                "    <th>id</th>\n" +
                "    <th>ip посетителя</th>\n" +
                "    <th>время посещения</th>\n" +
                "   </tr>");
        while (resSet.next()){
            out.println("<tr><td>" + resSet.getString("id") + "</td><td>" + resSet.getString("ip") + "</td><td>" + resSet.getString("time") + "</td></tr>");
        }
        out.println("</table>");
        resSet.close();
    }

    /**
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        response.setContentType("text/html; charset=UTF8");
        PrintWriter out = response.getWriter();

        try {
            data = new DataForPage();
        } catch (SQLException|ClassNotFoundException e) {
            out.println("<p>Не удалось подключиться к базе данных</p>");
            out.close();
            return;
        }

        try {
            out.println("<html>");
            out.println("<head>" +
                            "<title>Посетители сайта</title>" +
                            "<meta charset='utf-8'>\n" +
                            "<link rel='stylesheet' type='text/css' href='css/index.css'/>\n" +
                        "</head>");
            out.println("<body>");
            out.println("<div id='tableDiv'>");
            outUsersTable(out);
            out.println("</div>\n" +
                    "</body>\n" +
                    "</html>");

        } catch (SQLException e){
            out.println("<p>Что-то пошло не так</p>");
        } finally {
            out.close();
        }

        try {
            data.finalize();
        } catch (SQLException ignored){};
    }
}
