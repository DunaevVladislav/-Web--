import dataPack.DataForPage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class ServletVisitors extends HttpServlet {



    public static void getNextStringFromDB(PrintWriter out) throws SQLException{
        ResultSet resSet = DataForPage.statmt.executeQuery("SELECT * FROM users");
        while (resSet.next()){
            out.println("<p>" +resSet.getString("id") + " " + resSet.getString("ip") + " " + resSet.getString("time")+"</p>");
        }
        resSet.close();
    }



    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        try {
            DataForPage.initial();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }


        // установить MIME-type и кодировку ответа
        response.setContentType("text/html; charset=UTF8");
        PrintWriter out = response.getWriter();
        // Отправка веб-страницы
        try {


            out.println("<html>");
            out.println("<head><title>Servlet sample</title></head>");
            out.println("<body>");
            try {
                getNextStringFromDB(out);
            } catch (SQLException e) {
                out.println("asds");
            }
            out.println("<p>Запрошенный ресурс: " + request.getRequestURI() + "</p>");
            out.println("<p>Протокол: " + request.getProtocol() + "</p>");
            out.println("<p>Адрес сервера: " + request.getRemoteAddr() + "</p>");
            out.println("</body></html>");

        } finally {
            out.close();  // Всегда закрывать Writer
        }

        try {
            DataForPage.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
