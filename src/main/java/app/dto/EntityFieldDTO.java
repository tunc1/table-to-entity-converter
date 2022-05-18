package app.dto;

public class EntityFieldDTO
{
    private String name,type;
    private boolean isId,isFK;
    public EntityFieldDTO(String name,String type,boolean isId,boolean isFK)
    {
        this.name=name;
        this.type=type;
        this.isId=isId;
        this.isFK=isFK;
    }
    public String getName()
    {
        return name;
    }
    public String getType()
    {
        return type;
    }
    public boolean isId()
    {
        return isId;
    }
    public boolean isFK()
    {
        return isFK;
    }
}