import java.awt.*;
import java.awt.geom.Line2D;

public class Arm
{
    public Arm parent;
    public float angle, length, x, y;

    public Arm(float xx, float yy, float _angle, float _length, Arm _parent)
    {
        angle = _angle;
        length = _length;
        parent = _parent;
        x = xx;
        y = yy;
    }

    public float getEndX()
    {
        float netAngle = angle;

        Arm tmp = parent;
        while (tmp != null)
        {
            netAngle += tmp.angle;
            tmp = tmp.parent;
        }

        return x + length * (float) Math.cos(Math.toRadians(netAngle));
    }

    public float getEndY()
    {
        float netAngle = angle;

        Arm tmp = parent;
        while (tmp != null)
        {
            netAngle += tmp.angle;
            tmp = tmp.parent;
        }

        return y + length * (float) Math.sin(Math.toRadians(netAngle));
    }

    public void update()
    {
        if(parent != null)
        {
            x = parent.getEndX();
            y = parent.getEndY();
        }

        //angle += 15 * (float) Math.sin(Math.toRadians(System.currentTimeMillis()));
    }

    public void render(Graphics2D g)
    {
        g.draw(new Line2D.Float(x, y, getEndX(), getEndY()));
    }
}
