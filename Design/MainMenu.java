import utilities.Input;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class MainMenu
{
    private GameManager gameManager;

    private static class GButton
    {
        float x, y, w, h;
    }

    private GButton newGame, quit;

    MainMenu(GameManager g)
    {
        //game = Test.getInstance();
        gameManager = g;

        newGame = new GButton();
        quit = new GButton();

        newGame.w = quit.w = Test.WIDTH / 3;
        newGame.h = quit.h = Test.HEIGHT / 12;

        newGame.x = (Test.WIDTH - newGame.w) / 2;               //Middle of the screen
        newGame.y = ((2 * Test.HEIGHT / 3) - newGame.h) / 2;    //One-third from the top

        quit.x = (Test.WIDTH - quit.w) / 2;                     //Middle of the screen
        quit.y = ((4 * Test.HEIGHT / 3) - quit.h) / 2;          //One-third from bottom
    }

    public void render(Graphics2D g)
    {
        Stroke oldStroke = g.getStroke();
        Font oldFont = g.getFont();

        g.setStroke(new BasicStroke(5));
        g.setFont(new Font("Arial", Font.BOLD | Font.CENTER_BASELINE, 26));

        FontMetrics fm = g.getFontMetrics();

        float sx, sy;

        sx = (2 * newGame.x + newGame.w - fm.stringWidth("New Game")) / 2;
        sy = (2 * newGame.y + newGame.h + fm.getHeight()-fm.getAscent()/2) / 2;

        g.draw(new Rectangle2D.Float(newGame.x, newGame.y, newGame.w, newGame.h));
        g.drawString("New Game", sx, sy);

        sx = (2 * quit.x + quit.w - fm.stringWidth("Quit Game")) / 2;
        sy = (2 * quit.y + quit.h + fm.getHeight()-fm.getAscent()/2) / 2;

        g.draw(new Rectangle2D.Float(quit.x, quit.y, quit.w, quit.h));
        g.drawString("Quit Game", sx, sy);

        g.setFont(oldFont);
        g.setStroke(oldStroke);
    }

    public void update()
    {
        if(Input.mouseDown())
        {
            int x = Input.mouseX();
            int y = Input.mouseY();
            if(contains(newGame, x, y))gameManager.newGame();
            else if(contains(quit, x, y))gameManager.quit();
        }
    }

    private boolean contains(GButton b, int x, int y)
    {
        return x > b.x && x < b.x + b.w &&
                y > b.y && y < b.y + b.h;
    }
}
