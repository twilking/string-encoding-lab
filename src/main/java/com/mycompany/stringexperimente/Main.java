/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.stringexperimente;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 *
 * @author Tim Wilking
 */
public class Main {

    private static Path file;

    private static final String CHARSET_ENCODING = StandardCharsets.UTF_16.name();

    // UTF-16 Encoding / BigIndian BOM FE FF / LittleIndian BOM FF FE
    // 𤽜 = D8 53 DF 5C (Surrogates)
    // 中 = 4E 2D
    // 文 = 65 87
    // 字 = 5B 57
    // UNICODE Tabelle  : http://www.tamasoft.co.jp/en/general-info/unicode.html
    // UTF Converter    : http://macchiato.com/unicode/convert.html
    //String with surogates
    private static final String string = "𤽜中文字𤽜";

    //String without surogate
    //static String string = "中文字";
    /**
     * Reverses the string in different abstractions like bytes, chars and
     * StringBuilder.
     */
    public static void main(String[] args) throws UnsupportedEncodingException, IOException {

        file = Paths.get("C:\\temp\\StringExp\\string_orignal.txt");
        Files.write(file, string.getBytes(CHARSET_ENCODING), StandardOpenOption.CREATE);

        //StringBuilder+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        StringBuilder stringBuilder = new StringBuilder(string);
        stringBuilder.reverse();

        byte[] stringBuilderbytes = stringBuilder.toString().getBytes(CHARSET_ENCODING);

        file = Paths.get("C:\\temp\\StringExp\\stringBuilder_reverse.txt");
        Files.write(file, stringBuilderbytes, StandardOpenOption.CREATE);

        //StringReader+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        StringReader stringReader = new StringReader(string);

        char[] readedChars = new char[Math.toIntExact(string.chars().count())];

        if (readedChars.length > 0) {

            int c = stringReader.read(readedChars);

            while (c != -1) {
                c = stringReader.read(readedChars);
            }

        }

        file = Paths.get("C:\\temp\\StringExp\\stringReader_original.txt");
        Files.write(file, new String(readedChars).getBytes(CHARSET_ENCODING), StandardOpenOption.CREATE);

        //Bytes+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        byte[] pureBytes = string.getBytes(CHARSET_ENCODING);
        byte[] reverseByte = new byte[pureBytes.length];

        int j = 0;
        for (int i = pureBytes.length - 1; i >= 0; i--) {
            reverseByte[i] = pureBytes[j];
            j++;
        }

        file = Paths.get("C:\\temp\\StringExp\\pureBytes_reverse.txt");
        Files.write(file, reverseByte, StandardOpenOption.CREATE);

        //Char++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        char[] chars = string.toCharArray();
        char[] reversChars = new char[chars.length];

        int jj = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            reversChars[i] = chars[jj];
            jj++;
        }

        file = Paths.get("C:\\temp\\StringExp\\chars_reverse.txt");
        Files.write(file, String.valueOf(reversChars).getBytes(CHARSET_ENCODING), StandardOpenOption.CREATE);

    }

}
