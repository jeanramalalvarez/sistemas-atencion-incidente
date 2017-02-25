package pe.upc.atencionincidente.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.upc.atencionincidente.dao.SeguimientoDAO;
import pe.upc.atencionincidente.model.Analista;
import pe.upc.atencionincidente.model.Seguimiento;
import pe.upc.atencionincidente.model.SeguimientoCarteraAF;
import pe.upc.atencionincidente.model.SeguimientoCarteraCTI;
import pe.upc.atencionincidente.model.SeguimientoDemandaOferta;

@Service
public class SeguimientoServiceImpl implements SeguimientoService {
	
	@Autowired
	private SeguimientoDAO seguimientoDAO;
	
	@Override
	public List<Analista> getAnalista() {
		return seguimientoDAO.getAnalista();
	} 

	@Override
	public List<SeguimientoCarteraAF> getCarteraAF(Seguimiento form) {
		return seguimientoDAO.getCarteraAF(form);
	}

	@Override
	public List<SeguimientoCarteraCTI> getCarteraCTI(Seguimiento form) {
		return seguimientoDAO.getCarteraCTI(form);
	}

	@Override
	public List<SeguimientoDemandaOferta> getDemandaOferta(Seguimiento form) {
		return seguimientoDAO.getDemandaOferta(form);
	}
	
}
