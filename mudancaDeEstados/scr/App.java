
public class App {

    public static void main(String[] args) {
       
        Processo[] processos = new Processo[10];
        processos[0] = new Processo(0, 10000);
        processos[1] = new Processo(1, 5000);
        processos[2] = new Processo(2, 7000);
        processos[3] = new Processo(3, 3000);
        processos[4] = new Processo(4, 3000);
        processos[5] = new Processo(5, 8000);
        processos[6] = new Processo(6, 2000);
        processos[7] = new Processo(7, 5000);
        processos[8] = new Processo(8, 4000);
        processos[9] = new Processo(9, 10000);

      
        for (Processo p : processos) {
            p.executar();
        }
    }
}
