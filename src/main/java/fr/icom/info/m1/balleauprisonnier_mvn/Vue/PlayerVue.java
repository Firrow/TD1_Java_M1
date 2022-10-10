package fr.icom.info.m1.balleauprisonnier_mvn.Vue;
import fr.icom.info.m1.balleauprisonnier_mvn.Model.Player;

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
}
