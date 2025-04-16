import java.util.ArrayList;
import java.util.List;

public class FiltroDip {
    public static List<Dipendente> filtra(List<Dipendente> dipendenti, FiltroDipendenti filtro) {
        List<Dipendente> risultato = new ArrayList<>();
        for (Dipendente d : dipendenti) {
            if (filtro.test(d)) {
                risultato.add(d);
            }
        }
        return risultato;
    }
}
