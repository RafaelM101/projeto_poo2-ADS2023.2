package servicos;
import components.Terminal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class AgendaDia implements Terminal {

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

   public void imprimirAgenda() {
       List<LocalTime> horariosManha = new ArrayList<>();
       List<LocalTime> horariosTarde = new ArrayList<>();


       for (LocalTime hora : HoraDisponivel) {
           if (hora.isBefore(LocalTime.of(12, 30))) {
               horariosManha.add(hora);
           } else {
               horariosTarde.add(hora);
           }
       }

       System.out.println(NEGRITO + CYAN +"\tHorários Disponíveis:" + RESETAR);

       System.out.print(NEGRITO + CYAN +"\t\tManhã: " + RESETAR);
       imprimirHorarios(horariosManha);

       System.out.print(NEGRITO + CYAN +"\t\tTarde: " + RESETAR);
       imprimirHorarios(horariosTarde);

       System.out.println();
   }

    private void imprimirHorarios(List<LocalTime> horarios) {
        for (int i = 0; i < horarios.size(); i++) {
            System.out.print(VERDE + horarios.get(i) + RESETAR);
            if (i < horarios.size() - 1) {
                System.out.print(VERDE + ", " + RESETAR);
            }
        }
        System.out.println();
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

    public void removerHorario(LocalTime horarioatual){
        HoraDisponivel.removeIf(hora -> hora.isBefore(horarioatual));
    }
}
