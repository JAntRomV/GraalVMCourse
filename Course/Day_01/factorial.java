public class factorial{
    public static void main(String args[]){
    double i,fact=1;
    double number=100;// our number to do the necessary calculations in class Factorial
    for(i=1;i<=number;i++){
        fact=fact*i;
    }
    System.out.println("Factorial of "+number+" is: "+fact);
    }
}