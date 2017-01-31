var nuevaSolicitud;

$(document).ready(function() {

	nuevaSolicitud = {
		
		url:{
			updateSolicitud:"/atencion-incidente/solicitud/updateSolicitud",
			updateIncidente:"/atencion-incidente/solicitud/updateIncidente"
		},
		form:{
			tipoSolicitud:$("#tipoSolicitud"),
			form_datos:$("#form-datos"),
			form_incidente:$("#form-incidente")
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
		var nroCti = $("#nroCti").val();
		$("#mNroCti").text(nroCti);
		$("#show-nroCti").modal("show");
		
	}

	
	
	nuevaSolicitud.sendFormIncidente = function(){

		var options = { 
	        	//target:        '#output1',   // target element(s) to be updated with server response 
	        	beforeSubmit:  function(){ console.log("subiendo imagen") },  // pre-submit callback 
	        	success:   function(rs){ 
	        		console.log(rs) 
	        		nuevaSolicitud.setNroCti(rs.nroCti);
	        	},  // post-submit callback 
	        	error:function(){ console.log("error") },
	        	// other available options: 
	        	url: nuevaSolicitud.url.updateIncidente,
	        	type: "POST",
	        	dataType:  'json',        // 'xml', 'script', or 'json' (expected server response type) 
	        	clearForm: true        // clear all form fields after successful submit 
	        
	    }; 
		
		var form = nuevaSolicitud.form.form_incidente;
	 
	    // bind form using 'ajaxForm' 
		form.ajaxForm(options); 
	    
	    nuevaSolicitud.submit(form);
	}
	
	nuevaSolicitud.submit = function(form){

		form.submit();
	
	}


	nuevaSolicitud.form.tipoSolicitud.on("change",function(){
		
		nuevaSolicitud.resetFiles();
		nuevaSolicitud.showDetailxTipoSolicitud( $(this).val() )
	});

	nuevaSolicitud.btnGuardar.on("click",function(){

		if(!nuevaSolicitud.validar()){
			return false;
		}
		
		if( !confirm("Estas seguro de guardar solicitud.....67777777?") ){

			return false;
		}

		var data = nuevaSolicitud.getParamsFormData();

		nuevaSolicitud.processForm(data);

	})
	
	nuevaSolicitud.validar = function(){
		
		var sistema = $("#sistema").val();
	    var proceso =  $("#proceso").val();
	    var subProceso = $("#subProceso").val();
	    var tipo =  nuevaSolicitud.form.tipoSolicitud.val();
	    var	descripcion = $("#descripcion").val();
	    
	    if(tipo==3){
	      
	       descripcion = $("#descripcion2").val()
	    	
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
	    
	    
	    if(descripcion==""){
	    	alert("Ingresar descripcion");
	    	$("#descripcion").focus();
	    	return false;
	    }
	    
	    return true;
	}

	nuevaSolicitud.processForm = function(data){
	
		var tipo = nuevaSolicitud.form.tipoSolicitud.val();
		nuevaSolicitud.btnGuardar.attr("disabled",true);

		$.post(nuevaSolicitud.url.updateSolicitud,data,function(rsp){
			
				nuevaSolicitud.setIdSolicitud(rsp.idSolicitud,tipo);

				nuevaSolicitud.sendFormIncidente();

		},'json')
	}
	
	nuevaSolicitud.setIdSolicitud = function(idSolicitud,tipo){
		
		var usAfectados = $("#nroUsAfec").val();
		
		$(".usAfectados").val(usAfectados);
		
	}
	
	/*----------------------------------------------*/
	
	$("#sistema").change(function(){
		
		var data = {
				idSistema:$(this).val()
		}
		
		$.get("/atencion-incidente/solicitud/getListProcesos",data,function(rsp){
			
			console.log("procesos")
			console.log(rsp)
			
			var html = "<option value=''>Select</option>";
			
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
		var html = "<tr><td>"+nuevaSolicitud.nroFile +" </td><td>"+nomFile  +"</td><td>"+fechaCarga +" </td><td><a class='pull-right deleteFile' >X</a></td> </tr>";
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

	$(".btnAdjuntar").on("click",nuevaSolicitud.adjuntar);
	
	$("tbody").on("click",'a.deleteFile',function(){
		$(this).parent('td').parent('tr').remove();
	})
	
});

