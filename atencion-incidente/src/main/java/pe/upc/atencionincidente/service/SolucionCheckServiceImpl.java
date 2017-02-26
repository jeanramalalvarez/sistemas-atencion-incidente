package pe.upc.atencionincidente.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.upc.atencionincidente.daoImpl.SolucionCheckDao;
import pe.upc.atencionincidente.model.KbSolucionCheck;
@Service
public class SolucionCheckServiceImpl implements SolucionCheckService {

	@Autowired
	SolucionCheckDao solucionCheckDao;
	
	@Override
	public List<KbSolucionCheck> buscarKbSolucionCheck(KbSolucionCheck form) {
		return solucionCheckDao.buscarKbSolucionCheck(form);
	}

	@Override
	public String registrarKbSolucionCheck(KbSolucionCheck form) {
		return solucionCheckDao.registrarKbSolucionCheck(form);
	}

	@Override
	public void eliminarKbSolucionCheck(KbSolucionCheck form) {
		solucionCheckDao.eliminarKbSolucionCheck(form);		
	}

}
