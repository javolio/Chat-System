Chat-System
===========

A simple two-user chat system run over UDP. An initial connection is performed by supplying the proper IP Address and Port to the console of one end. After the initial communication is performed, two users may send messages to one another through a minimalist graphical interface. Messages can be sent and received simultaneously.

On the first computer, run either "Chat System Server.bat" or "Chat System Server.sh", depending on your operating system. Alternatively, run "Chat System Server.jar" from your command prompt or bash shell.

On the second computer, run either "Chat System Client.bat" or "Chat System Client.sh", depending on your operating system. Alternatively, run "Chat System Client.jar" from your command prompt or bash shell. Enter the IP Address and Port provided by the first computer.

At this point, a window will show up. Enter a message in the bottom field and press Enter to send it. Messages from your chat partner will show up in the top box. Your own messages will show up as well, with a caret (>) in front of them.