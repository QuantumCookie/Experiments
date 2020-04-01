import utilities.Input;

import java.awt.*;
import java.awt.geom.Path2D;
import java.util.ArrayList;

public class IKSystem
{
    public float x, y;
    private ArrayList<IK_Arm> arms;
    private IK_Arm lastArm;

    public float offsetX, offsetY;

    public IKSystem(float _x, float _y)
    {
        x = _x;
        y = _y;

        arms = new ArrayList<IK_Arm>();
        init();
    }

    private void init()
    {
        for(int i = 0; i < 20; i++)
        {
            addArm(30);
        }
    }

    public void update()
    {
        //notPinned();
        pinned();
    }

    private void pinned()
    {
        for(int x = 0; x < 5; x++)
        {
            notPinned();

            IK_Arm root = arms.get(0);
            float adjustmentX = root.x - this.x;
            float adjustmentY = root.y - this.y;

            for(int i = 0; i < arms.size(); i++)
            {
                IK_Arm a = arms.get(i);
                a.x = a.x - adjustmentX;
                a.y = a.y - adjustmentY;
            }
        }
    }

    private void notPinned()
    {
        lastArm.pointAt(Input.mouseX() - offsetX, Input.mouseY() - offsetX);
        lastArm.x = Input.mouseX() - lastArm.length * (float) Math.cos(lastArm.angle) - offsetX;
        lastArm.y = Input.mouseY() - lastArm.length * (float) Math.sin(lastArm.angle) - offsetY;

        IK_Arm current = lastArm.parent;
        IK_Arm next = lastArm;

        while(current != null)
        {
            current.pointAt(next.x, next.y);
            current.x = next.x - current.length * (float) Math.cos(current.angle);
            current.y = next.y - current.length * (float) Math.sin(current.angle);

            next = current;
            current = current.parent;
        }
    }

    private IK_Arm addArm(float length)
    {
        if(lastArm == null) lastArm = new IK_Arm(x, y, length, null);
        else lastArm = new IK_Arm(lastArm.getEndX(), lastArm.getEndY(), length, lastArm);

        arms.add(lastArm);
        return lastArm;
    }

    public void render(Graphics2D g)
    {
        Stroke old = g.getStroke();
        g.setStroke(new BasicStroke(10));

        Path2D.Float path = new Path2D.Float();
        path.moveTo(lastArm.x, lastArm.y);

        IK_Arm current = lastArm.parent;

        while(current != null)
        {
            path.lineTo(current.x, current.y);
            current = current.parent;
        }

        for(int i = 0; i < arms.size(); i++)
        {
            arms.get(i).render(g);
        }

        //g.draw(path);
        g.setStroke(old);
    }
}
