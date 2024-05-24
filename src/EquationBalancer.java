

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;


public class EquationBalancer {

    // instance variables
    private static String[] elements;
    private String leftHandSide;
    private String rightHandSide;
    private int side;

    // constructor
    public EquationBalancer(String equation, String side) {
        elements = readElements();
        leftHandSide = divideEquation(equation, 0);
        rightHandSide = divideEquation(equation, 1);

        if (side.equals("L")) {
            this.side = 0;
        }
        else {
            this.side = 1;
        }


    }

    // methods
    private String[] readElements() {

        String[] elementList = new String[118];
        int count = 0;

        try {
            File file = new File("C:\\Users\\ASUS\\Desktop\\CS102\\ChemicalEquationBalancer\\src\\elements.txt");

            Scanner scan = new Scanner(file);
            scan.useDelimiter(", ");

            while (scan.hasNext()) {
                String token = scan.next();
                elementList[count++] = token;
            }

        }
        catch (FileNotFoundException err) {
            System.err.println("File not found!");
            err.printStackTrace();
        }

        return elementList;
    }

    private String divideEquation(String equation, int index) {
        String[] dividedEquation = equation.split("=>"); // divided equation into two parts

        System.out.println(dividedEquation[index]);
        return dividedEquation[index];
    }

    private boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private int valueOfNumeric(String strNum) {
        return Integer.parseInt(strNum);
    }
    private boolean searchElement(String element) {

        for (int i = 0; i < elements.length; i++) {
            if (element.equals(elements[i])) {
                return true;
            }
        }
        return false;
    }

    private ArrayList<String> readEquation(int aSide) { // needs fixing

        String partedEquation = (aSide == 0) ? leftHandSide : rightHandSide;

        ArrayList<String> elementList = new ArrayList<>();
        String prevElement = "";

        for (int i = 0; i < partedEquation.length(); i++) {

            if (i < partedEquation.length() - 3 && searchElement(partedEquation.substring(i, i+3))) {
                elementList.add(partedEquation.substring(i, i+3));
                prevElement = partedEquation.substring(i, i+3);
            }
            else if (i < partedEquation.length() - 2 && searchElement(partedEquation.substring(i, i+2))) {
                elementList.add(partedEquation.substring(i, i+2));
                prevElement = partedEquation.substring(i, i+2);
            }
            else if (i < partedEquation.length() - 1 && searchElement(partedEquation.substring(i, i+1))) {
                elementList.add(partedEquation.substring(i, i+1));
                prevElement = partedEquation.substring(i, i+1);
            }

            else {

                if (i < partedEquation.length() - 3 && isNumeric(partedEquation.substring(i, i+3))) {
                    String par = partedEquation.substring(i, i+3);

                    for (int j = 0; j < valueOfNumeric(par); j++) {
                        elementList.add(prevElement);
                    }
                }

                else if (i < partedEquation.length() - 2 && isNumeric(partedEquation.substring(i, i+2))) {
                    String par = partedEquation.substring(i, i+2);

                    for (int j = 0; j < valueOfNumeric(par); j++) {
                        elementList.add(prevElement);
                    }
                }

                else if (i < partedEquation.length() - 1 && isNumeric(partedEquation.substring(i, i+1))) {
                    String par = partedEquation.substring(i, i+1);

                    for (int j = 0; j < valueOfNumeric(par); j++) {
                        elementList.add(prevElement);
                    }
                }
            }
        }

        Collections.sort(elementList); // sorted element list in alphabetical order to count more easily

        return elementList;
    }



    private ArrayList<Integer> countElements(ArrayList<String> elementsInEquation) {

        ArrayList<Integer> elementNumbers = new ArrayList<>();
        int count = 0;

        for (int i = 0; i < elementsInEquation.size(); i++) {

            if (i < elementsInEquation.size() - 1 && elementsInEquation.get(i).equals(elementsInEquation.get(i+1))) {
                count++;
            }
            else {
                elementNumbers.add(count);
                count = 1;
            }
        }

        return elementNumbers;


    }

    public String balanceEquation() {

        ArrayList<Integer> numberOfElementsOnSide = countElements(readEquation(side));
        ArrayList<Integer> numberOfElementsOnOtherSide = countElements(readEquation((side == 0) ? 1 : 0));

        String equation = leftHandSide + rightHandSide;

        if (numberOfElementsOnSide.size() == numberOfElementsOnOtherSide.size()) {
            return equation;
        }

        else {

        }

        return "";
    }


    public static void main(String[] args) {

    }

}



