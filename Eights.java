import java.util.ArrayList;
import java.util.Scanner;

/**
 * Simulates a game of Crazy Eights.
 * See https://en.wikipedia.org/wiki/Crazy_Eights.
 */
public class Eights {

    
    private ArrayList<Player> players;
    private PlayerPlus one;
    private Player two;
    private Hand drawPile;
    private Hand discardPile;
    private Scanner in;

    /**
     * Initializes the state of the game.
     */
    public Eights() {
        Deck deck = new Deck("Deck");
        deck.shuffle();

        // deal cards to each player
        one = new PlayerPlus("Allen");
        deck.deal(one.getHand(), 5);
        two = new Player("Chris");
        deck.deal(two.getHand(), 5);

        // turn one card face up
        discardPile = new Hand("Discards");
        deck.deal(discardPile, 1);

        // put the rest of the deck face down
        drawPile = new Hand("Draw pile");
        deck.dealAll(drawPile);

        // create the scanner we'll use to wait for the user
        in = new Scanner(System.in);
    }
    public Eights(int num){
        Deck deck = new Deck("Deck");
        deck.shuffle();
        String names = "Player";
        this.players = new ArrayList<Player>();
        for(int i = 0; i < num; i++){
            players.add(new Player(String.format("%s %d", names, i + 1)));
            deck.deal(players.get(i).getHand(), 5);
        }
                
        discardPile = new Hand("Discards");
        deck.deal(discardPile, 1);
                 
        drawPile = new Hand("Draw pile");
        deck.dealAll(drawPile);
               
        in = new Scanner(System.in);
    }

    /**
     * Returns true if either hand is empty.
     */
    public boolean isDone() {
        return one.getHand().isEmpty() || two.getHand().isEmpty();
    }

    /**
     * Moves cards from the discard pile to the draw pile and shuffles.
     */
    public void reshuffle() {
        // save the top card
        Card prev = discardPile.popCard();

        // move the rest of the cards
        discardPile.dealAll(drawPile);

        // put the top card back
        discardPile.addCard(prev);

        // shuffle the draw pile
        drawPile.shuffle();
    }

    /**
     * Returns a card from the draw pile.
     */
    public Card drawCard() {
        if (drawPile.isEmpty()) {
            reshuffle();
        }
        return drawPile.popCard();
    }

    /**
     * Switches players.
     */
    public Player nextPlayer(Player current) {
        if (current == one) {
            return two;
        } else {
            return one;
        }
    }
    public Player nextPlayer(Player current, boolean multiplayer) {
        if (players.indexOf(current) < players.size() - 1) {
            return players.get(players.indexOf(current) + 1);
        } else {
            return players.get(0);
        }
    }

    /**
     * Displays the state of the game.
     */
    public void displayState() {
        one.display();
        two.display();
        discardPile.display();
        System.out.print("Draw pile: ");
        System.out.println(drawPile.size() + " cards");
        in.nextLine();
    }

    /**
     * One player takes a turn.
     */
    public void takeTurn(Player player) {
        Card prev = discardPile.lastCard();
        Card next = player.play(this, prev);
        discardPile.addCard(next);


       // System.out.println(player.getName() + " plays " + next);
        //System.out.println();
    }

    public int determineWinner(Player one, Player two){
        if(one.score() > two.score()){
            return 1;
        }
        else{
            return 2;
        }
    }


    /**
     * Plays the game.
     */
    public int playGame() {
        Player player = one;
        

        // keep playing until there's a winner
        while (!isDone()) {
            //displayState();
            takeTurn(player);
            player = nextPlayer(player);
        }

        // display the final score
       // one.displayScore();
        //two.displayScore();

        return determineWinner(one, two);
    
 
    }
    public int playGame(int num) {
        
        Player current_player = players.get(0);
        while (!isDone()) {
        //displayState();
            takeTurn(current_player);
            current_player = nextPlayer(current_player);
        }
         return 0;
    }

    /**
     * Creates the game and runs it.
     */
    public static void main(String[] args){

       
    Eights game = new Eights(4);
        game.playGame(4);
        
        
    }

}
