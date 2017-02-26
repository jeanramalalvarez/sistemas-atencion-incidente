package pe.upc.atencionincidente.service;

import java.util.List;

import pe.upc.atencionincidente.model.KbSolucionCheck;

public interface SolucionCheckService {

	List<KbSolucionCheck> buscarKbSolucionCheck(KbSolucionCheck form);

	String registrarKbSolucionCheck(KbSolucionCheck form);

	void eliminarKbSolucionCheck(KbSolucionCheck form);

}
