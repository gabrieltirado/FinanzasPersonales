jQuery(document).ready(function($){		
	
	

	/**Acordeon*/			
			var accordion_head = $('#accordion h1');
			var accordion_body = $('#accordion div');
			accordion_head.live('click', function(event) {
				event.preventDefault();
				var  claseActive = $(this).attr('class');
				
				if (!$(this).hasClass('active')){
					accordion_body.slideUp(650); 
					$(this).next("div").stop(true,true).slideToggle(650);
					accordion_head.removeClass('active');
					$(this).addClass('active');
					
					 //utilizamos body y html, ya que dependiendo del navegador uno u otro no funciona
                              $('body,html').stop(true,true).animate({
                                       //realizamos la animacion hacia el ancla
                                       scrollTop: $('#accordion').offset().top
                               },650);
				}else{
					accordion_body.slideUp(650);
					accordion_head.removeClass('active');
					
				}
				 
			});
			
			
			
			
			
	
		
			
});	
