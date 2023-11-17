package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


import static org.example.Crop.cropId;

public class Farm {


ArrayList<Animals> animalList = new ArrayList<>();
ArrayList<Crop> cropList = new ArrayList<>();

File animalFile = new File("animals.txt");
File cropFile = new File("crops.txt");
Scanner scanner = new Scanner(System.in);




    public Farm() {


        /*animalList.add(new Animals("Flodhäst", "Hippotamus Amphibius"));
        animalList.add(new Animals("Kungskobra", "Ophiophagus Hannah"));
        animalList.add(new Animals("Sötvattenkrokodil", "Crocodylus Johnstoni"));
        animalList.add(new Animals("Enhörning", "Hästicus Fakeius"));*/

        /*cropList.add(new Crop("Carrot", "Root Vegetable", 10));
        cropList.add(new Crop("Wheat", "Cereal", 2));
        cropList.add(new Crop("Corn", "Cereal", 5));
        cropList.add(new Crop("Lenses", "Legumes", 3));*/

        /*try {
            FileReader reader = new FileReader(animalFile);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] variables = line.split(",");
                int animalId = Integer.parseInt(variables[0]);
                String name = variables[1];
                String species = variables[2];
                Animals animal = new Animals(animalId, name, species);
                animalList.add(animal);
                line = bufferedReader.readLine();
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/



        try (FileReader reader = new FileReader(animalFile);
             BufferedReader bufferedReader = new BufferedReader(reader)) {

            String line = bufferedReader.readLine();
            while (line != null) {
                int i = 0;
                String[] variables = line.split(",");
                int id = Integer.parseInt(variables[0]);
                String name = variables[1];
                String species = variables[2];
                Animals animal = new Animals(id, name, species);
                animalList.add(animal);
                i++;
                line = bufferedReader.readLine();

            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (FileReader reader = new FileReader(cropFile);
             BufferedReader bufferedReader = new BufferedReader(reader)) {

            String line = bufferedReader.readLine();
            while (line != null) {
                String[] variables = line.split(",");
                int cropId = Integer.parseInt(variables[0]);
                String name = variables[1];
                String cropType = variables[2];
                int quantity = Integer.parseInt(variables[3]);
                Crop crop = new Crop(cropId, name, cropType, quantity);
                cropList.add(crop);
                line = bufferedReader.readLine();
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }






public void farmMenu(){

    boolean activeProgram = true;
    while (activeProgram){
        System.out.println("Main Menu. Enter a number for what you want to do:");
        System.out.println("1. Add Animal.");
        System.out.println("2. Add Crop");
        System.out.println("3. View Animals");
        System.out.println("4. View Crops");
        System.out.println("5. Remove Animal.");
        System.out.println("6. Remove Crop.");
        System.out.println("7. Feed Animal.");
        System.out.println("8. Save.");
        System.out.println("9. Quit.");
        int choice = scanner.nextInt();

switch (choice){
    case 1:
        addAnimals();
        break;
    case 2:
        addCrops();
        break;
    case 3:
        viewAnimals();
        break;
    case 4:
        viewCrops();
        break;
    case 5:
        removeAnimal();
        break;
    case 6:
        removeCrop();
        break;

    case 7:
        feedAnimal();
        break;

    case 8:
        save();
        break;

    case 9:
        activeProgram = false;
        break;
    default:
        break;
}
    }

}




    void viewCrops() {
    for (Crop crops : cropList) {
        crops.GetDescription();
    }
}
    void viewAnimals() {
    for (Animals animals : animalList) {
        animals.GetDescription();
        }
    }

    public void addAnimals(){
        String paus = scanner.nextLine();
        System.out.println("Add a new animals. Please insert the information. \nName: ");
        String addAnimal = scanner.nextLine();
        System.out.println("Species: ");
        String addSpecies = scanner.nextLine();
        animalList.add(new Animals(addAnimal, addSpecies));
        viewAnimals();
    }

    public void addCrops(){
        viewCrops();
        System.out.println("Please insert the id of the crop you want to add to. If you want to add a crop that isn't on the list please insert a new id.");
        int idCheck = scanner.nextInt();
        Crop crop = getCropId(idCheck);

            if (crop == null) {
                System.out.println("No Crop with that id was found. Want to add a new crop to the list? \nPress 1 for Yes or press 2 for No.");
                int yesOrNo = scanner.nextInt();
                    switch (yesOrNo) {
                        case 1:
                            String paus = scanner.nextLine();
                            System.out.println("Please insert the information.");
                            System.out.println("Name: ");
                            String addCrop = scanner.nextLine();
                            System.out.println("Crop Type: ");
                            String addCropType = scanner.nextLine();
                            System.out.println("Quantity: ");
                            int cropQuantity = scanner.nextInt();;
                            cropList.add(new Crop(addCrop, addCropType,cropQuantity));
                            viewCrops();

                        case 2:
                            break;

                    }

            }
            else {
                System.out.println("How much quantity do you want to add to the crop?\n");
                int addCropQuantity = scanner.nextInt();
                crop.setQuantity(crop.getQuantity() + addCropQuantity);
                viewCrops();
            }
    }

private Crop getCropId (int id) {
    for (Crop crop : cropList) {
        if (crop.getId() == id) {
            return crop;
        }
    }
    return null;
}

private Animals getAnimalId (int id) {
        for (Animals animal : animalList) {
            if (animal.getId() == id) {
                return animal;
            }
        }
        return null;
}


private void removeAnimal() {
    boolean found = false;
    int input = scanner.nextInt();
    try {
        for (int i = 0; i < animalList.size(); i++) {
            if (input == animalList.get(i).getId()) {
                animalList.remove(i);
                found = true;
                i--;
            }
        }
        if (found == false){
            System.out.println("No such id exists in the list.");
        }
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
}


    private void removeCrop() {
        boolean found = false;
        int input = scanner.nextInt();
        try {
            for (int i = 0; i < cropList.size(); i++) {
                if (input == cropList.get(i).getId()) {
                    cropList.remove(i);
                    found = true;
                    i--;
                }
            }
            if (found == false){
                System.out.println("No such id exists in the list.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }





    private void feedAnimal() {
        viewCrops();
        System.out.println("Choose the id of the crop you wish to feed an animal to.");
        int input = scanner.nextInt();
        boolean found = false;

        try {
            for (int i = 0; i < cropList.size(); i++) {
                if (input == cropList.get(i).getId()) {
                    found = true;
                    if (cropList.get(i).getQuantity() < 1) {
                        System.out.println("The Quantity is 0. Please add quantity to the crop or choose another crop.");
                        break;
                    } else {
                        boolean haveBeenFed = false;
                        while (!haveBeenFed) {
                            System.out.println("Choose the animal you wish to feed.");
                            viewAnimals();
                            int inputAnimal = scanner.nextInt();
                            for (int u = 0; u < animalList.size(); u++) {
                                if (inputAnimal == animalList.get(u).getId()) {
                                    haveBeenFed = true;
                                    System.out.println("The animal has been fed.");
                                    cropList.get(i).setQuantity(cropList.get(i).getQuantity()-1);
                                    break;
                                }
                            }
                            if (!haveBeenFed) {
                                System.out.println("The id does not match any animal in the list.");
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




private void save() {
        try {
            FileWriter saveFile = new FileWriter(animalFile);
            BufferedWriter bw = new BufferedWriter(saveFile);
            for (int i = 0; i < animalList.size(); i++) {
                bw.write(animalList.get(i).getCSV());
                if (i < animalList.size() - 1) {
                    bw.newLine();
                }
            }
            bw.close();


        saveFile = new FileWriter(cropFile);
        bw = new BufferedWriter(saveFile);
        for (int i = 0; i < cropList.size(); i++) {
            bw.write(cropList.get(i).getCSV());
            if (i < cropList.size() - 1) {
                bw.newLine();
            }
        }
            bw.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

}

}


