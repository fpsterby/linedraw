import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Canvas canv = new Canvas(800,800);
        frame.add(canv);
        frame.pack();
        frame.setVisible(true);

        canv.startThread();
    }
}