package GCM;
//import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;

public class Grendezvous{
    private int id;
    private Gpatient patient;
    private LocalDate date;
    private LocalTime time;

    public Grendezvous(int id, Gpatient patient, LocalDate date,LocalTime time) {
        this.id = IDGenerator.generateId();
        this.patient = patient;
        this.date = date;
        this.time = time;
    }

    public LocalDate getdate() {
        return date;
    }

    public LocalTime gettime() {
        return time;
    }

    public int getId() {
        return id;
    }

    public Gpatient getpatient() {
        return patient;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPatient(Gpatient patient) {
        this.patient = patient;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Grendezvous [id=" + id + ", patient=" + patient + ", date=" + date + ", time=" + time + "]";
    }
    
}
