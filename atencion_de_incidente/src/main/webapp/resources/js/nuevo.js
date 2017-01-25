var nuevaSolicitud;

$(document).ready(function() {

	nuevaSolicitud = {
		
		url:{
			form:"/atencion-incidente/solicitud/save",
			tipo_1_2:"/atencion-incidente/solicitud/saveTipo_1_2",
			tipo3:"/atencion-incidente/solicitud/saveTipo3",
		},
		form:{
			tipoSolicitud:$("#tipoSolicitud"),
			form_datos:$("#form-datos"),
			form_tipo_1_2:$("#form-tipo-1-2"),
			form_tipo_3:$("#form-tipo-3")
		},
		views : {
			dom:$("#view_tipo_solicitud"),
			"tipo_sol_1":$(".tipo1"),
			"tipo_sol_2":$(".tipo2"),
			"tipo_sol_3":$(".tipo3")
		},
		buttons:$("#buttons"),
		btnGuardar:$("#btn_guardar")
	}

	nuevaSolicitud.showDetailxTipoSolicitud = function(tipoSolicitud){

		var name = "tipo_sol_"+tipoSolicitud;
		var cssClass = "active-ts"+tipoSolicitud;
		var obj = nuevaSolicitud.views[name];
		obj.removeClass("active-ts1");
		obj.removeClass("active-ts2");
		obj.removeClass("active-ts3");

		obj.addClass(cssClass);
		nuevaSolicitud.views['tipo_sol_1'].hide();
		nuevaSolicitud.views['tipo_sol_3'].hide();
		obj.show();
		nuevaSolicitud.buttons.show();
	}
	
	nuevaSolicitud.getParamsFormData = function(){

		var params = nuevaSolicitud.form.form_datos.serialize(true);

		return params;
	}
	
	nuevaSolicitud.setNroCti = function(nroCti){
		
		$("#nrocti").val(nroCti);
		$("#mNroCti").text(nroCti);
		$("#show-nroCti").modal("show");
		
	}

	nuevaSolicitud.sendFormTipo_1_2 = function(){
		
		var options = { 
	        	//target:        '#output1',   // target element(s) to be updated with server response 
	        	beforeSubmit:  function(){ console.log("subiendo imagen") },  // pre-submit callback 
	        	success:   function(rs){ 
	        		console.log(rs);
	        		nuevaSolicitud.setNroCti(rs.nroCti);
	        	},  // post-submit callback 

	        	error:function(){ console.log("error") },
	        	// other available options: 
	        	url: nuevaSolicitud.url.tipo_1_2,
	        	type: "POST",
	        	dataType:  'json',        // 'xml', 'script', or 'json' (expected server response type) 
	        	clearForm: true        // clear all form fields after successful submit 
	        
	    }; 
		
		var form = nuevaSolicitud.form.form_tipo_1_2;
	 
	    // bind form using 'ajaxForm' 
		form.ajaxForm(options); 
	    
	    nuevaSolicitud.submit(form);
		
	}
	
	nuevaSolicitud.sendFormTipo3 = function(){

		var options = { 
	        	//target:        '#output1',   // target element(s) to be updated with server response 
	        	beforeSubmit:  function(){ console.log("subiendo imagen") },  // pre-submit callback 
	        	success:   function(rs){ 
	        		console.log(rs) 
	        		nuevaSolicitud.setNroCti(rs.nroCti);
	        	},  // post-submit callback 
	        	error:function(){ console.log("error") },
	        	// other available options: 
	        	url: nuevaSolicitud.url.tipo3,
	        	type: "POST",
	        	dataType:  'json',        // 'xml', 'script', or 'json' (expected server response type) 
	        	clearForm: true        // clear all form fields after successful submit 
	        
	    }; 
		
		var form = nuevaSolicitud.form.form_tipo_3;
	 
	    // bind form using 'ajaxForm' 
		form.ajaxForm(options); 
	    
	    nuevaSolicitud.submit(form);
	}
	
	nuevaSolicitud.submit = function(form){

		form.submit();
	
	}

	nuevaSolicitud.sendFormTipo1=function(){

		nuevaSolicitud.sendFormTipo_1_2();
	}

	nuevaSolicitud.sendFormTipo2=function(){

	   nuevaSolicitud.sendFormTipo_1_2();
	}

	


	nuevaSolicitud.form.tipoSolicitud.on("change",function(){
		
		nuevaSolicitud.resetFiles();
		nuevaSolicitud.showDetailxTipoSolicitud( $(this).val() )
	});

	nuevaSolicitud.btnGuardar.on("click",function(){

		if(!nuevaSolicitud.validar()){
			return false;
		}
		
		if( !confirm("Esta seguro de guardar solicitud?") ){

			return false;
		}
		
		var	descripcionSol = $("#descripcion").val();
		
		$("#descripcionSol").text(descripcionSol);
		$("#descripcionSol").val(descripcionSol);

		var data = nuevaSolicitud.getParamsFormData();
		
		nuevaSolicitud.processForm(data);

	})
	
	nuevaSolicitud.validar = function(){
		
		var sistema = $("#sistema").val();
	    var proceso =  $("#proceso").val();
	    var subProceso = $("#subProceso").val();
	    var tipo =  nuevaSolicitud.form.tipoSolicitud.val();
	    var usAfec =  $("#nroUsAfec").val();
	    var	descripcion = $("#descripcion").val();
	    
	    if(tipo==3){
	      
	       descripcion = $("#descripcion2").val();
	    	
	    }
	   
	    if(sistema==""){
	    	alert("Seleccionar sistema");
	    	$("#sistema").focus();
	    	return false;
	    }
	    
	    if(proceso==""){
	    	alert("Seleccionar proceso");
	    	$("#proceso").focus();
	    	return false;
	    }
	    
	    if(subProceso==""){
	    	alert("Seleccionar sub proceso");
	    	$("#subProceso").focus();
	    	return false;
	    }
	    
	    if(usAfec==""){
	    	alert("Seleccionar nro usuarios afectados");
	    	$("#nroUsAfec").focus();
	    	return false;
	    }
	    
	   
	    
	    if(tipo==2 || tipo==1){
		      
		  asunto = $("#asunto").val();
		 
		  files = $(".tb-files-tipo1").find("tr");  
		  
/*		  if(asunto==""){
		    	alert("Ingresar asunto");
		    	$("#asunto").focus();
		    	return false;
		  }*/
		  
		  
		  if(descripcion==""){
		    	alert("Ingresar descripcion");
		    	$("#descripcion").focus();
		    	return false;
		  }
		  
		  if(files.length==0){
		    	alert("Ingresar por lo menos 1 archivo");
		    	return false;
		  }
		  
	    }
	    
/*	    if(tipo==1){
	    	
	    	 error = $("#error").val();
	    	 
	    	 if(error==""){
			    	alert("Ingresar error");
			    	$("#error").focus();
			    	return false;
			  }
	    }*/
	    
	    if(tipo==2){
	    	
	    	 datosModif = $("#datosModif").val();
	    	 
	    	 if(datosModif==""){
			    	alert("Ingresar datos a modificar");
			    	$("#datosModif").focus();
			    	return false;
			  }
	    }
	    
	    
	    if(tipo==3){
	      
	    	 resumen = $("#resumen").val();
			 fechasCorte = $("#fechasCorte").val();
			 estructuraReporte =  $("#estructuraReporte").val();
			 volumenes =  $("#volumenes").val();
			 files2 = $(".tb-files-tipo3").find("tr");  
			 
			 
			if(descripcion==""){
			    	alert("Ingresar descripcion");
			    	$("#descripcion").focus();
			    	return false;
			 }
			 
			 if(resumen==""){
			    	alert("Ingresar resumen");
			    	$("#resumen").focus();
			    	return false;
			  }
			  
			  if(fechasCorte==""){
			    	alert("Ingresar fechas de corte");
			    	$("#fechasCorte").focus();
			    	return false;
			  }
			  
			  if(estructuraReporte==""){
			    	alert("Ingresar estructuras de reporte");
			    	$("#estructuraReporte").focus();
			    	return false;
			  }
			  
			  if(volumenes==""){
			    	alert("Ingresar volumenes");
			    	$("#volumenes").focus();
			    	return false;
			  }
			  
			  if(files2.length==0){
			    	alert("Ingresar por lo menos 1 archivo");
			    	return false;
			  }
	    	
	    }
	   
	    
	    return true;
	}

	nuevaSolicitud.processForm = function(data){
		
		var tipo = nuevaSolicitud.form.tipoSolicitud.val();
		var fn = "sendFormTipo" + tipo;
		
		nuevaSolicitud.btnGuardar.attr("disabled",true);

		$.post(nuevaSolicitud.url.form,data,function(rsp){
			
				nuevaSolicitud.setIdSolicitud(rsp.idSolicitud,tipo);
				
				if(rsp.estado == 0){
					nuevaSolicitud.btnGuardar.attr("disabled",false);
					alert(rsp.mensaje);
					$("#descripcion").focus();
				}else{
					
				if(tipo==1 || tipo==2){
					
					console.log("Enviado tipo1 o 2")
					nuevaSolicitud[fn]();
					
				}else if(tipo==3){
					
					console.log("Enviado tipo3")
					nuevaSolicitud[fn]();
					
				}
				}
		},'json')
	}
	
	nuevaSolicitud.setIdSolicitud = function(idSolicitud,tipo){
		
		var usAfectados = $("#nroUsAfec").val();
		
		$(".idSolicitud_tipo"+tipo).val(idSolicitud);
		$(".usAfectados_tipo"+tipo).val(usAfectados);
		
	}
	
	/*----------------------------------------------*/
	
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
	
	$("#proceso").change(function(){
		
		var data = {
				idSistema:$("#sistema").val(),
				idProceso:$(this).val()
		}
		
		$.get("/atencion-incidente/solicitud/getListSubProcesos",data,function(rsp){
			
			console.log("susbprocesos")
			console.log(rsp);
			
			
			
			var html = "<option value=''>Select</option>";
			
			for(var i in rsp){
				
				html += "<option value='"+ i +"'>"+ rsp[i] +"</option>";
			}
			
			$("#subProceso").html("");
			$("#subProceso").append(html);
			
		},'json')
		
	});
	
	/*----------------------------adjuntar files-----------------------------*/
	
	nuevaSolicitud.nroFile = 1;

	nuevaSolicitud.adjuntar = function(){

		var tipo = nuevaSolicitud.form.tipoSolicitud.val();
		
		var fileActive = $(".active-file-tipo"+tipo);
		var table = $(".tb-files-tipo"+tipo);
		var nomFile = fileActive.val();
		
		if(nomFile==""){
			alert("debe seleccionar un archivo");
			return false;
		}
		
		var fechaCarga = moment().format("DD/MM/YYYY");
		var html = "<tr><td>"+nuevaSolicitud.nroFile +" </td><td>"+nomFile  +"</td><td>"+fechaCarga +"</td><td><a class='pull-right deleteFile' >X</a></td> </tr>";
		nuevaSolicitud.nroFile++;
		table.append(html);
		var parent = fileActive.parent();
		var copy = fileActive.clone();
		fileActive.removeClass("active-file-tipo"+tipo).hide();
		parent.append(copy);

	}

	nuevaSolicitud.resetFiles = function(){
		
		nuevaSolicitud.nroFile = 1;
		var tipo = nuevaSolicitud.form.tipoSolicitud.val();
		var fileActive = $(".active-file-tipo"+tipo);
		var copy = fileActive.clone();
		var parent = fileActive.parent();
		parent.html("");
		parent.append(copy);
		var table = $(".tb-files-tipo"+tipo);
		table.html(""); 
	}
	
	$("tbody").on("click",'a.deleteFile',function(){
		$(this).parent('td').parent('tr').remove();
	})

	$(".btnAdjuntar").on("click",nuevaSolicitud.adjuntar);
	
	nuevaSolicitud.loadSistemas = function() {
    	
    	$.get("/atencion-incidente/solicitud/listSistemas",{},function(rsp){
    		
    		var sestado = $("#sistema");
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
	
	nuevaSolicitud.loadSistemas();
	
	nuevaSolicitud.loadNroUsAfec = function() {
    	
    	$.get("/atencion-incidente/solicitud/listNroUsAfec",{},function(rsp){
    		
    		var sestado = $("#nroUsAfec");
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
	
	nuevaSolicitud.loadNroUsAfec();
	
});

