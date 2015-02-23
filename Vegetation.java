import java.util.Random;

/**
 * Classe para o objecto da Vegetacao
 * 
 * A classe tem o seu proprio gerador de numeros aleatorios para a geracao dos objectos de vegetacao.
 * 
 * @author Ricardo
 *
 */

public class Vegetation {
	private int energy, maxEnergy;
	private int growthStage, maxGrowthStage;
	private static Random rand = new Random();
	private double randVal;
	
	Vegetation(int maxEnergy, int maxGrowthStage){
		this.maxEnergy = maxEnergy;
		this.maxGrowthStage = maxGrowthStage;
		randVal = rand.nextDouble();
		growthStage = (int) (maxGrowthStage * randVal);
		
		if(growthStage == maxGrowthStage){
			energy = maxEnergy;
		}
	}
	
	Vegetation(int energy, int maxEnergy, int growthStage){
		this.energy = energy;
		this.maxEnergy = maxEnergy;
		this.growthStage = growthStage;
	}
	
	public void grow(){
		if(energy == 0){
			growthStage++;
			if(growthStage == maxGrowthStage)
				energy = maxEnergy;
		}
		
	}
	
	public void beEaten(){
		energy = 0;
		growthStage = 0;
	}
	
	/*GETTERS AND SETTERS*/
    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public void setMaxEnergy(int maxEnergy) {
        this.maxEnergy = maxEnergy;
    }

    public int getGrowthStage() {
        return growthStage;
    }

    public void setGrowthStage(int growthStage) {
        this.growthStage = growthStage;
    }

    public int getMaxGrowthStage() {
        return maxGrowthStage;
    }

    public void setMaxGrowthStage(int maxGrowthStage) {
        this.maxGrowthStage = maxGrowthStage;
    }

    public static Random getRand() {
        return rand;
    }

    public static void setRand(Random rand) {
        Vegetation.rand = rand;
    }

    public double getRandVal() {
        return randVal;
    }

    public void setRandVal(double randVal) {
        this.randVal = randVal;
    }
}
