package com.ja.plant;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

import static com.ja.plant.Settings.getDelimiter;

public class PlantList {
    private List<Plant> plants = new ArrayList<>();

    //Serazeni rostlin podle nazvu
    public StringBuilder getPlantsSortedByName() {
        List<Plant> sortedPlants = new ArrayList<>(plants);
        Collections.sort(sortedPlants, Comparator.comparing(Plant::getName));
        StringBuilder nameInfo = new StringBuilder();
        for (Plant plant : sortedPlants) {
            nameInfo.append(plant.toString());
        }
        return nameInfo;
    }

    //Serazeni rostlin podle nazvu
    public StringBuilder getPlantsSortedByLastWatering() {
        List<Plant> sortedPlants = new ArrayList<>(plants);
        Collections.sort(sortedPlants, Comparator.comparing(Plant::getWatering));
        StringBuilder wateringInfo = new StringBuilder();
        for (Plant plant : sortedPlants) {
            wateringInfo.append(plant.toString());
        }
        return wateringInfo;
    }



    //Metoda pro nacteni obsahu souboru
    public void loadContentFromFile(String fileName) throws PlantException {
        int lineCounter = 0;
        plants.clear();
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)))) {
            while (scanner.hasNextLine()) {
                lineCounter++;
                String line = scanner.nextLine();
                System.out.println(line);
                String[] parts = line.split(getDelimiter());
                if(parts.length != 5) {
                    throw new PlantException("Missing item on line" +lineCounter+ ":" +line+ "!");
                }
                String name = parts[0];
                String notes = parts[1];
                int wateringFrequency = Integer.parseInt(parts[2]);
                LocalDate watering = LocalDate.parse(parts[3]);
                LocalDate planted = LocalDate.parse(parts[4]);
                Plant plant = new Plant(name, notes, wateringFrequency, watering, planted);
                plants.add(plant);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File " + fileName + " was not found.\n" + e.getLocalizedMessage());
        } catch (DateTimeParseException e) {
            System.err.println("Date format is not correct on line: " + lineCounter + "\n" + e.getLocalizedMessage());
        } catch (NumberFormatException e){
            System.err.println("Wrong number format on line: " + lineCounter + "\n" + e.getLocalizedMessage());
        } finally {
            System.out.println("Loaded " +plants.size()+ " items.");
        }
    }

    //Metoda pro zapis do souboru
    public void savePlantsToFile(String fileName) throws PlantException{
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
            for (Plant plant : plants) {
                writer.println(
                        plant.getName() + getDelimiter() +
                                plant.getNotes() + getDelimiter() +
                                plant.getFrequencyOfWatering() + getDelimiter() +
                                plant.getWatering() + getDelimiter() +
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
    public void removePlant(int index) {
        System.out.println("Removing plant from list.");
        plants.remove(index);
    }

    //Pridani vice kvetin najednou - pridam cely seznam kvetin
    public void addPlants(List<Plant> plants){
        this.plants.addAll(plants);
    }

    //
    public StringBuilder getPlantsSortedByWatering() {
        List<Plant> sortedPlants = new ArrayList<>(plants);
        sortedPlants.sort(Comparator.comparing(Plant::getWateringInfo));
        StringBuilder wateringInfo = new StringBuilder();
        for (Plant plant : sortedPlants) {
            wateringInfo.append(plant.getWateringInfo());
        }
        return wateringInfo;
    }



}
