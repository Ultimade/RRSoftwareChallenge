package hu.rrsoftvare.RRSoftwareChallenge.Enums;

/**
 * Régiók enumerálása a használható régiókhoz
 */
public enum Regions {
    EUROPE("Európa"),
    ASIA("Ázsia"),
    AFRICA("Afrika"),
    AMERICA("Amerika"),
    AUSTRALIA("Ausztrália");

    String region;
    Regions(String region){
        this.region = region;
    }

    public String getRegion(){
        return region;
    }
}
