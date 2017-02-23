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

import pe.upc.atencionincidente.model.Analista;
import pe.upc.atencionincidente.model.Seguimiento;
import pe.upc.atencionincidente.model.SeguimientoCarteraAF;
import pe.upc.atencionincidente.model.SeguimientoCarteraCTI;
import pe.upc.atencionincidente.service.SeguimientoService;

@Controller
@RequestMapping(value="/seguimiento")
public class SeguimientoController {
	
	@Autowired
	SeguimientoService seguimientoService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView init() {
		ModelAndView mav = new ModelAndView("seguimiento");
		mav.addObject("analistaList", this.getAnalistaList() );
		mav.addObject("analistaList", this.getAnalistaList() );
		Seguimiento form = new Seguimiento();
		form.setTipoConsulta("1");
		form.setIdAnalista("0");
		form.setFecInicio("2016-01-01");
		form.setFecFin("2017-01-31");
		mav.addObject("carteraAF", seguimientoService.getCarteraAF(form) );
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> buscarSeguimiento(@ModelAttribute Seguimiento form){
		System.out.println("buscarSeguimiento");
		Map<String,Object> data = new HashMap<String,Object>();
	
		form.setTipoConsulta("1");
		List<SeguimientoCarteraAF> carteraAFList = seguimientoService.getCarteraAF(form);
		
		form.setTipoConsulta("2");
		List<SeguimientoCarteraCTI> carteraCTIList = seguimientoService.getCarteraCTI(form);
		
		data.put("carteraAFList", carteraAFList);
		data.put("carteraCTIList", carteraCTIList);

		return data;
	}
	
	private Map<String, String> getAnalistaList() {
		List<Analista> analistaList = seguimientoService.getAnalista();
		Map<String, String> list = new HashMap<String, String>();
		for (Analista analista : analistaList) {
			list.put(analista.getId(), analista.getNombresApellidos());
		}
		return list;
	}
	
}
