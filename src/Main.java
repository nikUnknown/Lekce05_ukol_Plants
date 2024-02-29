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
        String filenameout = Settings.getFileNameOut();

        PlantList plantList = new PlantList();

        try {
            plantList.loadContentFromFile(filename);
        } catch (PlantException e) {
            System.err.println("Error during loading a file: " + filename + ":\n" + e.getLocalizedMessage());
        }

        System.out.println("\nList of plants: " + plantList.getPlants() + "\n");

        //Pridani novych kvetin
        plantList.addPlant(new Plant("Africka kopriva", "jedovata", 7, LocalDate.of(2023, 8, 12), LocalDate.of(2023, 8, 10)));
        plantList.addPlant(new Plant("Bazalka"));


        //Vypsani kvetiny pomoci indexu
        plantList.getPlantByIndex(1);

        // Odebrani kvetiny
        try {
            plantList.removePlant(2);
        }catch (IndexOutOfBoundsException e) {
            System.err.println("Plant can not be removed.\n" + e.getLocalizedMessage());
        }

        //Vypis seznamu kvetin
        System.out.println("\nList of plants: " + plantList.getPlants());

        //Vypsani a setrizeni infa o zalivce vsech kvetin
        System.out.println("\nInfo about watering (sorted): " + plantList.getPlantsSortedByWatering());

        //Zapis kvetin do noveho seznamu
        plantList.savePlantsToFile(filenameout);
        System.out.println("*************");

        //Opetovne nacteni vygenerovaneho souboru
        System.out.println("Printing file again:");
        plantList.loadContentFromFile(filenameout);

        System.out.println("\nSorted lists of plants:");
        //Vypis kvetin serazenych podle jmena
        System.out.println(plantList.getPlantsSortedByName());

        //Vypis kvetin serazenych podle posledni zalivky
        System.out.println(plantList.getPlantsSortedByLastWatering());
    }
}