var consultarIncidenteForm2;

$(document).ready(function() {
   
    consultarIncidenteForm2 = {
    		ini : true,
    		tipoSolicitud:	function(){  return  $("#tipoSolicitud").val(); },
    		sistema:	function(){  return  $("#sistema").val(); } ,
    		proceso:	function(){  return  $("#proceso").val(); },
    		subProceso:	function(){  return  $("#subProceso").val(); }
    }
    
    consultarIncidenteForm2.tbSolicitudes = $('#solicitudes');
    
    consultarIncidenteForm2.getParams = function() {
    	return {
    			idTipoSolicitud:	consultarIncidenteForm2.tipoSolicitud(),
    			idSistema:	consultarIncidenteForm2.sistema(),
    			idProceso:	consultarIncidenteForm2.proceso(),
    			idSubproceso:	consultarIncidenteForm2.subProceso(),
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
                    url: '/atencion-incidente/incidente',
                    "type": "POST",
                    data:consultarIncidenteForm2.getParams,
                    dataSrc: 'data'
            },
    		columns: [
        		{ data: 'nuSecuencia',"orderable": false },
        		{ data: 'txtDescripcion' },
        		{ data: 'flgResolucion',"orderable": false },
        		{ data: 'accion',"orderable": false ,
                  render:function(data,type,row){
                	 var  btnEvaluar = '<button class="optBtn" style="width: 70px; float: left; margin-right: 8px;" ><a href="/atencion-incidente/solicitud/evaluar/'+row.nroCti +'">Agregar</a></button>';
                	 btnEvaluar += '<button class="optBtn" style="width: 70px; float: left; margin-right: 8px;" ><a href="/atencion-incidente/solicitud/evaluar/'+row.nroCti +'">Modificar</a></button>';
                	 btnEvaluar += '<button class="optBtn" style="width: 70px; float: left;" ><a href="/atencion-incidente/solicitud/evaluar/'+row.nroCti +'">Soluciones</a></button>';
                	 //if(row.estado=="Atendido" || row.estado=="Rechazado"){btnEvaluar="";}
                	 return btnEvaluar;
                  }
        		}
    		]
    	}

    );

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
    
    /*------------------------------------------------------*/

} );