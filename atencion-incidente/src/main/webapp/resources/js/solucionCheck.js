var solucionCheckForm;

$(document).ready(function() {
		
	    solucionCheckForm = {
	    		ini : 					true,
	    		idSolucionCh:			function(){  return  $("#idSolucionCh").val(); },
	    		txtGlosa:				function(){  return  $("#txtGlosa").val(); } ,
	    		txtPautas:				function(){  return  $("#txtPautas").val(); },
	    		txtDescripcion:			function(){  return  $("#txtDescripcion").val(); },
	     		txtAnexo:				function(){  return  $("#txtAnexo").val(); },
	    		txtRuta:    			function(){  return  $("#txtRuta").val(); },
	    	    		
	    		url:{
	    			form:"/atencion-incidente/solucionCheck/registrar"
	    		},
	    		urlCargar:{
	    			form:"/atencion-incidente/solucionCheck/cargar"
	    		},
	    		urlEliminar:{
	    			form:"/atencion-incidente/solucionCheck/eliminar"
	    		},
	    		btnGuardar:$("#btn_guardar"),
	    		btnLimpiar:$("#btn_limpiar"),
	    		
	    	
	    }

	    
	    solucionCheckForm.tbsolucionCheck = $('#solucionCheckList');
	    

	    solucionCheckForm.getParams = function() {
	    	return {
	    		    idSolucionCh:	solucionCheckForm.idSolucionCh(),
	    		    txtGlosa:			solucionCheckForm.txtGlosa(),
	    		    txtPautas:			solucionCheckForm.txtPautas(),
	    		    txtDescripcion:		solucionCheckForm.txtDescripcion(),
	    		    txtAnexo:		solucionCheckForm.txtAnexo(),
	    		    txtRuta:  			solucionCheckForm.txtRuta()
	    			
	    	};
	    };
	
	     solucionCheckForm.tbsolucionCheck.DataTable(
	     	{
	     		searching: false,
	     		lengthChange: false,
	     		pageLength: 10,
	     		//data: data,
	             ajax: {
	                     url: '/atencion-incidente/solucionCheck/buscar',
	                     "type": "POST",
	                     data: solucionCheckForm.getParams,
	                     //dataSrc: 'data'
	                     dataSrc: function(json){
	                    	 console.log(json);
	                    	 
	                     	if(json.idSolucionCh != undefined && json.idSolucionCh != null && json.idSolucionCh != ""){
	                     		console.log(json.idSolucionCh);
	                     		$("#idSolucionCh").val(json.idSolucionCh)
	                     	}
	                     	return json.data
	                     }
	             },
	     		columns: [
	         		{ data: 'idSolucionCh',"orderable": true },
	         		{ data: 'txtGlosa' },
	         		{ data: 'txtPautas' },
	         		{ data: 'txtDescripcion' },
	         		{ data: 'txtAnexo' },
	         		{ data: 'opcion',"orderable": false ,
	                   render:function(data, type, row){
	                 	 var  btnEvaluar =  '<button class="optBtn btn_cargar" style="width: 70px; float: left; margin-right: 8px;" data-id="' + row.idSolucionCh + '" >Modificar</button>';
	                 	 btnEvaluar += '<button class="optBtn btn_eliminar" style="width: 70px; float: left;" data-id="' + row.idSolucionCh + '" >Eliminar</button>';
	                 	 return btnEvaluar;
	                   }
	         		}
	     		]
	     	}

	     );
	     
	     $(document).on('click', '.btn_cargar', function(){
	     	var data = {idSolucionCh: $(this).attr("data-id") };
	     	console.log(data);
	     	solucionCheckForm.cargarsolucionCheck(data);
	     });
	     
	     solucionCheckForm.cargarsolucionCheck = function(data){
	 		$.post(solucionCheckForm.urlCargar.form, data, function(rsp){
	 			console.log(rsp);
	 			$("#idSolucionCh").val(rsp.solucionCheck.idSolucionCh);
	     		$("#txtGlosa").val(rsp.solucionCheck.txtGlosa);
	     		$("#txtPautas").val(rsp.solucionCheck.txtPautas);
	     		$("#txtDescripcion").val(rsp.solucionCheck.txtDescripcion);
	     		$("#txtAnexo").val(rsp.solucionCheck.txtAnexo);
	 				
	 		},'json')
	 	}
	     
	     
	     solucionCheckForm.limpiar = function(){

		      	$("#idSolucionCh").val(""); 
		     	$("#txtGlosa").val("");
		     	$("#txtPautas").val("");
		     	$("#txtDescripcion").val(""); 
		     	$("#txtAnexo").val(""); 

		     }
		     
	     solucionCheckForm.btnLimpiar.on("click", solucionCheckForm.limpiar);
	     
	     
	
	     $(document).on('click', '.btn_eliminarDet', function(){
	     	
	     	if( !confirm("Esta seguro de eliminar el detalle de Valor Clave") ){
	 			return false;
	 		}
	     	
	     	var data = {
	     			idSolucionCh: $(this).attr("data-id")		
	     	};
	     	solucionCheckForm.eliminar(data);
	     });
	     
	     solucionCheckForm.eliminar = function(data){
	 		$.post(solucionCheckForm.urlEliminar.form, data, function(rsp){
	 			solucionCheckForm.btn_buscarsolucionCheck();
	 			solucionCheckForm.limpiar();
	 		},'json')
	 	}

	     
	     
	     solucionCheckForm.btnGuardar.on("click",function(){

	 		if(!solucionCheckForm.validacion()){
	 			alert("Debe seleccionar los campos.");
	 			return false;
	 		}
	 		
	 		if(!solucionCheckForm.validacionRegistro()){
	 			alert("Debe ingresar por lo menos una descripcion.");
	 			return false;
	 		}
	 		
	 		if( !confirm("Esta seguro de guardar el Valor Clave") ){
	 			return false;
	 		}

	 		var data = solucionCheckForm.getParams();
	 		//console.log(data);
	 		solucionCheckForm.registrarsolucionCheck(data);

	 	});
	     
	     solucionCheckForm.validacion = function(){
	     	
	     	if ( solucionCheckForm.txtGlosa() == "" ){
	     		return false;
	     	}
	     	
	     	if ( solucionCheckForm.txtPautas() == "" ){
	     		return false
	     	}
	     	
	     	if ( solucionCheckForm.txtRuta()== "" ){
	     		return false
	     	}
	     	
	     	if ( solucionCheckForm.txtDescripcion()== "" ){
	     		return false
	     	}
	     	
	     	return true;
	     }
	     
	     solucionCheckForm.validacionRegistro = function(){
	     	
	     	if (solucionCheckForm.valorClave() == ""){
	     		return false;
	     	}
	     	return true;
	     }
	     
	     
	     solucionCheckForm.registrarsolucionCheck = function(data){
	 		
	    	 solucionCheckForm.btnGuardar.attr("disabled", true);

	 		$.post(solucionCheckForm.url.form, data, function(rsp){
	 			solucionCheckForm.btnGuardar.attr("disabled", false);
	 			solucionCheckForm.btn_buscarsolucionCheck();
	 			solucionCheckForm.limpiar();
	 			//alert(rsp.mensaje);
	 		},'json')
	 	}
	     
	     solucionCheckForm.btn_buscarsolucionCheck = function() {
	     	
	     	if (!solucionCheckForm.validacion()){
	     		alert("Debe seleccionar los filtro.");
	     		return false;
	     	}
	     	
	     	solucionCheckForm.ini = false;
	     	
	     	var table = solucionCheckForm.tbsolucionCheck.DataTable();
	     	table.ajax.reload();
	     	
	     	$("#tipoSolicitud").focus();
	     }


} ); 
	    	     