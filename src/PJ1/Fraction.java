/*************************************************************************************
 *
 * This class represents a fraction whose numerator and denominator are integers.
 *
 * Requirements:
 *      Implement interfaces: FractionInterface and Comparable (i.e. compareTo())
 *      Implement methods equals() and toString() from class Object
 *
 *      Should work for both positive and negative fractions
 *      Must always reduce fraction to lowest term
 *      For number such as 3/-10, it is same as -3/10 (see hints 2. below)
 *      Must display negative fraction as -x/y,
 *         example: (-3)/10 or 3/(-10), must display as -3/10
 *      Must throw exception in case of errors
 *      Must not add new or modify existing data fields
 *      Must not add new public methods
 *      May add private methods
 *
 * Hints:
 *
 * 1. To reduce a fraction such as 4/8 to lowest terms, you need to divide both
 *    the numerator and the denominator by their greatest common denominator.
 *    The greatest common denominator of 4 and 8 is 4, so when you divide
 *    the numerator and denominator of 4/8 by 4, you get the fraction 1/2.
 *    The recursive algorithm which finds the greatest common denominator of
 *    two positive integers is implemented (see code)
 *
 * 2. It will be easier to determine the correct sign of a fraction if you force
 *    the fraction's denominator to be positive. However, your implementation must
 *    handle negative denominators that the client might provide.
 *
 * 3. You need to downcast reference parameter FractionInterface to Fraction if
 *    you want to use it as Fraction. See add, subtract, multiply and divide methods
 *
 * 4. Use "this" to access this object if it is needed
 *
 ************************************************************************************/

package PJ1;

import static java.lang.Math.abs;

public class Fraction implements FractionInterface, Comparable<Fraction>
{
	private	int num;	// Numerator	
	private	int den;	// Denominator	

	public Fraction()
	{
		// set fraction to default = 0/1
		setFraction(0, 1);
	}	// end default constructor

	public Fraction(int numerator, int denominator)
	{
		// implement this method!
          
            if(denominator < 0){
                denominator = abs(denominator);
                numerator = -numerator;
            }
            
            if(numerator < 0 && denominator < 0){
                numerator = abs(numerator);
                denominator = abs(denominator);
            }
            setFraction(numerator, denominator);
            
            
	}	// end constructor

	public void setFraction(int numerator, int denominator)
	{
		// return ArithmeticException if initialDenominator is 0
		if(denominator == 0){
                    //throw exception
                    throw new ArithmeticException("Denominator can't be zero");
                }
                if(denominator < 0){
                denominator = abs(denominator);
                numerator = -numerator;
                }
            
                if(numerator < 0 && denominator < 0){
                numerator = abs(numerator);
                denominator = abs(denominator);
                }
                
                
                num = numerator;
                den = denominator;
	}	// end setFraction

	public int getNumerator()
	{
		// implement this method!
		return num;
	}	// end getNumerator

	public int getDenominator()
	{
		// implement this method!
		return den;
	}	// end getDenominator

	public char getSign()
	{
		// implement this method!
                int x = this.num;
                int y = this.den;
                char result = '\0';
                char neg = '-';
                char pos = '+';
                if((x < 0) || y < 0)
                    result = neg;
                else
                    result = pos;
		return result;
	}	// end getSign

	public void switchSign()
	{
		// implement this method!
		num = -num;
	}	// change setSign

	public FractionInterface add(FractionInterface secondFraction)
	{
		// a/b + c/d is (ad + cb)/(bd)
                FractionInterface result = new Fraction();
                Fraction a = (Fraction) result;  // sets fraction A to casted fraction interface result;
                a =  new Fraction((this.num * secondFraction.getDenominator() + 
                        secondFraction.getNumerator()*this.den), (this.den*secondFraction.getDenominator()));
		// implement this method!
                a.reduceToLowestTerms();
                
		return a;
	}	// end add

	public FractionInterface subtract(FractionInterface secondFraction)
	{
		// a/b - c/d is (ad - cb)/(bd)
		// implement this method!
                FractionInterface result = new Fraction();
                Fraction a = (Fraction) result;
                a =  new Fraction ((this.num * secondFraction.getDenominator()
                        - secondFraction.getNumerator() * this.den), (this.den * secondFraction.getDenominator()));
		a.reduceToLowestTerms();
               
                return a;
	}	// end subtract

	public FractionInterface multiply(FractionInterface secondFraction)
	{
		// a/b * c/d is (ac)/(bd)
		FractionInterface result = new Fraction();
                Fraction a = (Fraction) result;
                a =  new Fraction (this.num * secondFraction.getNumerator(),
                        (this.den * secondFraction.getDenominator())); 
		a.reduceToLowestTerms();
                return a;
	}	// end multiply

	public FractionInterface divide(FractionInterface secondFraction)
	{
		// return ArithmeticException if secondFraction is 0
		// a/b / c/d is (ad)/(bc)
		FractionInterface result = new Fraction();
                Fraction a = (Fraction) result;
                a =  new Fraction ((this.num * secondFraction.getDenominator()),
                        (this.den * secondFraction.getNumerator()));
                a.reduceToLowestTerms();
                return a;
	}	// end divide

	public FractionInterface getReciprocal() 
	{
		// return ArithmeticException if secondFraction is 0
		FractionInterface result = new Fraction();
                Fraction a = (Fraction) result;
                result = new Fraction(this.den, this.num);
                if (this.den == 0){
                    throw new ArithmeticException("Denominator can't be zero");
                }
		return result;
	} // end getReciprocal


	public boolean equals(Object other)
	{
		// implement this method!
            boolean result = false;
            this.reduceToLowestTerms();
            // reduces Object other to lowest terms
            ((Fraction)other).reduceToLowestTerms(); 
            // to get values of num and den of object other in int form
            int fNum = ((Fraction)other).num;
            int fDen = ((Fraction)other).den;
            
            if((this.num == fNum) && (this.den == fDen))
            {
                result = true;
            }
            else if(this != other)
            {
                result = false;
            }
		return result;
	} // end equals


	public int compareTo(Fraction other)
	{
		// implement this method!
            int result = 2;
            this.reduceToLowestTerms();
            // reduces Object other to lowest terms
            ((Fraction)other).reduceToLowestTerms(); 
            
            // used to compare objects
           int left = this.num * ((Fraction)other).den;
           int right = this.den * ((Fraction)other).num;
            
            if(this.equals(other))
                result = 0;
            // meaning this < other
            else if(left < right)
                result = -1;
            // meaning this > other
            else 
                result = 1;
		return result;
	} // end compareTo

	
	public String toString()
	{
		return num + "/" + den;
	} // end toString


	/** Task: Reduces a fraction to lowest terms. */

        //-----------------------------------------------------------------
        //  private methods start here
        //-----------------------------------------------------------------

	private void reduceToLowestTerms()
	{
                
                int common = greatestCommonDivisor(abs(num), abs(den));
                //System.out.println("In Reduce method common: " + common);
               
                num = num/common;
                den = den/common;
                
	}	// end reduceToLowestTerms

  	/** Task: Computes the greatest common secondFraction of two integers.
	 *  @param integerOne	 an integer
	 *  @param integerTwo	 another integer
	 *  @return the greatest common divisor of the two integers */
	private int greatestCommonDivisor(int integerOne, int integerTwo)
	{
		 int result;

		 if (integerOne % integerTwo == 0)
			result = integerTwo;
		 else
			result = greatestCommonDivisor(integerTwo, integerOne % integerTwo);

		 return result;
	}	// end greatestCommonDivisor


	//-----------------------------------------------------------------
	//  Simple test is provided here 

	public static void main(String[] args)
	{
		FractionInterface firstOperand = null;
		FractionInterface secondOperand = null;
		FractionInterface result = null;

		Fraction nineSixteenths = new Fraction(9, 16);	// 9/16
		Fraction oneFourth = new Fraction(1, 4);        // 1/4

		// 7/8 + 9/16
		firstOperand = new Fraction(7, 8);
		result = firstOperand.add(nineSixteenths);
		System.out.println("The sum of " + firstOperand + " and " +
				nineSixteenths + " is \t\t" + result);

		// 9/16 - 7/8
		firstOperand = nineSixteenths;
		secondOperand = new Fraction(7, 8);
		result = firstOperand.subtract(secondOperand);
		System.out.println("The difference of " + firstOperand	+
				" and " +	secondOperand + " is \t" + result);

		// 15/-2 * 1/4
		firstOperand.setFraction(15, -2);
		result = firstOperand.multiply(oneFourth);
		System.out.println("The product of " + firstOperand	+
				" and " +	oneFourth + " is \t" + result);

		// (-21/2) / (3/7)
		firstOperand.setFraction(-21, 2);
		secondOperand.setFraction(3, 7);
		result = firstOperand.divide(secondOperand);
		System.out.println("The quotient of " + firstOperand	+
				" and " +	secondOperand + " is \t" + result);

		// -21/2 + 7/8
		firstOperand.setFraction(-21, 2);
		secondOperand.setFraction(7, 8);
		result = firstOperand.add(secondOperand);
		System.out.println("The sum of " + firstOperand	+
				" and " +	secondOperand + " is \t\t" + result);
                
                System.out.println("\nReciprocal Test: " + secondOperand.getReciprocal());
                
		System.out.println();
                
                // fist = -21/2  second = 7/8 

		// equality check
		if (firstOperand.equals(firstOperand))
			System.out.println("Identity of fractions OK");
		else
			System.out.println("ERROR in identity of fractions");

		secondOperand.setFraction(-42, 4);
		if (firstOperand.equals(secondOperand))
			System.out.println("Equality of fractions OK");
		else
			System.out.println("ERROR in equality of fractions");

                
		// comparison check
		Fraction first  = (Fraction)firstOperand;
		Fraction second = (Fraction)secondOperand;
		
		if (first.compareTo(second) == 0)
			System.out.println("Fractions == operator OK");
		else
			System.out.println("ERROR in fractions == operator");

		second.setFraction(7, 8);
		if (first.compareTo(second) < 0)
			System.out.println("Fractions < operator OK");
		else
			System.out.println("ERROR in fractions < operator");

		if (second.compareTo(first) > 0)
			System.out.println("Fractions > operator OK");
		else
			System.out.println("ERROR in fractions > operator");

		System.out.println();
                
                System.out.println("before exception");

		try {
			Fraction a1 = new Fraction(1, 0);		    
		}
		catch ( ArithmeticException arithmeticException )
           	{
              		System.err.printf( "\nException: %s\n", arithmeticException );
           	} // end catch

		try {
			Fraction a2 = new Fraction();		    
			Fraction a3 = new Fraction(1, 2);		    
			a3.divide(a2);
		}
		catch ( ArithmeticException arithmeticException )
           	{
              		System.err.printf( "\nException: %s\n", arithmeticException );
           	} // end catch



	}	// end main
} // end Fraction

