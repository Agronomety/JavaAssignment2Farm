package org.example;

import java.util.List;
import java.util.Scanner;

public class Animals extends Entity {
   public String species;

   public static int animalId = 1;

   Scanner scanner = new Scanner(System.in);

public Animals (int id, String name, String species) {
   super(id, name);
   if (id > animalId) {
      animalId = id + 1;
   }
   this.species = species;
}

public Animals(String name, String species){
   super(animalId, name);
   animalId++;
   this.name = name;
   this.species = species;

}

   public void GetDescription(){
      System.out.println("Id: "+id+ ". Name: "+name+ ". Species: "+species+".");
   }

   public String getSpecies() {
      return species;
   }
   public void setSpecies(String species) {
      this.species = species;
   }

   public String getCSV () {
      return id + "," + name + "," + species;
   }

}
