package com.example.firstcomposeapp.ui.test

import com.example.firstcomposeapp.data.MAX_NO_OF_WORDS
import com.example.firstcomposeapp.data.SCORE_INCREASE
import com.example.firstcomposeapp.data.getUnscrambledWord
import com.example.firstcomposeapp.ui.GameViewModel
import org.junit.Assert.*
import org.junit.Test

class GamViewModelTest {
    private val viewModel = GameViewModel()

    //Success path
    @Test
    fun gameViewModel_CorrectWordGuessed_ScoreUpdatedAndErrorFlagUnset(){
        var currentGameUiState = viewModel.uiState.value
        val correctPlayerWord = getUnscrambledWord(currentGameUiState.currentScrambleWord)

        viewModel.updateUserGuess(correctPlayerWord)
        viewModel.checkUserGuess()
        currentGameUiState = viewModel.uiState.value

        assertFalse(currentGameUiState.isGuessWordWrong)
        assertEquals(SCORE_AFTER_FIRST_CORRECT_ANSWER,currentGameUiState.score)

    }
    //Error path
    @Test
    fun gameViewModel_IncorrectGuess_ErrorFlagSet(){
        val incorrectPlayerWord = "and"
        viewModel.updateUserGuess(incorrectPlayerWord)
        viewModel.checkUserGuess()

        val currentGameUiState = viewModel.uiState.value
        assertEquals(0,currentGameUiState.score)
        assertTrue(currentGameUiState.isGuessWordWrong)
    }

    //Boundary case
    @Test
    fun gameViewModel_Initialization_FirstWordLoad(){
        val gameUiState = viewModel.uiState.value
        val unScrambleWord = getUnscrambledWord(gameUiState.currentScrambleWord)

        assertNotEquals(unScrambleWord,gameUiState.currentScrambleWord)
        assertTrue(gameUiState.currentWordCount == 1)
        assertTrue(gameUiState.score == 0)
        assertFalse(gameUiState.isGuessWordWrong)
        assertFalse(gameUiState.isGameOver)
    }
    companion object {
        private const val SCORE_AFTER_FIRST_CORRECT_ANSWER = SCORE_INCREASE

    }

    //
    @Test
    fun gameViewModel_AllWordGuessed_UiStateUpdatedCorrectly(){
        var expectedScore = 0
        var currentGameUiState = viewModel.uiState.value

        var correctPlayerWord = getUnscrambledWord(currentGameUiState.currentScrambleWord)
        repeat(MAX_NO_OF_WORDS){
            expectedScore += SCORE_INCREASE
            viewModel.updateUserGuess(correctPlayerWord)
            viewModel.checkUserGuess()
            currentGameUiState = viewModel.uiState.value
            correctPlayerWord = getUnscrambledWord(currentGameUiState.currentScrambleWord)
            assertEquals(expectedScore, currentGameUiState.score)
        }
        assertEquals(MAX_NO_OF_WORDS, currentGameUiState.currentWordCount)
        assertTrue(currentGameUiState.isGameOver)
    }

    @Test
    fun gameViewModel_WordSkipped_ScoreUnChangedAndWordCountIncreased(){
        var currentUiState = viewModel.uiState.value
        val correctPlayerWord = getUnscrambledWord(currentUiState.currentScrambleWord)
        viewModel.updateUserGuess(correctPlayerWord)
        viewModel.checkUserGuess()

        currentUiState = viewModel.uiState.value
        val lastWordCount = currentUiState.currentWordCount
        viewModel.skipWord()

        currentUiState = viewModel.uiState.value
        assertEquals(SCORE_AFTER_FIRST_CORRECT_ANSWER,currentUiState.score)
        assertEquals(lastWordCount + 1, currentUiState.currentWordCount)
    }
}