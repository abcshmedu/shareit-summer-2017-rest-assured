# Starter Code für 2. Pratkikumsaufgabe Software-Architektur Sommer 2017

Team **rest assured**.

Andrea Limmer, Wolfgang Gabler 

Heroku: https://shareit-rest-assured.herokuapp.com/


#### REST-API für Exemplare

`Copy`-Exemplare werden durch eine eindeutige Copy-ID identifiziert.

`POST /media/books/copy`
Neues Exemplar für ein Buch anlegen. JSON Payload enthält ISBN des Buches sowie Namen des Besitzers.
Mögliche Fehler: ISBN invalide, Buch für ISBN oder Nutzer nicht vorhanden. Response enthält Copy-ID des Exemplares.

`GET /media/books/copy/{copy-id}`
Eine JSON-Repräsentation eines gespeicherten Buchexemplares liefern, falls vorhanden.

`GET /media/books/copy` Alle Buchexemplare auflisten.

`PUT /media/books/copy/{copy-id}` Daten zu vorhandenem Buchexemplar modifizieren. Mögliche Fehler: Copy-ID nicht vorhanden, Felder im JSON fehlen.

Analoge REST-API für Discs unter `/media/discs/copy/*` und Barcode statt ISBN.

