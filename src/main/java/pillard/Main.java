package pillard;

import java.util.*;

public class Main {

    public static void displayMenu() {
        System.out.println();
            System.out.println("What would you like to do?");
            System.out.println("  1) View all pets");
            System.out.println("  2) Add more pets");
            System.out.println("  3) Remove an existing pet");
            System.out.println("  4) Exit program");
            System.out.print("Your choice: ");
    }

    public static void main(String[] args) {
        PetDatabase petDB = new PetDatabase();
        int choice = 0;
        Scanner input = new Scanner(System.in);
        final String filename = "petDB.txt";

        while(choice != 4) {
            displayMenu();    
            choice = input.nextInt();
            System.out.println();

            switch(choice) {
                case 1: petDB.displayAll(); break;
                case 2: petDB.addPets(); break;
                case 3: petDB.removePet(); break;
                case 4: petDB.save(filename); break;
                default: System.out.println("Invalid Selection");
            }
        }
        input.close();
    }
}
