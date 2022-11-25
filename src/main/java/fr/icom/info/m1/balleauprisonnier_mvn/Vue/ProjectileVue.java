package fr.icom.info.m1.balleauprisonnier_mvn.Vue;

import fr.icom.info.m1.balleauprisonnier_mvn.Model.Projectile;
import javafx.geometry.BoundingBox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;


public class ProjectileVue extends ImageView {
	
  public Image projectileImg;
  public GraphicsContext gc;
  
  public ProjectileVue(GraphicsContext gc){
      this.gc = gc;
      projectileImg = new Image("assets/ball.png");
      super.setImage(projectileImg);
  }
  
  public void display(Projectile projectile) {

      gc.save(); // saves the current state on stack, including the current transform
      //rotate(gc, projectile.getDirection(), projectile.getX(), projectile.getY());
      gc.drawImage(projectileImg, projectile.getX(), projectile.getY());
      gc.restore(); // back to original state (before rotation)
  }
  
}
