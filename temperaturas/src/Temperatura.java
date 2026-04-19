public class Temperatura {
    private int dia, mes, anio;
    private double tempMax, tempMin;
    
    public Temperatura(int dia, int mes, int anio, double tempMax, double tempMin) {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
    }
    

    @Override
    public String toString() {
        return dia + "/" + mes + "/" + anio + ": " + tempMax + "ºC | " + tempMin + "ºC";
    }


    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }

    public double getTempMin() {
        return tempMin;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    
}
