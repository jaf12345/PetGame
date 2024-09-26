// Zafar Azad 19088536
import java.io.*;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class GameEngine {

    private Player player;
    private Scanner scanner;
    private Timer timer;
    private static final String SAVE_FILE = "C:\\Users\\zafar\\OneDrive\\Desktop\\Assessment2 PDC\\PetGame\\src\\data.txt";


    public GameEngine() {
        scanner = new Scanner(System.in);
        timer = new Timer();
    }

    public void startGame() {
    System.out.print("Please enter your name: \n");
    String playerName = scanner.nextLine();

    if (playerExists(playerName)) {
        loadPetData(playerName); 
    } else {
        System.out.println("No data found for " + playerName + ". Creating a new profile.");
        initializePlayer(playerName); 
        createPets(); 
    }
        startPeriodicUpdates();
        gameLoop();
    }

    private void startPeriodicUpdates() { //this section is AI generated to help peridoic updates
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updatePetStates(); // Periodically update pet states
            }
        }, 0, 20000); // Update every 20 seconds (5000 milliseconds)
    }

    private void updatePetStates() {
        if (player.getCurrentPet() != null && player.getCurrentPet() != null) {
            VirtualPet pet = player.getCurrentPet();
            pet.increaseHunger(); 
            pet.decreaseHappiness(); 
            pet.decreaseEnergy(); 
        }
    }

    

    private void initializePlayer(String playerName) {
        player = new Player(playerName);
    }

    private void createPets() {
        VirtualPet pet = null; 
        boolean choiceMade = false;
        while (!choiceMade) {
            System.out.println("Please choose a pet to start with:");
            System.out.println("1. Cat");
            System.out.println("2. Dog");
            System.out.println("3. Alligator");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); 
    
            System.out.println("Now please enter the name of the Pet: ");
            String name = scanner.nextLine();
    
            switch (choice) {
                case 1:
                    pet = new Cat(name, 50, 50, 50);
                    choiceMade = true;
                    break;
                case 2:
                    pet = new Dog(name, 50, 50, 50);
                    choiceMade = true;
                    break;
                case 3:
                    pet = new Alligator(name, 50, 50, 50);
                    choiceMade = true;
                    break;
                default:
                    System.out.println("Invalid option. Please select again.");
                    break;
            }
        }  
        if (pet != null) {
            player.setCurrentPet(pet);
        }
         

    }

    private void gameLoop() {
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getPlayerChoice();
            running = handleChoice(choice);
        }
        endGame();
        System.exit(0);
    }

    private void displayMenu() {
        System.out.println("1. Feed your pet");
        System.out.println("2. Play with your pet");
        System.out.println("3. Train your pet");
        System.out.println("4. Check pet status");
        System.out.println("5. Let pet take a nap");
        System.out.println("6. Use learned trick");
        System.out.println("7. Exit");
    }

    private int getPlayerChoice() {
        System.out.print("Please select an option: \n");
        return scanner.nextInt();
    }

    private boolean handleChoice(int choice) {
        switch (choice) {
            case 1:
                
                player.feedCurrentPet();
                break;
            case 2:
                
                player.playWithCurrentPet();
                break;
            case 3:                
                player.trainCurrentPet();
                break;
            case 4:
                player.showCurrentPetStatus();
                break;
            case 5:
                player.currentPetNap();
                break;
            case 6:
                player.useTrick(); 
                break;
            case 7:
                System.out.println("Exiting the game...");
                return false;
            default:
                System.out.println("Invalid choice, please try again.");
        }
        return true;
    }

    private void endGame() {
        savePetData();
        System.out.println("Thank you for playing!");
    }

    private boolean playerExists(String playerName) {
        File file = new File(SAVE_FILE);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data[0].equals(playerName)) {
                        return true; 
                    }
                }
            } catch (IOException e) {
                System.out.println("An error occurred while checking player data.");
            }
        }
        return false; 
    }

    
    private void savePetData() {
        if (player.getCurrentPet() != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(SAVE_FILE, true))) { // Note the `true` here for appending
                VirtualPet pet = player.getCurrentPet();
                writer.write(player.getPlayerName() + "," +
                             pet.getClass().getSimpleName() + "," +
                             pet.getName() + "," +
                             pet.getHungerLevel() + "," +
                             pet.getHappinessLevel() + "," +
                             pet.getEnergyLevel() + "\n"); // Add newline for next record
                System.out.println("Pet and player data saved successfully.");
            } catch (IOException e) {
                System.out.println("An error occurred while saving pet data.");
            }
        }
    }
    

    private void loadPetData(String playerName) {
        File file = new File(SAVE_FILE);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data[0].equals(playerName)) { // Check if the line matches the current player
                        String petType = data[1];
                        String petName = data[2];
                        int hunger = Integer.parseInt(data[3]);
                        int happiness = Integer.parseInt(data[4]);
                        int energy = Integer.parseInt(data[5]);
    
                        // Initialize player with the provided playerName
                        player = new Player(playerName);
                        VirtualPet pet = null;
                        switch (petType) {
                            case "Cat":
                                pet = new Cat(petName, hunger, happiness, energy);
                                break;
                            case "Dog":
                                pet = new Dog(petName, hunger, happiness, energy);
                                break;
                            case "Alligator":
                                pet = new Alligator(petName, hunger, happiness, energy);
                                break;
                            default:
                                System.out.println("Unknown pet type in save file.");
                                break;
                        }
    
                        if (pet != null) {
                            player.setCurrentPet(pet);
                            System.out.println("Player and pet data loaded successfully.");
                        }
                        break; 
                    }
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("An error occurred while loading player data.");
            }
        }
    }
    
}

