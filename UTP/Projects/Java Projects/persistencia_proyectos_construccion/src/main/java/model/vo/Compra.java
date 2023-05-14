package model.vo;

public class Compra{

    private Integer idCompra;
    private Integer cantidad;
    private String proveedor;
    private String pagado;
    private String fecha;
    private Integer idProyecto;
    private Integer idMaterialConstruccion;

    public Compra(){

    }

    public Compra(Integer idCompra, Integer cantidad, String proveedor, String pagado, String fecha,
            Integer idProyecto, Integer idMaterialConstruccion) {
        this.idCompra = idCompra;
        this.cantidad = cantidad;
        this.proveedor = proveedor;
        this.pagado = pagado;
        this.fecha = fecha;
        this.idProyecto = idProyecto;
        this.idMaterialConstruccion = idMaterialConstruccion;
    }

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getPagado() {
        return pagado;
    }

    public void setPagado(String pagado) {
        this.pagado = pagado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Integer getIdMaterialConstruccion() {
        return idMaterialConstruccion;
    }

    public void setIdMaterialConstruccion(Integer idMaterialConstruccion) {
        this.idMaterialConstruccion = idMaterialConstruccion;
    }

}
