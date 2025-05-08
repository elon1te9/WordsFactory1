package com.example.wordsfactory

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wordsfactory.databinding.ActivityDictionaryBinding

class DictionaryActivity : AppCompatActivity() {

    lateinit var bindingClass: ActivityDictionaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityDictionaryBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        bindingClass.recyclerViewMeanings.layoutManager = LinearLayoutManager(this)
        showNoWordState()

        bindingClass.edSearch.setOnEditorActionListener { textView, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE) {
                // Действие выполняется при нажатии на клавишу "Поиск" или "Готово" на клавиатуре

                // Скрываем программную клавиатуру
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(textView.windowToken, 0)

                // Получаем текст из строки поиска
                val query =
                    textView.text.toString().trim() // .trim() удаляет пробелы в начале и конце

                if (query.isEmpty())
                {
                    // Если строка поиска пустая, показываем состояние "Нет слова"
                    showNoWordState()
                }
                else
                {
                    // Если что-то введено (имитация поиска)
                    // Здесь в будущем будет вызов API
                    showWordFoundState(
                        "Simulated Word",
                        "Simulated Part of Speech",
                        getDummyMeanings()
                    ) // Показываем состояние "Слово найдено" с фиктивными данными
                }
                true // Возвращаем true, чтобы указать, что событие обработано
            }
            else
            {
                false // Возвращаем false, чтобы система обработала событие по умолчанию
            }
        }

    }

    private fun showNoWordState() {
        bindingClass.imNoWord.visibility = View.VISIBLE
        bindingClass.tvTitleNoWord.visibility = View.VISIBLE
        bindingClass.tvSubTitleNoWord.visibility = View.VISIBLE

        bindingClass.tvWord.visibility = View.GONE
        bindingClass.tvPartOfSpeach.visibility = View.GONE
        bindingClass.tvMeanings.visibility = View.GONE
        bindingClass.recyclerViewMeanings.visibility = View.GONE
        bindingClass.recyclerViewMeanings.visibility = View.GONE
    }

    private fun showWordFoundState(word: String, partOfSpeech: String, meanings: List<Meaning>) {
        bindingClass.imNoWord.visibility = View.GONE
        bindingClass.tvTitleNoWord.visibility = View.GONE
        bindingClass.tvSubTitleNoWord.visibility = View.GONE

        bindingClass.tvWord.visibility = View.VISIBLE
        bindingClass.tvPartOfSpeach.visibility = View.VISIBLE
        bindingClass.tvMeanings.visibility = View.VISIBLE
        bindingClass.recyclerViewMeanings.visibility = View.VISIBLE
        bindingClass.recyclerViewMeanings.visibility = View.VISIBLE

        // Обновляем TextView с данными слова
        bindingClass.tvWord.text = word // Здесь в будущем будет настоящее слово из API
        bindingClass.tvPartOfSpeach.text = partOfSpeech // Здесь будет часть речи из API

        // Настраиваем адаптер RecyclerView с данными
        bindingClass.recyclerViewMeanings.adapter = MeaningsAdapter(meanings) // Создадим MeaningsAdapter позже
    }
    data class Meaning(
        val definition: String,
        val example: String? // Может быть null
    )

    // Функция для получения статичных данных значений
    private fun getDummyMeanings(): List<Meaning> {
        return listOf(
            Meaning("The practice or skill of preparing food by combining, mixing, and heating ingredients.", "Example: he developed an interest in cooking."),
            Meaning("A meal that has been cooked.", "Example: she had a large cooking to do."),
            Meaning("The process of applying heat to food.", null) // Пример значения без примера
        )
    }




}