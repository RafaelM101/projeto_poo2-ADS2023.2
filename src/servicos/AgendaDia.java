package servicos;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class AgendaDia {

    private HashMap<LocalTime, Servico> Agendamentos_dia = new HashMap<>();
    private Set<LocalTime> HoraDisponivel = new LinkedHashSet<>();


    public AgendaDia(){
        String[] horariosString = {
                "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30",
                "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30",
                "16:00", "16:30", "17:00", "17:30", "18:00", "18:30"
        };
        for ( String horario : horariosString) {
            HoraDisponivel.add(LocalTime.parse(horario));
    }
    }

    @Override
    public String toString() {
        return HoraDisponivel + "Horários Disponíveis:" + "\n";
    }


    public void agendarHorario(LocalTime horario, Servico servico){
        if(HoraDisponivel.contains(horario)){
            Agendamentos_dia.put(horario, servico);
            HoraDisponivel.remove(horario);
        }}

    public void desmarcarHorario(LocalTime horario){
        if(Agendamentos_dia.get(horario)!=null){
            Agendamentos_dia.remove(horario);
            HoraDisponivel.add(horario);
        }
    }

    public void mudarHorario(LocalTime horario, Servico servico){
        desmarcarHorario(horario);
        agendarHorario(horario, servico);
    }

    public boolean verificarHorario(LocalTime horario){;
        return HoraDisponivel.contains(horario);
    }
    public HashMap<LocalTime, Servico> getAgendamentos_dia() {
        return Agendamentos_dia;
    }

    public Set<LocalTime> getHoraDisponivel() {
        return HoraDisponivel;
    }
}
