import java.util.Random;

/**
 * Classe base para os animais considerados na simulacao (ovelhas e lobos)
 * e que contem as caracteristicas basicas e as funcoes basicas para os movimentos
 * @author Ricardo
 *
 */

public class Animal {

	protected int posX, posY;
	protected float energy, maxEnergy;
	protected Random rand = new Random();
	protected double randVal;
	protected String name;


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
     * If energy <= 0, returns -1, else returns 0
 * @param posX
 * @param posY
     * @return -1 or 0
     */
    public int move(int posX, int posY){
        System.out.println("Move");
            this.posX = posX;
            this.posY = posY;

            this.energy -= 1;
            //System.out.println("Depois: " + posX + " " + posY);
            if(energy <= 0)
                    return -1;
                    //Matar o lobo
            else
                    return 0;
          
        
    }
    
    /** Método para o caso de a próxima posição ser "fora" do mapa
     * 
     * @param posX
     * @param posY 
     */
     public void toroidal(int posX, int posY)
    {
        //System.out.println("Toroidal");
        int max = World.dimention-1;
          if(posX > max && posY > max){
              this.posX = 0;
              this.posY = 0;
          }
          else if(posX < 0 && posY < 0){
              this.posX = max;
              this.posY = max;
          }
          else if(posX > max && posY < 0){
              this.posX = 0;
              this.posY = max;
          }
          else if(posX < 0 && posY > max){
              this.posX = max;
              this.posY = 0;
          }
          else if(posX > max){
              this.posX = 0;
              this.posY = posY;
          }
          else if(posX < 0){
              this.posX = max;
              this.posY = posY;
          }
          else if(posY > max){
              this.posY = 0;
              this.posX = posX;
          }
          else if(posY < 0){
              this.posY = max;
              this.posX = posX;
          }
          
        //System.out.println("Depois: " +this.posX + " " +this.posY);
    }

    public void moveFita()
    {
        //System.out.println("Antes: " +this.posX + " " +this.posY);
        int max = World.dimention-1;
        int random= rand.nextInt(8);
        System.out.println(name + " move to " + random);
        switch(random){
            case(0):
            {
                //System.out.println("Case0");
                if(inLimit(this.posX, this.posY, 0, max) == 0){
                    move(this.posX-1,this.posY+1);
                }
                else{
                    toroidal(this.posX - 1, this.posY + 1);
                }
                
                break;
            }
            case(1):
            {
                //System.out.println("Case1");
                if(inLimit(this.posX, this.posY, 1, max) == 0){
                    move(this.posX,this.posY+1);
                }
                else{
                    toroidal(this.posX,this.posY+1);
                }
                break;
            }
            case(2):
            {
                //System.out.println("Case2");
                if(inLimit(this.posX, this.posY, 2, max) == 0){
                    move(this.posX+1,this.posY+1);
                }
                else{
                    toroidal(this.posX+1,this.posY+1);
                }
                
                break;
            }
            case(3):
            {
                //System.out.println("Case3");
                if(inLimit(this.posX, this.posY, 3, max) == 0){
                    move(this.posX-1,this.posY);
                }
                else{
                    toroidal(this.posX-1,this.posY);
                }
                
                break;
            }
            case(4):
            {
                //System.out.println("Case4");
                if(inLimit(this.posX,this.posY, 4, max) == 0){
                    move(this.posX+1,this.posY);
                }
                else{
                    toroidal(this.posX+1,this.posY);
                }
                
                break;
            }
            case(5):
            {
                //System.out.println("Case5");
                if(inLimit(this.posX,this.posY , 5, max) == 0){
                    move(this.posX-1,this.posY-1);
                }
                else{
                    toroidal(this.posX-1,this.posY-1);
                }
                break;
            }
            case(6):
            {
                //System.out.println("Case6");
                if(inLimit(this.posX,this.posY , 6, max) == 0){
                    move(this.posX,this.posY-1);
                }
                else{
                    toroidal(this.posX,this.posY-1);
                }
                break;
            }
            case(7):
            {
                //System.out.println("Case7");
                if(inLimit(this.posX,this.posY , 7, max) == 0){
                    move(this.posX+1,this.posY-1);
                }
                else{
                    toroidal(this.posX+1, this.posY-1);
                }
                break;
            }
            default:
                break;
        }
    }
    
    /**
     * Método para ver se o animal está numa posição limite (ver se a posição seguint fica fora do mapa)
     * @param posX
     * @param posY
     * @return 
     */
    public int inLimit(int posX, int posY, int type, int max)
    {
        //System.out.println(posX + " " + posY);
        switch(type){
            case(0):
                if(posX == 0 || posY == max){
                    //System.out.println("No limite");
                    return 1;
                }
                else{
                    return 0;
                }
            case(1):
                //Check
                if(posY == max){
                    //System.out.println("No limite");
                    return 1;
                }
                else{
                    return 0;
                }
             case(2):
                 //Check
                 if(posX == max || posY == max){
                     //System.out.println("No limite");
                     return 1;
                 }
                 else{
                     return 0;
                 }
             case(3):
                 //Check
                 if(posX == 0){
                     //System.out.println("No limite");
                     return 1;
                 }
                 else{
                     return 0;
                 }
             case(4):
                 //Check
                 if(posX == max){
                     //System.out.println("No limite");
                     return 1;
                 }
                 else{
                     return 0;
                 }
             case(5):
                 if(posY == 0 || posX == 0){
                     //System.out.println("No limite");
                     return 1;
                 }
                 else{
                     return 0;
                 }
             case(6):
                 //Check
                 if(posY == 0){
                     //System.out.println("No limite");
                     return 1;
                 }
                 else{
                     return 0;
                 }
             case(7):
                 //Check
                 if(posY == 0 || posX == max){
                     //System.out.println("No limite");
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
