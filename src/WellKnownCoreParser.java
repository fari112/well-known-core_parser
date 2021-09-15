import data.WKCLists;
import data.WellKnownCoreData;
import data.WellKnownCoreEntry;

import java.util.ArrayList;

public class WellKnownCoreParser {

    String dumb;
    ArrayList<WellKnownCoreData> wellKnownCoreData = new ArrayList<>();

    public WellKnownCoreParser(String dumb) {
        this.dumb = dumb;
    }

    /**
     * Parser function for .well-known/core ressource
     */
    public void parseWellKnownCore() {
        WellKnownCoreData data = new WellKnownCoreData();


        for (int i = 0; i < dumb.length(); i++) {

            switch (dumb.charAt(i)) {
                case '<':
                    if (dumb.charAt(i + 2) != '>') {
                        WellKnownCoreEntry resultName;
                        resultName = analyzeAttributeName(i);
                        i = resultName.getCurrentPosition();
                        data.setName(resultName.getResult());
                    } else {
                        data.setName("");
                    }
                    break;
                case 't':
                    WellKnownCoreEntry resultTitle;
                    if (dumb.charAt(i + 1) == 'i' && dumb.charAt(i + 2) == 't' && dumb.charAt(i + 3) == 'l' && dumb.charAt(i + 4) == 'e' && dumb.charAt(i + 5) == '=') {
                        resultTitle = analyzeTitle(i);
                        i = resultTitle.getCurrentPosition();
                        data.setTitle(resultTitle.getResult());
                    }
                    break;
                case 'r':
                    WKCLists resultRessourceType;
                    if (dumb.charAt(i + 1) == 't' && dumb.charAt(i + 2) == '=') {
                        resultRessourceType = analyzeWKCLists(i);
                        i = resultRessourceType.getCurrentPosition();
                        data.setRessourceType(resultRessourceType.getRessourceTypes());
                    }
                    break;
                case 'c':
                    int contentType;
                    WellKnownCoreEntry wellKnownCoreEntry;
                    if (dumb.charAt(i + 1) == 't' && dumb.charAt(i + 2) == '=') {
                        i += 3;
                        if (dumb.charAt(i) == '"') {
                            i++;
                        }
                        wellKnownCoreEntry = analyzeContentType(i);
                        contentType = Integer.parseInt(wellKnownCoreEntry.getResult());
                        data.setContentType(contentType);
                        if (i == dumb.length() - 1) {
                            wellKnownCoreData.add(new WellKnownCoreData(data.getName(), data.getRessourceType(), data.getContentType(), data.getInterfaces(), data.getMaxSize(), data.isObservable(), data.getTitle()));
                        }
                        break;
                    }
                case 's':
                    WellKnownCoreEntry resultMaxSize;
                    if (dumb.length() > i + 2 && dumb.charAt(i + 1) == 'z' && dumb.charAt(i + 2) == '=') {
                        resultMaxSize = analyzeMaxSize(i);
                        i = resultMaxSize.getCurrentPosition();
                        data.setMaxSize(Integer.parseInt(resultMaxSize.getResult()));
                        break;
                    } else if (i == dumb.length() - 1) {
                        wellKnownCoreData.add(new WellKnownCoreData(data.getName(), data.getRessourceType(), data.getContentType(), data.getInterfaces(), data.getMaxSize(), data.isObservable(), data.getTitle()));
                        break;
                    }
                case 'i':
                    WKCLists resultInterfaces;
                    if (dumb.charAt(i + 1) == 'f' && dumb.charAt(i + 2) == '=') {
                        resultInterfaces = analyzeWKCLists(i);
                        i = resultInterfaces.getCurrentPosition();
                        data.setInterfaces(resultInterfaces.getRessourceTypes());
                    }
                    break;
                case 'o':
                    if (dumb.charAt(i + 1) == 'b' && dumb.charAt(i + 2) == 's') {
                        data.setObservable(true);
                    }
                    break;
                case ',':
                    wellKnownCoreData.add(new WellKnownCoreData(data.getName(), (ArrayList<String>) data.getRessourceType().clone(), data.getContentType(), (ArrayList<String>) data.getInterfaces().clone(), data.getMaxSize(), data.isObservable(), data.getTitle()));
                    data.clear();
                    break;
                default:
                    if (i == dumb.length() - 1) {
                        wellKnownCoreData.add(new WellKnownCoreData(data.getName(), data.getRessourceType(), data.getContentType(), data.getInterfaces(), data.getMaxSize(), data.isObservable(), data.getTitle()));
                    }
                    break;
            }
        }
    }

        /**
     * Helper function for wkc parser
     *
     * @param i current position in String representation of wkc
     * @return WKCLists element which carry's new position in String and analysis result
     */
    private WKCLists analyzeWKCLists(int i) {
        ArrayList<String> currentEntry = new ArrayList<>();
        String currentRT = "";
        WKCLists response = new WKCLists();
        for (int j = i + 4; j < dumb.length(); j++) {
            if (dumb.charAt(j) == '"') {
                currentEntry.add(currentRT);
                response.setCurrentPosition(j);
                response.setRessourceTypes(currentEntry);
                return response;
            } else if (dumb.charAt(j) == ' ') {
                currentEntry.add(currentRT);
                currentRT = "";
            } else {
                currentRT += dumb.charAt(j);
            }
        }
        return null;
    }

    /**
     * Helper function for wkc parser
     *
     * @param i current position in String representation of wkc
     * @return Element contains new position in String and analysis result
     */
    private WellKnownCoreEntry analyzeMaxSize(int i) {
        String currentEntry = "";
        WellKnownCoreEntry response = new WellKnownCoreEntry();
        for (int j = i + 3; j < dumb.length(); j++) {
            if (dumb.charAt(j) == ';') {
                response.setCurrentPosition(j);
                response.setResult(currentEntry);
                return response;
            } else {
                currentEntry += dumb.charAt(j);
            }
        }
        return null;
    }

    /**
     * Helper function for wkc parser
     *
     * @param i current position in String representation of wkc
     * @return Element contains new position in String and analysis result
     */
    private WellKnownCoreEntry analyzeContentType(int i) {
        String currentEntry = "";
        WellKnownCoreEntry response = new WellKnownCoreEntry();
        for (int j = i; j < dumb.length(); j++) {
            if (dumb.charAt(j) == ';' || dumb.charAt(j) == ' ' || dumb.charAt(j) == ',') {
                response.setCurrentPosition(j);
                response.setResult(currentEntry);
                return response;
            } else {
                currentEntry += dumb.charAt(j);
            }
        }
        response.setResult(currentEntry);
        return response;
    }

    /**
     * Helper function for wkc parser
     *
     * @param i current position in String representation of wkc
     * @return Element contains new position in String and analysis result
     */
    public WellKnownCoreEntry analyzeAttributeName(int i) {
        String currentEntry = "";
        WellKnownCoreEntry response = new WellKnownCoreEntry();
        for (int j = i + 2; j < dumb.length(); j++) {
            if (dumb.charAt(j) == '>') {
                response.setCurrentPosition(j);
                response.setResult(currentEntry);
                return response;
            } else {
                currentEntry += dumb.charAt(j);
            }
        }
        return null;
    }

    /**
     * Helper function for wkc parser
     *
     * @param i current position in String representation of wkc
     * @return Element contains new position in String and analysis result
     */
    public WellKnownCoreEntry analyzeTitle(int i) {
        String currentEntry = "";
        WellKnownCoreEntry response = new WellKnownCoreEntry();
        for (int j = i + 7; j < dumb.length(); j++) {
            if (dumb.charAt(j) == '"') {
                response.setCurrentPosition(j);
                response.setResult(currentEntry);
                return response;
            } else {
                currentEntry += dumb.charAt(j);
            }
        }
        return null;
    }


    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < wellKnownCoreData.size(); i++) {
            result += wellKnownCoreData.get(i).toString() + "\n";
        }
        return result;
    }
}
