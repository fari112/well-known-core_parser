package data;

import java.util.ArrayList;

public class WKCLists {

    ArrayList<String> ressourceTypes = new ArrayList<>();
    private int currentPosition = 0;

    public WKCLists() {
    }

    public WKCLists(ArrayList<String> ressourceTypes, int currentPosition) {
        this.ressourceTypes = ressourceTypes;
        this.currentPosition = currentPosition;
    }

    public ArrayList<String> getRessourceTypes() {
        return ressourceTypes;
    }

    public void setRessourceTypes(ArrayList<String> ressourceTypes) {
        this.ressourceTypes = ressourceTypes;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }
}

