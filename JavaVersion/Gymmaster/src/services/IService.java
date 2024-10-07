/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import java.util.List;

/**
 *
 * @author Houssem
 */
public interface IService <A>{
    
    public void ajouter(A a);
    public void supprimer(A a);
    public void modifier(A a);
    public List<A> afficher();
    //A rechercheparid(Integer id_article);
    public void rechercher(String titre_article);

}

