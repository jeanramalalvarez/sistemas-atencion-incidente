var consultarIncidenteForm2;

$(document).ready(function() {
   
    consultarIncidenteForm2 = {
    		ini : true,
    		tipoSolicitud:	function(){  return  $("#tipoSolicitud").val(); },
    		sistema:	function(){  return  $("#sistema").val(); } ,
    		proceso:	function(){  return  $("#proceso").val(); },
    		subProceso:	function(){  return  $("#subProceso").val(); },
    		
    		secuencia:	function(){  return  $("#txtSecuencia").val(); },
    		descripcion:	function(){  return  $("#txtDescripcion").val(); },
    		flagResolucion:	function(){  return  $("#flgResolucion").prop("checked")==true?"S":"N"; },
    		
    		idIncidenteBase:	function(){  return  $("#idIncidenteBase").val(); } ,
    		valoresClaveIncidente:	function(){  return getValoresClaveIncidente();  } ,
    		
    		url:{
    			form:"/atencion-incidente/incidente"
    		},
    		btnGuardar:$("#btn_guardar"),
    		
    		urlValorClave:{
    			form:"/atencion-incidente/incidente/agregarValorClave"
    		},
    		btnGuardarValorClave:$("#btn_guardar_valor_clave"),
    }
    
    function getValoresClaveIncidente(){
    	var cadena = "";
    	$.each($("#valoresClaveIncidente").val(), function( index, value ) {
    		cadena +=value +",";
    	});
    	return cadena;
    }
    
    consultarIncidenteForm2.tbSolicitudes = $('#solicitudes');
    
    consultarIncidenteForm2.getParams = function() {
    	return {
    			idTipoSolicitud:	consultarIncidenteForm2.tipoSolicitud(),
    			idSistema:	consultarIncidenteForm2.sistema(),
    			idProceso:	consultarIncidenteForm2.proceso(),
    			idSubproceso:	consultarIncidenteForm2.subProceso(),
    			
    			nuSecuencia:	consultarIncidenteForm2.secuencia(),
    			txtDescripcion:	consultarIncidenteForm2.descripcion(),
    			flgResolucion:	consultarIncidenteForm2.flagResolucion(),
    	};
    };
    
    consultarIncidenteForm2.getParamsValorClave = function() {
    	return {
    			idIncidenteBase:	consultarIncidenteForm2.idIncidenteBase(),
    			valoresClaveIncidente:	consultarIncidenteForm2.valoresClaveIncidente(),
    	};
    };
    
    consultarIncidenteForm2.limpiar = function(){
    	$("#tipoSolicitud").val("");
    	$("#sistema").val("");
    	$("#proceso").val("");
    	$("#subProceso").val("");
    	$("#tipoSolicitud").focus();
    }
    
    consultarIncidenteForm2.validacion = function(){
    	
    	if ( consultarIncidenteForm2.tipoSolicitud() != "" )
    	{
    		return true
    	}
    	
    	if ( consultarIncidenteForm2.sistema() != "" )
    	{
    		return true
    	}
    	
    	if ( consultarIncidenteForm2.proceso() != "" )
    	{
    		return true
    	}
    	
    	if ( consultarIncidenteForm2.subProceso() != "" )
    	{
    		return true
    	}
    	
    	return false;
    }
    
    consultarIncidenteForm2.validacionRegistro = function(){
    	
    	if ( consultarIncidenteForm2.descripcion() != "" )
    	{
    		return true
    	}
    	
    	/*if ( consultarIncidenteForm2.flagResolucion() != "" )
    	{
    		return true
    	}*/
    	
    	return false;
    }
    
    consultarIncidenteForm2.btn_buscarIncidente = function() {
    	
    	if (!consultarIncidenteForm2.validacion()){
    		
    		alert("Debe seleccionar por lo menos un filtro");
    		return false;
    	}
    	
    	consultarIncidenteForm2.ini = false;
    	
    	var table = consultarIncidenteForm2.tbSolicitudes.DataTable();
    	table.ajax.reload();
    	
    	$("#tipoSolicitud").focus();
    }

    consultarIncidenteForm2.tbSolicitudes.DataTable(
    	{
    		searching: false,
    		lengthChange: false,
    		pageLength: 10,
    		//data: data,
            ajax: {
                    url: '/atencion-incidente/incidente/buscar',
                    "type": "POST",
                    data: consultarIncidenteForm2.getParams,
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
        		{ data: 'nuSecuencia',"orderable": false },
        		{ data: 'txtDescripcion' },
        		{ data: 'flgResolucion',"orderable": false },
        		{ data: 'accion',"orderable": false ,
                  render:function(data, type, row){
                	 var  btnEvaluar = '<button class="optBtn" style="width: 70px; float: left; margin-right: 8px;" ><a href="/atencion-incidente/incidente/'+row.idIncidenteBase +'/agregarValorClave">Agregar</a></button>';
                	 btnEvaluar += '<button class="optBtn" style="width: 70px; float: left; margin-right: 8px;" ><a href="/atencion-incidente/incidente/'+row.idIncidenteBase +'/modificar">Modificar</a></button>';
                	 btnEvaluar += '<button class="optBtn" style="width: 70px; float: left;" ><a href="/atencion-incidente/incidente/'+row.idIncidenteBase +'/soluciones">Soluciones</a></button>';
                	 //if(row.estado=="Atendido" || row.estado=="Rechazado"){btnEvaluar="";}
                	 return btnEvaluar;
                  }
        		}
    		]
    	}

    );
    
    
    consultarIncidenteForm2.processForm = function(data){
		
		//var tipo = nuevaSolicitud.form.tipoSolicitud.val();
		//var fn = "sendFormTipo" + tipo;
		
    	consultarIncidenteForm2.btnGuardar.attr("disabled", true);

		$.post(consultarIncidenteForm2.url.form,data,function(rsp){
			
				//nuevaSolicitud.setIdSolicitud(rsp.idSolicitud,tipo);
				
				//if(rsp.estado == 0){
					consultarIncidenteForm2.btnGuardar.attr("disabled", false);
					consultarIncidenteForm2.btn_buscarIncidente();
					$("#txtDescripcion").val("");
					$("#flgResolucion").prop("checked", false); 
					//alert(rsp.mensaje);
					//$("#descripcion").focus();
				//}
		},'json')
	}
    
    consultarIncidenteForm2.btnGuardar.on("click",function(){

		if(!consultarIncidenteForm2.validacion()){
			return false;
		}
		
		if(!consultarIncidenteForm2.validacionRegistro()){
			return false;
		}
		
		if( !confirm("Esta seguro de guardar solicitud?") ){

			return false;
		}
		
		//var	descripcionSol = $("#descripcion").val();
		
		//$("#descripcionSol").text(descripcionSol);
		//$("#descripcionSol").val(descripcionSol);

		var data = consultarIncidenteForm2.getParams();
		console.log(data);
		consultarIncidenteForm2.processForm(data);

	});
    
    consultarIncidenteForm2.processFormValorClave = function(data){
		
		//var tipo = nuevaSolicitud.form.tipoSolicitud.val();
		//var fn = "sendFormTipo" + tipo;
		
    	consultarIncidenteForm2.btnGuardarValorClave.attr("disabled", true);

		$.post(consultarIncidenteForm2.urlValorClave.form,data,function(rsp){
			
				//nuevaSolicitud.setIdSolicitud(rsp.idSolicitud,tipo);
				
				//if(rsp.estado == 0){
					consultarIncidenteForm2.btnGuardarValorClave.attr("disabled", false);
					//consultarIncidenteForm2.btn_buscarIncidente();
					//$("#txtDescripcion").val("");
					//$("#flgResolucion").prop("checked", false); 
					//alert(rsp.mensaje);
					//$("#descripcion").focus();
				//}
		},'json')
	}
    
    consultarIncidenteForm2.btnGuardarValorClave.on("click",function(){
    	console.log("click");
		/*if(!consultarIncidenteForm2.validacion()){
			return false;
		}
		
		if(!consultarIncidenteForm2.validacionRegistro()){
			return false;
		}
		
		if( !confirm("Esta seguro de guardar solicitud?") ){

			return false;
		}*/
		
		//var	descripcionSol = $("#descripcion").val();
		
		//$("#descripcionSol").text(descripcionSol);
		//$("#descripcionSol").val(descripcionSol);

		var data = consultarIncidenteForm2.getParamsValorClave();
		console.log(data);
		consultarIncidenteForm2.processFormValorClave(data);

	});
    

    /*------------------------------------------------------------------*/
    
    $("#nrocti").on('blur',function(){
    	
    	var reg = /^CTI[0-9]{7}$/;
    	var v = $.trim($(this).val());
    	
    	if( v.length > 0 ){
    		
    		if( !reg.test(v) ){
    			alert("Debe ingresar el Nro. de CTI con formato correcto: CTINNNNNNN.");
    			$(this).val("");
    			$(this).focus();
    		}
    	}
    })
    
    $("#nroSS").on('blur',function(){
    	
    	var reg = /^SS[0-9]{5}$/;
    	var v = $.trim($(this).val());
    	
    	if( v.length > 0 ){
    		
    		if( !reg.test(v) ){
    			alert("Debe ingresar el Nro. SS con formato correcto: SSNNNNN.");
    			$(this).val("");
    			$(this).focus();
    		}
    	}
    })
    
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
		
		$.get("/atencion-incidente/incidente/getListSubProcesos",data,function(rsp){
			
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
    
    
    $("#btn_buscarIncidente").on("click",consultarIncidenteForm2.btn_buscarIncidente);
    $("#btn_limpiarConsulta").on("click",consultarIncidenteForm2.limpiar);
    
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