package fr.icom.info.m1.balleauprisonnier_mvn.Controller;

import fr.icom.info.m1.balleauprisonnier_mvn.Model.Projectile;
import fr.icom.info.m1.balleauprisonnier_mvn.Vue.ProjectileVue;

public class ProjectileController {
	
	private static ProjectileController singletonProjectileC;
	private ProjectileController() {
		
	}
	
    public static ProjectileController getInstance()
    {
        if (singletonProjectileC == null)
            singletonProjectileC = new ProjectileController();
  
        return singletonProjectileC;
    }
    
	public void startProjectile(Projectile projectile, ProjectileVue projectileVue, double angle) {
		/*if(!projectile.getMoving()) {
			projectile.setDirection(angle);
	        projectile.setMoving(true);
			}*/  //A décommenter
        projectile.setDirection(angle);
        projectile.setMoving(true);
	}
	
	public void throwProjectile(Projectile projectile, ProjectileVue projectileVue) {
        if (projectile.getMoving()) {
        	projectile.moveProjectile();
        	projectileVue.display(projectile);
        }

	}
}
