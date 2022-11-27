package fr.icom.info.m1.balleauprisonnier_mvn.Vue;

import fr.icom.info.m1.balleauprisonnier_mvn.Model.Projectile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class ProjectileVue extends ImageView {
	
  public Image projectileImg;
  public GraphicsContext gc;
  
  public ProjectileVue(GraphicsContext gc){
      this.gc = gc;
      projectileImg = new Image("assets/ball.png");
      super.setImage(projectileImg);
  }
  
  public void display(Projectile projectile) {

      gc.save(); // sauvegarde l'etat courant dans la pile
      gc.drawImage(projectileImg, projectile.getX(), projectile.getY());
      gc.restore(); // revient a l'etat d'origine (avant la rotation)
  }
  
}
