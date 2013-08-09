reagroup_robot
==============

Robot Assessment Task for REA group in Java

Overview
-----------

Assumptions
-----------

Justifications
--------------

Things to improve on
---------------------
One thing I am definitely not impressed by is the way REPORT command is done.
Currently the REPORT command uses System.out.println within the command class itself
rendering it VERY difficult to unit test. I think this is unacceptable and would have preferred
for the command to return Strings for the robot to print.

One simple way to do this is to break up the REPORT command to have another interface to allow
it to return Strings instead of a CommandLog.

Other than that, perhaps using a Finite State Machine could also assist with optimising the
performance of the robot. The FSM would allow a O(1) lookup of any state and provide the next
state very quickly. For a 5x5 board, this is very simple to do, but also felt the search space
and the solution did not fit the problem scale to warrant such memory use.

How to build
------------
I built this robot using the awesomeness of Maven.
So if you have maven installed on your computer, all you have to do is the following commands
at the root project folder (where reagrouprobot folder exists)

`mvn clean compile assembly:single`

This will compile an executable JAR with all dependencies pre-packaged
* `reagrouprobot\target\reagrouprobot.jar`

with the main function in com.winnergenic.reagroup.robot.ROBOT already referenced to in the MANIFEST.MF file

How to run
----------
From the target folder where the `reagrouprobot.jar` file is, you can 
run the robot by

`java -jar reagrouprobot.jar <input-flags>`

Input Flags
-----------
I have implemented several unique flags for the Robot allowing it to be more robust for expansion
in the future. If no flags are inserted, then the robot will merely accept user input.

* -f <filename>	uses a file (relative to execution point) as the input
* -s	triggers sensitive robot that will blow up on ANY errorneous input
* -b <int M>	changes the board size to be sized MxM

Example usage would be

* `java -jar reagrouprobot.jar -f ./sampleinput1.txt`
* `java -jar reagrouprobot.jar -s -f ./sampleinput1.txt`
* `java -jar reagrouprobot.jar -s -f ./sampleinput1.txt -b 100`

The last command will create a board of sized 100x100 by the way!

Attributions
-------------
* Apache Maven - http://maven.apache.org/license.html
* JUnit 4.10 - http://junit.org/
* log4j 1.2.14 - http://logging.apache.org/log4j/1.2/
* commons-io 2.4 - http://commons.apache.org/proper/commons-io/
* commons-lang 2.4 - http://commons.apache.org/proper/commons-lang/

Thanks
-------
Finally I'd like to say thanks for the opportunity to participate in this assessment task.
I had quite a lot of fun putting it together and continually find new ways to improve the robot.
Whether it be through fun easter eggs, by letting the robot make complaints (when asked to move off
the table), or possibly causing the robot to self-destruct.
Ultimately, I dreamed of wanting to name this robot 'terminator 2000' and allowing it to resurrect itself 
with catchy phrases like 'I'll be back', 'its NOT a tumor!' or 'get to da choppa!!!'

I had a lot fun and I sincerely thank the REA group for the opportunity and hope to hear
your feedback soon.

Alwin Chin

alwin.chin@gmail.com