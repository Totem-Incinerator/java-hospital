package Dao;

import model.Patient;

import java.util.ArrayList;
import java.util.List;

public class PatientDao implements Dao<Patient>{
    private List<Patient> patients;
    private Patient patientTest;

    public PatientDao(){
        this.patients = new ArrayList<Patient>();
    }
    public int getSizeList(){
        return patients.size();
    }

    public void printPatients(){
        this.patients.forEach((p) -> {
            System.out.println(p);
        });
    }

    @Override
    public Patient save(Patient p) {
        this.patients.add(p);
        return p;
    }

    @Override
    public Patient search(String dni) {
        Patient ps = null;
        for(Patient p: this.patients)
            if (p.getDni().equals(dni)) ps = p;
        return ps;
    }

    private Patient searchByInt(int index) {
        return patients.get(index);
    }

    @Override
    public Patient update(String dni, Patient patient) {
        Patient patientUpdated = null;
        for(Patient p: this.patients){
            if(p.getDni().equals(dni)){
                p.setName( (patient.getName() != null) ? patient.getName() : p.getName() );
                p.setIllness( (patient.getIllness() != null) ? patient.getIllness() : p.getIllness() );
                p.setEntryDate( (patient.getEntryDate() != null) ? patient.getEntryDate() : p.getEntryDate() );
                p.setDischargeDate( (patient.getDischargeDate() != null) ? patient.getDischargeDate() : p.getDischargeDate() );
                p.setTreatmen( (patient.getTreatmen() != null) ? patient.getTreatmen() : p.getTreatmen() );
                patientUpdated = p;
            }
        }
        return patientUpdated;
    }

    @Override
    public boolean delete(String dni) {
        Patient patienDelete = null;
        for(Patient p: this.patients){
            if(p.getDni().equals(dni)){
                patienDelete = p;
            }
        }
        return this.patients.remove(patienDelete);
    }
}
