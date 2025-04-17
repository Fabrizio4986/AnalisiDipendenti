import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Dipendente {
    private String nome;
    private int eta;
    private double stipendio;
    private Dipartimento dipartimento;

    public Dipendente(String nome, int eta, double stipendio, Dipartimento dipartimento) {
        this.nome = nome;
        this.eta = eta;
        this.stipendio = stipendio;
        this.dipartimento = dipartimento;
    }

    public Dipendente(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public int getEta() {
        return eta;
    }

    public double getStipendio() {
        return stipendio;
    }

    public Dipartimento getDipartimento() {
        return dipartimento;
    }

    public void infoDipendente() {
        System.out.print("Nome: " + nome + ", età: " + eta + ", Stipendio: " + stipendio + ", Dipartimento: " + dipartimento + ".");
        System.out.println();
    }

    public static void main(String[] args) {


        FiltroDipendenti filtroEta = d -> d.getEta() >= 30;
        FiltroDipendenti filtroStip = d -> d.getStipendio() >= 50000;
        List<Dipendente> dipendenti = Arrays.asList(
                new Dipendente("Mario", 35, 60000, Dipartimento.HR),
                new Dipendente("Luigi", 28, 45000, Dipartimento.IT),
                new Dipendente("Anna", 40, 52000, Dipartimento.SALES),
                new Dipendente("Paolo", 25, 30000, Dipartimento.SALES),
                new Dipendente("Luca", 38, 51000, Dipartimento.FINANCES)
        );

        System.out.println("----------Lista Dipendenti con età maggiore o uguale a 30---------- ");
        List<Dipendente> dipOverTrenta = FiltroDip.filtra(dipendenti, filtroEta);
        for (Dipendente dip : dipOverTrenta) {
            dip.infoDipendente();
        }

        System.out.println("----------Lista Dipendenti con stipendio maggiore di 50000---------- ");
        List<Dipendente> dipStipAlto = FiltroDip.filtra(dipendenti, filtroStip);
        for (Dipendente dip : dipStipAlto) {
            dip.infoDipendente();
        }

        System.out.println("----------Lista Dipendenti ordinata per nome---------- ");
        //Comparator<Dipendente> ordinaPerNome = (d1, d2) -> d1.getNome().compareToIgnoreCase(d2.getNome());
        dipendenti.sort(Comparator.comparing(Dipendente::getNome));
        for (Dipendente dip : dipendenti) {
            dip.infoDipendente();
        }

        System.out.println("----------Lista Dipendenti creata da lista nomi---------- ");
        List<String> nomi = Arrays.asList("Anna", "Marco", "Flavio", "Sara");
        List<Dipendente> dipDaNomi = new ArrayList<>();
        Function<String, Dipendente> creaDipendente = Dipendente::new;
        for (String nome : nomi) {
            dipDaNomi.add(creaDipendente.apply(nome));
        }
        for (Dipendente dip : dipDaNomi) {
            dip.infoDipendente();
        }

        System.out.println("----------Lista Dipendenti creata da operazioni Stream---------- ");

        Stream<Dipendente> streamDip = dipendenti.stream();

        Predicate<Dipendente> stipAlto = dipendente -> dipendente.stipendio >= 50000;
        Comparator<Dipendente> compEta = (d1, d2) ->
                Integer.compare(d1.getEta(), d2.getEta());
        Consumer<Dipendente> stampa = dipendente -> dipendente.infoDipendente();


        /*System.out.println("----------Lista Dipendenti con stipendio over 50k---------- ");
        Stream<Dipendente> dipStipCinquanta = streamDip.filter(stipAlto);
        dipStipCinquanta.forEach(stampa);*/

        /*System.out.println("----------Lista Dipendenti ordinati per età---------- ");
        Stream<Dipendente> dipOrdinatiPerEta = streamDip.sorted(compEta);
        dipOrdinatiPerEta.forEach(stampa);*/

        /*System.out.println("----------Media stipendi dipendenti---------- ");
        double mediaStipendi = streamDip.collect(Collectors.averagingDouble(Dipendente::getStipendio));
        System.out.println("Media Stipendi: " + mediaStipendi);*/

        System.out.println("----------Lista Dipendenti raggruppati per dipartimento---------- ");
        Map<Dipartimento, List<Dipendente>> dipendentiPerDipartimento = streamDip.collect(
                        Collectors.groupingBy(Dipendente::getDipartimento));
        for (Map.Entry<Dipartimento, List<Dipendente>> entry : dipendentiPerDipartimento.entrySet()) {
            System.out.println("Dipartimento: " + entry.getKey());
            for (Dipendente d : entry.getValue()) {
                d.infoDipendente();
            }
            System.out.println();
        }
    }
}
