import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.util.*;

public class lab6_q1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int no_process = 0;
        try {
            OutputStream out = new FileOutputStream(
                    "D:\\vs code files\\java files\\sem 4 assigment\\CS266assigment\\lab6_q1.dat");
        } catch (Exception e) {
            System.out.println("Exception caught " + e);
        }
        System.out.println("Number of processes (maximum 5)");
        no_process = sc.nextInt();
        String[] pid = new String[no_process];
        for (int i = 0; i < no_process; i++) {
            int temp = i;
            temp += 1;
            System.out.println("Process id of process " + temp);
            pid[i] = sc.next();
        }
        int[] arrival = new int[no_process];
        for (int i = 0; i < no_process; i++) {
            int temp1 = i;
            temp1 += 1;
            System.out.println("Enter Process arrival of process" + temp1);
            arrival[i] = sc.nextInt();
        }
        int[] processing = new int[no_process];
        for (int i = 0; i < no_process; i++) {
            int temp2 = i;
            temp2 += 1;
            System.out.println("Enter Processing time for process " + temp2);
            processing[i] = sc.nextInt();
        }
        float[] io = new float[no_process];
        for (int i = 0; i < no_process; i++) {
            int temp3 = i;
            temp3 += 1;
            System.out.println("Enter The elapsed time between I/O interrupts of process " + temp3);
            io[i] = sc.nextFloat();
        }
        float[] iowait = new float[no_process];
        for (int i = 0; i < no_process; i++) {
            int temp4 = i;
            temp4 += 1;
            System.out.println("Enter Time spent in waiting and processing the I/O for process " + temp4);
            iowait[i] = sc.nextFloat();
        }
        int[] priority = new int[no_process];
        for (int i = 0; i < no_process; i++) {
            int temp5 = i;
            temp5 += 1;
            System.out.println("Enter Priority for process " + temp5);
            priority[i] = sc.nextInt();
        }
        String[][] write_arr = new String[no_process][6];
        for (int i = 0; i < no_process; i++) {
            write_arr[i][0] = pid[i];
        }
        for (int i = 0; i < no_process; i++) {
            write_arr[i][1] = Integer.toString(arrival[i]);
        }
        for (int i = 0; i < no_process; i++) {
            write_arr[i][2] = Integer.toString(processing[i]);
        }
        for (int i = 0; i < no_process; i++) {
            write_arr[i][3] = Float.toString(io[i]);
        }
        for (int i = 0; i < no_process; i++) {
            write_arr[i][4] = Float.toString(iowait[i]);
        }
        for (int i = 0; i < no_process; i++) {
            write_arr[i][5] = Integer.toString(priority[i]);
        }
        try {
            FileWriter fw = new FileWriter(
                    "D:\\vs code files\\java files\\sem 4 assigment\\CS266assigment\\lab6_q1.dat");
            for (int i = 0; i < no_process; i++) {
                for (int j = 0; j < 6; j++) {
                    fw.write(" " + write_arr[i][j] + " ");
                }
                fw.write("\n");
            }
            fw.close();
        } catch (Exception e) {
            System.out.println("Exception caught: " + e);
        }
    }
}
