import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class Canvas extends JPanel implements Runnable, Listener {
    Thread t;
    List<Line> lines = new ArrayList<>();

    public Canvas(int width, int height){
        this.setDoubleBuffered(true);
        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(width, height));

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.green);


    }

    @Override
    public void run() {
        while (t != null){


            eep(100);
        }
    }

    public void startThread(){
        this.t = new Thread(this);
        t.start();
    }

    public void eep (double ms) {
        try {
            Thread.sleep((long) ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(MouseEvent e) {
        if (e.getButton() == 1){
            
        }
    }
}

class Line {
    Point p1;
    Point p2;

    public Line(Point P1, Point P2){
        p1 = P1;
        p2 = P2;
    }
}

class Point {
    double x;
    double y;
    
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
}
