var validaRegistroForm;



$(document).ready(function() {
	
    validaRegistroForm = {
    		ini : 					true,
    		tipoSolicitud:			function(){  return  $("#tipoSolicitud").val(); },
    		sistema:				function(){  return  $("#sistema").val(); } ,
    		proceso:				function(){  return  $("#proceso").val(); },
    		subProceso:				function(){  return  $("#subProceso").val(); },
    		
    		secuencia:				function(){  return  $("#txtSecuencia").val(); },
    		descripcion:			function(){  return  $("#txtDescripcion").val(); },
    		observacion:			function(){  return  $("#txtObservacion").val(); },
    	    		
    		url:{
    			form:"/atencion-incidente/validaRegistro"
    		},
    		urlCargar:{
    			form:"/atencion-incidente/validaRegistro/cargar"
    		},
    		urlEliminar:{
    			form:"/atencion-incidente/validaRegistro/eliminar"
    		},
    		btnGuardar:$("#btn_guardar"),
    		btnLimpiar:$("#btn_limpiar"),
    		
    		urlSolucion:{
    			form:"/atencion-incidente/validaRegistro/solucion"
    		},
    		urlCargarSolucion:{
    			form:"/atencion-incidente/validaRegistro/solucion/cargar"
    		},
    		urlEliminarSolucion:{
    			form:"/atencion-incidente/validaRegistro/solucion/eliminar"
    		},
    		btnGuardarSolucion:$("#btn_guardar_solucion"),
    		btnLimpiarSolucion:$("#btn_limpiar_solucion"),
    }

	$(document).on("click", "#btn_buscarValidaRegistro", function(){
			validaRegistroForm.btn_buscarValidaRegistro();
	});

    
    validaRegistroForm.tbvalidaRegistro = $('#validaRegistros');
    
    validaRegistroForm.getParams = function() {
    	return {
    			idTipoSolicitud:	validaRegistroForm.tipoSolicitud(),
    			idSistema:			validaRegistroForm.sistema(),
    			idProceso:			validaRegistroForm.proceso(),
    			idSubProceso:		validaRegistroForm.subProceso(),
    			
    			numSecuencia:		validaRegistroForm.secuencia(),
    			txtDescripcion:		validaRegistroForm.descripcion(),
    			txtObservacion:		validaRegistroForm.observacion(),
    	};
    };
    
    validaRegistroForm.getParamsSolucion = function() {
    	return {
    			idSolucion: 		validaRegistroForm.solucion(),
    			idIncidenteBase:	validaRegistroForm.incidente(),
    			txtDescripcion:		validaRegistroForm.descripcionSolucion(),
    			nuSecuencia: 		null,
    			nuPrioridad: 		validaRegistroForm.prioridad(),
    			nuVecesUso: 		"0",
    	};
    };
    
    validaRegistroForm.limpiar = function(){
    	$("#txtSecuencia").val("");
    	$("#txtDescripcion").val("");
    	$("#txtObservacion").val("");
    	//$("#tipoSolicitud").focus();
    }
    
    validaRegistroForm.validacion = function(){
    	
    	if ( validaRegistroForm.tipoSolicitud() == "" ){
    		return false;
    	}
    	
    	if ( validaRegistroForm.sistema() == "" ){
    		return false
    	}
    	
    	if ( validaRegistroForm.proceso() == "" ){
    		return false
    	}
    	
    	if ( validaRegistroForm.subProceso() == "" ){
    		return false
    	}
    	
    	return true;
    }
    
    validaRegistroForm.btn_buscarValidaRegistro = function() {
    	
    	if (!validaRegistroForm.validacion()){
    		alert("Debe seleccionar los filtro.");
    		return false;
    	}
    	
    	validaRegistroForm.ini = false;
    	
    	var table = validaRegistroForm.tbvalidaRegistro.DataTable();
    	table.ajax.reload();
    	
    	$("#tipoSolicitud").focus();
    }

    validaRegistroForm.tbvalidaRegistro.DataTable(
    	{
    		searching: false,
    		lengthChange: false,
    		pageLength: 10,
    		//data: data,
            ajax: {
                    url: '/atencion-incidente/validaRegistro/buscar',
                    "type": "POST",
                    data: validaRegistroForm.getParams,
                    //dataSrc: 'data'
                    dataSrc: function(json){
                    	if(json.secuencia != undefined && json.secuencia != null && json.secuencia != ""){
                    		console.log(json.secuencia);
                    		$("#txtSecuencia").val(json.secuencia)
                    	}
                    	return json.data
                    }
            },
    		columns: [
        		{ data: 'numSecuencia',"orderable": true },
        		{ data: 'txtDescripcion' },
        		{ data: 'txtObservacion' },
        		{ data: 'opcion',"orderable": false ,
                  render:function(data, type, row){
                	 var  btnEvaluar = '<button class="optBtn" style="width: 70px; float: left; margin-right: 8px;" ><a href="/atencion-incidente/validaRegistro/'+row.numSecuencia +'/mostrarValorClave">Detalle</a></button>';
                	 btnEvaluar += '<button class="optBtn btn_cargar" style="width: 70px; float: left; margin-right: 8px;" data-id="' + row.numSecuencia + '" >Modificar</button>';
                	 btnEvaluar += '<button class="optBtn btn_eliminar" style="width: 70px; float: left;" data-id="' + row.numSecuencia + '" >Eliminar</button>';
                	 //if(row.estado=="Atendido" || row.estado=="Rechazado"){btnEvaluar="";}
                	 return btnEvaluar;
                  }
        		}
    		]
    	}

    );
    
    validaRegistroForm.validacionRegistro = function(){
    	
    	if (validaRegistroForm.descripcion() == ""){
    		return false;
    	}
    	return true;
    }
    
    validaRegistroForm.btnLimpiar.on("click", validaRegistroForm.limpiar);
    
    validaRegistroForm.btnGuardar.on("click",function(){

		if(!validaRegistroForm.validacion()){
			alert("Debe seleccionar los campos.");
			return false;
		}
		
		if(!validaRegistroForm.validacionRegistro()){
			alert("Debe ingresar por lo menos una descripcion.");
			return false;
		}
		
		if( !confirm("Esta seguro de guardar la Palabra Clave") ){
			return false;
		}

		var data = validaRegistroForm.getParams();
		//console.log(data);
		validaRegistroForm.registrarValidaRegistro(data);

	});
    
    validaRegistroForm.registrarValidaRegistro = function(data){
		
    	validaRegistroForm.btnGuardar.attr("disabled", true);

		$.post(validaRegistroForm.url.form, data, function(rsp){
			validaRegistroForm.btnGuardar.attr("disabled", false);
			validaRegistroForm.btn_buscarValidaRegistro();
			validaRegistroForm.limpiar();
			//alert(rsp.mensaje);
		},'json')
	}
    
    $(document).on('click', '.btn_cargar', function(){
    	var data = {numSecuencia: $(this).attr("data-id")};
    	validaRegistroForm.cargarValidaRegistro(data);
    });
    
    validaRegistroForm.cargarValidaRegistro = function(data){
		$.post(validaRegistroForm.urlCargar.form, data, function(rsp){
			$("#txtSecuencia").val(rsp.validaRegistro.numSecuencia);
    		$("#txtDescripcion").val(rsp.validaRegistro.txtDescripcion);
    		$("#txtObservacion").val(rsp.validaRegistro.txtObservacion);
				
		},'json')
	}
    
    $(document).on('click', '.btn_eliminar', function(){
    	
    	if( !confirm("Esta seguro de eliminar la Palabra Clave") ){
			return false;
		}
    	
    	var data = {numSecuencia: $(this).attr("data-id")};
    	validaRegistroForm.eliminar(data);
    });
    
    validaRegistroForm.eliminar = function(data){
		$.post(validaRegistroForm.urlEliminar.form, data, function(rsp){
			validaRegistroForm.btn_buscarValidaRegistro();
			validaRegistroForm.limpiar();
		},'json')
	}

    
 /**************************************************************/
    
    

//    
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
	
    /*------------------------------------------------------*/
	

    
} );