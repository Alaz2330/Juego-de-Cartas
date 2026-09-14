import java.util.Random;

public class Baraja {
    private final int HAND_LENGTH = 10;
    private final int DECK_LENGTH = 52;

    private int decks_amount;
    private int topIndex;
    
    private Carta[] deck;

    public Baraja(int decks_amount){
        this.decks_amount = decks_amount;
        this.topIndex = (decks_amount * DECK_LENGTH) - 1;
        this.deck = new Carta[topIndex+1];    
        
        setBaraja();
    }

    public void setBaraja(){ 
        for (int j = 0; j <= decks_amount - 1; j++){           
            int index = j*DECK_LENGTH;
            for (int i = 1; i <= DECK_LENGTH; i++){
                deck[index] = new Carta(i);
                index ++;
            }
        }
    }
    private void shuffle(){
        Random random = new Random();
        int randomIndex = random.nextInt(topIndex + 1); 
        Carta topCard = deck[topIndex];        
        deck[topIndex] = deck[randomIndex];
        deck[randomIndex] = topCard;
    }

    public Carta getCarta(){
        shuffle();
        Carta mixxedCard = deck[topIndex];
        topIndex--;        
        return mixxedCard;
    }

    public void resetBaraja(){
        this.topIndex = (decks_amount * DECK_LENGTH) - 1; 
    }
}
