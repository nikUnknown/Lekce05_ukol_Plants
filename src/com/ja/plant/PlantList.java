package com.ja.plant;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlantList {
    private List<Plant> plants = new ArrayList<>();

    //Metoda pro nacteni obsahu souboru
    public void loadContentFromFile(String fileName) throws PlantException {
        int lineCounter = 0;
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)))) {
            while (scanner.hasNextLine()) {
                lineCounter++;
                String line = scanner.nextLine();
                System.out.println(line);
                String[] parts = line.split(";");
                if(parts.length != 5) {
                    String name = parts[0];
                    String notes = parts[1];
                    int wateringFrequency = Integer.parseInt(parts[2]);
                    LocalDate watering = LocalDate.parse(parts[3]);
                    LocalDate planted = LocalDate.parse(parts[4]);
                    Plant plant = new Plant(name, notes, wateringFrequency, watering, planted);
                    plants.add(plant);
                    throw new PlantException("Missing item on line" +lineCounter+ ":" +line+ "!");
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File " +fileName+ " was not found.\n" +e.getLocalizedMessage());
        } finally {
            System.out.println("Loaded " +plants.size()+ " items.");
        }
    }

    //Metoda pro zapis do souboru

    public void savePlantsToFile(String fileName) throws PlantException{
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
            for (Plant plant : plants) {
                writer.println(
                        plant.getName() + Settings.getDelimiter() +
                                plant.getNotes() + Settings.getDelimiter() +
                                plant.getWateringInfo() + Settings.getDelimiter() +
                                plant.getPlanted());
            }
        } catch (IOException e) {
            throw new PlantException("File " +fileName+ " was not found.\n" + e.getLocalizedMessage());
        } finally {
            System.out.println("The file was saved.");
        }

    }


    //Pridani kvetinu do seznamu
    public void addPlant(Plant plant){
        System.out.println("Adding a new plant to list: " + plant.toString());
        plants.add(plant);
    }

    //Ziskani kvetiny po zadani indexu
    public Plant getPlantByIndex(int index){
        return plants.get(index);
    }
    public List<Plant> getPlants() {
        return plants;
    }


    //Odebrani kvetiny ze seznamu
    public void removePlant(Plant plant){
        System.out.println("Removing plant from list.");
        plants.remove(plant);
    }

    //Pridani vice kvetin najednou - pridam cely seznam kvetin
    public void addPlants(List<Plant> plants){
        this.plants.addAll(plants);
    }


}
