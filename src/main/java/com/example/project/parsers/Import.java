package com.example.project.parsers;

import java.io.File;
import java.io.IOException;

import com.example.project.Storage;
import com.example.project.elements.objects.Room;
import com.example.project.elements.objects.elements.Floor;
import com.example.project.elements.objects.elements.Roof;
import com.example.project.elements.objects.elements.Wall;
import com.example.project.elements.workings.Work;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Import {

    public void impObject(File file) throws IOException, InvalidFormatException {

        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);

        for (int j = 7; j <= sheet.getLastRowNum(); j++) {

            if (sheet.getRow(j) != null) {


                Room room = new Room();
                Floor floor = new Floor();
                Wall wall = new Wall();
                Roof roof = new Roof();


                for (int i = 0; i < sheet.getRow(7).getLastCellNum(); i++) {


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

        workbook.close();
    }

    public void impWorks(File file) throws IOException, InvalidFormatException {

        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);

        for (int j = 1; j <= sheet.getLastRowNum(); j++) {

            if (sheet.getRow(j) != null) {


                Work work = new Work();


                for (int i = 0; i < sheet.getRow(0).getLastCellNum(); i++) {

                    if (sheet.getRow(j).getCell(i) != null) {



                        switch (sheet.getRow(0).getCell(i).getStringCellValue()) {
                            case "Код" -> work.setIdRoom((int) sheet.getRow(j).getCell(i).getNumericCellValue());
                            case "Часть" -> work.setNamePart(sheet.getRow(j).getCell(i).getStringCellValue());
                            case "Имя работы" -> work.setNameWork(sheet.getRow(j).getCell(i).getStringCellValue());
                            case "Описание" -> work.setDeskWork(sheet.getRow(j).getCell(i).getStringCellValue());
                            case "тип работы" -> work.setType(sheet.getRow(j).getCell(i).getStringCellValue());
                            case "Цена" -> work.setPrice(sheet.getRow(j).getCell(i).getNumericCellValue());
                            case "Очередность" -> work.setPriority((int) sheet.getRow(j).getCell(i).getNumericCellValue());
                            case "Норма Времени" -> work.setTimeTick((int) sheet.getRow(j).getCell(i).getNumericCellValue());
                            case "Количество работников" -> work.setNumberWorkers((int) sheet.getRow(j).getCell(i).getNumericCellValue());
                        }

                    }
                }
                if (work.getDeskWork() != null) {

                    Storage.getInstance().getWorks().add(work);
                }
            }
        }

        workbook.close();
    }

}
