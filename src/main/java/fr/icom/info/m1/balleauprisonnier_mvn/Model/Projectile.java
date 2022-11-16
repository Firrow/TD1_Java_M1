package fr.icom.info.m1.balleauprisonnier_mvn.Model;
import fr.icom.info.m1.balleauprisonnier_mvn.Vue.ProjectileVue;
import javafx.geometry.BoundingBox;

public class Projectile {

	private double x;       // position horizontale du projectile
	private double y;		// position verticale du projectile
	private double vitesse;
	private double direction;
	private ProjectileVue projectileVue;
	private boolean moving;
	private static Projectile singletonProjectile;
	
	public ProjectileVue getVue() {return projectileVue;}
	public void setVue(ProjectileVue projectileVue) {this.projectileVue = projectileVue;}
	public double getX() {return x;}
	public void setX(double x) {this.x = x;}
	public double getY() {return y;}
	public void setY(double y) {this.y = y;}
	public double getDirection() {return direction;}
	public void setDirection(double direction) {this.direction = direction;}
	public boolean getMoving() {return moving;}
	public void setMoving(boolean moving) {this.moving=moving;}


	private Projectile() {
		this.vitesse=1;
		direction=0;
		moving=false;
		
	}
    public static Projectile getInstance()
    {
        if (singletonProjectile == null)
            singletonProjectile = new Projectile();
  
        return singletonProjectile;
    }

	
	public void moveProjectile() {
		this.x+=vitesse*Math.sin(Math.toRadians(direction));
		this.y-=vitesse*Math.cos(Math.toRadians(direction));
	}

	public BoundingBox getBoundingBox(){
		return new BoundingBox(getX(), getY(), projectileVue.getImage().getWidth(), projectileVue.getImage().getHeight());
	}
}