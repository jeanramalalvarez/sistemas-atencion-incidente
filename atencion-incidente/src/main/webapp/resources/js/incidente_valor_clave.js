var incidenteValorClaveFomr;

$(document).ready(function() {
   
    incidenteValorClaveFomr = {
    		ini : true,
    		
    		idIncidenteBase:	function(){  return  $("#idIncidenteBase").val(); } ,
    		valoresClaveIncidente:	function(){  return incidenteValorClaveFomr.getValoresClaveIncidente();  } ,
    		
    		urlValorClave:{
    			form:"/atencion-incidente/incidente/agregarValorClave"
    		},
    		btnGuardarValorClave:$("#btn_guardar_valor_clave"),
    }
    
    incidenteValorClaveFomr.getValoresClaveIncidente = function() {
    	var cadena = "";
    	/*$.each($("#valoresClaveIncidente").val(), function( index, value ) {
    		cadena +=value +",";
    	});*/
    	$.each($('#valoresClaveIncidente option'), function( index, value ) {
    		cadena +=value.value +",";
    	});
    	return cadena;
    }
    
    incidenteValorClaveFomr.getParamsValorClave = function() {
    	return {
    			idIncidenteBase		 :	incidenteValorClaveFomr.idIncidenteBase(),
    			valoresClaveIncidente:	incidenteValorClaveFomr.valoresClaveIncidente(),
    	};
    };
    
    incidenteValorClaveFomr.validacion = function(){
    	
    	if ( incidenteValorClaveFomr.tipoSolicitud() != "" )
    	{
    		return true;
    	}
    	
    	return false;
    }

    incidenteValorClaveFomr.processFormValorClave = function(data){
		
		//var tipo = nuevaSolicitud.form.tipoSolicitud.val();
		//var fn = "sendFormTipo" + tipo;
		
    	incidenteValorClaveFomr.btnGuardarValorClave.attr("disabled", true);

		$.post(incidenteValorClaveFomr.urlValorClave.form, data, function(rsp){
			
				//nuevaSolicitud.setIdSolicitud(rsp.idSolicitud,tipo);
				
				//if(rsp.estado == 0){
					incidenteValorClaveFomr.btnGuardarValorClave.attr("disabled", false);
					//incidenteValorClaveFomr.btn_buscarIncidente();
					//$("#txtDescripcion").val("");
					//$("#flgResolucion").prop("checked", false); 
					//alert(rsp.mensaje);
					//$("#descripcion").focus();
				//}
		},'json')
	}
    
    incidenteValorClaveFomr.btnGuardarValorClave.on("click",function(){
    	console.log("click");
		/*if(!incidenteValorClaveFomr.validacion()){
			return false;
		}
		
		if(!incidenteValorClaveFomr.validacionRegistro()){
			return false;
		}
		*/
		if( !confirm("Esta seguro de guardar el/los valor(es) clave?") ){

			return false;
		}
		
		//var	descripcionSol = $("#descripcion").val();
		
		//$("#descripcionSol").text(descripcionSol);
		//$("#descripcionSol").val(descripcionSol);

		var data = incidenteValorClaveFomr.getParamsValorClave();
		console.log(data);
		incidenteValorClaveFomr.processFormValorClave(data);

	});
    

    /*------------------------------------------------------------------*/
    
    $("#sistema").change(function(){
		
		var data = {
				idSistema:$(this).val()
		}
		
		$.get("/atencion-incidente/incidente/getListProcesos",data,function(rsp){
			
			//console.log("procesos")
			//console.log(rsp)
			
			var html = "<option value=''>Seleccionar</option>";
			
			for(var i in rsp){
				
				html += "<option value='"+ i +"'>"+ rsp[i] +"</option>";
			}
			
			$("#proceso").html("");
			$("#proceso").append(html);
			
		},'json')
		
	});


	$("#proceso").change(function(){
	
		var data = {
				idSistema:$('#sistema').val(),
				idProceso:$(this).val()
		}
		
		$.get("/atencion-incidente/incidente/getListSubProcesos", data, function(rsp){
			
			//console.log("procesos")
			//console.log(rsp)
			
			var html = "<option value=''>Seleccionar</option>";
			
			for(var i in rsp){
				
				html += "<option value='"+ i +"'>"+ rsp[i] +"</option>";
			}
			
			$("#subProceso").html("");
			$("#subProceso").append(html);
			
		},'json')
		
	});
    
    $("#btn_agregarVC").on("click",function(){
    	!$('#valoresClave option:selected').remove().appendTo('#valoresClaveIncidente');
    });
    
    $("#btn_quitarVC").on("click",function(){
    	!$('#valoresClaveIncidente option:selected').remove().appendTo('#valoresClave');
    });
    
    //$('.pasartodos').click(function() { $('#valoresClave option').each(function() { $(this).remove().appendTo('#valoresClaveIncidente'); }); });
	//$('.quitartodos').click(function() { $('#valoresClaveIncidente option').each(function() { $(this).remove().appendTo('#valoresClave'); }); });
    
    /*------------------------------------------------------*/

} );