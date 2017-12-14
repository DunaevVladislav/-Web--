import dataPack.DataForPage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class ServletVisitors extends HttpServlet {


    private DataForPage data;

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

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        // установить MIME-type и кодировку ответа
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
