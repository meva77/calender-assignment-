import java.util.Scanner;

class me{
public static void main(String []x){

  me obj = new me();
  obj.arraymethod();

}
me(){}
me(String name,int age){
  System.out.println(".....................");
  System.out.println("name:\t"+name+"\nage:\t"+age);
  System.out.println(".....................");
}
me[] arraymethod(){

  String name;
  int age;

  me myarray[]=new me[3];
  Scanner input = new Scanner(System.in);

  for(int i=0;i<myarray.length;i++){

    System.out.println("enter name");
    name=input.next();
    System.out.println("enter age");
    age=input.nextInt();

  me oo = new me(name,age);
   myarray[i]=(me)oo;

 

 }
 return myarray;

}
}