package com.example.project.dop;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.example.project.objects.Room;
import com.example.project.objects.elements.Floor;
import com.example.project.objects.elements.Roof;
import com.example.project.objects.elements.Wall;
import com.example.project.objects.Work;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Import {

    public XSSFWorkbook createWorkbook(File file) throws IOException, InvalidFormatException {
        return new XSSFWorkbook(file);
    }

    public XSSFWorkbook createWorkbook(InputStream inputStream) throws IOException, InvalidFormatException {
        return new XSSFWorkbook(inputStream);
    }

    public void impObject(XSSFWorkbook workbook) throws IOException, InvalidFormatException {

        XSSFSheet sheet = workbook.getSheetAt(0);

        int numRowWithId = -1;  // Ряд, начиная с которого получаем интересующие нас значения
        int numCellWithId = -1; // Колонока, начиная с которой получаем интересующие на значения

        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            if (sheet.getRow(i) != null) {

                for (int j = 0; j < sheet.getRow(i).getLastCellNum(); j++) {

                    if (sheet.getRow(i).getCell(j) != null) {
                        if (sheet.getRow(i).getCell(j).getCellType() == CellType.NUMERIC) {
                            if (sheet.getRow(i).getCell(j).getNumericCellValue() == 1) {
                                numRowWithId = i;
                                numCellWithId = j - 1; // -1, потому что смотрим следующую клетку после интересующей нас
                                break;
                            }
                        }
                    }

                }
            }
            if (numRowWithId != -1) {
                break;
            }
        }

        if (numRowWithId != -1) {


            // numRowWithId + 1     (+1 потому-что numRowWithId это ряд с id для совпадения полей, а с +1 получаем ряды со значениями)
            for (int j = numRowWithId + 1; j <= sheet.getLastRowNum(); j++) {

                if (sheet.getRow(j) != null) {


                    Room room = new Room();
                    Floor floor = new Floor();
                    Wall wall = new Wall();
                    Roof roof = new Roof();


                    for (int i = numCellWithId; i < sheet.getRow(numRowWithId).getLastCellNum(); i++) {


                        if (sheet.getRow(j).getCell(i) != null) {

                            if (sheet.getRow(j).getCell(0) != null && sheet.getRow(j).getCell(0).getCellType() == CellType.NUMERIC) {

                                room.setId((int) sheet.getRow(j).getCell(0).getNumericCellValue());
                            }

                            switch (sheet.getRow(6).getCell(i).getRawValue()) {
                                case "1" -> room.setName(sheet.getRow(j).getCell(i).getStringCellValue());
                                case "2" -> room.setPlace(sheet.getRow(j).getCell(i).getStringCellValue());

                                case "6.1" -> floor.setSquare(sheet.getRow(j).getCell(i).getNumericCellValue());
                                case "6.2" -> floor.setDepth(sheet.getRow(j).getCell(i).getNumericCellValue());

                                case "6.3" -> wall.setSquare(sheet.getRow(j).getCell(i).getNumericCellValue());
                                case "6.4" -> wall.setDepth(sheet.getRow(j).getCell(i).getNumericCellValue());

                                case "6.5" -> roof.setSquare(sheet.getRow(j).getCell(i).getNumericCellValue());
                                case "6.6" -> roof.setDepth(sheet.getRow(j).getCell(i).getNumericCellValue());

                                case "7.1" -> room.setPower(sheet.getRow(j).getCell(i).getNumericCellValue());
                                case "7.2" -> room.setActivity(sheet.getRow(j).getCell(i).getNumericCellValue());
                            }

                            if (floor != null) {
                                room.setFloor(floor);
                            }
                            if (wall != null) {
                                room.setWall(wall);
                            }
                            if (roof != null) {
                                room.setRoof(roof);
                            }

                        }
                    }
                    if (room.getName() != null) {
                        Storage.getInstance().getRooms().add(room);
                    }
                }
            }
        }

        workbook.close();

    }

    public void impWorks(XSSFWorkbook workbook) throws IOException, InvalidFormatException {

        XSSFSheet sheet = workbook.getSheetAt(0);

        int numRowWithId = -1;  // Ряд, начиная с которого получаем интересующие нас значения
        int numCellWithId = -1; // Колонока, начиная с которой получаем интересующие на значения

        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            if (sheet.getRow(i) != null) {

                for (int j = 0; j < sheet.getRow(i).getLastCellNum(); j++) {

                    if (sheet.getRow(i).getCell(j) != null) {
                        if (sheet.getRow(i).getCell(j).getCellType() == CellType.STRING) {
                            if ("Код".equals(sheet.getRow(i).getCell(j).getStringCellValue())) {
                                numRowWithId = i;
                                numCellWithId = j;
                                break;
                            }
                        }
                    }

                }
            }
            if (numRowWithId != -1) {
                break;
            }
        }

        if (numRowWithId != -1) {


            // numRowWithId + 1     (+1 потому-что numRowWithId это ряд с id для совпадения полей, а с +1 получаем ряды со значениями)
            for (int j = numRowWithId + 1; j <= sheet.getLastRowNum(); j++) {

                if (sheet.getRow(j) != null) {


                    Work work = new Work();


                    for (int i = numCellWithId; i < sheet.getRow(numRowWithId).getLastCellNum(); i++) {

                        if (sheet.getRow(j).getCell(i) != null) {


                            switch (sheet.getRow(0).getCell(i).getStringCellValue()) {
                                case "Код" -> work.setIdRoom((int) sheet.getRow(j).getCell(i).getNumericCellValue());
                                case "Часть" -> work.setNamePart(sheet.getRow(j).getCell(i).getStringCellValue());
                                case "Имя работы" -> work.setNameWork(sheet.getRow(j).getCell(i).getStringCellValue());
                                case "Описание" -> work.setDeskWork(sheet.getRow(j).getCell(i).getStringCellValue());
                                case "тип работы" -> work.setType(sheet.getRow(j).getCell(i).getStringCellValue());
                                case "Цена" -> work.setPrice(sheet.getRow(j).getCell(i).getNumericCellValue());
                                case "Очередность" ->
                                        work.setPriority((int) sheet.getRow(j).getCell(i).getNumericCellValue());
                                case "Норма Времени" ->
                                        work.setTimeTick((int) sheet.getRow(j).getCell(i).getNumericCellValue());
                                case "Количество работников" ->
                                        work.setNumberWorkers((int) sheet.getRow(j).getCell(i).getNumericCellValue());
                            }

                        }
                    }
                    if (work.getDeskWork() != null) {

                        Storage.getInstance().getWorks().add(work);
                    }
                }
            }
        }

        workbook.close();
    }

}
