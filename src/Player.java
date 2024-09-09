// Zafar Azad 19088536
import java.util.Scanner;


public class Player{

    private String playerName;
    private VirtualPet currentPet;
    private Scanner scanner;

    Player(String name) {
        scanner = new Scanner(System.in);
        this.playerName = name;
        this.currentPet = null;
    }


    public String getPlayerName() {
        return this.playerName;
    }

    public void setCurrentPet(VirtualPet pet) {
        this.currentPet = pet;
    }

    public VirtualPet getCurrentPet() {
        return currentPet;
    }

    public void feedCurrentPet() {
        if (currentPet != null) {
            currentPet.eat();            
        } else {
            System.out.println("No pet selected cannot feed");
        }

    }

    public void playWithCurrentPet() {
        if (currentPet != null) {
            currentPet.play();
        }else {
            System.out.println("No pet selected cannot play");
        }

    }

    public void showCurrentPetStatus() {
        if (currentPet != null) {
            currentPet.status();
        }else {
            System.out.println("No pet selected cannot show status");
        }

    }

    

    public void displayPlayerInfo() {
        System.out.println("Player Name: " + this.playerName);
        if (currentPet != null) {
            System.out.println("Current Pet: " + currentPet.getName());
        } else {
            System.out.println("No pet selected.");
        }
    }

    public void trainCurrentPet() {
        if (currentPet != null) {
            if (currentPet instanceof Trainable) {
                System.out.println("What trick would you like to learn");
                String trickLearned = scanner.nextLine();
                ((Trainable) currentPet).learnTrick(trickLearned);  
            } else {
                System.out.println("This pet cannot be trained.\n");
            }
        } else {
            System.out.println("No pet selected to train.\n");
        }

    }

    public void useTrick() {
        if (currentPet instanceof Trainable) {
            Trainable trainablePet = (Trainable) currentPet;
            trainablePet.performTrick(); 
        } else {
            System.out.println("Your current pet cannot perform tricks.");
        }
    }

    public void currentPetNap() {
        if (currentPet != null) {
            currentPet.sleep();  
        } else {
            System.out.println("No pet selected to nap.");
        }

    }
    

}

