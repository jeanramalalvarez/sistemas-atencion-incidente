var evaluarSolicitud;

$(document).ready(function() {

	evaluarSolicitud = {
		form:{
			evaluar:$("#formEvaluar")
		},
		url:{
			form:"/atencion-incidente/solicitud/evaluar",
			solucion:"/atencion-incidente/solicitud/getsol"
		},
		btn:{
			volver:$("#btnVolver"),
			rechazar:$("#btnRechazar"),
			observar:$("#btnProcesarSolucion"),
			procesarSolucion:$("#btnObservar"),
			solicitarApoyo:$("#btnSolicitarApoyo"), 
			plantearSolucion:$("#btnPlantearSolucion"), 
			adjuntarFileObs:$(".btnAdjuntarObservacion"), 
			observacionGuardar:$("#btnModalObservacionGuardar"),
			noSolucion:$("#btnModalNoSolucion"),
			rechazoGuardar:$("#btnModalRechazoGuardar"),
			adjuntarFile:$(".btnAdjuntarEval"), 
			paseAProd:$("#btnPaseAProd"),
			mensaje:$("#btnMensaje"),
			cerrarSolucion:$("#btnCerrarSolucion"),
			soporteGuardar:$("#btnModalSoporteGuardar"),
			cerrarAceptar:$("#btnModalErrorAceptar")
		},
		panel:{
			procesarSolucion:$("#wrap_procesar_solucion"),
			plantearSolucion:$("#wrap_plantear_solucion"),
			area_desarrollo:$("#area-desarrollo"),
			area_proveedor:$("#area-proveedor")
		},
		grid:{
			solucionPropuesta:$("#tbSolucionPropuesta"),
			fileEvaluacion:$("#tb_file_eval")
			
		},
		combo:{
			valor:$("#valor"),
			nivelSolucion:$("#nivelSolucion"),
			areaSoporte:$("#areaSoporte")
		},
		textarea:{
			analisisFuncional:$("#analisisFuncional"),
			analisisTenico:$("#analisisTenico")
			
		},
		input:{
			tProgramadoAtencion:$("#tProgramadoAtencion"),
			tipoSolucion:$("#tipoSolucion"),
			tCalAtencion:$("#tCalAtencion"),
			tRealAtencion:$("#tRealAtencion"),
		},
		modal:{
			cerrar:$("#modalCerrar"),
			observar:$("#modalObservar"),
			soporte:$("#modalSoporte"),
			rechazar:$("#modalRechazar")
		}
		
		
	}
	
	var ob = evaluarSolicitud;
	
	ob.ini = function(){
		
		$('button').on('click',function(e){ e.preventDefault() })
		
		ob.btn.procesarSolucion.on("click",ob.procesarSolucion);
		ob.btn.volver.on("click",ob.volver);
		ob.btn.rechazar.on("click",ob.rechazar);
		ob.btn.observar.on("click",ob.observar);
		ob.btn.noSolucion.on("click",ob.noSolucion);
		
		ob.btn.solicitarApoyo.on("click",ob.solicitarApoyo);
		ob.btn.plantearSolucion.on("click",ob.plantearSolucion);
		
		ob.btn.adjuntarFile.on("click",ob.adjuntarFileEval);
		
		ob.btn.observacionGuardar.on("click",ob.saveObservacion);
		ob.btn.rechazoGuardar.on("click",ob.saveRechazo);
		ob.btn.adjuntarFileObs.on("click",ob.adjuntarFileObs);
		ob.btn.paseAProd.on("click",ob.paseAProd);
		ob.btn.mensaje.on("click",ob.mensaje);
		ob.btn.cerrarSolucion.on("click",ob.cerrarSolucion);
		
		ob.combo.areaSoporte.on("change",ob.areaSoporte);
		ob.btn.soporteGuardar.on("click",ob.soporteGuardar);
		ob.btn.cerrarAceptar.on("click",ob.cerrarAceptar);
		
	}

	
	ob.procesarSolucion = function(){
		
		ob.panel.procesarSolucion.show();
		ob.disable(ob.btn.rechazar);
		ob.disable(ob.btn.observar);
		
	}
	
	ob.volver = function(){
		
		ob.backConsulta();
		
	}
	

	ob.solicitarApoyo = function(){

		ob.modal.soporte.modal("show");
	}
	

	ob.plantearSolucion = function(){

		ob.panel.plantearSolucion.show();
		ob.disable(ob.btn.solicitarApoyo);
	}
	
	ob.paseAProd = function(){

		
	}
	
	ob.mensaje = function(){

		
	}
	
	ob.cerrarSolucion = function(){

		ob.modal.cerrar.modal("show");
	}
	
	ob.disable = function(obj){
		obj.attr("disabled","true");
	}
	
	ob.areaSoporte = function(){
		
		var area = ob.combo.areaSoporte.val();
		ob.panel.area_desarrollo.hide();
		ob.panel.area_proveedor.hide();
		
		
		ob.panel['area_'+area].show();
		
	}
	
    ob.sendForm= function(){
    	
    	if(!confirm("Esta seguro de registrar la solución propuesta?")){
    		return false;
    	}
    	
    	var options = { 
	        	//target:        '#output1',   // target element(s) to be updated with server response 
	        	beforeSubmit:  function(){ console.log("evaluacion........") },  // pre-submit callback 
	        	success:   function(rs){ 
	        		alert("Se registro la solución.");
	        		//ob.backConsulta();
	        		
	        	},  // post-submit callback 
	        	error:function(){ console.log("error") },
	        	// other available options: 
	        	url:"/atencion-incidente/solicitud/evaluar",
	        	type: "POST",
	        	dataType:  'json',        // 'xml', 'script', or 'json' (expected server response type) 
	        	clearForm: true        // clear all form fields after successful submit 
	        
	    }; 
		
		var form = ob.form.evaluar;
	    // bind form using 'ajaxForm' 
		form.ajaxForm(options); 
		form.submit();
	}
    
    ob.backConsulta = function(){
    	window.open("/atencion-incidente/solicitud/consulta2",'_self');
    }
    
    
    /*--------------evaluacion-----------------*/
    ob.nroFileEval = 1;
	ob.adjuntarFileEval = function(){

		
		var fileActive = $("#fileEvaluacion");
		var table = $(".tb_file_eval");
		var nomFile = fileActive.val();
		
		if(nomFile==""){
			alert("debe seleccionar un archivo");
			return false;
		}
		
		var fechaCarga = moment().format("DD/MM/YYYY");
		var html = "<tr><td>"+ob.nroFileObs +" </td><td>"+nomFile  +"</td><td>"+fechaCarga +" </td><td><a class='pull-right deleteFile' >X</a></td> </tr>";
		ob.nroFileObs++;
		table.append(html);
		var parent = fileActive.parent();
		var copy = fileActive.clone();
		fileActive.removeClass("active-file-eval").hide();
		parent.append(copy);

	}
	
	ob.cerrarAceptar = function(){
    	
    	ob.sendForm();
    }
    
    /*-----------------------Soporte-----------------------*/
    
    
    ob.soporteGuardar = function(){
    	
    	if(ob.combo.areaSoporte.val()==1){
    		
    		if($("#detalleAnalisisSoporte").val()==''){
    			alert('Ingresar Detalle de analisis');
    			return false;
    		}
    		
    		}else{
    		
    		if($("#detalleAnalisisProveedor").val()==''){
        		alert('Ingresar Detalle de analisis');
        		return false;
        	}
    	}
    	
    	ob.sendForm();
    }
    
    /*---------rechazar----------*/
    
    ob.rechazar = function(){
    	
    	ob.modal.rechazar.modal("show");
	}
    
    ob.saveRechazo = function(){
    	
    	if($("#modalRechazoDetalle").val()==''){
			alert('Ingresar Detalle');
			return false;
		}
    
    	ob.sendForm();
    }

	ob.noSolucion = function(){

		ob.modal.observar.modal("hide");
	}
    
	ob.getParamsFormData = function(){

		var params = ob.form.evaluar.serialize(true);

		return params;
	}
    /*--------------Observar---------------------------*/
    
	ob.observar = function(){		
		
		ob.modal.observar.modal("show");
		
		var data = ob.getParamsFormData();
		
		$.post(ob.url.solucion,data,function(rsp){
			
			$("#idSolucion").text(rsp[0].ID_SOLUCION)
			$("#idSolucion").val(rsp[0].ID_SOLUCION)
			
			$("#idAnalista").text(rsp[0].ID_ANALISTA)
			$("#idAnalista").val(rsp[0].ID_ANALISTA)
			
			$("#modalAnalista").text(rsp[0].ANALISTA)
			
			$("#modalObservacionDetalle").text(rsp[0].ACCIONES + "\n" + rsp[0].AVALIDACIONES );
			
			$("#captionAcciones").text("Descargar script acciones: ");
			$("#linkAcciones").text(rsp[0].SCRIPTACC);
			$("#linkAcciones").attr("href",rsp[0].PATHACC)
			
			$("#modalConsideraciones").text(rsp[0].CONSIDERACIONES + "\n" + rsp[0].CVALIDACIONES);
			
			$("#captionConsider").text("Descargar script consideraciones: ");
			$("#linkConsider").text(rsp[0].SCRIPTCON);
			$("#linkConsider").attr("href",rsp[0].PATHCON)
			
			//$("#modalObservacionDetalle").val(rsp[0].ID_SOLUCION + "\n");
			
			//alert("getsolucion regreso");
		},'json')
		
	}
   
    ob.saveObservacion = function(){
    	
    	if(!confirm("Estas seguro de registrar la solucion propuesta?")){
    		return false;
    	}
    	
    	var options = { 
	        	//target:        '#output1',   // target element(s) to be updated with server response 
	        	beforeSubmit:  function(){ console.log("evaluacion........") },  // pre-submit callback 
	        	success:   function(rs){ 
	        		alert("Se registro la solucion propuesta.");
	        		ob.modal.observar.modal("hide");
	        		$("#btnProcesarSolucion").attr("disabled",true);
	        		
	        	},  // post-submit callback 
	        	error:function(){ console.log("error") },
	        	// other available options: 
	        	url:"/atencion-incidente/solicitud/solucionAF",
	        	type: "POST",
	        	dataType:  'json',        // 'xml', 'script', or 'json' (expected server response type) 
	        	clearForm: false        // clear all form fields after successful submit 
	        
	    }; 
		
		var form = ob.form.evaluar;
	    // bind form using 'ajaxForm' 
		form.ajaxForm(options); 
		form.submit();
    }
   
    ob.nroFileObs = 1;
    
	ob.adjuntarFileObs = function(){

		
		var fileActive = $("#file_observacion");
		var table = $(".tbObservacionFiles");
		var nomFile = fileActive.val();
		
		if(nomFile==""){
			alert("debe seleccionar un archivo");
			return false;
		}
		
		var fechaCarga = moment().format("DD/MM/YYYY");
		var html = "<tr><td>"+ob.nroFileObs +" </td><td>"+nomFile  +"</td><td>"+fechaCarga +" </td><td><a class='pull-right deleteFile' >X</a></td> </tr>";
		ob.nroFileObs++;
		table.append(html);
		var parent = fileActive.parent();
		var copy = fileActive.clone();
		fileActive.removeClass("active-file-observacion").hide();
		parent.append(copy);

	}
	
	evaluarSolicitud.ini();
	
	$("tbody").on("click",'a.deleteFile',function(){
		$(this).parent('td').parent('tr').remove();
	})
	
});

