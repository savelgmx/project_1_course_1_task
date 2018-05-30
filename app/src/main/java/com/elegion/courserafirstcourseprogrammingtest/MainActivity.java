package com.elegion.courserafirstcourseprogrammingtest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
/*
Приложение в задании для первого курса
представляет собой простой механизм создания персонажа
для какой-нибудь фэнтезийной RPG.

На первом экране у нас элементы для задания
* Имени персонажа через EditText
* Расы (Человек, Эльф, Орк, Гном) через Spinner
* Класса (Воин, Лучник, Маг) через RadioButton'ы
Дальше идет выбор аттрибутов (Сила, Ловкость, Интеллект, Выносливость, Удача),
которые в зависимости от расы и выбранного класса влияют на характеристики персонажа.
И заканчивается экран списком CheckBox’ов с особыми навыками,
немного увеличивающими или уменьшающими характеристики героя.

Верстку менять не нужно.
В классе CharacterCreator нужно дописать логику методов.
Описание приложено к каждому методу.
Для проверки правильности реализации, в классе CreateCharacterFragment нужно
раскомментировать соответствующие участки кода. Они указаны через todo.
Далее можно нажать на пункт меню Create и посмотреть характеристики героя.
 */


public class MainActivity extends AppCompatActivity implements CreateCharacterFragment.Callback {

    public static final String CREATOR = "Creator";
    private CharacterCreator mCreator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            mCreator = new CharacterCreator();
            setFragment(CreateCharacterFragment.newInstance(), false);
        } else {
            mCreator = (CharacterCreator) savedInstanceState.getSerializable(CREATOR);
        }

    }

    private void setFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment, fragment.getClass().getSimpleName());
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onCreateCharacterStarted() {
        CreateCharacterFragment fragment = (CreateCharacterFragment) getSupportFragmentManager().findFragmentById(R.id.container);
        fragment.configureCreator(mCreator);
    }

    @Override
    public void onCreateCharacterCompleted(Character character) {
        setFragment(CharacterFragment.newInstance(character), true);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(CREATOR, mCreator);
    }
}
