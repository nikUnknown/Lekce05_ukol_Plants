package com.ja.plant;

import java.time.LocalDate;

public class Plant {
    private String name;
    private String notes;
    private LocalDate planted;
    private LocalDate watering;
    private int frequencyOfWatering;


    //Metoda getWateringInfo - vrátí název květiny, datum poslední zálivky a datum doporučené další zálivky.
    // (Metoda vrátí textový řetězec, obsahující požadované informace.)
    public String getWateringInfo() {
        LocalDate lastWatering = watering;
        LocalDate nextWateringDate = watering.plusDays(frequencyOfWatering);
        return "\nPlant: " + name + "\n"
                + "Last watering date: " + lastWatering + "\n"
                + "Next watering date: " + nextWateringDate +"\n";
    }

    //Konstruktory
    public Plant(String name, String notes,int frequencyOfWatering, LocalDate watering, LocalDate planted) throws PlantException {
        this.name = name;
        this.notes = notes;
        this.planted = planted;
        setWatering(watering);
        setFrequencyOfWatering(frequencyOfWatering);
    }

    // Druhy konstruktor
    public Plant(String name, int frequencyOfWatering, LocalDate planted) throws PlantException {
        this(name, "", frequencyOfWatering, LocalDate.now(), planted);
    }

    // Treti konstruktor
    public Plant(String name) throws PlantException {
        this(name, "",7,LocalDate.now(), LocalDate.now());
    }


    //Gettery a Settery
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getPlanted() {
        return planted;
    }

    public void setPlanted(LocalDate planted) {
        this.planted = planted;
    }

    public LocalDate getWatering() {
        return watering;
    }

    public void setWatering(LocalDate watering) throws PlantException{
        if (watering.isBefore(planted)){
            throw new PlantException("The date of the last watering cannot be before the date of planting.");
        }
        this.watering = watering;
    }

    public int getFrequencyOfWatering() {
        return frequencyOfWatering;
    }

    public void setFrequencyOfWatering(int frequencyOfWatering) throws PlantException{
        if (frequencyOfWatering <= 0){
            throw new PlantException("Frequency of watering can not be 0 or less.");
        }
        this.frequencyOfWatering = frequencyOfWatering;
    }

    @Override
    public String toString() {
        return "\nPlant:" +
                "name='" + name + '\'' +
                ", notes='" + notes + '\'' +
                ", planted=" + planted +
                ", watering=" + watering +
                ", frequencyOfWatering=" + frequencyOfWatering;
    }
}
