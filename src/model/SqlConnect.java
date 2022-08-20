package model;

import java.sql.*;

public class SqlConnect {
    private  Statement statement ;
     SqlConnect() {
     }

     public Connection getConnection()  {
        String databaseName = "carehomedb";
        String databaseUser = "root";
        String databasePassword = "4205286225";
        String url = "jdbc:mysql://localhost:3306/"+databaseName;

        try {
            Connection databaseLink = DriverManager.getConnection(url,databaseUser,databasePassword);
            return databaseLink;
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
            return null;
        }

    }

    //If the ward table empty , initlize Ward information to the system
    public void initlizeWard(Ward[] ward) throws SQLException{
         for(Room room : ward[0].getRoomMap().values()){
            for(Bed bed : room.getBedMap().values()){
                statement.executeUpdate("insert into ward value ('"+bed.getbID()+"','"+ bed.getBedEnum() +"', '"+room.getrNum()+"', 'w1')");
            }

        }
        for(Room room : ward[1].getRoomMap().values()){
            for(Bed bed : room.getBedMap().values()){
                statement.executeUpdate("insert into ward value ('"+bed.getbID()+"','"+ bed.getBedEnum() +"', '"+room.getrNum()+"', 'w2')");
            }
        }

    }

    //Method to get RestltSet from tables
    public ResultSet readDataBase(String tableName) throws SQLException {
         statement = getConnection().createStatement();
         ResultSet r= statement.executeQuery("select*from "+tableName);
         return r;
    }

    //Method add a patient information to the sql
    public void addPatientToSql(Patient p)  {
        try {
            statement = getConnection().createStatement();
            statement.executeUpdate("insert into patient value ('"+p.getId()+"','"+ p.getName() +"','"+p.getGender()+"','"+p.getNeedIsolation()+"','"+p.getPrescriptions()+"' ,'"+p.getBedId()+"')");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //Method modify patient information to the table
    public void modifyPatientBed(Patient p, int bedId, String bedEnum){
         try {
            statement = getConnection().createStatement();
            statement.executeUpdate("update patient set bedid ='"+bedId+"' where id = "+p.getId()+";");
            statement.executeUpdate("update ward set bedenum ='"+bedEnum+"'where bedid = "+bedId+";");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //Method
    public void addStaff(Staff s){
        try {
            statement = getConnection().createStatement();
            statement.executeUpdate("insert into staff value('"+s.getId()+"','"+s.getName()+"','"+s.getPassword()+"','"+s.getPosition()+"' )");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void modifyStaff(Staff s, String newName, String newPassword){
         try {
            statement = getConnection().createStatement();
            statement.executeUpdate("update staff set name = '"+newName+"' where id = "+s.getId() +";");
            statement.executeUpdate("update staff set password = '"+newPassword+"' where id = "+s.getId() +";" );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    //use table name , row name and a int information to delete from database
    public void useIntDeleteInformation(String tableName, String rowName, int newId){
        try {
            statement = getConnection().createStatement();
            statement.executeUpdate("delete from "+tableName+" where "+rowName+" = '"+newId+"';");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //Method add new doctor shift to database
    public void insertDoctorShifts(DoctorShift ds){
        try {
            statement = getConnection().createStatement();
            statement.executeUpdate("insert into doctorshift value ('"+ds.getShiftId()+"','"+ds.getDoctorId()+"', '"+ds.getWeek()+"', '"+ds.getTime()+"')");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //Method add a new nurse shift to database
    public void insertNurseShifts(NurseShift ns){
        try {
            statement = getConnection().createStatement();
            statement.executeUpdate("insert into nurseshift value ('"+ns.getShiftId()+"','"+ns.getNurseId()+"','"+ns.getWeek()+"','"+ns.getTime()+"')");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //Method change patient has the prescription or not
    public void updatePatientPrescription(int patientID , String hasOrNull){
        try {
            statement = getConnection().createStatement();
            statement.executeUpdate("update patient set prescriptions = '"+hasOrNull+"'where id  = "+patientID+";");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //Method add a new Medicine to the table
    public void insertMedicine(Prescription p, Medicine m){
        try {
            statement = getConnection().createStatement();
            statement.executeUpdate("insert into medicine_prescription value ('"+m.getMedName()+"','"+p.getPatientID()+"','"+m.getTime()+"','"+m.getAmount()+"','"+m.getUnit()+"','"+p.getDocID()+"','"+p.getReason()+"')");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //Method delete a medicine to the table
    public void deleteMedicine(Medicine m, int patientID){
        try {
            statement = getConnection().createStatement();
            statement.executeUpdate("delete from medicine_prescription where patientid = '"+patientID+"' and medName = '"+m.getMedName()+"'; " );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    //Method record a log
    public void recordAnLog(LogRecord log){

        try {
            statement = getConnection().createStatement();
            statement.executeUpdate("insert into log value ('"+log.getPatientID()+"','"+log.getStaffID()+"','"+log.getDetails()+"','"+log.getTime().toString()+"')");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



    }



}

