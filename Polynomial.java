import java.util.ArrayList;
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Polynomial{
    double[] coefficients;
    int[] exponents;

    public Polynomial(){
        this.coefficients = new double[1];
        this.exponents = new int[1];
    }

    public Polynomial(double[] coefficients, int[] exponents){
        this.coefficients = coefficients;
        this.exponents = exponents;
    }

    public Polynomial(File file) throws IOException {
        BufferedReader input = new BufferedReader(new FileReader(file));
        String line = input.readLine();
        input.close();

        line = line.replace("-", "+-");
        String[] parts = line.split("\\+");

        ArrayList<Double> coeffList = new ArrayList<>();
        ArrayList<Integer> expoList = new ArrayList<>();

        for(int i = 0; i < parts.length; i++){
            double coeff;
            int expo;
            if(parts[i].contains("x")){
                String[] subParts = parts[i].split("x");
                if(subParts[0].equals("")){
                    coeff = 1;
                }
                else if(subParts[0].equals("-")){
                    coeff = -1;
                }
                else{
                    coeff = Double.parseDouble(subParts[0]);
                }

                if(subParts.length > 1 && !subParts[1].equals("")){
                    expo = Integer.parseInt(subParts[1]);
                }
                else{
                    expo = 1;
                }
            }
            else{
                coeff = Double.parseDouble(parts[i]);
                expo = 0;
            }

            coeffList.add(coeff);
            expoList.add(expo);
        }

        this.coefficients = new double[coeffList.size()];
        this.exponents = new int[expoList.size()];

        for(int i = 0; i < coeffList.size(); i++){
            this.coefficients[i] = coeffList.get(i);
            this.exponents[i] = expoList.get(i);
        }
    }

    public Polynomial add(Polynomial x){
        ArrayList<Double> coeffArray = new ArrayList<>();
        ArrayList<Integer> expoArray = new ArrayList<>();

        for(int i = 0; i < this.coefficients.length; i++){
            coeffArray.add(this.coefficients[i]);
            expoArray.add(this.exponents[i]);
        }

        for(int i = 0; i < x.coefficients.length; i++){
            int currentExp = x.exponents[i];
            double currentCoeff = x.coefficients[i];
            if(expoArray.contains(currentExp)){
                int index = expoArray.indexOf(currentExp);
                coeffArray.set(index, coeffArray.get(index) + currentCoeff);
            } 
            else {
                coeffArray.add(currentCoeff);
                expoArray.add(currentExp);
            }
        }

        double[] finalCoeff = new double[coeffArray.size()];
        int[] finalExpo = new int[expoArray.size()];
        
        for(int i = 0; i < finalCoeff.length; i++){
            finalCoeff[i] = coeffArray.get(i);
            finalExpo[i] = expoArray.get(i);
        }

        return new Polynomial(finalCoeff, finalExpo);
    }

    public double evaluate(double x){
        double result = 0;
        for(int i = 0; i < this.coefficients.length; i++){
            result += this.coefficients[i] * Math.pow(x, this.exponents[i]);
        }
        return result;
    }

    public boolean hasRoot(double x){
        return evaluate(x) == 0;
    }

    public Polynomial multiply(Polynomial x){
        ArrayList<Double> coeffArray = new ArrayList<>();
        ArrayList<Integer> expoArray = new ArrayList<>();

        for(int i = 0; i < this.coefficients.length; i++){
            for(int j = 0; j < x.coefficients.length; j++){
                double coeff = this.coefficients[i] * x.coefficients[j];
                int expo = this.exponents[i] + x.exponents[j];
                
                if(expoArray.contains(expo)){
                    int index = expoArray.indexOf(expo);
                    coeffArray.set(index, coeffArray.get(index) + coeff);
                } else {
                    coeffArray.add(coeff);
                    expoArray.add(expo);
                }
            }
        }

        double[] finalCoeff = new double[coeffArray.size()];
        int[] finalExpo = new int[expoArray.size()];

        for(int i = 0; i < finalCoeff.length; i++){
            finalCoeff[i] = coeffArray.get(i);
            finalExpo[i] = expoArray.get(i);
        }

        return new Polynomial(finalCoeff, finalExpo);
    }

    public void saveToFile(String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        StringBuilder polynomial = new StringBuilder();

        for(int i = 0; i < coefficients.length; i++){
            double coeff = this.coefficients[i];
            int exp = this.exponents[i];

            if(i > 0){
                if(coeff >= 0) polynomial.append("+");
            }

            if(exp == 0){
               polynomial.append(coeff);
            }
            else if(exp == 1){
                polynomial.append(coeff).append("x");
            }
            else{
                polynomial.append(coeff).append("x").append(exp);
            }

        }
        writer.write(polynomial.toString());
        writer.close();
    }
    
}