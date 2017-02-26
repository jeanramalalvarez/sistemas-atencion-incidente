package pe.upc.atencionincidente.daoImpl;

import java.util.List;

import pe.upc.atencionincidente.model.KbSolucionCheck;

public interface SolucionCheckDao {

	List<KbSolucionCheck> buscarKbSolucionCheck(KbSolucionCheck form);

	String registrarKbSolucionCheck(KbSolucionCheck form);

	void eliminarKbSolucionCheck(KbSolucionCheck form);

}
