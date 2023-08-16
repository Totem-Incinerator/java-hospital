package model;

import java.time.LocalDate;
import java.util.Objects;

public class Patient {

    private static int nextId = 1;
    private int id;
    private String dni;
    private String name;
    private LocalDate entryDate;
    private String illness;
    private String treatmen;
    private LocalDate dischargeDate;

    public Patient(){}

    public Patient(String dni, String name, LocalDate entryDate, String illness, String treatmen, LocalDate dischargeDate) {
        this.dni = dni;
        this.name = name;
        this.entryDate = entryDate;
        this.illness = illness;
        this.treatmen = treatmen;
        this.dischargeDate = dischargeDate;
        this.id = nextId;
        nextId++;
    }

    public int getId() {
        return id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public String getIllness() {
        return illness;
    }

    public void setIllness(String illness) {
        this.illness = illness;
    }

    public String getTreatmen() {
        return treatmen;
    }

    public void setTreatmen(String treatmen) {
        this.treatmen = treatmen;
    }

    public LocalDate getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(LocalDate dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    @Override
    public String toString() {
        return "Patient:\n" +
                "DNI:\t" + this.dni + "\n" +
                "Name:\t" + this.name + "\n" +
                "Entry Date:\t" + this.entryDate + "\n" +
                "Illness:\t" + this.illness + "\n" +
                "Treatment:\t" + this.treatmen + "\n" +
                "Discharge Date:\t" + this.dischargeDate + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(dni, patient.dni) && Objects.equals(name, patient.name) && Objects.equals(entryDate, patient.entryDate) && Objects.equals(illness, patient.illness) && Objects.equals(treatmen, patient.treatmen) && Objects.equals(dischargeDate, patient.dischargeDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni, name, entryDate, illness, treatmen, dischargeDate);
    }
}
