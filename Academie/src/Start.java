
import domain.Academie;

import java.util.Scanner;

public class Start {
    public static String menu = " 1: adauga; \n 2: sterge dupa Id; \n 3: afiseaza toata lista; \n 4: afiseaza cursurile de programare; \n exit: iesire;";
    public static void command(){
        Academie ubb = Academie.getInstance();
        boolean exit = false;
        Scanner sc = new Scanner(System.in);
        System.out.println(menu);

        while (!exit){
            switch (sc.nextLine()){
                case "exit":
                    exit = true;
                    break;
                case "1":
                        System.out.println("Introduceti <tip>,<nume>,<pret>");
                        String[] inputs = sc.nextLine().split(",");
                        ubb.adaugaCurs(inputs);
                    System.out.println(menu);
                    break;
                case "2":
                    System.out.println("introduceti id-ul cursului");
                    try{
                        ubb.stergeCurs(Integer.parseInt(sc.nextLine()));
                        System.out.println(menu);
                    }catch (NumberFormatException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "3":
                    ubb.afiseazaCursuri();
                    System.out.println(menu);
                    break;
                case "4":
                    ubb.afiseazaCursuriProgramare();
                    System.out.println(menu);
                    break;
                default:
                    System.out.println(menu);
            }
        }
    }
}
