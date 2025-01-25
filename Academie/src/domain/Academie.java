package domain;

import Exceptions.PreaMulteCursuriException;

import java.util.ArrayList;
import java.util.List;

public class Academie {
    private List<Curs> cursuri = new ArrayList<>();
    int idCounter = 1;

    //Bill Pugh singleton implementation
    private Academie(){}

    private static class AcademieSingleton{
        private static final Academie instance = new Academie();
    }

    public static Academie getInstance(){
        return AcademieSingleton.instance;
    }

    public void adaugaCurs(String[] data){
       if (cursuri.size() >= 30){
           throw new PreaMulteCursuriException();
       }else {
           addCourseByType(data);
       }
    }

    public void stergeCurs(int id){
            cursuri.remove(cursuri.stream().filter(c -> c.getId() == id).findFirst().orElseThrow());//throws if element missing
            System.out.println("Cursul " + id + " sters");
    }

    public void afiseazaCursuri(){
        cursuri.forEach(System.out::println);
    }

    public void afiseazaCursuriProgramare(){
            cursuri.stream()
                    .filter(c -> c instanceof Programare)
                    .toList().forEach(System.out::println);
    }

    public List<Curs> getCursuri() {
        return cursuri;
    }

    public void reset() {
        cursuri = new ArrayList<>();
    }

    private void addCourseByType(String[] data) {
            switch (data[0].toLowerCase()){
                case "programare":
                    cursuri.add(new Programare(idCounter++, data[1].trim(), Double.parseDouble(data[2].trim())));
                    System.out.println("Cursul " + data[1] + " a fost adaugat");
                    break;
                case "limbi straine":
                    cursuri.add(new LimbiStraine(idCounter++, data[0].trim(), Double.parseDouble(data[2].trim())));
                    System.out.println("Cursul " + data[1] + " a fost adaugat");
                    break;
                default:
                    throw new IllegalArgumentException("Tip curs indispoibil");
            }
    }
}
