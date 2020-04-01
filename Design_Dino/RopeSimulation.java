import utilities.Input;
import utilities.Vector2f;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class RopeSimulation
{
    private final float k = 11f;

    private int N;
    private float segLength;
    private Vector2f[] points, oldPoints;

    public RopeSimulation(int numPoints)
    {
        N = numPoints;
        points = new Vector2f[N];
        oldPoints = new Vector2f[N];
        segLength = Test.WIDTH / numPoints;

        initDefaultRope();

        System.arraycopy(points, 0, oldPoints, 0, N);
    }

    private void initDefaultRope()
    {
        points[0] = new Vector2f(100f, Test.HEIGHT / 2);

        for(int i = 1; i < N; i++)
        {
            points[i] = new Vector2f(100f + i * segLength, points[0].y);
        }
    }

    public void update(float deltaTime)
    {
        ropeControls();
        updateRope(deltaTime);
    }

    private void updateRope(float deltaTime)
    {
        for(int i = 1; i < N; i++)
        {
            float distance = points[i].distance(points[i-1]);

            points[i].x = points[i-1].x + segLength * (points[i].x - points[i-1].x) / distance;
            points[i].y = points[i-1].y + segLength * (points[i].y - points[i-1].y) / distance;

            /*float distance = points[i].distance(points[i-1]);
            float a = (distance - segLength) * k;

            float ax = a * (points[i-1].x - points[i].x) / distance;
            float ay = a * (points[i-1].y - points[i].y) / distance;

            points[i].x = 2 * points[i].x - oldPoints[i].x + ax * deltaTime;
            points[i].y = 2 * points[i].y - oldPoints[i].y + ay * deltaTime;

            oldPoints[i].x = points[i].x;
            oldPoints[i].y = points[i].y;*/
        }
    }

    private float lastMouseX, lastMouseY;

    private void ropeControls()
    {
        if(Input.mouseDown(1))
        {
            points[0].x += Input.mouseX() - lastMouseX;
            points[0].y += Input.mouseY() - lastMouseY;
        }

        lastMouseX = Input.mouseX();
        lastMouseY = Input.mouseY();
    }

    public void render(Graphics2D g)
    {
        g.setColor(Color.BLACK);

        renderRope(g);
    }

    private void renderRope(Graphics2D g)
    {
        for(int i = 0; i < N-1; i++)
        {
            //g.fill(new Ellipse2D.Float(points[i].x, points[i].y, 0.4f * segLength, 0.4f * segLength));
            g.setStroke(new BasicStroke(0.5f * segLength));
            g.draw(new Line2D.Float(new Point2D.Float(points[i].x, points[i].y), new Point2D.Float(points[i+1].x, points[i+1].y)));
        }
    }
}
