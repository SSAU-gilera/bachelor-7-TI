package com.company;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {

            String hoffman = "XAAAAXXAXAAX";
            String adapt = "DABDCCCAAA";
        //String methodLZ = "ЗАБОР ЗАБОРИСТЫЙ";
        String methodLZ = "ДЕНЬ ПЕНЬ КОРЕНЬ";
            int sizeOfDictionary = 8;
            int sizeOfBuffer = 6;
            String decodAdapt = "'р'0'о'00'а'0100'п'1100'н'011111111101";
            String decodLZ77 = "<0,0,с> <0,0,о> <0,0,в> <0,0,а> <0,0, > <5,2,б> <5,1,к> <3,2,б> <5,2,е> <0,0,н> <4,3,к>";
            String decodLZSS = "[0'п'] [0'о'] [0'ж'] [0'а'] [0'р'] [0' '] [1<4,2>] [1<6,1>] [1<4,1>] [1<5,1>] [0'к'] [1<5,3>] [0'л'] [1<9,1>]";
            //String decodLZ78 = "<0,0,с> <0,0,о> <0,0,в> <0,0,а> <0,0, > <5,2,б> <5,1,к> <3,2,б> <5,2,е> <0,0,н> <4,3,к>";

            System.out.println("\nЗадание 1");
            Hoffman.methodHoffman(hoffman);
            System.out.println("\nЗадание 3");
            methodLZ77(methodLZ,sizeOfDictionary,sizeOfBuffer);
            System.out.println("\nЗадание 4");
            methodArifm(adapt);
    }

    public static ArrayList<ArrayList<String>> searchChars(String text){
        int numOfChars = text.length();
        String chars = "";
        ArrayList<ArrayList<String>> allChars = new ArrayList<>();
        for (int n = 0; n<numOfChars;n++){
            int numOfCurrChar = 0;
            ArrayList<String> currChar = new ArrayList<String>();
            char l = text.charAt(n);
            String el = String.valueOf(l);
            if (!chars.contains(el)){
                chars = chars + el;
                currChar.add(el);
                for (int m = 0; m<numOfChars;m++){
                    if (text.charAt(n)== text.charAt(m)){
                        numOfCurrChar++;
                    }
                }
                currChar.add(Integer.toString(numOfCurrChar));
                allChars.add(currChar);
            }
        }
        ArrayList<String> a,b;
        for (int i=0;i< allChars.size();i++){
            for (int j=0;j<allChars.size();j++){
                if (Integer.parseInt(allChars.get(i).get(1)) > Integer.parseInt(allChars.get(j).get(1))){
                    a = allChars.get(i);
                    b = allChars.get(j);
                    allChars.set(i,b);
                    allChars.set(j,a);
                }
            }
        }
        double startNum = 0;
        double end = 1;
        double dnn = end / numOfChars;
        double freq;
        allChars.get(0).add(Double.toString(startNum));
        for (int i=1;i<allChars.size();i++){
            int numOfCurrChar = Integer.parseInt(allChars.get(i-1).get(1));
            freq = dnn*numOfCurrChar;
            startNum = startNum +freq;
            allChars.get(i-1).add(Double.toString(startNum));
            allChars.get(i).add(Double.toString(startNum));
        }
        allChars.get(allChars.size()-1).add(Double.toString(end));
        return allChars;
    }

    public static void methodArifm(String text) {
        System.out.println("Cообщение: " + text);
        String chars = text;
        int numOfChars = text.length();
        ArrayList<ArrayList<String>> allChars = searchChars(chars);
        double min = 0;
        double max = 1;
        double delta;
        for (int n = 0; n<numOfChars;n++) {
            char l = text.charAt(n);
            String el = String.valueOf(l);
            for (int i=0;i<allChars.size();i++){
                String currEl = allChars.get(i).get(0);
                if (el.equals(currEl)){
                    delta = max - min;
                    double start = Double.parseDouble(allChars.get(i).get(2));
                    double end = Double.parseDouble(allChars.get(i).get(3));
                    max = min + delta*end;
                    min = min + delta*start;
                }
            }
        }
        DecimalFormat dfMin = new DecimalFormat("#.#######");
        DecimalFormat dfMax = new DecimalFormat("#.##########");
        System.out.println("Закодированное сообщение: [ " + dfMin.format(min) +" ; "+dfMax.format(max)+" ]");
    }

    public static void methodLZ77(String text, int nSlov, int nBuff){
        System.out.println("Cообщение: " + text);
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        ArrayList<String> slovar = new ArrayList<>();
        for (int i =0;i<nSlov;i++){
            slovar.add("_");
        }
        ArrayList<String> buffer = new ArrayList<>();
        for (int i=0;i<nBuff;i++){
            char l = text.charAt(i);
            String el = String.valueOf(l);
            buffer.add(el);
        }
        int currChar = nBuff;
        int first = 0;
        int second = 0;
        do{
            String elemFromSlov = null;
            ArrayList<String> oneResult = new ArrayList<>();
            for (int i =0;i<nSlov;i++){
                if (buffer.get(0).equals(slovar.get(i))){
                    elemFromSlov = buffer.get(0);
                }
            }
            if (buffer.get(0).equals(elemFromSlov)){
                second++;
                if (second==1){
                    first = slovar.indexOf(elemFromSlov);
                }
                slovar.add(buffer.get(0));
                buffer.remove(0);
                slovar.remove(0);
                if (currChar<text.length()) {
                    buffer.add(String.valueOf(text.charAt(currChar)));
                }
                currChar++;
            }
            else{
                oneResult.add(Integer.toString(first));
                oneResult.add(Integer.toString(second));
                oneResult.add(buffer.get(0));
                System.out.println(oneResult);
                result.add(oneResult);
                second = 0;
                first = 0;
                slovar.add(buffer.get(0));
                slovar.remove(0);
                if (currChar<text.length()) {
                    buffer.add(String.valueOf(text.charAt(currChar)));
                }
                buffer.remove(0);
                currChar++;
            }

        }while (buffer.size()>0);
        //System.out.println("Закодированное сообщение: " + result);
    }
}



