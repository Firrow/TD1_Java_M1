package fr.icom.info.m1.balleauprisonnier_mvn.Controller;

import fr.icom.info.m1.balleauprisonnier_mvn.Model.Player;
import fr.icom.info.m1.balleauprisonnier_mvn.Model.Projectile;
import fr.icom.info.m1.balleauprisonnier_mvn.Vue.ProjectileVue;
import javafx.scene.canvas.GraphicsContext;

public class ProjectileController {
	
	private ProjectileVue projectileVue;
	
	public ProjectileController() {
		
	}
    
	/*
	 * Creation du projectile
	 */
	public void startProjectile(Projectile projectile, Player player, double angle, GraphicsContext gc) {
        projectileVue= new ProjectileVue(gc);

        projectile = Projectile.getInstance();
        projectile.setX(player.getX());
        projectile.setY(player.getY()-50);
        projectile.setVue(projectileVue);
        projectileVue.display(projectile);
        
        projectile.setDirection(angle);
        projectile.setMoving(true);
	}

	/*
	 * Deplace le projectile et modifie son modele puis fait appel à sa vue
	 */
	public void moveProjectile(Projectile projectile, ProjectileVue projectileVue) {
		if(projectileVue!=null) {
			if (projectile.getMoving()) {
				projectile.moveProjectile();
			}
			projectileVue.display(projectile);
		}
	}

	/*
	 * Change la direction du projectile et le lance
	 */
	public void throwProjectile(Projectile projectile, double angle) {
		projectile.setMoving(true);
		projectile.setDirection(angle);
	}
}
