package pe.upc.atencionincidente.web;

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
import org.springframework.web.servlet.ModelAndView;

import pe.upc.atencionincidente.model.KbIncidente;
import pe.upc.atencionincidente.model.KbIncidenteKeyValues;
import pe.upc.atencionincidente.service.IncidenteService;
import pe.upc.atencionincidente.service.SolicitudService;

@Controller
@RequestMapping(value="/incidente")
public class IncidenteController {
	
	@Autowired
	SolicitudService solicitudService;
	
	@Autowired
	IncidenteService incidenteService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView init() {
		ModelAndView mav = new ModelAndView("incidente");
		mav.addObject("tiposSolicitud", this.getListTipoSolicitud() );
		mav.addObject("sistemas", this.getListSistemas() );
		mav.addObject("incidente", new KbIncidente() );
		return mav;
	}
	
	@RequestMapping(value="buscar", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> buscarKbIncidente(@ModelAttribute KbIncidente form){
		Map<String,Object> data = new HashMap<String,Object>();
	
		form.setTipoConsulta("1");
		List<KbIncidente> incidentes = incidenteService.buscarKbIncidente(form);
		
		data.put("data", incidentes);
		
		if(form.getIdSubproceso() != null && !form.getIdSubproceso().equals("") && incidentes.size() > 0 ){
			int secuencia = incidenteService.obtenerSecuencia();
			data.put("secuencia", secuencia);
		}
		return data;
	}
	
	@RequestMapping(value="/{idIncidenteBase}/agregarValorClave", method=RequestMethod.GET)
	public ModelAndView cargarValorClave(@PathVariable(value="idIncidenteBase") String idIncidenteBase){
		ModelAndView mav = new ModelAndView("incidente_valor_clave");
	
		KbIncidente form = new KbIncidente();
		form.setIdIncidenteBase(idIncidenteBase);
		form.setTipoConsulta("7");
		List<KbIncidente> incidentes = incidenteService.buscarKbIncidente(form);
		
		if(incidentes.size() > 0){
			KbIncidente kbIncidente = incidentes.get(0);
			mav.addObject("tiposSolicitud", this.getListTipoSolicitud() );
			mav.addObject("sistemas", this.getListSistemas() );
			mav.addObject("procesos", this.listProcesos(kbIncidente.getIdSistema()) );
			mav.addObject("subProcesos", this.listSubProcesos(kbIncidente.getIdSistema(), kbIncidente.getIdProceso()) );
			mav.addObject("incidente",  kbIncidente);
			mav.addObject("valoresClave",  this.getListKeyValues());
		}

		return mav;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> registrarKbIncidente(@ModelAttribute KbIncidente form){
		Map<String,Object> data = new HashMap<String,Object>();
	
		form.setUsuarioAdicion("ADMIN");
		String idIncidenteBase = incidenteService.registrarKbIncidente(form);

		data.put("idIncidenteBase", idIncidenteBase);

		return data;
	}
	
	@RequestMapping(value="/agregarValorClave", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> registrarKbIncidenteKeyValues(@ModelAttribute KbIncidenteKeyValues form){
		Map<String,Object> data = new HashMap<String,Object>();
	
		
		form.setUsuarioAdicion("ADMIN");
		for (String valor : form.getValoresClaveIncidente().split(",")) {
			form.setIdKeyValue(valor);
			String idIncidenteBase = incidenteService.registrarKbIncidenteKeyValues(form);
		}

		//data.put("idIncidenteBase", idIncidenteBase);

		return data;
	}
	
	@RequestMapping(value="/getListProcesos", method=RequestMethod.GET )
    public @ResponseBody Map<String,String> listProcesos(@RequestParam("idSistema") String idSistema){
		List<Map<String, Object>> lstPro = solicitudService.getListProcesos(idSistema);
		return populateCombo(lstPro);
	}
	
	@RequestMapping(value="/getListSubProcesos", method=RequestMethod.GET )
    public @ResponseBody Map<String,String> listSubProcesos(@RequestParam("idSistema") String idSistema,@RequestParam("idProceso") String idProceso){
		List<Map<String, Object>> lstSubPro = solicitudService.getListSubProcesos(idSistema, idProceso);
		return populateCombo(lstSubPro);
	}
	
	private Map<String, String> getListTipoSolicitud() {
		List<Map<String, Object>> lstSol = solicitudService.getListTipoSolicitud();
		return populateCombo(lstSol);
	}
	
	private Map<String, String> getListSistemas() {
		List<Map<String, Object>> lstSis = solicitudService.getSistemas();
		return populateCombo(lstSis);
	}
	
	private Map<String, String> getListKeyValues() {
		List<Map<String, Object>> lstSis = incidenteService.getListKeyValues();
		return populateCombo(lstSis);
	}
	
	public Map<String, String> populateCombo(List<Map<String, Object>> rows) {
		Map<String, String> data = new LinkedHashMap<String, String>();
		for (Map<String, Object> row : rows) {
			data.put(row.get("id").toString(),row.get("description").toString());
		}
		return data;
	}
	
}
