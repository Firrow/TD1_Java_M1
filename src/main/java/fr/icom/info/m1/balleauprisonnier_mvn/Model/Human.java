package fr.icom.info.m1.balleauprisonnier_mvn.Model;

import fr.icom.info.m1.balleauprisonnier_mvn.Vue.PlayerVue;
import javafx.scene.canvas.GraphicsContext;

public class Human extends Player {

    public Human(GraphicsContext gc, int xInit, int yInit, PlayerVue playerVue) {
        super(gc, xInit, yInit, playerVue);
    }
}
