import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class Canvas extends JPanel implements Runnable, Listener {
    Thread t;
    List<Line> lines = new ArrayList<>();
    Point p1 = null;
    Clicks clicks = new Clicks(this);

    public Canvas(int width, int height){
        this.setDoubleBuffered(true);
        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(width, height));
        this.setFocusable(true);
        this.addKeyListener(clicks);
        this.addMouseListener(clicks);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        if (p1 != null)
            g2.drawOval((int) p1.x, (int) p1.y, 3, 3);
        
        g2.setColor(Color.GREEN);


        for (Line line : lines) {
            drawLine(line, g2);
        }

    }

    @Override
    public void run() {
        while (t != null){
            repaint();
            eep(1);
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
        if (e.getButton() == 1 && p1 != null){
            lines.add(new Line(this.p1, new Point(e.getX(), e.getY())));
            System.out.println(lines.get(lines.size()- 1));
        } else if (e.getButton() == 3) {
            this.p1 = new Point(e.getX(), e.getY());
        }
    }

    public void drawLine(Line line, Graphics2D g2){
        double angle = Math.atan((line.p1.y - line.p2.y) / (line.p1.x - line.p2.x));
        angle = Math.PI - angle;
        angle = angle * (180 / Math.PI);
        
        System.out.println(angle);
        g2.drawLine((int) line.p1.x, (int) line.p1.y, (int) line.p2.x, (int) line.p2.y);
    }

    @Override
    public void onKeyPress(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_D:
                if (lines.size() > 0)
                    lines.remove(lines.size() -1);
                    break;
            default:
                System.out.println("fart");
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

    @Override
    public String toString() {
        return "line: " + p1.toString() + "\t" + p2.toString();
    }
}

class Point {
    double x;
    double y;
    
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "x = "+ this.x + ", y = " + this.y;
    }
    
}
