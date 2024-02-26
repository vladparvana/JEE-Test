import beans.StudentBean;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class JSONServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        DatabaseManager.loadDriver();
        List<StudentBean> students= DatabaseManager.getAllStudents();
        JSONArray jsonArray = new JSONArray();

        for(StudentBean student: students) {
            JSONObject obj = new JSONObject();

            obj.put("nume", student.getNume());
            obj.put("prenume", student.getPrenume());
            obj.put("varsta", student.getVarsta());;
            obj.put("facultate", student.getFacultate());;
            obj.put("an_studii", student.getAnStudii());

            jsonArray.put(obj);

        }

        httpServletResponse.getWriter().print(jsonArray);
    }
}
