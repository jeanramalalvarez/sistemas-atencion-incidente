package pe.upc.atencionincidente.dao;

import java.util.List;

import pe.upc.atencionincidente.model.Analista;
import pe.upc.atencionincidente.model.Seguimiento;
import pe.upc.atencionincidente.model.SeguimientoCarteraAF;
import pe.upc.atencionincidente.model.SeguimientoCarteraCTI;
import pe.upc.atencionincidente.model.SeguimientoDemandaOferta;


public interface SeguimientoDAO {
	
	List<Analista> getAnalista();
	
	List<SeguimientoCarteraAF> getCarteraAF(Seguimiento form);
	
	List<SeguimientoCarteraCTI> getCarteraCTI(Seguimiento form);

	List<SeguimientoDemandaOferta> getDemandaOferta(Seguimiento form);		
}
