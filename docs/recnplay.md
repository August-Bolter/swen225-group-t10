<h2>Record and Play</h2>
<h4>Implementation</h4>
<p>The implementation of the Record and Play (R&P) feature is spread out 
over several classes. </p>
<p>The GUI for R&P is mainly in the InfoPanel class. 
The record and replay buttons are in the InfoPanel. When the replay button
is clicked on the record button changes to a play/stop button and the replay
button changes to a change speed button. Additionally two new buttons, a next step button 
and an exit button are added to the GUI. When the change speed button is clicked
the main frame creates a pop-up window showing the possible speeds to choose from.
 </p>

<p>The code for R&P is in five classes, Record, Replay, loadJSON, saveJSON and Main. 
Record is used for recording the game, it uses SaveGame in saveJSON to 
save the initial board state of the board. In Main whenever doMove() is called 
i.e. the player moves, the move is sent to the record file by using the SaveMove() method
I created in saveJSON. These moves are loaded later for the replay by using the loadMove()
method in loadJSON. Main also controls the replay in the timer() method. It uses a variable
to control the replay speed, and executes the next move when the next step button is clicked.
It also manipulates the frameRate based on whether the replay is paused or playing. </p>

<p> So overall, recording is done in the Record, Main and saveJSON class, loading the record 
is done in the Replay and loadJSON class and controlling the replay is done in Main</p>

<h4>... Design Pattern</h4>

<p>Lmao</p>

<p>I'm fucked</p>

<h4>Using the Record and Play feature</h4>
<p>There are many features of R&P, here is a guide to use these features</p>