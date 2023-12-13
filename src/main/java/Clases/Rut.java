package Clases;

public class Rut {

    // ==================== ATRIBUTOS ====================
    private int      rut     , digito    , multiplo  , acumulador;

    private String   rutDigito;



    // ==================== CONSTRUCTORES ====================
    public Rut(int rut, int digito, int multiplo, int acumulador, String rutDigito) {
        this.rut            = rut;
        this.digito         = digito;
        this.multiplo       = multiplo;
        this.acumulador     = acumulador;
        this.rutDigito      = rutDigito;
    }

    public Rut() {
    }



    // ==================== VALIDAR RUT ====================
    public void validarRut() {
        int contador    = 2;
        acumulador      = 0;
        int tempRut     = rut;

        while (tempRut != 0) {
            int digito  = tempRut   % 10;
            multiplo    = digito    * contador;
            acumulador += multiplo;
            tempRut     = tempRut   / 10;
            contador    = contador  + 1;

            if (contador == 8) {
                contador = 2;
            }
        }

        // Cuando el RUT tenga una "K"
        if (rutDigito.equalsIgnoreCase("k")) {
            rutDigito = "10";
        }
    }



    // ==================== GETTER ====================
    public int getRut() {
        return rut;
    }

    public int getDigito() {
        return digito;
    }

    public int getMultiplo() {
        return multiplo;
    }

    public int getAcumulador() {
        return acumulador;
    }

    public String getRutDigito() {
        return rutDigito;
    }


    // ==================== SETTER ====================
    public void setRut(int rut) {
        this.rut = rut;
    }

    public void setDigito(int digito) {
        this.digito = digito;
    }

    public void setMultiplo(int multiplo) {
        this.multiplo = multiplo;
    }

    public void setAcumulador(int acumulador) {
        this.acumulador = acumulador;
    }

    public void setRutDigito(String rutDigito) {
        this.rutDigito = rutDigito;
    }
}
