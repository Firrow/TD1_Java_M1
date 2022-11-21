package fr.icom.info.m1.balleauprisonnier_mvn.Controller;

import fr.icom.info.m1.balleauprisonnier_mvn.Model.Player;
import fr.icom.info.m1.balleauprisonnier_mvn.Model.Projectile;
import fr.icom.info.m1.balleauprisonnier_mvn.Vue.ProjectileVue;
import javafx.scene.canvas.GraphicsContext;

public class ProjectileController {
	
	private ProjectileVue projectileVue;
	
	public ProjectileController() {
		
	}
    
	public void startProjectile(Projectile projectile, Player joueur, double angle, GraphicsContext gc) {
		/*if(!projectile.getMoving()) {
			projectile.setDirection(angle);
	        projectile.setMoving(true);
        } */
        projectileVue= new ProjectileVue(gc);

        projectile = Projectile.getInstance();
        projectile.setX(joueur.getX());
        projectile.setY(joueur.getY()-50);
        projectile.setVue(projectileVue);
        projectileVue.display(projectile);
        
        projectile.setDirection(angle);
        projectile.setMoving(true);
	}

	public void moveProjectile(Projectile projectile, ProjectileVue projectileVue) {
		if(projectileVue!=null) {
			if (projectile.getMoving()) {
				projectile.moveProjectile();
			}
			projectileVue.display(projectile);
		}
	}

	public void throwProjectile(Projectile projectile, double angle) {
        /*if(!projectile.getMoving()) {
            projectile.setDirection(angle);
            projectile.setMoving(true);
        } */
		projectile.setDirection(angle);
		System.out.println(angle);
		projectile.setMoving(true);
	}
	
	/*public void throwProjectile(Projectile projectile, ProjectileVue projectileVue) {
		if(projectileVue!=null) {
	        if (projectile.getMoving()) {
	        	projectile.moveProjectile();
	        }
        projectileVue.display(projectile);
		}
	}*/
}
