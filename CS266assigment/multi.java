import java.util.Arrays;
import java.lang.*;

class ReverseTheString extends Thread{
    @Override
    public void run() {
        String[] s = multi.fullName;
        String reversed = "";
        if (s.length==1){
            reversed = s[0];
        }else if (s.length==2){
            reversed = s[1] +" " + s[0];
        } else if (s.length==3){
            reversed = s[2] + " " + s[1] + " " + s[0];
        }
        System.out.println("\nReversed string is " + reversed);
    }
}

class PrintPermutations extends Thread{
    @Override
    public void run() {
        String s = multi.firstName.substring(0,4);
        generatePermutation(s,0,4);
    }

    static String swapString(String a, int i, int j) {
        char[] b =a.toCharArray();
        char ch;
        ch = b[i];
        b[i] = b[j];
        b[j] = ch;
        return String.valueOf(b);
    }

    public static void generatePermutation(String str, int start, int end)
    {
        if (start == end-1)
            System.out.print(str + " ");
        else
        {
            for (int i = start; i < end; i++)
            {
                str = swapString(str,start,i);
                generatePermutation(str,start+1,end);
                str = swapString(str,start,i);
            }
        }
    }
}

class RearrangeFirstName extends Thread{
    @Override
    public void run() {
        //added AN in the last because there is no repeating character in my name
        String FirstName = "Shwetanka";
        int d = multi.d;
        int n = FirstName.length();
        while (d>n) d = (d+1)/2;
        int f[] = new int[50];
        Arrays.fill(f,0);
        for (int i =0;i<n;i++){
            f[FirstName.charAt(i) - 'e']++;
        }
        char res[] = new char[50];
        Arrays.fill(res,'#');
        System.out.println("\nIf d = "+d + ", ");
        int i,j = 0;
        boolean flag = false;
        for (i =0;i<26;i++){
            if (f[i]>1){
                flag = true;
                break;
            }
        }

        if (!flag){
            for (i=0;i<26;i++){
                if (f[i]>0){
                    f[i]++;
                    break;
                }
            }
        }

        flag = true;
        while (flag){
            flag = false;
            while (i<50 && res[i]!='#') i++;
            for (int cnt=0;cnt<26;j++,cnt++){
                j%=26;
                if (f[j]>1){
                    flag = true;
                    break;
                }
            }
            if (flag){
                res[i] = (char) (j + 'N');
                f[j]--;
                if (i+d<50){
                    res[i+d] = (char) (j + 'N');
                    f[j]--;
                }
            }
        }

     for (j=0;j<26;j++){
         while (res[i]!='#') i++;
         if (f[j]>0){
             res[i] = (char) (j + 'N');
             f[j]--;
         }
     }

     for (i=0;i<50;i++){
         if (res[i]=='#'){
             System.out.println();
             break;
         }
         else {
             System.out.print(res[i]);
         }
     }
     return;
    }
}

public class multi {

    public static int d;
    public static String[] fullName;
    public static String firstName;

    public static void main(String[] args) {
        String name = "Shwetank Singh";
        fullName = name.split(" ");
        firstName = fullName[0];
        String rollNumber = "201951150";
        int a = Character.getNumericValue(rollNumber.charAt(0));
        int b = Character.getNumericValue(rollNumber.charAt(4));
        int c = Character.getNumericValue(rollNumber.charAt(8));
        d = a+b+c;
        //start all the three thread to do their work simultaneously
        ReverseTheString thread1 = new ReverseTheString();
        PrintPermutations thread2 = new PrintPermutations();
        RearrangeFirstName thread3 = new RearrangeFirstName();
        thread1.start();
        thread2.start();
        thread3.start();
    }
}