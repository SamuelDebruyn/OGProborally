package roborally.model;

import java.util.Set;
import java.util.TreeSet;

import be.kuleuven.cs.som.annotate.Basic;
import roborally.basics.Energy;
import roborally.basics.Orientation;
import roborally.basics.Position;
import roborally.interfaces.IRobot;
import roborally.utils.BatteryComparator;

/**
 * Een klasse om robots voor te stellen.
 * 
 * @author 	Bavo Goosens (1e bachelor informatica, r0297884), Samuel Debruyn (1e bachelor informatica, r0305472)
 * 
 * @version 2.0
 */
public class Robot implements IRobot{

	/**
	 * De energiekost van 1 move.
	 */
	public final static int MOVE_COST = 500;
	/**
	 * De energiekost van 1 draai.
	 */
	public final static int TURN_COST = 100;
	/**
	 * De minimale energie van een robot.
	 */
	public final static int MINENERGY = 0;
	/**
	 * De maximale energie van een robot.
	 */
	public final static int MAXENERGY = 20000;

	/**
	 * De positie van de robot. Null indien deze niet op het bord staat.
	 */
	private Position position;
	/**
	 * De energie van de robot.
	 */
	private Energy energy;
	/**
	 * De ori�ntatie van de robot.
	 */
	private Orientation orientation;
	/**
	 * Geeft weer of de robot getermineerd is of niet.
	 */
	private boolean isTerminated = false;
	
	private BatteryComparator bc;
	
	private Set<Battery> Possessions = new TreeSet<Battery>(bc);
	
	private Board board;

	/**
	 * Deze methode maakt een nieuwe robot aan.
	 * 
	 * @param	orientation
	 * 			De initi�le ori�ntatie van de robot.
	 * 
	 * @param	energy
	 * 			De initi�le ori�ntatie van de robot.
	 * 
	 * @post	De energie van de robot is gelijk aan de opgegeven energie.
	 * 			|new.getEnergy().equals(energy)
	 * 
	 * @post	De ori�ntatie van de robot is gelijk aan de opgegeven ori�ntatie.
	 * 			|new.getOrientation().equals(orientation)
	 */
	public Robot(Orientation orientation, Energy energy){
		setEnergy(energy);
		setOrientation(orientation);
	}
	/**
	 * Methode die het board instelt waartoe deze Robot behoort.
	 * 
	 * @param 	board
	 * 			Het board waarop deze robot zich bevindt.
	 */
	public void setBoard(Board board){
		this.board = board;
	}

	/**
	 * Methode die het board teruggeeft waarop deze robot zich bevindt. Deze methode kan ook null teruggeven wat wil zeggen dat de robot zich niet op een board bevindt.
	 * 
	 * @return	Board of null
	 * 			Het board waarop de robot zich bevindt of null als de robot niet op een board staat;
	 */
	public Board getBoard(){
		return this.board;
	}
	/**
	 * Methode om de ori�ntatie van een robot te wijzigen.
	 *  
	 * @param 	or
	 * 			De ori�ntatie die de robot moet krijgen.
	 * 
	 * @post	De direction van de robot is gelijk aan de gegeven parameter.
	 * 			|new.getOrientation() == or
	 */
	private void setOrientation(Orientation or) {
		this.orientation = or;
	}

	/**
	 * Methode om de ori�ntatie van de robot te verkrijgen.
	 * 
	 * @return 	De ori�ntatie van de robot.
	 * 			|new.orientation 			
	 */
	@Basic
	public Orientation getOrientation(){
		return orientation;
	}

	/**
	 * Methode om de energie van een robot te wijzigen.
	 * 
	 * @param	newEnergy
	 * 			De energie die de robot moet krijgen.
	 * 
	 * @post 	De energie van de robot is newEnergy.
	 * 			|new.getEnergy() == newEnergy
	 */
	private void setEnergy(Energy newEnergy) {
		this.energy = newEnergy;
	}

	/**
	 * Methode om de energie van de robot te verkrijgen.
	 * 
	 * @return 	De energie van de robot.
	 * 			|new.energy
	 */
	@Basic
	public Energy getEnergy(){
		return energy;
	}

	/**
	 * Methode om de positie van een robot te wijzigen.
	 * 
	 * @param 	position
	 * 			Nieuwe positie van de robot
	 * 
	 * @post 	De positie van de robot is gelijk aan de gegeven positie.
	 * 			|new.getPosition().getPosition() == position
	 */
	private void setPosition(Position position){
		this.position = position;
	}

	/**
	 * Methode om de positie van de robot te verkrijgen.
	 * 
	 * @return	De positie van de robot.
	 * 			|new.position
	 */
	@Basic
	public Position getPosition(){
		return position;
	}

	/**
	 * Methode die controleert of de opgegeven hoeveelheid energie een geldige hoeveelheid is.
	 * 
	 * @param 	energy
	 * 			De hoeveelheid energie.
	 *
	 * @return	boolean
	 * 			|(amount >= MINENERGY) && (amount <= MAXENERGY)
	 */
	public static boolean isValidRobotEnergyAmount(Energy energy){
		return (energy.getEnergy() >= MINENERGY) && (energy.getEnergy() <= MAXENERGY);
	}

	/**
	 * Deze methode berekent de verhouding tussen de huidige hoeveelheid energie en de maximale hoeveelheid energie.
	 * 
	 * @return	De verhouding tussen de huidige hoeveelheid energie en de maximale hoeveelheid energie.
	 * 			|new.getEnergy().getEnergy()/MAXENERGY
	 */
	public double getEnergyFraction(){
		return this.getEnergy().getEnergy()/MAXENERGY;
	}

	/**
	 * Draait de robot 1 keer in wijzerzin.
	 * 
	 * @post	De nieuwe ori�ntatie van de robot is gelijk aan de volgende ori�ntatie in wijzerzin.
	 * 			|if(isValidRobotEnergyAmount(new Energy(this.getEnergy().getEnergy() - TURN_COST)))
	 * 			|	new.getOrientation() == this.getOrienation().getClockwiseOrientation()
	 * @post	De energie van de robot is verminderd met benodigde energie voor een draai.
	 * 			|if(isValidRobotEnergyAmount(new Energy(this.getEnergy().getEnergy() - TURN_COST)))
	 * 			|	new.getEnergy().getEnergy().equals(this.getEnergy().getEnergy() - TURN_COST)
	 */
	public void turnClockWise(){
		//TODO
		if(isValidRobotEnergyAmount(new Energy(this.getEnergy().getEnergy() - TURN_COST))){
			this.setOrientation(this.getOrientation().getClockwiseOrientation());
			this.getEnergy().setEnergy(this.getEnergy().getEnergy() - TURN_COST);
		}
	}

	/**
	 * Draait de robot 1 keer in tegenwijzerzin.
	 * 
	 * @post	De nieuwe ori�ntatie van de robot is gelijk aan de volgende ori�ntatie in wijzerzin.
	 * 			|if(isValidRobotEnergyAmount(new Energy(this.getEnergy().getEnergy() - TURN_COST)))
	 * 			|	new.getOrientation() == this.getOrienation().getCounterClockwiseOrientation()
	 * @post	De energie van de robot is verminderd met benodigde energie voor een draai.
	 * 			|if(isValidRobotEnergyAmount(new Energy(this.getEnergy().getEnergy() - TURN_COST)))
	 * 			|	new.getEnergy().getEnergy().equals(this.getEnergy().getEnergy() - TURN_COST)
	 */
	public void turnCounterClockWise(){
		//TODO
		if(isValidRobotEnergyAmount(new Energy(this.getEnergy().getEnergy() - TURN_COST))){
			this.setOrientation(this.getOrientation().getCounterClockwiseOrientation());
			this.getEnergy().setEnergy(this.getEnergy().getEnergy() - TURN_COST);
		}
	}
	
	/**
	 * Deze methode beweegt de robot een stap vooruit indien mogelijk.
	 */
	public void move(){
		//TODO
	}
	
	/**
	 * Deze methode geeft de energie terug die nodig is om een bepaalde plaats te bereiken.
	 * 
	 * @param 	position
	 * 			De plaats die bereikt moet worden.
	 * 
	 * @return	De energie die nodig is om de plaats te bereiken.
	 */
	public Energy getEnergyRequiredToReach(Position position){
		//TODO
		return null;
	}
	
	/**
	 * Deze methode verplaatst de robot zo dicht mogelijk bij een andere robot.
	 * 
	 * @param	robot
	 * 			De robot waar naartoe moet bewogen worden.
	 */
	public void moveNextTo(Robot robot){
		//TODO
	}
	
	/**
	 * Deze methode doet een robot schieten met zijn laser.
	 * 
	 * @effect	Mogelijks wordt een object op het bord geraakt en verwijderd.
	 */
	public void shoot(){
		if(this.getPosition() != null){
			//TODO
		}else{
			System.err.println("Robot staat niet op een bord!");
		}
	}
	
	/**
	 * Deze methode vernietigt een robot.
	 * 
	 * @post	De robot bevindt zich niet op een bord.
	 * 			|new.getPosition() == nulls
	 * @post	De robot is vernietigd.
	 * 			|new.isDestroyed()
	 */
	public void destroy(){
		//TODO: remove from board
		this.isTerminated = true;
		this.setPosition(null);
	}

	public void recharge(){
		//TODO
	}
	
	public int moveCost(){
		//TODO
		return MOVE_COST;
	}
	
	public Set<Battery> getPossessions(){
		return this.Possessions;
	}

	public void pickUp(Battery battery) {
		// TODO Auto-generated method stub
		
		
	}

	public void use(Battery battery) {
		// TODO Auto-generated method stub
		
	}

	public void drop(Battery battery) {
		// TODO Auto-generated method stub
		
	}
	
	

}