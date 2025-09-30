package murach.sqlgateway;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class UserAdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String refresh = request.getParameter("refresh");
        if (refresh != null) {
            List<User> users = UserDB.getAllUsers();
            request.setAttribute("users", users);
        }

        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        switch (action) {
            case "add":
                // Kiểm tra email đã tồn tại chưa
                List<User> users = UserDB.getAllUsers();
                boolean exists = users.stream().anyMatch(u -> u.getEmail().equalsIgnoreCase(email));

                if (exists) {
                    // Email trùng → show lỗi trên index.jsp
                    request.setAttribute("error", "This email address already exists. Please enter another email address.");
                    request.setAttribute("email", email);
                    request.setAttribute("firstName", firstName);
                    request.setAttribute("lastName", lastName);
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                    return;
                }

                // Nếu chưa tồn tại → thêm và redirect thanks.jsp
                User newUser = new User(email, firstName, lastName);
                UserDB.insert(newUser);
                request.getSession().setAttribute("user", newUser);
                response.sendRedirect("thanks.jsp");
                return;

            case "update":
                User updatedUser = new User(email, firstName, lastName);
                UserDB.update(updatedUser);
                response.sendRedirect("users?refresh=1");
                return;

            case "delete":
                UserDB.delete(email);
                response.sendRedirect("users?refresh=1");
                return;

            default:
                response.sendRedirect("users?refresh=1");
        }
    }
}
