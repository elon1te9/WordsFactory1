package com.example.wordsfactory

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsfactory.databinding.ActivityDictionaryBinding

class DictionaryActivity : AppCompatActivity() {

    lateinit var bindingClass: ActivityDictionaryBinding

    private lateinit var editTextSearch: EditText
    private lateinit var imageViewNoWord: ImageView
    private lateinit var textViewNoWordTitle: TextView
    private lateinit var textViewNoWordDescription: TextView

    private lateinit var textViewWordFound: TextView
    private lateinit var textViewPartOfSpeechFound: TextView
    private lateinit var textViewMeaningsTitle: TextView
    private lateinit var recyclerViewMeanings: RecyclerView
    private lateinit var buttonAddToDictionary: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityDictionaryBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        editTextSearch = bindingClass.edSearch
        imageViewNoWord = bindingClass.imNoWord
        textViewNoWordTitle = bindingClass.tvTitleNoWord
        textViewNoWordDescription = bindingClass.tvSubTitleNoWord

        textViewWordFound = bindingClass.tvWord
        textViewPartOfSpeechFound = bindingClass.tvPartOfSpeach
        textViewMeaningsTitle = bindingClass.tvPartOfSpeach
        recyclerViewMeanings = bindingClass.recyclerViewMeanings
        buttonAddToDictionary = bindingClass.btAddToDictionary

        recyclerViewMeanings.layoutManager = LinearLayoutManager(this)
        showNoWordState()

        editTextSearch.setOnEditorActionListener { textView, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE) {

                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(textView.windowToken, 0)

                val query = textView.text.toString().trim()

                if (query.isEmpty()) {
                    showNoWordState()
                } else {

                    showWordFoundState(
                        "Simulated Word",
                        "Simulated Part of Speech",
                        getDummyMeanings()
                    )
                }

                true
            } else {
                false
            }
        }

        buttonAddToDictionary.setOnClickListener {

            val wordToAdd = textViewWordFound.text.toString()
            Toast.makeText(this, "$wordToAdd добавлен в словарь!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showNoWordState() {
        imageViewNoWord.visibility = View.VISIBLE
        textViewNoWordTitle.visibility = View.VISIBLE
        textViewNoWordDescription.visibility = View.VISIBLE

        textViewWordFound.visibility = View.GONE
        textViewPartOfSpeechFound.visibility = View.GONE
        textViewMeaningsTitle.visibility = View.GONE
        recyclerViewMeanings.visibility = View.GONE
        buttonAddToDictionary.visibility = View.GONE
    }

    private fun showWordFoundState(word: String, partOfSpeech: String, meanings: List<Meaning>) {
        imageViewNoWord.visibility = View.GONE
        textViewNoWordTitle.visibility = View.GONE
        textViewNoWordDescription.visibility = View.GONE

        textViewWordFound.visibility = View.VISIBLE
        textViewPartOfSpeechFound.visibility = View.VISIBLE
        textViewMeaningsTitle.visibility = View.VISIBLE
        recyclerViewMeanings.visibility = View.VISIBLE
        buttonAddToDictionary.visibility = View.VISIBLE

        textViewWordFound.text = word
        textViewPartOfSpeechFound.text = partOfSpeech

        recyclerViewMeanings.adapter = MeaningsAdapter(meanings)
    }

    data class Meaning(
        val definition: String,
        val example: String? // Может быть null
    )

    // Функция для получения статичных данных значений
    private fun getDummyMeanings(): List<Meaning> {
        return listOf(
            Meaning(
                "The practice or skill of preparing food by combining, mixing, and heating ingredients.",
                "Example: he developed an interest in cooking."
            ),
            Meaning("A meal that has been cooked.", "Example: she had a large cooking to do."),
            Meaning("The process of applying heat to food.", null) // Пример значения без примера
        )
    }

    class MeaningsAdapter(private val meanings: List<Meaning>) : RecyclerView.Adapter<MeaningsAdapter.MeaningViewHolder>() {

        // ViewHolder (создадим его реализацию далее)
        class MeaningViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val textViewMeaning: TextView = itemView.findViewById(R.id.textViewMeaning)
            val textViewExample: TextView = itemView.findViewById(R.id.textViewExample)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeaningViewHolder {
            // Тут создаем View для одного элемента списка из item_meaning.xml
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_meaning, parent, false)
            return MeaningViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: MeaningViewHolder, position: Int) {
            // Тут привязываем данные из списка к элементам View в ViewHolder
            val meaning = meanings[position]
            holder.textViewMeaning.text = meaning.definition
            if (meaning.example != null) {
                holder.textViewExample.text = meaning.example
                holder.textViewExample.visibility = View.VISIBLE
            } else {
                holder.textViewExample.visibility = View.GONE // Скрываем пример, если его нет
            }
        }

        override fun getItemCount() = meanings.size // Возвращаем количество элементов в списке данных
    }
}