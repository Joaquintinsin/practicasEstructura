package clases;

/**
 * A Class in Java that can perform addition or substraction over Rational Numbers
 * @autor Joaquín Tissera
 */

public class RationalClass {
    // Atributes, where b is used for denominator, b != 0
    private long a;
    private long b;
    
    /**
    * Constructor with no parameters.
    * Builds a RationalClass with a=1, b=1
    */
    public RationalClass() {
        a = 1;
        b = 1;
    }
    
    /**
     * Constructor with parameters.
     * @throws an exception when second parameter is less or equal than 0.
     */
    public RationalClass(long number1, long number2) {
        if (number2 <= 0) throw new IllegalArgumentException("El segundo parámetro tiene que ser positivo");
        a = number1;
        b = number2;
    }
    
    /**
     * Getter from the first attribute.
     * @return the first attribute.
     */
    public long getA() {
        return a;
    }
    
    /**
     * Setter from the first attribute.
     */
    public void setA(long number) {
        a = number;
    }
    
    /**
     * Getter from the second attribute.
     * @return the second attribute.
     */
    public long getB() {
        return b;
    }
    
    /**
     * Setter from second attribute.
     * @throws an exception when try to set a non-positive number
     */
    public void setB(long number) {
        if (number <= 0) throw new IllegalArgumentException("El segundo parámetro tiene que ser positivo");
        b = number;
    }
    
    /**
     * The addition on rational numbers
     * @return an abstract type RationalClass that contains both attributes aditionated each others.
     */
    public RationalClass suma(RationalClass rationalNumber) {
        if (rationalNumber == null || !(rationalNumber instanceof RationalClass)) 
        throw new IllegalArgumentException("No se le pasó un racional a la suma de racionales");
        // podría devolver null y no tirar excepción también
        
        if (rationalNumber.getB() != b){
            long nuevoNominador = (rationalNumber.getA() * b) + (rationalNumber.getB() * a);
            long nuevoDenominador = rationalNumber.getB() * b;
            RationalClass racionalResultado = new RationalClass(nuevoNominador, nuevoDenominador);
            racionalResultado.setA(nuevoNominador);
            racionalResultado.setB(nuevoDenominador);
            return racionalResultado;
        }
        RationalClass racionalResultado = new RationalClass(rationalNumber.getA() + a, b);
        return racionalResultado;
    }
    
    /**
     * The substraction on rational numbers
     * @return an abstract type RationalClass that contains both attributes substracted each others.
     *        it can be null if @param is not a RationalClass abstract type
     */
    public RationalClass resta(RationalClass rationalNumber) {
        if (!(rationalNumber instanceof RationalClass))
			return null;
        
        if (rationalNumber.getB() != b){
            long nuevoNominador = rationalNumber.getB() * a - rationalNumber.getA() * b;
            long nuevoDenominador = rationalNumber.getB() * b;
            RationalClass racionalResultado = new RationalClass(nuevoNominador, nuevoDenominador);
            racionalResultado.setA(nuevoNominador);
            racionalResultado.setB(nuevoDenominador);
            return racionalResultado;
        }
        RationalClass racionalResultado = new RationalClass(rationalNumber.getA() - a, b);
        return racionalResultado;
    }
    
    /**
     * The method equals, override from Java Object
     * @return = true when @param is the same class that the method calls or attributes are both the same
     *      otherwise @return = false
     */
    @Override
	public boolean equals(Object rationalNumber) {
		if (rationalNumber == null)
			return false;
		if (rationalNumber == this)
			return true;
		if (!(rationalNumber instanceof RationalClass))
			return false;
		
        RationalClass secondRationalNumber = (RationalClass) rationalNumber;
		return (secondRationalNumber.getA() == a && secondRationalNumber.getB() == b);
	}
    
    /**
     * The method toString, override from Java Object
     * @return the string that shows the number like a fraction
     */
    @Override
    public String toString() {
        return "El nro racional es " + a + "/" + b + "\n";
    }
}
