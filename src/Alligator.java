//Written by Ijaz (ID: 20112824)

//Alligators require more food and lose energy faster. They cannot be trained.

public class Alligator extends VirtualPet {

    private static final int HUNGER_DECREASE = 10;
    private static final int HUNGER_INCREASE = 10;
    private static final int ENERGY_INCREASE = 10;
    private static final int ENERGY_DECREASE = 10;
    private static final int HAPPINESS_INCREASE = 10;
    private static final int HAPPINESS_DECREASE = 10;
    
    //constructor
    public Alligator(String name, int hungerLevel, int energyLevel, int happinessLevel) {
        setName(name);
        setHungerLevel(hungerLevel);
        setEnergyLevel(energyLevel);
        setHappinessLevel(happinessLevel);
    }
    
    //adjusts energy when eating
    @Override
    public void eat() {
        if (getHungerLevel()>=10) {
            setHungerLevel(getHungerLevel()-HUNGER_DECREASE);
            System.out.println(getName()+" has finshed eating their food\n");
        }
        if(getHungerLevel()==0) {
            System.out.println(getName() + " now has a full belly and will explode if it keeps eating\n");
        }
        }
     
    //adjusts energy and happiness levels when playing
    @Override
    public void play() {
        if(getHappinessLevel()<100) {
            setHappinessLevel(getHappinessLevel()+HAPPINESS_INCREASE);
            System.out.println(getName()+" has finshed playing, they feel happy and relaxed\n");
        }
        if (getHappinessLevel()>=100) {
            System.out.println(getName()+" doesnt wish to play anymore\n");
        }

        }
    
    //adjusts energy levels when sleeping
    @Override
    public void sleep() {
        if(getEnergyLevel()<100) {
            setEnergyLevel(getEnergyLevel()+ENERGY_INCREASE);
            System.out.println(getName()+" has taken a nap and feels refreshed\n");
        }
        if(getEnergyLevel()>=100) {
            System.out.println(getName()+" is enegerised and doesnt want to nap right now\n");
        }
    }
    
    //indicates when pet is tired
    @Override
    public void decreaseEnergy() {
        if(getEnergyLevel()>0) {
            setEnergyLevel(getEnergyLevel()-ENERGY_DECREASE);
            System.out.println(getName()+" is getting tired\n");
            if (getEnergyLevel()<=10) {
                System.out.println(getName()+" really needs to take a nap\n");
            }
        }
                
    }
    
    //gets the status of a pet
    @Override
    public void status() {
        System.out.println(getName()+"\n"+"Enegry levels = "+getEnergyLevel()+"\nHunger levels = "+getHungerLevel()
        +"\nHappiness level = "+getHappinessLevel());
    }
    
    //indicates when pet is hungry
    @Override
    public void increaseHunger() {
        if (getHungerLevel()<90) {
            setHungerLevel(getHungerLevel()+HUNGER_INCREASE);
            System.out.println(getName()+" is getting hungry\n");
            if (getHungerLevel()>=95) {
                System.out.println(getName()+" is extreamly hungry, feed them now!\n");
            }
        }
                
    }
    
    //indicates when pet is sad
    @Override
    public void decreaseHappiness() {
        if (getHappinessLevel()<90) {
            setHappinessLevel(getHappinessLevel()-HAPPINESS_DECREASE);
            if (getHappinessLevel()<=10) {
                System.out.println(getName()+" is very sad play with them now!\n");
            }            
        }
    }

}
