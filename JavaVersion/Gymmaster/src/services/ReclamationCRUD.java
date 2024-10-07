package services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Reclamation;
import utils.MyConnection;

public class ReclamationCRUD {
    Connection cnx;
    public ReclamationCRUD() {
        cnx = MyConnection.getInstance().getCnx();
    }
        public boolean ajouterReclamation(Reclamation r) {
            try {
        String req = "INSERT INTO reclamation (description, DateReclamation, status, subject, useremail) VALUES (?, ?, 'encours',?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setDate(2, (Date) r.getDateReclamation());
            ps.setString(1, r.getDescription());
            ps.setString(3, r.getSubject());
            ps.setString(4, r.getUserEmail());
            ps.executeUpdate();
            System.out.println("Reclamation ajoutée !");
            return true;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }
    public void repondreReclamation(int i , String r , Date t) {
        try {
            String req = "UPDATE reclamation SET Reponse = ?, datereponse = ? WHERE idReclamation=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(3,i);
            ps.setString(1, r);
            ps.setDate(2,t);
            ps.executeUpdate();
            System.out.println("Réponse ajoutée à la réclamation " );
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public boolean SupprimerrReclamation (int id) {

        String req = "DELETE FROM reclamation WHERE IdReclamation=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public List<Reclamation> AfficherReclamations() {
        List<Reclamation> observableListReclamationsreclamations = new ArrayList<>();
        try {
            String query = "SELECT * FROM reclamation";
            Statement statement = cnx.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Reclamation reclamation = new Reclamation(0, query, null, null, query, null, query, 0);
                reclamation.setIdReclamation(resultSet.getInt("IdReclamation"));
                reclamation.setDescription(resultSet.getString("Description"));
                reclamation.setDateReclamation(resultSet.getDate("DateReclamation"));
                reclamation.setDateReponse(resultSet.getDate("DateReponse"));
                reclamation.setReponse(resultSet.getString("Reponse"));
                reclamation.setUserEmail(resultSet.getString("UserEmail"));
                reclamation.setStatus(resultSet.getString("status"));
                reclamation.setSubject(resultSet.getString("subject"));
                observableListReclamationsreclamations.add(reclamation);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return observableListReclamationsreclamations;
    }
    public void SetCloturee(int id){
         try {
            String req = "UPDATE reclamation SET status = ? WHERE idReclamation=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(2,id);
            ps.setString(1, "cloturee");
            ps.executeUpdate();
            System.out.println("Réclamation cloturée" );
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    

}

