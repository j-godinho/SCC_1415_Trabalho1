import java.util.ArrayList;
import java.util.Random;

/**
 * Classe para o objecto do mundo.
 * Contem uma matriz com a vegetacao e dois arraylist para as ovelhas e lobos.
 * Foi escolhido os arraylist por ser mais facil de lidar do que com uma matriz de array
 * @author Ricardo
 *
 */

public class World {
	private Random rand = new Random();
	
	static public int dimention;			// dimensao do mundo
	
	private int startWolfs, startSheeps;	// numero inicial de lobos e ovelhas
	static public float wolfMaxEnergy, sheepMaxEnergy;		//energia maxima dos lobos e das ovelhas
	
	private Vegetation[][] vegetationMap;			//matriz com a vegetacao
	private ArrayList<Wolf> wolfsMap;				//arraylist dos lobos
	private ArrayList<Sheep> sheepsMap;				//arraylist das ovelhas
	
	World(int dimention, int startWolfs, float wolfMaxEnergy, int startSheeps, float sheepMaxEnergy){
		World.dimention = dimention;
		this.startWolfs = startWolfs;
		this.startSheeps = startSheeps;
		World.wolfMaxEnergy = wolfMaxEnergy;
		World.sheepMaxEnergy = sheepMaxEnergy;
		this.vegetationMap = new Vegetation[dimention][dimention];
		this.wolfsMap = new ArrayList<Wolf>();
		this.sheepsMap = new ArrayList<Sheep>();
	}
	
	/**
	 * Faz a geracao inicial da vegetacao
	 */
	public void generateVegetation(){
		for(int l=0; l<dimention; l++){
			for(int c=0; c<dimention; c++){
				vegetationMap[l][c] = new Vegetation(4, 10);
			}
		}
	}
	
	/**
	 * Faz a geracao inicial dos lobos
	 */
	public void generateWolfs(){
		for(int w=0; w<startWolfs; w++){
			wolfsMap.add(new Wolf(wolfMaxEnergy, dimention));
		}
	}
	
	/**
	 * Faz a geracao inicial das ovelhas
	 */
	public void generateSheeps(){
		for(int s=0; s<startSheeps; s++){
			sheepsMap.add(new Sheep(sheepMaxEnergy, dimention));
		}
	}
	
	/**
	 * Funcao para fazer o sistema avancar uma unidade de tempo
	 * 
	 * !!!!!!!!!!!!!!! POR FINALIZAR !!!!!!!!!!!!!!!!!!!
	 */
	public void step(){
		// araylists temporarios usados para a criacao de novos animais
		ArrayList <Wolf> newWolfs= new ArrayList <Wolf>();
		ArrayList <Sheep> newSheeps= new ArrayList <Sheep>();
		
		// crescer vegetacao
		for(int l=0; l<dimention; l++){
			for(int c=0; c<dimention; c++){
				vegetationMap[l][c].grow();
			}
		}
		
		// mover animais
		//  lobos
		for (Wolf w : wolfsMap) {
            w.moveFita();
        }
		
		//  ovelhas
		for (Sheep s : sheepsMap) {
            s.moveFita();
        }
		
		// verificar mortes
		removeAnimalsZeroEnergy();
		
		// ovelhas comem vegetacao
		eatVegetation();
		
		// ovelhas procriam
		/*
		for (Sheep s : sheepsMap) {
            newSheep(s, newSheeps);
        }
        sheepsMap.addAll(newSheeps);
        newSheeps.clear();
		*/
		// lobos comem ovelhas
        //killSheeps();
		
		// lobos procriam
		/*
		for (Wolf w : wolfsMap) {
            newWolf(w, newWolfs);
        }
        wolfsMap.addAll(newWolfs);
        newWolfs.clear();
		*/
	}
	
	/**
     * Função para a criação de uma cria a partir de um lobo
     * @param wolf 
     * @param temp 
     * !!!!!!!!!!!!!!!!!!POR FINALIZAR!!!!!!!!!!!!!!!!!!!!!!
     */
    public void newWolf(Wolf wolf, ArrayList <Wolf> newWolfs)
    {
        if(rand.nextDouble()<=0.05)
        {
        	Wolf wTemp = new Wolf(wolf.getEnergy()/2,wolf.getMaxEnergy(),wolf.getPosX(),wolf.getPosY());
            newWolfs.add(wTemp);
            wolf.halfEnergy();
            System.out.println(wolf.getName() + " gerou um novo lobo: " + wTemp.getName());
        }
    }

    public void newSheep(Sheep sheep,ArrayList <Sheep> newSheeps)
    {
        if(rand.nextDouble()<=0.04)
        {
        	Sheep sTemp = new Sheep(sheep.getEnergy()/2,sheep.getMaxEnergy(), sheep.getPosX(),sheep.getPosY());
            newSheeps.add(sTemp);
            sheep.halfEnergy();
            System.out.println(sheep.getName() + " gerou uma nova ovelha: " + sTemp.getName());
        }
    }
    
    /**
     * Método para percorrer os arrayLists dos animais, e matar as ovelhas que
     * estão no mesmo quadrado que os lobos
     */
    public void killSheeps(){
    	ArrayList<Wolf> wolfsInSquare = new ArrayList<Wolf>();
    	ArrayList<Sheep> sheepsToKill = new ArrayList<Sheep>();
    	
    	for(Sheep s : sheepsMap){
    		for(Wolf w : wolfsMap){
    			if(s.getPosX() == w.getPosX() && s.getPosY() == w.getPosY()){
    				wolfsInSquare.add(w);
    			}
    		}
    		
    		if(!wolfsInSquare.isEmpty()){
    			for(Wolf w : wolfsInSquare){
    				w.addEnergy(sheepMaxEnergy/wolfsInSquare.size());
    			}
    			sheepsToKill.add(s);
    			wolfsInSquare.clear();
    		}
    	}
    	
    	for(Sheep s : sheepsToKill)
    		sheepsMap.remove(s);
    }
    
    /**
     *  Método para percorrer os arrays das ovelhas e da vegetação, para fazer a
     *  alimentação das ovelhas
     */
    public void eatVegetation()
    {   
        ArrayList<Sheep> sheepsToEat = new ArrayList <Sheep>();	//ovelhas que vao comer a vegetacao numa dada posicao
        
        for(int l = 0; l<dimention; l++)
        {
            for(int c=0; c<dimention; c++)
            {
                for(Sheep s : sheepsMap)
                {
                    if(s.getPosX()==l && s.getPosY()==c)
                    {
                    	sheepsToEat.add(s);  
                    }
                }
                
                if(!sheepsToEat.isEmpty())
                {
                    vegetationMap[l][c].beEaten();
                    divideEnergy(sheepsToEat);
                    sheepsToEat.clear();
                }
            }
        }
    }
    
    /**
     * Método para dividir a energia pelas ovelhas que se encontram no mesmo quadrado
     * @param temp 
     */
    public void divideEnergy(ArrayList <Sheep> sheepsToEat)
    {
        for (Sheep s : sheepsToEat) {
            s.addEnergy(sheepMaxEnergy/sheepsToEat.size());
        }
        
    }
    
    /**
     * Método para remover todos os animais com energia inferior ou igual a zero
     */
    public void removeAnimalsZeroEnergy()
    {
    	// arrays com os animais a remover
    	ArrayList<Wolf> wolfsToRemove = new ArrayList<Wolf>();
    	ArrayList<Sheep> sheepsToRemove = new ArrayList<Sheep>();
    	
    	// selecciona lobos a remover
        for(Wolf w : wolfsMap)
        {
            if(w.getEnergy()<=0)
            {
                wolfsToRemove.add(w);
            }
        }
        
        // remove lobos do array principal
        for(Wolf w : wolfsToRemove){
        	System.out.println(w.getName() + " morreu");
        	wolfsMap.remove(w);
        }
        
        // selecciona ovelhas a remover
        for(Sheep s : sheepsMap)
        {
            if(s.getEnergy()<=0)
            {
                sheepsToRemove.add(s);
            }
        }
        
        // remove ovelhas
        for(Sheep s : sheepsToRemove){
        	System.out.println(s.getName() + " morreu");
        	sheepsMap.remove(s);
        }
        
        
    }
	
	/**
	 * Devolve a quantidade de objectos de vegetacao com energia maior que zero
	 * @return
	 */
	public int getQuantityVegetation(){
		int nVeg = 0;
		for(int l=0; l<dimention; l++){
			for(int c=0; c<dimention; c++){
				if(vegetationMap[l][c].getEnergy() == 4)
					nVeg++;
			}
		}
		
		return nVeg;
	}
	
	/**
	 * Devolve a quantidade de ovelhas vivas no sistema
	 * @return
	 */
	public int getQuantitySheeps(){
		return sheepsMap.size();
	}
	
	/**
	 * Devolve a quantidade de lobos vivos no sistema
	 * @return
	 */
	public int getQuantityWolfs(){
		return wolfsMap.size();
	}
	
	/*GETTERS AND SETTERS*/
    public Random getRand() {
        return rand;
    }

    public void setRand(Random rand) {
        this.rand = rand;
    }

    public int getStartWolfs() {
        return startWolfs;
    }

    public void setStartWolfs(int startWolfs) {
        this.startWolfs = startWolfs;
    }

    public int getStartSheeps() {
        return startSheeps;
    }

    public void setStartSheeps(int startSheeps) {
        this.startSheeps = startSheeps;
    }

    public Vegetation[][] getVegetationMap() {
        return vegetationMap;
    }

    public void setVegetationMap(Vegetation[][] vegetationMap) {
        this.vegetationMap = vegetationMap;
    }

    public ArrayList<Wolf> getWolfsMap() {
        return wolfsMap;
    }

    public void setWolfsMap(ArrayList<Wolf> wolfsMap) {
        this.wolfsMap = wolfsMap;
    }

    public ArrayList<Sheep> getSheepsMap() {
        return sheepsMap;
    }

    public void setSheepsMap(ArrayList<Sheep> sheepsMap) {
        this.sheepsMap = sheepsMap;
    }
}
