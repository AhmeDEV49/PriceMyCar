package pricemycar.eni.fr.pricemycar.vehicleRecognition;

public class Vehicle {
    private String description;
    private int anneeSortie;
    private String boiteDeVitesse;
    private String carburantVersion;
    private String libVersion;
    private String libelleModele;
    private int nbPlace;
    private int puissance;

    public Vehicle() {

    }

    public Vehicle(String description, int anneeSortie, String boiteDeVitesse, String carburantVersion, String libVersion,String libelleModele, int nbPlace, int puissance)
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


    /**
    * Getters and setters
    *
    **/


    public int getPuissance() {
        return puissance;
    }

    public void setPuissance(int puissance) {
        this.puissance = puissance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAnneeSortie() {
        return anneeSortie;
    }

    public void setAnneeSortie(int anneeSortie) {
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

    public int getNbPlace() {
        return nbPlace;
    }

    public void setNbPlace(int nbPlace) {
        this.nbPlace = nbPlace;
    }
}
