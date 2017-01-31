package pe.upc.atencionincidente.web;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import pe.upc.atencionincidente.form.ConsultaIncidenteForm;
import pe.upc.atencionincidente.form.DatosSolicitudForm;
import pe.upc.atencionincidente.form.DetalleIncidenteForm;
import pe.upc.atencionincidente.form.EvaluarIncidenteForm;
import pe.upc.atencionincidente.model.Analista;
import pe.upc.atencionincidente.model.AnexoSolicitud;
import pe.upc.atencionincidente.model.Incidente;
import pe.upc.atencionincidente.model.Solicitante;
import pe.upc.atencionincidente.model.Solicitud;
import pe.upc.atencionincidente.service.EmailService;
import pe.upc.atencionincidente.service.SolicitudService;

@Controller
@RequestMapping(value="/solicitud")
public class SolicitudController {
	
	@Autowired
	SolicitudService solicitudService;
	
	@Autowired
	EmailService emailService;
	
	@RequestMapping(value = "/evaluar/{nroSolicitud}",method = RequestMethod.GET)
	public ModelAndView evaluar(@PathVariable(value="nroSolicitud") String nroSolicitud) {
		
		ModelAndView mav = this.editar(nroSolicitud);
		
		DatosSolicitudForm solicitud = solicitudService.getSolicitud(nroSolicitud);
		
		System.out.println("solicitud.getId_solicitud()");
		System.out.println(solicitud.getId_solicitud());
		
		String idSolicitud = solicitudService.updateEstadoIncidente(solicitud.getId_solicitud(),"EPA");
		
		System.out.println("IDSOLICITUD: " + idSolicitud);
		
		if( !idSolicitud.equals("0") ){
			System.out.println("IDSOLICITUD2: " + idSolicitud);

			/*----------send notificacion--------------*/
			Map<String,Object> mapNotificacion = new HashMap<String,Object>();
	        
			mapNotificacion.put("idSolicitud",solicitud.getId_solicitud());
			
			String mensaje = solicitudService.mensajeNotificacion(mapNotificacion);
			
			if( mensaje != null || mensaje != ""){
				
				String emailOp = solicitudService.getEmailOp(idSolicitud);
				
				System.out.println("EMAIL OPERATIVO: " + emailOp);
				
				 Thread thread = new Thread("Enviando Notificacion") {
				      public void run(){
				         System.out.println("run by: " + getName());
				         enviarNotificacion(solicitud.getNroCti(),mensaje,emailOp,"EN ATENCION - ");
				      }
				   };
				   thread.start();
			}
			/*----------send notificacion--------------*/
		}
		
		Analista analista = this.getAnalista();
		
		mav.addObject("analistaName", analista.getNombresApellidos());
		
		/*------------Soporte Area Desarrollo------------------*/
		mav.addObject("servicioDesarrollo","Servicio Desarrollo");
		mav.addObject("JefeDesarrollo","Jefe De Desarrollo");
		mav.addObject("AnalistaFuncional", "Jorge Reyes");
		
		/*------------Soporte Proveedor------------------*/
		mav.addObject("nombreProveedor","Cosapi Data");
		
		mav.setViewName("evaluar");

		return mav;
	}
	
	@RequestMapping(value = "/evaluar",method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> evaluarSave(@ModelAttribute EvaluarIncidenteForm form) {
		
		Map<String,Object> data = new HashMap<String,Object>();
		
		String newEvalSol = solicitudService.saveEvaluacion(form);
		System.out.println("newEvalSol: " + newEvalSol);
		
		String msg = "Se procedio a cerrar solicitud ";
		String nroCti = "CTI" + "16" + "0000" + form.getNroSolicitud();
		
		if(null!=form.getModalObservacionDetalle() && form.getModalObservacionDetalle()!=""){
			System.out.println("se observa: ");
			msg = "Se procedio a observar solicitud ";
		}
		
		if(null!=form.getDetalleAnalisisSoporte() && form.getDetalleAnalisisSoporte()!=""){
			System.out.println("PENDIENTE DE APROBACION DESARROLLO: ");
			msg = "Se procedio a observar solicitud ";
		}
		
		if(null!=form.getDetalleAnalisisProveedor() && form.getDetalleAnalisisProveedor()!=""){
			System.out.println("PENDIENTE DE APROBACION PROVEEDOR: ");
			msg = "Se procedio a observar solicitud ";
		}
		
		if(null!=form.getModalRechazoDetalle() && form.getModalRechazoDetalle()!=""){
			System.out.println("se rechaza: ");
			msg = "Se procedio a rechazar solicitud ";
		}
		

		/*----------send notificacion--------------*/
		this.enviarNotificacion(nroCti,msg,"pi2afpprima@gmail.com","");

		return data;
	}

	@RequestMapping(value = "/solucionAF",method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> saveSolucion(@ModelAttribute EvaluarIncidenteForm form) {
		
		Map<String,Object> data = new HashMap<String,Object>();
		
		String nroIncidente = solicitudService.saveSolucionAF(form);
		System.out.println("saveSolucionAF: " + nroIncidente);
		
		/*----------send notificacion--------------*/
		Map<String,Object> mapNotificacion = new HashMap<String,Object>();
        
		mapNotificacion.put("idSolicitud",form.getNroSolicitud());
		
		String mensaje = solicitudService.mensajeNotificacion(mapNotificacion);
		
		if( mensaje != null || mensaje != ""){
			
			Map<String,Object> mapAnalista = new HashMap<String,Object>(); 
			mapAnalista.put("idAnalista",form.getIdAnalista());
			
			Map<String,Object> analista = solicitudService.getAnalista(mapAnalista);
			
			 Thread thread = new Thread("Enviando Notificacion") {
			      public void run(){
			         System.out.println("run by: " + getName());
			         enviarNotificacion(nroIncidente,mensaje,String.valueOf(analista.get("correo")),"ATENDIDO - ");
			      }
			   };
			   thread.start();
		}

		return data;
	}

	@RequestMapping(value = "/consulta",method = RequestMethod.GET)
	public ModelAndView consultar() {
		
		System.out.println("consulta get: ");
		
		
		ModelAndView mav = new ModelAndView("consulta");
		mav.addObject("tipoSolicitud", this.getListTipoSolicitud() );
		//mav.addObject("estado", this.getListEstado() );

		return mav;
	}
	
	@RequestMapping(value = "/consulta2",method = RequestMethod.GET)
	public ModelAndView consultar2() {
		
		ModelAndView mav = new ModelAndView("consulta2");
		mav.addObject("tipoSolicitud", this.getListTipoSolicitud() );
		mav.addObject("estado", this.getListEstado() );
		mav.addObject("sistemas", this.getListSistemas() );
		
		Analista analista = this.getAnalista();
		
		mav.addObject("analistaName", analista.getNombresApellidos());

		return mav;
	}
	
	@RequestMapping(value = "/nuevo",method = RequestMethod.GET)
	public ModelAndView nuevo() {
		
		ModelAndView mav = new ModelAndView("nuevo");
		
		System.out.println("nuevo get: ");
	
		Solicitante solicitante = this.getSolicitante();
		mav.addObject("solicitante", solicitante);
		mav.addObject("tipoSolicitud", this.getListTipoSolicitud() );
		//mav.addObject("sistemas", this.getListSistemas() );
		//mav.addObject("nroUsAfec", this.getListUsAfectados() );

		return mav;
	}
	
	@RequestMapping(value = "/detalle/{nroSolicitud}",method = RequestMethod.GET)
	public ModelAndView detalle(@PathVariable(value="nroSolicitud") String nroSolicitud) {
		
		System.out.println("detalle get: ");
		ModelAndView mav = this.editar(nroSolicitud);
		mav.addObject("tipoSolicitud", this.getListTipoSolicitud() );
		mav.addObject("sistemas", this.getListSistemas() );
		mav.addObject("nroUsAfec", this.getListUsAfectados() );
		
		mav.setViewName("detalle");
		
		return mav;
		
	}
	

	@RequestMapping(value = "/editar/{nroSolicitud}",method = RequestMethod.GET)
	public ModelAndView editar(@PathVariable(value="nroSolicitud") String nroSolicitud) {
		
		System.out.println("nroSolicitud: " + nroSolicitud);
		
		ModelAndView mav = new ModelAndView("editar"); 
		
		mav.addObject("tipoSolicitud", this.getListTipoSolicitud() );
		mav.addObject("sistemas", this.getListSistemas() );
		mav.addObject("nroUsAfec", this.getListUsAfectados() );
		
		Solicitante solicitante = this.getSolicitante();
		mav.addObject("solicitante", solicitante);
		
		DatosSolicitudForm solicitud = solicitudService.getSolicitud(nroSolicitud);
		DetalleIncidenteForm incidente = solicitudService.getIncidente(solicitud.getId_solicitud());
		
		solicitud.setNroUsAfec(incidente.getUsAfectados());	
		List<Map<String,Object>> anexos = solicitudService.getListAnexos(solicitud.getId_solicitud());
		
		
		/*-----------------datos de la solicitud--------------------------------*/
		mav.addObject("idTipoSolicitud",solicitud.getTipoSolicitud());
		mav.addObject("idSistema",solicitud.getSistema());
		mav.addObject("idProceso",solicitud.getProceso());
		mav.addObject("idSubProceso",solicitud.getSubProceso());
		mav.addObject("idNroUsAfec",solicitud.getNroUsAfec());
		mav.addObject("cti",solicitud.getNroCti());
		
		mav.addObject("idSolicitud",solicitud.getId_solicitud());
		
		Map<String,String> procesos = this.listProcesos(solicitud.getSistema());
		mav.addObject("procesos",procesos);
		
		Map<String,String> subProcesos = this.listSubProcesos(solicitud.getSistema(),solicitud.getProceso());
		mav.addObject("subProcesos",subProcesos);
		
		/*-----------------------------------------------------------------------*/
		
		/*-----------------Detalle Solicitud---------------------*/
		mav.addObject("asunto",incidente.getAsunto());
		mav.addObject("description",incidente.getDescripcion());
		mav.addObject("afectaIdi",incidente.getAfectaIdi());
		
		mav.addObject("error",incidente.getError());
		mav.addObject("datosModif",incidente.getDatosModif());
		mav.addObject("estruct",incidente.getEstructuraReporte());
		mav.addObject("fechasCorte",incidente.getFechasCorte());
		mav.addObject("resumen",incidente.getResumen());
		mav.addObject("regulatorio",incidente.getRegulatorio());
		mav.addObject("volumenes",incidente.getVolumenes());
		
		/*--------------Anexos-------------------*/
		
		//mav.addObject("anexos",new String[]{"Hola","bien"});
		mav.addObject("anexos",anexos);
		
		return mav;
	}
	
	
	@RequestMapping(value="/updateSolicitud",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> updateSolicitud(@ModelAttribute DatosSolicitudForm form){
		Map<String,Object> data = new HashMap<String,Object>();
		
		System.out.println("updateSolicitud: ");
		System.out.println("nroSolicitud: " + form.getNroCti());
		
		Solicitante solicitante = this.getSolicitante();
		String idSolicitud = solicitudService.updateSolicitud(form, solicitante);
		
		data.put("nroCti",form.getNroCti());
		return data;
	}
	
	@RequestMapping(value="/updateIncidente", method=RequestMethod.POST )
    public @ResponseBody Map<String,Object> updateIncidente(@RequestParam("file") MultipartFile[] files,@RequestParam("file2") MultipartFile[] files2,@ModelAttribute DetalleIncidenteForm form){
		Map<String,Object> data = new HashMap<String,Object>();
		
		System.out.println("updateIncidente: ");
		System.out.println("idSolicitud: " + form.getIdSolicitud());
		
		/*---------------------------------*/
		
		Solicitante solicitante = this.getSolicitante();
		Incidente incidente = new Incidente();
		
		incidente.setIdSolicitud(form.getIdSolicitud());
		incidente.setIdAreaAtencion(solicitante.getArea());
		incidente.setNumUsuariosAfectados(form.getUsAfectados());
		incidente.setTxtAsunto(form.getAsunto());
		incidente.setDatosModif(form.getDatosModif());
		
		
		if( form.getAfectaIdi() != null ){
			form.setAfectaIdi("1");
		}else{
			form.setAfectaIdi("0");
		}
		
		System.out.println("form.getAfectaIdi: " + form.getAfectaIdi());
		
		incidente.setFlgAfectaIdi(form.getAfectaIdi());
		incidente.setTxtDescripcion(form.getDescripcion());
		incidente.setTxtError(form.getError());
		incidente.setUsuarioAdicion(solicitante.getId());
		incidente.setFlgRegulatorio("0");
		
		String nroCti = solicitudService.updateIncidente(incidente);
		Map<String,String> map = new HashMap<String,String>();
		
		map.put("nroCti",nroCti);
		
    	return data;
    }
	
	
	@RequestMapping(value="/consulta",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> consultaIncidente(@ModelAttribute ConsultaIncidenteForm form){
		Map<String,Object> data = new HashMap<String,Object>();
		
		System.out.println(form.getNroCti());
		
		Solicitante solicitante = this.getSolicitante();
		form.setUsuarioAdicion(solicitante.getId());	
		List<Solicitud> solicitudes = solicitudService.consultarSolicitud(form);
		
		data.put("data",solicitudes);
		
		return data;
	}
	
	@RequestMapping(value="/consulta2",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> consultaIncidenteAnalista(@ModelAttribute ConsultaIncidenteForm form){
		Map<String,Object> data = new HashMap<String,Object>();
		
		System.out.println(form.getNroCti());
		
		Analista analista = this.getAnalista();
		form.setIdAnalista(analista.getId());	
		List<Solicitud> solicitudes = solicitudService.consultarSolicitudAnalista(form);
		
		data.put("data",solicitudes);
		
		return data;
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> saveSolicitud(@ModelAttribute DatosSolicitudForm form){
		Map<String,Object> data = new HashMap<String,Object>();
		
		System.out.println("tipoSolicitud: " + form.getTipoSolicitud());
		
		form.setAnalistaF("1");
		Solicitante solicitante = this.getSolicitante();
		String idSolicitud = solicitudService.saveSolicitud(form, solicitante);
		String estado = idSolicitud.substring(0, 1);
		String mensaje = idSolicitud.substring(1);
		
		System.out.println("idSolicitud: " + idSolicitud);
		
		data.put("idSolicitud",idSolicitud);
		data.put("estado",estado);
		data.put("mensaje",mensaje);
		return data;
	}
	
	@RequestMapping(value="/getsol",method=RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getsol(@ModelAttribute EvaluarIncidenteForm form) {

		System.out.println("getsol: " + form.getNroSolicitud());
		
		List<Map<String, Object>> soluciones = solicitudService.getSolucionIncidente(form);
		System.out.println("getsol: " + soluciones.toString());
		
//		String estado = idSolicitud.substring(0, 1);
//		String mensaje = idSolicitud.substring(1);
//		System.out.println("idSolicitud: " + idSolicitud);
//		data.put("idSolicitud",idSolicitud);
//		data.put("estado",estado);
//		data.put("mensaje",mensaje);
		
		return soluciones;
	}
	
	@RequestMapping(value="/saveTipo_1_2", method=RequestMethod.POST )
    public @ResponseBody Map<String,String> multipleSave(@RequestParam("file") MultipartFile[] files,@ModelAttribute DetalleIncidenteForm form){
    	
		String msg = "";
		
		System.out.println("saveTipo_1_2");
		System.out.println("idSolicitud: " + form.getIdSolicitud());
		
		Solicitante solicitante = this.getSolicitante();
		Incidente incidente = new Incidente();
		
		incidente.setIdSolicitud(form.getIdSolicitud());
		incidente.setIdAreaAtencion(solicitante.getArea());
		incidente.setNumUsuariosAfectados(form.getUsAfectados());
		incidente.setTxtAsunto(form.getAsunto());
		incidente.setDatosModif(form.getDatosModif());
		
		
		if( form.getAfectaIdi() != null ){
			form.setAfectaIdi("1");
		}else{
			form.setAfectaIdi("0");
		}
		
		System.out.println("form.getAfectaIdi: " + form.getAfectaIdi());
		
		incidente.setFlgAfectaIdi(form.getAfectaIdi());
		incidente.setTxtDescripcion(form.getDescripcion());
		incidente.setTxtError(form.getError());
		incidente.setUsuarioAdicion(solicitante.getId());
		incidente.setFlgRegulatorio("0");
		
		String nroCti = solicitudService.saveIncidente(incidente);
		Map<String,String> map = new HashMap<String,String>();
		
		map.put("nroCti",nroCti);
		
		AnexoSolicitud anexo = new AnexoSolicitud();
		anexo.setIdSolicitud(form.getIdSolicitud());
		anexo.setTxtDescripcion(form.getDescripcion());
		anexo.setUsuarioAdicion(solicitante.getId());
		
		int numSecuencia;
    	
    	if (files != null && files.length >0) {
    		for(int i =0 ;i< files.length; i++){
    		 
    		 numSecuencia = i + 1;
    		 anexo.setNumSecuencia(String.valueOf(numSecuencia));	
    		 msg = this.saveFile(files[i],anexo,"nuevo/");
    		 
    		 
    		}
    	} else {
    		msg = "Unable to upload. File is empty.";
        }
    	
    	System.out.println("msg upload: " + msg);
    	
    	System.out.println("enviarNotificacion");
    	
    	
    	/*-----------asignacion-----------*/
    	
    	Map<String,Object> mapAsignacion = new HashMap<String,Object>();
    	
    	mapAsignacion.put("idSolicitud",form.getIdSolicitud());
    	mapAsignacion.put("FLG_AFECTA_IDI",form.getAfectaIdi() );
    	mapAsignacion.put("FLG_REGULATORIO", incidente.getFlgRegulatorio() );
    	
    	String idAnalista = solicitudService.asignarAnalista(mapAsignacion);
    	
      /*---------------------Notificacion---------------------*/
    	
    	System.out.println("SolicitudController idAnalista " + idAnalista);
    	
    	if( idAnalista != null && idAnalista != "null"){
    		
    		System.out.println("idAnalista != null ");
    		
    		Map<String,Object> mapNotificacion = new HashMap<String,Object>();
        
    		mapNotificacion.put("idSolicitud",form.getIdSolicitud());
    		
    		String mensaje = solicitudService.mensajeNotificacion(mapNotificacion);
    		
    		if( mensaje != null || mensaje != ""){
    			
    			Map<String,Object> mapAnalista = new HashMap<String,Object>(); 
    			mapAnalista.put("idAnalista",idAnalista);
    			
    			Map<String,Object> analista = solicitudService.getAnalista(mapAnalista);
    			
    			 Thread thread = new Thread("Enviando Notificacion") {
    			      public void run(){
    			         System.out.println("run by: " + getName());
    			         enviarNotificacion(nroCti,mensaje,String.valueOf(analista.get("correo")),"");
    			      }
    			   };
    			   thread.start();
    			
    			
    		}
    		
    	}
    	
    	
    	return map;
    }
	
	@RequestMapping(value="/saveTipo3", method=RequestMethod.POST )
    public @ResponseBody Map<String,String> multipleSave2(@RequestParam("file2") MultipartFile[] files,@ModelAttribute DetalleIncidenteForm form){
    	
		String msg = "";
		
		System.out.println("saveTipo3");
		System.out.println("idSolicitud: " + form.getIdSolicitud());
		
		Solicitante solicitante = this.getSolicitante();
		Incidente incidente = new Incidente();
		
		incidente.setIdSolicitud(form.getIdSolicitud());
		incidente.setIdAreaAtencion(solicitante.getArea());
		incidente.setNumUsuariosAfectados(form.getUsAfectados());
		incidente.setTxtDescripcion(form.getDescripcion2());
		incidente.setVolumenes(form.getVolumenes());
		incidente.setUsuarioAdicion(solicitante.getId());
		incidente.setTxtResumen(form.getResumen());
		incidente.setTxtFechasCorte(form.getFechasCorte());
		incidente.setTxtEstructuraReporte(form.getEstructuraReporte());
		
		if( form.getRegulatorio() != null ){
			form.setRegulatorio("1");
		}else{
			form.setRegulatorio("0");
		}
		
		System.out.println("form.getRegulatorio: " + form.getRegulatorio());
		
		incidente.setFlgRegulatorio(form.getRegulatorio());
		incidente.setFlgAfectaIdi("0");
		
		String nroCti = solicitudService.saveIncidente(incidente);
		Map<String,String> map = new HashMap<String,String>();
		
		map.put("nroCti",nroCti);
    	
		AnexoSolicitud anexo = new AnexoSolicitud();
		anexo.setIdSolicitud(form.getIdSolicitud());
		anexo.setTxtDescripcion(form.getDescripcion2());
		anexo.setUsuarioAdicion(solicitante.getId());
    	
		int numSecuencia;
    	
    	if (files != null && files.length >0) {
    		for(int i =0 ;i< files.length; i++){
    		 
    		 numSecuencia = i + 1;
    		 anexo.setNumSecuencia(String.valueOf(numSecuencia));	
    		 msg = this.saveFile(files[i],anexo,"nuevo/");
    		 
    		 
    		}
    	} else {
    		msg = "Unable to upload. File is empty.";
        }
    	
    	System.out.println("msg upload: " + msg);
    	
    	
    	/*-----------asignacion-----------*/
    	
    	Map<String,Object> mapAsignacion = new HashMap<String,Object>();
    	
    	mapAsignacion.put("idSolicitud",form.getIdSolicitud());
    	mapAsignacion.put("FLG_AFECTA_IDI",form.getAfectaIdi() );
    	mapAsignacion.put("FLG_REGULATORIO", incidente.getFlgRegulatorio() );
    	
    	String idAnalista = solicitudService.asignarAnalista(mapAsignacion);
    	
 /*---------------------Notificacion---------------------*/
    	
    	if( idAnalista != null && idAnalista != "null"){
    		
    		System.out.println("idAnalista != null ");
    		
    		Map<String,Object> mapNotificacion = new HashMap<String,Object>();
        
    		mapNotificacion.put("idSolicitud",form.getIdSolicitud());
    		
    		String mensaje = solicitudService.mensajeNotificacion(mapNotificacion);
    		
    		if( mensaje != null || mensaje != ""){
    			
    			Map<String,Object> mapAnalista = new HashMap<String,Object>(); 
    			mapAnalista.put("idAnalista",idAnalista);
    			
    			Map<String,Object> analista = solicitudService.getAnalista(mapAnalista);
    			
    			Thread thread = new Thread("Enviando Notificacion") {
  			      public void run(){
  			         System.out.println("run by: " + getName());
  			         enviarNotificacion(nroCti,mensaje,String.valueOf(analista.get("correo")),"");
  			      }
  			   };
  			   thread.start();
    		}
    		
    	}
    	
    	return map;
    }
	
	
	private String saveFile( MultipartFile file,AnexoSolicitud anexo, String sruta){
		
		String fileName = null;
    	String msg = "";
    	String nruta = "U:/home/CTI1600001/";
	
	      try {
	    	  		nruta = nruta + sruta;
	    	  
	                fileName = file.getOriginalFilename();
	                
	                System.out.println("fileName: " + fileName);
	                
	                anexo.setTxtRuta(fileName);
	                
	                File folder = new File(nruta);
	                
	                if ( !folder.exists()){
	                	folder.mkdirs();
	                }
	                
	                byte[] bytes = file.getBytes();
	                
	                BufferedOutputStream buffStream = 
	                        new BufferedOutputStream(new FileOutputStream(new File(nruta + fileName)));
	                buffStream.write(bytes);
	                buffStream.close();
	                msg += "You have successfully uploaded " + fileName +"<br/>";
	                
	                String ruta = solicitudService.saveAnexoSolicitud(anexo);
	                
	                return msg; 
	                
	        } catch (Exception e) {
	            	System.out.println("You failed to upload " + fileName + ": " + e.getMessage() +"<br/>");
	                return "You failed to upload " + fileName + ": " + e.getMessage() +"<br/>";
	        }

	}
	
	
	/*------------------Fill Combos-------------------*/
	
	//@ModelAttribute("tipoSolicitud")
	private Map<String, String> getListTipoSolicitud() {
		
		System.out.println("@getListTipoSolicitud: ");		
		//List<Map<String, Object>> lstSol = solicitudService.getListTipoSolicitud();
		List<Map<String, Object>> lstSol = new ArrayList<Map<String, Object>>();
		
		Map<String, Object> map = new HashMap<String, Object>();	
		map.put("id", 1);
		map.put("description", "Soporte Aplicativo");
		Map<String, Object> map2 = new HashMap<String, Object>();	
		map2.put("id", 2);
		map2.put("description", "Actualización de Datos");
		Map<String, Object> map3 = new HashMap<String, Object>();	
		map3.put("id", 3);
		map3.put("description", "Extracción de Datos");
		
		lstSol.add(map);
		lstSol.add(map2);
		lstSol.add(map3);
		
		System.out.println("fin @getListTipoSolicitud: ");	
		return populateCombo(lstSol);
	}
	
	//@ModelAttribute("nroUsAfec")
	@RequestMapping(value="/listNroUsAfec",method=RequestMethod.GET)
	public @ResponseBody Map<String, String> getListUsAfectados() {
		
		System.out.println("@getListUsAfectados : ");
		List<Map<String, Object>> lstUsAfec = solicitudService.getListUsAfectados();
		System.out.println("fin @getListUsAfectados : ");
		return populateCombo(lstUsAfec);
	}
	
	//@ModelAttribute("sistemas")
	@RequestMapping(value="/listSistemas",method=RequestMethod.GET)
	public @ResponseBody Map<String, String> getListSistemas() {
		
		System.out.println("@getListSistemas: ");
		List<Map<String, Object>> lstSis = solicitudService.getSistemas();
		System.out.println("fin @getListSistemas : ");
		return populateCombo(lstSis);
	}
	
	@RequestMapping(value="/listEstados",method=RequestMethod.GET)
	public @ResponseBody Map<String, String> getListEstado() {
		
		System.out.println("@getListEstado: ");
		List<Map<String, Object>> lstEst = solicitudService.getListEstado();
		System.out.println("Fin @getListEstado: ");
		
		return populateCombo(lstEst);
	}
	
    /*-------------------------------------------------------------*/
	
	@RequestMapping(value="/getListProcesos", method=RequestMethod.GET )
    public @ResponseBody Map<String,String> listProcesos(@RequestParam("idSistema") String idSistema){
		
		System.out.println("getListProcesos: ");
		List<Map<String, Object>> lstPro = solicitudService.getListProcesos(idSistema);
		System.out.println("fin getListProcesos: ");
		return populateCombo(lstPro);
	}
	
	@RequestMapping(value="/getListSubProcesos", method=RequestMethod.GET )
    public @ResponseBody Map<String,String> listSubProcesos(@RequestParam("idSistema") String idSistema,@RequestParam("idProceso") String idProceso){
		
		System.out.println("getListSubProcesos: ");
		List<Map<String, Object>> lstSubPro = solicitudService.getListSubProcesos(idSistema, idProceso);
		System.out.println("fin getListSubProcesos: ");
		return populateCombo(lstSubPro);
	}
	
	/*--------------------------------------------------------------*/
	
	
	
	//Utils
	public Map<String, String> populateCombo(List<Map<String, Object>> rows) {
		
		System.out.println("@populateCombo: ");
		Map<String, String> data = new LinkedHashMap<String, String>();
		for (Map<String, Object> row : rows) {
			data.put(row.get("id").toString(),row.get("description").toString());
		}
		System.out.println("fin @populateCombo: ");
		return data;
	}
	
	
	public Analista getAnalista(){
		
		Analista analista = new Analista();
		analista.setId("5");
		analista.setNombresApellidos("S77762 - Jorge Reyes");
		analista.setArea("2");
		analista.setCargo("11");
		
		return analista;
	}
	

	public Solicitante getSolicitante(){
		
		Solicitante solicitante = new Solicitante();
		solicitante.setId("JHUAMAN");
		solicitante.setTxtAgencia("Agencia-San-Isidro");
		solicitante.setAgencia("1");
		solicitante.setMatricula("SI2921");
		solicitante.setNombresApellidos("Javier Huaman");
		solicitante.setTxtArea("Operaciones");
		solicitante.setArea("2");
		solicitante.setTxtCargo("Usuario de Operaciones");
		solicitante.setCargo("11");
		solicitante.setAnexo("2121");
		
		return solicitante;
	}
	
	public void enviarNotificacion(String nroCti,String Mensaje,String correo, String asunto){
		
		String sAsunto;
		String toAddr = correo;
		String fromAddr = "pi2afpprima@gmail.com";
		
		if( asunto != null ){
			sAsunto = asunto;
		}else{
			sAsunto = "CTI - ";
		}
 
		// email subject
		String subject = sAsunto + "Solicitud de Atención de Incidente";
 
		// email body
		String body =  Mensaje + " " + nroCti;
		emailService.readyToSendEmail(toAddr, fromAddr, subject, body);
	}
	
	
}
