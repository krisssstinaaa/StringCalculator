package org.krissssxxxx;

public class Main {
    public static void main(String[] args) {
        StringCalculator calc = new StringCalculator();
        //конструкція try - catch застосована для обробки помилок, які можуть виникнути
        try{
            System.out.println(calc.add("//[kdlfk][fgfvxc]\n1fgfvxc2kdlfk3"));
        }
        catch (Exception e ){
            e.printStackTrace(System.out);
        }
    }
}
