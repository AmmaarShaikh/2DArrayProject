import java.util.ArrayList;
import java.util.Scanner;

// main class for the program;
// the methods of this class set up the players and run the game
public class TicTacToe
{
  // array of Players
	private Player[] players;
  
  // Board object used in the game
	private Board board;
    private Board board2;
    private ArrayList<Boat> boats;
    Scanner scanner = new Scanner(System.in);
  
  /**
   * Creates a player with the symbol X and a player with the symbol O
   * and initializes the players instance variable with the two players.
   *
   * Creates a new Board and assigns it to board, then draws game boad.
   */
  public TicTacToe()
  {
    setup();
  }
  
  private void setup()
  {
    players = new Player[2];
    
    for (int i = 0; i < players.length; i++)
    {
      String symbol = Player.playerSymbols[i];
      players[i] = new Player(symbol);
    }
    
    System.out.print("What size board? ((b)ig, (m)edium, (s)mall)): ");
    String boardSize = scanner.nextLine();

        board = new Board(boardSize);
        board2 = new Board(boardSize);
        boats = new ArrayList<>();
        if (board.getBoardSize() == 10){
            boats.add(new Boat("Aircraft Carrier", 5));
            boats.add(new Boat("Battleship", 4));
            boats.add(new Boat("Battleship", 4));
            boats.add(new Boat("Cruiser", 3));
            boats.add(new Boat("Cruiser", 3));
            boats.add(new Boat("Destroyer", 2));
            boats.add(new Boat("Destroyer", 2));
            boats.add(new Boat("Submarine", 1));
            boats.add(new Boat("Submarine", 1));
        }
        if (board.getBoardSize() == 8){
            boats.add(new Boat("Aircraft Carrier", 5));
            boats.add(new Boat("Battleship", 4));
            boats.add(new Boat("Cruiser", 3));
            boats.add(new Boat("Destroyer", 2));
            boats.add(new Boat("Destroyer", 2));
            boats.add(new Boat("Submarine", 1));
            boats.add(new Boat("Submarine", 1));
        }
        if (board.getBoardSize() == 6){
            boats = new ArrayList<>();
            boats.add(new Boat("Aircraft Carrier", 5));
            boats.add(new Boat("Battleship", 4));
            boats.add(new Boat("Cruiser", 3));
            boats.add(new Boat("Destroyer", 2));
            boats.add(new Boat("Submarine", 1));
        }
    
    // draws the board as part of setup

        System.out.println("The warships can either be placed horizontal or vertical");
        System.out.println("If vertical, the warship will go towards the right of the spot you pick");
        System.out.println("If horizontal, the warship will go down from the spot you pick");
        System.out.println("Player 1, start placing warships(Player 2 no peeking)");
        board.drawBoard();
        for (int i = 0; i < boats.size(); i++){
            System.out.println(boats.get(i).getName() + " has a length of " + boats.get(i).getSize());
            System.out.print("Do you want it to be (v)ertical or (h)orizontal: ");
            String direc = scanner.nextLine();

            if (direc.equals("h")){
                boats.get(i).isfacedown(false);
            }
            System.out.print("Where do you want the ship to be placed: ");
            int placement = scanner.nextInt();

            boolean fits = false;
            while (fits == false){
                if (boats.get(i).getFaceDown() == false){
                    if (placement + boats.get(i).getSize() > board.getBoardSize()){
                        System.out.print("The boat does not fit, try again: ");
                        placement = scanner.nextInt();
                    }
                    else{
                        fits = true;
                    }
                }
            }

        }
  }
	
  /**
   * A loop will run the game until the board is full.
   * Inside the loop, each player will take a turn, starting with p1/
   * If either player's turn ends the game (the takeTurn() method returns true), break out of the loop.
   */
	public void runGame()
	{
    boolean gameOver = false;
    
    // repeat until either the board is full (i.e. all spaces have been selected),
    // or the game is over because someone won, which is true if takeTurn returns true
		while (!board.isFull() && !gameOver)
		{ 
      // each time through the while loop, iterate over the players array;
      // we add a break just in case the first player in array wins the game,
      // to prevent the next player from getting another turn  
      for (int i = 0; i < players.length; i++)
      {
			  if(takeTurn(players[i]))
			  {
          gameOver = true;
          break;  // breaks out of the for-loop -- NOT the while loop
        }
      }
		}
    System.out.println("Thanks for playing!");
  }
	
   /**
    * Allows the current player to take a turn.
    * Print a message saying whose turn it is: X or O
    * Draw the board for player reference.
    * Allow the appropriate player to enter the space number they want to occupy and record the move.
    * If the player has won the game, print a message and return true.
    * If the board is full, print a message and return true.
    * Otherwise, the game is not yet over and return false.
    *
    * @param p  the player taking the turn.
    * @return  true if the GAME is over, false if the TURN is over but the game is not over
    */	
	public boolean takeTurn(Player player)
	{
    Scanner scanner = new Scanner(System.in);
    boolean selectedValidSpace = false;
    
    // repeat until player selects a valid space, which occurs when recordMove returns true;
    // this occurs when the player has selected a numbered "blank" space.
    while (!selectedValidSpace)
    {
      System.out.print("Player " + player.getSymbol() + "'s turn! Choose a space: ");
      int chosenSpace = scanner.nextInt();
      selectedValidSpace = board.recordMove(chosenSpace, player);
   	}

    // redraw the board, which will include the newly placed X or O as updated via recordMove
  	board.drawBoard();
    
    // check to see if the board reveals a winning condition for either X or O
		String winner = board.checkWinner();
		
    if (!winner.equals(Space.BLANK))
		{
			System.out.println(winner + " won!");
			return true;
		}
  
    if (board.isFull())
    {
      System.out.println("The game is a tie!");
      return true;
    }
    
		return false;		
	}
}