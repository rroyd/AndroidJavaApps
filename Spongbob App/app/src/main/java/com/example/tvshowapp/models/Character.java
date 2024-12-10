package com.example.tvshowapp.models;

public class Character {
     String characterName;
     String characterDescription;

    Integer image;

     public Character(String characterName, String characterDescription,
                           Integer image) {
         this.characterName = characterName;
         this.characterDescription = characterDescription;
         this.image = image;
     }


    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getCharacterDescription() {
        return characterDescription;
    }

    public void setCharacterDescription(String characterDescription) {
        this.characterDescription = characterDescription;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
