import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TextToAudio {

    public void textToAudio(String file) throws IOException {


        String filePathName = "F:\\textToAudio/готовое/";

        Concatenate cikloH = new Concatenate();
        Concatenate nomerH = new Concatenate();
        Concatenate ritmoH = new Concatenate();

        List<String> ciklo = new ArrayList<>();
        List<String> nomer = new ArrayList<>();
        List<String> ritmo = new ArrayList<>();

        List<String> lines2 = new ArrayList<>();

        RenameFile renameFileF = new RenameFile();
        Duration duration = new Duration();
        StringBuilder builder = new StringBuilder();

        char[] alphabet = {'_', 'А', 'Б', 'В', 'Г', 'Д', 'Е',
                'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н',
                'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц',
                'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я'};

        // Выборка первой строки из файла (для Названия сохраняемого файла)
        //Удаление пробелов в начале и в конце первой строки (для Названия сохраняемого файла)
        String fileName = file.replaceAll("^\\s+", "").replaceAll("\\s+$", "");
        //String fileName = firstStringNoWhiteSpaceStartEnd.toUpperCase();


        // создание списка из текстового файла
        List<String> lines = Files.readAllLines(Paths.get("doc.txt"));


        //Добавление '_' в конце строки с помощью StringBuilder
        for (String string : lines) {
            builder.append(string + "_");
        }
        System.out.println("Добавление '_' в конце строки с помощью StringBuilder " + builder);

        // перевод StringBuilder в Строку
        String result = builder.toString();
        System.out.println("// перевод StringBuilder в Строку " + result);

        //Замена пробелов на '_'
        lines2.add(result.replaceAll("\\s", "_"));

        //Создание переизлучения на Радастеид-100


        //преобразуем все буквы в листе строк в верхний регистр
        lines2.replaceAll(String::toUpperCase);

        //преобразовать массив строк в массив символов
        char[] characters = String.join("", lines2).toCharArray();

        //Удаление самого последнего пробела
        characters[characters.length - 1] = 0;

        //удалить 2 или более соседних символов '_' из массива
        for (int i = 0; i < characters.length; i++)
            while (characters[i] == '_' && characters[i + 1] == '_') { // проверка если два пробела подряд
                for (int j = (i + 1); j < characters.length; j++)
                    characters[j - 1] = characters[j]; // shift the rest of array  - сдвинуть остальную часть массива
                characters[i] = '_';
                characters[characters.length - 1] = 0;
            }

        // Добавление фразы в начало
        //ciklo.add("F:\\textToAudio/название/" + text2 + ".wav");
        //ciklo.add("F:\\textToAudio/Начало_переизлучения.wav");
        //nomer.add("F:\\textToAudio/Начало_переизлучения.wav");
        // ritmo.add("F:\\textToAudio/Начало_переизлучения.wav");

        // создание списка для сборки wav файла на ЦиклоХладавит (_оль.wav - Хладастея минус)
        // создание списка для сборки wav файла на Ритмохладавит (_ИРЪ.wav - Блокиратор)
        // создание списка для сборки wav файла на Номерной Хладавит (0.wav - Хладастея Даль)
        for (char character : characters)
            for (int i = 0; i < alphabet.length; i++)
                if (character == alphabet[i]) {
                    ciklo.add("F:\\textToAudio/Chiklo/" + alphabet[i] + "оль.wav");
                    //nomer.add("F:\\textToAudio/Nomer/" + i + ".wav");
                   // ritmo.add("F:\\textToAudio/Ritmo/" + alphabet[i] + "ИРЪ.wav");
                }

        // Добавление фразы в конце
        // ciklo.add("F:\\textToAudio/Конец_переизлучения.wav");
        //nomer.add("F:\\textToAudio/Конец_переизлучения.wav");
        //ritmo.add("F:\\textToAudio/Конец_переизлучения.wav");

        // Добавление варианта музыки рамдомным способом в конце.
        // int rdm = (int) (Math.random() * 5);
        //ciklo.add("F:\\textToAudio/Sound/v" + rdm + ".wav");
        //nomer.add("F:\\textToAudio/Sound/v" + rdm + ".wav");
        // ritmo.add("F:\\textToAudio/Sound/v" + rdm + ".wav");

        //визуальный блок проверки
        // System.out.println(Math.random() + " " + rdm); // рандом не превышает 5
        System.out.println("изуально проверять на символы '_' " + characters); // визуально проверять на символы '_'
        System.out.println("Циклохладавит" + ciklo);
        //System.out.println(nomer);
        //System.out.println(ritmo);

        try {
            cikloH.concatenateFiles(ciklo, filePathName + fileName + "_Ц ");
            //nomerH.concatenateFiles(nomer, filePathName + fileName + "_N ");
            //ritmoH.concatenateFiles(ritmo, filePathName + fileName + "_Р ");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Создание
        File fileC = new File(filePathName + fileName + "_Ц ");
       // File fileN = new File(filePathName + fileName + "_N ");
       // File fileR = new File(filePathName + fileName + "_Р ");

        renameFileF.rename(filePathName + fileName + "_Ц ", duration.durationFile(fileC), ".wav");
       // renameFileF.rename(filePathName + fileName + "_N ", duration.durationFile(fileN), ".wav");
       // renameFileF.rename(filePathName + fileName + "_Р ", duration.durationFile(fileR), ".wav");
    }
}