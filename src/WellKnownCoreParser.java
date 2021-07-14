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

    //TODO observable abschließen
    public void decodeWellKnownCore() {
        WellKnownCoreData data = new WellKnownCoreData();

        int counter = 0;

        for (int i = 0; i < dumb.length(); i++) {

            switch (dumb.charAt(i)) {
                case '<':
                    WellKnownCoreEntry resultName;
                    resultName = analyzeAttributeName(i);
                    i = resultName.getCurrentPosition();
                    data.setName(resultName.getResult());
                    break;
                case 'r':
                    WKCLists resultRessourceType;
                    if (dumb.charAt(i + 1) == 't' && dumb.charAt(i + 2) == '=') {
                        resultRessourceType = analyzeWKCLists(i);
                        i = resultRessourceType.getCurrentPosition();
                        data.setRessourceType(resultRessourceType.getRessourceTypes());
                        break;
                    }
                case 'c':
                    int contentType;
                    if (dumb.charAt(i + 1) == 't' && dumb.charAt(i + 2) == '=') {
                        i += 3;
                        if (dumb.charAt(i + 1) != ',' && dumb.charAt(i + 1) != ';') {
                            String temp = dumb.charAt(i) + String.valueOf(dumb.charAt(i + 1));
                            contentType = Integer.parseInt(temp, 10);
                        } else {
                            contentType = Integer.parseInt(String.valueOf(dumb.charAt(i)));
                        }
                        data.setContentType(contentType);
                        break;
                    }
                case 's':
                    WellKnownCoreEntry resultMaxSize;
                    if (dumb.charAt(i + 1) == 'z' && dumb.charAt(i + 2) == '=') {
                        resultMaxSize = analyzeMaxSize(i);
                        i = resultMaxSize.getCurrentPosition() + 1;
                        data.setMaxSize(Integer.parseInt(resultMaxSize.getResult()));
                        break;
                    }
                case 'i':
                    WKCLists resultInterfaces;
                    if (dumb.charAt(i + 1) == 'f' && dumb.charAt(i + 2) == '=') {
                        resultInterfaces = analyzeWKCLists(i);
                        i = resultInterfaces.getCurrentPosition();
                        data.setInterfaces(resultInterfaces.getRessourceTypes());
                        break;
                    }
                case ',':
                    wellKnownCoreData.add(counter, data);
                    //System.out.println(wellKnownCoreData.get(counter).toString());
                    counter++;
                    data.clear();
                    break;
                default:
                    break;
            }
        }
    }

    private WKCLists analyzeWKCLists(int i) {
        ArrayList<String> currentEntry = new ArrayList<>();
        String current = "";
        WKCLists response = new WKCLists();
        for (int j = i + 4; j < dumb.length(); j++) {
            if (dumb.charAt(j) == '"') {
                currentEntry.add(current);
                response.setCurrentPosition(j);
                response.setRessourceTypes(currentEntry);
                return response;
            } else if (dumb.charAt(j) == ' ') {
                currentEntry.add(current);
                current = "";
            } else {
                current += dumb.charAt(j);
            }
        }
        return null;
    }

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

    @Override
    public String toString() {
        return "WellKnownCoreParser{" +
                "wellKnownCoreData=" + wellKnownCoreData +
                '}';
    }
}
