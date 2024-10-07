/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import entities.Commande;
//import utils.DataSource;
import utils.MyConnection;

/**
 *
 * @author H
 */
public class ServiceCommande {
    Connection cnx;
    public ServiceCommande() {
        cnx = MyConnection.getInstance().getCnx();
    }
 
    public void supprimer(Commande c) {
       
           try {
            PreparedStatement statement = cnx.prepareStatement(
    "DELETE FROM Commande WHERE idarticle = ?"
);
   statement.setString(1, c.getNom_prenom_commande());
    statement.executeUpdate();
            
          
            System.out.println("Article supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    }


