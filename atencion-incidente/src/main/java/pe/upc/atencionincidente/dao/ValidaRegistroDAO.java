package pe.upc.atencionincidente.dao;

import java.util.List;

import pe.upc.atencionincidente.model.KbValidaRegistro;

public interface ValidaRegistroDAO {
	
	List<KbValidaRegistro> buscarKbValidaRegistro(KbValidaRegistro form);
	String registrarKbValidaRegistro(KbValidaRegistro form);
	void eliminarKbValidaRegistro(KbValidaRegistro form);
	
}
