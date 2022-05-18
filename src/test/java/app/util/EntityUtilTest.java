package app.util;

import app.dto.EntityDTO;
import app.dto.EntityFieldDTO;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EntityUtilTest
{
    @Test
    void generateContent()
    {
        String expected="import javax.persistence.*;\n"+
                "\n"+
                "@Entity\n"+
                "public class City\n"+
                "{\n"+
                "\t@Id\n"+
                "\tprivate Long id;\n"+
                "\t@ManyToOne\n"+
                "\tprivate Country country;\n"+
                "\tprivate String name;\n"+
                "}";
        List<EntityFieldDTO> entityFields=new LinkedList<>();
        entityFields.add(new EntityFieldDTO("id","Long",true,false));
        entityFields.add(new EntityFieldDTO("country","Country",false,true));
        entityFields.add(new EntityFieldDTO("name","String",false,false));
        EntityDTO entityDTO=new EntityDTO("City",entityFields);
        String content=EntityUtil.generateContent(entityDTO);
        assertEquals(expected,content);
    }
}