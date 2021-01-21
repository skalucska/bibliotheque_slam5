package fr.pgah.java.unbrco.model.bibliotheque;

import java.util.ArrayList;
import java.util.List;

import fr.pgah.java.unbrco.model.livre.GenreLivre;
import fr.pgah.java.unbrco.model.livre.Livre;

public class Bibliotheque {

  private Bibliothecaire gerant;
  private String nom;
  private List<Bibliotheque> branches = new ArrayList<>();
  private List<Livre> livresReference = new ArrayList<>();
  private List<Livre> livresRomans = new ArrayList<>();
  private List<Livre> livresBiographie = new ArrayList<>();
  private List<Livre> livresManuels = new ArrayList<>();
  private List<Livre> livreCuisine = new ArrayList<>();

  public Bibliotheque(String nom, Bibliothecaire gerant) {
    this.nom = nom;
    this.gerant = gerant;
  }

  public String getNom() {
    return nom;
  }

  public Bibliothecaire getGerant() {
    return gerant;
  }

  // PREREQUIS : livre != null
  // MODIFIE : this
  // EFFETS : stocke livre dans la collection appropriée de cet objet
  public void enregistrerLivre(Livre livre) {
    if (livre != null) {
      switch (livre.getGenre()) {
        case REFERENCE:
          livresReference.add(livre);
          break;

        case ROMAN:
          livresRomans.add(livre);
          break;

        case BIBLIOGRAPHIE:
          livresBiographie.add(livre);
          break;

        case CUISINE:
          livreCuisine.add(livre);
          break;

        case MANUEL:
          livresManuels.add(livre);
          break;

        default:
          System.out.println("livre n'appartient a aucun genre");
      }
    }
  }

  // PREREQUIS : livre != null
  // RENVOIE : vrai si livre est dans le catalogue de cette bibliothèque
  // (qu'il soit actuellement emprunté ou non)
  public boolean estDansCatalogue(Livre livre) {
    if (livre != null) {
      GenreLivre genre = livre.getGenre();
      switch (genre) {
        case REFERENCE:
          if (livresReference.contains(livre)) {
            return true;
          }
          break;

        case ROMAN:
          if (livresRomans.contains(livre)) {
            return true;
          }
          break;

        case BIBLIOGRAPHIE:
          if (livresBiographie.contains(livre)) {
            return true;
          }
          break;

        case CUISINE:
          if (livreCuisine.contains(livre)) {
            return true;
          }
          break;

        case MANUEL:
          if (livresManuels.contains(livre)) {
            return true;
          }
          break;
      }
    }
    return false;
  }

  // PREREQUIS: livre != null
  // RENVOIE : vrai si livre est disponible à l'emprunt
  public boolean peutEtreEmprunte(Livre livre) {
    if (livre.estSorti() == true) {
      return false;
    }
    return true;
  }

  // PREREQUIS : livre != null
  // RENVOIE : vrai si livre est dans le catalogue de cette bibliotheque
  // ou dans celui de l'un de ses branches
  public boolean estDansCatalogueEtendu(Livre livre) {
    if (estDansCatalogue(livre)) {
      return true;
    } else {
      for (Bibliotheque bibliotheque : branches) {
        if (bibliotheque.estDansCatalogue(livre)) {
          return true;
        }
      }
    }
    return false;
  }

  // PREREQUIS : livre != null
  // MODIFIE : this
  // EFFETS : enregistre le fait que livre est maintenant emprunté (si possible)
  // RENVOIE : vrai si l'opération est un succès
  public boolean enregistrerSortie(Livre livre) {
    if (livre != null) {
      if (livre.estSorti() == true) {
        return false;
      }
    }
    return false;
  }

  // PREREQUIS : livre != null
  // MODIFIE : this
  // EFFETS : enregistre le fait que livre est revenu (si possible)
  // RENVOIE : vrai si l'opération est un succès
  public boolean enregistrerRetour(Livre livre) {
    if (livre != null) {
      if (livre.estSorti() == false) {
        return true;
      }
    }
    return false;
  }

  // PREREQUIS : gerant != null
  // MODIFIE : this
  // EFFETS : set le nouveau gérant
  // RENVOIE : vrai si l'opération est un succès
  public boolean engagerGerant(Bibliothecaire bibliothecaire) {
    if (gerant != null) {
      this.gerant = bibliothecaire;
      return true;
    }
    return false;
  }

  // EFFETS : affiche le catalogue de cette bibliothèque
  // (toutes les informations de chaque livre)
  public void afficherCatalogue() {
    for (Bibliotheque bibliotheque : branches) {
      System.out.println(bibliotheque);
    }
  }
}
