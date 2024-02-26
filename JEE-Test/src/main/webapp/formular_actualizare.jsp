<html xmlns:jsp="http://java.sun.com/JSP/Page">
	<head>
		<title>Formular student</title>
		<meta charset="UTF-8" />
	</head>
	<body>
		<h3>Informatii student</h3>
		<jsp:useBean id="studentBean" class="beans.StudentBean" />
        <jsp:setProperty name="studentBean" property="nume" value='<%=request.getAttribute("nume") %>'/>
        <jsp:setProperty name="studentBean" property="prenume" value='<%=request.getAttribute("prenume") %>'/>
        <jsp:setProperty name="studentBean" property="varsta" value='<%=request.getAttribute("varsta") %>'/>
        <jsp:setProperty name="studentBean" property="facultate" value='<%=request.getAttribute("facultate") %>'/>
        <jsp:setProperty name="studentBean" property="anStudii" value='<%=request.getAttribute("an_studii") %>'/>
		<form action="./update-student?id=<%= request.getAttribute("id")%>" method="post">
			Nume: <input type="text" name="nume" value=<jsp:getProperty name="studentBean" property="nume" />>
			<br />
			Prenume: <input type="text" name="prenume" value=<jsp:getProperty name="studentBean" property="prenume" />>
			<br />
			Varsta: <input type="number" name="varsta" value=<jsp:getProperty name="studentBean" property="varsta" />>
			<br />
			Facultate: <input type="text" name="facultate" value=<jsp:getProperty name="studentBean" property="facultate" />>
            <br />
            An de studii: <input type="number" name="an_studii" value=<jsp:getProperty name="studentBean" property="anStudii" />>
            <br />
			<br />
			<button type="submit" name="submit">Actualizeaza</button>
		</form>
	</body>
</html>