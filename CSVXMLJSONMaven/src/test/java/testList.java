import org.example.Employee;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.example.Main.jsonToList;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.junit.Assert.assertEquals;
import static org.junit.matchers.JUnitMatchers.hasItems;

public class testList {
    @Test
    public void jsonToListTest () throws ParseException { //если дали правильно, то разберёт правильно
        String json = "[{\"id\":1,\"firstName\":\"John1\",\"lastName\":\"Smith1\",\"country\":\"USA\",\"age\":25},{\"id\":2,\"firstName\":\"Inav2\",\"lastName\":\"Petrov2\",\"country\":\"RU\",\"age\":23}]";
        List<Employee> employeeList = new ArrayList<Employee>();
        Assertions.assertEquals(2 , jsonToList(json).size());
        Assertions.assertEquals("Inav2" , jsonToList(json).get(1).getfirstName());
        Assertions.assertEquals(2 , jsonToList(json).get(1).getId());
    }
    @Test
    public void jsonToListTest2 () throws ParseException { //если прописали чепуху, то вернёт пустоту и не сломается
        String json = "qwert_y(){}";
        List<Employee> employeeList = new ArrayList<Employee>();
        Assertions.assertNull(jsonToList(json));
    }
    @Test
    public void jsonToListTest3 () throws ParseException { //если дали правильный json с синтаксической ошибкой
        String json = "[{\"id\":1,\"firstName\":\"John1\",\"lastName\":\"Smith1\",\"country\":\"USA\",\"age\":25},{\"id\":2,\"firstName\":\"Inav2\",\"lastName\":\"Petrov2\",\"country\":\"RU\",\"age\":23}]";
//        List<Employee> employeeList = new ArrayList<Employee>();
        Assertions.assertEquals(2 , jsonToList(json).size());
        Assertions.assertEquals(25 , jsonToList(json).get(0).getAge());
    }

    @Test //Задача "JUnit + Hamcrest"
    public void jsonToListTest4() throws ParseException {
        String json = "[{\"id\":1,\"firstName\":\"John1\",\"lastName\":\"Smith1\",\"country\":\"USA\",\"age\":25},{\"id\":2,\"firstName\":\"Inav2\",\"lastName\":\"Petrov2\",\"country\":\"RU\",\"age\":23}]";
//        List<Employee> employeeList = new ArrayList<Employee>();
        assertThat(jsonToList(json), notNullValue());
    }
    @Test //Задача "JUnit + Hamcrest"
    public void jsonToListTest5() throws ParseException { //сбой при передаче json должен возвразщать null
        String json = "[{\"id\":1,\"firstName\":\"John1\",\"lastName\":\"Smith1\",\"country\":\"lastName\":\"Petrov2\",\"country\":\"RU\",\"age\":23}]";
//        List<Employee> employeeList = new ArrayList<Employee>();
        assertThat(jsonToList(json), nullValue());
    }
    @Test //Задача "JUnit + Hamcrest"
    public void jsonToListTest6() throws ParseException { //сбой при передаче json должен возвразщать null
        String json = "[{\"id\":1,\"firstName\":\"John1\",\"lastName\":\"Smith1\",\"country\":\"USA\",\"age\":25},{\"id\":2,\"firstName\":\"Inav2\",\"lastName\":\"Petrov2\",\"country\":\"RU\",\"age\":23}]";
        assertThat(jsonToList(json).get(0).getId(), instanceOf(Long.class));
    }
}
