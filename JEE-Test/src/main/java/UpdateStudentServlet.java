import beans.StudentBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Year;

public class UpdateStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        // deserializare student baza de date
        DatabaseManager.loadDriver();
        int studentId = Integer.parseInt(request.getParameter("id"));
        StudentBean bean =DatabaseManager.getStudentById(studentId);

        request.setAttribute("nume", bean.getNume());
        request.setAttribute("prenume", bean.getPrenume());
        request.setAttribute("varsta", bean.getVarsta());
        request.setAttribute("facultate",bean.getFacultate());
        request.setAttribute("an_studii",bean.getAnStudii());
        request.setAttribute("id",studentId);

        // redirectionare date catre pagina de afisare a informatiilor studentului
        request.getRequestDispatcher("./formular_actualizare.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        DatabaseManager.loadDriver();
        int studentId = Integer.parseInt(request.getParameter("id"));

        // se citesc parametrii din cererea de tip POST
        String nume = request.getParameter("nume");
        String prenume = request.getParameter("prenume");
        String facultate = request.getParameter("facultate");
        int an_studii = Integer.parseInt(request.getParameter("an_studii"));
        int varsta = Integer.parseInt(request.getParameter("varsta"));
        /*
        procesarea datelor - calcularea anului nasterii
        */
        int anCurent = Year.now().getValue();
        int anNastere = anCurent - varsta;

        // creare bean si populare cu date
        StudentBean bean = new StudentBean();
        bean.setNume(nume);
        bean.setPrenume(prenume);
        bean.setVarsta(varsta);
        bean.setFacultate(facultate);
        bean.setAnStudii(an_studii);

        // serializare bean in baza de date
        DatabaseManager.updateStudent(studentId,nume,prenume,varsta,facultate,an_studii);

        // se trimit datele primite si anul nasterii catre o alta pagina JSP pentru afisare
        request.setAttribute("nume", nume);
        request.setAttribute("prenume", prenume);
        request.setAttribute("varsta", varsta);
        request.setAttribute("anNastere", anNastere);
        request.setAttribute("an_studii",an_studii);
        request.setAttribute("facultate",facultate);
        request.setAttribute("id",studentId);
        request.getRequestDispatcher("./info-student.jsp").forward(request, response);
    }
}