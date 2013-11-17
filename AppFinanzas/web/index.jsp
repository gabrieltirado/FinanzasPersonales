<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <title>Finanzas Personales</title>
        <link rel="stylesheet" href="css/normalize.css">
        <link rel="stylesheet" href="css/contact-form.css">
        <link rel="stylesheet" href="css/main.css">
    </head>
    <body>
        <section>
            <form class="g-form">
                <div class="fila">
                    <div class="columna">Fecha</div>
                    <div class="columna">Concepto</div>
                    <div class="columna">Valor</div>
                </div>
                <div class="fila">
                    <div class="columna">
                        <input type="date" id="inputFecha" maxlength="100" placeholder="Fecha" required>
                    </div>
                    <div class="columna">
                        <input type="text" id="inputConcepto" maxlength="100" placeholder="Concepto" required>
                    </div>            
                    <div class="columna">
                        <input type="number" id="inputValor" maxlength="100" placeholder="Valor" required>
                    </div>    
                    <div class="columna g-form-controls">
                        <button type="submit">Guardar</button>
                        <button type="reset">cancelar</button>
                    </div>
                </div>
            </form>
            <h1></h1>
            <form class="g-form" action="" method="post">
                <h2></h2>

                <label for="inputFecha">Fecha</label>
                <input type="date" id="inputFecha" maxlength="100" placeholder="Fecha" required>

                <label for="inputConcepto">Concepto</label>
                <input type="text" id="inputConcepto" maxlength="100" placeholder="Concepto" required>

                <label for="inputValor">Valor</label>
                <input type="number" id="inputValor" maxlength="100" placeholder="Valor" required>

    <!--            <label for="inputAffiliation">Affiliation</label>
                <input type="text" id="inputAffiliation" placeholder="Your organization">-->

                <!--<label><input type="checkbox" checked="checked">Subscribe Newsletter?</label>-->

                <div class="g-form-controls">
                    <button type="submit">Guardar</button>
                    <button type="reset">cancelar</button>
                </div>
            </form>
        </section>
        
    </body>
</html>
