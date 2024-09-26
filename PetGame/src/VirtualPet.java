// Zafar Azad 19088536

public abstract class VirtualPet {
    private String name;
    private int hungerLevel;
    private int energyLevel;
    private int happinessLevel;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHungerLevel() {
        return hungerLevel;
    }

    public void setHungerLevel(int hungerLevel) {
        this.hungerLevel = hungerLevel;
    }

    public int getEnergyLevel() {
        return energyLevel;
    }

    public void setEnergyLevel(int energyLevel) {
        this.energyLevel = energyLevel;
    }

    public int getHappinessLevel() {
        return happinessLevel;
    }

    public void setHappinessLevel(int happinessLevel) {
        this.happinessLevel = happinessLevel;
    }



    public abstract void eat();
    public abstract void increaseHunger();

    public abstract void play();
    public abstract void decreaseHappiness();


    public abstract void sleep();
    public abstract void decreaseEnergy();


    public abstract void status();
}

