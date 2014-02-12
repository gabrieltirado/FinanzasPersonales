<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <title>Finanzas Personales</title>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        <script src="js/main.js"></script>
        <link rel="stylesheet" href="css/normalize.css">
        <link rel="stylesheet" href="css/template-form.css">
        <link rel="stylesheet" href="css/main.css">
        <link rel="stylesheet" href="css/style.css">
        <link href="http://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div class="wrap">
            <!---strat-header---->
            <div class="header">
                <div class="logo">
                    <a href="index.html"> </a>
                </div>
                <div class="top-nav">
                    <ul>
                        <li><a href="index.jsp"> <span> </span></a></li>
                        <li class="active"><a href="#">Gastos</a></li>
                        <li><a href="Presupuesto">Presupuesto</a></li>
                        <li><a href="#">Templates</a></li>
                        <li><a href="icons.html">Icons</a></li>
                        <li><a href="#">Wordpress</a></li>
                        <div class="clear"> </div>
                    </ul>
                </div>
                <div class="clear"></div>
            </div>
        </div>

        <div class="seccionCuentas">
            <h1 class="txtTitleCuentas">Cuentas por Cobrar</h1>
              
            <table border="1">                
                    <tr>
                        <th>Fecha Entrega</th>
                        <th>Fecha Vencimiento</th>
                        <th>Nombre deudor</th>
                        <th>Concepto</th>
                        <th>Valor</th>
                        <th>Acciones</th>
                    </tr>                
                    <tr>                        
                    <form action="">
                        <td><input type="date" value="" required /></td>
                        <td><input type="date" value="" required /></td>
                        <td><input type="text" value="" required /></td>
                        <td><input type="text" value="" required /></td>
                        <td><input type="number" value="" required /></td>
                        <td>
                            <input type="submit" value="Guardar"/>
                            <input type="reset" value="Limpiar"/>
                        </td>
                    </form>
                    </tr>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <%
                        out.print(request.getAttribute("cuentasPorCobrarResult"));
                    %>
            </table>
            
        </div>
    </body>
</html>
