public class EightsHand extends Hand{

    public EightsHand(String label){
        super(label);
    }
    public int scoreHand(){
        int score = 0;
        for(int i = 0; i < size(); i++){
            int temp = getCardRank(i);
            if(temp > 10){
                score += 10;
            }
            else if(temp == 8){
                score += 20;
            }
            else{
                score += temp;
            }
        }
        return score;
    }
}