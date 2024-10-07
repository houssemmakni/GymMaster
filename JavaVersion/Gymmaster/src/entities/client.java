package entities;

public class client {
    private int IdClient;
    private String Nom;
    private String Prenom;
    private String Email;
    private long numero;
    public client(int idClient, String nom, String prenom, String email, long numero) {
        IdClient = idClient;
        Nom = nom;
        Prenom = prenom;
        Email = email;
        this.numero = numero;
    }
    public int getIdClient() {
        return IdClient;
    }
    public void setIdClient(int idClient) {
        IdClient = idClient;
    }
    public String getNom() {
        return Nom;
    }
    public void setNom(String nom) {
        Nom = nom;
    }
    public String getPrenom() {
        return Prenom;
    }
    public void setPrenom(String prenom) {
        Prenom = prenom;
    }
    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        Email = email;
    }
    public long getNumero() {
        return numero;
    }
    public void setNumero(long numero) {
        this.numero = numero;
    }
}


