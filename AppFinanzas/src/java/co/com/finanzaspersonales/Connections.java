package co.com.finanzaspersonales;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.security.auth.login.Configuration;

public class Connections extends HttpServlet {

    Properties prop = new Properties();
    String FILE_PROPERTIES = "/wamp/www/PDI/XML/EditXML/src/java/properties/config.properties";

    @Override
    public void init(ServletConfig conf)
            throws ServletException {
        super.init(conf);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ConectaBD</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ConectaBD at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Connection conexion = null;



        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            prop.load(new FileInputStream(FILE_PROPERTIES));

            String database = prop.getProperty("database"),
                    dbuser = prop.getProperty("dbuser"),
                    dbpassword = prop.getProperty("dbpassword"),
                    dbport = prop.getProperty("dbport"),
                    dbSID = prop.getProperty("dbSID");

            conexion = DriverManager.getConnection("jdbc:oracle:thin:@" + database + ":" + dbport + ":" + dbSID, dbuser, dbpassword);

            Statement stmt = conexion.createStatement();

            //save the select statement in a string
            String selectStat = "SELECT ALERT_ID as id, DESCRIPTION as description from DBINDEXADOR.AEO_ALERT";            

            //create a result set
            ResultSet rows = stmt.executeQuery(selectStat);            

            int count = 0;
            String result = "";
            String isPar = "";
            
            result += "<table><tr>";
            result += "<th>ID</th>";
            result += "<th>DESCRIPTION</th>";
            result += "</tr>";
            while (rows.next()) {
                count += 1;
                isPar = count%2==0?"par":"impar";
                result += "<tr class='"+isPar+"'>";
                result += "<td>"+rows.getString("ID")+"</td>";
                result += "<td>"+rows.getString("DESCRIPTION")+"</td>";   
                result += "</tr>";
            }
            result += "</table>";
            
            out.println("<html>");
            out.println("<body>");
            out.println(result);
            out.println("</body>");
            out.println("</html>");

        } catch (FileNotFoundException exception) {
            out.println("Archivo no encontrado: " + exception.getCause());
        } catch (ClassNotFoundException e1) {
            out.println("ERROR:No encuentro el driver de la BD: "
                    + e1.getMessage() + "<br />");
        } catch (SQLException e2) {
            out.println("ERROR: Fallo en SQL: " + e2.getMessage());
        } finally {
            try {
                if (conexion != null) {
                    conexion.close();
                }

            } catch (SQLException e3) {
                out.println("ERROR:Fallo al desconectar de la BD: "
                        + e3.getMessage());
            }

        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        doGet(request, response);
    }
    
    
}
