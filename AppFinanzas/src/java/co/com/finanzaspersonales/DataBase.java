/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.finanzaspersonales;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 *
 * @author gtirado
 */
public class DataBase {

    Properties prop = new Properties();
    String FILE_PROPERTIES = "/wamp/www/PDI/XML/EditXML/src/java/properties/config.properties";

    public Connection getConnectionDataBase() throws SQLException, IOException {
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

        } catch (FileNotFoundException exception) {
            System.out.println("Archivo no encontrado: " + exception.getCause());
        } catch (ClassNotFoundException e1) {
            System.out.println("ERROR:No encuentro el driver de la BD: " + e1.getMessage() + "<br />");
        } catch (SQLException e2) {
            System.out.println("ERROR: Fallo en SQL: " + e2.getMessage());
        }/* finally {
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

    public String insertRow(Connection conexion, String statInsert) {
        try {
        Statement stmt = conexion.createStatement();

        //save the select statement in a string
        /*"INSERT INTO DBINDEXADOR.CIUDADES_CTC (NOMBRE, CODIGO) VALUES ('Monteria', '99001')"*/
        /*INSERT INTO DBINDEXADOR.AF_GASTOS_DIARIOS (FECHA, CONCEPTO, VALOR, NOTAS, ID_GASTO) 
	VALUES ('2014-01-20 12:50:44.047', 'Maquina Pragma', 1400, '', 2)*/
       
       String statInsert2 =  "INSERT INTO AF_GASTOS_DIARIOS (FECHA, CONCEPTO, VALOR, NOTAS, ID_GASTO)" +
	"VALUES (TO_DATE('2014-01-20 12:50:44.047', 'MM/DD/YYYY HH:MI AM'), 'Tinto2', 1920, NULL, 6)";
         

        //create a result set
        stmt.executeUpdate(statInsert);
        
        return "update";
        }catch(SQLException exc){
            System.out.println("Insert: "+exc);
            return "error: " + exc;
        }
    }

    public String showResult(ResultSet rows) throws SQLException {
        int count = 0;
        String result = "";
        String isPar = "";

        result += "<table><tr>";
        result += "<th>FECHA</th>";
        result += "<th>CONCEPTO</th>";
        result += "<th>VALOR</th>";
        result += "<th>NOTAS</th>";
        result += "</tr>";
        while (rows.next()) {


            count += 1;
            isPar = count % 2 == 0 ? "par" : "impar";
            result += "<tr class='" + isPar + "'>";
            result += "<td>" + rows.getString(1) + "</td>";
            result += "<td>" + rows.getString(2) + "</td>";
            result += "<td>" + rows.getString(3) + "</td>";
            result += "<td>" + rows.getString(4) + "</td>";
            result += "</tr>";
        }
        result += "</table>";

        return result;
    }

    
}
