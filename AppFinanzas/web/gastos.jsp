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
    </head>
    <body>
        <section>
            <form class="g-form"  action="Gastos" method="post">
                <div class="fila">
                    <div class="columna">Fecha</div>
                    <div class="columna">Concepto</div>
                    <div class="columna">Valor</div>
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
                    <div class="columna g-form-controls">
                        <button type="submit">Guardar</button>
                        <button type="reset">cancelar</button>
                    </div>
                </div>
            </form>                      
        </section>
        <div class="seccionGastos">
            <h1 class="txtTitleGastos">Gastos diarios</h1>
            <%                
                out.print(request.getAttribute("resultado"));
            %>            
        </div>
    </body>
</html>
