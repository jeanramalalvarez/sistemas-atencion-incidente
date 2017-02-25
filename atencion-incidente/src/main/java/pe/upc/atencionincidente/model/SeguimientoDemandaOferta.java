package pe.upc.atencionincidente.model;

public class SeguimientoDemandaOferta {
	
	private String fecha;
	
	private Integer cartera;
	
	private Integer capacidad;
	
	private Integer oferta;
	
	private Integer demanda;

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Integer getCartera() {
		return cartera;
	}
	
	public void setCartera(Integer cartera) {
		this.cartera = cartera;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	public Integer getOferta() {
		return oferta;
	}

	public void setOferta(Integer oferta) {
		this.oferta = oferta;
	}

	public Integer getDemanda() {
		return demanda;
	}

	public void setDemanda(Integer demanda) {
		this.demanda = demanda;
	}

}
