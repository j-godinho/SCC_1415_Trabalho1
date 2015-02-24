import java.util.Scanner;

/**
 * Classe que faz a simulacao
 * 
 * @author Ricardo
 *
 */


public class Simulation {
	private static int worldDimention = 6;
	private static int nWolfs = 2;
	private static int nSheeps = 2;
	private static float wolfMaxEnergy = 30;
	private static float sheepMaxEnergy = 7;
	private static World world;
	private static int maxTime = 20;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		world = new World(worldDimention, nWolfs, wolfMaxEnergy, nSheeps, sheepMaxEnergy);
		
		world.generateVegetation();
		world.generateSheeps();
		world.generateWolfs();
		
		int time = 0;
		
		System.out.println("STEP: " + time);
		
		printAll();
		
		while(time++ < maxTime){
			System.out.println("STEP: " + time);
			world.step();
			System.out.println("Lobos: " + world.getQuantityWolfs());
			System.out.println("Ovelhas: " + world.getQuantitySheeps());
            System.out.println("Vegetação: " + world.getQuantityVegetation());
            
            printAll();
            
            System.out.println("press key to continue...");
            sc.nextLine();
		}
		
		int nVegetation = world.getQuantityVegetation();
		
		System.out.println("Vegetation: " + nVegetation);
	}
	
	private static void printAll(){
		System.out.println("----- VEGETACAO -----");
		for(int l=0; l < World.dimention; l++){
			for(int c=0; c < World.dimention; c++){
				System.out.print(world.getVegetationMap()[l][c].getEnergy() + "$" + world.getVegetationMap()[l][c].getGrowthStage() + "\t");
			}
			System.out.println();
		}
		
		System.out.println("----- LOBOS -----");
		for(Wolf w : world.getWolfsMap()){
			System.out.println(w.getName() + "\n X: " + w.getPosX() + " Y: " + w.getPosY() 
					+ "\nEnergy: " + w.getEnergy() + "\n");
		}
		
		System.out.println("----- OVELHAS -----");
		for(Sheep s : world.getSheepsMap()){
			System.out.println(s.getName() + "\n X: " + s.getPosX() + " Y: " + s.getPosY() 
					+ "\nEnergy: " + s.getEnergy() + "\n");
		}
	}

}
