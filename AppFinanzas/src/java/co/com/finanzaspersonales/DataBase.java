/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.finanzaspersonales;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 *
 * @author gtirado
 */
public class DataBase {

    Properties prop = new Properties();
    String FILE_PROPERTIES = "../../properties/config.properties";

    public Connection getConnectionDataBase() throws SQLException, IOException {
        Connection conexion = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            /*prop.load(new FileInputStream(FILE_PROPERTIES));

             String database = prop.getProperty("database"),
             dbuser = prop.getProperty("dbuser"),
             dbpassword = prop.getProperty("dbpassword"),
             dbport = prop.getProperty("dbport"),
             dbSID = prop.getProperty("dbSID");*/

            /*INSERT INTO `app_finanzas_personales`.`usuarios` (`id`, `nombre`, `apellidos`, `username`, `password`, `email`) VALUES (NULL, '', '', '', '', '');*/
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/app_finanzas_personales?zeroDateTimeBehavior=convertToNull&user=root&password=");

        } catch (SQLException exception) {
            System.out.println("Error BD: " + exception);
        } catch (ClassNotFoundException notFoundException) {
            System.out.println("ERROR:No encuentro el driver de la BD: " + notFoundException.getMessage() + "<br />");
        } /* finally {
         try {
         if (conexion != null) {
         conexion.close();
         }

         } catch (SQLException e3) {
         System.out.println("ERROR:Fallo al desconectar de la BD: " + e3.getMessage());
         }

         }*/
        return conexion;
    }

    public ResultSet Select(Connection conexion, String stat) throws SQLException {
        Statement stmt = conexion.createStatement();

        //save the select statement in a string
        /*"SELECT ALERT_ID as id, DESCRIPTION as description from DBINDEXADOR.AEO_ALERT"*/
        String selectStat = stat;

        //create a result set
        ResultSet rows = stmt.executeQuery(selectStat);

        return rows;
    }

    public String updateRow(Connection conexion, String statInsert) {
        try {
            Statement stmt = conexion.createStatement();

            //save the select statement in a string
        /*"INSERT INTO DBINDEXADOR.CIUDADES_CTC (NOMBRE, CODIGO) VALUES ('Monteria', '99001')"*/
            /*INSERT INTO DBINDEXADOR.AF_GASTOS_DIARIOS (FECHA, CONCEPTO, VALOR, NOTAS, ID_GASTO) 
             VALUES ('2014-01-20 12:50:44.047', 'Maquina Pragma', 1400, '', 2)*/

            String statInsert2 = "INSERT INTO AF_GASTOS_DIARIOS (FECHA, CONCEPTO, VALOR, NOTAS, ID_GASTO)"
                    + "VALUES (TO_DATE('2014-01-20 12:50:44.047', 'MM/DD/YYYY HH:MI AM'), 'Tinto2', 1920, NULL, 6)";


            //create a result set
            stmt.executeUpdate(statInsert);

            return "update";
        } catch (SQLException exc) {
            System.out.println("Insert: " + exc);
            return "error: " + exc;
        }
    }

    public String showResult(ResultSet rows) {
        int count = 0;
        int columnas = 0;
        String result = "";
        String isPar = "";

        result += "<table><tr>";
        result += "<th>FECHA</th>";
        result += "<th>CONCEPTO</th>";
        result += "<th>VALOR</th>";
        result += "<th>CATEGORIA</th>";
        result += "</tr>";
        try {

            while (rows.next()) {
                
                columnas = rows.getMetaData().getColumnCount();                
                                
                count += 1;
                isPar = count % 2 == 0 ? "par" : "impar";
                result += "<tr class='" + isPar + "'>";
                while(columnas > 1){
                    /* <a href="#" id="username" data-type="text" data-pk="1" data-url="/post" data-title="Enter username">superuser</a> */
                    //result += "<td><a href='#' id='username' data-type='text' data-pk='1' data-url='/post' data-title='Enter username'>" + rows.getString(columnas) + "</a></td>";
                    result += "<td class='dato_gasto'>" + rows.getString(columnas) + "</td>";
                    columnas--;
                }       
                result += "<td id='columna_gasto_"+rows.getString(1)+"'><a href='javascript:;' onclick='javascript:eliminarGasto("+rows.getString(1)+")'>Eliminar</a>|<a href='javascript:;' onclick='javascript:editarGasto("+rows.getString(1)+", this)'>Editar</a></td>";
                result += "</tr>";                
                
            }
            result += "</table>";


        } catch (SQLException exception) {
            System.out.println("Exception: " + exception.getMessage());
             result += "<tr>";
             result += "Sin datos";  
             result += "</tr>";  
        }

        return result;
    }
}
