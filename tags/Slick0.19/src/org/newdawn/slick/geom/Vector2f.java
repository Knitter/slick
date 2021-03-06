package org.newdawn.slick.geom;

import org.newdawn.slick.util.FastTrig;

/**
 * A two dimensional vector
 * 
 * @author Kevin Glass
 */
public strictfp class Vector2f {
	/** The x component of this vector */
	public float x;
	/** The y component of this vector */
	public float y;
	
	/**
	 * Create an empty vector
	 */
	public Vector2f() {
	}

	/**
	 * Create a new vector based on an angle
	 * 
	 * @param theta The angle of the vector in degrees
	 */
	public Vector2f(double theta) {
		setTheta(theta);
	}

	/**
	 * Calculate the components of the vectors based on a angle
	 * 
	 * @param theta The angle to calculate the components from (in degrees)
	 */
	public void setTheta(double theta) {
		// Next lines are to prevent numbers like -1.8369701E-16
		// when working with negative numbers
		if ((theta < -360) || (theta > 360)) {
			theta = theta % 360;
		}
		if (theta < 0) {
			theta = 360 + theta;
		}
		double oldTheta = getTheta();
		if ((theta < -360) || (theta > 360)) {
			oldTheta = oldTheta % 360;
		}
		if (theta < 0) {
			oldTheta = 360 + oldTheta;
		}

		x = x / (float) FastTrig.cos(StrictMath.toRadians(oldTheta))
				* (float) FastTrig.cos(StrictMath.toRadians(theta));
		y = x / (float) FastTrig.sin(StrictMath.toRadians(oldTheta))
				* (float) FastTrig.sin(StrictMath.toRadians(theta));
	} 
	
	/**
	 * Adjust this vector by a given angle
	 * 
	 * @param theta
	 *            The angle to adjust the angle by (in degrees)
	 */
	public void add(double theta) {
		setTheta(getTheta() + theta);
	}

	/**
	 * Adjust this vector by a given angle
	 * 
	 * @param theta The angle to adjust the angle by (in degrees)
	 */
	public void sub(double theta) {
		setTheta(getTheta() - theta);
	}
	
	/**
	 * Get the angle this vector is at
	 * 
	 * @return The angle this vector is at (in degrees)
	 */
	public double getTheta() {
		double theta = StrictMath.toDegrees(StrictMath.atan2(y, x));
		if ((theta < -360) || (theta > 360)) {
			theta = theta % 360;
		}
		if (theta < 0) {
			theta = 360 + theta;
		}

		return theta;
	} 
	
	/**
	 * Get the x component
	 * 
	 * @return The x component
	 */
	public float getX() {
		return x;
	}

	/**
	 * Get the y component
	 * 
	 * @return The y component
	 */
	public float getY() {
		return y;
	}
	
	/**
	 * Create a new vector based on another
	 * 
	 * @param other The other vector to copy into this one
	 */
	public Vector2f(Vector2f other) {
		this(other.getX(),other.getY());
	}
	
	/**
	 * Create a new vector 
	 * 
	 * @param x The x component to assign
	 * @param y The y component to assign
	 */
	public Vector2f(float x, float y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Set the value of this vector
	 * 
	 * @param other The values to set into the vector
	 */
	public void set(Vector2f other) {
		set(other.getX(),other.getY());
	}
	
	/**
	 * Dot this vector against another
	 * 
	 * @param other The other vector dot agianst
	 * @return The dot product of the two vectors
	 */
	public float dot(Vector2f other) {
		return (x * other.getX()) + (y * other.getY());
	}
	
	/**
	 * Set the values in this vector
	 * 
	 * @param x The x component to set
	 * @param y The y component to set
	 */
	public void set(float x, float y) { 
		this.x = x; 
		this.y = y; 
	}

	/**
	 * Negate this vector 
	 * 
	 * @return A copy of this vector negated
	 */
	public Vector2f negate() {
		return new Vector2f(-x, -y); 
	}
	
	/**
	 * Add a vector to this vector
	 * 
	 * @param v The vector to add
	 */
	public void add(Vector2f v)
	{
		x += v.getX(); 
		y += v.getY();
	}
	
	/**
	 * Subtract a vector from this vector
	 * 
	 * @param v The vector subtract
	 */
	public void sub(Vector2f v)
	{
		x -= v.getX(); 
		y -= v.getY();
	}

	/**
	 * Scale this vector by a value
	 * 
	 * @param a The value to scale this vector by
	 */
	public void scale(float a)
	{
		x *= a; 
		y *= a;
	}

	/**
	 * Normalise the vector
	 */
	public void normalise() {
		float l = length();
		
		x /= l;
		y /= l;
	}
	
	/**
     * The normal of the vector
     * 
     * @return A unit vector with the same direction as the vector
     */
    public Vector2f getNormal() {
	   Vector2f cp = copy();
	   cp.normalise();
	   return cp;
    } 
    
	/**
	 * The length of the vector squared
	 * 
	 * @return The length of the vector squared
	 */
	public float lengthSquared() {
		return (x * x) + (y * y);
	}
	
	/**
	 * Get the length of this vector
	 * 
	 * @return The length of this vector
	 */
	public float length() 
	{
		return (float) Math.sqrt(lengthSquared());
	}
	
	/**
	 * Project this vector onto another
	 * 
	 * @param b The vector to project onto
	 * @param result The projected vector
	 */
	public void projectOntoUnit(Vector2f b, Vector2f result) {
		float dp = b.dot(this);
		
		result.x = dp * b.getX();
		result.y = dp * b.getY();
		
	}
	
	/**
	 * Return a copy of this vector
	 * 
	 * @return The new instance that copies this vector
	 */
	public Vector2f copy() {
		return new Vector2f(x,y);
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "[Vector2f "+x+","+y+" ("+length()+")]";
	}
	
	/**
	 * Get the distance from this point to another
	 * 
	 * @param other The other point we're measuring to
	 * @return The distance to the other point
	 */
	public float distance(Vector2f other) {
		float dx = other.getX() - getX();
		float dy = other.getY() - getY();
		
		return (float) Math.sqrt((dx*dx)+(dy*dy));
	}
	
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return (int) ((x*y)*1000);
	}
	
	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object other) {
		if (other instanceof Vector2f) {
			Vector2f o = ((Vector2f) other);
			return (o.x == x) && (o.y == y);
		}
		
		return false;
	}
}
