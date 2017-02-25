var seguimientoForm;

$(document).ready(function() {
	
    seguimientoForm = {
    		analista:	function(){  return  $("#idAnalista").val(); },
    		fecIni:		function(){  return  $("#fecha_ini").val(); },
    		fecFin:		function(){  return  $("#fecha_fin").val(); } ,
    		
    		url:{
    			form:"/atencion-incidente/seguimiento"
    		},
    		btnGenerar:	$("#btn_generar"),
    		tbCarteraAF:	$("#tb_carteraAF"),
    		cnvCarteraCTI: $("#cnv_carteraCTI"),
    		cnvDemandaOferta: $("#cnv_demandaOferta"),
    }
    
    seguimientoForm.getParams = function() {
    	return {
    			idAnalista:	seguimientoForm.analista(),
    			fecInicio:	seguimientoForm.fecIni(),
    			fecFin:		seguimientoForm.fecFin(),
    	};
    };

    seguimientoForm.btnGenerar.on('click', function(){
    	seguimientoForm.btnGenerar.attr("disabled", true);
    	var data = seguimientoForm.getParams();
    	seguimientoForm.buscar(data);
    });
    
    seguimientoForm.buscar = function(data){
		$.post(seguimientoForm.url.form, data, function(rsp){
			console.log(rsp);
			seguimientoForm.getCarteraAF(rsp.carteraAFList);
			seguimientoForm.getCarteraCTI(rsp.carteraCTIList);
			seguimientoForm.getDemandaOferta(rsp.demandaOfertaList);
			
			seguimientoForm.btnGenerar.attr("disabled", false);
		},'json')
	}
    
    seguimientoForm.getCarteraAF = function(list){
    	
		seguimientoForm.tbCarteraAF.find('tbody').html('');
		
		$.each(list, function(key, value){
			seguimientoForm.tbCarteraAF.find('tbody').append('<tr><td>'+value.analistaFunciona+'</td><td>'+value.enProceso+'</td><td>'+value.asignado+'</td><td>'+value.total+'</td></tr>');
		});
		
    }
    
    seguimientoForm.getCarteraCTI = function(list){
		
    	var labels = [];
		var data = [];
		var i= 0;
		$.each(list, function(key, value){
			labels[i] = value.estado;
			data[i] = value.total;
			i++;
		});
		
		var ctx = seguimientoForm.cnvCarteraCTI;
		
		var pieChart = new Chart(ctx, {
			type: 'pie',
			data: {
			datasets: [{
			  data: data,
			  backgroundColor: [
				"#455C73",
				"#FF4C01",
				"#BDC3C7"
			  ],
			  label: 'My dataset' // for legend
			}],
			labels: labels
		  },
					
		  otpions: {
			  legend: {
                    position:"top"
                    
               }
		  }
				  
		});
		
    }
    
    seguimientoForm.getDemandaOferta = function(list){
		
    	var labels = [];
		var dataCartera = [];
		var dataCapacidad = [];
		var dataOferta = [];
		var dataDemanda = [];
		var i= 0;
		$.each(list, function(key, value){
			labels[i] = value.fecha;
			dataCartera[i] = value.cartera==0?null:value.cartera;
			dataCapacidad[i] = value.capacidad==0?null:value.capacidad;
			dataOferta[i] = value.oferta;
			dataDemanda[i] = value.demanda;
			i++;
		});
		
		var ctx = seguimientoForm.cnvDemandaOferta;
		
		var mybarChart = new Chart(ctx, {
			type: 'bar',
			data: {
				labels: labels,
				datasets: [
				           {
				        	   type: 'line',
				        	   label: 'Cartera',
				        	   backgroundColor: "#2F8900",
				        	   borderColor: '#2F8900',
				        	   borderWidth: 0,
				        	   fill: false,
				        	   lineTension: 0,
				        	   data: dataCartera,
						   	},
						   	{
						   		type: 'line',
						   		label: 'Capacidad',
						   		backgroundColor: "#e36906",
						   		borderColor: '#e36906',
						   		borderWidth: 0,
						   		fill: false,
						   		lineTension: 0,
						   		data: dataCapacidad,
						   	},
						   	{
						   		type: 'bar',
						   		label: 'Oferta',
						   		backgroundColor: "#bf4f4c",
						   		borderColor: '#bf4f4c',
						   		borderWidth: 0,
						   		data: dataOferta,
						   	},
						   	{
						   		type: 'bar',
						   		label: 'Demanda',
						   		backgroundColor: "#4e7abc",
						   		borderColor: '#4e7abc',
						   		borderWidth: 0,
						   		data: dataDemanda,
						   	},
					  ]
				},
				options: {
					title:{
	                  display:false,
	                  text:"Demanda vs Oferta"
	              },/*
					hover: {
	              mode: 'label'
	          },
	              tooltips: {
	                  mode: 'label'
	              },*/
	              responsive: true,
					scales: {
						xAxes: [{
								stacked: true,
								/*gridLines: {
									zeroLineColor: "rgba(0,255,0,1)"
								}*/
							}],
						yAxes: [{
						  ticks: {
							beginAtZero: false,
							stacked: true
						  },
						  
						}],
				  }
				}
			  });
		
    }
    
    $('#fecha_ini').val('2016-01-01');
    $('#fecha_fin').val('2018-01-01');
    
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

    seguimientoForm.getCarteraCTI(null);
    seguimientoForm.getDemandaOferta(null);
    
} );
