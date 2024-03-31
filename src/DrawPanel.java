import javax.swing.JOptionPane;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.util.ArrayList;


class DrawPanel extends JPanel implements MouseListener {
    private ArrayList<Card> deck;
    private ElevensGame game;
    private Rectangle button;
    public DrawPanel(ArrayList<Card> deck) {
        this.deck = deck;
        button = new Rectangle(297, 150, 160, 26);
        this.addMouseListener(this);
        game = new ElevensGame();
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 30;
        int y = 30;
        ArrayList<Card> hand = game.getHand();
        for (int i = 0; i < hand.size(); i++) {
            Card c = hand.get(i);
            if (i % 3 == 0 && i != 0) {
                x = 30;
                y += 80;
            }
            if (c.getHighlight()) {
                g.drawRect(x, y, c.getImage().getWidth(), c.getImage().getHeight());
            }

            c.setRectangleLocation(x, y);
            g.drawImage(c.getImage(), x, y, null);
            x = x + c.getImage().getWidth() + 10;
        }
        g.setFont(new Font("Courier New", Font.BOLD, 20));
        g.drawString("GET NEW CARDS", 300, 170);
        g.drawRect((int)button.getX(), (int)button.getY(), (int)button.getWidth(), (int)button.getHeight());
    }
    public void mousePressed(MouseEvent e) {

        Point clicked = e.getPoint();
        ArrayList<Card> hand = game.getHand();
        if (e.getButton() == 1) {
            if (button.contains(clicked)) {
                game.eliminateCards(hand);
            }

            for (int i = 0; i < hand.size(); i++) {
                Rectangle box = hand.get(i).getCardBox();
                if (box.contains(clicked)) {
                    hand.get(i).flipHighlight();
                }
            }
        }
        if (e.getButton() == 3) {
            for (int i = 0; i < hand.size(); i++) {
                Rectangle box = hand.get(i).getCardBox();
                if (box.contains(clicked)) {
                    hand.get(i).flipHighlight();
                }
            }
        }
        if (game.checkWin()) {
            JOptionPane.showMessageDialog(null, "Congratulations! You won!");
            // You may want to reset the game here
        }
        if (game.checkLose()) {
            JOptionPane.showMessageDialog(null, "No valid moves available. You lose!");
            // You may want to reset the game here
        }
    }

    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseClicked(MouseEvent e) { }
}