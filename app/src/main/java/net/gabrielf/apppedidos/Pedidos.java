package net.gabrielf.apppedidos;

/**
 * Created by pangui-dev-2015 on 06-05-2016.
 */
public class Pedidos {

    private int id_empleado;
    private String fecha;
    private int total;

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
