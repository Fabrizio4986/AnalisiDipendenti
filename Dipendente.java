import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

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

    public static void main(String[] args) {


        FiltroDipendenti filtroEta = d -> d.getEta()>= 30;
        FiltroDipendenti filtroStip = d-> d.getStipendio()>=50000;
        List<Dipendente> dipendenti = Arrays.asList(
                new Dipendente("Mario", 35, 60000,Dipartimento.HR),
                new Dipendente("Luigi", 28, 45000,Dipartimento.IT),
                new Dipendente("Anna", 40, 52000,Dipartimento.SALES),
                new Dipendente("Paolo", 25, 30000,Dipartimento.SALES),
                new Dipendente("Luca", 38, 51000,Dipartimento.FINANCES)
        );

        List<Dipendente> dipOverTrenta = FiltroDip.filtra(dipendenti,filtroEta);
        for (Dipendente dip : dipOverTrenta) {
            System.out.println(dip.getNome());
        }
        List<Dipendente> dipStipAlto = FiltroDip.filtra(dipendenti,filtroStip);
        for (Dipendente dip : dipStipAlto) {
            System.out.println(dip.getStipendio());
        }
    }
    }
