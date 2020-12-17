package com.codingblocks.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , View.OnClickListener{
    var player =1
    var turn_count=0
    var board_status=Array(size = 3){ IntArray (size = 3)}
    lateinit var board:Array<Array<Button>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        board=arrayOf(
            arrayOf(button,button1,button2),
            arrayOf(button3,button4,button5),
            arrayOf(button6,button7,button8)
        )
        for(i:Array<Button> in board){
            for(button:Button in i){
                button.setOnClickListener(this)
            }
        }

        initializeBoard()
        button10.setOnClickListener{
            initializeBoard()
            textView2.text="Player 1 turn"
            turn_count=0
            player=1
        }
    }

    private fun initializeBoard() {
        for(i : Int in 0..2){
            for(j: Int in 0..2){
                board_status[i][j]=-1
                board[i][j].isEnabled=true
                board[i][j].text=""
            }
        }
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.button ->{
                updateValue(row=0, col=0, p=player)
            }
            R.id.button1 ->{
                updateValue(row=0, col=1, p=player)
            }
            R.id.button2 ->{
                updateValue(row=0, col=2, p=player)

            }
            R.id.button3 ->{
                updateValue(row=1, col=0, p=player)

            }
            R.id.button4 ->{
                updateValue(row=1, col=1, p=player)

            }
            R.id.button5 ->{
                updateValue(row=1, col=2, p=player)

            }
            R.id.button6 ->{
                updateValue(row=2, col=0, p=player)

            }
            R.id.button7 ->{
                updateValue(row=2, col=1, p=player)

            }
            R.id.button8 ->{
                updateValue(row=2, col=2, p=player)

            }
        }
        if (player==1) {
            player=2
        }else{
            player=1
        }
        turn_count++
        if(player==1){
            updateDisplay("Player 1 Turn")
        }else{
            updateDisplay("Player 2 Turn")
        }
        if(turn_count==9) updateDisplay("Game Draw")
        checkWinner()
    }

    private fun checkWinner() {
        // horizontal rows
        for(i:Int in 0..2){
            if(board_status[i][0]!=-1){
                if(board_status[i][0]==board_status[i][1] && board_status[i][1]==board_status[i][2]){
                    if(board_status[i][0]==1){
                        updateDisplay("Player 1 wins" )
                        break
                    }else if(board_status[i][0]==2){
                        updateDisplay("Player 2 wins")
                        break
                    }
                }
            }
        }
        //columns
        for(i:Int in 0..2){
            if(board_status[0][i]!=-1){
                if(board_status[0][i]==board_status[1][i] && board_status[1][i]==board_status[2][i]){
                    if(board_status[0][i]==1){
                        updateDisplay("Player 1 wins" )
                        break
                    }else if(board_status[0][i]==2){
                        updateDisplay("Player 2 wins")
                        break
                    }
                }
            }
        }
        //first diagnols
        if(board_status[0][0]!=-1){
            if(board_status[0][0]==board_status[1][1] && board_status[1][1]==board_status[2][2]){
                if(board_status[0][0]==1){
                    updateDisplay("Player 1 wins" )
                }else if(board_status[0][0]==2){
                    updateDisplay("Player 2 wins")
                }
            }
        }
        //second diagnol
        if(board_status[0][0]!=-1){
            if(board_status[0][2]==board_status[1][1] && board_status[1][1]==board_status[2][0]){
                if(board_status[2][0]==1){
                    updateDisplay("Player 1 wins" )
                }else if(board_status[2][0]==2){
                    updateDisplay("Player 2 wins")
                }
            }
        }

    }

    private fun updateDisplay(txt: String) {
        textView2.text=txt
        if(txt.contains("wins")){
            disableButton()
        }
    }
    private fun disableButton(){
        for(i: Array<Button> in board){
            for(button : Button in i){
                button.isEnabled=false
            }
        }
    }

    private fun updateValue(row: Int, col: Int, p: Int) {
        val text: String = if (player==1) "X" else "O"
        val value: Int = if(player==1) 1 else 2
        board[row][col].apply {
            isEnabled=false
            setText(text)
        }
        board_status[row][col]=value

    }
}