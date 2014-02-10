package co.com.finanzaspersonales;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gtirado
 */
public class Gastos extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {

            String fecha = request.getParameter("inputFecha");

            String concepto = request.getParameter("inputConcepto");
            String valor = request.getParameter("inputValor");
            String notas = request.getParameter("inputNotas");
            
            String consulta = "", resultado = "";
            DataBase db = new DataBase();
            Connection conn = db.getConnectionDataBase();

            String insert = "";

            if (concepto != null && valor != null) {
                if (fecha != "") {
                    insert = "INSERT INTO AF_GASTOS_DIARIOS (FECHA, CONCEPTO, VALOR, NOTAS) "
                            + "VALUES (TO_DATE('" + fecha + "', 'yyyy-mm-dd hh24:mi:ss'), '" + concepto + "', " + valor + ", '')";
                } else {
                    insert = "INSERT INTO AF_GASTOS_DIARIOS (FECHA, CONCEPTO, VALOR, NOTAS) "
                            + "VALUES (TO_DATE(sysdate), '" + concepto + "', " + valor + ", '')";
                }
                String resultInser = db.insertRow(conn, insert);
            }


            resultado = getGastos(db, conn);

            request.setAttribute("resultado", resultado);
            
            
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");            
            
            request.setAttribute("fecha", dateFormat.format(date));
            request.getRequestDispatcher("gastos.jsp").forward(request, response);

        } catch (Exception exception) {
            out.println(exception.getCause());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    public String getGastos(DataBase db, Connection conn) {
        try {
            String consulta = "select FECHA, CONCEPTO, VALOR, NOTAS from AF_GASTOS_DIARIOS order by fecha asc";
            //consulta = "select * from DBINDEXADOR.CIUDADES_CTC;";
            ResultSet Select = db.Select(conn, consulta);

            String resultado = db.showResult(Select);

            return resultado;

        } catch (SQLException exc) {
            return null;
        }
    }
}
