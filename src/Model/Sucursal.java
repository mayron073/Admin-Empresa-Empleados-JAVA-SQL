package Model;


public class Sucursal {
    private int idSucursal;
    private  String sucursalName;

    public Sucursal() {
        idSucursal = 0;
        sucursalName = "";
    }

    public Sucursal(int idSucursal, String sucursalName) {
        this.idSucursal = idSucursal;
        this.sucursalName = sucursalName;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getSucursalName() {
        return sucursalName;
    }

    public void setSucursalName(String sucursalName) {
        this.sucursalName = sucursalName;
    }

    @Override
    public String toString() {
        return getSucursalName();
    }
    
    
    
    
}

