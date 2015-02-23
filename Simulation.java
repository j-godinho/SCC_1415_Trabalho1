/**
 * Classe que faz a simulacao
 * 
 * @author Ricardo
 *
 */


public class Simulation {
	private static int worldDimention = 3;
	private static int nWolfs = 2;
	private static int nSheeps = 1;
	private static float wolfMaxEnergy = 30;
	private static float sheepMaxEnergy = 7;
	private static World world;
	private static int maxTime = 10;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		world = new World(worldDimention, nWolfs, wolfMaxEnergy, nSheeps, sheepMaxEnergy);
		
		world.generateVegetation();
		world.generateSheeps();
		world.generateWolfs();
		
		int time = 0;
		
		while(time++ < maxTime){
			System.out.println("STEP: " + time);
			world.step();
			System.out.println("Lobos: " + world.getWolfsMap().size());
			System.out.println("Ovelhas: " + world.getSheepsMap().size());
            System.out.println("Vegetação: " + world.getVegetation());
		}
		
		int nVegetation = world.getVegetation();
		
		System.out.println("Vegetation: " + nVegetation);
	}

}
