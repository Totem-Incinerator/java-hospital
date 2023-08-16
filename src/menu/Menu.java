package menu;

import Dao.PatientDao;
import model.Patient;

import java.time.LocalDate;
import java.util.Scanner;

public class Menu {
    private PatientDao dao = new PatientDao();
    private Scanner mainMenuScanner, enterPatientScanner, searchScanner, deleteScanner, updateScanner;
    int option = 0;

    public Menu(){ }

    public void loadMainMenu() {
        mainMenuScanner = new Scanner(System.in);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(">  Welcome to Stars Hospital!  >");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("Select an option: ");
        System.out.println("1. \tEnter Patient");
        System.out.println("2. \tSearch Patient");
        System.out.println("3. \tUpdate Patient");
        System.out.println("4. \tDelete Patient");
        System.out.println("5. \tList patients");
        System.out.println("9. \tExit");
        printMessage("Patients registered in the system: " + dao.getSizeList());
        while(option != 9){
            option = mainMenuScanner.nextInt();
            loadMenuMethod(option);
        }
    }

    private void loadMenuMethod(int option){
        System.out.flush();
        switch (option){
            case 1:
                enterPatientMenu();
                break;
            case 2:
                searchMenu();
                break;
            case 3:
                updatePatientMenu();
                break;
            case 4:
                deletePatientMenu();
                break;
            case 5:
                printPatients();
                break;
            default:
                printMessage("Invalid option.");
                loadMainMenu();
        }
    }

    private void printPatients() {
        Scanner sc = new Scanner(System.in);
        String option = "no";
        while(!option.equals("b")){
            dao.printPatients();
            printMessage("Press b to return main menu.");
            option = sc.nextLine();
        }
        loadMainMenu();
    }

    private void enterPatientMenu() {

        enterPatientScanner = new Scanner(System.in);

        Patient newPatient = new Patient();

        System.out.println("Enter patient information: ");

        System.out.println("Enter DNI: ");
        newPatient.setDni(enterPatientScanner.nextLine());

        System.out.println("Enter name: ");
        newPatient.setName(enterPatientScanner.nextLine());

        System.out.println("Enter entry date (YYYY-MM-DD): ");
        newPatient.setEntryDate(LocalDate.parse(enterPatientScanner.nextLine()));

        System.out.println("Enter illness: ");
        newPatient.setIllness(enterPatientScanner.nextLine());

        System.out.println("Enter treatment: ");
        newPatient.setTreatmen(enterPatientScanner.nextLine());

        System.out.println("Enter discharge date (YYYY-MM-DD): ");
        newPatient.setDischargeDate(LocalDate.parse(enterPatientScanner.nextLine()));

        Patient patientValidate = dao.save(newPatient);

        System.out.println("Saving...");
        if(patientValidate != null){
            System.out.println("Patient saved succesfully!");
        }

        String option = "no";
        System.out.println("> Do you want to create another patient? (yes / no)");
        option = enterPatientScanner.nextLine();
        switch (option){
            case "yes":
                enterPatientMenu();
                break;
            case "no":
                loadMainMenu();
                break;
            default:
                printMessage("Invalid option...");
        }
    }

    private void updatePatientMenu(){

        updateScanner = new Scanner(System.in);

        printMessage("Enter patient DNI: ");

        String dni = updateScanner.nextLine();

        Patient patientUpdated = dao.search(dni);

        if(patientUpdated == null){
            printMessage("Patient not found...");
        }

        System.out.println("Enter name or 'skip' to avoid this field: ");
        var name = updateScanner.nextLine();
        if(name.equals("skip")){
            patientUpdated.setName(patientUpdated.getName());    
        }else{
            patientUpdated.setName(name);
        }


        System.out.println("Enter illness or 'skip' to avoid this field: ");
        var illness = updateScanner.nextLine();
        if(illness.equals("skip")){
            patientUpdated.setIllness(patientUpdated.getIllness());
        }else{
            patientUpdated.setIllness(illness);
        }


        System.out.println("Enter treatment or 'skip' to avoid this field: ");
        var treatment = updateScanner.nextLine();
        if(treatment.equals("skip")){
            patientUpdated.setTreatmen(patientUpdated.getTreatmen());
        }else{
            patientUpdated.setTreatmen(treatment);
        }


        System.out.println("Enter discharge date (YYYY-MM-DD) or 'skip' to avoid this field: ");
        var dischargeDate = updateScanner.nextLine();
        if(dischargeDate.equals("skip")){
            patientUpdated.setDischargeDate(LocalDate.parse(patientUpdated.getDischargeDate().toString()));
        }else{
            patientUpdated.setDischargeDate(LocalDate.parse(dischargeDate));
        }

        Patient patientValidate = dao.update(dni, patientUpdated);

        System.out.println("Updating...");
        if(patientValidate != null){
            System.out.println("Patient updated succesfully!");
        }

        String option = "no";
        printMessage("Do you want to update another patient? (yes / no)");
        option = updateScanner.nextLine();
        switch (option){
            case "yes":
                updatePatientMenu();
                break;
            case "no":
                loadMainMenu();
                break;
            default:
                printMessage("Invalid option...");
        }
    }

    public void searchMenu() {
        searchScanner = new Scanner(System.in);
        printMessage("Ingresar dni: ");
        Patient patientFound = dao.search(searchScanner.nextLine());
        if(patientFound != null){
            printMessage("Patient found: ");
            printMessage(patientFound.toString());

            String optionMenu = searchScanner.nextLine();
            while(!optionMenu.equals("1")){
                printMessage("Press 1 to return to the main menu.");
                optionMenu = searchScanner.nextLine();
            }
            loadMainMenu();
        }else{
            printMessage("Patient not found...");
            loadMainMenu();
        }
    }

    private void deletePatientMenu() {
        deleteScanner = new Scanner(System.in);
        printMessage("Enter the patient's dni ");
        Patient patientToDelete = dao.search(deleteScanner.nextLine());
        if(patientToDelete != null){
            printMessage("Patient delete: " + dao.delete(patientToDelete.getDni()));
            printMessage("Do you want to delete another patient? (yes / no)");
            String option = deleteScanner.nextLine();
            if(option.equals("yes")){
                deletePatientMenu();
            }else{
                loadMainMenu();
            }
        }else{
            printMessage("Patient not found");
            loadMainMenu();
        }
    }

    private static void printMessage(String message){
        System.out.println("> " + message);
    }

    public void createPatients(){
        Patient patient1 = new Patient("1061808989", "Jhonathan", LocalDate.now(), "Desconocida", "Pastillas", LocalDate.parse("2023-08-23"));
        Patient patient2 = new Patient("1061654332", "Pepa Pig", LocalDate.now(), "Terminal", "Acetaminofen", LocalDate.parse("2023-12-07"));
        Patient patient3 = new Patient("1061767555", "Julio Jaramillo", LocalDate.now(), "Asma", "Tratamiento contra el asma", LocalDate.now());
        Patient patient4 = new Patient("1234", "Mario Bros", LocalDate.now(), "Sobre dosis de zetas", "Suero para la desintoxicación", LocalDate.now());
        dao.save(patient1);
        dao.save(patient2);
        dao.save(patient3);
        dao.save(patient4);
    }
}