package utilities;

import java.awt.event.*;
import java.awt.Component;

public class Input implements KeyListener, MouseListener, MouseMotionListener
{
    public static final int HORIZONTAL = 0, VERTICAL = 1;

    private static Input input = new Input();
    
    private long[] keys;
    private float horizontal, vertical;
    private StringBuffer keyCache;

    private long[] mouse;
    private float mouseX, mouseY;

    private Input()
    {
        keys = new long[256];
        horizontal = vertical = 0.0f;
        keyCache = new StringBuffer();

        mouse = new long[4];
        mouseX = mouseY = 0f;
    }
    
    public static void setComponent(Component c)
    {
        c.removeKeyListener(input);
        c.addKeyListener(input);

        c.removeMouseListener(input);
        c.addMouseListener(input);

        c.removeMouseMotionListener(input);
        c.addMouseMotionListener(input);
    }
    
    public static float getAxis(int axis)
    {
        switch(axis)
        {
            case HORIZONTAL: return input.horizontal;
            case VERTICAL: return input.vertical;
            default: return 0;
        }
    }
    
    public static boolean keyDown(int code)
    {
        return input.keys[code % input.keys.length] > 0;
    }

    public static boolean mouseDown(int code){  return input.mouse[code % input.mouse.length] > 0;}

    public static float mouseX(){  return input.mouseX;}

    public static float mouseY(){  return input.mouseY;}
    
    @Override
    public void keyPressed(KeyEvent e)
    {
        int code = e.getKeyCode();
        input.keys[code % input.keys.length] = System.nanoTime();
        switch(code)
        {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
            input.vertical = -1;
            break;
            
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
            input.vertical = 1;
            break;
            
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
            input.horizontal = -1;
            break;
            
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
            input.horizontal = 1;
            break;
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e)
    {
        int code = e.getKeyCode();
        input.keys[code % input.keys.length] = 0;
        switch(code)
        {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
            input.vertical = 0;
            break;
            
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
            input.horizontal = 0;
            break;
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e)
    {
        keyCache.append(e.getKeyChar());
        if(keyCache.length() > 1024)
        {
            keyCache.setLength(0);
        }
    }

    public void mouseClicked(MouseEvent e){

    }

    public void mousePressed(MouseEvent e)
    {
        mouse[e.getButton()] = System.nanoTime();
        //System.out.println("Pressed" + e.getButton());
    }

    public void mouseReleased(MouseEvent e)
    {
        mouse[e.getButton()] = 0;
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseDragged(MouseEvent e)
    {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    public void mouseMoved(MouseEvent e)
    {
        mouseX = e.getX();
        mouseY = e.getY();
    }
}