import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class lab6_q2 {
    public static void main(String[] args) {
        System.out.println("Entered program2");
        File ogFile = null;
        for (String str : args) {
            File newFile = new File(str);
            if (newFile.exists()) {
                ogFile = newFile;
            }
        }
        String str = null;
        String temp_str = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(ogFile));
            while ((str = br.readLine()) != null) {
                temp_str += str;
            }
        } catch (Exception e) {
            System.out.println("Excpetion caught: " + e);
        }
        int cnt = 0;
        int len = temp_str.length();
        String[] stringof = new String[len];
        for (int i = 0; i < len; i++) {
            stringof[i] = "";
        }
        int j = 0;
        temp_str = temp_str.trim();
        int k = 0;
        outer: while (k < 30) {
            if (j == temp_str.length()) {
                return;
            }
            try {
                while (temp_str.charAt(j) != ' ') {
                    stringof[k] += temp_str.charAt(j);
                    j += 1;
                }
            } catch (Exception e) {
                System.out.println("Exception ignored");
            }
            j += 2;
            k += 1;
        }
        for (String x : stringof) {
            System.out.println(x);
        }
        int j_arr = 1;
        int j_burst = 2;
        int j_ioi = 3;
        int j_iow = 4;
        int total = 0;
        int count_i = 0;
        while (stringof[count_i] != "") {
            total += 1;
            count_i++;
        }
        int total_temp = total / 6;
        float[] arrival = new float[total_temp];
        float[] burst = new float[total_temp];
        float[] ioint = new float[total_temp];
        float[] iowait = new float[total_temp];
        float[] exit = new float[total_temp];
        float[] wait = new float[total_temp];
        float[] turnaround = new float[total_temp];
        /* int[] wait_time=new int[total_temp]; */
        for (int i = 0; i < total_temp; i++) {
            arrival[i] = Float.parseFloat(stringof[j_arr]);
            j_arr += 6;
            if (stringof[j_arr] == "") {
                break;
            }
        }
        for (int i = 0; i < total_temp; i++) {
            burst[i] = Float.parseFloat(stringof[j_burst]);
            j_burst += 6;
            if (stringof[j_burst] == "") {
                break;
            }
        }
        for (int i = 0; i < total_temp; i++) {
            ioint[i] = Float.parseFloat(stringof[j_ioi]);
            j_ioi += 6;
            if (stringof[j_ioi] == "") {
                break;
            }
        }
        for (int i = 0; i < total_temp; i++) {
            iowait[i] = Float.parseFloat(stringof[j_iow]);
            j_iow += 6;
            if (stringof[j_iow] == "") {
                break;
            }
        }
        /*
         * for(int i=0;i<total_temp;i++) { burst[i]= burst[i]+ioint[]; }
         */
        burst[total_temp / 2] = burst[total_temp / 2] + ioint[total_temp / 2];
        burst[total_temp / 2 + 1] = burst[total_temp / 2 + 1] + ioint[total_temp / 2 + 1];
        float remain[] = new float[total_temp];
        for (int i = 0; i < total_temp; i++) {
            remain[i] = burst[i];
        }
        float exec = 0;
        float arrive = arrival[0];
        float quantum = 10;
        while (true) {
            boolean done = true;
            for (int i = 0; i < total_temp; i++) {
                if (remain[i] > 0) {
                    done = false;
                    if (remain[i] > quantum && arrival[i] <= arrive) {
                        exec += quantum;
                        remain[i] -= quantum;
                        arrive++;
                    } else {
                        if (arrival[i] <= arrive) {
                            arrive++;
                            exec += remain[i];
                            remain[i] = 0;
                            exit[i] = exec;
                        }
                    }
                }
            }
            if (done == true) {
                break;
            }
        }
        for (int i = 0; i < total_temp; i++) {
            turnaround[i] = exit[i] - arrival[i];
        }
        for (int i = 0; i < total_temp; i++) {
            wait[i] = turnaround[i] - burst[i];
        }
        System.out.println("Turnaround " + " Waiting\t");
        float turn_sum = 0;
        float wait_sum = 0;
        for (int i = 0; i < total_temp; i++) {
            turn_sum += turnaround[i];
            wait_sum += wait[i];
            System.out.println(" " + turnaround[i] + "\t\t " + wait[i]);
        }
        System.out.println("Average turn around time: " + turn_sum / (float) total_temp);
        System.out.println("Average waiting time: " + wait_sum / (float) total_temp);
    }
}
