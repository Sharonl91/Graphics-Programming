import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ElevensGame {
    private ArrayList<Card> deck;
    private ArrayList<Card> hand;

    public ElevensGame() {
        deck = Card.buildDeck();
        hand = new ArrayList<>();
        dealCards();
    }

    public void dealCards() {
        for (int i = 0; i < 9; i++) {
            int r = (int) (Math.random() * deck.size());
            Card c = deck.remove(r);
            hand.add(c);
        }
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public boolean eliminateCards(ArrayList<Card> selectedCards) {
        if (selectedCards.size() == 2) {
            String value1 = selectedCards.get(0).getValue();
            String value2 = selectedCards.get(1).getValue();
            int intValue1 = value1.equals("A") ? 1 : Integer.parseInt(value1);
            int intValue2 = value2.equals("A") ? 1 : Integer.parseInt(value2);

            int sum = intValue1 + intValue2;
            if (sum == 11) {
                hand.removeAll(selectedCards);
                return true;
            }
        } else if (selectedCards.size() == 3) {
            int jack = 0, queen = 0, king = 0;
            for (Card card : selectedCards) {
                String value = card.getValue();
                if (value.equals("11")) jack++;
                else if (value.equals("12")) queen++;
                else if (value.equals("13")) king++;
            }

            if (jack > 0 && queen > 0 && king > 0) {
                hand.removeAll(selectedCards);
                return true;
            }
            JOptionPane.showMessageDialog(null, "No valid move available!");
        }
        return false;
    }

    public boolean checkWin () {
        return deck.isEmpty();
    }
    public boolean checkLose () {
        for (int i = 0; i < hand.size(); i++) {
            Card card1 = hand.get(i);
            for (int j = i + 1; j < hand.size(); j++) {
                Card card2 = hand.get(j);
                int sum = Integer.parseInt(card1.getValue() + card2.getValue());
                if (sum == 11) {
                    return false; // There's at least one valid move left
                }
            }
        }
        int jack = 0, queen = 0, king = 0;
        for (Card card : hand) {
            if (Integer.parseInt(card.getValue()) == 11) { jack++;
            } else if (Integer.parseInt(card.getValue()) == 12) queen++;
            else if (Integer.parseInt(card.getValue()) == 13) king++;
        }
        return !(jack > 0 && queen > 0 && king > 0); // Return true if no valid moves left
    }
}
