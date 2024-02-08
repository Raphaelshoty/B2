package com.b2.teste;



import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.*;

@Service
public class LeituraArquivo {

    private String mit;

    private String bitMap;

    public String processaIsoText(String message){

       this.mit = message.substring(0, 4);

       this.bitMap = this.hexToBinary(message.substring(4, 22));

       return this.mapCampos(message, mit, bitMap) ;
    }

    private String hexToBinary(String s){
        return "00" + new BigInteger(s,16).toString(2);
    }


    private List<String> verificaCamposPresentes(String bitMap){
        char[] bitmapArray = bitMap.toCharArray();
        var listCamposPresentes = new ArrayList<String>();

        for (int i = 0; i < bitmapArray.length; i++) {
            if (bitmapArray[i] == '1') {
                int fieldNumber = i + 1;
                listCamposPresentes.add(""+fieldNumber);
            }
        }
        return listCamposPresentes;
    }

    private String mapCampos(String data, String mit, String bitMap){
       var stb = new StringBuilder();
       stb.append("Versão 0 da iso 8583 de 1987,\nmensagem classe 2(Financeira)\nfunção 0(requisição) \norigem da transação 0(partindo da adiquirente)" + mit + "\n");
       stb.append("Message: " + mit + "\n");
       stb.append("Bitmap: "+bitMap+"\n");
       stb.append("Posição 3 no bitmap -> Processing Code: "+ data.substring(12,18) + "\n");
       stb.append("Posição 4 no bitmap -> Transaction Amount: "+ data.substring(18,31) + "\n");
       stb.append("Posição 7 no bitmap -> Transmission Date & Time: "+ data.substring(31,44) + "\n");
       stb.append("Posição 8 no bitmap -> Fee Amount: "+ data.substring(44,56) + "\n");
       stb.append("Posição 11 no bitmap -> System Trace Audit Number: "+ data.substring(56,62) + "\n");
       stb.append("Posição 12 no bitmap -> Local Transaction Time: "+ data.substring(62,68) + "\n");
       stb.append("Posição 15 no bitmap -> Settlement Date: "+ data.substring(68,73) + "\n");
       stb.append("Posição 19 no bitmap -> Acquiring Country: "+ data.substring(73,76) + "\n");
       stb.append("Posição 20 no bitmap -> PAN Country Code: "+ data.substring(76,79) + "\n");
       stb.append("Posição 23 no bitmap -> PAN Sequence Number: "+ data.substring(79,82) + "\n");
       stb.append("Posição 24 no bitmap -> Function Code: "+ data.substring(82,85) + "\n");
       stb.append("Posição 26 no bitmap -> POS Capture Code: "+ data.substring(85,87) + "\n");
       stb.append("Posição 32 no bitmap -> Acquiring Identification Code: "+ data.substring(87,88) + "\n");
       stb.append("Posição 35 no bitmap -> Track 2: "+ data.substring(88) + "\n");
       return stb.toString();
    }


}
