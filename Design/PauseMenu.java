import utilities.Input;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class PauseMenu
{
    private GameManager gameManager;

    private static class GButton
    {
        float x, y, w, h;
    }

    private GButton resume, restart, mainMenu, quit;

    PauseMenu(GameManager g)
    {
        //game = Test.getInstance();
        gameManager = g;

        resume = new GButton();
        restart = new GButton();
        mainMenu = new GButton();
        quit = new GButton();

        resume.w = quit.w = restart.w = mainMenu.w = Test.WIDTH / 3;
        resume.h = quit.h = restart.h = mainMenu.h = Test.HEIGHT / 12;

        resume.x = (Test.WIDTH - resume.w) / 2;                     //Middle of the screen
        resume.y = (Test.HEIGHT / 5) - (resume.h / 2);          //One-third from the top

        restart.x = (Test.WIDTH - restart.w) / 2;                     //Middle of the screen
        restart.y = (2 * Test.HEIGHT / 5) - (restart.h / 2);          //One-third from bottom

        mainMenu.x = (Test.WIDTH - mainMenu.w) / 2;                     //Middle of the screen
        mainMenu.y = (3 * Test.HEIGHT / 5) - (mainMenu.h / 2);          //One-third from the top

        quit.x = (Test.WIDTH - quit.w) / 2;                         //Middle of the screen
        quit.y = (4 * Test.HEIGHT / 5) - (quit.h / 2);              //One-third from bottom
    }

    public void render(Graphics2D g)
    {
        Stroke oldStroke = g.getStroke();
        Font oldFont = g.getFont();

        g.setStroke(new BasicStroke(5));
        g.setFont(new Font("Arial", Font.BOLD | Font.CENTER_BASELINE, 26));

        FontMetrics fm = g.getFontMetrics();

        float sx, sy;

        sx = (2 * resume.x + resume.w - fm.stringWidth("Resume")) / 2;
        sy = (2 * resume.y + resume.h + fm.getHeight()-fm.getAscent()/2) / 2;

        g.draw(new Rectangle2D.Float(resume.x, resume.y, resume.w, resume.h));
        g.drawString("Resume", sx, sy);

        sx = (2 * restart.x + restart.w - fm.stringWidth("Restart")) / 2;
        sy = (2 * restart.y + restart.h + fm.getHeight()-fm.getAscent()/2) / 2;

        g.draw(new Rectangle2D.Float(restart.x, restart.y, restart.w, restart.h));
        g.drawString("Restart", sx, sy);

        sx = (2 * mainMenu.x + mainMenu.w - fm.stringWidth("Main Menu")) / 2;
        sy = (2 * mainMenu.y + mainMenu.h + fm.getHeight()-fm.getAscent()/2) / 2;

        g.draw(new Rectangle2D.Float(mainMenu.x, mainMenu.y, mainMenu.w, mainMenu.h));
        g.drawString("Main Menu", sx, sy);

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
            if(contains(resume, x, y))gameManager.togglePause();
            else if(contains(restart, x, y))gameManager.newGame();
            else if(contains(mainMenu, x, y))gameManager.mainMenu();
            else if(contains(quit, x, y))gameManager.quit();
        }
    }

    private boolean contains(GButton b, int x, int y)
    {
        return x > b.x && x < b.x + b.w &&
                y > b.y && y < b.y + b.h;
    }
}
