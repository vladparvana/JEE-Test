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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class GetStudentsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        DatabaseManager.loadDriver();
        String field = request.getParameter("field");
        if(field != null) {

            List<StudentBean> searchList=new ArrayList<>();
            Set<StudentBean> uniqueSearchList=new HashSet<>();

            uniqueSearchList.addAll(DatabaseManager.getStudentByFacultate(field));
            uniqueSearchList.addAll(DatabaseManager.getStudentByNume(field));
            uniqueSearchList.addAll(DatabaseManager.getStudentByPrenume(field));
            try {
                uniqueSearchList.addAll(DatabaseManager.getStudentByAnStudii(Integer.parseInt(field)));
                uniqueSearchList.addAll(DatabaseManager.getStudentByVarsta(Integer.parseInt(field)));
            }
            catch (NumberFormatException e)
            {

            }
            searchList.addAll(uniqueSearchList);
            request.setAttribute("studentList", searchList);
        }
        else
            request.setAttribute("studentList", DatabaseManager.getAllStudents());
        request.getRequestDispatcher("./all-students.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        DatabaseManager.loadDriver();
        List<StudentBean> studentList = DatabaseManager.getAllStudents();
        request.setAttribute("studentList",studentList);
        // redirectionare date catre pagina de afisare a informatiilor studentului
        request.getRequestDispatcher("./all-students.jsp").forward(request, response);
    }
}
