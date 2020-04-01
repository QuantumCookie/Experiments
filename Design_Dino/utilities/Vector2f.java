package utilities;

public class Vector2f
{
    public float x, y;
    
    public Vector2f(float i, float j)
    {
        x = i;
        y = j;
    }
    
    public Vector2f(Vector2f other)
    {
        x = other.x;
        y = other.y;
    }
    
    public Vector2f()
    {
        this(0, 0);
    }
    
    public Vector2f add(Vector2f other)
    {
        x += other.x;
        y += other.y;

        return  this;
    }

    public Vector2f subtract(Vector2f other)
    {
        x -= other.x;
        y -= other.y;

        return this;
    }

    public Vector2f multiply(float other)
    {
        x *= other;
        y *= other;

        return  this;
    }

    public Vector2f divide(float other)
    {
        x /= other;
        y /= other;

        return  this;
    }
    
    public float dot(Vector2f other)
    {
        return x * other.x + y * other.y;
    }
    
    public float cross(Vector2f other)
    {
        return x * other.y - other.x * y;
    }
    
    public float magnitude()
    {
        return (float)Math.sqrt(x * x + y * y);
    }

    public float distance(Vector2f other){return (float)Math.sqrt((x - other.x) * (x - other.x) + (y - other.y) * (y - other.y));}
    
    public void normalize()
    {
        float mag = magnitude();
        x /= mag;
        y /= mag;
    }
}