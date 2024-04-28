package Defaults;

import static java.lang.Character.*;


public class Verification {

    public  static boolean  Alpha(String ch){
    if (ch.length()==0)
                return true;
        for(int i=0; i<ch.length();i++)
            if(!isLetter(ch.charAt(i)) && !isWhitespace(ch.charAt(i)))
                return false;
        if(ch.indexOf(" ")==0 || ch.indexOf(" ")==ch.length()-1)
            return false;
        return true;

    }
    public  static boolean  test(String ch){
        int j=0,k=0,h=0,f=0;
        if (ch.length()==0)
            return false;
        for(int i=0; i<ch.length();i++)
            if(isDigit(ch.charAt(i)))
                j=1;
            else if(isLetter(ch.charAt(i)))
            {
                if(isUpperCase(ch.charAt(i)))
                    k=1;
                else
                    h=1;
            }
            else
                f=1;
        if(k+j+h+f ==4)
            return true;
        return false;
    }
    public static boolean verifdate(String ch)
    {
        if(ch.charAt(4)!='-' && ch.charAt(7)!='-'  )
            return false;
        String an=ch.substring(0,ch.indexOf("-")+1);
        String mon=ch.substring(5,ch.lastIndexOf("-")+1);
        String day=ch.substring(8,9);
        if(!Num(an) && !Num(mon) && !Num(day))
            return false;
        int d=Integer.parseInt(day);
        int m=Integer.parseInt(mon);
        int a=Integer.parseInt(an);
        if(d>31 && d<1 && m<1 && m>12 && a<1900)
            return false;
        return true;
    }
    public  static boolean  Num(String ch){
        if (ch.length()==0)
            return true;
        for(int i=0; i<ch.length();i++)
            if(!isDigit(ch.charAt(i)))
                return false;
        return true;

    }
    public static boolean verif(String ch){
        String premier=ch.substring(0,4);
        String deux=ch.substring(5,9);
        if(!Alpha(premier))
            return false;
        if(!Num(deux))
            return false;
        return true;

    }

    public static void main(String[] args) {


    }

}
