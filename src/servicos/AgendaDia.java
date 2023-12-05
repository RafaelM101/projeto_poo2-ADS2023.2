package servicos;
import components.Terminal;


import java.time.LocalTime;

import java.util.*;

public class AgendaDia implements Terminal {

    private HashMap<LocalTime, Servico> Agendamentos_dia = new HashMap<>();
    private Set<LocalTime> HoraDisponivel = new LinkedHashSet<>();

    public Set<LocalTime> getHoraDisponivel() {
       return this.HoraDisponivel;
    }

    public AgendaDia(){
        String[] horariosString = {
                "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30",
                "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30",
                "16:00", "16:30", "17:00", "17:30", "18:00", "18:30"
        };
        for ( String horario : horariosString) {
            this.HoraDisponivel.add(LocalTime.parse(horario));
    }
    }
    public void imprimirAgenda() {
        List<LocalTime> horariosManha = new ArrayList<>();
        List<LocalTime> horariosTarde = new ArrayList<>();


        for (LocalTime hora : this.HoraDisponivel) {
            if (hora.isBefore(LocalTime.of(12, 30))) {
                horariosManha.add(hora);
            } else {
                horariosTarde.add(hora);
            }
        }

        System.out.println(NEGRITO + CYAN +"\tHorários Disponíveis:" + RESETAR);

        System.out.print(NEGRITO + CYAN +"\t\tManhã: " + RESETAR);
        Collections.sort(horariosManha);
        imprimirHorarios(horariosManha);

        System.out.print(NEGRITO + CYAN +"\t\tTarde: " + RESETAR);
        Collections.sort(horariosTarde);
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
        if(this.HoraDisponivel.contains(horario)){
            this.Agendamentos_dia.put(horario, servico);
            this.HoraDisponivel.remove(horario);
        }}

    public void desmarcarHorario(LocalTime horario){
        if(this.Agendamentos_dia.get(horario)!=null){
            this.Agendamentos_dia.remove(horario);
            this.HoraDisponivel.add(horario);
        }
    }

    public boolean verificarHorario(LocalTime horario){;
        return this.HoraDisponivel.contains(horario);
    }

}
