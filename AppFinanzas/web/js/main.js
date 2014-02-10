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

    jQuery("form").submit(function(e) {
        var strData = jQuery(this).serialize();        
        var inf = "";
        var peticionAjax = jQuery.ajax({
            type: 'POST',
            url: "/AppFinanzas/Gastos",
            data: strData,
            beforeSend: function() {
            },
            success: function(text, status, xhr) {
                inf = text.replace(/^(\s|\&nbsp;)*|(\s|\&nbsp;)*$/g, "");
                console.log(inf);
            },
            complete: function() {
                
            }
        });
        return false;
    });
});