package fr.icom.info.m1.balleauprisonnier_mvn.Vue;
import fr.icom.info.m1.balleauprisonnier_mvn.Model.Player;
import fr.icom.info.m1.balleauprisonnier_mvn.Sprite;

public class PlayerVue {

    private Sprite sprite;

    //vient de la classe sprite




    public PlayerVue(Sprite sprite){
        this.sprite = sprite;
    }

    public Sprite getSprite() {
        return this.sprite;
    }

    public void spriteAnimate(Player player){
        //System.out.println("Animating sprite");
        if(!sprite.isRunning) {sprite.playContinuously();}
        sprite.setX(player.getX());
        sprite.setY(player.getY());
    }

    public void playShoot(){
        sprite.setFrameCounter(0);
        sprite.getTimeline().stop();
        sprite.setTimeline(sprite.getShootTimeline());
        sprite.getTimeline().setCycleCount(sprite.getNumCellsShoot());
        sprite.getTimeline().setOnFinished(e -> sprite.playContinuously());
        sprite.getTimeline().playFromStart();
    }

    /*public void setX(double x) {
        sprite.setX(player.getX());
        sprite.setY(player.getY());
        //todo : définir position sprite en fonction de Player
    }*/
}
