<html xmlns:jsp="http://java.sun.com/JSP/Page">
<head>
    <title>Lista studentilor</title>
</head>
<body>
    <h3>Lista studentilor</h3>
    <form action="./get-students" method="post">
    			<input type="text" name="field" />
    			<button type="submit" name="submit">Trimite</button>
    		</form>
    <table>
            <tr>
            <td>Nume</td>
            <td>Prenume</td>
            <td>Varsta</td>
            <td>Facultate</td>
            <td>An de studii</td>
            <td>Informatii</td>
            </tr>

        <% java.util.List<beans.StudentBean> studentList = (java.util.List<beans.StudentBean>) request.getAttribute("studentList");
           for (beans.StudentBean student : studentList) {
        %>
        <tr>
        <td>
            <%= student.getNume() %>
        </td>
        <td>
            <%= student.getPrenume() %>
        </td>
        <td>
            <%= student.getVarsta() %>
        </td>
        <td>
            <%= student.getFacultate() %>
        </td>
        <td>
            <%= student.getAnStudii() %>
        </td>
        <td>
            <a href="./read-student?id=<%= student.getId()%>">Vizualizare</a>
        </td>

        <tr>
        <% } %>
    </table>
</body>
</html>