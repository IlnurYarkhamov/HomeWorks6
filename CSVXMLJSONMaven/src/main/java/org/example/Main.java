package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws CsvValidationException, IOException, ParserConfigurationException, SAXException, ParseException {
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "data.csv";
        List<Employee> list = parseCSV(columnMapping, fileName);

        String json = listToJson(list);
        writeString("data.json", json);

        //Задача 2: XML - JSON парсер
        List<Employee> listXML = parseXML("data.xml");
        System.out.println("РЕШЕНИЕ ВТОРОЙ ЗАДАЧИ: " + listXML);


        String json1 = listToJson(listXML);

        writeString("res2.txt", json1);

        //Задача 3: JSON парсер (со звездочкой *)
        String jsonParser = readString("new_data.json");
        System.out.println("РЕШЕНИЕ ТРЕТЬЕЙ ЗАДАЧИ: " + jsonParser);
        List<Employee> list3 = jsonToList(json);

        for(Employee a : list3)
        System.out.println(a.toString());
    }


    public static List<Employee> jsonToList(String json) throws ParseException {
        JSONParser jParser = new JSONParser();
        GsonBuilder builder1 = new GsonBuilder();
        Gson gson = builder1.create();
        JSONArray jArr;
        try {
            jArr = (JSONArray) jParser.parse(json);
        } catch (ParseException e) {
            return null;
        }

        List<Employee> employeeList = new ArrayList<Employee>();

        for(int i=0; i<jArr.size(); i++) {
            JSONObject personEmp = (JSONObject) jArr.get(i);
            Employee e = new Employee();
            e = gson.fromJson(String.valueOf(personEmp), Employee.class);
//            System.out.println(e);
            employeeList.add(e);
        }
        return employeeList;
    }

    private static String readString(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            String json11 = "";
            while ((line = reader.readLine()) != null) {
               json11 = json11 + line;
            }
        return json11;
    }

    private static List<Employee> parseXML(String fileName) throws ParserConfigurationException, IOException, SAXException {

        List<Employee> listXML = new ArrayList<>();


        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        // Создается дерево DOM документа из файла
        Document document = documentBuilder.parse(fileName);
        Node root = document.getDocumentElement(); //находим начало XML
        NodeList persons = root.getChildNodes();
        for (int i = 0; i < persons.getLength(); i++) {
            Node person = persons.item(i);
            if (person.getNodeType() != Node.TEXT_NODE) {
                // Просматриваем все подэлементы корневого - т.е. человека
                NodeList personProps = person.getChildNodes();
                Employee e = new Employee();
                for (int j = 0; j < personProps.getLength(); j++) {
                    Node personProp = personProps.item(j);
                    // Если нода не текст, то это человек - заходим внутрь
                    if (personProp.getNodeType() != Node.TEXT_NODE) {
                        System.out.println(personProp.getNodeName() +
                                ":" + personProp.getChildNodes().item(0).getTextContent());
                        if (j == 1) e.setId(personProp.getChildNodes().item(0).getTextContent());
                        if (j == 3) e.setfirstName(personProp.getChildNodes().item(0).getTextContent());
                        if (j == 5) e.setlastName(personProp.getChildNodes().item(0).getTextContent());
                        if (j == 7) e.setCountry(personProp.getChildNodes().item(0).getTextContent());
                        if (j == 9) e.setAge(personProp.getChildNodes().item(0).getTextContent());
                    }
                }
                listXML.add(e);
                System.out.println("===========>>>>");
            }
        }
        return listXML;
    }

    private static void writeString(String fileName, String json) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String listToJson(List<Employee> list) {
        Type listType = new TypeToken<List<Employee>>() {}.getType(); //пытаемся получитҗ имя класса
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String json = gson.toJson(list, listType);
        System.out.println(json);
        return json;
    }


    private static List<Employee> parseCSV(String[] columnMapping, String fileName) throws IOException, CsvValidationException {
        CSVReader reader = new CSVReader(new FileReader(fileName));
        List<Employee> emps = new ArrayList<Employee>();
        // read line by line
        String[] record = null;

        while ((record = reader.readNext()) != null) {
            Employee emp = new Employee();
            emp.setId(record[0]);
            emp.setfirstName(record[1]);
            emp.setlastName(record[2]);
            emp.setCountry(record[3]);
            emp.setAge(record[4]);
            emps.add(emp);
        }
        System.out.println(emps);
        reader.close();
        return emps;
    }
}