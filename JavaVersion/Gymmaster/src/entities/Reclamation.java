package entities;

import java.time.LocalDate;
import java.util.Date;

public class Reclamation {
    private int IdReclamation;
    private String Description;
    private Date DateReclamation;
    private Date DateReponse;
    private String Reponse;
    private String UserEmail;
    private String Subject;
    private String Status;
    
    public Reclamation(int idReclamation, String description, Date dateReclamation) {
        IdReclamation = idReclamation;
        Description = description;
        DateReclamation = dateReclamation;
    }

    public Reclamation(int idReclamation, String description, Date dateReclamation, Date dateReponse, String reponse,
        Date dateModification, String modification, int idUser) {
        IdReclamation = idReclamation;
        Description = description;
        DateReclamation = dateReclamation;
        DateReponse = dateReponse;
        reponse = reponse;
        UserEmail = UserEmail;
        Subject = Subject;
        Status = Status;
    }

    public Reclamation() {
    }

    public Reclamation(int idReclamation, String description, LocalDate dateReclamation) {
    }

    public int getIdReclamation() {
        return this.IdReclamation;
    }

    public void setIdReclamation(int idReclamation) {
        IdReclamation = idReclamation;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Date getDateReclamation() {
        return  DateReclamation;
    }

    public void setDateReclamation(Date dateReclamation) {
        DateReclamation = dateReclamation;
    }

    public Date getDateReponse() {
        return DateReponse;
    }

    public void setDateReponse(Date dateReponse) {
        DateReponse = dateReponse;
    }

    public String getReponse() {
        return Reponse;
    }

    public void setReponse(String reponse) {
        Reponse = reponse;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }



}   
    
    