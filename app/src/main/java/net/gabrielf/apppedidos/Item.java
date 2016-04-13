package net.gabrielf.apppedidos;

/**
 * Created by pangui-dev-2015 on 05-04-2016.
 */
public class Item {
    private int _id;
    private String nombreprod;
    private String detalleprod;
    private int precioprod;

    public Item(){
        //Constructor Vacio
    }

    public Item(String nombreprod, String detalleprod, int precioprod) {
        this.nombreprod = nombreprod;
        this.detalleprod = detalleprod;
        this.precioprod = precioprod;
    }

    public String getNombreprod() {
        return nombreprod;
    }

    public void setNombreprod(String nombreprod) {
        this.nombreprod = nombreprod;
    }

    public int getPrecioprod() {
        return precioprod;
    }

    public void setPrecioprod(int precioprod) {
        this.precioprod = precioprod;
    }

    public String getDetalleprod() {
        return detalleprod;
    }

    public void setDetalleprod(String detalleprod) {
        this.detalleprod = detalleprod;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
