import utilities.GTimer;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FKSystem
{
    public float x, y;

    public ArrayList<Arm> arms;
    private Arm lastArm = null;

    public FKSystem(float xx, float yy)
    {
        x = xx;
        y = yy;

        arms = new ArrayList<Arm>();
    }

    public Arm addArm(float angle, float length)
    {
        Arm a;

        if(lastArm == null) a = new Arm(x, y, angle, length, null);
        else a = new Arm(lastArm.getEndX(), lastArm.getEndY(), angle, length, lastArm);

        arms.add(a);

        lastArm = a;
        return a;
    }

    public void update()
    {
        for(int i = 0; i < arms.size(); i++)
        {
            arms.get(i).update();
        }
    }

    public void render(Graphics2D g)
    {
        Stroke old = g.getStroke();
        g.setStroke(new BasicStroke(10));

        Path2D.Float path = new Path2D.Float();
        path.moveTo(x, y);

        for(int i = 0; i < arms.size(); i++)
        {
            arms.get(i).render(g);
            path.lineTo(arms.get(i).getEndX(), arms.get(i).getEndY());
        }

        //g.draw(path);

        g.setStroke(old);
    }
}
