package com.example.firstcomposeapp.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.firstcomposeapp.data.MAX_NO_OF_WORDS
import com.example.firstcomposeapp.data.SCORE_INCREASE
import com.example.firstcomposeapp.data.allWords
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {

    // Game UI state
    private val _uiState = MutableStateFlow(GameUiState())

    // Backing property to avoid state updates from other classes
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    private lateinit var currentWord: String
    private var usedWord: MutableSet<String> = mutableSetOf()

    var userGuess by mutableStateOf("")
        private set

    init {
        resetGame()
    }

    private fun pickRandomWordAndShuffle(): String {
        currentWord = allWords.random()
        Log.d("GameViewModel", "Random word: $currentWord")
        if (usedWord.contains(currentWord)) {
            return pickRandomWordAndShuffle()
        }
        usedWord.add(currentWord)
        return shuffleCurrentWord(currentWord)
    }

    private fun shuffleCurrentWord(word: String): String {
        val tempWord = word.toCharArray()
        // Scramble the word
        tempWord.shuffle()
        while (String(tempWord) == word) {
            tempWord.shuffle()
        }
        return String(tempWord)
    }

    fun resetGame() {
        usedWord.clear()
        _uiState.value = GameUiState(currentScrambleWord = pickRandomWordAndShuffle())
    }

    fun updateUserGuess(guessWord: String) {
        userGuess = guessWord
    }

    fun checkUserGuess() {
        if (userGuess.equals(currentWord, ignoreCase = true)) {
            // User's guess correct, increase the score
            // and call updateGameState() to prepare the game for next round
            val updatedScore = _uiState.value.score.plus(SCORE_INCREASE)
            updatedGameState(updatedScore)
        } else {
            // User's guess is wrong, show error
            _uiState.update { currentState -> currentState.copy(isGuessWordWrong = true) }
        }
        // Reset user guess
        updateUserGuess("")
    }

    private fun updatedGameState(updatedScore: Int) {
        if(usedWord.size == MAX_NO_OF_WORDS){
            // Last round in the game
            _uiState.update {
                currentState -> currentState.copy(
                    score = updatedScore,
                    isGuessWordWrong = false,
                    isGameOver = true
                )
            }
        }
        else{
            // Normal round in the game
            _uiState.update { currentState ->
                currentState.copy(
                    score = updatedScore,
                    currentWordCount = currentState.currentWordCount.inc(),
                    isGuessWordWrong = false,
                    currentScrambleWord = pickRandomWordAndShuffle()
                )
            }
        }

    }

    fun skipWord() {
        updatedGameState(_uiState.value.score)
        // Reset user guess
        updateUserGuess("")
    }


}