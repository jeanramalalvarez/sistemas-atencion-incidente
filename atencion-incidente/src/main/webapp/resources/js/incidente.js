var incidenteForm;

$(document).ready(function() {
   
    incidenteForm = {
    		ini : true,
    		tipoSolicitud:	function(){  return  $("#tipoSolicitud").val(); },
    		sistema:	function(){  return  $("#sistema").val(); } ,
    		proceso:	function(){  return  $("#proceso").val(); },
    		subProceso:	function(){  return  $("#subProceso").val(); },
    		
    		secuencia:	function(){  return  $("#txtSecuencia").val(); },
    		descripcion:	function(){  return  $("#txtDescripcion").val(); },
    		flagResolucion:	function(){  return  $("#flgResolucion").prop("checked")==true?"S":"N"; },
    		
    		url:{
    			form:"/atencion-incidente/incidente"
    		},
    		btnGuardar:$("#btn_guardar"),
    }
    
    incidenteForm.tbSolicitudes = $('#solicitudes');
    
    incidenteForm.getParams = function() {
    	return {
    			idTipoSolicitud:	incidenteForm.tipoSolicitud(),
    			idSistema:	incidenteForm.sistema(),
    			idProceso:	incidenteForm.proceso(),
    			idSubproceso:	incidenteForm.subProceso(),
    			
    			nuSecuencia:	incidenteForm.secuencia(),
    			txtDescripcion:	incidenteForm.descripcion(),
    			flgResolucion:	incidenteForm.flagResolucion(),
    	};
    };
    
    incidenteForm.limpiar = function(){
    	$("#tipoSolicitud").val("");
    	$("#sistema").val("");
    	$("#proceso").val("");
    	$("#subProceso").val("");
    	$("#tipoSolicitud").focus();
    }
    
    incidenteForm.validacion = function(){
    	
    	if ( incidenteForm.tipoSolicitud() != "" )
    	{
    		return true
    	}
    	
    	if ( incidenteForm.sistema() != "" )
    	{
    		return true
    	}
    	
    	if ( incidenteForm.proceso() != "" )
    	{
    		return true
    	}
    	
    	if ( incidenteForm.subProceso() != "" )
    	{
    		return true
    	}
    	
    	return false;
    }
    
    incidenteForm.validacionRegistro = function(){
    	
    	if ( incidenteForm.descripcion() != "" )
    	{
    		return true
    	}
    	
    	/*if ( incidenteForm.flagResolucion() != "" )
    	{
    		return true
    	}*/
    	
    	return false;
    }
    
    incidenteForm.btn_buscarIncidente = function() {
    	
    	if (!incidenteForm.validacion()){
    		
    		alert("Debe seleccionar por lo menos un filtro");
    		return false;
    	}
    	
    	incidenteForm.ini = false;
    	
    	var table = incidenteForm.tbSolicitudes.DataTable();
    	table.ajax.reload();
    	
    	$("#tipoSolicitud").focus();
    }

    incidenteForm.tbSolicitudes.DataTable(
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
    
    
    incidenteForm.processForm = function(data){
		
		//var tipo = nuevaSolicitud.form.tipoSolicitud.val();
		//var fn = "sendFormTipo" + tipo;
		
    	incidenteForm.btnGuardar.attr("disabled", true);

		$.post(incidenteForm.url.form,data,function(rsp){
			
				//nuevaSolicitud.setIdSolicitud(rsp.idSolicitud,tipo);
				
				//if(rsp.estado == 0){
					incidenteForm.btnGuardar.attr("disabled", false);
					incidenteForm.btn_buscarIncidente();
					$("#txtDescripcion").val("");
					$("#flgResolucion").prop("checked", false); 
					//alert(rsp.mensaje);
					//$("#descripcion").focus();
				//}
		},'json')
	}
    
    incidenteForm.btnGuardar.on("click",function(){

		if(!incidenteForm.validacion()){
			return false;
		}
		
		if(!incidenteForm.validacionRegistro()){
			return false;
		}
		
		if( !confirm("Esta seguro de guardar el incidente?") ){

			return false;
		}
		
		//var	descripcionSol = $("#descripcion").val();
		
		//$("#descripcionSol").text(descripcionSol);
		//$("#descripcionSol").val(descripcionSol);

		var data = incidenteForm.getParams();
		console.log(data);
		incidenteForm.processForm(data);

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
    
    
    $("#btn_buscarIncidente").on("click",incidenteForm.btn_buscarIncidente);
    $("#btn_limpiarConsulta").on("click",incidenteForm.limpiar);
    /*------------------------------------------------------*/

} );