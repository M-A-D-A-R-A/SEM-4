#include <bits/stdc++.h>
using namespace std;
string id;
int cnt=0;
vector<string> IDs; // book id is auto generated
struct data // a structure is created which holds the data about one book
including the following options
{
string bookId;
string bookTitle;
string publisher;
string authorName;
int TotalCopies=0; // Its value is total no. of copies of the book present in
library
int AvailableCopies=0; // its value is the remaining books in library
};
void insertBook()
{
ofstream fout;
data val;
cout<<"Enter Book Title:"<<endl;
cin>>val.bookTitle;
cout<<"Enter Book Publisher:"<<endl;
cin>>val.publisher;
cout<<"Enter Book Author Name:"<<endl;
cin>>val.authorName;
cout<<"Enter Total copies:"<<endl;
cin>>val.TotalCopies;
id+='a';
cnt++;
char ch=cnt+'0';
val.bookId=id+ch;
fout.open("Input.txt",ios::app);
fout.write((char*)&val,sizeof(val));
fout.close();
}
void searchBook()
{
string pattern;
cout<<"Enter Book Name";
cin>>pattern;
}
void printBook()
{
ifstream fin;
fin.open("Input.txt",ios::in);
fin.seekg(0);
data val;
fin.read((char*)&val,sizeof(val));
while(!fin.eof())
{
cout<<val.authorName<<endl;
cout<<val.AvailableCopies<<endl;
fin.read((char*)&val,sizeof(val));
}
fin.close();
}
void exitProgram()
{
printBook();
cout<<"Thankyou!!";
exit(0);
}
int main()
{
int choice;
while(true)
{
cout<<"Choose an option"<<endl;
cout<<"1: To add a new book"<<endl;
cout<<"2: To search a book"<<endl;
cout<<"3: To issue a book"<<endl;
cout<<"4: To return a book"<<endl;
cout<<"-1: To exit"<<endl;
cin>>choice;
switch(choice)
{
case 1: insertBook();
break;
case 2: searchBook();
break;
case 3:
break;
case 4: break;
case -1: exitProgram(); break;
default: exitProgram(); break;
}
}
printBook();
return 0;
}