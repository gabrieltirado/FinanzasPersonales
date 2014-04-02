<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <title>Finanzas Personales</title>
        <!--<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>-->

        <script src="http://code.jquery.com/jquery-2.0.3.min.js"></script> 


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
                <form class="g-form" name="ingresoGasto">
                    <div class="fila">
                        <div class="columna"><label for="inputFecha">Fecha</label></div>
                        <div class="columna"><label for="inputConcepto">Concepto</label></div>
                        <div class="columna"><label for="inputValor">Valor</label></div>
                        <div class="columna"><label for="selectCategorias">Categoria</label></div>
                    </div>
                    <div class="fila">
                        <div class="columna columnaCambiarFecha">
                            <label for=""><%
                                out.print(request.getAttribute("fecha"));
                                %></label>
                            <a href="javascript:;" class="letbtn cambiarFecha">Cambiar Fecha</a>                                                

                        </div>
                        <div class="columna">
                            <input type="text" id="inputConcepto" name="inputConcepto" maxlength="100" placeholder="Concepto" required/>
                        </div>            
                        <div class="columna">
                            <input type="number" id="inputValor" name="inputValor" maxlength="100" placeholder="Valor" required/>
                        </div>    
                        <div class="columna">                                
                            <select name="selectCategorias" id="selectCategorias" class="selectCategorias" required>
                                <option value=""> -- Seleccione -- </option>
                                <%
                                    out.print(request.getAttribute("categorias"));
                                %>
                                <option value="" class="crearCategoria">Crear Categoria...</option>
                            </select>
                        </div>    
                    </div>
                    <div class="g-form-controls">
                        <button type="submit">Guardar</button>
                        <button type="reset">cancelar</button>
                    </div>
                </form>                                     
            </div>
        </div>
                                
        <div class="wrap">
            <div class="content-top-header">
                <h3>Gastos diarios</h3>
                <p>Lista ordenada de gastos diarios</p>
            </div> 
        </div>  
                                
        <div class="seccionGastos">
            <div class="slider">
                <div class="wrap">
                    <div class="listadoDiario">
                        <%
                            out.print(request.getAttribute("resultado"));
                        %>
                        <input type="hidden" name="categoriasJson" id="categoriasJson" value="<%out.print(request.getAttribute("categoriasJson"));%>">            
                    </div>
                </div>
            </div>
        </div>
                    
        <div class="text-monials">
            <div class="wrap">
                <ul>
                    <li></li>
                    <li><p>Una parte de todo lo que gano es mio y puedo conservarlo</p> </li>
                    <li> </li>
                    <div class="clear"> </div>
                </ul>
            </div>
        </div>
                    
        <div class="seccionEdicion">
            <form class="g-form g-form2" name="formularioEdicion">
                <div id="camposEdicion"></div>
                <div class="g-form-controls">
                    <button type="submit">Guardar</button>
                    <button type="reset">cancelar</button>
                    <a class="btn" href="iconsingle.html">More freebies</a>
                </div>
            </form>
        </div>
    </body>
</html>
