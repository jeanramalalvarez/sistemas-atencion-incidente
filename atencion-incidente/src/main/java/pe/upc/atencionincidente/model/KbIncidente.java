package pe.upc.atencionincidente.model;

public class KbIncidente{
	
	private String idIncidenteBase;
	
	private String idTipoSolicitud;
	
	private String idSistema;
	
	private String idProceso;
	
	private String idSubproceso;
	
	private String nuSecuencia;
	
	private String txtDescripcion;
	
	private String flgResolucion;
	
	public String getIdIncidenteBase() {
		return idIncidenteBase;
	}
	
	public void setIdIncidenteBase(String idIncidenteBase) {
		this.idIncidenteBase = idIncidenteBase;
	}

	public String getIdTipoSolicitud() {
		return idTipoSolicitud;
	}

	public void setIdTipoSolicitud(String idTipoSolicitud) {
		this.idTipoSolicitud = idTipoSolicitud;
	}

	public String getIdSistema() {
		return idSistema;
	}

	public void setIdSistema(String idSistema) {
		this.idSistema = idSistema;
	}

	public String getIdProceso() {
		return idProceso;
	}

	public void setIdProceso(String idProceso) {
		this.idProceso = idProceso;
	}

	public String getIdSubproceso() {
		return idSubproceso;
	}

	public void setIdSubproceso(String idSubproceso) {
		this.idSubproceso = idSubproceso;
	}

	public String getNuSecuencia() {
		return nuSecuencia;
	}

	public void setNuSecuencia(String nuSecuencia) {
		this.nuSecuencia = nuSecuencia;
	}

	public String getTxtDescripcion() {
		return txtDescripcion;
	}

	public void setTxtDescripcion(String txtDescripcion) {
		this.txtDescripcion = txtDescripcion;
	}

	public String getFlgResolucion() {
		return flgResolucion;
	}

	public void setFlgResolucion(String flgResolucion) {
		this.flgResolucion = flgResolucion;
	}
	
}
