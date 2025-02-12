package week2.inventoryManagementSystem;

public class Location {
    private int aisles;
    private int shelf;
    private int bin;

    public Location(int aisles, int shelf, int bin) {
        this.aisles = aisles;
        this.shelf = shelf;
        this.bin = bin;
    }

    public int getAisles() {
        return aisles;
    }

    public void setAisles(int aisles) {
        this.aisles = aisles;
    }

    public int getShelf() {
        return shelf;
    }

    public void setShelf(int shelf) {
        this.shelf = shelf;
    }

    public int getBin() {
        return bin;
    }

    public void setBin(int bin) {
        this.bin = bin;
    }

    @Override
    public String toString() {
        return "Location{" +
                "aisles=" + aisles +
                ", shelf=" + shelf +
                ", bin=" + bin +
                '}';
    }
}
