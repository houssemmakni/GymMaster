package entities;

public class Commande {
    private int id;
    private int id_article; // Clé étrangère faisant référence à l'ID de l'article
    private String nombre;
    private String addresse_commande;
    private String nom_prenom_commande;
    private String tel_commande;

    public Commande(int id, int id_article, String nombre, String addresse_commande, String nom_prenom_commande, String tel_commande) {
        this.id = id;
        this.id_article = id_article;
        this.nombre = nombre;
        this.addresse_commande = addresse_commande;
        this.nom_prenom_commande = nom_prenom_commande;
        this.tel_commande = tel_commande;
    }

    public Commande(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String getAddresse_commande() {
        return addresse_commande;
    }

    public void setAddresse_commande(String addresse_commande) {
        this.addresse_commande = addresse_commande;
    }
    
    public String getNom_prenom_commande() {
        return nom_prenom_commande;
    }

    public void setNom_prenom_commande(String nom_prenom_commande) {
        this.nom_prenom_commande = nom_prenom_commande;
    }
    
      public String getTel_commande() {
        return tel_commande;
    }

    public void setTel_commande(String tel_commande) {
        this.tel_commande = tel_commande;
    }
    
    public int getId() {
        return id;
    }

    public void setIdCommande(int id_commande) {
        this.id = id_commande;
    }

    public int getIdArticle() {
        return id_article;
    }

    public void setIdArticle(int id_article) {
        this.id_article = id_article;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "id=" + id +
                ", id_article=" + id_article +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
