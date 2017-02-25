package pe.upc.atencionincidente.service;

import java.util.List;
import java.util.Map;

import pe.upc.atencionincidente.model.KbValidaRegistro;
import pe.upc.atencionincidente.model.KbValidaRegistroDet;

public interface ValidaRegistroService {
	
	List<KbValidaRegistro> buscarKbValidaRegistro(KbValidaRegistro form);
	String registrarKbValidaRegistro(KbValidaRegistro form);
	List<Map<String, Object>> getListKeyValues();
	void eliminarKbValidaRegistro(KbValidaRegistro form);
	List<KbValidaRegistroDet> buscarKbValidaRegistroDet(KbValidaRegistroDet form);
	String registrarKbValidaRegistroDet(KbValidaRegistroDet form);
	void eliminarKbValidaRegistroDet(KbValidaRegistroDet form);

}
