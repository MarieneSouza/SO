
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class Processo {

    private int PID;
    private int TP; // Tempo total de processamento
    private int CP; // Contador de Programa
    private String EP; // Estado do processo
    private int NES; // Número de vezes que realizou uma operação de E/S
    private int N_CPU; // Número de vezes que usou a CPU

    public Processo(int PID, int TP) {
        this.PID = PID;
        this.TP = TP;
        this.CP = 0;
        this.EP = "Pronto";
        this.NES = 0;
        this.N_CPU = 0;
    }

    public void executar() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("log.txt", true))) {
            Random rand = new Random();
            int quantum = 1000;

           
            while (CP < TP) {
               
                for (int i = 0; i < quantum && CP < TP; i++) {
                    CP++;
                    N_CPU++;
                    if (rand.nextDouble() < 0.01) { 
                        bloquear(writer);
                        break;
                    }
                }

             
                if (CP < TP) {
                    trocarContexto("Pronto", writer);
                } else {
                    terminar(writer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void bloquear(PrintWriter writer) {
        NES++;
        Random rand = new Random();
        if (rand.nextDouble() < 0.3) {
            trocarContexto("Pronto", writer);
        } else {
            trocarContexto("Bloqueado", writer);
        }
    }

    private void trocarContexto(String novoEstado, PrintWriter writer) {
        writer.println("Troca de Contexto: Processo " + PID + " " + EP + " -> " + novoEstado);
        EP = novoEstado;
    }

    private void terminar(PrintWriter writer) {
        EP = "Terminado"; 
        writer.println("Processo " + PID + " terminou.");
        writer.println("Dados do Processo " + PID + ":");
        writer.println("Tempo de Processamento: " + TP);
        writer.println("Contador de Programa (CP): " + CP);
        writer.println("Estado Final: " + EP);
        writer.println("Número de E/S: " + NES);
        writer.println("Número de CPU: " + N_CPU);
    }
}
