var incidenteForm;

$(document).ready(function() {
	
    incidenteForm = {
    		ini : 					true,
    		incidente:				function(){  return  $("#idIncidenteBase").val(); },
    		tipoSolicitud:			function(){  return  $("#tipoSolicitud").val(); },
    		sistema:				function(){  return  $("#sistema").val(); } ,
    		proceso:				function(){  return  $("#proceso").val(); },
    		subProceso:				function(){  return  $("#subProceso").val(); },
    		
    		secuencia:				function(){  return  $("#txtSecuencia").val(); },
    		descripcion:			function(){  return  $("#txtDescripcion").val(); },
    		flagResolucion:			function(){  return  $("#flgResolucion").prop("checked")==true?"S":"N"; },
    		
    		solucion:				function(){  return  $("#idSolucion").val(); },
    		nroSolucion:			function(){  return  $("#txtNroSolucion").val(); },
    		prioridad:				function(){  return  $("#txtPrioridad").val(); },
    		descripcionSolucion:	function(){  return  $("#txtDescripcionSolucion").val(); },
    		
    		url:{
    			form:"/atencion-incidente/incidente"
    		},
    		urlCargar:{
    			form:"/atencion-incidente/incidente/cargar"
    		},
    		btnGuardar:$("#btn_guardar"),
    		btnLimpiar:$("#btn_limpiar"),
    		
    		urlSolucion:{
    			form:"/atencion-incidente/incidente/solucion"
    		},
    		urlCargarSolucion:{
    			form:"/atencion-incidente/incidente/solucion/cargar"
    		},
    		urlEliminarSolucion:{
    			form:"/atencion-incidente/incidente/solucion/eliminar"
    		},
    		btnGuardarSolucion:$("#btn_guardar_solucion"),
    		btnLimpiarSolucion:$("#btn_limpiar_solucion"),
    }
    
    incidenteForm.tbIncidentes = $('#incidentes');
    
    incidenteForm.getParams = function() {
    	return {
    			idIncidenteBase:	incidenteForm.incidente(),
    			idTipoSolicitud:	incidenteForm.tipoSolicitud(),
    			idSistema:			incidenteForm.sistema(),
    			idProceso:			incidenteForm.proceso(),
    			idSubproceso:		incidenteForm.subProceso(),
    			
    			nuSecuencia:		incidenteForm.secuencia(),
    			txtDescripcion:		incidenteForm.descripcion(),
    			flgResolucion:		incidenteForm.flagResolucion(),
    	};
    };
    
    incidenteForm.getParamsSolucion = function() {
    	return {
    			idSolucion: 		incidenteForm.solucion(),
    			idIncidenteBase:	incidenteForm.incidente(),
    			txtDescripcion:		incidenteForm.descripcionSolucion(),
    			nuSecuencia: 		null,
    			nuPrioridad: 		incidenteForm.prioridad(),
    			nuVecesUso: 		"0",
    	};
    };
    
    incidenteForm.limpiar = function(){
    	$("#idIncidenteBase").val("");
    	$("#txtSecuencia").val("");
    	$("#txtDescripcion").val("");
    	$("#flgResolucion").prop("checked", false);
    	//$("#tipoSolicitud").focus();
    }
    
    incidenteForm.validacion = function(){
    	
    	if ( incidenteForm.tipoSolicitud() == "" ){
    		return false;
    	}
    	
    	if ( incidenteForm.sistema() == "" ){
    		return false
    	}
    	
    	if ( incidenteForm.proceso() == "" ){
    		return false
    	}
    	
    	if ( incidenteForm.subProceso() == "" ){
    		return false
    	}
    	
    	return true;
    }
    
    incidenteForm.btn_buscarIncidente = function() {
    	
    	if (!incidenteForm.validacion()){
    		alert("Debe seleccionar los filtro.");
    		return false;
    	}
    	
    	incidenteForm.ini = false;
    	
    	var table = incidenteForm.tbIncidentes.DataTable();
    	table.ajax.reload();
    	
    	$("#tipoSolicitud").focus();
    }

    incidenteForm.tbIncidentes.DataTable(
    	{
    		searching: false,
    		lengthChange: false,
    		pageLength: 10,
    		//data: data,
            ajax: {
                    url: '/atencion-incidente/incidente/buscar',
                    "type": "POST",
                    data: incidenteForm.getParams,
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
        		{ data: 'nuSecuencia',"orderable": true },
        		{ data: 'txtDescripcion' },
        		{ data: 'flgResolucion',"orderable": false },
        		{ data: 'opcion',"orderable": false ,
                  render:function(data, type, row){
                	 var  btnEvaluar = '<button class="optBtn" style="width: 70px; float: left; margin-right: 8px;" ><a href="/atencion-incidente/incidente/'+row.idIncidenteBase +'/agregarValorClave">Agregar</a></button>';
                	 btnEvaluar += '<button class="optBtn btn_cargar" style="width: 70px; float: left; margin-right: 8px;" data-id="' + row.idIncidenteBase + '" >Modificar</button>';
                	 btnEvaluar += '<button class="optBtn btn_soluciones" style="width: 70px; float: left;" data-id="' + row.idIncidenteBase + '" >Soluciones</button>';
                	 //if(row.estado=="Atendido" || row.estado=="Rechazado"){btnEvaluar="";}
                	 return btnEvaluar;
                  }
        		}
    		]
    	}

    );
    
    incidenteForm.validacionRegistroIncidente = function(){
    	
    	if (incidenteForm.descripcion() == ""){
    		return false;
    	}
    	return true;
    }
    
    incidenteForm.btnLimpiar.on("click", incidenteForm.limpiar);
    
    incidenteForm.btnGuardar.on("click",function(){

		if(!incidenteForm.validacion()){
			alert("Debe seleccionar los campos.");
			return false;
		}
		
		if(!incidenteForm.validacionRegistroIncidente()){
			alert("Debe ingresar por lo menos una descripcion.");
			return false;
		}
		
		if( !confirm("Esta seguro de guardar el incidente") ){
			return false;
		}

		var data = incidenteForm.getParams();
		//console.log(data);
		incidenteForm.registrarIncidente(data);

	});
    
    incidenteForm.registrarIncidente = function(data){
		
    	incidenteForm.btnGuardar.attr("disabled", true);

		$.post(incidenteForm.url.form, data, function(rsp){
			incidenteForm.btnGuardar.attr("disabled", false);
			incidenteForm.btn_buscarIncidente();
			incidenteForm.limpiar();
			//alert(rsp.mensaje);
		},'json')
	}
    
    $(document).on('click', '.btn_cargar', function(){
    	var data = {idIncidenteBase: $(this).attr("data-id")};
    	incidenteForm.cargarIncidente(data);
    });
    
    incidenteForm.cargarIncidente = function(data){
		$.post(incidenteForm.urlCargar.form, data, function(rsp){
			$("#idIncidenteBase").val(rsp.incidente.idIncidenteBase);
			$("#txtSecuencia").val(rsp.incidente.nuSecuencia);
    		$("#txtDescripcion").val(rsp.incidente.txtDescripcion);
    		$("#flgResolucion").prop( "checked", rsp.incidente.flgResolucion == "S"?true:false );
				
		},'json')
	}
    
    $(document).on('click', '.btn_soluciones', function(){
    	incidenteForm.buscarSolucion($(this).attr("data-id"));
    });
    
    incidenteForm.buscarSolucion = function(data){
    	$("#contentSolucion").css("display", "block");
    	$("#idIncidenteBase").val(data);
    	var table = incidenteForm.tbSoluciones.DataTable();
    	table.ajax.reload();
    	//var strAncla=$(this).attr('href'); //id del ancla
		$('body,html').stop(true,true).animate({				
			scrollTop: $("#contentSolucion").offset().top
		},1000);
	}
    
    
    incidenteForm.tbSoluciones = $('#soluciones');
    
    incidenteForm.tbSoluciones.DataTable(
        	{
        		searching: false,
        		lengthChange: false,
        		pageLength: 10,
        		//data: data,
                ajax: {
                        url: '/atencion-incidente/incidente/solucion/buscar',
                        "type": "POST",
                        data: incidenteForm.getParamsSolucion,
                        //dataSrc: 'data'
                        dataSrc: function(json){
                        	return json.soluciones
                        }
                },
        		columns: [
            		{ data: 'nuSecuencia',"orderable": true },
            		{ data: 'txtDescripcion' },
            		{ data: 'nuPrioridad',"orderable": false },
            		{ data: 'nuVecesUso',"orderable": false },
            		{ data: 'opcion',"orderable": false ,
                      render:function(data, type, row){
                    	 var btnEvaluar = '<button class="optBtn btn_eliminar_solucion" style="width: 70px; float: left; margin-right: 8px;" data-id="' + row.idSolucion + '" >Eliminar</button>';
                    	 btnEvaluar += '<button class="optBtn btn_cargar_solucion" style="width: 70px; float: left; margin-right: 8px;" data-id="' + row.idSolucion + '" >Modificar</button>';
                    	 return btnEvaluar;
                      }
            		}
        		]
        	}

    );
    
    incidenteForm.limpiarSolucion = function(){
    	$("#idSolucion").val("");
    	$("#txtNroSolucion").val("");
    	$("#txtPrioridad").val("");
    	$("#txtDescripcionSolucion").val("");
    }
    
    incidenteForm.validacionRegistroSolucion = function(){
    	
    	if ( incidenteForm.descripcionSolucion() == "" ){
    		return false;
    	}
    	
    	if ( incidenteForm.prioridad() == "" ){
    		return false;
    	}
    	
    	return true;
    }
    
    incidenteForm.btnLimpiarSolucion.on("click", incidenteForm.limpiarSolucion);
    
    incidenteForm.btnGuardarSolucion.on("click",function(){

		if(!incidenteForm.validacionRegistroSolucion()){
			alert("Debe ingresar una descripcion y una prioridad.");
			return false;
		}

		if( !confirm("Esta seguro de guardar la solucion") ){
			return false;
		}

		var data = incidenteForm.getParamsSolucion();
		//console.log(data);
		incidenteForm.registrarSolucion(data);

	});
    
    incidenteForm.registrarSolucion = function(data){
		
    	incidenteForm.btnGuardarSolucion.attr("disabled", true);

		$.post(incidenteForm.urlSolucion.form, data, function(rsp){
			incidenteForm.btnGuardarSolucion.attr("disabled", false);
			incidenteForm.buscarSolucion($("#idIncidenteBase").val());
			incidenteForm.limpiarSolucion();
		},'json')
	}
    
    $(document).on('click', '.btn_cargar_solucion', function(){
    	var data = {idSolucion: $(this).attr("data-id")};
    	incidenteForm.cargarSolucion(data);
    });
    
    incidenteForm.cargarSolucion = function(data){
		$.post(incidenteForm.urlCargarSolucion.form, data, function(rsp){
			$("#idSolucion").val(rsp.solucion.idSolucion);
			$("#txtNroSolucion").val(rsp.solucion.nuSecuencia);
    		$("#txtPrioridad").val(rsp.solucion.nuPrioridad);
    		$("#txtDescripcionSolucion").val(rsp.solucion.txtDescripcion);
				
		},'json')
	}
    
    $(document).on('click', '.btn_eliminar_solucion', function(){
    	
    	if( !confirm("Esta seguro de eliminar la solucion") ){
			return false;
		}
    	
    	var data = {idSolucion: $(this).attr("data-id")};
    	incidenteForm.eliminarSolucion(data);
    });
    
    incidenteForm.eliminarSolucion = function(data){
		$.post(incidenteForm.urlEliminarSolucion.form, data, function(rsp){
			incidenteForm.buscarSolucion($("#idIncidenteBase").val());
		},'json')
	}

    /*------------------------------------------------------------------*/
    
//    $("#nrocti").on('blur',function(){
//    	
//    	var reg = /^CTI[0-9]{7}$/;
//    	var v = $.trim($(this).val());
//    	
//    	if( v.length > 0 ){
//    		
//    		if( !reg.test(v) ){
//    			alert("Debe ingresar el Nro. de CTI con formato correcto: CTINNNNNNN.");
//    			$(this).val("");
//    			$(this).focus();
//    		}
//    	}
//    })
    
//    $("#nroSS").on('blur',function(){
//    	
//    	var reg = /^SS[0-9]{5}$/;
//    	var v = $.trim($(this).val());
//    	
//    	if( v.length > 0 ){
//    		
//    		if( !reg.test(v) ){
//    			alert("Debe ingresar el Nro. SS con formato correcto: SSNNNNN.");
//    			$(this).val("");
//    			$(this).focus();
//    		}
//    	}
//    })
//    
    $("#sistema").change(function(){
		
		var data = {
				idSistema:$(this).val()
		}
		
		$.get("/atencion-incidente/incidente/getListProcesos",data,function(rsp){
			
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
			
			var html = "<option value=''>Seleccionar</option>";
			
			for(var i in rsp){
				html += "<option value='"+ i +"'>"+ rsp[i] +"</option>";
			}
			
			$("#subProceso").html("");
			$("#subProceso").append(html);
			
		},'json')
		
	});
	
	$("#btn_buscarIncidente").on("click", incidenteForm.btn_buscarIncidente);
    /*------------------------------------------------------*/
    
} );