/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class MatrixOperation {

    /**
     * Receives an equation and return the value associated with the variable X, if none is found it returns 1
     * @param equation
     * @return X
     */
    private int getX(String equation) {
        int index = equation.indexOf("x");
        String xCoef = "";

        if(index == -1){ // if index is equals to -1 is because it was not found.
            return 0;
        }
        try {
            xCoef = equation.substring(index - 1, index);

            if (xCoef.equals("-")) { // if the character before x is a  minus signal then return -1
                return Integer.parseInt("-1");
            }
            if(xCoef.equals("+")){ // if it is a plus signal return 1
                return 1;
            }
            
            try {

                //if in case x is bigger than 9, or if it has  a invisible number
                if (equation.substring(index - 2, index - 1).matches("\\d+") || equation.substring(index - 2, index - 1).equals("-")) {

                    if (equation.substring(index - 2, index - 1).matches("\\d+")) {

                        xCoef = "" + equation.substring(index - 2, index - 1) + "" + xCoef;
                        try {

                            if (equation.substring(index - 3, index - 2).equals("-")) {
                                xCoef = "-" + xCoef;
                                return Integer.parseInt(xCoef);
                            }
                        } catch (Exception e) {
                            return Integer.parseInt(xCoef);
                        }
                    }

                    if (equation.substring(index - 2, index - 1).equals("-")) { // checking signal of this number

                        xCoef = "-" + xCoef;
                        return Integer.parseInt(xCoef);
                    }
                }
            } catch (StringIndexOutOfBoundsException ex) {
                return Integer.parseInt(xCoef);
            }

        } catch (StringIndexOutOfBoundsException ex) {

            return Integer.parseInt("1");
        }

        return Integer.parseInt(xCoef);
    }

    // Y and Z are going to follow the same logic, so i will not comment the specifics
    
    /**
     * Receives an equation and return the value associated with the variable Y, if none is found it returns 1
     * @param equation
     * @return Y
     */
    private int getY(String equation) {
        int index = equation.indexOf("y");
        String yCoef = "";

        if(index == -1){
            return 0;
        }
        try {
            yCoef = equation.substring(index - 1, index);
            
            if (yCoef.equals("-")) {
                return Integer.parseInt("-1");
            }
            if(yCoef.equals("+")){
                return 1;
            }

            try {

                //if in case x is bigger than 9, or if it has  a invisible number
                if (equation.substring(index - 2, index - 1).matches("\\d+") || equation.substring(index - 2, index - 1).equals("-")) {

                    if (equation.substring(index - 2, index - 1).matches("\\d+")) {

                        yCoef = "" + equation.substring(index - 2, index - 1) + "" + yCoef;
                        try {

                            if (equation.substring(index - 3, index - 2).equals("-")) {
                                yCoef = "-" + yCoef;
                                return Integer.parseInt(yCoef);
                            }
                        } catch (Exception e) {
                            return Integer.parseInt(yCoef);
                        }
                    }

                    if (equation.substring(index - 2, index - 1).equals("-")) {

                        yCoef = "-" + yCoef;
                        return Integer.parseInt(yCoef);
                    }
                }
            } catch (StringIndexOutOfBoundsException ex) {
                return Integer.parseInt(yCoef);
            }

        } catch (StringIndexOutOfBoundsException ex) {

            return Integer.parseInt("1");
        }
        return Integer.parseInt(yCoef);
    }
    
    /**
     * Receives an equation and return the value associated with the variable X, if none is found it returns 1
     * @param equation
     * @return Z
     */
    private int getZ(String equation) {
        int index = equation.indexOf("z");
        if(index == -1){
            return 0;
        }
        String zCoef = "";

        try {
            zCoef = equation.substring(index - 1, index);

            if (zCoef.equals("-")) {
                return Integer.parseInt("-1");
            }
            if(zCoef.equals("+")){
                return 1;
            }
            
            try {

                //if in case x is bigger than 9, or if it has  a invisible number
                if (equation.substring(index - 2, index - 1).matches("\\d+") || equation.substring(index - 2, index - 1).equals("-")) {

                    if (equation.substring(index - 2, index - 1).matches("\\d+")) {

                        zCoef = "" + equation.substring(index - 2, index - 1) + "" + zCoef;
                        try {

                            if (equation.substring(index - 3, index - 2).equals("-")) {
                                zCoef = "-" + zCoef;
                                return Integer.parseInt(zCoef);
                            }
                        } catch (Exception e) {
                            return Integer.parseInt(zCoef);
                        }
                    }

                    if (equation.substring(index - 2, index - 1).equals("-")) {

                        zCoef = "-" + zCoef;
                        return Integer.parseInt(zCoef);
                    }
                }
            } catch (StringIndexOutOfBoundsException ex) {
                return Integer.parseInt(zCoef);
            }

        } catch (StringIndexOutOfBoundsException ex) {

            return Integer.parseInt("1");
        }

        return Integer.parseInt(zCoef);
    }
    
    /**
     * Receives an equation and return the value not associated with any variable if none is found it returns 0
     * @param equation
     * @return X
     */
    private int getConst(String equation) {

        String number = "";
        CharSequence inputStr = equation;
        String patternStr = "[1-9]";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(inputStr);
        String secondUnit = "";

        while (matcher.find()) {
            int index = matcher.start();
            number = equation.substring(index, index + 1);
            try {
                String nextChar = equation.substring(index + 1, index + 2);
                //Check if its bigger than 9
                try {
                    if (nextChar.matches("[0-9]") == true) {
                        secondUnit = nextChar;
                        nextChar = equation.substring(index + 2, index + 3);

                        //if this is not coeficient of x or y return it
                        if (nextChar.equals("x") == false && nextChar.equals("y") == false && nextChar.equals("z")==false) {
                            //just checking the signal
                            if (equation.substring(index - 1, index).equals("-")) {
                                return Integer.parseInt("-" + number + "" + secondUnit);
                            } else {
                                return Integer.parseInt(number + "" + secondUnit);
                            }
                        }
                    }
                } catch (Exception e) {
                    if (equation.substring(index - 1, index).equals("-")) {
                        return Integer.parseInt("-" + number + secondUnit);
                    } else {
                        return Integer.parseInt(number + secondUnit);
                    }
                }

                try {
                    if (nextChar.equals("x") == false && nextChar.equals("y") == false && nextChar.equals("z")==false) {
                        if (equation.substring(index - 1, index).equals("-")) {
                            return Integer.parseInt("-" + number);
                        } else {
                            return Integer.parseInt(number);
                        }
                    }
                } catch (StringIndexOutOfBoundsException e) {
                    return Integer.parseInt(number);
                }

            } catch (StringIndexOutOfBoundsException ex) {

                if (equation.substring(index - 1, index).equals("-")) {
                    return Integer.parseInt("-" + number);
                } else {
                    return Integer.parseInt(number);
                }
            }
        }
        return 0;
    }
    
    /**
     * 
     * invokes three methods(getX,getY,getConst) and rearrange the equation into the correct form and signals.
     */
    private ArrayList<Integer> fixSignal2x2(String equation) throws Exception{
    
        ArrayList<Integer> list = new ArrayList<Integer>();
        int x = getX(equation);
        int y = getY(equation);
        int cons = getConst(equation);
        //later just add Z
        
        if(x == 0 || y == 0){
            throw new Exception("the inserted equation is not right");
        }
        // this case is to take care of reformatting the equation to the right format
        int index = equation.indexOf("=");
        String afterEqual = equation.substring(index);
        
        if(afterEqual.contains(String.valueOf(x) + "x")){
            if(String.valueOf(x).substring(0,1).equals("-")){
                    x = Integer.parseInt(String.valueOf(x).substring(1));
            }else{
                    x = Integer.parseInt("-" + String.valueOf(x));
            }
        }

        if(afterEqual.contains(String.valueOf(y) + "y")){
            if(String.valueOf(y).substring(0,1).equals("-")){
                    y = Integer.parseInt(String.valueOf(y).substring(1));
            }else{
                    y = Integer.parseInt("-" + String.valueOf(y));
            }
        }

        if(!afterEqual.contains(String.valueOf(cons))){
            if(String.valueOf(cons).substring(0,1).equals("-")){
                    cons = Integer.parseInt(String.valueOf(cons).substring(1));
            }else{
                    cons = Integer.parseInt("-" + String.valueOf(cons));
            }
        }
        
        
        // this part is to change all the signals in case x is negative
        if(String.valueOf(x).substring(0,1).equals("-")){
            x = Integer.parseInt(String.valueOf(x).substring(1));
            if(String.valueOf(y).substring(0,1).equals("-")){
            
                y = Integer.parseInt(String.valueOf(y).substring(1));
            }else{
                y = Integer.parseInt("-" + String.valueOf(y));
            }
            
            if(String.valueOf(cons).substring(0,1).equals("-")){
            
                cons = Integer.parseInt(String.valueOf(cons).substring(1));
            }else{
                cons = Integer.parseInt("-" + String.valueOf(cons));
            }
            list.add(x);
            list.add(y);
            list.add(cons);
        
        }else{
            list.add(x);
            list.add(y);
            list.add(cons);
        }
            return list;
    }
    
    /**
     * receives two equations, get the values that comes with x and y, fix their signals with fixSignal2x2 method, and then build the matrixA 2x2.
     * @param equation1
     * @param equation2
     * @return
     * @throws Exception 
     */
    private int[][] buildingMatrixA2x2(String equation1 , String equation2) throws Exception{
        
    
    int[][] matrixA = new int[2][2];
        matrixA[0][0] = fixSignal2x2(equation1).get(0);
        matrixA[0][1] = fixSignal2x2(equation1).get(1);
        matrixA[1][0] = fixSignal2x2(equation2).get(0);
        matrixA[1][1] = fixSignal2x2(equation2).get(1);

    return matrixA;
    }
    
    /**
     * receives two equations, get the values of the constants, fix their signal with fixSignal2x2 method, and then build the matrixB 2x2.
     * @param equation1
     * @param equation2
     * @return
     * @throws Exception 
     */
    private double[][] buildingMatrixB2x2(String equation1 , String equation2) throws Exception{
    
        double[][] matrixConst = new double[2][2];
        matrixConst[0][0] = fixSignal2x2(equation1).get(2);
        matrixConst[1][0] = fixSignal2x2(equation2).get(2);
        
    return matrixConst;
    }
    
    /**
     * This method calls the built matrixA from last method, find its determinant, and then calculate the inverse of the matrix A.
     * @param equation1
     * @param equation2
     * @return
     * @throws Exception 
     */
    private double[][] findingInverseOfMatrixA2x2(String equation1,String equation2) throws Exception{
    
        //first find determinant
        int[][] matrixA = buildingMatrixA2x2(equation1, equation2);
        double determinant = (matrixA[0][0]*matrixA[1][1])-(matrixA[0][1]*matrixA[1][0]);
        //apply formula
        double[][] inverseMatrix = new double[2][2];
            inverseMatrix[0][0] = (1/determinant) * matrixA[1][1];
            inverseMatrix[0][1] = (1/determinant) * -(matrixA[0][1]);  
            inverseMatrix[1][0] = (1/determinant) * -(matrixA[1][0]);
            inverseMatrix[1][1] = (1/determinant) * matrixA[0][0];

        return inverseMatrix;
    }
    
    /**
     * Now that we have the inverse of matrix A, we can now apply the formula and get the values for x and Y.
     * @param equation1
     * @param equation2
     * @return
     * @throws Exception 
     */
    public HashMap<String,Double> solvingEquation2x2(String equation1,String equation2) throws Exception{
    
    double[][] matrixA = findingInverseOfMatrixA2x2(equation1,equation2);
    double[][] matrixB = buildingMatrixB2x2(equation1,equation2);
    HashMap<String,Double> resultSet = new HashMap<String,Double>();
    double xResult = (matrixA[0][0] * matrixB[0][0]) + (matrixA[0][1] * matrixB[1][0]);
    double yResult = (matrixA[1][0] * matrixB[0][0]) + (matrixA[1][1] * matrixB[1][0]);
    
     resultSet.put("x",xResult);
     resultSet.put("y",yResult);
     
     return resultSet;
    }
    
    /**
     * 
     * invokes all the methods methods(getX,getY,getZ,getConst) and rearrange the equation into the correct form and signals.
     */
    private ArrayList<Integer> fixSignal3x3(String equation) throws Exception{
    
        ArrayList<Integer> list = new ArrayList<Integer>();
        int x = getX(equation);
        int y = getY(equation);
        int cons = getConst(equation);
        int z = getZ(equation);
        
        /*if(x == 0 || y == 0 || z==0){
            throw new Exception("the inserted equation is not right");
        }*/
        // this case is to take care of reformatting the equation to the right format
        int index = equation.indexOf("=");
        String afterEqual = equation.substring(index);
        
        if(afterEqual.contains(String.valueOf(x) + "x")){
            if(String.valueOf(x).substring(0,1).equals("-")){
                    x = Integer.parseInt(String.valueOf(x).substring(1));
            }else{
                    x = Integer.parseInt("-" + String.valueOf(x));
            }
        }

        if(afterEqual.contains(String.valueOf(y) + "y")){
            if(String.valueOf(y).substring(0,1).equals("-")){
                    y = Integer.parseInt(String.valueOf(y).substring(1));
            }else{
                    y = Integer.parseInt("-" + String.valueOf(y));
            }
        }
        
        if(afterEqual.contains(String.valueOf(z) + "z")){
            if(String.valueOf(z).substring(0,1).equals("-")){
                    z = Integer.parseInt(String.valueOf(z).substring(1));
            }else{
                    z = Integer.parseInt("-" + String.valueOf(z));
            }
        }

        if(!afterEqual.contains(String.valueOf(cons))){
            if(String.valueOf(cons).substring(0,1).equals("-")){
                    cons = Integer.parseInt(String.valueOf(cons).substring(1));
            }else{
                    cons = Integer.parseInt("-" + String.valueOf(cons));
            }
        }
        
        
        // this part is to change all the signals in case x is negative
        if(String.valueOf(x).substring(0,1).equals("-")){
            x = Integer.parseInt(String.valueOf(x).substring(1));
            if(String.valueOf(y).substring(0,1).equals("-")){
            
                y = Integer.parseInt(String.valueOf(y).substring(1));
            }else{
                y = Integer.parseInt("-" + String.valueOf(y));
            }
            
            if(String.valueOf(cons).substring(0,1).equals("-")){
            
                cons = Integer.parseInt(String.valueOf(cons).substring(1));
            }else{
                cons = Integer.parseInt("-" + String.valueOf(cons));
            }
            if(String.valueOf(z).substring(0,1).equals("-")){
            
                z = Integer.parseInt(String.valueOf(z).substring(1));
            }else{
                z = Integer.parseInt("-" + String.valueOf(z));
            }
            list.add(x);
            list.add(y);
            list.add(z);
            list.add(cons);
        
        }else{
            list.add(x);
            list.add(y);
            list.add(z);
            list.add(cons);
            
        }
            return list;
    }
    
     /**
     * receives three equations, get the values that comes with x, y and z, fix their signals with fixSignal3x3 method, and then build the matrixA 3x3.
     * @param equation1
     * @param equation2
     * @param equation3
     * @return MatrixA
     * @throws Exception 
     */
    private int[][] buildingMatrixA3x3(String equation1 , String equation2, String equation3) throws Exception{
        //just overload method with another variable for 3x3
    
    int[][] matrixA = new int[3][3];
        matrixA[0][0] = fixSignal3x3(equation1).get(0);
        matrixA[0][1] = fixSignal3x3(equation1).get(1);
        matrixA[0][2] = fixSignal3x3(equation1).get(2);
        matrixA[1][0] = fixSignal3x3(equation2).get(0);
        matrixA[1][1] = fixSignal3x3(equation2).get(1);
        matrixA[1][2] = fixSignal3x3(equation2).get(2);
        matrixA[2][0] = fixSignal3x3(equation3).get(0);
        matrixA[2][1] = fixSignal3x3(equation3).get(1);
        matrixA[2][2] = fixSignal3x3(equation3).get(2);

    return matrixA;
    }
    
    /**
     * receives three equations, get the values of the constants, fix their signal with fixSignal3x3 method, and then build the matrixB 3x3.
     * @param equation1
     * @param equation2
     * @param equation3
     * @return MatrixB
     * @throws Exception 
     */
    private double[][] buildingMatrixB3x3(String equation1 , String equation2, String equation3) throws Exception{
    
        double[][] matrixConst = new double[3][1];
        matrixConst[0][0] = fixSignal3x3(equation1).get(3);
        matrixConst[1][0] = fixSignal3x3(equation2).get(3);
        matrixConst[2][0] = fixSignal3x3(equation3).get(3);
        
    return matrixConst;
    }
    
    /**
     * This method calls the built matrixA from last method, find its determinant, and then calculate the inverse of the matrix A.
     * @param equation1
     * @param equation2
     * @param equation3
     * @return Inverse of Matrix A
     * @throws Exception 
     */
    private double[][] findingInverseOfMatrixA3x3(String equation1,String equation2,String equation3) throws Exception{
    
        //first find determinant
        int[][] matrixA = buildingMatrixA3x3(equation1, equation2,equation3);
        
        int[] minors = new int[9];
        minors[0] = (matrixA[1][1] * matrixA[2][2]) - (matrixA[1][2] * matrixA[2][1]);
        minors[1] = -((matrixA[1][0] * matrixA[2][2]) - (matrixA[1][2] * matrixA[2][0]));
        minors[2] = (matrixA[1][0] * matrixA[2][1]) - (matrixA[1][1] * matrixA[2][0]);
        minors[3] = -((matrixA[0][1] * matrixA[2][2]) - (matrixA[0][2] * matrixA[2][1]));
        minors[4] = (matrixA[0][0] * matrixA[2][2]) - (matrixA[0][2] * matrixA[2][0]);
        minors[5] = -((matrixA[0][0] * matrixA[2][1]) - (matrixA[0][1] * matrixA[2][0]));
        minors[6] = (matrixA[0][1] * matrixA[1][2]) - (matrixA[0][2] * matrixA[1][1]);
        minors[7] = -((matrixA[0][0] * matrixA[1][2]) - (matrixA[1][0] * matrixA[0][2]));
        minors[8] = (matrixA[0][0] * matrixA[1][1]) - (matrixA[1][0] * matrixA[0][1]);
        
        
        double determinant = (matrixA[0][0] * minors[0])+(matrixA[0][1] * minors[1])+(matrixA[0][2] * minors[2]);
        //apply formula
        double[][] inverseMatrix = new double[3][3];
            inverseMatrix[0][0] = (1/determinant) * minors[0];
            inverseMatrix[1][0] = (1/determinant) * minors[1];
            inverseMatrix[2][0] = (1/determinant) * minors[2];
            inverseMatrix[0][1] = (1/determinant) * minors[3];
            inverseMatrix[1][1] = (1/determinant) * minors[4];
            inverseMatrix[2][1] = (1/determinant) * minors[5];
            inverseMatrix[0][2] = (1/determinant) * minors[6];
            inverseMatrix[1][2] = (1/determinant) * minors[7];
            inverseMatrix[2][2] = (1/determinant) * minors[8];
        return inverseMatrix;
    }
    
    /**
     * Now that we have the inverse of matrix A, we can now apply the formula and get the values for X, Y and Z.
     * @param equation1
     * @param equation2
     * @param equation3
     * @return
     * @throws Exception 
     */
    public HashMap<String,Double> solvingEquation3x3(String equation1,String equation2, String equation3) throws Exception{
    
    double[][] matrixA = findingInverseOfMatrixA3x3(equation1,equation2,equation3);
        //System.out.println(matrixA[0][0]);
    double[][] matrixB = buildingMatrixB3x3(equation1,equation2,equation3);
    HashMap<String,Double> resultSet = new HashMap<String,Double>();
    
    double xResult = (matrixA[0][0] * matrixB[0][0]) + (matrixA[0][1] * matrixB[1][0]) + matrixA[0][2] * matrixB[2][0];
    double yResult = (matrixA[1][0] * matrixB[0][0]) + (matrixA[1][1] * matrixB[1][0]) + (matrixA[1][2] * matrixB[2][0]);
    double zResult = (matrixA[2][0] * matrixB[0][0]) + (matrixA[2][1] * matrixB[1][0]) + (matrixA[2][2] * matrixB[2][0]);
    
     resultSet.put("x",xResult);
     resultSet.put("y",yResult);
     resultSet.put("z",zResult);
     
     return resultSet;
    }
}
