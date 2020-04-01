import java.awt.*;
import java.awt.geom.Line2D;

public class IK_Arm
{
    public IK_Arm parent;

    public float x, y;
    public float length;
    public float angle;

    public IK_Arm(float _x, float _y, float _length, IK_Arm _parent)
    {
        x = _x;
        y = _y;

        angle = 0f;
        length = _length;
        parent = _parent;
    }

    public float getEndX()
    {
        return x + length * (float) Math.cos(angle);
    }

    public float getEndY()
    {
        return y + length * (float) Math.sin(angle);
    }

    public void pointAt(float targetX, float targetY)
    {
        this.angle = (float) Math.atan2(targetY - y, targetX - x);
    }

    public void update()
    {

    }

    public void render(Graphics2D g)
    {
        if(parent == null)
            g.setColor(Color.red);
        else
            g.setColor(Color.black);

        g.draw(new Line2D.Float(x, y, getEndX(), getEndY()));
    }
}
