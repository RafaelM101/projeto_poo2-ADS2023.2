package servicos;
import java.time.LocalTime;

public class AgendaDia {

    Agendamento[] listaAgendamento = new Agendamento[10];
    LocalTime[] HoraAgendadas = new LocalTime[20];

    LocalTime[] HoraDisponivel = new LocalTime[20];


    public AgendaDia(){
        String[] horariosString = {
                "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30",
                "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30",
                "16:00", "16:30", "17:00", "17:30", "18:00", "18:30"
        };
        for (int i = 0; i < HoraDisponivel.length; i++) {
            HoraDisponivel[i] = LocalTime.parse(horariosString[i]);
    }
    }


}
