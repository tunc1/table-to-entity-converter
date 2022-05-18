package app.util;

import app.dto.EntityDTO;
import app.dto.EntityFieldDTO;

public class EntityUtil
{
    public static String generateContent(EntityDTO entity)
    {
        String content="import javax.persistence.*;\n"+
                "\n"+
                "@Entity\n"+
                "public class "+entity.getEntityName();
        content+="\n{";
        for(EntityFieldDTO field:entity.getFields())
        {
            if(field.isId())
                content+="\n\t@Id";
            else if(field.isFK())
                content+="\n\t@ManyToOne";
            content+="\n\tprivate "+field.getType()+" "+field.getName()+";";
        }
        content+="\n}";
        return content;
    }
}