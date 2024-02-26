import beans.StudentBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.List;

public class DeleteStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        // deserializare student din baza de date
        DatabaseManager.loadDriver();
        int studentId = Integer.parseInt(request.getParameter("id"));
        DatabaseManager.deleteStudent(studentId);

        // redirectionare date catre pagina de afisare a informatiilor studentului
        request.getRequestDispatcher("./get-students").forward(request, response);
    }
}


