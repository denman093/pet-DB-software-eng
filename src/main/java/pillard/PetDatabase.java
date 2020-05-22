package pillard;

import java.util.*;
import java.io.*;

public class PetDatabase {
    private ArrayList<Pet> pets = new ArrayList<>();
    Scanner input = new Scanner(System.in);
    
    public PetDatabase() { }

    public void add(Pet pet) {
        pets.add(pet);
    }

    public void addPets() {
        String name = "";
        int age, count = 0;
        
        System.out.println("Enter \"done\" to stop.");

        while(!name.equals("done")) {
            System.out.print("Add pet (name, age): ");

            name = input.next();
            if(name.matches("done")) break;

            age = input.nextInt();

            pets.add(new Pet(name, age));
            count++;
        }
        System.out.println(count + " pets added.");
        input.nextLine();
    }

    public void displayAll() {
        header();

        for(Pet pet : pets) 
            System.out.printf("| %3d| %-10s| %4d|\n", pets.indexOf(pet), pet.getName(), pet.getAge());

        footer(pets.size());
    }
    
    public void searchByName() {
    	int count = 0;
    	
    	System.out.print("Enter a name to search: ");
    	String searchedName = input.next();
    	
    	header(); 
    	
    	for(Pet pet : pets) 
    		if(pet.getName().toLowerCase().equals(searchedName.toLowerCase())) {
    			System.out.printf("| %3d| %-10s| %4d|\n", pets.indexOf(pet), pet.getName(), pet.getAge());
    			count++;
    		}

        footer(count);
    }
    
    public void searchByAge() {
    	int searchedAge;
    	int count = 0;
    	
    	System.out.print("Enter an age to search: ");
    	searchedAge = input.nextInt();
    	
    	header();
    	
    	for(Pet pet : pets) 
    		if(pet.getAge() == searchedAge) {
    			System.out.printf("| %3d| %-10s| %4d|\n", pets.indexOf(pet), pet.getName(), pet.getAge());
    			count++;
    		}

        footer(count);
    }
    
    public void updatePet() {
        displayAll();

        if(pets.isEmpty()) {
            System.out.println("No pets to update");
            return;
        }

        System.out.print("Enter the pet ID to update: ");
        int petID = input.nextInt();

        System.out.print("Enter a new name and new age: ");
        String name = input.next();
        int age = input.nextInt();

        pets.get(petID).setName(name);
        pets.get(petID).setAge(age);
    }


    public void removePet() {
        displayAll();

        if(pets.isEmpty()) {
            System.out.println("No pets to remove.");
            return;
        }

        System.out.print("Enter the pet ID to remove: ");
        int petID = input.nextInt();

        if(petID >= 0 && petID < pets.size()) 
            pets.remove(petID);
        else
            System.out.println("Unable to remove pet.");
    }
    
    public boolean save(String filename) {
	try {
		FileOutputStream file = new FileOutputStream(filename); 
		ObjectOutputStream out = new ObjectOutputStream(file);
			
		out.writeObject(pets);
		out.close(); 
		file.close();
			
		System.out.println("Database has been saved.");
		return true;
			
	} catch (FileNotFoundException e) {
		System.out.println("File not found");
		return false;
	} catch (IOException e) {
		System.out.println("Error with input/output");
		return false;
	}
    }
    
    public ArrayList<Pet> load(String filename) {
        try { 
            FileInputStream file = new FileInputStream(filename); 
            ObjectInputStream in = new ObjectInputStream(file); 
			
            pets = (ArrayList) in.readObject(); 
			  
            in.close(); 
            file.close(); 
			
            System.out.println("Database has been loaded.");
	} 
	catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(1);
	} 
	catch (ClassNotFoundException e) {
            System.out.println("Class not found");
	} 
	catch (IOException e) {
            System.out.println("Error with input/output");
	} 
		 
	return pets;
    }
   
    public static void header() {
    	System.out.println("+----------------------+");
        System.out.printf("| %-3s| %-10s| %-4s|\n", "ID", "NAME", "AGE");
        System.out.println("+----------------------+");
    }

    public static void footer(int size) {
        if(size == 0)
            System.out.println("No records found.");
        else {
            System.out.println("+----------------------+");
            System.out.println(size + " rows in set."); 
        }
    }

    @Override
    public String toString() {
        return "Pets: " + pets;
    }
}