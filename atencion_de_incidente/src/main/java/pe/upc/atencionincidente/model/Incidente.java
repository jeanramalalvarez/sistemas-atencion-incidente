package pe.upc.atencionincidente.model;

public class Incidente{
	
	private String idSolicitud;
	private String idAreaAtencion;
	private String numUsuariosAfectados;
	private String txtAsunto;
	private String flgAfectaIdi;
	private String txtResumen;
	private String txtDescripcion;
	private String txtError;
	private String txtFechasCorte;
	private String txtEstructuraReporte;
	private String usuarioAdicion;
	private String flgRegulatorio;
	private String datosModif;
	private String volumenes;
	
	public String getDatosModif() {
		return datosModif;
	}
	public void setDatosModif(String datosModif) {
		this.datosModif = datosModif;
	}
	public String getVolumenes() {
		return volumenes;
	}
	public void setVolumenes(String volumenes) {
		this.volumenes = volumenes;
	}
	
	public String getIdSolicitud() {
		return idSolicitud;
	}
	public void setIdSolicitud(String idSolicitud) {
		this.idSolicitud = idSolicitud;
	}
	public String getIdAreaAtencion() {
		return idAreaAtencion;
	}
	public void setIdAreaAtencion(String idAreaAtencion) {
		this.idAreaAtencion = idAreaAtencion;
	}
	public String getNumUsuariosAfectados() {
		return numUsuariosAfectados;
	}
	public void setNumUsuariosAfectados(String numUsuariosAfectados) {
		this.numUsuariosAfectados = numUsuariosAfectados;
	}
	public String getTxtAsunto() {
		return txtAsunto;
	}
	public void setTxtAsunto(String txtAsunto) {
		this.txtAsunto = txtAsunto;
	}
	public String getFlgAfectaIdi() {
		return flgAfectaIdi;
	}
	public void setFlgAfectaIdi(String flgAfectaIdi) {
		this.flgAfectaIdi = flgAfectaIdi;
	}
	public String getTxtResumen() {
		return txtResumen;
	}
	public void setTxtResumen(String txtResumen) {
		this.txtResumen = txtResumen;
	}
	public String getTxtDescripcion() {
		return txtDescripcion;
	}
	public void setTxtDescripcion(String txtDescripcion) {
		this.txtDescripcion = txtDescripcion;
	}
	public String getTxtError() {
		return txtError;
	}
	public void setTxtError(String txtError) {
		this.txtError = txtError;
	}
	public String getTxtFechasCorte() {
		return txtFechasCorte;
	}
	public void setTxtFechasCorte(String txtFechasCorte) {
		this.txtFechasCorte = txtFechasCorte;
	}
	public String getTxtEstructuraReporte() {
		return txtEstructuraReporte;
	}
	public void setTxtEstructuraReporte(String txtEstructuraReporte) {
		this.txtEstructuraReporte = txtEstructuraReporte;
	}
	public String getUsuarioAdicion() {
		return usuarioAdicion;
	}
	public void setUsuarioAdicion(String usuarioAdicion) {
		this.usuarioAdicion = usuarioAdicion;
	}
	public String getFlgRegulatorio() {
		return flgRegulatorio;
	}
	public void setFlgRegulatorio(String flgRegulatorio) {
		this.flgRegulatorio = flgRegulatorio;
	}
	

}
