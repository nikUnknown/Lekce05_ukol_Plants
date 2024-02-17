import com.ja.plant.PlantException;
import com.ja.plant.PlantList;
import com.ja.plant.Settings;

public class Main {


    public static void main(String[] args) {

        String filename = Settings.getFilename();
        PlantList plantList = new PlantList();

        try {
            plantList.loadContentFromFile(filename);
        } catch (PlantException e) {
            System.err.println("Error during loading a file: " +filename+ ":\n" + e.getLocalizedMessage());
        }

        //Vypis seznamu kvetin
        System.out.println("List of flowers: \n"+plantList.getPlants());


    }
}