var validaRegistroDetForm;

$(document).ready(function() {
		
	    validaRegistroDetForm = {
	    		ini : 					true,
	    		tipoSolicitud:			function(){  return  $("#tipoSolicitud").val(); },
	    		sistema:				function(){  return  $("#sistema").val(); } ,
	    		proceso:				function(){  return  $("#procesos").val(); },
	    		subProceso:				function(){  return  $("#subProcesos").val(); },
	    		
	    		secuencia:				function(){  return  $("#txtSecuencia").val(); },
	    		numClave:    			function(){  return  $("#txtNumClave").val(); },
	    		tipClave:				function(){  return  $("#tipoClave").val(); },
	    		valorClave:				function(){  return  $("#txtValorClave").val(); },
	    		logitud:				function(){  return  $("#numLogitudParam").val(); },
	    		formato:				function(){  return  $("#txtFormatoParam").val(); },
	    	    		
	    		url:{
	    			form:"/atencion-incidente/validaRegistro/registrarDetalle"
	    		},
	    		urlCargar:{
	    			form:"/atencion-incidente/validaRegistro/cargarDetalle"
	    		},
	    		urlEliminar:{
	    			form:"/atencion-incidente/validaRegistro/eliminarDetalle"
	    		},
	    		btnGuardar:$("#btn_guardarDet"),
	    		btnLimpiar:$("#btn_limpiarDet"),
	    		
	    	
	    }

	    
	    validaRegistroDetForm.tbvalidaRegistroDet = $('#validaRegistroDetList');
	    

	    validaRegistroDetForm.getParams = function() {
	    	return {
	    			idTipoSolicitud:	validaRegistroDetForm.tipoSolicitud(),
	    			idSistema:			validaRegistroDetForm.sistema(),
	    			idProceso:			validaRegistroDetForm.proceso(),
	    			idSubProceso:		validaRegistroDetForm.subProceso(),
	    			numSecuencia:		validaRegistroDetForm.secuencia(),
	    			numClave:  			validaRegistroDetForm.numClave(),
	    			tipClave:			validaRegistroDetForm.tipClave(),
	    			txtValorClave :		validaRegistroDetForm.valorClave(),
	    			numLogitudParam:	validaRegistroDetForm.logitud(),
	    		    txtFormatoParam :	validaRegistroDetForm.formato()
	    	};
	    };
	
	     validaRegistroDetForm.tbvalidaRegistroDet.DataTable(
	     	{
	     		searching: false,
	     		lengthChange: false,
	     		pageLength: 10,
	     		//data: data,
	             ajax: {
	                     url: '/atencion-incidente/validaRegistro/buscarDetalle',
	                     "type": "POST",
	                     data: validaRegistroDetForm.getParams,
	                     //dataSrc: 'data'
	                     dataSrc: function(json){
	                    	 console.log(json);
	                    	 
	                     	if(json.secuencia != undefined && json.secuencia != null && json.secuencia != ""){
	                     		console.log(json.secuencia);
	                     		$("#txtSecuencia").val(json.secuencia)
	                     	}
	                     	return json.data
	                     }
	             },
	     		columns: [
	         		{ data: 'numClave',"orderable": true },
	         		{ data: 'tipClave' },
	         		{ data: 'txtValorClave' },
	         		{ data: 'numLogitudParam' },
	         		{ data: 'txtFormatoParam' },
	         		{ data: 'opcion',"orderable": false ,
	                   render:function(data, type, row){
	                 	 var  btnEvaluar =  '<button class="optBtn btn_cargarDet" style="width: 70px; float: left; margin-right: 8px;" data-id="' + row.numClave + '" >Modificar</button>';
	                 	 btnEvaluar += '<button class="optBtn btn_eliminarDet" style="width: 70px; float: left;" data-id="' + row.numClave + '" >Eliminar</button>';
	                 	 return btnEvaluar;
	                   }
	         		}
	     		]
	     	}

	     );
	     
	     $(document).on('click', '.btn_cargarDet', function(){
	     	var data = {numClave: $(this).attr("data-id"), 
	     				numSecuencia:$("#txtSecuencia").val() };
	     	console.log(data);
	     	validaRegistroDetForm.cargarValidaRegistroDet(data);
	     });
	     
	     validaRegistroDetForm.cargarValidaRegistroDet = function(data){
	 		$.post(validaRegistroDetForm.urlCargar.form, data, function(rsp){
	 			console.log(rsp);
	 			$("#txtNumClave").val(rsp.validaRegistroDet.numClave);
	     		$("#txtValorClave").val(rsp.validaRegistroDet.txtValorClave);
	     		$("#numLogitudParam").val(rsp.validaRegistroDet.numLogitudParam);
	     		$("#txtFormatoParam").val(rsp.validaRegistroDet.txtFormatoParam);
	     		$("#tipoClave").val(rsp.validaRegistroDet.tipClave);
	 				
	 		},'json')
	 	}
	     
	     
	     validaRegistroDetForm.limpiar = function(){

		      	$("#txtNumClave").val(""); 
		     	$("#txtValorClave").val("");
		     	$("#numLogitudParam").val("");
		     	$("#txtFormatoParam").val(""); 

		     }
		     
	     validaRegistroDetForm.btnLimpiar.on("click", validaRegistroDetForm.limpiar);
	     
	     
	
	     $(document).on('click', '.btn_eliminarDet', function(){
	     	
	     	if( !confirm("Esta seguro de eliminar el detalle de Valor Clave") ){
	 			return false;
	 		}
	     	
	     	var data = {
	     			numClave: $(this).attr("data-id"), 
     				numSecuencia:$("#txtSecuencia").val()		
	     	};
	     	validaRegistroDetForm.eliminar(data);
	     });
	     
	     validaRegistroDetForm.eliminar = function(data){
	 		$.post(validaRegistroDetForm.urlEliminar.form, data, function(rsp){
	 			validaRegistroDetForm.btn_buscarValidaRegistro();
	 			validaRegistroDetForm.limpiar();
	 		},'json')
	 	}

	     
	     
	     validaRegistroDetForm.btnGuardar.on("click",function(){

	 		if(!validaRegistroDetForm.validacion()){
	 			alert("Debe seleccionar los campos.");
	 			return false;
	 		}
	 		
	 		if(!validaRegistroDetForm.validacionRegistro()){
	 			alert("Debe ingresar por lo menos una descripcion.");
	 			return false;
	 		}
	 		
	 		if( !confirm("Esta seguro de guardar el detalle Valor Clave") ){
	 			return false;
	 		}

	 		var data = validaRegistroDetForm.getParams();
	 		//console.log(data);
	 		validaRegistroDetForm.registrarValidaRegistroDet(data);

	 	});
	     
	     validaRegistroDetForm.validacion = function(){
	     	
	     	if ( validaRegistroDetForm.tipoSolicitud() == "" ){
	     		return false;
	     	}
	     	
	     	if ( validaRegistroDetForm.sistema() == "" ){
	     		return false
	     	}
	     	
	     	if ( validaRegistroDetForm.proceso() == "" ){
	     		return false
	     	}
	     	
	     	if ( validaRegistroDetForm.subProceso() == "" ){
	     		return false
	     	}
	     	
	     	return true;
	     }
	     
	     validaRegistroDetForm.validacionRegistro = function(){
	     	
	     	if (validaRegistroDetForm.valorClave() == ""){
	     		return false;
	     	}
	     	return true;
	     }
	     
	     
	     validaRegistroDetForm.registrarValidaRegistroDet = function(data){
	 		
	    	 validaRegistroDetForm.btnGuardar.attr("disabled", true);

	 		$.post(validaRegistroDetForm.url.form, data, function(rsp){
	 			validaRegistroDetForm.btnGuardar.attr("disabled", false);
	 			validaRegistroDetForm.btn_buscarValidaRegistro();
	 			validaRegistroDetForm.limpiar();
	 			//alert(rsp.mensaje);
	 		},'json')
	 	}
	     
	     validaRegistroDetForm.btn_buscarValidaRegistro = function() {
	     	
	     	if (!validaRegistroDetForm.validacion()){
	     		alert("Debe seleccionar los filtro.");
	     		return false;
	     	}
	     	
	     	validaRegistroDetForm.ini = false;
	     	
	     	var table = validaRegistroDetForm.tbvalidaRegistroDet.DataTable();
	     	table.ajax.reload();
	     	
	     	$("#tipoSolicitud").focus();
	     }

	     
	     
	     $("#sistema").change(function(){
	 		
	 		var data = {
	 				idSistema:$(this).val()
	 		}
	 		
	 		$.get("/atencion-incidente/validaRegistro/getListProcesos",data,function(rsp){
	 			
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
	 		
	 		$.get("/atencion-incidente/validaRegistro/getListSubProcesos", data, function(rsp){
	 			
	 			var html = "<option value=''>Seleccionar</option>";
	 			
	 			for(var i in rsp){
	 				html += "<option value='"+ i +"'>"+ rsp[i] +"</option>";
	 			}
	 			
	 			$("#subProceso").html("");
	 			$("#subProceso").append(html);
	 			
	 		},'json')
	 		
	 	});
} ); 
	    	     