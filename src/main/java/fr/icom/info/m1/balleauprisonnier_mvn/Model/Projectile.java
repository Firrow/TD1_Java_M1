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
	
	/*
	 * Accesseurs et mutateurs
	 */
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


	/*
	 * Constructeur prive car singleton
	 */
	private Projectile() {
		this.vitesse=1;
		direction=0;
		moving=false;
		
	}
	
	/*
	 * Recupere l'instance du projectile si il existe deja, sinon il est cree
	 */
    public static Projectile getInstance()
    {
        if (singletonProjectile == null)
            singletonProjectile = new Projectile();
  
        return singletonProjectile;
    }

	/*
	 * Mise a jour de la position du projectile
	 */
	public void moveProjectile() {
		this.x+=vitesse*Math.sin(Math.toRadians(direction));
		this.y-=vitesse*Math.cos(Math.toRadians(direction));
	}

	/*
	 * Le projectile est positionne relativement au joueur
	 */
	public void withPlayerMove(Player player, int miseANiveau){
		if(player.isTake_ball()){
			this.x = player.getX();
			this.y = player.getY()+miseANiveau;
		}
	}

	public BoundingBox getBoundingBox(){
		return new BoundingBox(getX(), getY(), projectileVue.getImage().getWidth(), projectileVue.getImage().getHeight());
	}
}
