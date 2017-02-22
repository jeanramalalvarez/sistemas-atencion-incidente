package pe.upc.atencionincidente.dao;

import java.util.List;

import pe.upc.atencionincidente.model.Analista;
import pe.upc.atencionincidente.model.Seguimiento;
import pe.upc.atencionincidente.model.SeguimientoCarteraAF;


public interface SeguimientoDAO {
	
	List<Analista> getAnalista();
	
	List<SeguimientoCarteraAF> getCarteraAnalistaFuncional(Seguimiento form);

		
}
