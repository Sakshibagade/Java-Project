package HospitalManagementSystem;

import java.sql.*;
import java.util.Scanner;

public class HospitalManagementSystem {
    private static final String url="jdbc:mysql://127.0.0.1:3306/hospital";
    private static final String username="root";
    private static final String password="Sakshi@09";


    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Scanner scanner=new Scanner(System.in);
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Patient patient=new Patient(connection,scanner);
            Doctor doctor=new Doctor(connection);

            while(true){
                System.out.println("HOSPITAL MANAGEMENT SYSTEM");
                System.out.println("1. Add Patient");
                System.out.println("2. View Patient");
                System.out.println("3. View Doctor");
                System.out.println("4. Book Appointment");
                System.out.println("5. Exit");

                System.out.println("Enter your choice: ");
                int choice=scanner.nextInt();
                switch (choice){

                    case 1:
                        //Add Patient
                        patient.addPatient();
                        System.out.println();
                        break;
                    case 2:
                        //View Patient
                        patient.viewPatient();
                        System.out.println();
                        break;
                    case 3:
                        //View Doctors
                        doctor.viewDoctors();
                        System.out.println();
                        break;
                    case 4:
                        //Book Appointment
                        bookAppointment(patient,doctor,connection,scanner);
                        System.out.println();
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Invalid choice");



                }


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void bookAppointment(Patient patient,Doctor doctor,Connection connection,Scanner scanner){
        System.out.println("Enter Patient ID");
        int patientId=scanner.nextInt();
        System.out.println("Enter Doctor ID");
        int doctorId=scanner.nextInt();
        System.out.println("Enter appointment date (YYYY-MM-DD): ");
        String appointmentDate=scanner.next();

        if(patient.getPatientBYId(patientId) && doctor.getDoctorBYId(doctorId) ){
            if(checkDoctorAvailability(doctorId,appointmentDate,connection)){
                String appointmentQuery="insert into appointments (patient_id,doctors_id,appointment_date) Values(?,?,?)";
                try{
                    PreparedStatement preparedStatement = connection.prepareStatement(appointmentQuery);
                    preparedStatement.setInt(1,patientId);
                    preparedStatement.setInt(2,doctorId);
                    preparedStatement.setString(3,appointmentDate);
                    int rowAffected=preparedStatement.executeUpdate();
                    if(rowAffected>0){
                        System.out.println("Appointment booked successfully");
                    }else{
                        System.out.println("Appointment not booked");
                    }
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }else{
                System.out.println("Doctor is not available");
            }
        }else {
            System.out.println("Either doctor or patient doesn't exist!!!");
        }

    }

    private static boolean checkDoctorAvailability(int doctorId, String appointmentDate, Connection connection) {
        String query = "SELECT COUNT(*) FROM appointments WHERE doctors_id=? AND appointment_date=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, doctorId);
            preparedStatement.setString(2, appointmentDate);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count == 0; // doctor available only if no rows found
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
