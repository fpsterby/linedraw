import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Clicks implements MouseListener, KeyListener {
    final Listener l;
    Point p1; Point p2;
    public Clicks(Listener l){
        this.l = l;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        l.onClick(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    
    }

    @Override
    public void mouseExited(MouseEvent e) {
    
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("key pressed");
        l.onKeyPress(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

}

interface Listener {
    public void onClick(MouseEvent e);
    public void onKeyPress(KeyEvent e);
}