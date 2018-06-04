package com.elegion.courserafirstcourseprogrammingtest;


import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Observable;

public class CharacterCreator extends Observable implements Serializable {

    private String mName;
    private Specialization mSpecialization;
    private Race mRace;
    private int mAvailablePoints;
    private Map<String, Integer> mAttributesMap = new HashMap<>();
    private Map<String, Boolean> mPerksMap = new HashMap<>();
    public CharacterCreator() {
        mRace = Race.HUMAN;
        mSpecialization = Specialization.WARRIOR;
        mAvailablePoints = 5;
        mAttributesMap.put(Attribute.STRENGTH.name(), 5);
        mAttributesMap.put(Attribute.AGILITY.name(), 5);
        mAttributesMap.put(Attribute.INTELLECT.name(), 5);
        mAttributesMap.put(Attribute.STAMINA.name(), 5);
        mAttributesMap.put(Attribute.LUCK.name(), 5);
    }

    /*
    добавлено 30.05.2018 метод который из строки больших букв
    делает строку которая должна начинаться с заглавной буквы, остальные строчные
     */
    public String capitalizeFirstLetter(String original) {
        if (original == null || original.length() == 0) {
            return original;
        }
        return original.substring(0, 1).toUpperCase() + original.substring(1).toLowerCase();
    }

    public String[] getSpecializations() {
        // TODO: 11.12.2017
        /*
        *   этот метод должен возвращать массив строк, созданных на основе enum Specialization
        *   Строки должны начинаться с заглавной буквы, остальные строчные
        * */        /*
        *   этот метод должен возвращать массив строк, созданных на основе enum Specialization
        *    Строки должны начинаться с заглавной буквы, остальные строчные
        * */

        java.util.LinkedList<String> listOfSpecialisations = new LinkedList<String>();
        for (Specialization spec : Specialization.values()) {
            listOfSpecialisations.add(capitalizeFirstLetter(spec.name()));
        }

        return listOfSpecialisations.toArray(new String[listOfSpecialisations.size()]);

    }

    public String[] getRaces() {
        // TODO: 11.12.2017
        /*
        *   этот метод должен возвращать массив строк, созданных на основе enum Races
        *    Строка должна быть формата - первая буква заглавная, остальные строчные
        *   One, Two, Three
        * */
        java.util.LinkedList<String> listOfRaces = new LinkedList<String>();
        for (Race rc : Race.values()) {
            listOfRaces.add(capitalizeFirstLetter(rc.name()));
        }

        return listOfRaces.toArray(new String[listOfRaces.size()]);
    }

    public String[] getAttributes() {
        // TODO: 11.12.2017
        /*
        *   этот метод должен возвращать массив строк, созданных на основе enum Attribute
        *    Строка должна быть формата - первая буква заглавная, остальные строчные
        *   One, Two, Three
        * */
        java.util.LinkedList<String> listOfAttributes = new LinkedList<String>();
        for (Attribute atb : Attribute.values()) {
            listOfAttributes.add(capitalizeFirstLetter(atb.name()));
        }
        return listOfAttributes.toArray(new String[listOfAttributes.size()]);
    }

    public String[] getPerks() {
        // TODO: 11.12.2017
        /*
        *   этот метод должен возвращать массив строк, созданных на основе enum Perk
        *   Строка должна быть формата - первая буква заглавная, остальные строчные
        *   One, Two, Three
        *
        * */
        java.util.LinkedList<String> listOfPerks = new LinkedList<String>();
        for (Perk prk : Perk.values()) {
            listOfPerks.add(capitalizeFirstLetter(prk.name()));
        }
        return listOfPerks.toArray(new String[listOfPerks.size()]);
    }

    public void updateAttributeValue(int position, int updateTo) {
        // TODO: 11.12.2017
        /*
        *  этот метод увеличивает/уменьшает соответствующее значение атрибута
        *  рекомендуется реализовывать его в последнюю очередь
        *
        * 1. на вход подается позиция изменяемого атрибута
        * и на сколько нужно этот атрибут увеличить/уменьшить
        * 2. по позиции атрибута выявляется название атрибута из enum Attribute
        * 3. по названию атрибута получается значение атрибута из mAttributesMap
        * 4. если атрибут собирается увеличиться и у персонажа достаточно очков
        * для увеличения атрибута (mAvailablePoints)
        *    или
        *    если атрибут собирается уменьшиться и уменьшенный атрибут будет больше 0,
        *    то атрибут изменяется, количество доступных очков меняется в противоположную сторону.
        *
        * 5. убедитесь в том, что измененное значение атрибута записано в mAttributesMap
        * 6. убедитесь в том, что измененное значение количества очков записано в mAvailablePoints;
        * 7. после изменения нужно вызвать методы setChanged(); notifyObservers();
        *    для того, чтобы изменения отразились на верстке
        *
        * пример работы алгоритма.
        * на вход подается (0, -1)
        * из значения 0 выясняем, что меняться будет атрибут STRENGTH
        * получаем текущее значение этого атрибута из mAttributesMap
        * допустим, оно равно 3
        * по условию все алгоритма все проходит
        * сила уменьшается до 2, количество доступных очков увеличивается на +1
        *
        * если STRENGTH равно 1, то ничего не происходит,
        * так как мы не можем уменьшить атрибут ниже 1
        *
        * если на вход пришло (0, 1)
        *   если доступных очков больше 0,
        *       то STRENGTH увеличивается на 1, количество доступных очков уменьшается на 1
        *   если количество доступных очков равно 0
        *       то мы не можем увеличить атрибут, ничего не происходит        *
        * */

        String nameAttribute = Attribute.values()[position].name();
        Integer value = mAttributesMap.get(nameAttribute);
        if ((updateTo<0 && value ==1) || (updateTo>0 && mAvailablePoints==0)) {
            return;
        }
        if ((updateTo> 0 && updateTo<=mAvailablePoints) ||
                (updateTo<0 && value-updateTo>0)) {

            value += updateTo;
            mAvailablePoints -= updateTo;

            mAttributesMap.put(nameAttribute, value);
            setChanged();
            notifyObservers();
        }
    }

    public void setName(String name) {
        mName = name;
    }

    public String getAvailablePoints() {
        return String.valueOf(mAvailablePoints);
    }

    public void setAvailablePoints(int availablePoints) {
        mAvailablePoints = availablePoints;
    }

    public Map<String, Integer> getAttributesMap() {
        return mAttributesMap;
    }

    public void checkPerk(String text, boolean isChecked) {
        mPerksMap.put(text, isChecked);
    }

    public Character create() {
        Character character = new Character();
        character.setName(mName);
        character.setRace(mRace);
        character.setSpecialization(mSpecialization);
        character.setAttributes(mAttributesMap);
        character.setPerks(mPerksMap);
        character.calculateParameters();
        return character;
    }

    public Specialization getSpecialization() {
        return mSpecialization;
    }

    public void setSpecialization(int position) {
        // TODO: 11.12.2017
       /*
        *  этот метод задает специализацию в переменную mSpecialization
        *  на вход подается число, и из enum Specialization выбирается соответствующий класс
        *  0 - Warrior
        *  1 - Archer
        *  2 - Mage
        *  если введенное число меньше 0, то в mSpecialization записывается самое первое
        *  (порядковый номер - 0) значение
        *  если введенное число больше длины enum, то в mSpecialization записывается
        *  самое последнее (длина - 1) значение
        *
        * */
        if (position<0) position = 0;
        else  if (position> Specialization.values().length-1)
            position = Specialization.values().length-1;
        mSpecialization =  Specialization.values()[position];
    }

    public Race getRace() {
        return mRace;
    }

    public void setRace(int position) {
        // TODO: 11.12.2017
        /*
        *  этот метод задает специализацию в переменную mRace
        *  на вход подается число, и из enum Race выбирается соответствующая раса
        *  0 - Human
        *  1 - Elf
        *  2 - Orc
        *  3 - Dwarf
        *  если введенное число меньше 0, то в mRace
        *  записывается самое первое (порядковый номер - 0) значение
        *  если введенное число больше длины enum, то в mRace записывается
        *  самое последнее (длина - 1) значение
        *
        * */
        if (position<0) position = 0;
        else  if (position>= Race.values().length)
            position = Race.values().length-1;
        mRace =  Race.values()[position];
    }

    public Map<String, Boolean> getPerksMap() {
        return mPerksMap;
    }

    public int getRacePosition() {
        return mRace.ordinal();
    }

    public int getSpecializationPosition() {
        return mSpecialization.ordinal();
    }

    public enum Specialization {
        WARRIOR, ARCHER, MAGE
    }

    public enum Race {
        HUMAN, ELF, ORC, DWARF
    }

    public enum Attribute {
        STRENGTH, AGILITY, INTELLECT, STAMINA, LUCK
    }

    public enum Perk {
        BERSERK, CALM, LIGHTWEIGHT, HEAVYARMORED, OBSERVANT, MEDITATIONS
    }
}
