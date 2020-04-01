import utilities.Input;
import utilities.Vector2f;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Simulation
{
    public class Mass
    {
        public Vector2f position;
        public float mass;

        public Mass(float m)
        {
            mass = m;
            position = new Vector2f();
        }
    }

    /*
    private Vector2f basic;

    public float ax, ay;
    public float px, py, x, y;
    public float vx, vy;*/

    Mass pivot, load;
    float radius;

    float lax, lay, lvx, lvy, oldx, oldy;

    public Simulation()
    {
        /*ax = 0f;
        ay = 1f;

        px = x = 20f;
        py = y = 20f;*/

        radius = 100f;

        load = new Mass(1);
        pivot = new Mass(1);

        pivot.position = new Vector2f(Test.WIDTH / 3, Test.HEIGHT / 3);
        load.position = new Vector2f(pivot.position.x + radius, pivot.position.y);

        oldx = load.position.x;
        oldy = load.position.y;
    }

    float lastX, lastY;
    float k = 10f;
    float distance;

    private void applyForces(float deltaTime)
    {
        if(Input.mouseDown(1))
        {
            pivot.position.x += Input.mouseX() - lastX;
            pivot.position.y += Input.mouseY() - lastY;
        }

        lastX = Input.mouseX();
        lastY = Input.mouseY();

        //lvx = (load.position.x - oldx) + lax * deltaTime;
        //lvy = (load.position.y - oldy) + lay * deltaTime;

        load.position.x = (2f * oldx - load.position.x) + lax * deltaTime;
        load.position.y = (2f * oldy - load.position.y) + lay * deltaTime;

        oldx = load.position.x;
        oldy = load.position.y;
    }

    private void applyConstraints(float deltaTime)
    {
        distance = pivot.position.distance(load.position);

        float a = -k * (radius - distance);

        lax = a * (pivot.position.x - load.position.x) / distance;
        lay = a * (pivot.position.y - load.position.y) / distance;
    }

    private void simulate(float deltaTime)
    {
        /*
        vx = (x - px) + ax * deltaTime;
        vy = (y - py) + ay * deltaTime;
        px = x;
        py = y;

        x += vx;
        y += vy;*/
    }

    public float timer = 0f;

    public void update(float deltaTime)
    {
        //applyForces(deltaTime);
        applyConstraints(deltaTime);
        applyForces(deltaTime);
        simulate(deltaTime);

        timer += deltaTime;

        if(timer > 1f)
        {
            System.out.println(deltaTime);
            timer = 0f;
        }
    }

    public void render(Graphics2D g)
    {
        g.setColor(Color.BLACK);
        //g.fill(new Ellipse2D.Float(m.position.x, m.position.y, 40f, 40f));
        g.fill(new Ellipse2D.Float(load.position.x - 20f, load.position.y - 20f, 40f, 40f));
        g.fill(new Ellipse2D.Float(pivot.position.x - 20f, pivot.position.y - 20f, 40f, 40f));
            g.drawString(String.format("Position: ( %.2f, %.2f )", load.position.x, load.position.y), 100f, 20f);
        /*g.drawString(String.format("Velocity: ( %.2f, %.2f )", vx, vy), 100f, 40f);*/

        //g.drawString(String.format(Input.mouseDown(0) ? "Clicked" : "Nah"), 100f, 20f);
    }
}
