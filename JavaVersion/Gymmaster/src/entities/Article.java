/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;


import java.io.File;

/**
 *
 * @author Houssem
 */
public class Article {
    private int id;
    private String titre_article;
    private String image;
    private String description_article;
    private String prix;
    private File imageFile;
 
    
    public Article(int id, String titre_article, String image, String description_article, String prix){
        this.id=id;
        this.titre_article=titre_article;
        this.image=image;
        this.description_article=description_article;
        this.prix=prix;
 
    }
      public Article(String titre_article){
       
        this.titre_article=titre_article;
       
 
    }
    public Article(String titre_article, String image, String description_article, String prix){
        this.titre_article=titre_article;
        this.image=image;
        this.description_article=description_article;
        this.prix=prix;
      
    } 
    public Article(int id, String titre_article, String image, String description_article, String prix, File imageFile) {
        this.id = id;
        this.titre_article = titre_article;
        this.image = image;
        this.description_article = description_article;
        this.prix = prix;
        this.imageFile = imageFile;
    }
       public File getImageFile() {
        return imageFile;
    }

    public void setImageFile(File imageFile) {
        this.imageFile = imageFile;
    }
    public int getidArticle() {
        return id;
    }

    public void setidArticle(int id) {
        this.id = id;
    }
    public String gettitre_article() {
        return titre_article;
    }

    public void settitre_article(String titre_article) {
        this.titre_article = titre_article;
    }
    public String getimage() {
        return image;
    }

    public void setimage(String image) {
        this.image = image;
    }
    public String getdescription_article() {
        return description_article;
    }

    public void setdescription_article(String description_article) {
        this.description_article = description_article;
    }
    public String getprix() {
        return prix;
    }

    public void setprix(String prix) {
        this.prix = prix;
    }

 
     @Override
    public String toString() {
        return "Article{" + "id=" + id + ", titre_article=" + titre_article + ", image=" + image + ", description_article=" + description_article + ", prix=" + prix + '}';
    }
}   
