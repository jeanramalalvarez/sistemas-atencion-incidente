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
    		
    		solucionTmp:			function(){  return  $("#idSolucionTmp").val(); },
    		tipoSolucion:			function(){  return  $("#tipoSolucion").val(); },
    		secuenciaSolucionSetup:	function(){  return  $("#numSecuenciaSolucionSetup").val(); },
    		glosa:					function(){  return  $("#txtGlosa").val(); },
    		sustento:				function(){  return  $("#txtSustento").val(); },
    		descripcionSolucionSetup:	function(){  return  $("#txtDescripcionSolucionSetup").val(); },
    		ruta:					function(){  return  $("#txtRuta").val(); },
    		
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
    		

    		urlSolucionSetup:{
    			form:"/atencion-incidente/incidente/solucionSetup"
    		},
    		urlCargarSolucionSetup:{
    			form:"/atencion-incidente/incidente/solucionSetup/cargar"
    		},
    		urlEliminarSolucionSetup:{
    			form:"/atencion-incidente/incidente/solucionSetup/eliminar"
    		},
    		btnGuardarSolucionSetup:$("#btn_guardar_solucion_setup"),
    		btnLimpiarSolucionSetup:$("#btn_limpiar_solucion_setup"),
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
    
    incidenteForm.getParamsSolucionSetup = function() {
    	return {
    			idSolucion: 		incidenteForm.solucionTmp(),
    			idTipoSolucion: 	incidenteForm.tipoSolucion(),
    			numSecuencia: 		incidenteForm.secuenciaSolucionSetup(),
    			txtGlosa: 			incidenteForm.glosa(),
    			txtSustento: 		incidenteForm.sustento(),
    			txtDescripcion: 	incidenteForm.descripcionSolucionSetup(),
    			txtRuta: 			incidenteForm.ruta(),
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
    
    //Incidente - Inicio
    
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
    
    //Incidente - Fin
    
    //Solucion - Inicio
    
    $(document).on('click', '.btn_soluciones', function(){
    	incidenteForm.buscarSolucion($(this).attr("data-id"));
    });
    
    incidenteForm.buscarSolucion = function(data){
    	$("#contentSolucion").css("display", "block");
    	$("#idIncidenteBase").val(data);
    	var table = incidenteForm.tbSoluciones.DataTable();
    	table.ajax.reload();
    	
    	//var strAncla=$(this).attr('href'); //id del ancla
		$('body, html').stop(true, true).animate({
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
                    	 btnEvaluar += '<input type="hidden" name="idSolucion" value="' + row.idSolucion + '" />';
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
    
    $(document).on('click', '.btn_cargar_solucion', function(e){
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
    
    $(document).on('click', "#soluciones tbody tr", function(e){
    	if(e.target.nodeName == "TD"){
    		var idSolicitud = $(this).children().last().children().last().val();
    		incidenteForm.limpiarSolucionSetup();
    		incidenteForm.buscarSolucionSetup(idSolicitud);
    		$('.nav-tabs a[href="#tabSolucion"]').tab('show');
    	}
    });
    
    //Solucion - Fin
    
    //Solucion Setup - Inicio
    
    //$('#txtRuta').filestyle({buttonText: "Find file"});
    
    incidenteForm.buscarSolucionSetup = function(data){
    	$("#contentSolucion").css("display", "block");
    	$("#idSolucionTmp").val(data);
    	var table = incidenteForm.tbSolucionesSetup.DataTable();
    	table.ajax.reload();
	}
    
    incidenteForm.tbSolucionesSetup = $('#solucionesSetup');
    
    incidenteForm.tbSolucionesSetup.DataTable(
        	{
        		searching: false,
        		lengthChange: false,
        		pageLength: 10,
        		//data: data,
                ajax: {
                        url: '/atencion-incidente/incidente/solucionSetup/buscar',
                        "type": "POST",
                        data: incidenteForm.getParamsSolucionSetup,
                        //dataSrc: 'data'
                        dataSrc: function(json){
                        	return json.solucionesSetup
                        }
                },
        		columns: [
            		{ data: 'numSecuencia',"orderable": true },
            		{ data: 'deTipoSolucion' },
            		{ data: 'txtGlosa' },
            		{ data: 'txtSustento' },
            		{ data: 'txtDescripcion' },
            		{ data: 'txtAnexo' },
            		{ data: 'opcion',"orderable": false ,
                      render:function(data, type, row){
                    	 var btnEvaluar = '<button class="optBtn btn_eliminar_solucion_setup" style="width: 70px; float: left; margin-right: 8px;" data-id="' + row.numSecuencia + '" >Eliminar</button>';
                    	 btnEvaluar += '<button class="optBtn btn_cargar_solucion_setup" style="width: 70px; float: left; margin-right: 8px;" data-id="' + row.numSecuencia + '" >Modificar</button>';
                    	 return btnEvaluar;
                      }
            		}
        		]
        	}

    );
    
    incidenteForm.limpiarSolucionSetup = function(){
    	$("#tipoSolucion").val("");
    	$("#txtGlosa").val("");
    	$("#txtSustento").val("");
    	$("#txtDescripcionSolucionSetup").val("");
    	//$("#txtRuta").val("");
    	$("#txtRuta").filestyle('clear');
    }
    
    incidenteForm.validacionRegistroSolucionSetup = function(){
    	
    	if ( incidenteForm.tipoSolicitud() == "" ){
    		return false;
    	}
    	
    	if ( incidenteForm.glosa() == "" ){
    		return false;
    	}
    	
    	if ( incidenteForm.sustento() == "" ){
    		return false;
    	}
    	
    	if ( incidenteForm.descripcionSolucionSetup() == "" ){
    		return false;
    	}
    	
    	//if ( incidenteForm.ruta() == "" ){
    	if ( $("#txtRuta").parent().find("input[type=text]").val() == "" ){
    		return false;
    	}
    	
    	return true;
    }
    
    incidenteForm.btnLimpiarSolucionSetup.on("click", incidenteForm.limpiarSolucionSetup);
    
    incidenteForm.btnGuardarSolucionSetup.on("click",function(){
    	
    	if ( incidenteForm.solucionTmp() == "" ){
    		alert("Debe seleccionar una solucion.");
    		return;
    	}

		if(!incidenteForm.validacionRegistroSolucionSetup()){
			alert("Debe ingresar los datos solicitados.");
			return false;
		}

		if( !confirm("Esta seguro de guardar la configuracion de la solucion") ){
			return false;
		}

		var data = incidenteForm.getParamsSolucionSetup();
		//console.log(data);
		incidenteForm.registrarSolucionSetup(data);

	});
    
    incidenteForm.registrarSolucionSetup = function(data){
		
    	incidenteForm.btnGuardarSolucionSetup.attr("disabled", true);

		$.post(incidenteForm.urlSolucionSetup.form, data, function(rsp){
			incidenteForm.btnGuardarSolucionSetup.attr("disabled", false);
			incidenteForm.buscarSolucionSetup($("#idSolucionTmp").val());
			incidenteForm.limpiarSolucionSetup();
		},'json')
	}
    
    $(document).on('click', '.btn_cargar_solucion_setup', function(e){
    	var data = {idSolucion: incidenteForm.solucionTmp(), numSecuencia: $(this).attr("data-id")};
    	incidenteForm.cargarSolucionSetup(data);
    });
    
    incidenteForm.cargarSolucionSetup = function(data){
		$.post(incidenteForm.urlCargarSolucionSetup.form, data, function(rsp){
			$("#numSecuenciaSolucionSetup").val(rsp.solucionSetup.numSecuencia);
			$("#tipoSolucion").val(rsp.solucionSetup.idTipoSolucion);
			$("#txtGlosa").val(rsp.solucionSetup.txtGlosa);
			$("#txtSustento").val(rsp.solucionSetup.txtSustento);
    		$("#txtDescripcionSolucionSetup").val(rsp.solucionSetup.txtDescripcion);
    		//$("#txtRuta").val(rsp.solucionSetup.txtRuta);
    		$("#txtRuta").parent().find("input[type=text]").val(rsp.solucionSetup.txtAnexo);
		},'json')
	}
    
    $(document).on('click', '.btn_eliminar_solucion_setup', function(){
    	
    	if( !confirm("Esta seguro de eliminar la configuracion de la solucion") ){
			return false;
		}
    	
    	var data = {idSolucion: incidenteForm.solucionTmp(), numSecuencia: $(this).attr("data-id")};
    	incidenteForm.eliminarSolucionSetup(data);
    });
    
    incidenteForm.eliminarSolucionSetup = function(data){
		$.post(incidenteForm.urlEliminarSolucionSetup.form, data, function(rsp){
			incidenteForm.buscarSolucionSetup($("#idSolucionTmp").val());
		},'json')
	}
    
    //Solucion Setup - Fin

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