import javax.swing.JFrame;
import java.util.ArrayList;

public class MainFrame extends JFrame implements Runnable {

    private DrawPanel p;
    private ArrayList<Card> deck;
    private Thread windowThread;

    public MainFrame(String display) {
        super(display);
        int frameWidth = 500;
        int frameHeight = 500;
        deck = Card.buildDeck();
        p = new DrawPanel(deck);
        this.add(p);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(frameWidth, frameHeight);
        this.setLocation(600, 100);
        this.setVisible(true);
        startThread();

    }

    public void startThread() {
        windowThread = new Thread(this);
        windowThread.start();
    }

    public void run() {
        while (true) {
            p.repaint();
        }
    }
}



