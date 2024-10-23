public class PlayerPlus extends Player{
    
    public PlayerPlus(String name){
        super(name);
    }
    @Override
    public Card play(Eights eights, Card prev) {
        Card card = searchForMatchPlus(prev);
        if (card == null) {
            card = drawForMatch(eights, prev);
        }
        return card;
    }
  
    public Card searchForMatchPlus(Card prev){
        int target_suit = prev.getSuit();
        int target_rank = prev.getRank();
        int match_index = 0;
        Card card_out = new Card(0, 0);
        for(int i = 0; i < hand.size(); i++){
            if(hand.getCardSuit(i) == target_suit 
            && hand.getCardRank(i) > card_out.getRank()){
                card_out = hand.getCard(i);
                match_index = i;
            }
            else if (hand.getCardRank(i) == target_rank
            && hand.getCardRank(i) > card_out.getRank()){
                card_out = hand.getCard(i);
                match_index = i;
            }
            else if (hand.getCardRank(i) == 8
            && hand.getCardRank(i) > card_out.getRank()){
                card_out = hand.getCard(i);
                match_index = i;
            }

        }
        if(card_out.getRank() > 0){
            hand.popCard(match_index);
            return card_out;
        }
        else{
        return null;
        }
    }
}