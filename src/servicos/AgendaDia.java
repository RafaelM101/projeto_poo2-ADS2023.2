package servicos;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class AgendaDia {

    private HashMap<LocalTime, Servico> Agendamentos_dia = new HashMap<>();
    private Set<LocalTime> HoraDisponivel = new HashSet<>();


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

    public void agendarHorario(String horario, Servico servico){
        LocalTime horario_escolhido = LocalTime.parse(horario);
        if(HoraDisponivel.contains(horario_escolhido)){
            Agendamentos_dia.put(horario_escolhido, servico);
            HoraDisponivel.remove(horario_escolhido);
        }}

    public void desmarcarHorario(String horario){
        LocalTime horario_escolhido = LocalTime.parse(horario);
        if(Agendamentos_dia.get(horario_escolhido)!=null){
            Agendamentos_dia.remove(horario_escolhido);
            HoraDisponivel.add(horario_escolhido);
        }
    }

    public void mudarHorario(String horario, Servico servico){
        desmarcarHorario(horario);
        agendarHorario(horario, servico);
    }

    public HashMap<LocalTime, Servico> getAgendamentos_dia() {
        return Agendamentos_dia;
    }

    public Set<LocalTime> getHoraDisponivel() {
        return HoraDisponivel;
    }
}
