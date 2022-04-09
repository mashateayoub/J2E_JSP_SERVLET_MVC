package com.mid.model;

import java.util.Date;


public class Livre {

	private int isbn;
	private int matricule;
	private String titre;
	private String editeur;
	private String description;
	private Date date_edition;
	
	public Livre() {
		
	}

	public Livre(int isbn, String titre, String editeur, String description,Date date_edition, int matricule) {
		this.isbn = isbn;
		this.titre = titre;
		this.editeur = editeur;
		this.description = description;
		this.date_edition = date_edition;
		this.matricule = matricule;
	}

	
	
	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}
	
	
	
	
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}




	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



	public String getEditeur() {
		return editeur;
	}

	public void setEditeur(String editeur) {
		this.editeur = editeur;
	}

	
	
	public int getMatricule() {
		return matricule;
	}

	public void setMatricule(int matricule) {
		this.matricule = matricule;
	}
	
	

	public Date getDate_edition() {
		return date_edition;
	}

	public void setDate_edition(Date date_edition) {
		this.date_edition = date_edition;
	}
	
	
	
	
}

