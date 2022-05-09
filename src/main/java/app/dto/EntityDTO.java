package app.dto;

import java.util.List;

public class EntityDTO
{
    private String entityName;
    private List<EntityFieldDTO> fields;
    public EntityDTO(String entityName,List<EntityFieldDTO> fields)
    {
        this.entityName=entityName;
        this.fields=fields;
    }
    public String getEntityName()
    {
        return entityName;
    }
    public List<EntityFieldDTO> getFields()
    {
        return fields;
    }
}