package net.gabrielf.apppedidos;

/**
 * Created by pangui-dev-2015 on 07-05-2016.
 */
public class PedidosDeta {

    private int _id_pedido;
    private int _id_producto;
    private int cantidad;
    private int precioxunidad;

    public int get_id_pedido() {
        return _id_pedido;
    }

    public void set_id_pedido(int _id_pedido) {
        this._id_pedido = _id_pedido;
    }

    public int get_id_producto() {
        return _id_producto;
    }

    public void set_id_producto(int _id_producto) {
        this._id_producto = _id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecioxunidad() {
        return precioxunidad;
    }

    public void setPrecioxunidad(int precioxunidad) {
        this.precioxunidad = precioxunidad;
    }
}
