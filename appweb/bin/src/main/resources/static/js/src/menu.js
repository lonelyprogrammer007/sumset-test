$(document).ready(function() {
	$('.sub-menu').click(function (e) {
		var sub = $(this);
		var menu=$(sub).attr("href");
		if($(menu).is(':visible')){
			$(menu).slideUp();
		}else{
			$(menu).slideDown();
		}
			
		var todos = $('.multi-collapse').not($(menu));
		for(let i =0; i < todos.length; i++){
			if($(todos[i]).is(':visible')){
				$(todos[i]).slideUp();
			}
		}
		
		
		e.stopPropagation();
		e.preventDefault();
		
		

		
	});
	$(document).click(function(e){
		if(!$(event.target).is('.drop-menu')){
			$('.item-drop').find('.ip').attr('src','/Segplan/img/instrumentos-de-planeacion.png');
			$('.item-drop').find('.form-group').find('.instrumentos').css('color','#FFFFFF');
			$('.item-drop').find('.form-group').find('.planeacion').css('color','#FFFFFF');
			$('.item-drop').css('background-color','');
			$('.item-drop').find('.bp').attr('src','/Segplan/img/banco-distrital.png');
			$('.item-drop').find('.form-group').find('.instrumentos').css('color','#FFFFFF');
			$('.item-drop').find('.form-group').find('.planeacion').css('color','#FFFFFF');
			
			$('.item-drop').find('.accion').attr('src','/Segplan/img/plan-de-accion.png');
			$('.item-drop').find('.form-group').find('.instrumentos').css('color','#FFFFFF');
			$('.item-drop').find('.rp').attr('src','/Segplan/img/reportes.png');
			$('.item-drop').find('.form-group').find('.instrumentos').css('color','#FFFFFF');
			$('.item-drop').find('.afs').attr('src','/Segplan/img/administracion-funcional.png');
			$('.item-drop').find('.form-group').find('.instrumentos').css('color','#FFFFFF');
			$('.item-drop').find('.planeacion').css('color','#FFFFFF');
		}
	})
	$(".nivel1").click(function(e) {

		var menu = $(this);
		
		var expanded = $(menu).attr("aria-expanded");
		if(expanded == "false" ){
			var todos = $('.multi-collapse').css("display","none");
			
		}
	});
	$('.instrumento').click(function(){
		if(!$(this).parent().parent().hasClass('show')){
			$(this).find('.ip').attr('src','/Segplan/img/instrumentos-de-planeacion-morado.png');
			$(this).find('.form-group').find('.instrumentos').css('color','#513388');
			$(this).find('.form-group').find('.planeacion').css('color','#513388');
			$(this).css('background-color','#ede3fc');
			
			recogertodos($(this));
		}else{
			$(this).find('.ip').attr('src','/Segplan/img/instrumentos-de-planeacion.png');
			$(this).find('.form-group').find('.instrumentos').css('color','#FFFFFF');
			$(this).find('.form-group').find('.planeacion').css('color','#FFFFFF');
			$(this).css('background-color','');
			$(this).removeClass('activo');
		}
	});
	$('.bancos').click(function(){
		if(!$(this).parent().parent().hasClass('show')){
			$(this).find('.bp').attr('src','/Segplan/img/banco-distrital-morado.png');
			$(this).find('.form-group').find('.instrumentos').css('color','#513388');
			$(this).find('.form-group').find('.planeacion').css('color','#513388');
			
			$(this).css('background-color','#ede3fc');
			recogertodos($(this));
		}else{
			$(this).find('.bp').attr('src','/Segplan/img/banco-distrital.png');
			$(this).find('.form-group').find('.instrumentos').css('color','#FFFFFF');
			$(this).find('.form-group').find('.planeacion').css('color','#FFFFFF');
			$(this).css('background-color','');
			$(this).removeClass('activo');
		}
	});
	
	$('.planes').click(function(){
		if(!$(this).parent().parent().hasClass('show')){
			$(this).find('.accion').attr('src','/Segplan/img/plan-de-accion-morado.png');
			$(this).find('.form-group').find('.instrumentos').css('color','#513388');
		
			$(this).css('background-color','#ede3fc');
			recogertodos($(this));
		}else{
			$(this).find('.accion').attr('src','/Segplan/img/plan-de-accion.png');
			$(this).find('.form-group').find('.instrumentos').css('color','#FFFFFF');
			
			$(this).css('background-color','');
		}
	});
	$('.reportes').click(function(){
		if(!$(this).parent().parent().hasClass('show')){
			$(this).find('.rp').attr('src','/Segplan/img/reportes-morado.png');
			$(this).find('.form-group').find('.instrumentos').css('color','#513388');
			
			$(this).css('background-color','#ede3fc');
			recogertodos($(this));
		}else{
			$(this).find('.rp').attr('src','/Segplan/img/reportes.png');
			$(this).find('.form-group').find('.instrumentos').css('color','#FFFFFF');
			
			$(this).css('background-color','');
		}
	});
	$('.administracion').click(function(){
		if(!$(this).parent().parent().hasClass('show')){
			$(this).find('.afs').attr('src','/Segplan/img/administracion-funcional-morado.png');
			$(this).find('.form-group').find('.instrumentos').css('color','#513388');
			$(this).find('.form-group').find('.planeacion').css('color','#513388');
			
			$(this).css('background-color','#ede3fc');
			recogertodos($(this));
		}else{
			$(this).find('.afs').attr('src','/Segplan/img/administracion-funcional.png');
			$(this).find('.form-group').find('.instrumentos').css('color','#FFFFFF');
			$(this).find('.form-group').find('.planeacion').css('color','#FFFFFF');
			
			$(this).css('background-color','');
		}
	});
	
	function recogertodos(thisnot){
		var todos = $('.item-drop').not($(thisnot));
		
		
		for (var i = 0; i < todos.length; i++) {
			
				
				if($(todos[i]).hasClass('instrumento')){
					
					$(todos[i]).find('.ip').attr('src','/Segplan/img/instrumentos-de-planeacion.png');
					$(todos[i]).find('.form-group').find('.instrumentos').css('color','#FFFFFF');
					$(todos[i]).find('.form-group').find('.planeacion').css('color','#FFFFFF');
					$(todos[i]).css('background-color','');
					
					
					
				}else if ($(todos[i]).hasClass('bancos')) {
					$(todos[i]).find('.bp').attr('src','/Segplan/img/banco-distrital.png');
					$(todos[i]).find('.form-group').find('.instrumentos').css('color','#FFFFFF');
					$(todos[i]).find('.form-group').find('.planeacion').css('color','#FFFFFF');
					$(todos[i]).css('background-color','');
					
				}else if ($(todos[i]).hasClass('planes')) {
					$(todos[i]).find('.accion').attr('src','/Segplan/img/plan-de-accion.png');
					$(todos[i]).find('.instrumentos').css('color','#FFFFFF');
					
					$(todos[i]).css('background-color','');
				}else if ($(todos[i]).hasClass('reportes')) {
					$(todos[i]).find('.rp').attr('src','/Segplan/img/reportes.png');
					$(todos[i]).find('.form-group').find('.instrumentos').css('color','#FFFFFF');
					
					$(todos[i]).css('background-color','');
					
				}else if ($(todos[i]).hasClass('administracion')) {
					$(todos[i]).find('.afs').attr('src','/Segplan/img/administracion-funcional.png');
					$(todos[i]).find('.form-group').find('.instrumentos').css('color','#FFFFFF');
					$(todos[i]).find('.planeacion').css('color','#FFFFFF');
					
					$(todos[i]).css('background-color','');
				}
			
		}
	}
});