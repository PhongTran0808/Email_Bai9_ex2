package murach.sqlgateway;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

public class SQLServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String sql = request.getParameter("sql");
        out.println("<h2>SQL result:</h2>");

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement()) {

            boolean hasResultSet = stmt.execute(sql);

            if (hasResultSet) {
                ResultSet rs = stmt.getResultSet();
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();

                out.println("<table border='1'>");
                out.println("<tr>");
                for (int i = 1; i <= columnCount; i++) {
                    out.println("<th>" + rsmd.getColumnName(i) + "</th>");
                }
                out.println("</tr>");

                while (rs.next()) {
                    out.println("<tr>");
                    for (int i = 1; i <= columnCount; i++) {
                        out.println("<td>" + rs.getString(i) + "</td>");
                    }
                    out.println("</tr>");
                }
                out.println("</table>");
            } else {
                int updateCount = stmt.getUpdateCount();
                out.println("<p>Update Count: " + updateCount + "</p>");
            }

        } catch (Exception e) {
            out.println("<p style='color:red;'>Error: " + e.getMessage() + "</p>");
        }
    }
}
