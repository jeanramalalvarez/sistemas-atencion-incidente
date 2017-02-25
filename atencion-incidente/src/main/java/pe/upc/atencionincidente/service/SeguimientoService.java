package pe.upc.atencionincidente.service;

import java.util.List;

import pe.upc.atencionincidente.model.Analista;
import pe.upc.atencionincidente.model.Seguimiento;
import pe.upc.atencionincidente.model.SeguimientoCarteraAF;
import pe.upc.atencionincidente.model.SeguimientoCarteraCTI;
import pe.upc.atencionincidente.model.SeguimientoDemandaOferta;
import pe.upc.atencionincidente.model.SeguimientoProductividad;


public interface SeguimientoService {
	
	List<Analista> getAnalista();
	
	List<SeguimientoCarteraAF> getCarteraAF(Seguimiento form);
	
	List<SeguimientoCarteraCTI> getCarteraCTI(Seguimiento form);

	List<SeguimientoDemandaOferta> getDemandaOferta(Seguimiento form);
	
	List<SeguimientoProductividad> getProductividad(Seguimiento form);
}
