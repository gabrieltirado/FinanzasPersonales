package co.com.finanzaspersonales;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
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
            String categoria = request.getParameter("selectCategorias");
            String idGasto = request.getParameter("idGasto");
            String accionGasto = request.getParameter("accionGasto");

            System.out.println("accionGasto: " + accionGasto);

            String consulta = "", resultado = "", categorias = "", categoriasJson = "";
            
            
            DataBase db = new DataBase();
            Connection conn = db.getConnectionDataBase();

            String insert = "", deleteSql = "", updateSql = "";

            /* Validar el envio del formulario con los campos diligenciados*/
            if (concepto != null && valor != null && categoria != null && !"editar".equals(accionGasto)) {
                /* Validar si selecciono alguna categoria*/
                if (categoria != "") {

                    if (fecha != null) {
                        insert = "INSERT INTO app_finanzas_personales.gastosdiarios (id, Usuario_id, fecha, concepto, valor, Categoria_id) "
                                + "VALUES (NULL, '1', '" + fecha + "', '" + concepto + "', '" + valor + "', '" + categoria + "')";
                    } else {
                        insert = "INSERT INTO app_finanzas_personales.gastosdiarios (id, Usuario_id, fecha, concepto, valor, Categoria_id) "
                                + "VALUES (NULL, '1', CURDATE(), '" + concepto + "', " + valor + ", '" + categoria + "')";
                    }

                    String resultInser = db.updateRow(conn, insert);
                    System.out.println("resultInser: " + resultInser);
                }
            }

            if (accionGasto != null && "eliminar".equals(accionGasto)) {
                deleteSql = "DELETE FROM app_finanzas_personales.gastosdiarios WHERE gastosdiarios.id=" + idGasto;
                db.updateRow(conn, deleteSql);
            }
            
            if (accionGasto != null && "editar".equals(accionGasto)) {
                /*UPDATE `app_finanzas_personales`.`gastosdiarios` SET `fecha` = '2014-03-28', `concepto` = 'Almuerzo2', `valor` = '8501', `Categoria_id` = '2' WHERE `gastosdiarios`.`id` = 10;*/
                updateSql = "UPDATE app_finanzas_personales.gastosdiarios SET fecha = '" + fecha + "', concepto = '" + concepto + "', valor = " + valor + ", Categoria_id = '" + categoria + "' WHERE gastosdiarios.id=" + idGasto;
                System.out.println("updateSql: "+updateSql);
                db.updateRow(conn, updateSql);
            }

            if (conn != null) {
                resultado = getGastos(db, conn);
                ResultSet rowsCat = getCategorias(db, conn);
                categoriasJson += "[";
                while (rowsCat.next()) {
                    categorias += "<option value='" + rowsCat.getString("id") + "'>" + rowsCat.getString("nombre") + "</option>";
                    categoriasJson += "{value: " + rowsCat.getString("id") + ",text: '" + rowsCat.getString("nombre") + "'},";
                                    
                }  
                categoriasJson += "]";
                
            }else{
                categorias += "<option value='' style='background:red;'>Error base de datos...</option>";
                resultado = "<h1 class='error_bd'>Validar conexión a la base de datos</h1>";
            }
            
            request.setAttribute("resultado", resultado);
            request.setAttribute("categorias", categorias);
            request.setAttribute("categoriasJson", categoriasJson);
            
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
        /*System.out.println("No vales un peso; No me vas a tumbar la aplicación");*/
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
        String resultadoGastos = "";
        try {
            String consulta = "SELECT gastosdiarios.id, nombre AS categoria, valor, concepto, fecha FROM gastosdiarios JOIN categorias ON gastosdiarios.categoria_id = categorias.id order by id asc";
            //consulta = "select * from DBINDEXADOR.CIUDADES_CTC;fecha, concepto, valor, nombre";
            ResultSet selectResult = db.Select(conn, consulta);


            resultadoGastos = db.showResult(selectResult);

        } catch (SQLException exc) {
            System.out.println("Error al obtener gastos: " + exc);
        }
        return resultadoGastos;
    }

    public ResultSet getCategorias(DataBase db, Connection conn) {
        ResultSet selectResult;
        selectResult = null;
        try {
            String consulta = "select id, nombre from categorias order by id asc";

            selectResult = db.Select(conn, consulta);


        } catch (SQLException exception) {
            System.out.println("Exception Categorias: " + exception);
        }
        return selectResult;
    }
}
