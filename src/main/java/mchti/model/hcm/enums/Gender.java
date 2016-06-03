package mchti.model.hcm.enums;

public enum Gender {

    MALE("Male"), 
    FEMALE("Female"), 
    OTHER("Other");
    
    String name;

    private Gender(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
