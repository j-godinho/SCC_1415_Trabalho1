import java.util.Random;

/**
 * Classe para os objectos ovelhas
 * 
 * A classe tem o seu proprio gerador de numeros aleatorios para a geracao das ovelhas.
 * 
 * @author Ricardo
 *
 */

public class Sheep extends Animal{	
	
	Sheep(float maxEnergy, int worldDim){
		super();
		//this.rand = new Random();
		this.maxEnergy = maxEnergy;
		randVal = rand.nextDouble();
		energy = (float) (maxEnergy * randVal);
		randVal = rand.nextDouble();
		posX = (int) (worldDim * randVal);
		randVal = rand.nextDouble();
		posY = (int) (worldDim * randVal);
		
	}
	
	Sheep(float energy, float maxEnergy, int posX, int posY){
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
	 * Adds value to energy to a maximum of 7
	 * @param value
	 * @return energy
	 */
	public float addEnergy(float value){
		this.energy += value;
		
		if(energy > 7)
			energy = 7;
		
		return energy;
	}
     
     
	public void halfEnergy(){
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
	 public Random getRand() {
	     return rand;
	 }
	
	 public void setRand(Random rand) {
	     Sheep.rand = rand;
	 }
	
	 public double getRandVal() {
	     return randVal;
	 }
	
	 public void setRandVal(double randVal) {
	     this.randVal = randVal;
	 }
	*/
}
