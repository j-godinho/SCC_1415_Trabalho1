import java.util.Random;


public class Animal {
	
	protected int posX, posY;
	protected float energy, maxEnergy;
	protected Random rand = new Random();
	protected double randVal;
	
	
	/*
	 * Aceder 'as dimensoes do mundo directamente da classe World
	 * atraves da variavel static dimention
	 */
	
	
	public void moveToPos(int posX, int posY){
		this.posX = posX;
		this.posY = posY;
		this.energy -= 1.0;
	}
	
	/* ################################################# 
	 * 
	 * 		FUNCOES PARA MOVIMENTO - a rever
	 * 
	 * ################################################# */
	
	/**
     * Moves the wolf to the given position and decrements his energy.
	 * @param posX
	 * @param posY
     */
    public void move(int posX, int posY){
            this.posX = posX;
            this.posY = posY;

            this.energy -= 1;
            
            System.out.println("Depois: " +this.posX + " " +this.posY);
    }
    
    /**
     * 
     * @param posX
     * @param posY 
     */
     public void toroidal(int posX, int posY)
    {
        int max = 2;
          if(posX > World.dimention-1){
              this.posX = 0;
              this.posY = posY;
          }
          else if(posX < 0){
              this.posX = World.dimention-1;
              this.posY = posY;
          }
          else if(posY > World.dimention-1){
              this.posY = 0;
              this.posX = posX;
          }
          else if(posY < 0){
              this.posY = World.dimention-1;
              this.posX = posX;
          }
        System.out.println("Depois: " +this.posX + " " +this.posY);
    }

    public void moveFita()
    {
        System.out.println("Antes: " +this.posX + " " +this.posY);
        int max = 3;
        int random=rand.nextInt(8);
        switch(random){
            case(0):
            {
                if(inLimit(posX, posY, 0, World.dimention-1) == 0){
                    move(posX-1,posY+1);
                }
                else{
                    toroidal(posX - 1, posY + 1);
                }
                
                break;
            }
            case(1):
            {
                if(inLimit(posX, posY, 1, max) == 0){
                    move(posX,posY+1);
                }
                else{
                    toroidal(posX,posY+1);
                }
                break;
            }
            case(2):
            {
                if(inLimit(posX, posY, 2, max) == 0){
                    move(posX+1,posY+1);
                }
                else{
                    toroidal(posX+1,posY+1);
                }
                
                break;
            }
            case(3):
            {
                if(inLimit(posX, posY, 3, max) == 0){
                    move(posX-1,posY);
                }
                else{
                    toroidal(posX-1,posY);
                }
                
                break;
            }
            case(4):
            {
                if(inLimit(posX+1,posY, 4, max) == 0){
                    move(posX+1,posY);
                }
                else{
                    toroidal(posX+1,posY);
                }
                
                break;
            }
            case(5):
            {
                if(inLimit(posX-1,posY-1 , 5, max) == 0){
                    move(posX-1,posY-1);
                }
                else{
                    toroidal(posX-1,posY-1);
                }
                break;
            }
            case(6):
            {
                if(inLimit(posX,posY-1 , 6, max) == 0){
                    move(posX,posY-1);
                }
                else{
                    toroidal(posX,posY-1);
                }
                break;
            }
            case(7):
            {
                if(inLimit(posX+1,posY-1 , 7, max) == 0){
                    move(posX+1,posY-1);
                }
                else{
                    toroidal(posX+1, posY-1);
                }
                break;
            }
            default:
                break;
        }
    }
    
    /**
     * Method to check if the wolf is in the limit of the map
     * @param posX
     * @param posY
     * @return 
     */
    public int inLimit(int posX, int posY, int type, int max)
    {
        switch(type){
            case(0):
                if((posX == 0 && posY == max) || (posY == max) || (posX == max)){
                    return 1;
                }
                else{
                    return 0;
                }
            case(1):
                if(posY == max && posX != 0 && posX != max){
                    return 1;
                }
                else{
                    return 0;
                }
             case(2):
                 if((posY == max && (posX == 0 || posX == max)) || (posX == max)){
                     return 1;
                 }
                 else{
                     return 0;
                 }
             case(3):
                 if(posX == 0 && (posY != 0 || posY != max)){
                     return 1;
                 }
                 else{
                     return 0;
                 }
             case(4):
                 if(posX == max && (posY != 0 || posY != max)){
                     return 1;
                 }
                 else{
                     return 0;
                 }
             case(5):
                 if((posX == 0 && posY == 0) || (posX == 0 && (posY != 0 || posY != max)) || (posY == 0 && (posX != 0 || posX != max))){
                     return 1;
                 }
                 else{
                     return 0;
                 }
             case(6):
                 if(posX != max && (posY == 0 && posY != max)){
                     return 1;
                 }
                 else{
                     return 0;
                 }
             case(7):
                 if((posX == 50 && posY == 0) && (posY == 0 && (posX != 0 && posX != max))){
                     return 1;
                 }
                 else{
                     return 0;
                 }
        }                
        return 0;
    }
    
    
    /* ################################################# 
	 * 
	 * 		GETTERS / SETTERS
	 * 
	 * ################################################# */
    

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
	
	

	
}
