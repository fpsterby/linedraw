import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Clicks implements MouseListener {
    final Listener l;

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

    public void addListener(Listener l){

    }

}

interface Listener {
    public void onClick(MouseEvent e);
}