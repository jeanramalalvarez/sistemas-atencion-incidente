package pe.upc.atencionincidente.form;

public class DetalleIncidenteForm {
	
	private String asunto;
	private String afectaIdi;
	private String descripcion;
	private String datosModif;
	private String error;
	
	private String regulatorio;
	private String resumen;
	private String fechasCorte;
	private String descripcion2;
	private String estructuraReporte;
	private String volumenes;

	private String idSolicitud;
	private String usAfectados;
	
	public String getDatosModif() {
		return datosModif;
	}
	public void setDatosModif(String datosModif) {
		this.datosModif = datosModif;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getAfectaIdi() {
		return afectaIdi;
	}
	public void setAfectaIdi(String afectaIdi) {
		this.afectaIdi = afectaIdi;
	}
	
	public String getRegulatorio() {
		return regulatorio;
	}
	public void setRegulatorio(String regulatorio) {
		this.regulatorio = regulatorio;
	}
	public String getResumen() {
		return resumen;
	}
	public void setResumen(String resumen) {
		this.resumen = resumen;
	}
	public String getVolumenes() {
		return volumenes;
	}
	public void setVolumenes(String volumenes) {
		this.volumenes = volumenes;
	}
	public String getFechasCorte() {
		return fechasCorte;
	}
	public void setFechasCorte(String fechasCorte) {
		this.fechasCorte = fechasCorte;
	}
	public String getEstructuraReporte() {
		return estructuraReporte;
	}
	public void setEstructuraReporte(String estructuraReporte) {
		this.estructuraReporte = estructuraReporte;
	}
	
	public String getDescripcion2() {
		return descripcion2;
	}
	public void setDescripcion2(String descripcion2) {
		this.descripcion2 = descripcion2;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getUsAfectados() {
		return usAfectados;
	}
	public void setUsAfectados(String usAfectados) {
		this.usAfectados = usAfectados;
	}
	public String getIdSolicitud() {
		return idSolicitud;
	}
	public void setIdSolicitud(String idSolicitud) {
		this.idSolicitud = idSolicitud;
	}
	


}
