public class EightsCard extends Card{
    public static boolean match(Card card1, Card card2){
        return card1.getSuit() == card2.getSuit()
        || card1.getRank() == card2.getRank()
        || card1.getRank() == 8;
    }
    public int scoreCard(Card c){
        int temp = c.getRank();
        if(temp > 10){
            return 10;
        }
        else if(temp == 8){
            return 20;
        }
        else{
            return temp;
        }
    }
}