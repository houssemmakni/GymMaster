/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
 package services;

import entities.Article;
import utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
//import java.sql.PreparedStatement;
import java.util.stream.Collectors;

import java.util.Comparator;





/**
 *
 * @author Houssem
 */
 public class ServiceArticle implements IService<Article>{
    Connection cnx = DataSource.getInstance().getCnx();



    @Override
    public void ajouter(Article a) {
        try {
            String req = "INSERT INTO article (titre_article, image, description_article, prix) VALUES ('" + a.gettitre_article() + "','" + a.getimage() + "','" + a.getdescription_article() + "','" + a.getprix() +"')";
            PreparedStatement st = cnx.prepareStatement(req);
            st.executeUpdate();
            System.out.println("Article ajouté !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @Override
    public void supprimer(Article a) {
       
           try {
            PreparedStatement statement = cnx.prepareStatement(
    "DELETE FROM article WHERE titre_article = ?"
);
   statement.setString(1, a.gettitre_article());
    statement.executeUpdate();
            
          
            System.out.println("Article supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

@Override
    public void modifier(Article a) {
        try {
            String req = "UPDATE article SET image=?, description_article=? ,prix=? WHERE titre_article=?" ;
           PreparedStatement pst = cnx.prepareStatement(req);
           
           
            pst.setString(1, a.getimage());
            pst.setString(2, a.getdescription_article());
            pst.setString(3,a.getprix());
             pst.setString(4, a.gettitre_article());
          
            pst.executeUpdate();
            System.out.println("Article modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Article> afficher() {
        List<Article> list = new ArrayList<>();
        
        try {
            String req = "SELECT titre_article,image , description_article, prix FROM article";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()) {
                list.add(new Article( rs.getString("titre_article"),rs.getString("image"),rs.getString("description_article"),rs.getString("prix")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }

    /**
     *
     * @param index
     */
    @Override
    public void rechercher(String index){
List<Article> result = afficher().stream().filter(line -> index.equals(line.gettitre_article())).collect(Collectors.toList());
                    System.out.println("----------");
                    result.forEach(System.out::println);

}
   public void TrierParId (){
 ServiceArticle sa = new ServiceArticle();
List<Article> TrierParId = sa.afficher().stream().sorted(Comparator.comparing(Article::getidArticle)).collect(Collectors.toList());
                            TrierParId.forEach(System.out::println);
}




}
 
