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

        int count = 0;
        for (Line line : lines) {
            g2.setColor(getRainbowColor(count, 0.3f));
            g2.drawLine((int) line.p1.x, (int) line.p1.y, (int) line.p2.x, (int) line.p2.y);
            count ++;
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
            Line line = lines.get(lines.size()- 1);
            System.out.println(line + "\t" + line.calcAngle());
        } else if (e.getButton() == 3) {
            this.p1 = new Point(e.getX(), e.getY());
        }
    }

    @Override
    public void onKeyPress(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_D:
                if (lines.size() > 0)
                    lines.remove(lines.size() -1);
                    break;
            case KeyEvent.VK_P:
                if (lines.size() > 0)
                    System.out.println(lines.get(lines.size() -1));
            default:
                System.out.println("fart");
        }
    }

    private Color getRainbowColor(int index, float multiplier){
        return new Color(Color.HSBtoRGB(0.1f * index * multiplier, .7f, 1));
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
        return "line: p1(" + p1.toString() + ")\tp2(" + p2.toString()+")";
    }

    public double calcAngle() {
        double angle = 0;
        angle = Math.atan((p2.y - p1.y) / (p2.x - p1.x));

        angle = Math.PI - angle;
        angle = angle * (180 / Math.PI);

        if (p2.x - p1.x < 0){
            if (p2.y - p1.y > 0) {
                // bottom left
                angle = angle - 180;
            } else {
                angle = 180 + angle;    // then 180 - angle
            }
        }
        
        System.out.println(angle);
        return angle;
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
