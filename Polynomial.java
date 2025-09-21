public class Polynomial{
    double[] coefficients;

    public Polynomial(){
        this.coefficients = new double[1];
    }

    public Polynomial(double[] coefficients){
        this.coefficients = coefficients;
    }

    public Polynomial add(Polynomial x){
        int length = Math.max(this.coefficients.length, x.coefficients.length);
        double[] result = new double[length];
        int index = 0;
        while(index < this.coefficients.length && index < x.coefficients.length){
            result[index] = this.coefficients[index] + x.coefficients[index];
            index++;
        }
        while(index < this.coefficients.length){
            result[index] = this.coefficients[index];
            index++;
        }
        while(index < x.coefficients.length){
            result[index] = x.coefficients[index];
            index++;
        }
        return new Polynomial(result);
    }

    public double evaluate(double x){
        double result = 0;
        for(int i = 0; i < this.coefficients.length; i++){
            result += this.coefficients[i] * Math.pow(x, i);
        }
        return result;
    }

    public boolean hasRoot(double x){
        return evaluate(x) == 0;
    }
}