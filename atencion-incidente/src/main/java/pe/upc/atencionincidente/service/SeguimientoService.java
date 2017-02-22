package pe.upc.atencionincidente.service;

import java.util.List;

import pe.upc.atencionincidente.model.Seguimiento;
import pe.upc.atencionincidente.model.SeguimientoCarteraAF;


public interface SeguimientoService {
	
	List<SeguimientoCarteraAF> getCarteraAnalistaFuncional(Seguimiento form);

		
}
