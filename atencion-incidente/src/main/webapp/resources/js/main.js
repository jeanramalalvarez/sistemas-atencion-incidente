var consultarIncidenteForm;

$(document).ready(function() {
   
    consultarIncidenteForm = {
    		ini : true,
    		nrocti : function(){  
    			/*if( consultarIncidenteForm.ini && $("#nrocti").val()=="") 
    			{
    				return '';
    			}*/
    			
    			return $("#nrocti").val();  
    		},
    		fecha_ini:function(){  return $("#fecha_ini").val(); },
    		fecha_fin:function(){  return $("#fecha_fin").val(); },
    		tipoSolicitud:function(){  return  $("#tipoSolicitud").val(); },
    		estado:function(){  return  $("#estado").val(); },
    		nroSS:function(){  return  $("#nroSS").val(); } 
    }
    
    consultarIncidenteForm.tbSolicitudes = $('#solicitudes');
    
    consultarIncidenteForm.getParams = function() {
    	
    	return {
    			nroCti:consultarIncidenteForm.nrocti(),
    			estado:consultarIncidenteForm.estado(),
    			tipoSol:consultarIncidenteForm.tipoSolicitud(),
    			fechaIni:consultarIncidenteForm.fecha_ini(),
    			fechaFin:consultarIncidenteForm.fecha_fin(),
    			nroSS:consultarIncidenteForm.nroSS()
    	};
    };
    
    consultarIncidenteForm.limpiar = function(){
    	
    	$("#nrocti").val("");  
    	$("#fecha_ini").val("");
    	$("#fecha_fin").val(""); 
    	$("#tipoSolicitud").val(""); 
    	$("#estado").val(""); 
    	$("#nroSS").val(""); 
    	
    	$("#nrocti").focus();
    	
    }
    
    consultarIncidenteForm.validacion = function(){
    	
    	if ( consultarIncidenteForm.nrocti() != "" )
    	{
    		return true
    	}
    	
    	if ( consultarIncidenteForm.estado() != "" )
    	{
    		return true
    	}
    	
    	if ( consultarIncidenteForm.tipoSolicitud() != "" )
    	{
    		return true
    	}
    	
    	if ( consultarIncidenteForm.fecha_ini() != "" && consultarIncidenteForm.fecha_fin() != "")
    	{
    		return true;
    	}
    	
    	if ( consultarIncidenteForm.nroSS() != "" )
    	{
    		return true;
    	}
    	
    	return false;
    }
    
    consultarIncidenteForm.btn_buscarIncidente = function() {
    	
    	if (!consultarIncidenteForm.validacion()){
    		
    		alert("Debe seleccionar por lo menos un filtro");
    		return false;
    	}
    	
    	consultarIncidenteForm.ini = false;
    	
    	var table = consultarIncidenteForm.tbSolicitudes.DataTable();
    	table.ajax.reload();
    	
    	$("#nrocti").focus();
    }

    consultarIncidenteForm.tbSolicitudes.DataTable(
    	{
    		searching: false,
    		lengthChange: false,
    		pageLength: 10,
    		//data: data,
            ajax: {
                    url: '/atencion-incidente/solicitud/consulta',
                    "type": "POST",
                    data:consultarIncidenteForm.getParams,
                    dataSrc: 'data'
            },
    		columns: [
        		{ data: 'nroCti',"orderable": false },
        		{ data: 'estado' },
        		{ data: 'tipoSol' },
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
                	  var  btnDetalle='<button class="optBtn"><a href="/atencion-incidente/solicitud/detalle/'+row.nroCti +'">Detalle</a></button>';
                	  var  btnModificar = '';
                  	
                	  if(row.estado=='Registrado' || row.estado == 'Pendiente de Aprobar' || row.estado == 'Modificado'){
                		  btnModificar = '<button class="optBtn"><a href="/atencion-incidente/solicitud/editar/'+row.nroCti +'">Editar</a></button>';
                	  }
                	  
                	  return btnModificar + btnDetalle;
                  }
        		}
    		]
    	}

    );

    /*------------------------------------------------------------------*/
    
	$('.datepicker1').datepicker({
    	format: 'yyyy-mm-dd',
    	//startDate:"2005-01-01",
    	endDate: '0d'
	});   

    $('#fecha_ini').datepicker()
    .on('changeDate', function(e) {
        var ini = $('#fecha_ini').datepicker('getDate');
        $('#fecha_fin').datepicker('setStartDate',ini);     
    }).on('blur',function(e){
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
    })
    .on('blur',function(e){
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
    
  consultarIncidenteForm.loadEstados = function() {
    	
    	$.get("/atencion-incidente/solicitud/listEstados",{},function(rsp){
    		
    		var sestado = $("#estado");
    		var html = "";
    		var k = null;
    		var v = null;
    		
    		$.each(rsp,function(estado){
    			k = estado;
    			v = rsp[estado];
    			html = "<option value='"+k+"' >"+v+"</option>";
    			sestado.append(html);
    		})
    		
    	},'json');
    
    }
    
    
    $("#btn_buscarIncidente").on("click",consultarIncidenteForm.btn_buscarIncidente);
    $("#btn_limpiarConsulta").on("click",consultarIncidenteForm.limpiar);
    
    consultarIncidenteForm.loadEstados();
    
    /*------------------------------------------------------*/

} );