import beans.StudentBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    public static void loadDriver()  {
        // load the sqlite-JDBC driver using the current class loader
        try {
            Class.forName("org.sqlite.JDBC");
        }
        catch (ClassNotFoundException e)
        {
            System.err.println(e.getMessage());
        }

        Connection connection = null;
        try
        {// create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:/home/student/1309B/ParvanaVladStefan/JEE-Test/JEE-Test/sample.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            statement.executeUpdate("create table if not exists student (id integer primary key autoincrement, nume string, prenume string, varsta integer, an_studii integer, facultate string)");
            //statement.executeUpdate("insert into student (nume,prenume) values ('Parvana','Vlad')");
        }
        catch(SQLException e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }

        finally
        {
            try
            {
                if(connection != null)
                    connection.close();
            }
            catch(SQLException e)
            {
                // connection close failed.
                System.err.println(e);
            }
        }
    }

    public static int addStudent(String nume,String prenume, int varsta,String facultate, int anStudii)
    {
        Connection connection = null;
        ResultSet rs = null;
        try
        {

            connection = DriverManager.getConnection("jdbc:sqlite:/home/student/1309B/ParvanaVladStefan/JEE-Test/JEE-Test/sample.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            statement.executeUpdate("insert into student (nume,prenume,varsta,facultate,an_studii) values ('"+nume+"','"+prenume+"','"+varsta+"','"+facultate+"','"+anStudii+"')");
            rs=statement.executeQuery("select seq from sqlite_sequence where name='student'");
            int studentId=0;
            studentId=rs.getInt("seq");
            return studentId;


        }
        catch(SQLException e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
        finally
        {
            try
            {
                if(connection != null)
                    connection.close();
            }
            catch(SQLException e)
            {
                // connection close failed.
                System.err.println(e);
            }
        }
        return 0;
    }
    public static List<StudentBean> getAllStudents() {
        List<StudentBean> studentList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            connection = DriverManager.getConnection("jdbc:sqlite:/home/student/1309B/ParvanaVladStefan/JEE-Test/JEE-Test/sample.db");


            String query = "SELECT * FROM student";
            stmt = connection.prepareStatement(query);
            rs = stmt.executeQuery();


            while (rs.next()) {
                StudentBean student = new StudentBean();
                student.setId(rs.getInt("id"));
                student.setNume(rs.getString("nume"));
                student.setPrenume(rs.getString("prenume"));
                student.setVarsta(rs.getInt("varsta"));
                student.setAnStudii(rs.getInt("an_studii"));
                student.setFacultate(rs.getString("facultate"));
                studentList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return studentList;
    }
    public static List<StudentBean> getStudentByNume(String nume) {
        List<StudentBean> studentList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            connection = DriverManager.getConnection("jdbc:sqlite:/home/student/1309B/ParvanaVladStefan/JEE-Test/JEE-Test/sample.db");


            String query = "SELECT * FROM student where lower(nume) LIKE '%"+nume.toLowerCase()+"%'";
            stmt = connection.prepareStatement(query);
            rs = stmt.executeQuery();


            while (rs.next()) {
                StudentBean student = new StudentBean();
                student.setId(rs.getInt("id"));
                student.setNume(rs.getString("nume"));
                student.setPrenume(rs.getString("prenume"));
                student.setVarsta(rs.getInt("varsta"));
                student.setAnStudii(rs.getInt("an_studii"));
                student.setFacultate(rs.getString("facultate"));
                studentList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return studentList;
    }
    public static List<StudentBean> getStudentByPrenume(String prenume) {
        List<StudentBean> studentList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            connection = DriverManager.getConnection("jdbc:sqlite:/home/student/1309B/ParvanaVladStefan/JEE-Test/JEE-Test/sample.db");


            String query = "SELECT * FROM student where lower(prenume) LIKE '%"+prenume.toLowerCase()+"%'";
            stmt = connection.prepareStatement(query);
            rs = stmt.executeQuery();


            while (rs.next()) {
                StudentBean student = new StudentBean();
                student.setId(rs.getInt("id"));
                student.setNume(rs.getString("nume"));
                student.setPrenume(rs.getString("prenume"));
                student.setVarsta(rs.getInt("varsta"));
                student.setAnStudii(rs.getInt("an_studii"));
                student.setFacultate(rs.getString("facultate"));
                studentList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return studentList;
    }
    public static List<StudentBean> getStudentByFacultate(String facultate) {
        List<StudentBean> studentList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            connection = DriverManager.getConnection("jdbc:sqlite:/home/student/1309B/ParvanaVladStefan/JEE-Test/JEE-Test/sample.db");


            String query = "SELECT * FROM student where lower(facultate) LIKE '%"+facultate.toLowerCase()+"%'";
            stmt = connection.prepareStatement(query);
            rs = stmt.executeQuery();


            while (rs.next()) {
                StudentBean student = new StudentBean();
                student.setId(rs.getInt("id"));
                student.setNume(rs.getString("nume"));
                student.setPrenume(rs.getString("prenume"));
                student.setVarsta(rs.getInt("varsta"));
                student.setAnStudii(rs.getInt("an_studii"));
                student.setFacultate(rs.getString("facultate"));
                studentList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return studentList;
    }
    public static List<StudentBean> getStudentByVarsta(int varsta) {
        List<StudentBean> studentList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            connection = DriverManager.getConnection("jdbc:sqlite:/home/student/1309B/ParvanaVladStefan/JEE-Test/JEE-Test/sample.db");


            String query = "SELECT * FROM student where varsta='"+varsta+"'";
            stmt = connection.prepareStatement(query);
            rs = stmt.executeQuery();


            while (rs.next()) {
                StudentBean student = new StudentBean();
                student.setId(rs.getInt("id"));
                student.setNume(rs.getString("nume"));
                student.setPrenume(rs.getString("prenume"));
                student.setVarsta(rs.getInt("varsta"));
                student.setAnStudii(rs.getInt("an_studii"));
                student.setFacultate(rs.getString("facultate"));
                studentList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return studentList;
    }
    public static List<StudentBean> getStudentByAnStudii(int anStudii) {
        List<StudentBean> studentList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            connection = DriverManager.getConnection("jdbc:sqlite:/home/student/1309B/ParvanaVladStefan/JEE-Test/JEE-Test/sample.db");


            String query = "SELECT * FROM student where an_studii='"+anStudii+"'";
            stmt = connection.prepareStatement(query);
            rs = stmt.executeQuery();


            while (rs.next()) {
                StudentBean student = new StudentBean();
                student.setId(rs.getInt("id"));
                student.setNume(rs.getString("nume"));
                student.setPrenume(rs.getString("prenume"));
                student.setVarsta(rs.getInt("varsta"));
                student.setAnStudii(rs.getInt("an_studii"));
                student.setFacultate(rs.getString("facultate"));
                studentList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return studentList;
    }
    public static StudentBean getStudentById(int studentId) {
        List<StudentBean> studentList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            connection = DriverManager.getConnection("jdbc:sqlite:/home/student/1309B/ParvanaVladStefan/JEE-Test/JEE-Test/sample.db");


            String query = "SELECT * FROM student WHERE id="+studentId;
            stmt = connection.prepareStatement(query);
            rs = stmt.executeQuery();


            if (rs.next()) {
                StudentBean student = new StudentBean();
                student.setId(rs.getInt("id"));
                student.setNume(rs.getString("nume"));
                student.setPrenume(rs.getString("prenume"));
                student.setVarsta(rs.getInt("varsta"));
                student.setFacultate(rs.getString("facultate"));
                student.setAnStudii(rs.getInt("an_studii"));
                return student;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    public static void updateStudent(int id,String nume,String prenume, int varsta,String facultate, int anStudii)
    {
        Connection connection = null;
        try
        {
            connection = DriverManager.getConnection("jdbc:sqlite:/home/student/1309B/ParvanaVladStefan/JEE-Test/JEE-Test/sample.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            statement.executeUpdate("update student set nume ='"+nume+"',prenume='"+prenume+"',an_studii=+'"+anStudii+"',facultate='"+facultate+"',varsta='"+varsta+"' where id='"+id+"'");
        }
        catch(SQLException e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
        finally
        {
            try
            {
                if(connection != null)
                    connection.close();
            }
            catch(SQLException e)
            {
                // connection close failed.
                System.err.println(e);
            }
        }
    }
    public static void deleteStudent(int id)
    {
        Connection connection = null;
        try
        {
            connection = DriverManager.getConnection("jdbc:sqlite:/home/student/1309B/ParvanaVladStefan/JEE-Test/JEE-Test/sample.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            statement.executeUpdate("delete from student where id='"+id+"'");
        }
        catch(SQLException e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
        finally
        {
            try
            {
                if(connection != null)
                    connection.close();
            }
            catch(SQLException e)
            {
                // connection close failed.
                System.err.println(e);
            }
        }
    }
}
