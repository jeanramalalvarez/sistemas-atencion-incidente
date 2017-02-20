package pe.upc.atencionincidente.service;

import java.util.List;
import java.util.Map;

import pe.upc.atencionincidente.model.KbIncidente;
import pe.upc.atencionincidente.model.KbIncidenteKeyValues;
import pe.upc.atencionincidente.model.KbSolucion;
import pe.upc.atencionincidente.model.KbSolucionCheck;
import pe.upc.atencionincidente.model.KbSolucionSetup;
import pe.upc.atencionincidente.model.KbSolucionSetupCheck;

public interface IncidenteService {

	List<KbIncidente> buscarKbIncidente(KbIncidente form);
	
	String registrarKbIncidente(KbIncidente form);
	
	int obtenerSecuencia();
	
	List<Map<String, Object>> getListKeyValues();
	
	String registrarKbIncidenteKeyValues(KbIncidenteKeyValues form);
	
	List<KbIncidente> buscarKbIncidenteValorClave(KbIncidente form);
	
	void eliminarKbIncidenteKeyValues(KbIncidenteKeyValues form);
	
	List<KbSolucion> buscarKbSolucion(KbSolucion form);
	
	String registrarKbSolucion(KbSolucion form);
	
	void eliminarKbSolucion(KbSolucion form);
	
	List<KbSolucionSetup> buscarKbSolucionSetup(KbSolucionSetup form);
	
	String registrarKbSolucionSetup(KbSolucionSetup form);
	
	void eliminarKbSolucionSetup(KbSolucionSetup form);
	
	List<KbSolucionCheck> buscarKbSolucionCheck(KbSolucionCheck form);
	
	String registrarKbSolucionSetupCheck(KbSolucionSetupCheck form);
	
	void eliminarKbSolucionSetupCheck(KbSolucionSetupCheck form);
	
}
