import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import beans.StudentBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.time.Year;

public class ProcessStudentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        // se citesc parametrii din cererea de tip POST
        String nume = request.getParameter("nume");
        String prenume = request.getParameter("prenume");
        int varsta = Integer.parseInt(request.getParameter("varsta"));
        /*
        procesarea datelor - calcularea anului nasterii
        */
        int anCurent = Year.now().getValue();
        int anNastere = anCurent - varsta;
        String facultate = request.getParameter("facultate");
        int an_studii = Integer.parseInt(request.getParameter("an_studii"));


        // creare bean si populare cu date
        StudentBean bean = new StudentBean();
        bean.setNume(nume);
        bean.setPrenume(prenume);
        bean.setVarsta(varsta);
        bean.setAnStudii(an_studii);
        bean.setFacultate(facultate);


        // adaugare in baza de date
        DatabaseManager.loadDriver();
        int studentId=DatabaseManager.addStudent(bean.getNume(),bean.getPrenume(),bean.getVarsta(),bean.getFacultate(), bean.getAnStudii());
        bean.setId(studentId);

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