package pe.upc.atencionincidente.dao;

import java.util.List;
import java.util.Map;

import pe.upc.atencionincidente.model.KbIncidente;
import pe.upc.atencionincidente.model.KbIncidenteKeyValues;

public interface IncidenteDAO {

	List<KbIncidente> buscarKbIncidente(KbIncidente form);
	
	String registrarKbIncidente(KbIncidente form);
	
	int obtenerSecuencia();
	
	List<Map<String, Object>> getListKeyValues();
	
	String registrarKbIncidenteKeyValues(KbIncidenteKeyValues form);
	
}
