package com.company;

public class Main
{
    static String[][] neravnCode = {
            {"000"," "},
            {"100","о"},
            {"1000","е"},
            {"1100","а"},
            {"10000","и"},
            {"10100","т"},
            {"11000","н"},
            {"11100","с"},
            {"101000","р"},
            {"101100","в"},
            {"110000","л"},
            {"110100","к"},
            {"111000","м"},
            {"111100","д"},
            {"1010000","п"},
            {"1010100","у"},
            {"1011000","я"},
            {"1011100","ы"},
            {"1101000","з"},
            {"1101100","ь"},
            {"1110000","б"},
            {"1110100","г"},
            {"1111000","ч"},
            {"1111100","й"},
            {"10101000","х"},
            {"10101100","ж"},
            {"10110000","ю"},
            {"10110100","ш"},
            {"10111000","ц"},
            {"10111100","щ"},
            {"11010000","э"},
            {"11011000","ф"}
    };
    static String[][] fanoCode = {
            {"000"," "},
            {"001","о"},
            {"0100","е"},
            {"0101","а"},
            {"0110","и"},
            {"0111","т"},
            {"1000","н"},
            {"1001","с"},
            {"10100","р"},
            {"10101","в"},
            {"10110","л"},
            {"10111","к"},
            {"11000","м"},
            {"110010","д"},
            {"110011","п"},
            {"110100","у"},
            {"110110","я"},
            {"110111","ы"},
            {"111000","з"},
            {"111001","ь"},
            {"111010","б"},
            {"111011","г"},
            {"111100","ч"},
            {"1111010","й"},
            {"1111011","х"},
            {"1111100","ж"},
            {"1111101","ю"},
            {"11111100","ш"},
            {"11111101","ц"},
            {"11111110","щ"},
            {"111111110","э"},
            {"111111111","ф"}
    };
    public static void main(String[] args)
    {
        String neravn = "11110010100010101001110100100001010100000011100110000100101100000110001000000111010010010110010010100010000101001101100";
        String fano = "10101000101110011010001101100100011010001000001110101101111011000100001110100110001000001";
        System.out.println(decoderNeravn(neravn));
        String resultFano = decoderFano(fano);
        System.out.println(resultFano);
    }

    private static String decoderNeravn (String code){
        String text = "";
        String codeE = code.replace("000001", "00 1");
        String[] words = codeE.split(" ");
        String decod;
        for (int w=0;w<words.length;w++){
            decod = words[w].replace("001", "00 1");
            String[] letters = decod.split(" ");
            for(String letter : letters){
                for (int i=0; i<32;i++){
                    if (letter.equals(neravnCode[i][0])){
                        text = text.concat(neravnCode[i][1]);
                        break;
                    }
                }
            }
            text=text.concat(" ");
        }
        return text;
    }

    private static String decoderFano (String code){
        String text = "";
        String letter = "";
        String el;
        for (int c = 0; c < code.length(); c++) {
            char l = code.charAt(c);
            el = String.valueOf(l);
            letter = letter.concat(el);
            for (int i = 0; i < 32; i++) {
                if (letter.equals(fanoCode[i][0])){
                    text = text.concat(fanoCode[i][1]);
                    letter = "";
                    break;
                }
            }
        }
        return text;
    }
}
