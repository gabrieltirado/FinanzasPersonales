/*var peticion = jQuery.ajax({    
		type: 'GET',                
		url: "/wps/myportal/proteccion/web/home/canales-servicios/asesor-virtual",
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
    jQuery('.cambiarFecha').on('click', function(){
        jQuery('.columnaCambiarFecha').html(input);
    });
});