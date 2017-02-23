package pe.upc.atencionincidente.service;

import java.util.List;
import java.util.Map;

import pe.upc.atencionincidente.model.KbValidaRegistro;

public interface ValidaRegistroService {
	
	List<KbValidaRegistro> buscarKbValidaRegistro(KbValidaRegistro form);
	String registrarKbValidaRegistro(KbValidaRegistro form);
	List<Map<String, Object>> getListKeyValues();
	void eliminarKbValidaRegistro(KbValidaRegistro form);

}
