import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.*;
import java.io.File;
import javax.swing.*;
import javax.swing.text.StyleConstants;

import utilities.*;

public class Test extends Canvas implements GListener
{
    public static final int WIDTH = 1024, HEIGHT = 640;

    private Simulation sim;
    private RopeSimulation ropeSim;
    private FKSystem fks;
    private Arm arm1, arm2, arm3;
    private BufferedImage bi;
    private Graphics2D bg;

    float lastX, lastY;

    private IKSystem iks;

    //private float x;

    private Test()
    {
        Dimension size = new Dimension(WIDTH, HEIGHT);
        setPreferredSize(size);
        setMaximumSize(size);
        setMinimumSize(size);
        
        initialize();
    }
    
    private void initialize()
    {
        GTimer.initialize(this);
        Input.setComponent(this);

        //x = 0;

        //sim = new Simulation();
        //ropeSim = new RopeSimulation(1000);
        /*fks = new FKSystem(WIDTH / 2, HEIGHT / 2);

        arm1 = fks.addArm(0, 50);
        arm2 = fks.addArm(0, 200);
        arm3 = fks.addArm(0, 75);

        lastX = arm3.getEndX();
        lastY = arm3.getEndY();

        bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        bg = bi.createGraphics();
        bg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);*/

        iks = new IKSystem(WIDTH / 2, HEIGHT / 2);
        iks.offsetX = 0f;
        iks.offsetY = 0f;
    }
    
    private void start()
    {
        GTimer.start();
    }
    
    private void stop()
    {
        GTimer.stop();
    }

    private void restart()
    {
        initialize();
    }

    @Override
    public void update()
    {
        if(Input.keyDown(KeyEvent.VK_R))restart();
        else
        {
            //x += 100f * GTimer.deltaTime();
            //System.out.println(x + ", Delta: " + GTimer.deltaTime());
            //sim.update(GTimer.deltaTime());
            //ropeSim.update(GTimer.deltaTime());

            /*arm1.angle += 0.5f;// * Math.sin(System.currentTimeMillis() / 1000 + 3.6 * Math.PI);
            arm2.angle += 0.5f * Math.sin(System.currentTimeMillis() / 1000);
            arm3.angle += 2f * Math.cos(System.currentTimeMillis() / 1000);

            fks.update();*/

            iks.update();
        }
    }

    @Override
    public void render()
    {
        BufferStrategy bs = getBufferStrategy();
        if(bs == null)
        {
            createBufferStrategy(3);
            return;
        }
        
        do
        {
            Graphics2D g = (Graphics2D) bs.getDrawGraphics();
            do
            {
                g.clearRect(0, 0, WIDTH, HEIGHT);
                g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                render(g);
            }
            while(bs.contentsLost());
            bs.show();
            g.dispose();
        }
        while(bs.contentsRestored());
    }
    
    private void render(Graphics2D g)
    {
        g.clearRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.BLACK);
        //g.fillRect(0, 0, Test.WIDTH, Test.HEIGHT);
        //g.fill(new Ellipse2D.Float(x, 100f, 20f, 20f));
        //sim.render(g);
        //ropeSim.render(g);
        /*fks.render(g);

        //g.fill(new Ellipse2D.Float(arm3.getEndX()-25, arm3.getEndY()-25, 50, 50));
        bg.setColor(Color.black);
        bg.setStroke(new BasicStroke(2f));
        bg.draw(new Line2D.Float(lastX, lastY, lastX = arm3.getEndX(), lastY = arm3.getEndY()));
        g.drawImage(bi, 0, 0, this);*/

        iks.render(g);
    }
    
    public static void main(String[] args)
    {
        Test test = new Test();
        
        JFrame frame = new JFrame("Test");
        
        frame.add(test);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        test.start();
    }
}