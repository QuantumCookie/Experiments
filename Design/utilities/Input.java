package utilities;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Input implements KeyListener, MouseListener
{
    public static final int HORIZONTAL = 0, VERTICAL = 1;

    private static Input input = new Input();

    //private long[] keys;
    private boolean[] up, down;
    private long[] keys;
    private float horizontal, vertical;
    private boolean mouseDown;
    private int mouseX, mouseY;
    private StringBuffer keyCache;

    private Input()
    {
        //keys = new long[256];
        up = new boolean[256];
        down = new boolean[256];
        keys = new long[256];
        horizontal = vertical = 0.0f;
        mouseX = mouseY = 0;
        mouseDown = false;
        keyCache = new StringBuffer();
    }

    public static void setComponent(Component c)
    {
        c.removeKeyListener(input);
        c.addKeyListener(input);
        c.removeMouseListener(input);
        c.addMouseListener(input);
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
        return input.down[code % input.down.length];
    }

    public static boolean keyUp(int code)
    {
        return input.up[code % input.up.length];
    }

    public static boolean mouseDown(){return input.mouseDown;}

    public static int mouseX(){return input.mouseX;}

    public static int mouseY(){return input.mouseY;}

    public static void poll()
    {
        input.pollInput();
    }

    private void pollInput()
    {
        keys = new long[256];
        up = new boolean[256];
        down = new boolean[256];
        keyCache.setLength(0);
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        int code = e.getKeyCode();
        input.down[code % input.down.length] = true;
        input.up[code % input.up.length] = false;
        input.keys[code % input.keys.length] = System.currentTimeMillis();
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
        input.down[code % input.down.length] = false;
        input.up[code % input.up.length] = true;
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

    @Override
    public void mousePressed(MouseEvent e)
    {
        mouseDown = true;
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        mouseDown = false;
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {

    }

    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }
}