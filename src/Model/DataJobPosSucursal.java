package Model;


public class DataJobPosSucursal {
    private int idSucursal;
    private String nombreSucursal;
    private int idPuestoT;
    private String nombrePuestoT;
    private float salary;

    public DataJobPosSucursal() {
        this.idSucursal = 0;
        this.nombreSucursal = "";
        this.idPuestoT = 0;
        this.nombrePuestoT = "";
        this.salary = 0.0f;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }

    public int getIdPuestoT() {
        return idPuestoT;
    }

    public void setIdPuestoT(int idPuestoT) {
        this.idPuestoT = idPuestoT;
    }

    public String getNombrePuestoT() {
        return nombrePuestoT;
    }

    public void setNombrePuestoT(String nombrePuestoT) {
        this.nombrePuestoT = nombrePuestoT;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }
    
    
    
}
