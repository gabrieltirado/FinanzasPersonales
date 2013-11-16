<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Finanzas Personales</title>
        <link rel="stylesheet" href="css/normalize.css">
        <link rel="stylesheet" href="css/contact-form.css">
    </head>
    <body>
        <section>
            <h1></h1>
            <form class="g-form" action="" method="post">
                <h2></h2>

                <label for="inputFecha">Fecha</label>
                <input type="text" id="inputFecha" maxlength="100" placeholder="John F. Cannedy" required>

                <label for="inputConcepto">Concepto</label>
                <input type="email" id="inputConcepto" maxlength="100" placeholder="yourname@yourcomapny.com" required>

                <label for="inputWebsite">Valor</label>
                <input type="url" maxlength="100" placeholder="http://" required>

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
