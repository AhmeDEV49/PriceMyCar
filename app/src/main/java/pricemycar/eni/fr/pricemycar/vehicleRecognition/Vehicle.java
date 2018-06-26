package pricemycar.eni.fr.pricemycar.vehicleRecognition;

public class Vehicle {
    private String description;
    private String anneeSortie;
    private String boiteDeVitesse;
    private String carburantVersion;
    private String libVersion;
    private String libelleModele;
    private String nbPlace;
    private String puissance;

    public Vehicle() {

    }

    public Vehicle(String description, String anneeSortie, String boiteDeVitesse, String carburantVersion, String libVersion,String libelleModele, String nbPlace, String puissance)
    {
        this.description = description;
        this.anneeSortie = anneeSortie;
        this.boiteDeVitesse = boiteDeVitesse;
        this.carburantVersion = carburantVersion;
        this.libVersion = libVersion;
        this.libelleModele = libelleModele;
        this.nbPlace = nbPlace;
        this.puissance = puissance;
    }

    public String toString() {
      return "Description : "+this.description+"\n Année de sortie :"+this.anneeSortie+"\n Boite de vitesse :"+this.boiteDeVitesse+"\n Carburant :"+this.carburantVersion+"\n Version : "+this.libVersion+"\n Modèle :"+this.libelleModele+"\n Place :"+this.nbPlace+"\n Puissance :"+this.puissance;
    }


    /**
    * Getters and setters
    *
    **/


    public String getPuissance() {
        return puissance;
    }

    public void setPuissance(String puissance) {
        this.puissance = puissance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnneeSortie() {
        return anneeSortie;
    }

    public void setAnneeSortie(String anneeSortie) {
        this.anneeSortie = anneeSortie;
    }

    public String getBoiteDeVitesse() {
        return boiteDeVitesse;
    }

    public void setBoiteDeVitesse(String boiteDeVitesse) {
        this.boiteDeVitesse = boiteDeVitesse;
    }

    public String getCarburantVersion() {
        return carburantVersion;
    }

    public void setCarburantVersion(String carburantVersion) {
        this.carburantVersion = carburantVersion;
    }

    public String getLibVersion() {
        return libVersion;
    }

    public void setLibVersion(String libVersion) {
        this.libVersion = libVersion;
    }

    public String getLibelleModele() {
        return libelleModele;
    }

    public void setLibelleModele(String libelleModele) {
        this.libelleModele = libelleModele;
    }

    public String getNbPlace() {
        return nbPlace;
    }

    public void setNbPlace(String nbPlace) {
        this.nbPlace = nbPlace;
    }
}
