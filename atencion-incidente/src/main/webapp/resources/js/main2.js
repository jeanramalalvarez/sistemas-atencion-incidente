var consultarIncidenteForm2;

$(document).ready(function() {
   
    consultarIncidenteForm2 = {
    		ini : true,
    		nrocti : function(){  
    			/*if( consultarIncidenteForm2.ini && $("#nrocti").val()=="") 
    			{
    				return '';
    			}*/
    			
    			return $("#nrocti").val();  
    		},
    		fecha_ini:function(){  return $("#fecha_ini").val(); },
    		fecha_fin:function(){  return $("#fecha_fin").val(); },
    		tipoSolicitud:function(){  return  $("#tipoSolicitud").val(); },
    		estado:function(){  return  $("#estado").val(); },
    		nroSS:function(){  return  $("#nroSS").val(); } ,
    		sistema:function(){  return  $("#sistema").val(); } ,
    		proceso:function(){  return  $("#proceso").val(); } 
    }
    
    consultarIncidenteForm2.tbSolicitudes = $('#solicitudes');
    
    consultarIncidenteForm2.getParams = function() {
    	
    	return {
    			nroCti:consultarIncidenteForm2.nrocti(),
    			estado:consultarIncidenteForm2.estado(),
    			tipoSol:consultarIncidenteForm2.tipoSolicitud(),
    			fechaIni:consultarIncidenteForm2.fecha_ini(),
    			fechaFin:consultarIncidenteForm2.fecha_fin(),
    			nroSS:consultarIncidenteForm2.nroSS(),
    			sistema:consultarIncidenteForm2.sistema(),
    			proceso:consultarIncidenteForm2.proceso(),
    	};
    };
    
    consultarIncidenteForm2.limpiar = function(){
    	
    	$("#nrocti").val("");  
    	$("#fecha_ini").val("");
    	$("#fecha_fin").val(""); 
    	$("#tipoSolicitud").val(""); 
    	$("#estado").val(""); 
    	$("#nroSS").val(""); 
    	$("#sistema").val(""); 
    	$("#proceso").val("");
    	
    	$("#nrocti").focus();
    	
    }
    
    consultarIncidenteForm2.validacion = function(){
    	
    	if ( consultarIncidenteForm2.sistema() != "" )
    	{
    		return true
    	}
    	
    	if ( consultarIncidenteForm2.proceso() != "" )
    	{
    		return true
    	}
    	
    	if ( consultarIncidenteForm2.estado() != "" )
    	{
    		return true
    	}
    	
    	if ( consultarIncidenteForm2.tipoSolicitud() != "" )
    	{
    		return true
    	}
    	
    	if ( consultarIncidenteForm2.fecha_ini() != "" && consultarIncidenteForm2.fecha_fin() != "")
    	{
    		return true;
    	}
    	
    	if ( consultarIncidenteForm2.nroSS() != "" )
    	{
    		return true;
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
    	
    	$("#nrocti").focus();
    }

    consultarIncidenteForm2.tbSolicitudes.DataTable(
    	{
    		searching: false,
    		lengthChange: false,
    		pageLength: 10,
    		//data: data,
            ajax: {
                    url: '/atencion-incidente/solicitud/consulta2',
                    "type": "POST",
                    data:consultarIncidenteForm2.getParams,
                    dataSrc: 'data'
            },
    		columns: [
        		{ data: 'nroCti',"orderable": false },
        		{ data: 'estado' },
        		{ data: 'tipoSol',"orderable": false },
        		{ data: 'sistema',"orderable": false },
        		{ data: 'proceso' ,"orderable": false},
        		{ data: 'fechaReg',"orderable": false },
        		{ data: 'prioridad',"orderable": false },
        		{ data: 'usAfec' ,"orderable": false},
        		{ data: 'nroSS',"orderable": false },
        		{ data: 'solicitante' ,"orderable": false},
        		{ data: 'analistaF' ,"orderable": false},
        		{ data: 'accion',"orderable": false ,
                  render:function(data,type,row){
                	  console.log(data);
                	  console.log(row);
                	  var  btnEvaluar='<button class="optBtn"><a href="/atencion-incidente/solicitud/evaluar/'+row.nroCti +'">Evaluar</a></button>';
                  	   
                	 if(row.estado=="Atendido" || row.estado=="Rechazado"){btnEvaluar="";}
                	  
                	  return btnEvaluar;
                  }
        		}
    		]
    	}

    );

    /*------------------------------------------------------------------*/
    
	$('.datepicker1').datepicker({
		format: 'yyyy-mm-dd',
    	startDate:"2005-01-01",
    	endDate: '0d'
	});   

    $('#fecha_ini').datepicker()
    .on('changeDate', function(e) {
        var ini = $('#fecha_ini').datepicker('getDate');
        $('#fecha_fin').datepicker('setStartDate',ini);
        
        if( moment($(this).val()).isBefore('2005-01-01') ){
        	alert("La fecha de busqueda debe ser mayor o igual al 2005-01-01");
        	$(this).val("");
        	$(this).focus();
        }
        
    }); 

    $('#fecha_fin').datepicker()
    .on('changeDate', function(e) {
        var fin = $('#fecha_fin').datepicker('getDate');
        $('#fecha_ini').datepicker('setEndDate',fin);
        
        if( moment($(this).val()).isBefore('2005-01-01') ){
        	alert("La fecha de busqueda debe ser mayor o igual al 2005-01-01");
        	$(this).val("");
        	$(this).focus();
        }
    });
    
    $('#fecha_ini').datepicker()
    .on('clearDate', function(e) {
        $('#fecha_fin').datepicker('setStartDate',"");
    }); 
    
    $('#fecha_fin').datepicker()
    .on('clearDate', function(e) {
        $('#fecha_ini').datepicker('setEndDate',"");
    });
    
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
		
		$.get("/atencion-incidente/solicitud/getListProcesos",data,function(rsp){
			
			console.log("procesos")
			console.log(rsp)
			
			var html = "<option value=''></option>";
			
			for(var i in rsp){
				
				html += "<option value='"+ i +"'>"+ rsp[i] +"</option>";
			}
			
			$("#proceso").html("");
			$("#proceso").append(html);
			
		},'json')
		
	});
    
    
    
    $("#btn_buscarIncidente").on("click",consultarIncidenteForm2.btn_buscarIncidente);
    $("#btn_limpiarConsulta").on("click",consultarIncidenteForm2.limpiar);
    
    /*------------------------------------------------------*/

} );