import com.ja.plant.Plant;
import com.ja.plant.PlantException;
import com.ja.plant.PlantList;
import com.ja.plant.Settings;

import java.time.LocalDate;

public class Main {


    public static void main(String[] args) throws PlantException {

        System.out.println("**************");
        System.out.println("   FLOWERS   \n");

        String filename = Settings.getFilename();

        PlantList plantList = new PlantList();

        try {
            plantList.loadContentFromFile(filename);
        } catch (PlantException e) {
            System.err.println("Error during loading a file: " +filename+ ":\n" + e.getLocalizedMessage());
        }

        System.out.println("List of plants:\n" + plantList.getPlants()+"\n");

        // Pridani novych kvetin
        try {
            plantList.addPlant(new Plant("Africka kopriva", "jedovata",7, LocalDate.of(2023, 8, 12), LocalDate.of(2023, 8, 10)));
        } catch (PlantException e) {
            throw new PlantException("File was not saved correctly.\n" + e.getLocalizedMessage());
        }
        try {
            plantList.addPlant(new Plant("Bazalka"));
        } catch (PlantException e) {
            throw new PlantException("File was not saved correctly.\n" + e.getLocalizedMessage());
        }

        //Vypsani kvetiny pomoci indexu
        plantList.getPlantByIndex(1);

        // Odebrani kvetiny
        plantList.removePlant(2);

        //Vypis seznamu kvetin
        System.out.println("List of plants: \n"+plantList.getPlants());

        //Vypsani a setrizeni infa o zalivce vsech kvetin
        System.out.println("Info about watering (sorted):\n" + plantList.getPlantsSortedByWatering());


    }
}