# Code-Projekt-3.5-2018



id | Info Message | richtung
---|---------|---------
'I' 0xFF| | server -> client
client_id | byte[1]	0xFF |
big_board | byte`[3][3]` 0xFF |
cells |byte`[9][9]` 0xFF|
active_field |byte [2] 0xFF|

id | Move Messages | richtung
---|--------------|---------
'M' 0xFF Move Message| | client -> server
coordinates | byte[2] 0xFF |

id | Win message | richtung
---|--------------|---------
'W' Win Message | | server -> client
client_id | byte[1] 0xFF | 

id | Error Message | richtung
---|--------------|---------
'E' 0xFF | | server -> client
<<<<<<< HEAD
error_code | byte[1] 0xFF |



