package pe.upc.atencionincidente.dao;

import java.util.List;
import java.util.Map;

import pe.upc.atencionincidente.model.KbIncidente;
import pe.upc.atencionincidente.model.KbIncidenteKeyValues;
import pe.upc.atencionincidente.model.KbSolucion;
import pe.upc.atencionincidente.model.KbSolucionSetup;

public interface IncidenteDAO {

	List<KbIncidente> buscarKbIncidente(KbIncidente form);
	
	String registrarKbIncidente(KbIncidente form);
	
	int obtenerSecuencia();
	
	List<Map<String, Object>> getListKeyValues();
	
	String registrarKbIncidenteKeyValues(KbIncidenteKeyValues form);
	
	List<KbIncidente> buscarKbIncidenteValorClave(KbIncidente form);
	
	List<KbSolucion> buscarKbSolucion(KbSolucion form);
	
	String registrarKbSolucion(KbSolucion form);
	
	void eliminarKbSolucion(KbSolucion form);
	
	List<KbSolucionSetup> buscarKbSolucionSetup(KbSolucionSetup form);
	
	String registrarKbSolucionSetup(KbSolucionSetup form);
	
	void eliminarKbSolucionSetup(KbSolucionSetup form);
	
}
