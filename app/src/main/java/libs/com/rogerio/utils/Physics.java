package libs.com.rogerio.utils;


/*
 * Autor: Rogerio C. Santos - rogerio@rogeriocs.com.br
 * http://www.rogeriocs.com.br
 */
public class Physics {
	static private float g=0.9f;
	
	static public  void gravity(ObjetosImg obj){
		if(obj.vel<55.5)
			obj.vel += g;
		obj.position.setY(obj.position.getY()+(int)obj.vel);
	}
	static public boolean collide(ObjetosImg obj1,ObjetosImg obj2){
		int wX = (int) (obj1.position.getX()+obj1.getObjW());
		int h  = (int) (obj1.position.getY()+obj1.getObjH());
		boolean dimension = ((obj2.position.getX()>=obj1.position.getX() && obj2.position.getX()<wX)
				|| ((obj2.position.getX()+obj2. getObjW())>=obj1.position.getX() && (obj2.position.getX()+obj2.objW)<wX));
		
		if(dimension && ((obj2.position.getY()>=obj1.position.getY()&&obj2.position.getY()<=h)
					|| (obj2.position.getY()+obj2.getObjH()>=obj1.position.getY()&&obj2.position.getY()<=h)
					|| (obj2.position.getY()>=(obj1.position.getY()+obj1.getObjH())))){
			return true;
		}
		
		
		
		return false;
	}
	static public boolean collide(Sprite obj1,Sprite obj2){
		int w = (int) (obj1.getPosition().getX()+obj1.getWidth());
		int h  = (int) (obj1.getPosition().getY()+obj1.getHeight());
		
		boolean dimension = ((obj2.getPosition().getX()>=obj1.getPosition().getX() && obj2.getPosition().getX()<w)
				|| ((obj2.getPosition().getX()+obj2.getWidth())>=obj1.getPosition().getX() 
						&& (obj2.getPosition().getX()+obj2.getWidth())<w));
		
		if(dimension && ((obj2.getPosition().getY()>=obj1.getPosition().getY()&&obj2.getPosition().getY()<=h)
					|| (obj2.getPosition().getY()+obj2.getHeight()>=obj1.getPosition().getY() && obj2.getPosition().getY()+obj2.getHeight()<=h)
					)){
			return true;
		}
		
		
		
		return false;
	}
	static public boolean collide(Sprite obj1,Vector2 obj2){
		int wX = (int) (obj1.getPosition().getX()+obj1.getWidth());
		int h  = (int) (obj1.getPosition().getY()+obj1.getHeight());
		boolean dimension = (obj2.getX()>=obj1.getPosition().getX() && obj2.getX()<wX);
		
		
		if(dimension && (obj2.getY()>=obj1.getPosition().getY()&&obj2.getY()<=h)){
			return true;
		}
		
		
		
		return false;
		
	}
	
}
