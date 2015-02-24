import java.util.Random;

/**
 * Classe para os objectos dos lobos.
 * 
 * A classe tem o seu proprio gerador de numeros aleatorios para a geracao dos lobos.
 * 
 * @author Ricardo
 *
 */

public class Wolf extends Animal{
	
	static private int wolfN = 1;
	
	private double randVal;
	
	Wolf(float maxEnergy, int worldDim){
		super();
		//this.rand = new Random();
		this.maxEnergy = maxEnergy;
		randVal = rand.nextDouble();
		energy = (float) (maxEnergy * randVal);
		randVal = rand.nextDouble();
		posX = (int) (worldDim * randVal);
		randVal = rand.nextDouble();
		posY = (int) (worldDim * randVal);
		this.setName("Wolf " + wolfN++);
		
	}
	
	Wolf(float energy, float maxEnergy, int posX, int posY){
		super();
		this.energy = energy;
		this.maxEnergy = maxEnergy;
		this.posX = posX;
		this.posY = posY;
	}
	
	/* #######################################
	 * 			METHODS
	 * #####################################*/
	
	/**
	 * Adds value to energy to a maximum of 30
	 * @param value
	 * @return energy
	 */
	public float addEnergy(float value){
		this.energy += value;
		
		if(energy > 30)
			energy = 30;
		
		return energy;
	}
	
	/**
    * Método para quando o lobo mata uma ovelha e ganha 20 de energia
    */
    public void killSheep()
    {
        setEnergy(getEnergy()+20);
    }
    
    /**
     * Divides energy of wolf by two (criation of another wolf)
     */
    public void halfEnergy()
    {
        this.energy=energy/2;
    }
    
    /*GETTERS AND SETTERS*/
    
    public float getEnergy() {
        return energy;
    }

    public void setEnergy(float energy) {
        this.energy = energy;
    }

    public float getMaxEnergy() {
        return maxEnergy;
    }

    public void setMaxEnergy(float maxEnergy) {
        this.maxEnergy = maxEnergy;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    /*
    public static Random getRand() {
        return rand;
    }

    public static void setRand(Random rand) {
        Wolf.rand = rand;
    }*/

    public double getRandVal() {
        return randVal;
    }

    public void setRandVal(double randVal) {
        this.randVal = randVal;
    }

    @Override
    public String toString() {
        return "Wolf{" + "energy=" + energy + ", maxEnergy=" + maxEnergy + ", posX=" + posX + ", posY=" + posY + ", randVal=" + randVal + '}';
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
