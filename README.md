# Dog Door

## DogDoor file:<br>
The dogDoor class can be affected by other classes, such as the remote (which can be used to open the door) and the barkRecognizer.
<br><br>

*	Declaration of a public class DogDoor
*	4 instance variables 
        *	doorOpen - a boolean which will say what state the door is in
        *	dogPositions - an ArrayList of booleans which will provide the last known position of the dog(s)
        *	dogs - an ArrayList of type dog (defined below) which will include such things as the bark of that dog/dogs and the location of any dog/dogs
        *	doorLockDelay - a private variable which will create a custom delay time (in seconds) to close the door (to prevent unwanted visitors)
*	The first constructor is a ‘no-args’ constructor which declares any new door with no arguments made will be initialized as closed with no preset barks or positions and a default delay of 6 seconds.
        *	Inside the constructor is declared the creation of a new ArrayList of dogs and a new ArrayList of dogPositions (empty for the time being)
*	The next constructor is a constructor that takes the arguments doorOpen (a boolean to set the above instance variable (does your door start open or closed?)), and doorLockDelay (an int to set your desired delay time for your personal door.)
        *	Inside the constructor is declared the creation of a new ArrayList of dogs and a new ArrayList of dogPositions (empty for the time being)
*	A third constructor was included to allow a door to be initialized with all of the items included in the above constructors plus two added arguments of an ArrayList of both dogs (type Dog) and dogPostions (type boolean). This allows the user to initialize their personal dog door with all the instance variables declared right off
*	The class has a toString method which, when called will print out the status of the door and the current delay before locking
*	The timeClose function closes the door after the delay runs out it utilizes the ‘Thread’ function in Java, declaring it to be a timer thread which when it has run its course will execute the command to close the door
*	The next method openDoor simply sets the door to open (because it’s a boolean doorOpen = true means the door is open
*	The next method doorClose does the same thing but opposite as the doorOpen method
*	The clearDogs method delete all the dogs and positions from their respective lists 
*	The setDogs method takes an Arralists of dogs (Type Dog) and sets it to the dogs variable. It also initializes  its position to where the user declares its dog to be (isDogInside = true or false)
*	Next is the isDoorOpen method which just returns the status of the door (true or false)
*	After that is the isDogIn method which returns the status of the dogs being inside (again true or false)
*	Next is the addDog method which takes an argument of dog (type Dog) and adds that dog to the ArrayList dogs and sets its position to where the user declares its dog to be (isDogInside = true or false)(this method is there in case one gets another dog or if you would like your neighbors dog to be able to get in)
*	Right after follows the removeBark method which removes a dog from the ArrayList dogs (this is in case you would like to revoke a dog’s right to gain entry into your house or if your dog dies)
*	Next is the getDogs method which simply returns a list of the dogs currently on registry
*	The getDogPositions method does the same thing (it returns a list of the current whereabouts of the dogs on registry, as far as inside outside is concerned)
*	getDoorLockDelay returns the delay time currently set
*	Right after is the setDoorLockDelay method which allows one to set and reset the lock delay for the door as they see fit.


## DoorTimer
What follows is a class of doorTimer which implements Runnable
*	First is a run method which takes no args and initializes a timer and a TimerTask ( a command or sequence of commands to be carried out when the timer is done)
*	Next is a run method which overrides the abstract method in TimerTask and queries the door, if the door is open then keep open is set to false, next it asks for the dogs position and then switches it (this assumes the dog goes inside when it gets the door open) 
*	The method then prints out "Door is Open" and sets keepOpen to true again
*	If however keepOpen = false, the closeDoor method is called, the program prints "Door is Closed" and the timer is canceled
*	If somehow none of those conditions are met the timer is canceled, which ends the cycle.
*	The last line of this class sets the timer to run the task after the delay time (the * 1000 is to push it from milliseconds to seconds) and repeat it again after the delay time 

## Dog

The next class is the Dog class

*	Declaration of a class Dog with three instance variables, name(String), bark(Bark), and inHouse(boolean)
*	The constructor Dog takes three arguments (the same as the instance variables) and sets the instance variables to the values passed in
*	move() is a method which, when called changes the position of the dog ( if in then the dog goes out and vice versa)
        *	If there is something blocking the dog then the method prints “(dogs name) can’t move.”
*	The class includes a method toString which returns a statement describing the dog (ie. name, sound of bark and position)
*	isInHouse is a method which returns the position of the dog as a true/false statement
*	The getBark method is of a type Bark (another class below)  which returns the unique bark of the dog
*	setBark sets the bark that the getBark method returns (and adds it to the ArrayList of barks (to allow many different barks to be stored in memory
        *	There are of course two setBark methods, one takes a bark (Type Bark)  as an argument the other takes a bark (Type String) (this overloading of the method makes it so whatever the input, Bark or String, the noise will be added to the ArrayList)
*	The setInHouse method allows the user to declare where the dog is to begin (so when the dog moves this position can be changed)
*	The getBarkSound method returns the noise your dog makes
*	The getName method returns the dogs name

## BarkRecognizer

The next class is the BarkRecognizer class

*	The BarkRecognizer has one instance variable, dogDoor (type DogDoor)
*	The BarkRecognizer constructor takes a dogDoor (type DogDoor) as an argument and sets the instance variable 
        *	This links the BarkRecognizer to that door
*	Next is a no args constructor for the BarkRecognizer (this allows for a ‘blank slate’ BarkRecognizer to be initialized 
*	getYourBarks returns the ArrayList that stores all the Barks on record 
*	The setYourBark method takes an ArrayList of dogs (type Dog) as its argument and calls the dogDoor’s function of setDogs with dogs as its argument
*	The addBark method takes a dog (type Dog) as an argument and puts that dog in the registry (the ArrayList of recognized dogs) (through the DogDoor class)
*	removeBark does the same as the addBark but instead of adding the dog to the registry it removes it
*	The next method clearBarks wipes the whole bark registry
*	The bark check takes in a String (ie. a noise) and matches it to a dog in registry, if it exists the door opens and the timer is started.
*	The next method is checkScratch, it takes a boolean as its argument. If it detects a scratch then it will call announce scratch ( a DoorRemote function) to let the user know that the dog is at the door

## DoorRemote

The next class is the DoorRemote class

*	This class has two instance variables, barkRecognizer (type BarkRecognizer) and dogDoor(type DogDoor)
*	Next is a no args constructor 
*	Next is a constructor that takes an argument of barkReconizer (type BarkRecognizer), and dogDoor (type DogDoor) and sets the instance variables respectively
*	It has a pushButton method that when called, calls close() if the door is open (to close the door before the timer is up) or if the door is closed, calls open() (to open the door)
        *	Both close() and open() are methods in remote
*	Next is the aforementioned open() method which calls the DogDoor class’s functions of openDoor() and timerClose()
*	Following that is the close() function, which calls (the instance variable) dogDoor.closeDoor() which closes the door
*	getIsOpen() prints out the state of the door. If it's open  it prints "The door is open." and such
*	The addBarkKey method allows the wielder of the remote to add a dog's bark to the register
        *	It requires three arguments, name (String), bark (String), and position (boolean)
*	setDoorDelay sets the delay for the door in seconds, it requires an int argument
        *	When called, this method calls the DogDoor classes method setDoorLockDelay
*	announceScratch was mentioned above, the method simply prints out “Scratching at door”
*	The newOwner method erases the dogs register when called

## Bark

The next class is the Bark class

*	This class has one instance variable, bark (type String)
*	The first method is the Bark constructor, it takes one argument, bark (type String) and sets the instance variable
*	Next is the no args constructor
*	The setBark() method takes a String argument and sets the instance variable (this is so that if the no args constructor is used you can still set the bark, (or less likely if your dogs voice changes))
*	The getBark() method returns the sound current set (this will change for each doggie object one has)
*	The toString() method prints out what the current bark sound is.

To test all these classes together a Main class was created

*	It creates a dog and a door, links the two together and runs a series of tests
        *	These tests included opening the door, moving the dog, then having the door close
        *	They also included trying to move a dog through a closed door (the result prints out the message “(dogs name) can’t move”
*	For a more full rundown of the main, look at the main file which has the results included. It also has many comments explaining what each test is doing.
