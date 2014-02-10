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

        <div class="slider">
            <div class="wrap">
                <div class="tabla-presupuesto">
                    <form class="g-form">
                        <div class="fila">
                            <div class="columna">Fecha</div>
                            <div class="columna">Concepto</div>
                            <div class="columna">Valor</div>
                            <div class="columna">Notas</div>
                        </div>
                        <div class="fila">
                            <div class="columna columnaCambiarFecha">
                                <%
                                    out.print(request.getAttribute("fecha"));
                                %>
                                <a href="javascript:;" class="cambiarFecha">Cambiar Fecha</a>                                                

                            </div>
                            <div class="columna">
                                <input type="text" id="inputConcepto" name="inputConcepto" maxlength="100" placeholder="Concepto" required/>
                            </div>            
                            <div class="columna">
                                <input type="number" id="inputValor" name="inputValor" maxlength="100" placeholder="Valor" required/>
                            </div>    
                            <div class="columna">
                                <input type="text" id="inputNotas" name="inputNotas" maxlength="100" placeholder="Notas" />
                            </div>    
                            <div class="columna g-form-controls">
                                <button type="submit">Guardar</button>
                                <button type="reset">cancelar</button>
                            </div>
                        </div>
                    </form>                      
                </div>
            </div>
        </div>
        <div class="seccionGastos">
            <h1 class="txtTitleGastos">Gastos diarios</h1>
            <%
                out.print(request.getAttribute("resultado"));
            %>            
        </div>
    </body>
</html>
