import java.util.Scanner;

 class Main {
    public static void main(String[] args) {

        Scanner inp = new Scanner(System.in);
        boolean keepAsking = true;

        do {
            System.out.println("Please enter a chemical equation: ");
            String equation = inp.nextLine();
            System.out.println("Do you want to balance the right or left side? (L/R):");
            String side = inp.nextLine();
            EquationBalancer eq = new EquationBalancer(equation, side);
            System.out.println("Balanced equation: " + eq.balanceEquation());
            System.out.println("Do you want to try another equation? (Y/N):");
            if (inp.nextLine().equalsIgnoreCase("N")) {
                keepAsking = false;
            }
        }
        while(keepAsking);

        System.out.println("Thank you! Goodbye!");


    }
}