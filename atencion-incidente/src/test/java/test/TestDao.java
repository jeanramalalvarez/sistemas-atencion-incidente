package test;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pe.upc.atencionincidente.dao.SolicitudDAO;
import pe.upc.atencionincidente.form.DatosSolicitudForm;
import pe.upc.atencionincidente.model.Solicitante;
import pe.upc.atencionincidente.model.Solicitud;

public class TestDao {

	public static void main(String[] args) throws SQLException {
		
		
		  ApplicationContext context = new ClassPathXmlApplicationContext("test-dao.xml");
	 
	      SolicitudDAO solicitudDAO = (SolicitudDAO) context.getBean("solicitudDAO");
	      
	      //solicitudDAO.query();
	      
	      //List<Map<String, Object>> lstTipoSoli = solicitudDAO.getListTipoSolicitud();
	      //List<Map<String, Object>> lstTipoSoli = solicitudDAO.getListEstado();  
	      
	     // List<Solicitud> list = solicitudDAO.consultarSolicitud();
	      
	     // System.out.println(list.get(0).getNroCti());
	      
	      DatosSolicitudForm form = new DatosSolicitudForm();
	      Solicitante solicitante = new Solicitante();
	      
	      form.setSistema("1");
	      form.setProceso("1");
	      form.setSubProceso("1");
	      form.setTipoSolicitud("2");

			solicitante.setAgencia("1");
			solicitante.setArea("1");
			solicitante.setId("1");
	      
	      
	      String a = solicitudDAO.saveSolicitud(form, solicitante);
	      
	      System.out.println(a);

	}

}
