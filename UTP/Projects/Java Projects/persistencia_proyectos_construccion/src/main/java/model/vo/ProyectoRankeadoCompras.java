package model.vo;

public class ProyectoRankeadoCompras {
    
    private Integer idProyecto;
    private String clasificacion;
    private Integer gastoCompras;
    private String serial;
    
    public ProyectoRankeadoCompras() {
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public Integer getGastoCompras() {
        return gastoCompras;
    }

    public void setGastoCompras(Integer gastoCompras) {
        this.gastoCompras = gastoCompras;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }
    
}
