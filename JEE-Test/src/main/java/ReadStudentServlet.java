import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import beans.StudentBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.time.Year;


public class ReadStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        // deserializare student din baza de date
        DatabaseManager.loadDriver();
        int studentId = Integer.parseInt(request.getParameter("id"));
        StudentBean bean =DatabaseManager.getStudentById(studentId);

        request.setAttribute("nume", bean.getNume());
        request.setAttribute("prenume", bean.getPrenume());
        request.setAttribute("varsta", bean.getVarsta());
        request.setAttribute("facultate",bean.getFacultate());
        request.setAttribute("an_studii",bean.getAnStudii());
        int varsta = bean.getVarsta();
        int anCurent = Year.now().getValue();
        int anNastere = anCurent - varsta;
        request.setAttribute("anNastere", anNastere);
        request.setAttribute("id",studentId);

        // redirectionare date catre pagina de afisare a informatiilor studentului
        request.getRequestDispatcher("./info-student.jsp").forward(request, response);
    }
}