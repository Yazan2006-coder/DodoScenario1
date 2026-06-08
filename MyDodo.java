import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 *
 * @author Sjaak Smetsers & Renske Smetsers-Weeda
 * @version 3.0 -- 20-01-2017
 */
public class MyDodo extends Dodo
{
    private int myNrOfEggsHatched;
    private int waardeBlauweEi = 2;
    private int waardeGoudenEi = 10;
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
    public void swapEggValues() {
    int tijdelijkeWaardeEi = waardeBlauweEi; // sla blauw op
    waardeBlauweEi = waardeGoudenEi; // blauw krijgt waarde van goud
    waardeGoudenEi = tijdelijkeWaardeEi; // goud krijgt oude waarde van blauw
    System.out.println("Blauw ei: " + waardeBlauweEi);
    System.out.println("Gouden ei: " + waardeGoudenEi);
    }
    /**
     * Moves Dodo to the cell with the given coordinates.
     * First moves horizontally to the correct column,
     * then vertically to the correct row.  
     */
    public boolean locationReached(int x, int y) {
    return getX() == x && getY() == y;
    }
    public void goToLocation(int coordX, int coordY) {
    if (!validCoordinates(coordX, coordY)) {
        return; // stop als coordinaten ongeldig zijn
    }
    // move horizontally
    while (getX() != coordX) {
        if (getX() < coordX) {
            setDirection(EAST);
        } else {
            setDirection(WEST);
        }
        move();
    }
    // move vertically
    while (getY() != coordY) {
        if (getY() < coordY) {
            setDirection(SOUTH);
        } else {
            setDirection(NORTH);
        }
        move();
    }
    }
    /**
     * Checks if the given coordinates are valid (within the world boundaries).
     * Shows an error message if the coordinates are invalid.
     * 
     */
    public boolean validCoordinates(int x, int y) {
    if (x >= 0 && x < getWorld().getWidth() && y >= 0 && y < getWorld().getHeight()) {
        return true;
    } else {
        showError("Invalid coordinates");
        return false;
    }
    }
    /**
     * Counts the number of eggs in the current row.
     * Mimi walks to the end of the world counting eggs,
     * then returns to the starting position facing the original direction.
     */
    public int countEggsInRow() {
    int eggCount = 0;
    // check huidige cel (eerste cel)
    if (onEgg()) {
        eggCount++;
    }
    // loop naar het einde en tel eieren
    while (!borderAhead()) {
        move();
        if (onEgg()) {
            eggCount++;
        }
    }
    // terug naar beginpositie
    goBackToStartOfRowAndFaceBack();
    // toon compliment met het aantal eieren
    showCompliment("Er zijn " + eggCount + " eieren in deze rij!");
    return eggCount;
    }
    /**
     * Lays a trail of eggs behind Mimi as she moves forward
     * Mimi ends up standing on the last egg laid
     */
    public void layTrailOfEggs(int n) {
    if (n < 1) {
        showError("Invalid number of eggs: " + n);
        return;
    }
    // leg eerste ei op huidige cel
    layEgg();
    // beweeg en leg de rest van de eieren
    int eggsLaid = 1;
    while (eggsLaid < n) {
        if (!canMove()) {
            showError("Not enough cells to lay " + n + " eggs!");
            return;
        }
        move();
        layEgg();
        eggsLaid++;
    }
    }
    /**
     * Counts all eggs in the world by going through each row.
     * Mimi returns to her starting position after counting.
     */
    public int countAllEggs() {
    int totalEggs = 0;
    int startX = getX();
    int startY = getY();
    // loop door elke rij
    int currentRow = 0;
    while (currentRow < getWorld().getHeight()) {
        // ga naar het begin van de rij
        goToLocation(0, currentRow);
        setDirection(EAST);
        // tel eieren in deze rij en voeg toe aan totaal
        totalEggs += countEggsInRow();
        System.out.println("Rij " + currentRow + ": " + totalEggs + " eieren totaal");
        currentRow++;
    }
    // terug naar beginpositie
    goToLocation(startX, startY);
    setDirection(EAST);
    return totalEggs;
    }
    /**
     * Finds the row with the most eggs in the world.
     * Prints the row number to the console and returns to starting position.
     * If multiple rows have the same maximum, the first row found is returned.
     */
    public int findRowWithMostEggs() {
    int startX = getX();
    int startY = getY();
    int maxEggs = 0;
    int bestRow = 0;
    int currentRow = 0;
    // loop door elke rij
    while (currentRow < getWorld().getHeight()) {
        goToLocation(0, currentRow);
        setDirection(EAST);
        int eggsInRow = countEggsInRow();
        System.out.println("Rij " + currentRow + ": " + eggsInRow + " eieren");
        // is deze rij beter dan de vorige beste?
        if (eggsInRow > maxEggs) {
            maxEggs = eggsInRow;
            bestRow = currentRow;
        }
        currentRow++;
    }
    // terug naar beginpositie
    goToLocation(startX, startY);
    setDirection(EAST);
    System.out.println("Rij met de meeste eieren: " + bestRow + " (" + maxEggs + " eieren)");
    showCompliment("Rij met de meeste eieren: rij " + bestRow);
    return bestRow;
    }
    /**
     * Mimi lays a monument of eggs on top eachother from its current position
     */
    public void eggMonument() {
    int startX = getX();
    int startY = getY();
    int currentRow = 0;
    while (startY + currentRow < getWorld().getHeight()) {
    int monu = 0;
    while (monu <= currentRow && startX + monu < getWorld().getWidth()) {
        setLocation(startX + monu, startY + currentRow);
        if (canLayEgg()) layEgg();
        monu++;
    }
    currentRow++;
    }
    }
    /**
     * Fills the world with a monument from Mimi's current position.
     * Each row contains double the amount of eggs
     * compared to the row above it.
     */
    public void strongerMonument() {
    int startX = getX();
    int startY = getY();
    int currentRow = 0;
    int eggsToLay = 1; // begin met 1 ei
    while (startY + currentRow < getWorld().getHeight()) {
        goToLocation(startX, startY + currentRow);
        setDirection(EAST);
        int eggsLaid = 0;
        while (eggsLaid < eggsToLay) {
            layEgg();
            eggsLaid++;
            if (eggsLaid < eggsToLay && canMove()) {
                move();
            } else {
                break; // rand bereikt, stop met deze rij
            }
        }
        // leg eieren
        currentRow++;
        eggsToLay = eggsToLay * 2; // verdubbel voor volgende rij
    }
    // terug naar beginpositie
    goToLocation(startX, startY);
    setDirection(EAST);
    }
    /**
     * Fills the world as much as possible with a pyramid pattern
     */
    public void buildPyramid() {
    int startX = getX();
    int startY = getY();
    int currentRow = 0;
    int eggsToLay = 1;
    int worldWidth = getWorld().getWidth();
    while (startY + currentRow < getWorld().getHeight()) {
        // stop als eieren niet meer passen
        if (eggsToLay > worldWidth) {
            break;
        }
        // bereken startpositie gecentreerd
        int rowStartX = startX + (worldWidth / 2) - (eggsToLay / 2);
        // ga naar begin van deze rij
        goToLocation(rowStartX, startY + currentRow);
        setDirection(EAST);
        // leg eieren
        int eggsLaid = 0;
        while (eggsLaid < eggsToLay && canMove()) {
            layEgg();
            eggsLaid++;
            if (eggsLaid < eggsToLay) {
                move();
            }
        }
        currentRow++;
        eggsToLay = eggsToLay + 2; // elke rij 2 meer
    }
    // terug naar beginpositie
    goToLocation(startX, startY);
    setDirection(EAST);
    }
    /**
     * Calculates the average number of eggs per row in the world.
     * Uses typecasting to return a double value.
     */
    public double averageEggsPerRow() {
    int totalEggs = 0;
    int totalRows = getWorld().getHeight();
    int currentRow = 0;
    while (currentRow < totalRows) {
        goToLocation(0, currentRow);
        setDirection(EAST);
        totalEggs += countEggsInRow();
        currentRow++;
    }
    // terug naar beginpositie
    goToLocation(0, 0);
    setDirection(EAST);
    // typecast naar double voor kommagetal
    double average = (double) totalEggs / totalRows;
    System.out.println("Totaal eieren: " + totalEggs);
    System.out.println("Totaal rijen: " + totalRows);
    System.out.println("Gemiddelde: " + average);
    return average;
    }
    /**
     * Counts the number of eggs in the column.
     */
    public int countEggsInColumn(int column) {
    int eggCount = 0;
    int currentRow = 0;
    while (currentRow < getWorld().getHeight()) {
        goToLocation(column, currentRow);
        if (onEgg()) {
            eggCount++;
        }
        currentRow++;
    }
    return eggCount;
    }
    /**
     * Adds a parity egg to each row that has an odd number of eggs.
     * A golden egg is placed at the end of the row.
     */
    public void addRowParityBits() {
    int currentRow = 0;
    while (currentRow < getWorld().getHeight()) {
        goToLocation(0, currentRow);
        setDirection(EAST);
        int eggsInRow = countEggsInRow();
        //gouden ei toevoegen aan het einde
        if (eggsInRow % 2 != 0) {
            goToLocation(getWorld().getWidth() - 1, currentRow);
            getWorld().addObject(new GoldenEgg(), getX(), getY());
        }
        currentRow++;
    }
    }
    /**
     * Adds a parity egg to each column that has an odd number of eggs.
     * A golden egg is placed at the bottom of the column.
     */
    public void addColumnParityBits() {
    int currentColumn = 0;
    while (currentColumn < getWorld().getWidth()) {
        int eggsInColumn = countEggsInColumn(currentColumn);
        //gouden ei toevoegen onderaan
        if (eggsInColumn % 2 != 0) {
            goToLocation(currentColumn, getWorld().getHeight() - 1);
            getWorld().addObject(new GoldenEgg(), getX(), getY());
        }
        currentColumn++;
    }
    }
    /**
     * Prepares the world with parity bits for all rows and columns.
     * Adds golden eggs to rows and columns with odd egg counts
     */
    public void addParityBits() {
    addRowParityBits();    // eerst rijen
    addColumnParityBits(); // dan kolommen
    // terug naar beginpositie
    goToLocation(0, 0);
    setDirection(EAST);
    showCompliment("Pariteitsbit algoritme klaar!");
    }
    /**
     * Detects errors in the world using the parity bit algorithm.
     * Checks all rows and columns for odd egg counts.
     * Prints the location of the error if found.
     */
    public void detectError() {
    int errorRow = -1;    // -1 betekent geen fout gevonden
    int errorColumn = -1;
    // controleer alle rijen
    int currentRow = 0;
    while (currentRow < getWorld().getHeight()) {
        goToLocation(0, currentRow);
        setDirection(EAST);
        int eggsInRow = countEggsInRow();
        if (eggsInRow % 2 != 0) {
            errorRow = currentRow;
        }
        currentRow++;
    }
    // controleer alle kolommen
    int currentColumn = 0;
    while (currentColumn < getWorld().getWidth()) {
        int eggsInColumn = countEggsInColumn(currentColumn);
        if (eggsInColumn % 2 != 0) {
            errorColumn = currentColumn;
        }
        currentColumn++;
    }
    // terug naar beginpositie
    goToLocation(0, 0);
    setDirection(EAST);
    // resultaat bepalen
    if (errorRow == -1 && errorColumn == -1) {
        showCompliment("Geen fout gevonden!");
        System.out.println("Geen fout gevonden.");
    } else {
        System.out.println("Fout gevonden op rij: " + errorRow + ", kolom: " + errorColumn);
        showCompliment("Fout op coordinaten (" + errorColumn + ", " + errorRow + ")");
    }
    }
}




