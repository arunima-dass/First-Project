# First-Project
A notes organizer that will take our messy notes related to all sorts of topic and organize them under broader topics to make it easy to access specific things related to specific topics quickly, easily and conveniently.  


## How it works 
- Write notes in inbox.txt
- Define topics using keywords such as "Gym: workout, push, pull, weights"
- Run the program, and it writes organized.txt with each note under its topic
- Notes that match no topic go under "Uncategorized"

## How to run 
- Using the bash command ./organize.sh

## Known Limitations 
- Keywords also match inside of longer words, for example "test" matches with "latest"
- If a role matches more than one topic, then the first topic in "topics.txt" wins.

## Ideas for Future Versions: 
-Ensuring that longer words consisting of shorter words do not get mixed. 
-Clearing the inbox after sorting 
-Overriding to force a note into a topic 
