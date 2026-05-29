import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 *
 * @author Sjaak Smetsers & Renske Smetsers-Weeda
 * @version 3.0 -- 20-01-2017
 */
public class MyDodo extends Dodo
{
    private int myNrOfEggsHatched;
    
    public MyDodo() {
        super( EAST );
        myNrOfEggsHatched = 0;
    }

    public void act() {
    }

    /**
     * Move one cell forward in the current direction.
     * 
     * <P> Initial: Dodo is somewhere in the world
     * <P> Final: If possible, Dodo has moved forward one cell
     *
     */
    public void move() {
        if ( canMove() ) {
            step();
        } else {
            showError( "I'm stuck!" );
        }
    }

    /**
     * Test if Dodo can move forward, (there are no obstructions
     *    or end of world in the cell in front of her).
     * 
     * <p> Initial: Dodo is somewhere in the world
     * <p> Final:   Same as initial situation
     * 
     * @return boolean true if Dodo can move (no obstructions ahead)
     *                 false if Dodo can't move
     *                      (an obstruction or end of world ahead)
     */
    public boolean canMove() {
        if ( borderAhead() || fenceAhead()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Hatches the egg in the current cell by removing
     * the egg from the cell.
     * Gives an error message if there is no egg
     * 
     * <p> Initial: Dodo is somewhere in the world. There is an egg in Dodo's cell.
     * <p> Final: Dodo is in the same cell. The egg has been removed (hatched).     
     */    
    public void hatchEgg () {
        if ( onEgg() ) {
            pickUpEgg();
            myNrOfEggsHatched++;
        } else {
            showError( "There was no egg in this cell" );
        }
    }
    
    /**
     * Returns the number of eggs Dodo has hatched so far.
     * 
     * @return int number of eggs hatched by Dodo
     */
    public int getNrOfEggsHatched() {
        return myNrOfEggsHatched;
    }
    
    /**
     * Move given number of cells forward in the current direction.
     * 
     * <p> Initial:   
     * <p> Final:  
     * 
     * @param   int distance: the number of steps made
     */
    public void jump( int distance ) {
        int nrStepsTaken = 0;               // set counter to 0
        while ( nrStepsTaken < distance ) { // check if more steps must be taken  
            move();                         // take a step
            nrStepsTaken++;                 // increment the counter
            System.out.println("moved " + nrStepsTaken); // print to console
        }
    }

    
    
    
    /**
     * Walks to edge of the world printing the coordinates at each step
     * 
     * <p> Initial: Dodo is on West side of world facing East.
     * <p> Final:   Dodo is on East side of world facing East.
     *              Coordinates of each cell printed in the console.
     */

    public void walkToWorldEdgePrintingCoordinates( ){
        while( ! borderAhead() ){
            System.out.println("x: " + getX() + ", y: " + getY()); //print cooardinates
            move(); //move
        }
    }

    /**
     * Test if Dodo can lay an egg.
     *          (there is not already an egg in the cell)
     * 
     * <p> Initial: Dodo is somewhere in the world
     * <p> Final:   Same as initial situation
     * 
     * @return boolean true if Dodo can lay an egg (no egg there)
     *                 false if Dodo can't lay an egg
     *                      (already an egg in the cell)
     */

        public boolean canLayEgg( ){
        if( onEgg() ){
            return false; // er ligt al een ei dus kan geen ei leggen
        }else{
            return true; // geen ei aanwezig dus kan wel een ei leggen
        }
    }
    /**
     * Makes mimi turn around to the opposite direction
     */
    
        public void turn180() {
        turnRight(); // draait een kwartslag rechts
        turnRight(); // tweede kwartslag rechts dus nu 180 graden gedraaid
    }
    /**
     * Mimi can climb over fence if there is a fence ahead
     */
        public void climbOverFence() {
        turnLeft();  // draai naar boven
        move(); // stap omhoog 
        turnRight(); // draai naar rechts
        move(); // stap over het hek
        move(); // een stap verder
        turnRight(); // draai naar beneden
        move(); // stap omlaag     
        turnLeft(); // draai terug naar rechts
    }
    /**
     * checks if there is a grain ahead. if there is a grain ahead, 
     * you get the result true
     */
        public boolean grainAhead() {
        move(); // stap vooruit
        boolean result = onGrain(); // check grain
        // terug naar beginpositie
        stepOneCellBackwards(); // terug naar beginpositie

        return result;
    }
    /**
     * mimi will keep walking until she finds and egg, when she find an egg
     * she stops.
     */
    public void gotoEgg() {
    while (!onEgg()) { // nog niet op een ei
        move(); // zet een stap       
    }
    }
    /**
     * Mimi will turn around and walk to the edge of the world, and afterwards
     * turn around to the original direction
     */
    public void goBackToStartOfRowAndFaceBack() {
    turn180();// draai om
    walkToWorldEdgePrintingCoordinates();// loop naar het begin van de rij
    turn180();// draai terug naar originele richting
    }
    /**
     * Mimi will keep moving until she finds a fence, when she finds a fence 
     * she will climb over it.
     */
    public void walkToWorldEdgeClimbingOverFences() {
    while (!borderAhead()) {        // zolang geen rand voor Mimi
        if (fenceAhead()) {
            climbOverFence();       // klim erover
        } else {
            move();                 // stap vooruit
        }
    }
    }
    /**
     * Mimi will walk to the edge of the world and look for grains, when she
     * walks over the grains the cooardinates will be printed
     */
    public void pickUpGrainsAndPrintCoordinates() {
    // check huidige cel (eerste cel)
    if (onGrain()) {
        pickUpGrain();
        System.out.println("x: " + getX() + ", y: " + getY());
    }
    while (!borderAhead()) {
        move();
        if (onGrain()) {
            pickUpGrainsAndPrintCoordinates();
        }
    }
    }
    /**
     * Mimi will turn 180 degress and take a step backwards, afterwards take
     * another 180 degrees turn and face the original direction.
     */
    public void stepOneCellBackwards() {
    turn180(); // 180 graden draaien
    move();    // een stap zetten
    turn180(); // weer 180 graden draaien
    }
    /**
     * Mimi will walk to the edge of the world and lay eggs on nests she
     * finds ahead.
     */
    public void worldEmptyNestsTopRow(){
    // check huidige cel eerst
    while (!borderAhead()) {
        move();
        if (onNest() && !onEgg()) {
            layEgg();
        }
    }
    }
    /**
     * Mimi will look for nests and lay eggs on them. she will climb fences
     * when she finds fences ahead.
     */
    public void walkToNestClimbingOverFences() {
    while (!onNest()) {
        if (fenceAhead()) {
            climbOverFence();
        } else {
            move();
        }
    }
    layEgg(); // ei leggen als er een nest wordt gevonden
    }
    /**
     * Mimi will lay an egg and walk around the fenced area and stop at
     * the cell where the egg was placed.
     */
    public void walkAroundFencedArea() {
    layEgg(); // ei leggen
    move(); // eerst wegstappen van het ei
    while (!onEgg()) {
        // kijk naar rechts
        turnRight();
        boolean hekRechts = fenceAhead();
        turnLeft();
        if (hekRechts) {
            // hek rechts
            if (fenceAhead()) {
                turnLeft();  // draai links
            } else {
                move();      // vrij voor = loop door
            }
        } else {
            // draai rechts om de buitenhoek
            turnRight();
            move();
        }
    }
    }
    /**
     * Mimi will move and follow the eggs ahead and stop at the nest
     */
    public void faceNextEgg() {
    turnLeft(); // begin met links kijken (nooit achteruit)
    while (!eggAhead() && !nestAhead()) {
        turnRight();
    }
    }
     void walkToNextEgg() {
    move(); // stap van huidig ei af
    while (!onEgg() && !onNest()) {
        move();
    }
    }
    public void eggTrailToNest() {
    while (!onNest()) {
        faceNextEgg();
        walkToNextEgg();
    }
    }
    /**
     * Mimi will keep walking in the maze and walk through openings 
     * and stop when she finds the nest
     */
    public void solveMaze() {
    while (!onNest()) {
        turnRight();
        if (!fenceAhead()) {
            move();
        } else {
            turnLeft(); // terug naar originele richting
            if (!fenceAhead()) {
                move();
            } else {
                turnLeft();
                if (!fenceAhead()) {
                    move();
                } else {
                    turnLeft();
                    move();
                }
            }
        }
    }
    }
    /**
     * Mimi faces direction
     * 
     * @param newDirection: the new direction mimi will face
     */
    public void faceDirection(int newDirection){
        if (newDirection >= 0 && newDirection <= 3){
            while (getDirection() != newDirection)
            turnRight();
        }
    }
}




