<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="murach.sqlgateway.User" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>User Admin</title>
    <style>
        table { border-collapse: collapse; width: 70%; margin-top: 20px; }
        th, td { border: 1px solid black; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        h1 { color: teal; }
        form { margin-top: 20px; }
    </style>
</head>
<body>
<h1>Users</h1>

<%
    String error = (String) request.getAttribute("error");
    if (error != null) {
%>
    <p style="color:red;"><%= error %></p>
<%
    }
%>

<%
    List<User> users = (List<User>) request.getAttribute("users");
    if (users == null || users.isEmpty()) {
%>
    <p style="color:red;">⚠ No users found in the database.</p>
<%
    } else {
%>
    <table>
        <tr>
            <th>Email</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Actions</th>
        </tr>
        <%
            for (User u : users) {
        %>
        <tr>
            <td><%= u.getEmail() %></td>
            <td><%= u.getFirstName() %></td>
            <td><%= u.getLastName() %></td>
            <td>
                <!-- Edit -->
                <form action="thanks.jsp" method="get" style="display:inline;">
                    <input type="hidden" name="email" value="<%= u.getEmail() %>">
                    <input type="hidden" name="firstName" value="<%= u.getFirstName() %>">
                    <input type="hidden" name="lastName" value="<%= u.getLastName() %>">
                    <input type="submit" value="Update">
                </form> |
                <!-- Delete -->
                <form action="users" method="post" style="display:inline;" 
                      onsubmit="return confirm('Are you sure you want to delete this user?');">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="email" value="<%= u.getEmail() %>">
                    <input type="submit" value="Delete">
                </form>
            </td>
        </tr>
        <%
            }
        %>
    </table>
<%
    }
%>
<!-- Refresh link -->
<p><a href="users?refresh=1">Refresh</a></p>
</body>
</html>