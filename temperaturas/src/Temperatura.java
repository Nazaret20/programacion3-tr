import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Temperatura implements Serializable {
    private LocalDate date;
    private double tempMax, tempMin;
    
    public Temperatura(LocalDate date, double tempMax, double tempMin) {
        this.date = date;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
    }
    

    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return formato + ": " + tempMax + "ºC | " + tempMin + "ºC";
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


    public LocalDate getDate() {
        return date;
    }


    public void setDate(LocalDate date) {
        this.date = date;
    }

    
}
