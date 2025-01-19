import Exceptions.PreaMulteCursuriException;

import java.util.ArrayList;
import java.util.List;

public class Academie {

    private static Academie instance;
    private List<Curs> cursuri;
    int idCounter = 1;

    private Academie(){}

    public static synchronized Academie getInstance(){
        if (instance == null){
            synchronized (Academie.class){
                if(instance == null){
                    instance = new Academie();
                }
            }
        }
        return instance;
    }

    public void adaugaCurs(String[] data){
        try {
            if (cursuri == null){
                cursuri = new ArrayList<>();
            }
            if (cursuri.size() == 30){
                throw new PreaMulteCursuriException();
            }else {
                try{
                    if (data[0].equals("Programare") || data[0].equals("programare")){
                    cursuri.add(new Programare(idCounter++, data[1].trim(), Double.parseDouble(data[2].trim())));
                } else if (data[0].equals("Limbi Straine") || data[0].equals("limbi straine")) {
                    cursuri.add(new LimbiStraine(idCounter++, data[0].trim(), Double.parseDouble(data[2].trim())));
                }else{
                    System.out.println("Cursul nu exista");
                }
                    System.out.println("Cursul " + data[1] + " a fost adaugat");
                }catch (Exception e){
                    System.out.println("Introduceti datele corect [Enter]");
                }
            }
        }catch (PreaMulteCursuriException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void stergeCurs(int id){
        try {
            cursuri.remove(cursuri.stream().filter(c -> c.getId() == id).findFirst().orElseThrow());//throws if element missing
            //cursuri.removeIf(c -> c.getId() == id); //doesn't throw if element missing
            System.out.println("Cursul " + id + " sters");
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public void afiseazaCursuri(){
        try {
            if (cursuri.isEmpty()){
                System.out.println("Lista e goala");
            }else{
                cursuri.forEach(System.out::println);
            }
        }catch (Exception ex){
            System.out.println("Lista nu exista");
        }
    }

    public void afiseazaCursuriProgramare(){
        try {
            List<Curs> prog = cursuri.stream().filter(c -> c instanceof Programare).toList();
            if (prog.isEmpty()){
                System.out.println("Lista e goala");
            }else {
                prog.forEach(System.out::println);
            }
        }catch (NullPointerException ex){
            System.out.println("Lista e goala sau nu exista");
        }
    }
}
