package fr.icom.info.m1.balleauprisonnier_mvn.Vue;
import fr.icom.info.m1.balleauprisonnier_mvn.Model.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

public class PlayerVue {

    private Sprite sprite;
    public GraphicsContext gc;
    public Image dirArrowPlayer;
    public double angle;
    public double x;
    public double y;

    
    /*
     * Accesseurs et mutateurs
     */
    public Image getdirArrowPlayer() { return this.dirArrowPlayer; }
    public double getX() { return this.x; }
    public void setX(double x) { this.x = x; }
    public double getY() { return this.y; }
    public void setY(double y) { this.y = y; }
    public double getAngle() { return angle; }
    public void setAngle(double angle) { this.angle = angle; }



    public PlayerVue(Sprite sprite, GraphicsContext gc, String side, double anglePlayer, double xPlayer, double yPlayer){
        this.sprite = sprite;
        this.gc = gc;
        this.dirArrowPlayer = createArrow(side).getImage();
        this.angle = anglePlayer;
        this.x = xPlayer;
        this.y = yPlayer;
    }

    public Sprite getSprite() {
        return this.sprite;
    }

    public ImageView createArrow(String side){

        Image directionArrow;

        if(side.equals("top")){
            directionArrow = new Image("assets/PlayerArrowDown.png");
        }
        else{
            directionArrow = new Image("assets/PlayerArrowUp.png");
        }

        ImageView playerDirectionArrow = new ImageView();
        playerDirectionArrow.setImage(directionArrow);
        playerDirectionArrow.setFitWidth(10);
        playerDirectionArrow.setPreserveRatio(true);
        playerDirectionArrow.setSmooth(true);
        playerDirectionArrow.setCache(true);

        return playerDirectionArrow;
    }

    public void spriteAnimate(Player player){
        if(!sprite.isRunning) {sprite.playContinuously();}
        sprite.setX(player.getX());
        sprite.setY(player.getY());
    }

    public void display(Player player)
    {
        gc.save(); // sauvegarde l'etat courant dans la pile
        rotate(gc, player.getAngle(), player.getX() + dirArrowPlayer.getWidth() / 2, player.getY() + dirArrowPlayer.getHeight() / 2);
        gc.drawImage(dirArrowPlayer, player.getX(), player.getY());
        gc.restore(); // revient a l'etat d'origine (avant la rotation)
    }

    private void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }
}
