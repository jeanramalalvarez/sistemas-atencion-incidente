package pe.upc.atencionincidente.dao;

import java.util.List;

import pe.upc.atencionincidente.model.KbValidaRegistro;
import pe.upc.atencionincidente.model.KbValidaRegistroDet;

public interface ValidaRegistroDAO {
	
	List<KbValidaRegistro> buscarKbValidaRegistro(KbValidaRegistro form);
	String registrarKbValidaRegistro(KbValidaRegistro form);
	void eliminarKbValidaRegistro(KbValidaRegistro form);
	List<KbValidaRegistroDet> buscarKbValidaRegistroDet(KbValidaRegistroDet form);
	String registrarKbValidaRegistroDet(KbValidaRegistroDet form);
	void eliminarKbValidaRegistroDet(KbValidaRegistroDet form);
	
}