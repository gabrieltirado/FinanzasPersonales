/*var peticion = jQuery.ajax({    
 type: 'GET',                
 url: "/AppFinanzas/Gastos",
 data: null,
 beforeSend: function(){
 },
 success: function(text,status,xhr) {                		
 inf = text.replace(/^(\s|\&nbsp;)*|(\s|\&nbsp;)*$/g,""); 
 console.log(status);
 console.log(xhr);			
 },
 complete: function(){
 
 }
 });*/
jQuery(document).ready(function() {
    var input = '<input type="date" id="inputFecha" name="inputFecha" maxlength="100" placeholder="Fecha"/>';
    jQuery('.cambiarFecha').on('click', function() {
        jQuery('.columnaCambiarFecha').html(input);
    });

    /*jQuery('button[type="submit"]').on('click', function(e){
     e.preventDefault();
     var str = jQuery( "form" ).serialize();
     alert(str);
     });  */

    jQuery("form[name='ingresoGasto']").submit(function(e) {
        var strData = jQuery(this).serialize();
        var inputConcepto = jQuery('input#inputConcepto');
        /* Validar que el campo no contenga cosas diferentes a texto */
        if (!/[^A-Za-z0-9\-\_\s]/.test(inputConcepto.val())) {           
            inputConcepto.removeClass('error_characters');            
            guardarGasto(strData);
        } else {
            inputConcepto.addClass('error_characters');
            /*inputConcepto.insertAfter('<label class="error_characters">Validar caracteres</label>');*/
        }

        return false;
    });

    jQuery("a.cambiarFecha").on('click', function() {
        jQuery("#inputFecha").focus();
    });
});

function eliminarGasto(idGasto) {
    var accionGasto = "eliminar";
    if (confirm("¿Desea eliminar este gasto?")) {
        jQuery.ajax({
            type: 'POST',
            url: "/AppFinanzas/Gastos",
            data: "idGasto=" + idGasto + "&accionGasto=" + accionGasto,
            beforeSend: function() {
            },
            success: function(text, status, xhr) {
                inf = text.replace(/^(\s|\&nbsp;)*|(\s|\&nbsp;)*$/g, "");
                jQuery('.listadoDiario').html('');
                jQuery('.listadoDiario').html('<div class="editado">' + jQuery(jQuery.parseHTML(inf)).find('.listadoDiario').addClass('editado').html() + '</div>')



            },
            complete: function() {

            }
        });
    }
}

function editarGasto(idGasto, element) {
    var strData = "", htmlForm = "";
    var accionGasto = "editar";
    /*Pasar de string a object mediante eval*/
    var categoriasJson = eval(jQuery('#categoriasJson').val()), datosGastos = {};
    /* Eliminar la ultima aparicion de la coma (,)*/
    /*categoriasJson = categoriasJson.replace(/,([^\,]*)$/, '$1');*/

    var columnas = jQuery(element).parents('tr').children('td.dato_gasto');
    jQuery.each(columnas, function(index, item) {
        datosGastos[index] = jQuery(item).html();
    });

    htmlForm += '<input type="date" id="inputFecha" name="inputFecha" maxlength="100" placeholder="Fecha" value="' + datosGastos[0] + '">'
    htmlForm += '<input type="text" id="inputConcepto" name="inputConcepto" maxlength="100" placeholder="Concepto" value="' + datosGastos[1] + '" required>'
    htmlForm += '<input type="number" id="inputValor" name="inputValor" maxlength="100" placeholder="Valor" value="' + datosGastos[2] + '" required>'
    /* Selección Categoría */
    htmlForm += '<select name="selectCategorias" id="selectCategorias" class="selectCategorias" required>';
    for (var i = 0; i < categoriasJson.length; i++) {
        if (categoriasJson[i].text !== datosGastos[3]) {
            htmlForm += '<option value="' + categoriasJson[i].value + '">' + categoriasJson[i].text + '</option>';
        } else {
            htmlForm += '<option value="' + categoriasJson[i].value + '" selected class="selected">' + categoriasJson[i].text + '</option>';
        }
    }
    htmlForm += '</select>';


    jQuery("#camposEdicion").html(htmlForm);
    
    /* Dar el foco al primer campo del formulario de edición */    
    jQuery('form[name="formularioEdicion"] input:first-child').unbind("focus");
    jQuery('form[name="formularioEdicion"] input:first-child').focus();
    
    

    strData = "idGasto=" + idGasto + "&accionGasto=" + accionGasto;

    jQuery('form[name="formularioEdicion"]').off('submit').on('submit', function(e) {
        if (confirm("¿Desea editar este gasto?")) {
            var strDataForm = jQuery(this).serialize();
            alert(strData + "&" + strDataForm)
            guardarGasto(strData + "&" + strDataForm);
        }
        return false;
    });


}

function guardarGasto(strData) {
    var inf = "";
    var peticionAjax = jQuery.ajax({
        type: 'POST',
        url: "/AppFinanzas/Gastos",
        data: strData,
        beforeSend: function() {
            $('<img src="images/ajax-loader.gif" id="spinnerGeneral" />').css('position','absolute').hide().appendTo('body');
            var thisElement = jQuery('.listadoDiario');
            var position = thisElement.offset();
            $('#spinnerGeneral').css({top: position.top + (thisElement.height()/2) - 65, left: position.left + (thisElement.width()/2) - 45}).fadeIn(); 
        },
        success: function(text, status, xhr) {
            inf = text.replace(/^(\s|\&nbsp;)*|(\s|\&nbsp;)*$/g, "");
            jQuery('.listadoDiario').html('');
            jQuery('.listadoDiario').html('<div class="editado">' + jQuery(jQuery.parseHTML(inf)).find('.listadoDiario').addClass('editado').html() + '</div>')
            jQuery('form.g-form').find('input, select').val("");
        },
        complete: function() {
            $('#spinnerGeneral').fadeOut();   
        }
    });
}