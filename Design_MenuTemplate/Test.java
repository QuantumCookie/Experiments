import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import javax.swing.*;

import utilities.*;

public class Test extends Canvas implements GListener
{
    public static final float WIDTH = 500, HEIGHT = 500;
    //private static  Test instance = null;
    
    private BufferStrategy bs;

    private GameManager gameManager;
    //private MainMenu mainMenu;

    private State state;
    
    //private double x, y, w, h;
    private Test()
    {
        Dimension size = new Dimension((int)WIDTH, (int)HEIGHT);
        setPreferredSize(size);
        setMaximumSize(size);
        setMinimumSize(size);
        
        initialize();
    }
    
    @Override
    public void update()
    {
        gameManager.update();
    }
    
    private void initialize()
    {
        GTimer.initialize(this);
        Input.setComponent(this);

        state = State.MAINMENU;

        //mainMenu = new MainMenu(this);
        gameManager = new GameManager(this);
    }
    
    private void start()
    {
        GTimer.start();
    }
    
    private void stop()
    {
        GTimer.stop();
    }
    
    @Override
    public void render()
    {
        bs = getBufferStrategy();
        if(bs == null)
        {
            createBufferStrategy(3);
            return;
        }
        
        do
        {
            Graphics2D grap = (Graphics2D) bs.getDrawGraphics();
            grap.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            do
            {
                grap.clearRect(0, 0, (int)WIDTH, (int)HEIGHT);
                gameManager.render(grap);
            }
            while(bs.contentsLost());
            bs.show();
            grap.dispose();
        }
        while(bs.contentsRestored());
    }

    /*public static Test getInstance()
    {
        if(instance == null)instance = new Test();
        return instance;
    }*/

    public void setState(State state)
    {
        this.state = state;
    }

    public State getState()
    {
        return state;
    }
    
    public static void main(String[] args)
    {
        Test test = new Test();
        //Test test = Test.getInstance();
        
        JFrame frame = new JFrame("Test");
        
        frame.add(test);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        test.start();
    }
}