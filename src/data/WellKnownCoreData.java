package data;

import java.util.ArrayList;

public class WellKnownCoreData {

    String name = "";
    ArrayList<String> ressourceType = new ArrayList<>();
    int contentType = -1;
    ArrayList<String> interfaces = new ArrayList<>();
    int maxSize = -1;
    boolean observable = false;

    public WellKnownCoreData() {
    }

    public WellKnownCoreData(String name, ArrayList<String> ressourceType, int contentType, ArrayList<String> interfaces, int maxSize, boolean observable) {
        this.name = name;
        this.ressourceType = ressourceType;
        this.contentType = contentType;
        this.interfaces = interfaces;
        this.maxSize = maxSize;
        this.observable = observable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getRessourceType() {
        return ressourceType;
    }

    public void setRessourceType(ArrayList<String> ressourceType) {
        this.ressourceType = ressourceType;
    }

    public int getContentType() {
        return contentType;
    }

    public void setContentType(int contentType) {
        this.contentType = contentType;
    }

    public ArrayList<String> getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(ArrayList<String> interfaces) {
        this.interfaces = interfaces;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public boolean isObservable() {
        return observable;
    }

    public void setObservable(boolean observable) {
        this.observable = observable;
    }

    public void clear() {
        setName("");
        ressourceType.clear();
        contentType = -1;
        interfaces.clear();
        maxSize = -1;
        setObservable(false);
    }

    @Override
    public String toString() {
        return "WellKnownCoreData{" +
                "name='" + name + '\'' +
                ", ressourceType=" + ressourceType +
                ", contentType=" + contentType +
                ", interfaces=" + interfaces +
                ", maxSize=" + maxSize +
                ", observable=" + observable +
                '}';
    }
}

