<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Update User</title>
    <style>
        body { font-family: Arial, sans-serif; }
        h1 { color: teal; }
        form { margin-top: 20px; }
        table { border-collapse: collapse; }
        td { padding: 6px 10px; }
        td:first-child { text-align: right; font-weight: bold; }
        input[type="text"] {
            width: 250px;
            padding: 5px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        input[type="submit"] {
            margin-top: 10px;
            padding: 6px 12px;
            border: none;
            border-radius: 4px;
            background-color: teal;
            color: white;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: darkcyan;
        }
    </style>
</head>
<body>
<h1>Update User</h1>
<p>NOTE: You can't update the email address.</p>

<form action="users" method="post">
    <input type="hidden" name="action" value="update">

    <table>
        <tr>
            <td>Email:</td>
            <td><input type="text" name="email" 
                       value="<%= request.getParameter("email") %>" readonly></td>
        </tr>
        <tr>
            <td>First Name:</td>
            <td><input type="text" name="firstName" 
                       value="<%= request.getParameter("firstName") %>"></td>
        </tr>
        <tr>
            <td>Last Name:</td>
            <td><input type="text" name="lastName" 
                       value="<%= request.getParameter("lastName") %>"></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Update"></td>
        </tr>
    </table>
</form>
</body>
</html>
