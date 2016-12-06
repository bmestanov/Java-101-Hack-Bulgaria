/*package week2;
import java.util.Vector;

public class Polynomial<T extends Number> {
	private Vector<T> coefficients;
	
	private Polynomial(Vector<T> coeff){
		this.coefficients = new Vector<T>(coeff);
	}
	
	private Polynomial(int size){
		this.coefficients = new Vector<T>(size);
	}
	
	public Polynomial(T coefficient, int power){
		this.coefficients = new Vector<T>(power+1);
		for(int i=0; i < power; i++){
			coefficients.add(null);
		}
		
		coefficients.addElement(coefficient);
	}
	
	public Polynomial(T[] coefficients){
		this.coefficients = new Vector<>(coefficients.length);
		for(T d : coefficients){
			this.coefficients.add(d);
		}
	}
	

	@Override
	public String toString() {
		String result = "";
		int length = coefficients.size()-1;
		for(int i = length; i>=1; i--){
			T coeff = coefficients.get(i);
			if(coeff != null){
				result += getPolynomialElement(coeff, i);
				result += "  ";
			}
		}
		
		result += getPolynomialElement(coefficients.firstElement(), 0);		
		return result;
	}
	
	public Polynomial add(T coeff, int power) {
		Polynomial to_return = new Polynomial<T>(coefficients);
		
		//Checking if the coefficient for that power exists
		if(to_return.coefficients.size()-1 < power){
			while(to_return.coefficients.size() != power){
				to_return.coefficients.add(null);
			}
			
			to_return.coefficients.add(power,coeff);
		} else {
			T to_add = (T)to_return.coefficients.get(power);
			to_return.coefficients.set(power, addition(to_add, coeff));
		}
		
		return to_return;
	}
	
	private boolean isFloatingPoint(T number){
		return number instanceof Float || number instanceof Double;
	}
	
	private Number addition(T to_add, T coeff) {
		// TODO Auto-generated method stub
		if(isFloatingPoint(to_add) || isFloatingPoint(coeff)){
			return to_add.doubleValue() + coeff.doubleValue();
		}
		return to_add.intValue() + coeff.intValue();
	}

	private String getPolynomialElement(T coeff, int power){
		//Helper function to extract string from giver coefficient and power
		if(coeff == null){
			return "";
		}
		return coeff + (power == 0 ? "" : "x^" + power);
	}
	
	public Polynomial polynomialAddition(Polynomial poly2){
		return polynomialOperation(poly2, Operation.PLUS);
	}
	
	public Polynomial polynomialSubtraction(Polynomial poly2){
		return polynomialOperation(poly2, Operation.MINUS);
	}
	
	public Polynomial polynomialMult(Polynomial poly2) {
		return polynomialOperation(poly2, Operation.MULT);
	}
	
	public Polynomial multByConst(T f){
		Vector<T> multiplied = new Vector<T>(coefficients.size());
		for(T i : coefficients){
			multiplied.add((T)multiply(i,f));
		}
		return new Polynomial(multiplied);
	}
	
	private Number multiply(T i, T f) {
		// TODO Auto-generated method stub
		if(isFloatingPoint(i) || isFloatingPoint(f)){
			return i.doubleValue()*f.doubleValue();
		}
		return i.intValue()*f.intValue();
	}

	public Polynomial firstDeriv(){
		Vector<T> new_coeff = new Vector<>(coefficients.size()-1);
		for(int i=1; i<coefficients.size();i++){
			T number = coefficients.get(i);
			if(isFloatingPoint(number)){
				//ToDo Find out why this doesn't work!
				new_coeff.add(number.doubleValue()*i);
			} else {
				new_coeff.add(number.intValue()*i);
			}
		}
		
		return new Polynomial(new_coeff);
	}
	
	private Polynomial polynomialOperation(Polynomial poly2, Operation oper){

		int poly1_len = this.coefficients.size();
		int poly2_len = poly2.coefficients.size();

		Polynomial to_return = new Polynomial(Math.max(poly1_len, poly2_len));
		
		int i;
		for(i=0; i<poly1_len && i<poly2_len; i++){
			Float elem1 = this.coefficients.get(i);
			Float elem2 = poly2.coefficients.get(i);
			Float new_elem = 0f;
			
			switch(oper){
			case PLUS: {
				new_elem = elem1 + elem2;
				break;
			}
			case MINUS: {
				new_elem = elem1 - elem2;
				break;
			}
			case MULT:{
				new_elem = elem1 * elem2;
			}
			}
			
			to_return.coefficients.add(new_elem);
		}
		
		if(i < poly1_len){
			while(i != poly1_len){
				to_return.coefficients.add(this.coefficients.get(i));
				i++;
			}
		} else if (i < poly2_len){
			while(i != poly2_len){
				to_return.coefficients.add(poly2.coefficients.get(i));
				i++;
			}
		}
		
		return to_return;
	}
}

enum Operation{
	PLUS, MINUS, MULT
}
*/