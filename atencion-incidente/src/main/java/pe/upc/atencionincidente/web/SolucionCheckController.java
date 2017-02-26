package pe.upc.atencionincidente.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pe.upc.atencionincidente.model.KbSolucionCheck;
import pe.upc.atencionincidente.service.SolucionCheckService;

@Controller
@RequestMapping(value="/solucionCheck")
public class SolucionCheckController {
	
	@Autowired
	SolucionCheckService 	solucionCheckService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView init() {
		ModelAndView mav = new ModelAndView("solucionCheck");
		mav.addObject("solucionCheck", new KbSolucionCheck());
		return mav;
	}
	

	@RequestMapping(value="buscar", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> buscarKbSolucionCheck(@ModelAttribute KbSolucionCheck form){
		System.out.println("buscarKbSolucionCheck");
		Map<String,Object> data = new HashMap<String,Object>();
	
		form.setTipoConsulta("1");
	
		List<KbSolucionCheck> solucionCheck = solucionCheckService.buscarKbSolucionCheck(form);
		
		data.put("data", solucionCheck);
		
//		if(form.getIdSubproceso() != null && !form.getIdSubproceso().equals("") && incidentes.size() > 0 ){
//			int secuencia = incidenteService.obtenerSecuencia();
//			data.put("secuencia", secuencia);
//		}
		return data;
	}
		
	@RequestMapping(value="registrar", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> registrarKbSolucionCheck(@ModelAttribute KbSolucionCheck form){
		System.out.println("registrarKbSolucionCheck");
		Map<String,Object> data = new HashMap<String,Object>();
	
		form.setUsuarioAdicion("ADMIN");
		String idSolucionCh = solucionCheckService.registrarKbSolucionCheck(form);

		data.put("idSolucionCh", idSolucionCh);

		return data;
	}
	
	@RequestMapping(value="/cargar", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> cargarKbSolucionCheck(@ModelAttribute KbSolucionCheck form){
		System.out.println("cargarKbSolucionCheck");
		
		form.setTipoConsulta("2");
		List<KbSolucionCheck> validaRegistros = solucionCheckService.buscarKbSolucionCheck(form);
		KbSolucionCheck KbSolucionCheck = validaRegistros.get(0);

		Map<String,Object> data = new HashMap<String,Object>();		
		data.put("validaRegistro",  KbSolucionCheck);

		return data;
	}
	
	@RequestMapping(value="/eliminar", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> eliminarKbSolucionCheck(@ModelAttribute KbSolucionCheck form){
		System.out.println("eliminarKbSolucionCheck");
		
		solucionCheckService.eliminarKbSolucionCheck(form);

		Map<String,Object> data = new HashMap<String,Object>();		
		data.put("mensaje",  "ok");

		return data;
	}

}
