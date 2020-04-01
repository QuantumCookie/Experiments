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
    
    public void add(Vector2f other)
    {
        x += other.x;
        y += other.y;
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
    
    public void normalize()
    {
        float mag = magnitude();
        x /= mag;
        y /= mag;
    }
}