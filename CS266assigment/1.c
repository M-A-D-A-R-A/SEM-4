#include<stdio.h>
#include <signal.h>
#include<sys/types.h>
#include<unistd.h>
void handle_sigcommand(int num)
{
 if(num==SIGINT)
 {
 printf("\n INTERRUPT \n");
 sleep(10);
 }
 else if(num==SIGKILL)
 {
 printf("\n KILLED \n");
 int temp=getpid();
 kill(temp,SIGKILL);

 }
}
int main()
{
 signal(SIGINT,handle_sigcommand);
 signal(SIGKILL,handle_sigcommand);
 while(1)
 {
 printf("WHY");
 sleep(2);
 }
 return 0;
}